package com.basic.router.launcher;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import com.basic.router.core.InstrumentationHook;
import com.basic.router.core.LogisticsCenter;
import com.basic.router.exception.HandlerException;
import com.basic.router.exception.InitException;
import com.basic.router.exception.NoRouteFoundException;
import com.basic.router.facade.Postcard;
import com.basic.router.facade.callback.InterceptorCallback;
import com.basic.router.facade.callback.NavigationCallback;
import com.basic.router.facade.model.RouteMeta;
import com.basic.router.facade.service.*;
import com.basic.router.facade.template.ILogger;
import com.basic.router.facade.template.IRouteGroup;
import com.basic.router.thread.DefaultPoolExecutor;
import com.basic.router.utils.Consts;
import com.basic.router.utils.DefaultLogger;
import com.basic.router.utils.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Router core (Facade patten)
 *
 * @author Alex <a href="mailto:zhilong.liu@aliyun.com">Contact me.</a>
 * @version 1.0
 * @since 16/8/16 14:39
 */
final class _Router {
    static ILogger logger = new DefaultLogger(Consts.TAG);
    private volatile static boolean monitorMode = false;
    private volatile static boolean debuggable = false;
    private volatile static boolean autoInject = false;
    private volatile static _Router instance = null;
    private volatile static boolean hasInit = false;
    private volatile static ThreadPoolExecutor executor = DefaultPoolExecutor.getInstance();
    private static Handler mHandler;
    private static Context mContext;

    private static InterceptorService interceptorService;

    private _Router() {
    }

    protected static synchronized boolean init(Application application) {
        mContext = application;
        LogisticsCenter.init(mContext, executor);
        logger.info(Consts.TAG, "Router init success!");
        hasInit = true;
        mHandler = new Handler(Looper.getMainLooper());

        return true;
    }

    /**
     * Destroy router, it can be used only in debug mode.
     */
    static synchronized void destroy() {
        if (debuggable()) {
            hasInit = false;
            LogisticsCenter.suspend();
            logger.info(Consts.TAG, "Router destroy success!");
        } else {
            logger.error(Consts.TAG, "Destroy can be used in debug mode only!");
        }
    }

    protected static _Router getInstance() {
        if (!hasInit) {
            throw new InitException("RouterCore::Init::Invoke init(context) first!");
        } else {
            if (instance == null) {
                synchronized (_Router.class) {
                    if (instance == null) {
                        instance = new _Router();
                    }
                }
            }
            return instance;
        }
    }

    static synchronized void openDebug() {
        debuggable = true;
        logger.info(Consts.TAG, "Router openDebug");
    }

    static synchronized void openLog() {
        logger.showLog(true);
        logger.info(Consts.TAG, "Router openLog");
    }

    @Deprecated
    static synchronized void enableAutoInject() {
        autoInject = true;
    }

    @Deprecated
    static boolean canAutoInject() {
        return autoInject;
    }

    @Deprecated
    static void attachBaseContext() {
        Log.i(Consts.TAG, "Router start attachBaseContext");
        try {
            Class<?> mMainThreadClass = Class.forName("android.app.ActivityThread");

            // Get current main thread.
            Method getMainThread = mMainThreadClass.getDeclaredMethod("currentActivityThread");
            getMainThread.setAccessible(true);
            Object currentActivityThread = getMainThread.invoke(null);

            // The field contain instrumentation.
            Field mInstrumentationField = mMainThreadClass.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);

            // Hook current instrumentation
            mInstrumentationField.set(currentActivityThread, new InstrumentationHook());
            Log.i(Consts.TAG, "Router hook instrumentation success!");
        } catch (Exception ex) {
            Log.e(Consts.TAG, "Router hook instrumentation failed! [" + ex.getMessage() + "]");
        }
    }

    static synchronized void printStackTrace() {
        logger.showStackTrace(true);
        logger.info(Consts.TAG, "Router printStackTrace");
    }

    static synchronized void setExecutor(ThreadPoolExecutor tpe) {
        executor = tpe;
    }

    static synchronized void monitorMode() {
        monitorMode = true;
        logger.info(Consts.TAG, "Router monitorMode on");
    }

    static boolean isMonitorMode() {
        return monitorMode;
    }

    static boolean debuggable() {
        return debuggable;
    }

    static void setLogger(ILogger userLogger) {
        if (null != userLogger) {
            logger = userLogger;
        }
    }

    static void inject(Object thiz) {
        AutowiredService autowiredService = ((AutowiredService) Router.getInstance().build("/router/service/autowired").navigation());
        if (null != autowiredService) {
            autowiredService.autowire(thiz);
        }
    }

    /**
     * Build postcard by path and default group
     */
    protected Postcard build(String path) {
        if (TextUtils.isEmpty(path)) {
            throw new HandlerException(Consts.TAG + "Parameter is invalid!");
        } else {
            PathReplaceService pService = Router.getInstance().navigation(PathReplaceService.class);
            if (null != pService) {
                path = pService.forString(path);
            }
            return build(path, extractGroup(path), true);
        }
    }

    /**
     * Build postcard by uri
     */
    protected Postcard build(Uri uri) {
        if (null == uri || TextUtils.isEmpty(uri.toString())) {
            throw new HandlerException(Consts.TAG + "Parameter invalid!");
        } else {
            PathReplaceService pService = Router.getInstance().navigation(PathReplaceService.class);
            if (null != pService) {
                uri = pService.forUri(uri);
            }
            return new Postcard(uri.getPath(), extractGroup(uri.getPath()), uri, null);
        }
    }

    /**
     * Build postcard by path and group
     */
    protected Postcard build(String path, String group, Boolean afterReplace) {
        if (TextUtils.isEmpty(path) || TextUtils.isEmpty(group)) {
            throw new HandlerException(Consts.TAG + "Parameter is invalid!");
        } else {
            if (!afterReplace) {
                PathReplaceService pService = Router.getInstance().navigation(PathReplaceService.class);
                if (null != pService) {
                    path = pService.forString(path);
                }
            }
            return new Postcard(path, group);
        }
    }

    /**
     * Extract the default group from path.
     */
    private String extractGroup(String path) {
        if (TextUtils.isEmpty(path) || !path.startsWith("/")) {
            throw new HandlerException(Consts.TAG + "Extract the default group failed, the path must be start with '/' and contain more than 2 '/'!");
        }

        try {
            String defaultGroup = path.substring(1, path.indexOf("/", 1));
            if (TextUtils.isEmpty(defaultGroup)) {
                throw new HandlerException(Consts.TAG + "Extract the default group failed! There's nothing between 2 '/'!");
            } else {
                return defaultGroup;
            }
        } catch (Exception e) {
            logger.warning(Consts.TAG, "Failed to extract default group! " + e.getMessage());
            return null;
        }
    }

    static void afterInit() {
        // Trigger interceptor init, use byName.
        interceptorService = (InterceptorService) Router.getInstance().build("/router/service/interceptor").navigation();
    }

    protected <T> T navigation(Class<? extends T> service) {
        try {
            Postcard postcard = LogisticsCenter.buildProvider(service.getName());

            // Compatible 1.0.5 compiler sdk.
            // Earlier versions did not use the fully qualified name to get the service
            if (null == postcard) {
                // No service, or this service in old version.
                postcard = LogisticsCenter.buildProvider(service.getSimpleName());
            }

            if (null == postcard) {
                return null;
            }

            // Set application to postcard.
            postcard.setContext(mContext);

            LogisticsCenter.completion(postcard);
            return (T) postcard.getProvider();
        } catch (NoRouteFoundException ex) {
            logger.warning(Consts.TAG, ex.getMessage());
            return null;
        }
    }

    /**
     * Use router navigation.
     *
     * @param context     Activity or null.
     * @param postcard    Route metas
     * @param requestCode RequestCode
     * @param callback    cb
     */
    protected Object navigation(final Context context, final Postcard postcard, final int requestCode, final NavigationCallback callback) {
        PretreatmentService pretreatmentService = Router.getInstance().navigation(PretreatmentService.class);
        if (null != pretreatmentService && !pretreatmentService.onPretreatment(context, postcard)) {
            // Pretreatment failed, navigation canceled.
            return null;
        }

        // Set context to postcard.
        postcard.setContext(null == context ? mContext : context);

        try {
            LogisticsCenter.completion(postcard);
        } catch (NoRouteFoundException ex) {
            logger.warning(Consts.TAG, ex.getMessage());

            if (debuggable()) {
                // Show friendly tips for user.
                runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "There's no route matched!\n" +
                                " Path = [" + postcard.getPath() + "]\n" +
                                " Group = [" + postcard.getGroup() + "]", Toast.LENGTH_LONG).show();
                    }
                });
            }

            if (null != callback) {
                callback.onLost(postcard);
            } else {
                // No callback for this invoke, then we use the global degrade service.
                DegradeService degradeService = Router.getInstance().navigation(DegradeService.class);
                if (null != degradeService) {
                    degradeService.onLost(context, postcard);
                }
            }

            return null;
        }

        if (null != callback) {
            callback.onFound(postcard);
        }

        if (!postcard.isGreenChannel()) {   // It must be run in async thread, maybe interceptor cost too mush time made ANR.
            interceptorService.doInterceptions(postcard, new InterceptorCallback() {
                /**
                 * Continue process
                 *
                 * @param postcard route meta
                 */
                @Override
                public void onContinue(Postcard postcard) {
                    _navigation(postcard, requestCode, callback);
                }

                /**
                 * Interrupt process, pipeline will be destory when this method called.
                 *
                 * @param exception Reson of interrupt.
                 */
                @Override
                public void onInterrupt(Throwable exception) {
                    if (null != callback) {
                        callback.onInterrupt(postcard);
                    }

                    logger.info(Consts.TAG, "Navigation failed, termination by interceptor : " + exception.getMessage());
                }
            });
        } else {
            return _navigation(postcard, requestCode, callback);
        }

        return null;
    }

    private Object _navigation(final Postcard postcard, final int requestCode, final NavigationCallback callback) {
        final Context currentContext = postcard.getContext();

        switch (postcard.getType()) {
            case ACTIVITY:
                // Build intent
                final Intent intent = new Intent(currentContext, postcard.getDestination());
                intent.putExtras(postcard.getExtras());

                // Set flags.
                int flags = postcard.getFlags();
                if (0 != flags) {
                    intent.setFlags(flags);
                }

                // Non activity, need FLAG_ACTIVITY_NEW_TASK
                if (!(currentContext instanceof Activity)) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }

                // Set Actions
                String action = postcard.getAction();
                if (!TextUtils.isEmpty(action)) {
                    intent.setAction(action);
                }

                // Navigation in main looper.
                runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(requestCode, currentContext, intent, postcard, callback);
                    }
                });

                break;
            case PROVIDER:
                return postcard.getProvider();
            case BOARDCAST:
            case CONTENT_PROVIDER:
            case FRAGMENT:
                Class<?> fragmentMeta = postcard.getDestination();
                try {
                    Object instance = fragmentMeta.getConstructor().newInstance();
                    if (instance instanceof Fragment) {
                        ((Fragment) instance).setArguments(postcard.getExtras());
                    } else if (instance instanceof androidx.fragment.app.Fragment) {
                        ((androidx.fragment.app.Fragment) instance).setArguments(postcard.getExtras());
                    }

                    return instance;
                } catch (Exception ex) {
                    logger.error(Consts.TAG, "Fetch fragment instance error, " + TextUtils.formatStackTrace(ex.getStackTrace()));
                }
            case METHOD:
            case SERVICE:
            default:
                return null;
        }

        return null;
    }

    /**
     * Be sure execute in main thread.
     *
     * @param runnable code
     */
    private void runInMainThread(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            mHandler.post(runnable);
        } else {
            runnable.run();
        }
    }

    /**
     * Start activity
     *
     * @see ActivityCompat
     */
    private void startActivity(int requestCode, Context currentContext, Intent intent, Postcard postcard, NavigationCallback callback) {
        if (requestCode >= 0) {  // Need start for result
            if (currentContext instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) currentContext, intent, requestCode, postcard.getOptionsBundle());
            } else {
                logger.warning(Consts.TAG, "Must use [navigation(activity, ...)] to support [startActivityForResult]");
            }
        } else {
            ActivityCompat.startActivity(currentContext, intent, postcard.getOptionsBundle());
        }

        if ((-1 != postcard.getEnterAnim() && -1 != postcard.getExitAnim()) && currentContext instanceof Activity) {    // Old version.
            ((Activity) currentContext).overridePendingTransition(postcard.getEnterAnim(), postcard.getExitAnim());
        }

        if (null != callback) { // Navigation over.
            callback.onArrival(postcard);
        }
    }

    boolean addRouteGroup(IRouteGroup group) {
        if (null == group) {
            return false;
        }

        String groupName = null;

        try {
            // Extract route meta.
            Map<String, RouteMeta> dynamicRoute = new HashMap<>();
            group.loadInto(dynamicRoute);

            // Check route meta.
            for (Map.Entry<String, RouteMeta> route : dynamicRoute.entrySet()) {
                String path = route.getKey();
                String groupByExtract = extractGroup(path);
                RouteMeta meta = route.getValue();

                if (null == groupName) {
                    groupName = groupByExtract;
                }

                if (null == groupName || !groupName.equals(groupByExtract) || !groupName.equals(meta.getGroup())) {
                    // Group name not consistent
                    return false;
                }
            }

            LogisticsCenter.addRouteGroupDynamic(groupName, group);

            logger.info(Consts.TAG, "Add route group [" + groupName + "] finish, " + dynamicRoute.size() + " new route meta.");

            return true;
        } catch (Exception exception) {
            logger.error(Consts.TAG, "Add route group dynamic exception!", exception);
        }

        return false;
    }
}

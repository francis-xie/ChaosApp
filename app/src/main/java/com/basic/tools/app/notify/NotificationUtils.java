

package com.basic.tools.app.notify;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationManagerCompat;

import com.basic.tools.XUtil;
import com.basic.tools.app.notify.builder.BaseBuilder;
import com.basic.tools.app.notify.builder.BigPicBuilder;
import com.basic.tools.app.notify.builder.BigTextBuilder;
import com.basic.tools.app.notify.builder.CustomViewBuilder;
import com.basic.tools.app.notify.builder.MailboxBuilder;
import com.basic.tools.app.notify.builder.ProgressBuilder;

/**
 * <pre>
 *     desc   : 通知栏工具类
 
 *     time   : 2018/4/28 上午12:25
 * </pre>
 */
public final class NotificationUtils {

    private static NotificationManager sNotificationManager;

    private NotificationUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    //==================通知的api======================//

    /**
     * 构建简单的通知
     *
     * @param id            通知的ID
     * @param smallIcon     顶部状态栏的小图标
     * @param contentTitle  通知中心的标题
     * @param contentText   通知中心中的内容
     * @param contentIntent 通知点击的事件
     * @return
     */
    public static BaseBuilder buildSimple(int id, int smallIcon, CharSequence contentTitle, CharSequence contentText, PendingIntent contentIntent) {
        return new BaseBuilder()
                .setId(id)
                .setBaseInfo(smallIcon, contentTitle, contentText)
                .setContentIntent(contentIntent);
    }

    /**
     * 构建带图片的通知
     *
     * @param id           通知的ID
     * @param smallIcon    顶部状态栏的小图标
     * @param contentTitle 通知中心的标题
     * @param summaryText  图片的概要信息
     * @return
     */
    public static BigPicBuilder buildBigPic(int id, int smallIcon, CharSequence contentTitle, CharSequence summaryText) {
        return new BigPicBuilder()
                .setId(id)
                .setSmallIcon(smallIcon)
                .setContentTitle(contentTitle)
                .setSummaryText(summaryText);
    }

    /**
     * 构建多文本通知
     *
     * @param id           通知的ID
     * @param smallIcon    顶部状态栏的小图标
     * @param contentTitle 通知中心的标题
     * @param contentText  通知中心中的内容
     * @return
     */
    public static BigTextBuilder buildBigText(int id, int smallIcon, CharSequence contentTitle, CharSequence contentText) {
        return new BigTextBuilder()
                .setId(id)
                .setBaseInfo(smallIcon, contentTitle, contentText);
    }

    /**
     * 构建带多条消息合并的消息盒通知
     *
     * @param id           通知的ID
     * @param smallIcon    顶部状态栏的小图标
     * @param contentTitle 通知中心的标题
     * @return
     */
    public static MailboxBuilder buildMailBox(int id, int smallIcon, CharSequence contentTitle) {
        return new MailboxBuilder()
                .setId(id)
                .setSmallIcon(smallIcon)
                .setContentTitle(contentTitle);
    }


    /**
     * 构建带进度条的通知
     *
     * @param id           通知的ID
     * @param smallIcon    顶部状态栏的小图标
     * @param contentTitle 顶部状态栏的小图标
     * @param max          最大进度
     * @param progress     目前的进度
     * @return
     */
    public static ProgressBuilder buildProgress(int id, int smallIcon, CharSequence contentTitle, int max, int progress) {
        return new ProgressBuilder()
                .setProgress(max, progress)
                .setId(id)
                .setSmallIcon(smallIcon)
                .setContentTitle(contentTitle);

    }

    /**
     * 构建无精确进度的通知
     *
     * @param id           通知的ID
     * @param smallIcon    顶部状态栏的小图标
     * @param contentTitle 顶部状态栏的小图标
     * @return
     */
    public static ProgressBuilder buildProgress(int id, int smallIcon, CharSequence contentTitle) {
        return new ProgressBuilder()
                .setIndeterminate(true)
                .setId(id)
                .setSmallIcon(smallIcon)
                .setContentTitle(contentTitle);
    }

    /**
     * 构建自定义通知
     *
     * @param id           通知的ID
     * @param smallIcon    顶部状态栏的小图标
     * @param contentTitle 顶部状态栏的小图标
     * @param packageName  包名
     * @param layoutId     自定义布局资源id
     * @return
     */
    public static CustomViewBuilder buildCustomView(int id, int smallIcon, CharSequence contentTitle, String packageName, int layoutId) {
        return new CustomViewBuilder(packageName, layoutId)
                .setId(id)
                .setSmallIcon(smallIcon)
                .setContentTitle(contentTitle);
    }

    //===================通知栏权限设置==================//

    /**
     * 通知栏权限是否获取
     *
     * @param context
     * @return
     */
    public static boolean isNotifyPermissionOpen(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return NotificationManagerCompat.from(context).getImportance() != NotificationManager.IMPORTANCE_NONE;
        }
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    /**
     * 打开通知栏权限设置页面
     *
     * @param context
     */
    public static void openNotifyPermissionSetting(Context context) {
        try {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //直接跳转到应用通知设置的代码：
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, context.getApplicationInfo().uid);
                context.startActivity(intent);
                return;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("app_package", context.getPackageName());
                intent.putExtra("app_uid", context.getApplicationInfo().uid);
                context.startActivity(intent);
                return;
            }
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivity(intent);
                return;
            }

            //4.4以下没有从app跳转到应用通知设置页面的Action，可考虑跳转到应用详情页面,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                context.startActivity(intent);
                return;
            }

            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            context.startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //=======================================================//


    /**
     * 通知
     *
     * @param id           通知ID
     * @param notification 通知的内容
     */
    public static void notify(int id, Notification notification) {
        if (sNotificationManager == null) {
            sNotificationManager = getNotificationManager();
        }
        sNotificationManager.notify(id, notification);
    }

    /**
     * 取消通知
     *
     * @param id 通知ID
     */
    public static void cancel(int id) {
        if (sNotificationManager == null) {
            sNotificationManager = getNotificationManager();
        }
        sNotificationManager.cancel(id);
    }

    /**
     * 取消所有通知
     */
    public static void cancelAll() {
        if (sNotificationManager == null) {
            sNotificationManager = getNotificationManager();
        }
        sNotificationManager.cancelAll();
    }

    public static NotificationManager getNotificationManager() {
        return (NotificationManager) XUtil.getContext().getSystemService(Activity.NOTIFICATION_SERVICE);
    }

    public static NotificationManager getManager() {
        if (sNotificationManager == null) {
            sNotificationManager = getNotificationManager();
        }
        return sNotificationManager;
    }
}

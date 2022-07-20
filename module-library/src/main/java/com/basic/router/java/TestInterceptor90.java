

package com.basic.router.java;

import android.content.Context;
import android.util.Log;
import com.basic.router.annotation.Interceptor;
import com.basic.router.facade.Postcard;
import com.basic.router.facade.callback.InterceptorCallback;
import com.basic.router.facade.template.IInterceptor;

/**
 *
 * @since 2018/5/22 下午2:32
 */
@Interceptor(priority = 90)
public class TestInterceptor90 implements IInterceptor {
    /**
     * The operation of this tollgate.
     *
     * @param postcard meta
     * @param callback cb
     */
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        callback.onContinue(postcard);
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    @Override
    public void init(Context context) {
        Log.e("test", "位于moudle1中的拦截器初始化了");
    }
}

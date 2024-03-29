

package com.basic.router.facade.service;

import com.basic.router.facade.Postcard;
import com.basic.router.facade.callback.InterceptorCallback;
import com.basic.router.facade.template.IProvider;

/**
 * 拦截服务
 * @since 2018/5/17 上午1:00
 */
public interface InterceptorService extends IProvider {

    /**
     * 执行拦截操作
     */
    void doInterceptions(Postcard postcard, InterceptorCallback callback);
}

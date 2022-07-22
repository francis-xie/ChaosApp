package com.basic.router.facade.service;

import com.basic.router.facade.Postcard;
import com.basic.router.facade.callback.InterceptorCallback;
import com.basic.router.facade.template.IProvider;

/**
 * Interceptor service
 */
public interface InterceptorService extends IProvider {

    /**
     * Do interceptions
     */
    void doInterceptions(Postcard postcard, InterceptorCallback callback);
}

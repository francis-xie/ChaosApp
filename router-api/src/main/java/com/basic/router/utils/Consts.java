package com.basic.router.utils;

/**
 * Router constants.
 *
 * @author Alex <a href="mailto:zhilong.liu@aliyun.com">Contact me.</a>
 * @version 1.0
 * @since 16/8/23 9:38
 */
public final class Consts {
    public static final String SDK_NAME = "Router";
    public static final String TAG = SDK_NAME + "::";
    public static final String SEPARATOR = "$$";
    public static final String SUFFIX_ROOT = "Root";
    public static final String SUFFIX_INTERCEPTORS = "Interceptors";
    public static final String SUFFIX_PROVIDERS = "Providers";
    public static final String SUFFIX_AUTOWIRED = SEPARATOR + SDK_NAME + SEPARATOR + "Autowired";
    public static final String DOT = ".";
    public static final String ROUTE_ROOT_PAKCAGE = "com.basic.router.routes";

    public static final String ROUTER_SP_CACHE_KEY = "SP_ROUTER_CACHE";
    public static final String ROUTER_SP_KEY_MAP = "ROUTER_MAP";

    public static final String LAST_VERSION_NAME = "LAST_VERSION_NAME";
    public static final String LAST_VERSION_CODE = "LAST_VERSION_CODE";
}

package com.basic.router.compiler.util;

/**
 * 常量
 * @since 2018/5/21 下午6:17
 */
public class Consts {
    // Generate
    public static final String SEPARATOR = "$$";
    public static final String PROJECT = "app";
    public static final String PACKAGE_OF_XROUTER = "com.basic.router";
    public static final String TAG = PROJECT + "::";
    public static final String WARNING_TIPS = "DO NOT EDIT THIS FILE!!! IT WAS GENERATED BY XROUTER.";
    public static final String METHOD_LOAD_INTO = "loadInto";
    public static final String METHOD_INJECT = "inject";
    public static final String NAME_OF_ROOT = PROJECT + SEPARATOR + "Root" + SEPARATOR;
    public static final String NAME_OF_PROVIDER = PROJECT + SEPARATOR + "Providers" + SEPARATOR;
    public static final String NAME_OF_GROUP = PROJECT + SEPARATOR + "Group" + SEPARATOR;
    public static final String NAME_OF_INTERCEPTOR = PROJECT + SEPARATOR + "Interceptors" + SEPARATOR;
    public static final String NAME_OF_AUTOWIRED = SEPARATOR + PROJECT + SEPARATOR + "AutoWired";
    public static final String PACKAGE_OF_GENERATE_FILE = PACKAGE_OF_XROUTER + ".routes";

    // System interface
    public static final String ACTIVITY = "android.app.Activity";
    public static final String FRAGMENT = "android.app.Fragment";
    public static final String FRAGMENT_V4 = "androidx.fragment.app.Fragment";
    public static final String SERVICE = "android.app.Service";
    public static final String PARCELABLE = "android.os.Parcelable";

    // Java type
    private static final String LANG = "java.lang";
    public static final String BYTE = LANG + ".Byte";
    public static final String SHORT = LANG + ".Short";
    public static final String INTEGER = LANG + ".Integer";
    public static final String LONG = LANG + ".Long";
    public static final String FLOAT = LANG + ".Float";
    public static final String DOUBEL = LANG + ".Double";
    public static final String BOOLEAN = LANG + ".Boolean";
    public static final String STRING = LANG + ".String";

    // Custom interface
    private static final String FACADE_PACKAGE = PACKAGE_OF_XROUTER+ ".facade";
    private static final String TEMPLATE_PACKAGE = ".template";
    private static final String SERVICE_PACKAGE = ".service";
    public static final String IPROVIDER = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IProvider";
    public static final String IPROVIDER_GROUP = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IProviderGroup";
    public static final String IINTERCEPTOR = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IInterceptor";
    public static final String IINTERCEPTOR_GROUP = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IInterceptorGroup";
    public static final String ITROUTE_ROOT = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IRouteRoot";
    public static final String IROUTE_GROUP = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IRouteGroup";
    public static final String ISYRINGE = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".ISyringe";
    public static final String SERIALIZATION_SERVICE = FACADE_PACKAGE + SERVICE_PACKAGE + ".SerializationService";

    // Log
    public static final String PREFIX_OF_LOGGER = PROJECT + "::Compiler ";

    // Options of processor
    public static final String KEY_MODULE_NAME = "moduleName";

    // Annotation type
    public static final String ANNOTATION_TYPE_INTECEPTOR = PACKAGE_OF_XROUTER + ".annotation.Interceptor";
    public static final String ANNOTATION_TYPE_ROUTE = PACKAGE_OF_XROUTER + ".annotation.Router";
    public static final String ANNOTATION_TYPE_AUTOWIRED = PACKAGE_OF_XROUTER + ".annotation.AutoWired";
}
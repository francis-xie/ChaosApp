

package com.basic.code.utils.sdkinit;

import android.app.Application;

import com.basic.aop.XAOP;
import com.basic.dbms.XUIDataBaseRepository;
import com.basic.dbms.logs.DBLog;
import com.basic.leaf.PageConfig;
import com.basic.router.launcher.Router;
import com.basic.code.MyApp;
import com.basic.code.base.BaseActivity;
import com.basic.code.base.db.InternalDataBase;
import com.basic.code.utils.TokenUtils;
import com.basic.code.utils.XToastUtils;
import com.basic.tools.XUtil;
import com.basic.tools.common.StringUtils;

/**
 * X系列基础库的初始化
 *

 * @since 2019-07-06 9:24
 */
public final class XBasicLibInit {

    private XBasicLibInit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化基础库
     */
    public static void init(Application application) {
        initUtils(application);
        initPage(application);
        initAOP(application);
        initRouter(application);
        initDB(application);
    }

    /**
     * 初始化工具类
     *
     * @param application 应用上下文
     */
    private static void initUtils(Application application) {
        XUtil.init(application);
        XUtil.debug(MyApp.isDebug());
        TokenUtils.init(application);
    }


    /**
     * 初始化XPage页面框架
     *
     * @param application
     */
    private static void initPage(Application application) {
        //自动注册页面
        PageConfig.getInstance()
                .debug(MyApp.isDebug() ? "PageLog" : null)
                .setContainActivityClazz(BaseActivity.class)
                .init(application);
    }

    /**
     * 初始化XAOP切片框架
     *
     * @param application
     */
    private static void initAOP(Application application) {
        //初始化插件
        XAOP.init(application);
        //日志打印切片开启
        XAOP.debug(MyApp.isDebug());
        //设置动态申请权限切片 申请权限被拒绝的事件响应监听
        XAOP.setOnPermissionDeniedListener(permissionsDenied -> XToastUtils.error("权限申请被拒绝:" + StringUtils.listToString(permissionsDenied, ",")));
    }

    /**
     * 初始化Router路由
     *
     * @param application
     */
    private static void initRouter(Application application) {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (MyApp.isDebug()) {
            Router.openLog();     // 打印日志
            Router.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        Router.init(application);
    }

    /**
     * 初始化数据库框架
     *
     * @param application
     */
    private static void initDB(Application application) {
        XUIDataBaseRepository.getInstance()
                //设置内部存储的数据库实现接口
                .setIDatabase(new InternalDataBase())
                .init(application);
        DBLog.debug(MyApp.isDebug());
    }

//    /**
//     * 初始化video的存放路径[xvideo项目太大，去除]
//     */
//    public static void initVideo() {
//        XVideo.setVideoCachePath(PathUtils.getExtDcimPath() + "/xvideo/");
//        // 初始化拍摄
//        XVideo.initialize(false, null);
//    }


}

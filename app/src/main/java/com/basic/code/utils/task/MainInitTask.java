

package com.basic.code.utils.task;

import android.app.Application;

import androidx.annotation.NonNull;

import com.mikepenz.iconics.Iconics;
import com.basic.duty.api.step.SimpleTaskStep;
import com.basic.duty.core.ThreadType;
import com.basic.face.XUI;
import com.basic.code.MyApp;
import com.basic.code.utils.SettingSPUtils;
import com.basic.code.utils.sdkinit.XBasicLibInit;
import com.basic.code.utils.sdkinit.XUpdateInit;
import com.basic.code.widget.iconfont.XUIIconFont;

/**
 * 主要初始化任务，放在第一位执行
 *

 * @since 2/17/22 12:43 AM
 */
public class MainInitTask extends SimpleTaskStep {

    private Application mApplication;

    /**
     * 构造方法
     *
     * @param application 应用上下文
     */
    public MainInitTask(Application application) {
        mApplication = application;
    }

    @Override
    public void doTask() throws Exception {
        // 初始化基础库
        XBasicLibInit.init(mApplication);
        // 初始化UI框架
        initUi();
        // XUpdate版本更新
        XUpdateInit.init(mApplication);
    }

    /**
     * 初始化UI框架
     */
    private void initUi() {
        XUI.debug(MyApp.isDebug());
        if (SettingSPUtils.getInstance().isUseCustomFont()) {
            //设置默认字体为华文行楷
            XUI.initFontStyle("fonts/hwxk.ttf");
        }
        //字体图标库
        Iconics.init(mApplication);
        //这是自己定义的图标库
        Iconics.registerFont(new XUIIconFont());
    }

    @Override
    public String getName() {
        return "MainInitTask";
    }

    @NonNull
    @Override
    public ThreadType getThreadType() {
        return ThreadType.SYNC;
    }
}

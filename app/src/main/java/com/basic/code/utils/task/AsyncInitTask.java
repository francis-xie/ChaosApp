

package com.basic.code.utils.task;

import android.app.Application;

import androidx.annotation.NonNull;

import com.basic.camera.CameraView;
import com.basic.image.tools.PictureFileUtils;
import com.basic.duty.api.step.SimpleTaskStep;
import com.basic.duty.core.ThreadType;
import com.basic.code.utils.LocationService;
import com.basic.code.utils.sdkinit.ANRWatchDogInit;
import com.basic.code.utils.sdkinit.AutoCameraStrategy;
import com.basic.code.utils.sdkinit.TbsInit;
import com.basic.code.utils.sdkinit.UMengInit;

/**
 * 异步初始化任务
 * 放一些优先级不是很高的、耗时的初始化任务
 *
 
 * @since 2/17/22 12:26 AM
 */
public class AsyncInitTask extends SimpleTaskStep {

    private Application mApplication;

    /**
     * 构造方法
     *
     * @param application 应用上下文
     */
    public AsyncInitTask(Application application) {
        mApplication = application;
    }

    @Override
    public void doTask() throws Exception {
        // 友盟SDK
        UMengInit.init(mApplication);
        // ANR监控
        ANRWatchDogInit.init();

        TbsInit.init(mApplication);
        // 百度定位
        LocationService.get().init(mApplication);
        // 图片路径
        PictureFileUtils.setAppName("xui");
        // 照相机
        CameraView.setICameraStrategy(new AutoCameraStrategy(1920 * 1080));
    }

    @Override
    public String getName() {
        return "AsyncInitTask";
    }

    @NonNull
    @Override
    public ThreadType getThreadType() {
        return ThreadType.ASYNC;
    }
}



package com.basic.duty.api.step;

import androidx.annotation.NonNull;

import com.basic.duty.core.ThreadType;
import com.basic.duty.core.param.ITaskParam;
import com.basic.duty.core.step.impl.AbstractTaskStep;
import com.basic.duty.core.step.impl.AutoNotifyTaskStepHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 简单的任务执行步骤
 *

 * @since 2/1/22 11:11 PM
 */
public abstract class SimpleTaskStep extends AbstractTaskStep {

    private static final AtomicInteger TASK_NUMBER = new AtomicInteger(1);

    /**
     * 任务名称
     */
    private String mName;

    /**
     * 构造方法
     */
    public SimpleTaskStep() {
        this("SimpleTaskStep-" + TASK_NUMBER.getAndIncrement());
    }

    /**
     * 构造方法
     *
     * @param name 任务名称
     */
    public SimpleTaskStep(String name) {
        initTaskStep(name);
    }

    /**
     * 构造方法
     *
     * @param name       任务名称
     * @param threadType 线程类型
     */
    public SimpleTaskStep(String name, ThreadType threadType) {
        super(threadType);
        initTaskStep(name);
    }

    /**
     * 构造方法
     *
     * @param name      任务名称
     * @param taskParam 任务参数
     */
    public SimpleTaskStep(String name, @NonNull ITaskParam taskParam) {
        super(taskParam);
        initTaskStep(name);
    }

    /**
     * 构造方法
     *
     * @param name       任务名称
     * @param threadType 线程类型
     * @param taskParam  任务参数
     */
    public SimpleTaskStep(String name, ThreadType threadType, @NonNull ITaskParam taskParam) {
        super(threadType, taskParam);
        initTaskStep(name);
    }

    /**
     * 初始化任务步骤
     *
     * @param name 任务步骤名
     */
    protected void initTaskStep(String name) {
        mName = name;
        if (isAutoNotify()) {
            setTaskStepHandler(new AutoNotifyTaskStepHandler());
        }
    }


    @Override
    public String getName() {
        return mName;
    }

    /**
     * 是否自动通知执行结果【需要手动控制的请设置false】
     *
     * @return 是否自动通知执行结果
     */
    protected boolean isAutoNotify() {
        return true;
    }
}

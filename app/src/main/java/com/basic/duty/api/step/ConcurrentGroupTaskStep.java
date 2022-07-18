

package com.basic.duty.api.step;

import androidx.annotation.NonNull;

import com.basic.duty.core.param.ITaskParam;
import com.basic.duty.core.param.ITaskResult;
import com.basic.duty.core.step.ITaskStep;
import com.basic.duty.core.step.impl.AbstractGroupTaskStep;
import com.basic.duty.thread.pool.ICancelable;
import com.basic.duty.utils.TaskUtils;

/**
 * 并行任务组(不进行具体的任务）
 *

 * @since 2021/11/8 2:04 AM
 */
public class ConcurrentGroupTaskStep extends AbstractGroupTaskStep {

    /**
     * 获取并行任务组
     *
     * @return 并行任务组
     */
    public static ConcurrentGroupTaskStep get() {
        return new ConcurrentGroupTaskStep();
    }

    /**
     * 获取并行任务组
     *
     * @param name 任务组名称
     * @return 并行任务组
     */
    public static ConcurrentGroupTaskStep get(String name) {
        return new ConcurrentGroupTaskStep(name);
    }

    public ConcurrentGroupTaskStep() {
        super();
    }

    public ConcurrentGroupTaskStep(String name) {
        super(name);
    }

    public ConcurrentGroupTaskStep(String name, @NonNull ITaskParam taskParam) {
        super(name, taskParam);
    }

    @Override
    public void doTask() throws Exception {
        initGroupTask();
        if (mTaskTotalSize > 0) {
            for (ITaskStep taskStep : getTasks()) {
                if (taskStep != null && taskStep.accept()) {
                    taskStep.prepareTask(getResult());
                    ICancelable cancelable = TaskUtils.executeTask(taskStep);
                    taskStep.setCancelable(cancelable);
                }
            }
        } else {
            notifyTaskSucceed(getResult());
        }
    }

    @Override
    public void onTaskStepCompleted(@NonNull ITaskStep step, @NonNull ITaskResult result) {
        getResult().saveResultNotPath(result);
        getResult().addGroupPath(step.getName(), mTaskIndex.getAndIncrement(), mTaskTotalSize);
        if (mTaskIndex.get() == mTaskTotalSize) {
            notifyTaskSucceed(result);
        }
    }

    @Override
    public void onTaskStepError(@NonNull ITaskStep step, @NonNull ITaskResult result) {
        if (mTaskIndex.get() != -1) {
            // 并行任务，只通知一次失败
            notifyTaskFailed(result);
            mTaskIndex.set(-1);
        }
    }
}

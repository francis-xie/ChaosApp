

package com.basic.duty.api.step;

import androidx.annotation.NonNull;

import com.basic.duty.core.param.ITaskParam;
import com.basic.duty.core.param.ITaskResult;
import com.basic.duty.core.step.ITaskStep;
import com.basic.duty.core.step.impl.AbstractGroupTaskStep;
import com.basic.duty.thread.pool.ICancelable;
import com.basic.duty.utils.TaskUtils;

/**
 * 串行任务组(不进行具体的任务）
 *

 * @since 2021/11/8 2:02 AM
 */
public class SerialGroupTaskStep extends AbstractGroupTaskStep {

    /**
     * 获取串行任务组
     *
     * @return 串行任务组
     */
    public static SerialGroupTaskStep get() {
        return new SerialGroupTaskStep();
    }

    /**
     * 获取串行任务组
     *
     * @param name 任务组名称
     * @return 串行任务组
     */
    public static SerialGroupTaskStep get(String name) {
        return new SerialGroupTaskStep(name);
    }

    public SerialGroupTaskStep() {
        super();
    }

    public SerialGroupTaskStep(String name) {
        super(name);
    }

    public SerialGroupTaskStep(String name, @NonNull ITaskParam taskParam) {
        super(name, taskParam);
    }

    @Override
    public void doTask() throws Exception {
        ITaskStep firstTaskStep = TaskUtils.findNextTaskStep(getTasks(), null);
        if (firstTaskStep != null) {
            initGroupTask();
            firstTaskStep.prepareTask(getResult());
            ICancelable cancelable = TaskUtils.executeTask(firstTaskStep);
            firstTaskStep.setCancelable(cancelable);
        } else {
            notifyTaskSucceed(getResult());
        }
    }

    @Override
    public void onTaskStepCompleted(@NonNull ITaskStep step, @NonNull ITaskResult result) {
        getResult().saveResultNotPath(result);
        getResult().addGroupPath(step.getName(), mTaskIndex.getAndIncrement(), mTaskTotalSize);
        ITaskStep nextTaskStep = TaskUtils.findNextTaskStep(getTasks(), step);
        if (nextTaskStep != null) {
            // 更新数据，将上一个task的结果更新到下一个task
            nextTaskStep.prepareTask(getResult());
            ICancelable cancelable = TaskUtils.executeTask(nextTaskStep);
            nextTaskStep.setCancelable(cancelable);
        } else {
            notifyTaskSucceed(result);
        }
    }

    @Override
    public void onTaskStepError(@NonNull ITaskStep step, @NonNull ITaskResult result) {
        notifyTaskFailed(result);
    }

}



package com.basic.duty.core.step.impl;

import androidx.annotation.NonNull;

import com.basic.duty.core.param.ITaskParam;
import com.basic.duty.core.param.impl.TaskParam;
import com.basic.duty.core.param.impl.TaskResult;
import com.basic.duty.core.step.IGroupTaskStep;
import com.basic.duty.core.step.ITaskStep;
import com.basic.duty.core.step.ITaskStepLifecycle;
import com.basic.duty.logger.TaskLogger;
import com.basic.duty.utils.CommonUtils;
import com.basic.duty.utils.TaskUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 抽象任务组(不进行具体的任务）
 *

 * @since 2/1/22 11:27 PM
 */
public abstract class AbstractGroupTaskStep extends AbstractTaskStep implements ITaskStepLifecycle, IGroupTaskStep {

    private static final String TAG = TaskLogger.getLogTag("AbstractGroupTaskStep");

    /**
     * 任务组的编号【静态全局】
     */
    private static final AtomicInteger GROUP_TASK_NUMBER = new AtomicInteger(1);

    /**
     * 任务组名称
     */
    private String mName;
    /**
     * 任务结果
     */
    private TaskResult mResult = new TaskResult();

    /**
     * 执行任务集合
     */
    private List<ITaskStep> mTasks = new CopyOnWriteArrayList<>();

    /**
     * 任务执行索引
     */
    protected AtomicInteger mTaskIndex = new AtomicInteger(0);

    /**
     * 任务执行总数
     */
    protected int mTaskTotalSize;

    /**
     * 构造方法
     */
    public AbstractGroupTaskStep() {
        mName = "GroupTaskStep-" + GROUP_TASK_NUMBER.getAndIncrement();
    }

    /**
     * 构造方法
     *
     * @param name 任务组名称
     */
    public AbstractGroupTaskStep(String name) {
        mName = name;
    }

    /**
     * 构造方法
     *
     * @param name      任务组名称
     * @param taskParam 任务参数
     */
    public AbstractGroupTaskStep(String name, @NonNull ITaskParam taskParam) {
        mName = name;
        setTaskParam(taskParam);
    }

    @Override
    public IGroupTaskStep addTask(ITaskStep taskStep) {
        if (taskStep != null) {
            taskStep.setTaskStepLifecycle(this);
            mTasks.add(taskStep);
        }
        return this;
    }

    @Override
    public IGroupTaskStep addTasks(List<ITaskStep> taskStepList) {
        if (!CommonUtils.isEmpty(taskStepList)) {
            for (ITaskStep taskStep : taskStepList) {
                addTask(taskStep);
            }
        }
        return this;
    }

    @Override
    public AbstractGroupTaskStep setTaskParam(@NonNull ITaskParam taskParam) {
        super.setTaskParam(taskParam);
        mResult.updateParam(taskParam);
        return this;
    }

    /**
     * 初始化组任务
     */
    protected void initGroupTask() {
        mTaskTotalSize = TaskUtils.findTaskStepSize(getTasks());
        mTaskIndex.set(0);
        TaskLogger.dTag(TAG, getTaskLogName() + " initGroupTask, task total size:" + mTaskTotalSize);
    }

    @Override
    public void prepareTask(TaskParam taskParam) {
        super.prepareTask(taskParam);
        mResult.updateParam(taskParam);
    }

    @Override
    public void clearTask() {
        if (CommonUtils.isEmpty(mTasks)) {
            return;
        }
        for (ITaskStep taskStep : mTasks) {
            taskStep.recycle();
        }
        mTasks.clear();
    }

    @Override
    public void recycle() {
        mResult.clear();
        clearTask();
        super.recycle();
    }

    @Override
    public void cancel() {
        if (isCancelled()) {
            return;
        }
        for (ITaskStep taskStep : mTasks) {
            taskStep.cancel();
        }
        super.cancel();
    }

    @NonNull
    @Override
    public ITaskParam getTaskParam() {
        return mResult;
    }

    @Override
    public String getName() {
        return mName;
    }

    public List<ITaskStep> getTasks() {
        return mTasks;
    }

    public TaskResult getResult() {
        return mResult;
    }

    @Override
    protected String getTaskLogName() {
        return "Group task step [" + getName() + "]";
    }
}

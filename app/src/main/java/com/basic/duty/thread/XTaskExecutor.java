

package com.basic.duty.thread;

import com.basic.duty.thread.executor.ICategoryExecutorCore;
import com.basic.duty.thread.executor.IPriorityExecutorCore;
import com.basic.duty.thread.executor.impl.CategoryExecutorCore;
import com.basic.duty.thread.executor.impl.PriorityExecutorCore;
import com.basic.duty.thread.pool.ICancelable;

/**
 * XTask的执行者
 *
 
 * @since 2021/10/9 2:30 AM
 */
public class XTaskExecutor implements IPriorityExecutorCore, ICategoryExecutorCore {

    private static volatile XTaskExecutor sInstance = null;

    /**
     * 优先级执行内核实现接口
     */
    private IPriorityExecutorCore mPriorityExecutorCore;
    /**
     * 分类执行内核实现接口
     */
    private ICategoryExecutorCore mCategoryExecutorCore;

    /**
     * 获取XTask的执行者
     *
     * @return XTask的执行者
     */
    public static XTaskExecutor get() {
        if (sInstance == null) {
            synchronized (XTaskExecutor.class) {
                if (sInstance == null) {
                    sInstance = new XTaskExecutor();
                }
            }
        }
        return sInstance;
    }

    /**
     * 私有构造方法
     */
    private XTaskExecutor() {
        mCategoryExecutorCore = new CategoryExecutorCore();
        mPriorityExecutorCore = new PriorityExecutorCore();
    }

    /**
     * 设置优先级控制的执行内核实现接口
     *
     * @param priorityExecutorCore 优先级控制的执行内核实现接口
     * @return this
     */
    public XTaskExecutor setPriorityExecutorCore(IPriorityExecutorCore priorityExecutorCore) {
        mPriorityExecutorCore = priorityExecutorCore;
        return this;
    }

    /**
     * 设置类别执行内核实现接口
     *
     * @param categoryExecutorCore 类别执行内核实现接口
     * @return this
     */
    public XTaskExecutor setCategoryExecutorCore(ICategoryExecutorCore categoryExecutorCore) {
        mCategoryExecutorCore = categoryExecutorCore;
        return this;
    }

    @Override
    public ICancelable submit(Runnable task, int priority) {
        return mPriorityExecutorCore.submit(task, priority);
    }

    @Override
    public ICancelable submit(String groupName, Runnable task, int priority) {
        return mPriorityExecutorCore.submit(groupName, task, priority);
    }

    @Override
    public boolean postToMain(Runnable task) {
        return mCategoryExecutorCore.postToMain(task);
    }

    @Override
    public void shutdown() {
        mCategoryExecutorCore.shutdown();
        mPriorityExecutorCore.shutdown();
    }

    @Override
    public ICancelable emergentSubmit(Runnable task) {
        return mCategoryExecutorCore.emergentSubmit(task);
    }

    @Override
    public ICancelable submit(Runnable task) {
        return mCategoryExecutorCore.submit(task);
    }

    @Override
    public ICancelable backgroundSubmit(Runnable task) {
        return mCategoryExecutorCore.backgroundSubmit(task);
    }

    @Override
    public ICancelable ioSubmit(Runnable task) {
        return mCategoryExecutorCore.ioSubmit(task);
    }

    @Override
    public ICancelable groupSubmit(String groupName, Runnable task) {
        return mCategoryExecutorCore.groupSubmit(groupName, task);
    }

}

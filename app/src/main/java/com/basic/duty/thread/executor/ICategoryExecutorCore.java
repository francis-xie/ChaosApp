

package com.basic.duty.thread.executor;

import com.basic.duty.thread.pool.ICancelable;

/**
 * 拥有不同类别的执行者内核实现接口
 *

 * @since 1/27/22 12:50 AM
 */
public interface ICategoryExecutorCore extends IExecutorCore {

    /**
     * 执行任务到主线程
     *
     * @param task 任务
     * @return 是否执行成功
     */
    boolean postToMain(Runnable task);

    /**
     * 执行紧急异步任务【线程的优先级默认是10】
     *
     * @param task 任务
     * @return 取消接口
     */
    ICancelable emergentSubmit(Runnable task);

    /**
     * 执行普通异步任务【线程的优先级是5】
     *
     * @param task 任务
     * @return 取消接口
     */
    ICancelable submit(Runnable task);

    /**
     * 执行后台异步任务【线程的优先级是1】
     *
     * @param task 任务
     * @return 取消接口
     */
    ICancelable backgroundSubmit(Runnable task);

    /**
     * 执行io耗时的异步任务【线程的优先级是5】
     *
     * @param task 任务
     * @return 取消接口
     */
    ICancelable ioSubmit(Runnable task);

    /**
     * 执行分组异步任务【线程的优先级是5】
     *
     * @param groupName 任务组名
     * @param task      任务
     * @return 取消接口
     */
    ICancelable groupSubmit(String groupName, Runnable task);


}



package com.basic.util.rxjava.task;

import com.basic.util.rxjava.impl.IRxIOTask;
import com.basic.util.rxjava.impl.IRxUITask;

/**
 * 通用的Rx异步执行任务，在io线程中进行数据处理，在ui线程中刷新ui
 *

 * @since 2018/6/10 下午9:28
 */
public abstract class RxAsyncTask<T, R> implements IRxIOTask<T, R>, IRxUITask<R> {

    /**
     * IO执行任务的入参
     */
    private T InData;

    /**
     * IO执行任务的出参,UI执行任务的入参
     */
    private R OutData;

    public RxAsyncTask(T inData) {
        InData = inData;
    }

    public T getInData() {
        return InData;
    }

    public RxAsyncTask setInData(T inData) {
        InData = inData;
        return this;
    }

    public R getOutData() {
        return OutData;
    }

    public RxAsyncTask setOutData(R outData) {
        OutData = outData;
        return this;
    }
}

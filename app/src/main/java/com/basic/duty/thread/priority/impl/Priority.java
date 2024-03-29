

package com.basic.duty.thread.priority.impl;

import com.basic.duty.thread.priority.IPriority;

/**
 * 优先级【先比较优先级，再比较序号】
 *

 * @since 2021/10/9 11:29 AM
 */
public class Priority implements IPriority {

    /**
     * 优先级
     */
    private int mPriority;

    /**
     * 序号
     */
    private long mId;

    public Priority() {

    }

    public Priority(int priority) {
        mPriority = priority;
    }

    @Override
    public int priority() {
        return mPriority;
    }

    @Override
    public void priority(int priority) {
        mPriority = priority;
    }

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public void setId(long id) {
        mId = id;
    }

    @Override
    public String toString() {
        return "Priority{" +
                "mPriority=" + mPriority +
                ", mId=" + mId +
                '}';
    }
}

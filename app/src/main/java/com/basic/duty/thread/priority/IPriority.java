

package com.basic.duty.thread.priority;

/**
 * 优先级排序接口
 *

 * @since 2021/10/9 2:32 AM
 */
public interface IPriority {

    /**
     * 获取优先级，越大越靠前
     *
     * @return 优先级
     */
    int priority();

    /**
     * 改变优先级，越大越靠前
     *
     * @param priority 优先级
     */
    void priority(int priority);

    /**
     * 获取序号
     *
     * @return 序号
     */
    long getId();

    /**
     * 设置序号
     *
     * @param id 序号
     */
    void setId(long id);
}

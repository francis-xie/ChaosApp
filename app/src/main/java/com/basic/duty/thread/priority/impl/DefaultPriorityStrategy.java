

package com.basic.duty.thread.priority.impl;

import com.basic.duty.thread.priority.IPriority;
import com.basic.duty.thread.priority.IPriorityStrategy;

/**
 * 默认的优先级比较策略
 * 优先级值大的放前面，当优先值一样的，序号小的放前面
 *

 * @since 2021/10/9 3:05 PM
 */
public class DefaultPriorityStrategy implements IPriorityStrategy {
    @Override
    public int compare(IPriority priority, IPriority other) {
        if (priority == null || other == null) {
            return 0;
        }
        if (priority.priority() == other.priority()) {
            // ASC(升序)，序号小的放前面
            return priority.getId() < other.getId() ? -1 : 1;
        } else {
            // DESC(降序), 优先级大的放前面
            return priority.priority() > other.priority() ? -1 : 1;
        }
    }
}

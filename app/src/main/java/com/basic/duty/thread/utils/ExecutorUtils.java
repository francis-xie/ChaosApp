

package com.basic.duty.thread.utils;

import java.util.Collection;
import java.util.concurrent.ExecutorService;

/**
 * 线程池工具
 *

 * @since 1/21/22 2:11 AM
 */
public final class ExecutorUtils {

    private ExecutorUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 关闭线程池
     *
     * @param executor 线程池
     */
    public static void shutdown(ExecutorService executor) {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }

    /**
     * 关闭线程池
     *
     * @param executors 线程池集合
     */
    public static void shutdown(Collection<? extends ExecutorService> executors) {
        if (executors == null || executors.isEmpty()) {
            return;
        }
        for (ExecutorService executor : executors) {
            shutdown(executor);
        }
    }

}

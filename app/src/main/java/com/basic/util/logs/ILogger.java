

package com.basic.util.logs;

/**
 * 简易的日志记录接口
 *

 * @since 2018/6/10 下午9:30
 */
public interface ILogger {

    /**
     * 打印信息
     *
     * @param priority 优先级
     * @param tag      标签
     * @param message  信息
     * @param t        出错信息
     */
    void log(int priority, String tag, String message, Throwable t);

}

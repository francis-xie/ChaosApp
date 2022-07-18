

package com.basic.duty.thread.pool;

/**
 * 取消者
 *

 * @since 2/7/22 12:39 PM
 */
public interface ICanceller extends ICancelable {

    /**
     * 获取取消的标识
     *
     * @return 取消的标识
     */
    String getName();
}

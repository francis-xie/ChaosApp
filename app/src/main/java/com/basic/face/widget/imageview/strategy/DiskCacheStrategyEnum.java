

package com.basic.face.widget.imageview.strategy;

/**
 * 磁盘缓存策略枚举
 *

 * @since 2019-11-09 10:58
 */
public enum DiskCacheStrategyEnum {
    /**
     * Caches remote data with both {@link #DATA} and {@link #RESOURCE}, and local data with
     * {@link #RESOURCE} only.
     */
    ALL,
    /**
     * Saves no data to cache.
     */
    NONE,
    /**
     * Writes retrieved data directly to the disk cache before it's decoded.
     */
    DATA,
    /**
     * Writes resources to disk after they've been decoded.
     */
    RESOURCE,
    /**
     * Tries to intelligently choose a strategy
     */
    AUTOMATIC,
}

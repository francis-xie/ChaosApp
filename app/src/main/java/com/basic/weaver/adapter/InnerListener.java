package com.basic.weaver.adapter;


import com.basic.weaver.model.City;

/**
 * 列表的监听
 *

 * @since 2018/12/30 下午6:47
 */
public interface InnerListener {
    /**
     * 选择
     *
     * @param position
     * @param city
     */
    void pick(int position, City city);

    /**
     * 定位
     */
    void locate();
}

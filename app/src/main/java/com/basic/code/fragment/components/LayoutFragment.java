

package com.basic.code.fragment.components;

import com.basic.leaf.annotation.Page;
import com.basic.code.R;
import com.basic.code.base.ComponentContainerFragment;
import com.basic.code.fragment.components.layout.AlphaViewFragment;
import com.basic.code.fragment.components.layout.ExpandableLayoutFragment;
import com.basic.code.fragment.components.layout.GroupListViewFragment;
import com.basic.code.fragment.components.layout.LinkageScrollLayoutFragment;
import com.basic.code.fragment.components.layout.XUILayoutFragment;

/**

 * @since 2019-06-02 16:46
 */
@Page(name = "通用布局", extra = R.drawable.ic_widget_layout)
public class LayoutFragment extends ComponentContainerFragment {
    /**
     * 获取页面的类集合[使用@Page注解进行注册的页面]
     *
     * @return
     */
    @Override
    protected Class[] getPagesClasses() {
        return new Class[]{
                AlphaViewFragment.class,
                XUILayoutFragment.class,
                GroupListViewFragment.class,
                ExpandableLayoutFragment.class,
                LinkageScrollLayoutFragment.class,
        };
    }
}

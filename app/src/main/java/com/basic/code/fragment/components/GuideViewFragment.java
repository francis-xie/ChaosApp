package com.basic.code.fragment.components;

import com.basic.leaf.annotation.Page;
import com.basic.code.R;
import com.basic.code.base.ComponentContainerFragment;
import com.basic.code.fragment.components.guideview.GuideCaseViewQueueFragment;
import com.basic.code.fragment.components.guideview.GuideCaseViewStyleFragment;
import com.basic.code.fragment.components.guideview.SplashFragment;

/**
 * 引导页
 *

 * @since 2018/11/30 上午12:57
 */
@Page(name = "引导页", extra = R.drawable.ic_widget_guideview)
public class GuideViewFragment extends ComponentContainerFragment {

    @Override
    public Class[] getPagesClasses() {
        return new Class[]{
                GuideCaseViewQueueFragment.class,
                GuideCaseViewStyleFragment.class,
                SplashFragment.class
        };
    }
}


package com.basic.code.fragment.components;

import com.basic.code.fragment.components.progress.ArcLoadingViewFragment;
import com.basic.code.fragment.components.progress.BeautifulProgressBarFragment;
import com.basic.code.fragment.components.progress.DeterminateCircularFragment;
import com.basic.code.fragment.components.progress.MaterialProgressBarFragment;
import com.basic.code.fragment.components.progress.RatingBarFragment;
import com.basic.code.fragment.components.progress.RotateLoadingViewFragment;
import com.basic.leaf.annotation.Page;
import com.basic.code.R;
import com.basic.code.base.ComponentContainerFragment;

/**

 * @since 2018/11/26 下午1:47
 */
@Page(name = "进度条", extra = R.drawable.ic_widget_loading)
public class ProgressBarFragment extends ComponentContainerFragment {
    @Override
    public Class[] getPagesClasses() {
        return new Class[]{
                ArcLoadingViewFragment.class,
                RotateLoadingViewFragment.class,
                MaterialProgressBarFragment.class,
                DeterminateCircularFragment.class,
                RatingBarFragment.class,
                BeautifulProgressBarFragment.class
        };
    }
}

package com.basic.code.fragment;

import com.basic.leaf.annotation.Page;
import com.basic.leaf.config.AppPageConfig;
import com.basic.leaf.enums.CoreAnim;
import com.basic.leaf.model.PageInfo;
import com.basic.code.base.BaseHomeFragment;

import java.util.List;

/**
 * 组件的主要界面
 *

 * @since 2018/11/14 下午2:22
 */
@Page(name = "组件", anim = CoreAnim.none)
public class ComponentsFragment extends BaseHomeFragment {

    @Override
    protected List<PageInfo> getPageContents() {
        return AppPageConfig.getInstance().getComponents();
    }

}



package com.basic.code.fragment;

import com.basic.leaf.annotation.Page;
import com.basic.leaf.config.AppPageConfig;
import com.basic.leaf.enums.CoreAnim;
import com.basic.leaf.model.PageInfo;
import com.basic.code.base.BaseHomeFragment;

import java.util.List;

/**

 * @since 2018/12/29 上午11:16
 */
@Page(name = "拓展", anim = CoreAnim.none)
public class ExpandsFragment extends BaseHomeFragment {

    @Override
    protected List<PageInfo> getPageContents() {
        return AppPageConfig.getInstance().getExpands();
    }
}

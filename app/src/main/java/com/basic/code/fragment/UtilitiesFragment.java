

package com.basic.code.fragment;

import com.basic.leaf.annotation.Page;
import com.basic.leaf.config.AppPageConfig;
import com.basic.leaf.enums.CoreAnim;
import com.basic.leaf.model.PageInfo;
import com.basic.code.base.BaseHomeFragment;

import java.util.List;

/**

 * @since 2018/12/27 上午10:09
 */
@Page(name = "工具", anim = CoreAnim.none)
public class UtilitiesFragment extends BaseHomeFragment {

    @Override
    protected List<PageInfo> getPageContents() {
        return AppPageConfig.getInstance().getUtils();
    }
}

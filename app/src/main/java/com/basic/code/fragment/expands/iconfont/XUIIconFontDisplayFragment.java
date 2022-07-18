

package com.basic.code.fragment.expands.iconfont;

import androidx.recyclerview.widget.RecyclerView;

import com.basic.leaf.annotation.Page;
import com.basic.face.utils.DensityUtils;
import com.basic.face.utils.WidgetUtils;
import com.basic.code.R;
import com.basic.code.adapter.IconFontGridAdapter;
import com.basic.code.base.BaseFragment;
import com.basic.code.widget.iconfont.XUIIconFont;

import butterknife.BindView;

/**

 * @since 2019-10-13 18:55
 */
@Page(name = "XUIIconFont展示")
public class XUIIconFontDisplayFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_xui_iconfont_display;
    }

    @Override
    protected void initViews() {
        WidgetUtils.initGridRecyclerView(recyclerView, 3, DensityUtils.dp2px(2));

        recyclerView.setAdapter(new IconFontGridAdapter(XUIIconFont.Icon.values()));
    }

}

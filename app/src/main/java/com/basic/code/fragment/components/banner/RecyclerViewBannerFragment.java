

package com.basic.code.fragment.components.banner;

import com.basic.leaf.annotation.Page;
import com.basic.face.widget.banner.recycler.BannerLayout;
import com.basic.code.DemoDataProvider;
import com.basic.code.R;
import com.basic.code.adapter.RecyclerViewBannerAdapter;
import com.basic.code.base.BaseFragment;
import com.basic.code.utils.XToastUtils;

import butterknife.BindView;

/**

 * @since 2019-05-30 00:40
 */
@Page(name = "使用RecyclerView实现的Banner")
public class RecyclerViewBannerFragment extends BaseFragment implements BannerLayout.OnBannerItemClickListener {

    @BindView(R.id.bl_horizontal)
    BannerLayout blHorizontal;
    @BindView(R.id.bl_vertical)
    BannerLayout blVertical;

    private RecyclerViewBannerAdapter mAdapterHorizontal;
    private RecyclerViewBannerAdapter mAdapterVertical;


    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recyclerview_banner;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        blHorizontal.setAdapter(mAdapterHorizontal = new RecyclerViewBannerAdapter(DemoDataProvider.urls));
        blVertical.setAdapter(mAdapterVertical = new RecyclerViewBannerAdapter(DemoDataProvider.urls));
    }

    @Override
    protected void initListeners() {
        mAdapterHorizontal.setOnBannerItemClickListener(this);
        mAdapterVertical.setOnBannerItemClickListener(this);

        blHorizontal.setOnIndicatorIndexChangedListener(position -> XToastUtils.toast("轮播到第" + (position + 1) + "个"));
    }

    @Override
    public void onItemClick(int position) {
        XToastUtils.toast("点击了第" + (position + 1) + "个");
    }

}

package com.basic.code.fragment.components.refresh.broccoli;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basic.down.layout.SmartRefreshLayout;
import com.basic.down.layout.api.RefreshLayout;
import com.basic.down.layout.listener.OnRefreshLoadMoreListener;
import com.basic.aop.annotation.SingleClick;
import com.basic.leaf.annotation.Page;
import com.basic.face.utils.DensityUtils;
import com.basic.face.utils.ThemeUtils;
import com.basic.face.utils.WidgetUtils;
import com.basic.face.widget.actionbar.TitleBar;
import com.basic.code.DemoDataProvider;
import com.basic.code.R;
import com.basic.code.adapter.NewsListAdapter;
import com.basic.code.base.BaseFragment;
import com.basic.code.utils.Utils;

import butterknife.BindView;

/**

 * @since 2019/4/7 上午10:58
 */
@Page(name = "普通占位控件")
public class CommonPlaceholderFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private NewsListAdapter mNewsListAdapter;
    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_broccoli_place_holder;
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.addAction(new TitleBar.TextAction("截图") {
            @SingleClick
            @Override
            public void performAction(View view) {
                Utils.showCaptureBitmap(getContext(), Utils.getRecyclerViewScreenSpot(recyclerView));
            }
        });
        return titleBar;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        WidgetUtils.initRecyclerView(recyclerView, DensityUtils.dp2px(5), ThemeUtils.resolveColor(getContext(), R.attr.xui_config_color_background));

        recyclerView.setAdapter(mNewsListAdapter = new NewsListAdapter(false));
    }

    @Override
    protected void initListeners() {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(() -> {
                    mNewsListAdapter.loadMore(DemoDataProvider.getDemoNewInfos());
                    refreshLayout.finishLoadMore();
                }, 1000);
            }

            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(() -> {
                    mNewsListAdapter.refresh(DemoDataProvider.getDemoNewInfos());
                    refreshLayout.finishRefresh();
                }, 3000);
            }
        });

        mNewsListAdapter.setOnItemClickListener((itemView, item, position) -> Utils.goWeb(getContext(), item.getDetailUrl()));

        //设置刷新加载时禁止所有列表操作
        refreshLayout.setDisableContentWhenRefresh(true);
        refreshLayout.setDisableContentWhenLoading(true);
        refreshLayout.autoRefresh();
    }

    @Override
    public void onDestroyView() {
        mNewsListAdapter.recycle();
        super.onDestroyView();
    }


}

package com.basic.code.fragment.components.popupwindow;

import android.view.View;

import com.basic.aop.annotation.SingleClick;
import com.basic.leaf.annotation.Page;
import com.basic.face.widget.actionbar.TitleBar;
import com.basic.face.widget.popupwindow.popup.XUISimpleExpandablePopup;
import com.basic.face.widget.popupwindow.popup.XUISimplePopup;
import com.basic.code.DemoDataProvider;
import com.basic.code.R;
import com.basic.code.base.BaseFragment;
import com.basic.code.utils.XToastUtils;
import com.basic.tools.display.DensityUtils;

import butterknife.OnClick;

/**

 * @date 2017/11/12 下午4:34
 */
@Page(name = "弹出框统一样式")
public class PopupWindowStyleFragment extends BaseFragment {
    private XUISimplePopup mListPopup;
    private XUISimpleExpandablePopup mExpandableListPopup;
    private XUISimplePopup mMenuPopup;

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.setCenterClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View view) {
                mMenuPopup.showDown(view);
            }
        });
        titleBar.addAction(new TitleBar.TextAction("菜单") {
            @SingleClick
            @Override
            public void performAction(View view) {
                mMenuPopup.showDown(view);
            }
        });
        return titleBar;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_popupwindow_style;
    }

    @Override
    protected void initViews() {
        initListPopup();
        initExpandableListPopup();
        initMenuPopup();
    }

    @Override
    protected void initListeners() {

    }

    private void initListPopup() {
        mListPopup = new XUISimplePopup(getContext(), DemoDataProvider.dpiItems)
                .create(DensityUtils.dip2px(getContext(), 170), (adapter, item, position) -> XToastUtils.toast(item.getTitle().toString()))
                .setHasDivider(true);
    }

    private void initExpandableListPopup() {
        mExpandableListPopup = new XUISimpleExpandablePopup(getContext(), DemoDataProvider.expandableItems)
                .create(DensityUtils.dip2px(getContext(), 200), DensityUtils.dip2px(getContext(), 200))
                .setOnExpandableItemClickListener(false, (adapter, group, groupPosition, childPosition) -> XToastUtils.toast(group.getChildItem(childPosition).getTitle()));
    }

    private void initMenuPopup() {
        mMenuPopup = new XUISimplePopup(getContext(), DemoDataProvider.menuItems)
                .create((adapter, item, position) -> XToastUtils.toast(item.getTitle().toString()));
    }

    @SingleClick
    @OnClick({R.id.btn_commonlist_popup, R.id.btn_expandable_popup, R.id.btn_menu_popup})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commonlist_popup:
                mListPopup.showDown(v);
                break;
            case R.id.btn_expandable_popup:
                mExpandableListPopup.clearExpandStatus();
                mExpandableListPopup.showDown(v);
                break;
            case R.id.btn_menu_popup:
                mMenuPopup.showDown(v);
                break;
            default:
                break;
        }
    }


}

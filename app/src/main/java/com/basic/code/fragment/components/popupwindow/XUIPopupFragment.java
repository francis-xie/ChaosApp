package com.basic.code.fragment.components.popupwindow;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.basic.leaf.annotation.Page;
import com.basic.face.XUI;
import com.basic.face.adapter.simple.XUISimpleAdapter;
import com.basic.face.utils.DensityUtils;
import com.basic.face.widget.popupwindow.popup.XUIListPopup;
import com.basic.face.widget.popupwindow.popup.XUIPopup;
import com.basic.code.R;
import com.basic.code.base.BaseFragment;
import com.basic.code.utils.XToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**

 * @date 2017/11/11 下午3:35
 */
@Page(name = "XUIPopup\n通用弹窗")
public class XUIPopupFragment extends BaseFragment {
    private XUIPopup mNormalPopup;
    private XUIListPopup mListPopup;

    @BindView(R.id.btn_common_popup)
    Button mBtnCommonPopup;
    @BindView(R.id.btn_list_popup)
    Button mBtnListPopup;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_xui_popup;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @OnClick({R.id.btn_common_popup, R.id.btn_list_popup})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_common_popup:
                initNormalPopupIfNeed();
                mNormalPopup.setAnimStyle(XUIPopup.ANIM_GROW_FROM_CENTER);
                mNormalPopup.setPreferredDirection(XUIPopup.DIRECTION_TOP);
                mNormalPopup.show(v);
                mBtnCommonPopup.setText("隐藏普通浮层");
                break;
            case R.id.btn_list_popup:
                initListPopupIfNeed();
                mListPopup.setAnimStyle(XUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(XUIPopup.DIRECTION_TOP);
                mListPopup.show(v);
                mBtnListPopup.setText("隐藏列表浮层");
                break;
            default:
                break;
        }
    }

    private void initNormalPopupIfNeed() {
        if (mNormalPopup == null) {
            mNormalPopup = new XUIPopup(getContext());
            TextView textView = new TextView(getContext());
            textView.setLayoutParams(mNormalPopup.generateLayoutParam(
                    DensityUtils.dp2px(getContext(), 250),
                    WRAP_CONTENT
            ));
            textView.setLineSpacing(DensityUtils.dp2px(4), 1.0f);
            int padding = DensityUtils.dp2px(20);
            textView.setPadding(padding, padding, padding, padding);
            textView.setText("Popup 可以设置其位置以及显示和隐藏的动画");
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.xui_config_color_content_text));
            textView.setTypeface(XUI.getDefaultTypeface());
            mNormalPopup.setContentView(textView);
            mNormalPopup.setOnDismissListener(() -> {
                if (mBtnCommonPopup != null) {
                    mBtnCommonPopup.setText("显示普通浮层");
                }
            });
        }
    }

    private void initListPopupIfNeed() {
        if (mListPopup == null) {

            String[] listItems = new String[]{
                    "Item 1",
                    "Item 2",
                    "Item 3",
                    "Item 4",
                    "Item 5",
            };

            XUISimpleAdapter adapter = XUISimpleAdapter.create(getContext(), listItems);
            mListPopup = new XUIListPopup(getContext(), adapter);
            mListPopup.create(DensityUtils.dp2px(200), DensityUtils.dp2px(150), (adapterView, view, i, l) -> {
                XToastUtils.toast("Item " + (i + 1));
                mListPopup.dismiss();
            });
            mListPopup.setOnDismissListener(() -> {
                if (mBtnListPopup != null) {
                    mBtnListPopup.setText("显示列表浮层");
                }
            });
        }
    }
}

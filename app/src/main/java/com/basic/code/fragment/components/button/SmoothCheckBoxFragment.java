

package com.basic.code.fragment.components.button;

import com.basic.leaf.annotation.Page;
import com.basic.face.widget.button.SmoothCheckBox;
import com.basic.code.R;
import com.basic.code.base.BaseFragment;
import com.basic.code.utils.XToastUtils;

import butterknife.BindView;

/**

 * @since 2020-01-06 10:05
 */
@Page(name = "SmoothCheckBox\n自带切换动画的CheckBox")
public class SmoothCheckBoxFragment extends BaseFragment implements SmoothCheckBox.OnCheckedChangeListener {
    @BindView(R.id.scb)
    SmoothCheckBox scb;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_smooth_checkbox;
    }

    @Override
    protected void initViews() {
        scb.setChecked(true);
    }

    @Override
    protected void initListeners() {
        scb.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
        XToastUtils.toast("isChecked：" + isChecked);
    }
}

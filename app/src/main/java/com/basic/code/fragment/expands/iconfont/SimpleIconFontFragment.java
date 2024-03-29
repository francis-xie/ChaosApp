

package com.basic.code.fragment.expands.iconfont;

import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.mikepenz.iconics.IconicsColor;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.IconicsSize;
import com.basic.leaf.annotation.Page;
import com.basic.face.utils.ThemeUtils;
import com.basic.code.R;
import com.basic.code.base.BaseFragment;
import com.basic.code.widget.iconfont.XUIIconFont;
import com.basic.code.widget.iconfont.XUIIconImageView;

import butterknife.BindView;

/**

 * @since 2019-10-13 17:07
 */
@Page(name = "字体图标库的用法展示")
public class SimpleIconFontFragment extends BaseFragment {


    @BindView(R.id.iv_font)
    AppCompatImageView ivFont;
    @BindView(R.id.xiiv_font)
    XUIIconImageView xiivFont;
    @BindView(R.id.tv_font)
    TextView tvFont;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_simple_iconfont;
    }

    @Override
    protected void initViews() {
        IconicsDrawable drawable = new IconicsDrawable(getContext())
                .icon(XUIIconFont.Icon.xui_emoj)
                .color(IconicsColor.colorInt(ThemeUtils.getMainThemeColor(getContext())))
                .size(IconicsSize.dp(24));
        ivFont.setImageDrawable(drawable);

        xiivFont.setIconText("emoj");


        //TextView一定要注入字体，否则无法生效，字体注入方法详见 com.basic.code.widget.iconfont.IconFontActivity类
        tvFont.setText("{xui_emoj}");
    }
}

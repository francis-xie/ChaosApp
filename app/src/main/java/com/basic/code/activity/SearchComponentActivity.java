

package com.basic.code.activity;

import android.os.Bundle;

import com.basic.leaf.core.PageOption;
import com.basic.leaf.enums.CoreAnim;
import com.basic.code.base.BaseActivity;
import com.basic.code.fragment.other.SearchComponentFragment;
import com.basic.code.utils.Utils;

/**
 * 组件搜索页面
 *

 * @since 2020/4/1 1:26 AM
 */
public class SearchComponentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PageOption.to(SearchComponentFragment.class)
                .setAnim(CoreAnim.none)
                .open(this);
    }

    @Override
    public void onBackPressed() {
        if (this.getSupportFragmentManager().getBackStackEntryCount() == 1) {
            Utils.syncMainPageStatus();
        }
        super.onBackPressed();
    }
}

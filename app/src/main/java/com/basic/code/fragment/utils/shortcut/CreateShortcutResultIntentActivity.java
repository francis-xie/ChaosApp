

package com.basic.code.fragment.utils.shortcut;

import android.os.Bundle;

import com.basic.leaf.core.PageOption;
import com.basic.leaf.enums.CoreAnim;
import com.basic.code.base.BaseActivity;
import com.basic.code.fragment.utils.ShortcutUtilsFragment;
import com.basic.code.utils.Utils;

/**
 
 * @since 2021/10/7 7:00 PM
 */
public class CreateShortcutResultIntentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PageOption.to(ShortcutUtilsFragment.class)
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



package com.basic.code.base;

import android.os.Bundle;

import com.basic.router.annotation.AutoWired;
import com.basic.router.annotation.Router;
import com.basic.router.launcher.XRouter;
import com.basic.code.utils.Utils;
import com.basic.code.utils.XToastUtils;
import com.basic.tools.common.StringUtils;

/**
 * https://?/leaf/transfer?pageName=xxxxx&....
 * applink的中转
 *

 * @since 2019-07-06 9:37
 */
@Router(path = "/leaf/transfer")
public class XPageTransferActivity extends BaseActivity {

    @AutoWired(name = "pageName")
    String pageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XRouter.getInstance().inject(this);

        if (!StringUtils.isEmpty(pageName)) {
            if (openPage(pageName, getIntent().getExtras()) == null) {
                XToastUtils.toast("页面未找到！");
                finish();
            }
        } else {
            XToastUtils.toast("页面未找到！");
            finish();
        }
    }

    @Override
    protected void onRelease() {
        super.onRelease();
        Utils.syncMainPageStatus();

    }
}

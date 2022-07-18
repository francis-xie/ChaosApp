

package com.basic.tools;

import android.app.Application;

import androidx.annotation.Keep;

import androidx.core.content.FileProvider;
import com.basic.tools.common.ObjectUtils;

/**
 * 提供FileProvider能力，并执行自动初始化
 *

 * @since 2020/6/5 11:26 PM
 */
@Keep
public final class FileProvider4Utils extends FileProvider {

    @Override
    public boolean onCreate() {
        if (XUtil.isAutoInit()) {
            Application application = ObjectUtils.cast(getContext(), Application.class);
            if (application != null) {
                XUtil.init(application.getApplicationContext());
            }
        }
        return super.onCreate();
    }
}



package com.basic.router.java;

import android.app.Activity;
import android.os.Bundle;

import com.basic.router.R;
import com.basic.router.annotation.Router;

@Router(path = "/module/1")
public class TestModuleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_module);
    }
}

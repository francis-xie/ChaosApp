

package com.basic.router.java;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.basic.router.R;
import com.basic.router.annotation.Router;

@Router(path = "/module/2", group = "m2")
public class TestModule2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_module2);
    }
}

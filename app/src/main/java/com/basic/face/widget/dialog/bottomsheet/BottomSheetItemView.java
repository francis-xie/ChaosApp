package com.basic.face.widget.dialog.bottomsheet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import com.basic.code.R;
import com.basic.face.widget.alpha.XUIAlphaLinearLayout;

/**
 * BottomSheet 的ItemView
 *

 * @since 2018/11/30 下午1:58
 */
public class BottomSheetItemView extends XUIAlphaLinearLayout {

    private AppCompatImageView mAppCompatImageView;
    private ViewStub mSubScript;
    private TextView mTextView;


    public BottomSheetItemView(Context context) {
        super(context);
    }

    public BottomSheetItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomSheetItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mAppCompatImageView = findViewById(R.id.grid_item_image);
        mSubScript = findViewById(R.id.grid_item_subscript);
        mTextView = findViewById(R.id.grid_item_title);
    }

    public AppCompatImageView getAppCompatImageView() {
        return mAppCompatImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public ViewStub getSubScript() {
        return mSubScript;
    }

    @NonNull
    @Override
    public String toString() {
        return mTextView.getText().toString();
    }
}

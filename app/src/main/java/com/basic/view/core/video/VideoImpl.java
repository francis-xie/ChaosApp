/*
 * Copyright (C)  Justson(https://github.com/Justson/AgentWeb)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.basic.view.core.video;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import androidx.core.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.basic.view.core.event.EventInterceptor;

import java.util.HashSet;
import java.util.Set;

/**
 * 视频播放
 *
 
 * @since 2019/1/4 上午10:41
 */
public class VideoImpl implements IVideo, EventInterceptor {

    private Activity mActivity;
    private WebView mWebView;
    private Set<Pair<Integer, Integer>> mFlags;
    private View mMovieView = null;
    private ViewGroup mMovieParentView = null;
    private WebChromeClient.CustomViewCallback mCallback;

    public VideoImpl(Activity activity, WebView webView) {
        mActivity = activity;
        mWebView = webView;
        mFlags = new HashSet<>();

    }

    @Override
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        Activity activity;
        if ((activity = this.mActivity) == null || activity.isFinishing()) {
            return;
        }
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Window window = activity.getWindow();
        Pair<Integer, Integer> mPair = null;
        // 保存当前屏幕的状态
        if ((window.getAttributes().flags & WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) == 0) {
            mPair = new Pair<>(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 0);
            window.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            mFlags.add(mPair);
        }

        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) && (window.getAttributes().flags & WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED) == 0) {
            mPair = new Pair<>(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, 0);
            window.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            mFlags.add(mPair);
        }


        if (mMovieView != null) {
            callback.onCustomViewHidden();
            return;
        }

        if (mWebView != null) {
            mWebView.setVisibility(View.GONE);
        }

        if (mMovieParentView == null) {
            FrameLayout mDecorView = (FrameLayout) activity.getWindow().getDecorView();
            mMovieParentView = new FrameLayout(activity);
            mMovieParentView.setBackgroundColor(Color.BLACK);
            mDecorView.addView(mMovieParentView);
        }
        this.mCallback = callback;
        mMovieParentView.addView(this.mMovieView = view);
        mMovieParentView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onHideCustomView() {
        if (mMovieView == null) {
            return;
        }
        if (mActivity != null && mActivity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        if (!mFlags.isEmpty()) {
            for (Pair<Integer, Integer> mPair : mFlags) {
                mActivity.getWindow().setFlags(mPair.second, mPair.first);
            }
            mFlags.clear();
        }

        mMovieView.setVisibility(View.GONE);
        if (mMovieParentView != null && mMovieView != null) {
            mMovieParentView.removeView(mMovieView);

        }
        if (mMovieParentView != null) {
            mMovieParentView.setVisibility(View.GONE);
        }

        if (this.mCallback != null) {
            mCallback.onCustomViewHidden();
        }
        this.mMovieView = null;
        if (mWebView != null) {
            mWebView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean isVideoState() {
        return mMovieView != null;
    }

    @Override
    public boolean event() {
        if (isVideoState()) {
            onHideCustomView();
            return true;
        } else {
            return false;
        }

    }
}

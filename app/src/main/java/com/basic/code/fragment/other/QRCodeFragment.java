

package com.basic.code.fragment.other;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.basic.leaf.annotation.Page;
import com.basic.scan.XQRCode;
import com.basic.scan.util.QRCodeAnalyzeUtils;
import com.basic.face.widget.dialog.bottomsheet.BottomSheet;
import com.basic.code.R;
import com.basic.code.base.BaseFragment;
import com.basic.code.base.webview.AgentWebActivity;
import com.basic.code.fragment.components.imageview.DrawablePreviewFragment;
import com.basic.code.utils.XToastUtils;
import com.basic.tools.app.PathUtils;
import com.basic.tools.app.SocialShareUtils;
import com.basic.tools.display.ImageUtils;
import com.basic.tools.file.FileUtils;
import com.basic.tools.net.NetworkUtils;

import java.io.File;

import butterknife.BindView;

import static com.basic.code.base.webview.AgentWebFragment.KEY_URL;
import static com.basic.code.fragment.components.imageview.DrawablePreviewFragment.DRAWABLE_ID;

/**

 * @since 2019/1/7 上午9:14
 */
@Page(name = "扫码关注")
public class QRCodeFragment extends BaseFragment implements View.OnClickListener, View.OnLongClickListener {

    @BindView(R.id.iv_qq_group)
    ImageView ivQqGroup;
    @BindView(R.id.iv_win_xin)
    ImageView ivWinXin;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_qrcode;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        ivQqGroup.setTag("qq_group");
        ivQqGroup.setOnClickListener(this);
        ivQqGroup.setOnLongClickListener(this);
        ivWinXin.setTag("wei_xin_subscription_number");
        ivWinXin.setOnClickListener(this);
        ivWinXin.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.iv_qq_group:
                bundle.putInt(DRAWABLE_ID, R.drawable.img_xui_qq);
                openPage(DrawablePreviewFragment.class, bundle);
                break;
            case R.id.iv_win_xin:
                bundle.putInt(DRAWABLE_ID, R.drawable.img_winxin_subscription_number);
                openPage(DrawablePreviewFragment.class, bundle);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v instanceof ImageView) {
            showBottomSheetList(v, ImageUtils.drawable2Bitmap(((ImageView) v).getDrawable()));
        }
        return true;
    }

    @NonNull
    private String getImgFilePath(View v) {
        return FileUtils.getDiskFilesDir() + File.separator + v.getTag() + ".png";
    }

    private void showBottomSheetList(View v, final Bitmap bitmap) {
        final String imgPath = getImgFilePath(v);
        new BottomSheet.BottomListSheetBuilder(getActivity())
                .addItem("发送给朋友")
                .addItem("保存图片")
                .addItem("识别图中的二维码")
                .setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
                    dialog.dismiss();
                    boolean result = checkFile(imgPath, bitmap);
                    switch (position) {
                        case 0:
                            if (result) {
                                SocialShareUtils.sharePicture(getActivity(), PathUtils.getUriForFile(FileUtils.getFileByPath(imgPath)));
                            } else {
                                XToastUtils.toast("图片发送失败!");
                            }
                            break;
                        case 1:
                            if (result) {
                                XToastUtils.toast("图片保存成功:" + imgPath);
                            } else {
                                XToastUtils.toast("图片保存失败!");
                            }
                            break;
                        case 2:
                            if (result) {
                                XQRCode.analyzeQRCode(imgPath, new QRCodeAnalyzeUtils.AnalyzeCallback() {
                                    @Override
                                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                                        if (NetworkUtils.isUrlValid(result)) {
                                            goWeb(result);
                                        }
                                    }

                                    @Override
                                    public void onAnalyzeFailed() {
                                        XToastUtils.toast("解析二维码失败！");
                                    }
                                });
                            } else {
                                XToastUtils.toast("二维码识别失败!");
                            }
                            break;
                        default:
                            break;
                    }
                })
                .build()
                .show();
    }

    private boolean checkFile(String imgPath, Bitmap bitmap) {
        boolean result = FileUtils.isFileExists(imgPath);
        if (!result) {
            result = ImageUtils.save(bitmap, imgPath, Bitmap.CompressFormat.PNG);
        }
        return result;
    }

    /**
     * 请求浏览器
     *
     * @param url
     */
    public void goWeb(final String url) {
        Intent intent = new Intent(getContext(), AgentWebActivity.class);
        intent.putExtra(KEY_URL, url);
        startActivity(intent);
    }
}

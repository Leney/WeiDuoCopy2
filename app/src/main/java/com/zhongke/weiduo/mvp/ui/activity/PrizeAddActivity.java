package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.PrizeAddContract;
import com.zhongke.weiduo.mvp.presenter.PrizeAddPresenter;
import com.zhongke.weiduo.mvp.ui.widget.MyImageView;
import com.zhongke.weiduo.mvp.ui.widget.TakePhotoPopWin;
import com.zhongke.weiduo.util.SizeUtils;

import java.io.File;

import static com.zhongke.weiduo.R.id.ll_parent;

/**
 * Created by ${tanlei} on 2017/6/21.
 */

public class PrizeAddActivity extends BaseMvpActivity implements View.OnClickListener, TakePhotoPopWin.OnClickItemListeners, PrizeAddContract {
    /**
     * Presenter
     */
    private PrizeAddPresenter prizeAddPresenter;
    /**
     * 从相册界面跳转回来的标志
     */
    private final int PHOTO_REQUEST_GALLERY = 1;
    /**
     * 从相机界面跳转回来的标志
     */
    private final int PHOTO_REQUEST_CAREMA = 2;
    /**
     * 从裁剪界面跳转回来的标志
     */
    private final int PHOTO_REQUEST_CUT = 3;
    /**
     * 提交按钮
     */
    private TextView tvSubmit;
    /**
     * 弹出框，弹出拍照，本地选择照片
     */
    private TakePhotoPopWin takePhotoPopWin;
    /**
     * 封装照片的文件对象
     */
    private File tempFile;
    /**
     * 添加上传礼物的按钮
     */
    private RelativeLayout rlLocal;
    /**
     * 上传礼物的线性布局
     */
    private LinearLayout llLocal;
    /**
     * 系统选择的线性布局
     */
    private LinearLayout llSystemSelect;
    /**
     * 已经选择的现形布局
     */
    private LinearLayout llHaveChosen;
    /**
     * 换一换按钮
     */
    private TextView tvChange;
    /**
     * 窗口参数对象，用来设置窗口的透明度
     */
    private WindowManager.LayoutParams params;
    /**
     * 奖励标准的线性布局
     */
    private LinearLayout llParent;
    /**
     * 继续添加按钮
     */
    private TextView tvAdd;

    @Override
    protected BasePresenter createPresenter() {
        prizeAddPresenter = new PrizeAddPresenter(this, mDataManager);
        return prizeAddPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_prize_add);
        showCenterView();
        init();
        setListener();
    }

    /**
     * 设置监听器
     */
    private void setListener() {
        tvSubmit.setOnClickListener(this);
        rlLocal.setOnClickListener(this);
        tvChange.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    public void init() {
        setTitleName(getString(R.string.add_prize));
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        rlLocal = (RelativeLayout) findViewById(R.id.rl_local);
        llLocal = (LinearLayout) findViewById(R.id.ll_photograph);
        llSystemSelect = (LinearLayout) findViewById(R.id.ll_system_select);
        llHaveChosen = (LinearLayout) findViewById(R.id.ll_have_chosen);
        tvChange = (TextView) findViewById(R.id.change);
        llParent = (LinearLayout) findViewById(ll_parent);
        tvAdd = (TextView) findViewById(R.id.tv_add);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击了提交按钮
            case R.id.tv_submit:
                break;
            //点击了上传礼物按钮
            case R.id.rl_local:
                setPhotoPopupWindow();
                break;
            //点击的换一换按钮
            case R.id.change:
                break;
            //点击了继续添加按钮
            case R.id.tv_add:
                View view = LayoutInflater.from(this).inflate(R.layout.prize_layout, llParent, true);
                break;
            default:
                break;
        }
    }

    /**
     * 显示底部弹出框，以及设置透明度
     */
    private void setPhotoPopupWindow() {
        takePhotoPopWin = new TakePhotoPopWin(this, LayoutInflater.from(this).inflate(R.layout.take_photo_pop, null));
        takePhotoPopWin.setOnClickItemListeners(this);
        //设置Popupwindow显示位置（从底部弹出）
        takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        params = getWindow().getAttributes();
        //当弹出Popupwindow时，背景变半透明
        params.alpha = 0.7f;
        getWindow().setAttributes(params);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
        takePhotoPopWin.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
    }

    @Override
    public void clickItem(int position) {
        switch (position) {
            case 1:
                // 激活系统图库，选择一张图片
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                takePhotoPopWin.dismiss();
                break;
            case 2:
                // 激活相机
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                if (hasSdcard()) {
                    tempFile = new File(Environment.getExternalStorageDirectory(),
                            "PHOTO_FILE_NAME");
                    // 从文件中创建uri
                    Uri uri = Uri.fromFile(tempFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                }
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
                takePhotoPopWin.dismiss();
                break;
            case 3:
                takePhotoPopWin.dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
            }

        } else if (requestCode == 3) {
            // 从剪切图片返回的数据
            if (data != null) {
                creatPrizeImageView(data);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 创建一个圆形ImageView
     *
     * @param data
     */
    private void creatPrizeImageView(Intent data) {
        Bitmap bitmap = data.getParcelableExtra("data");
        MyImageView myImageView = new MyImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(SizeUtils.dp2px(this,48.3f),SizeUtils.dp2px(this,48.3f));  //image的布局方式
        params.setMargins(SizeUtils.dp2px(this,19f),SizeUtils.dp2px(this,11.3f),0,0);
        myImageView.setLayoutParams(params);
        myImageView.setImageBitmap(bitmap);
        llLocal.addView(myImageView);
    }

    /**
     * 判断sdcard是否可用
     *
     * @return
     */
    private boolean hasSdcard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
}

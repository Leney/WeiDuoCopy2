package com.zhongke.weiduo.mvp.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.BuildConfig;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.app.utils.StringUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.Luban.LubanUtils;
import com.zhongke.weiduo.library.aliyunOss.AliYunOssClient;
import com.zhongke.weiduo.library.easyPermission.PermissionManager;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActivateContract;
import com.zhongke.weiduo.mvp.model.entity.LabInfo;
import com.zhongke.weiduo.mvp.model.entity.Pickers;
import com.zhongke.weiduo.mvp.presenter.ActivatePresenter;
import com.zhongke.weiduo.mvp.ui.adapter.LabAdapter;
import com.zhongke.weiduo.mvp.ui.widget.BottomDialog;
import com.zhongke.weiduo.mvp.ui.widget.CustomDialog;
import com.zhongke.weiduo.mvp.ui.widget.PickerScrollView;
import com.zhongke.weiduo.mvp.ui.widget.TakePhotoPopWin;
import com.zhongke.weiduo.mvp.ui.widget.WapGridView;
import com.zhongke.weiduo.util.ActionConstances;
import com.zhongke.weiduo.util.BitmapUtils;
import com.zhongke.weiduo.util.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;
import rx.Subscriber;


/**
 * 激活设备界面
 * Created by llj on 2017/6/20.
 */

public class ActivateDeviceActivity extends BaseMvpActivity implements ActivateContract, LabAdapter.OnAddLabListener, View.OnClickListener, TakePhotoPopWin.OnClickItemListeners, EasyPermissions.PermissionCallbacks {
    private static final String DEVICE_CODE = "deviceCode";
    private final String tag = ActivateDeviceActivity.class.getSimpleName();
    private final String[] writePermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int writeRequestCode = 110;
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
    @Bind(R.id.activate_nick_name_input)
    EditText nickNameInput;
    @Bind(R.id.activate_school_input)
    EditText schoolNameInput;
    @Bind(R.id.activate_age_input)
    TextView ageText;
    @Bind(R.id.activate_sex_input)
    TextView sexText;
    @Bind(R.id.activate_my_role_input)
    TextView myRoleText;
    @Bind(R.id.activate_baby_labs_lay)
    WapGridView babyLabsLay;
    @Bind(R.id.activate_target_labs_lay)
    WapGridView targetLabsLay;
    @Bind(R.id.activate_activate_btn)
    TextView activateBtn;
    private ActivatePresenter mPresenter;
    /**
     * 弹出框，弹出拍照，本地选择照片
     */
    private TakePhotoPopWin takePhotoPopWin;
    private ImageView ivBabyPhoto;
    private EditText tvMyFamily;
    private LabAdapter babyLabAdapter, targetLabAdapter;
    private List<LabInfo> babyLabList, targetLabList;
    private BottomDialog sexDialog;
    private BottomDialog ageDialog;
    private BottomDialog roleDialog;
    private List<Pickers> sexList;
    private List<Pickers> ageList;
    private List<Pickers> roleList;
    private String deviceCode;
    private AliYunOssClient.UpLoadFileRequest request;
    /**
     * 窗口参数对象，用来设置窗口的透明度
     */
    private WindowManager.LayoutParams params;
    /**
     * 封装照片的文件对象
     */
    private File tempFile;
    private String currentPicturePath;

    public static void startActivity(Context context, String deviceCode) {
        Intent intent = new Intent(context, ActivateDeviceActivity.class);
        intent.putExtra(DEVICE_CODE, deviceCode);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName(getResources().getString(R.string.activate));
        setCenterLay(R.layout.activity_activate);
        recoverState(savedInstanceState);
        receiverIntent();
        ButterKnife.bind(this);
        showCenterView();
        init();
        // TODO xxxx
        mPresenter.getInfo();
    }


    private void recoverState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentPicturePath = savedInstanceState.getString(tag);
        }
    }

    /**
     * 接收传递过来的数据
     */
    private void receiverIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            deviceCode = intent.getStringExtra(DEVICE_CODE);
        }
    }

    private void init() {
        babyLabList = new ArrayList<>();
        babyLabAdapter = new LabAdapter(babyLabList, true);
        babyLabsLay.setAdapter(babyLabAdapter);
        ivBabyPhoto = (ImageView) findViewById(R.id.iv_baby_photo);
        tvMyFamily = (EditText) findViewById(R.id.activate_my_family_input);
        targetLabList = new ArrayList<>();
        targetLabAdapter = new LabAdapter(targetLabList, false);
        targetLabsLay.setAdapter(targetLabAdapter);

        activateBtn.setOnClickListener(this);
        ivBabyPhoto.setOnClickListener(this);
        sexText.setOnClickListener(this);
        ageText.setOnClickListener(this);
        myRoleText.setOnClickListener(this);
        nickNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s == null) {
                    tvMyFamily.setText("");
                } else {
                    tvMyFamily.setText(s + "的家庭");
                }
            }
        });
        sexList = new ArrayList<>();
        Pickers man = new Pickers();
        man.setId(0);
        man.setName(getResources().getString(R.string.man));
        Pickers madam = new Pickers();
        madam.setId(1);
        madam.setName(getResources().getString(R.string.madam));
        sexList.add(man);
        sexList.add(madam);

        ageList = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            Pickers pickers = new Pickers();
            pickers.setId(i);
            pickers.setName(i + "岁");
            ageList.add(pickers);
        }

        roleList = new ArrayList<>();
        String[] roles = getResources().getStringArray(R.array.roles);
        for (int i = 0; i < roles.length; i++) {
            Pickers pickers = new Pickers();
            pickers.setId(i);
            pickers.setName(roles[i]);
            pickers.setValue(i + 1);
            roleList.add(pickers);
        }
    }

    @Override
    public void getInfoSuccess() {
        String[] babyLabs = {"乐于助人0", "注意力集中1", "非常有爱心2", "乐于助人3", "注意力集中4", "非常有爱心5", "非常有爱心6", "非常有爱心7", "非常有爱心8", "非常有爱心9", "非常有爱心10", "非常有爱心11", "＋ 新增"};

        for (int i = 0; i < babyLabs.length; i++) {
            LabInfo labInfo = new LabInfo();
            labInfo.setName(babyLabs[i]);
            labInfo.setCheck(false);
            babyLabList.add(labInfo);
        }
        babyLabAdapter.notifyDataSetChanged();
        babyLabAdapter.setAddLabListener(this);


        String[] targetLabs = {"学习习惯0", "卫生习惯1", "阅读习惯2", "礼貌习惯3", "劳动习惯4", "饮食习惯5", "安全习惯6", "运动习惯7", "思维方式8"};
        for (int i = 0; i < targetLabs.length; i++) {
            LabInfo labInfo = new LabInfo();
            labInfo.setName(targetLabs[i]);
            labInfo.setCheck(false);
            targetLabList.add(labInfo);
        }
        targetLabAdapter.notifyDataSetChanged();
    }

    @Override
    public void getInfoFailed(int errorCode, String msg) {
        showErrorView();
    }

    @Override
    public void activateSuccess() {
        Toast.makeText(this, getResources().getString(R.string.activate_success), Toast.LENGTH_SHORT).show();
        // 跳转到创建家庭界面
       /* CreateFamilyActivity.startActivity(ActivateDeviceActivity.this);*/
        // 发送激活设备成功的广播
        EventBus.getDefault().post(ActionConstances.ACTION_ACTIVATE_DEVICE_SUCCESS);
        finish();
    }

    @Override
    public void activateFailed(int errorCode, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new ActivatePresenter(ActivateDeviceActivity.this, mDataManager, this);
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (request != null && !request.isFinish()) {
            request.cancel();
        }
        ButterKnife.unbind(this);
    }

    @Override
    public void addLab() {
        // 添加宝贝标签的点击回调事件
        final CustomDialog dialog = new CustomDialog(ActivateDeviceActivity.this);
        final EditText input = new EditText(ActivateDeviceActivity.this);
        dialog.setCenterLay(input);
        dialog.setLeftBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setRightBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String labName = input.getText().toString();
                if (StringUtil.isNull(labName)) {
                    Toast.makeText(ActivateDeviceActivity.this, getResources().getString(R.string.input_baby_add_lab), Toast.LENGTH_SHORT).show();
                    return;
                }
                LabInfo labInfo = new LabInfo();
                labInfo.setName(labName);
                labInfo.setCheck(true);
                int length = babyLabList.size();
                // 添加到"新增"前面
                babyLabList.add(length - 1, labInfo);
                babyLabAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 上传File
     *
     * @param file
     */
    public void upLoadFile(final Map<String, Object> map, File file) {
        request = AliYunOssClient.getInstance().startUpLoad(file.getAbsolutePath(), new AliYunOssClient.RequestResponseListener() {
            @Override
            public void start() {
            }

            @Override
            public void onProgress(String progress) {
                Log.i(tag, " 上传的文件进度 " + progress);
            }

            @Override
            public void onError(String localFilePath, String errorMSG) {
                Log.i(tag, " 上传的文件异常 ");
            }

            @Override
            public void onSuccess(String localFilePath, String upLoadFilePath) {
                map.put("headImageUrl", upLoadFilePath);
                mPresenter.activateDevice(map);
                Log.i(tag, " 上传的文件成功 路径是 " + upLoadFilePath);
            }
        });
        //  request.cancel();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activate_activate_btn:
                String nickName = nickNameInput.getText().toString();
                if (TextUtils.isEmpty(nickName)) {
                    UIUtils.showToast("请输入名字");
                    return;
                }
                Map<String, Object> map = new HashMap<>();
//                map.put("token", ZkApplication.getInstance().getToken());
                map.put("deviceCode", deviceCode);
                map.put("familyName", tvMyFamily.getText().toString());
                String familyTitle = myRoleText.getText().toString();
                if (StringUtils.isEmpty(familyTitle)) {
                    UIUtils.showToast("请选择您的角色");
                    return;
                }
                if (!familyTitle.contains(getString(R.string.please_choice))) {
                    map.put("familyTitle", (int) myRoleText.getTag());
                }
                map.put("nickName", nickName);
                String sex = sexText.getText().toString();
                if (!sex.contains(getString(R.string.please_choice))) {
                    int sexPosition = sex.equals("男") ? 1 : 2;
                    map.put("sex", sexPosition);
                }
                String birthday = ageText.getText().toString();
                if (!birthday.contains(getString(R.string.please_choice))) {
                    map.put("birthday", birthday);
                }
                String school = schoolNameInput.getText().toString();
                if (!TextUtils.isEmpty(school)) {
                    map.put("school", school);
                }
                String tagList = babyLabAdapter.getCheckTab();
                if (!TextUtils.isEmpty(tagList)) {
                    map.put("tagList", tagList);
                }
                String majorList = targetLabAdapter.getCheckTab();
                if (!TextUtils.isEmpty(majorList)) {
                    map.put("majorList", majorList);
                }
                if (ivBabyPhoto.getTag() != null) {
                    LubanUtils.scalePictureWithRxJava(getApplicationContext(), currentPicturePath, new Subscriber<File>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(File file) {
                            upLoadFile(map, file);
                        }
                    });
                } else {
                    mPresenter.activateDevice(map);
                }
                break;
            case R.id.activate_sex_input:
                // 性别
                Log.i("llj", "点击性别");
                if (sexDialog == null) {
                    sexDialog = new BottomDialog(ActivateDeviceActivity.this, R.layout.dialog_one_scroll);
                    final PickerScrollView scrollView = (PickerScrollView) sexDialog.findViewById(R.id.dialog_scroll_picker);
                    // 设置数据，默认选择第一条
                    scrollView.setData(sexList);
                    scrollView.setSelected(0);

                    TextView cancelBtn = (TextView) sexDialog.findViewById(R.id.dialog_scroll_cancel_btn);
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sexDialog.dismiss();
                        }
                    });

                    TextView sureBtn = (TextView) sexDialog.findViewById(R.id.dialog_scroll_sure_btn);
                    sureBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Pickers pickers = scrollView.getCurSelectedItem();
                            if (pickers != null) {
                                sexText.setText(pickers.getName());
                            }
                            sexDialog.dismiss();
                        }
                    });
                }
                sexDialog.show();
                break;
            case R.id.activate_age_input:
                // 年龄
                if (ageDialog == null) {
                    ageDialog = new BottomDialog(ActivateDeviceActivity.this, R.layout.dialog_three_scroll);
                    final PickerScrollView scrollView1 = (PickerScrollView) ageDialog.findViewById(R.id.dialog_scroll_picker1);
                    final PickerScrollView scrollView2 = (PickerScrollView) ageDialog.findViewById(R.id.dialog_scroll_picker2);
                    final PickerScrollView scrollView3 = (PickerScrollView) ageDialog.findViewById(R.id.dialog_scroll_picker3);

                    List<Pickers> yearList = new ArrayList<>();
                    for (int i = 0; i < 18; i++) {
                        Pickers pickers = new Pickers();
                        pickers.setId(i);
                        if (i < 10) {
                            pickers.setName("200" + i);
                        } else {
                            pickers.setName("20" + i);
                        }
                        yearList.add(pickers);
                    }

                    List<Pickers> monthList = new ArrayList<>();
                    for (int i = 1; i < 13; i++) {
                        Pickers pickers = new Pickers();
                        pickers.setId(i);
                        if (i < 10) {
                            pickers.setName("0" + i);
                        } else {
                            pickers.setName(i + "");
                        }
                        monthList.add(pickers);
                    }

                    final List<Pickers> dayList = new ArrayList<>();
                    for (int i = 1; i < 31; i++) {
                        Pickers pickers = new Pickers();
                        pickers.setId(i);
                        if (i < 10) {
                            pickers.setName("0" + i);
                        } else {
                            pickers.setName(i + "");
                        }
                        dayList.add(pickers);
                    }


                    // 设置数据，默认选择第一条
                    scrollView1.setData(yearList);
                    scrollView1.setSelected(0);

                    scrollView2.setData(monthList);
                    scrollView2.setSelected(0);

                    scrollView3.setData(dayList);
                    scrollView3.setSelected(0);

                    TextView cancelBtn = (TextView) ageDialog.findViewById(R.id.dialog_scroll_cancel_btn);
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ageDialog.dismiss();
                        }
                    });

                    TextView sureBtn = (TextView) ageDialog.findViewById(R.id.dialog_scroll_sure_btn);
                    sureBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Pickers pickers1 = scrollView1.getCurSelectedItem();
                            Pickers pickers2 = scrollView2.getCurSelectedItem();
                            Pickers pickers3 = scrollView3.getCurSelectedItem();

                            String birth = pickers1.getName() + "-" + pickers2.getName() + "-" + pickers3.getName();
//                            if (pickers != null) {
                            ageText.setText(birth);
//                            }
                            ageDialog.dismiss();
                        }
                    });
                }
                ageDialog.show();
                break;
            case R.id.activate_my_role_input:
                // 角色
                if (roleDialog == null) {
                    roleDialog = new BottomDialog(ActivateDeviceActivity.this, R.layout.dialog_one_scroll);
                    final PickerScrollView scrollView = (PickerScrollView) roleDialog.findViewById(R.id.dialog_scroll_picker);
                    // 设置数据，默认选择第一条
                    scrollView.setData(roleList);
                    scrollView.setSelected(0);

                    TextView cancelBtn = (TextView) roleDialog.findViewById(R.id.dialog_scroll_cancel_btn);
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            roleDialog.dismiss();
                        }
                    });

                    TextView sureBtn = (TextView) roleDialog.findViewById(R.id.dialog_scroll_sure_btn);
                    sureBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Pickers pickers = scrollView.getCurSelectedItem();
                            if (pickers != null) {
                                myRoleText.setText(pickers.getName());
                                myRoleText.setTag(pickers.getValue());
                            }
                            roleDialog.dismiss();
                        }
                    });
                }
                roleDialog.show();
                break;
            case R.id.iv_baby_photo:
                if (PermissionManager.checkPermission(this, writePermission)) {
                    setPhotoPopupWindow();
                } else {
                    PermissionManager.requestPermission(this, "拍照存储图片需要写入磁盘权限", writeRequestCode, writePermission);
                }
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

    /**
     * 创建一个圆形ImageView
     *
     * @param data
     */
    private void createPrizeImageView(Intent data) {
        PhotoLoaderUtil.display(this, ivBabyPhoto, currentPicturePath, R.drawable.default_img);
        ivBabyPhoto.setTag(currentPicturePath);
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
                    /*tempFile = new File(Environment.getExternalStorageDirectory(), "PHOTO_FILE_NAME");*/
                    tempFile = BitmapUtils.getDiskFile(getApplicationContext(), BitmapUtils.getBitmapFileName());
                    /**
                     * 指定拍照存储路径
                     * 7.0 及其以上使用FileProvider替换'file://'访问
                     */
                    if (Build.VERSION.SDK_INT >= 24) {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", new File(tempFile.getAbsolutePath())));
                        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    } else {
                        // 从文件中创建uri
                        Uri uri = Uri.fromFile(tempFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    }
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
                currentPicturePath = tempFile.getAbsolutePath();
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
            }

        } else if (requestCode == 3) {

            // 从剪切图片返回的数据
            if (data != null) {
                createPrizeImageView(data);
            }
        } else if (requestCode == PermissionManager.DEFAULT_SETTINGS_REQ_CODE) {
            if (PermissionManager.checkPermission(this, writePermission)) {
                setPhotoPopupWindow();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionManager.handlerPermissionResult(requestCode, permissions, grantResults, this);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

    /**
     * 根据Uri裁剪图片
     *
     * @param uri
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= 24) {
            Uri newUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", new File(uri.getEncodedPath()));
            intent.setDataAndType(newUri, "image/*");
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            intent.setDataAndType(uri, "image/*");
        }
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        // 取消人脸识别
        intent.putExtra("noFaceDetection", true);
       /* //是否把剪切后的图片通过data返回
        intent.putExtra("return-data", true);*/
        File cropFile = BitmapUtils.getDiskFile(getApplicationContext(), BitmapUtils.getBitmapFileName());
        currentPicturePath = cropFile.getAbsolutePath();
        if (Build.VERSION.SDK_INT >= 24) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", new File(currentPicturePath)));
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            Uri cropUri = Uri.fromFile(cropFile);
            //设置裁剪后文件保存路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(tag, currentPicturePath);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == writeRequestCode) {
            setPhotoPopupWindow();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        PermissionManager.handlerPermissionRefuseNotTip(this, perms);
        Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
    }
}

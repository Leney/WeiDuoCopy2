package com.zhongke.weiduo.mvp.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.Luban.LubanUtils;
import com.zhongke.weiduo.library.aliyunOss.AliYunOssClient;
import com.zhongke.weiduo.library.easyPermission.PermissionManager;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.PersonalContract;
import com.zhongke.weiduo.mvp.model.entity.NewPersonalData;
import com.zhongke.weiduo.mvp.model.entity.Pickers;
import com.zhongke.weiduo.mvp.model.event.ModifyImage;
import com.zhongke.weiduo.mvp.model.event.ModifyNickName;
import com.zhongke.weiduo.mvp.model.requestEntity.ModifyUserBean;
import com.zhongke.weiduo.mvp.presenter.PersonalPresenter;
import com.zhongke.weiduo.mvp.ui.widget.BottomDialog;
import com.zhongke.weiduo.mvp.ui.widget.CustomDatePicker;
import com.zhongke.weiduo.mvp.ui.widget.PickerScrollView;
import com.zhongke.weiduo.mvp.ui.widget.dialog.ActualNameDialog;
import com.zhongke.weiduo.mvp.ui.widget.dialog.BirthDialog;
import com.zhongke.weiduo.mvp.ui.widget.dialog.ChooseProfileDialog;
import com.zhongke.weiduo.mvp.ui.widget.dialog.HomeAddressDialog;
import com.zhongke.weiduo.mvp.ui.widget.dialog.NickNameDialog;
import com.zhongke.weiduo.mvp.ui.widget.wheelview.CustomDateView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;
import rx.Subscriber;

import static com.zhongke.weiduo.R.id.input_name;
import static com.zhongke.weiduo.R.id.input_nick_name;


/*
* 个人资料页面
* */
public class PersonalActivity extends BaseMvpActivity implements PersonalContract, View.OnClickListener ,EasyPermissions.PermissionCallbacks {

    @Bind(R.id.profile_picture_constraint)
    ConstraintLayout profilePicture;
    @Bind(R.id.profile_picture)
    ImageView profile_picture;
    @Bind(R.id.user_nickname_constraint)
    ConstraintLayout nicknameConstraint;
    @Bind(R.id.nick_name)
    TextView nick_name;
    @Bind(R.id.actual_name_constraint)
    ConstraintLayout actualnameContraint;
    @Bind(R.id.actual_name)
    TextView actual_name;
    @Bind(R.id.gender_constraint)
    ConstraintLayout genderConstraint;
    @Bind(R.id.personal_gender)
    TextView gender;
    @Bind(R.id.home_address_constraint)
    ConstraintLayout homeConstraint;
    @Bind(R.id.home_address)
    TextView homeAddress;
    @Bind(R.id.date_of_birth_constraint)
    ConstraintLayout birthContraint;
    @Bind(R.id.date_birth)
    TextView date_birth;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    PersonalPresenter personalPresenter;
    private Context context;
    private ChooseProfileDialog dialog;
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_ALBUM = 2;
    public static final int REQUEST_CROP = 3;
    public static final String IMAGE_UNSPECIFIED = "image/*";
    private File mImageFile;
    private List<Pickers> sexList;
    private BottomDialog genderDialog;
    private HomeAddressDialog homeDialog;
    private NickNameDialog nickDialog;
    private ActualNameDialog nameDialog;
    private CustomDatePicker customDatePicker;
    private String now;
    private BirthDialog birthDialog;
    private ModifyUserBean modifyUserBean;
    private String fullName;
    private String homeAddressStr;
    private String nickNameStr;
    private int sexInt = 1;
    private String birthDay;
    //拍照权限
    private final String[] cameraPermission={Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int cameraRequestCode = 111;
    private final String[] selectAlbumPermission = {Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int albumRequestCode = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_personal);
        showLoadingView();
        setTitleName("个人资料");
        ButterKnife.bind(this);
        context = this;
        initView();
        initData();
    }

    private void initView() {
        profilePicture.setOnClickListener(this);
        nicknameConstraint.setOnClickListener(this);
        actualnameContraint.setOnClickListener(this);
        genderConstraint.setOnClickListener(this);
        birthContraint.setOnClickListener(this);
        homeConstraint.setOnClickListener(this);
    }

    private void initData() {
        sexList = new ArrayList<>();
        Pickers man = new Pickers();
        man.setId(0);
        man.setName(getResources().getString(R.string.man));
        Pickers madam = new Pickers();
        madam.setId(1);
        madam.setName(getResources().getString(R.string.madam));
        sexList.add(man);
        sexList.add(madam);
        personalPresenter.getPresenter();
       /* et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 11) {
                    if (modifyUserBean != null) {
                        if (!s.toString().equals(modifyUserBean.getUserPhone())) {
                            modifyUserBean.setUserPhone(s.toString());
                            personalPresenter.modifyInfo(modifyUserBean);
                        }
                    }
                }
            }
        });*/
    }

    @Override
    protected BasePresenter createPresenter() {
        personalPresenter = new PersonalPresenter(context, this);
        return personalPresenter;
    }

    //选择拍照
    private void selectCamera() {
        createImageFile();
        if (!mImageFile.exists()) {
            return;
        }
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mImageFile));
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    public void selectAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        /*intent.setType("image*//*");*/
        startActivityForResult(intent, REQUEST_ALBUM);
    }

    private void cropImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mImageFile));
        startActivityForResult(intent, REQUEST_CROP);
    }

    private void createImageFile() {
        mImageFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        try {
            mImageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "出错啦", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CAMERA://从相机拍照返回
                if (data !=null) {
                    cropImage(Uri.fromFile(mImageFile));
                }
                break;
            case REQUEST_ALBUM://从相册返回
                createImageFile();
                if (!mImageFile.exists()) {
                    return;
                }
                Uri uri = null;
                if (data != null) {
                    uri = data.getData();
                }
                if (uri != null) {
                    cropImage(uri);
                }
                break;
            case REQUEST_CROP://裁剪
                profile_picture.setImageURI(Uri.fromFile(mImageFile));
                editPersonMSG();
                if (dialog != null) {
                    dialog.dismiss();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_picture_constraint://用户头像这一栏
                if (dialog == null) {
                    dialog = new ChooseProfileDialog(context, R.style.profile_picture_dialog_style);
                }
                dialog.show();
                LinearLayout take_photo = (LinearLayout) dialog.findViewById(R.id.take_photo);
                LinearLayout chooseAlbum = (LinearLayout) dialog.findViewById(R.id.choose_album);
                //拍照
                take_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //检测权限
                        if (PermissionManager.checkPermission(PersonalActivity.this,cameraPermission)) {
                            selectCamera();
                        } else{
                            PermissionManager.requestPermission(PersonalActivity.this,"拍照,需要开启相机权限,读写外部存储权限",cameraRequestCode,cameraPermission);
                        }

                    }
                });
                //从相册选择
                chooseAlbum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (PermissionManager.checkPermission(PersonalActivity.this,selectAlbumPermission)) {
                            selectAlbum();
                        } else {
                            PermissionManager.requestPermission(PersonalActivity.this,"从相册选择,需要读写外部存储权限",cameraRequestCode,cameraPermission);
                        }
                    }
                });

                break;
            case R.id.user_nickname_constraint: //用户昵称
                if (nickDialog == null) {
                    nickDialog = new NickNameDialog(context, R.style.home_address_dialog_style);
                }
                nickDialog.show();
                LinearLayout nickname_sure = (LinearLayout) nickDialog.findViewById(R.id.nickname_sure);
                EditText input_nickName = (EditText) nickDialog.findViewById(input_nick_name);
                if (!TextUtils.isEmpty(nickNameStr)) {
                    input_nickName.setHint(nickNameStr);
                }
                nickname_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (input_nickName.getText().toString().trim() == null || "".equals(input_nickName.getText().toString().trim())) {
                            return;
                        }
                        nick_name.setText(input_nickName.getText().toString().trim());
                        if (!input_nickName.getText().toString().trim().equals(modifyUserBean.getNickName())) {
                            modifyUserBean.setNickName(input_nickName.getText().toString().trim());
                            personalPresenter.modifyInfo(modifyUserBean, 2);
                        }
                        nickDialog.dismiss();
                    }
                });
                break;

            case R.id.actual_name_constraint:   //真实姓名
                if (nameDialog == null) {
                    nameDialog = new ActualNameDialog(context, R.style.home_address_dialog_style);
                }
                nameDialog.show();
                LinearLayout name_sure = (LinearLayout) nameDialog.findViewById(R.id.name_sure);
                EditText inputName = (EditText) nameDialog.findViewById(input_name);
                if (!TextUtils.isEmpty(fullName)) {
                    inputName.setHint(fullName);
                }
                name_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actual_name.setText(inputName.getText().toString().trim());
                        if (!inputName.getText().toString().trim().equals(modifyUserBean.getFullName())) {
                            modifyUserBean.setFullName(inputName.getText().toString().trim());
                            personalPresenter.modifyInfo(modifyUserBean);
                        }
                        nameDialog.dismiss();
                    }
                });
                break;

            case R.id.gender_constraint://性别栏
                if (genderDialog == null) {
                    genderDialog = new BottomDialog(PersonalActivity.this, R.layout.dialog_one_scroll);
                    final PickerScrollView pickerScrollView = (PickerScrollView) genderDialog.findViewById(R.id.dialog_scroll_picker);

                    pickerScrollView.setData(sexList);
                    if (sexInt == 1) {
                        pickerScrollView.setSelected(0);
                    } else {
                        pickerScrollView.setSelected(1);
                    }
                    TextView cancel = (TextView) genderDialog.findViewById(R.id.dialog_scroll_cancel_btn);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            genderDialog.dismiss();
                        }
                    });
                    TextView sure = (TextView) genderDialog.findViewById(R.id.dialog_scroll_sure_btn);
                    sure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Pickers pickers = pickerScrollView.getCurSelectedItem();
                            if (pickers != null) {
                                gender.setText(pickers.getName());
                                int gender;
                                if (pickers.getName().equals("男")) {
                                    gender = 1;
                                } else {
                                    gender = 2;
                                }
                                if (!String.valueOf(gender).equals(modifyUserBean.getSex())) {
                                    modifyUserBean.setSex(gender + "");
                                    personalPresenter.modifyInfo(modifyUserBean);
                                }
                            }
                            genderDialog.dismiss();
                        }
                    });
                }
                genderDialog.show();
                break;
            case R.id.date_of_birth_constraint: //出生日期
//                customDatePicker.show(now);
                if (birthDialog == null) {
                    birthDialog = new BirthDialog(context, R.style.profile_picture_dialog_style);
                }
                birthDialog.show();
                CustomDateView dateView = (CustomDateView) birthDialog.findViewById(R.id.date_of_birth);
                //初始化默认的日期显示
                if (birthDialog == null) {
                    if (!TextUtils.isEmpty(birthDay)) {
                        dateView.setCurrentItem(birthDay);
                    }
                }
                TextView birth_sure = (TextView) birthDialog.findViewById(R.id.personal_birth_sure);
                birth_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        date_birth.setText(dateView.getYear() + "-" + dateView.getMonth() + "-" + dateView.getDay());
                        birthDialog.dismiss();
                        if (!(dateView.getYear() + "-" + dateView.getMonth() + "-" + dateView.getDay()).equals(modifyUserBean.getBirthday())) {
                            modifyUserBean.setBirthday(dateView.getYear() + "-" + dateView.getMonth() + "-" + dateView.getDay());
                            personalPresenter.modifyInfo(modifyUserBean);
                        }
                    }
                });
                break;

            case R.id.home_address_constraint://家庭地址栏
                if (homeDialog == null) {
                    homeDialog = new HomeAddressDialog(context, R.style.home_address_dialog_style);
                }
                homeDialog.show();
                LinearLayout address_sure = (LinearLayout) homeDialog.findViewById(R.id.address_sure);
                EditText inputAddress = (EditText) homeDialog.findViewById(R.id.input_addrees);
                if (!TextUtils.isEmpty(homeAddressStr)) {
                    inputAddress.setHint(homeAddressStr);
                }
                address_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        homeAddress.setText(inputAddress.getText().toString());
                        homeDialog.dismiss();
                        if (!inputAddress.getText().toString().equals(modifyUserBean.getFamilyAddress())) {
                            modifyUserBean.setFamilyAddress(inputAddress.getText().toString());
                            personalPresenter.modifyInfo(modifyUserBean);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * 把图片上传到阿里云中获取到地址。
     */
    private void editPersonMSG() {
        if (mImageFile != null) {
            LubanUtils.scalePictureWithRxJava(this, mImageFile.getAbsolutePath(), new Subscriber<File>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(File file) {
                    AliYunOssClient.getInstance().startUpLoad(file.getAbsolutePath(), new AliYunOssClient.RequestResponseListener() {
                        @Override
                        public void start() {

                        }

                        @Override
                        public void onProgress(String progress) {

                        }

                        @Override
                        public void onError(String localFilePath, String errorMSG) {

                        }

                        @Override
                        public void onSuccess(String localFilePath, String upLoadFilePath) {
                            modifyUserBean.setHeadImageUrl(upLoadFilePath);
                            personalPresenter.modifyInfo(modifyUserBean, 1);
                        }
                    });
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void showPersonal(NewPersonalData newPersonalData) {
        showCenterView();
        if (newPersonalData != null) {
            nickNameStr = newPersonalData.getSysUser().getNickName();
            nick_name.setText(nickNameStr);
            tv_phone.setText(newPersonalData.getSysUser().getUserPhone());
            birthDay = newPersonalData.getSysUser().getBirthday();
            date_birth.setText(birthDay);
            fullName = newPersonalData.getSysUser().getFullName();
            actual_name.setText(fullName);
            homeAddressStr = newPersonalData.getSysUser().getFamilyAddress();
            homeAddress.setText(homeAddressStr);
            if (newPersonalData.getSysUser().getSex() == 1) {
                sexInt = 1;
                gender.setText("男");
            } else {
                sexInt = 2;
                gender.setText("女");
            }
            Glide.with(this).load(newPersonalData.getSysUser().getHeadImageUrl()).into(profile_picture);
            //封装请求参数
            modifyUserBean = new ModifyUserBean("mga7csnv1509791787822", newPersonalData.getSysUser().getNickName(), newPersonalData.getSysUser().getHeadImageUrl(),
                    newPersonalData.getSysUser().getUserPhone(), newPersonalData.getSysUser().getSex() + "",
                    newPersonalData.getSysUser().getBirthday(), newPersonalData.getSysUser().getFamilyAddress(), newPersonalData.getSysUser().getFullName());
        } else {
            showNoDataView();
        }
    }

    @Override
    public void showFailure() {
        showErrorView();
    }

    /**
     * 显示保存成功的toast
     */
    @Override
    public void showSaveSuccess() {
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSaveSuccess(int state) {
        if (state == 1) {
            LogUtil.e("headimg_url--" + modifyUserBean.getHeadImageUrl());
            personalPresenter.changeLocalData(modifyUserBean.getHeadImageUrl(), 1);
            EventBus.getDefault().post(new ModifyImage(modifyUserBean.getHeadImageUrl()));
        } else if (state == 2) {
            LogUtil.e("NickName--" + modifyUserBean.getNickName());
            personalPresenter.changeLocalData(modifyUserBean.getNickName(), 2);
            EventBus.getDefault().post(new ModifyNickName(modifyUserBean.getNickName()));
        }

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示保存失败的toast
     */
    @Override
    public void showSaveFailure(CommonException e) {
        Toast.makeText(this, "保存失败," + e.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == cameraRequestCode) {
            //选择拍照
            selectCamera();
        } else if (requestCode == albumRequestCode) {
            //从相册选择
            selectAlbum();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == cameraRequestCode) {
            UIUtils.showToast("拍照权限,读写外部存储被拒绝，无法开启拍照界面");
            PermissionManager.handlerPermissionRefuseNotTip(this, perms);
        } else if (requestCode == albumRequestCode) {
            UIUtils.showToast("读写外部存储被拒绝，无法开启从相册选择界面");
            PermissionManager.handlerPermissionRefuseNotTip(this, perms);
        }
    }
}

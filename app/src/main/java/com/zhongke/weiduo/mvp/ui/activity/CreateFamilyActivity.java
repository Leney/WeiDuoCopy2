package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CreateFamilyContract;
import com.zhongke.weiduo.mvp.model.entity.FamilyInfo;
import com.zhongke.weiduo.mvp.presenter.CreateFamilyPresenter;
import com.zhongke.weiduo.mvp.ui.widget.CustomDialog;
import com.zhongke.weiduo.util.StringUtils;
import com.zhongke.weiduo.util.Tools;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 创建家庭界面
 * Created by llj on 2017/6/21.
 */

public class CreateFamilyActivity extends BaseMvpActivity implements CreateFamilyContract, View.OnClickListener {
    private CreateFamilyPresenter mPresenter;

    @Bind(R.id.create_family_name_input)
    EditText familyNameInput;

    @Bind(R.id.create_family_create_btn)
    TextView saveBtn;


    /**
     * 创建中... dialog
     */
    private CustomDialog creatingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Tools.setStatusColor(this, getResources().getColor(R.color.edit_lay_bg));
        setTitleName(getResources().getString(R.string.create_family));
//        baseTitle.setTitleBackground(R.color.edit_lay_bg);
//        baseTitle.setLeftImgRes(R.drawable.back_img_black);
//        baseTitle.setTitleNameColor(R.color.black_text_color);
        setCenterLay(R.layout.activity_create_family);

        showCenterView();
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void createSuccess(FamilyInfo familyInfo) {
        creatingDialog.dismiss();

        final CustomDialog createSuccessDialog = new CustomDialog(CreateFamilyActivity.this,false);
        createSuccessDialog.setCenterLay(R.layout.dialog_create_family_success);
        createSuccessDialog.setTitleVisible(false);
        createSuccessDialog.setBtnLayVisible(false);

        TextView familyCode = (TextView) createSuccessDialog.findViewById(R.id.create_family_success_text);
        TextView addMemberBtn = (TextView) createSuccessDialog.findViewById(R.id.create_family_success_add_btn);
        TextView goNewTaskBtn = (TextView) createSuccessDialog.findViewById(R.id.create_family_success_new_task_btn);

        String all = String.format(getResources().getString(R.string.create_family_code),familyInfo.getId()+"");
        familyCode.setText(Tools.setOneOfTextColor(all,familyInfo.getId()+"","#1cbf61"));
        addMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 跳转到添加成员界面
                AddMemberActivity.startActivity(CreateFamilyActivity.this);
                createSuccessDialog.dismiss();

            }
        });
        goNewTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO 跳转到新手任务界面(估计是主页)
                createSuccessDialog.dismiss();
                MainActivity.startActivity(CreateFamilyActivity.this);
                finish();
            }
        });
        createSuccessDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                // dialog消失finish掉此界面
                finish();
            }
        });
        createSuccessDialog.show();
    }

    @Override
    public void createFailed(int errorCode, String msg) {
        creatingDialog.dismiss();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new CreateFamilyPresenter(CreateFamilyActivity.this, mDataManager, this);
        return mPresenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_family_create_btn:
                // 保存按钮
                String familyName = familyNameInput.getText().toString().trim();
                if (StringUtils.isEmpty(familyName)) {
                    Toast.makeText(this, getResources().getString(R.string.family_name_input), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (creatingDialog == null) {
                    creatingDialog = new CustomDialog(CreateFamilyActivity.this);
                    creatingDialog.setBtnLayVisible(false);
                    creatingDialog.setTitleVisible(false);
                    creatingDialog.setMessage(getResources().getString(R.string.creating));
                }
                creatingDialog.show();
                // TODO 创建家庭
                mPresenter.createFamily();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CreateFamilyActivity.class));
    }
}

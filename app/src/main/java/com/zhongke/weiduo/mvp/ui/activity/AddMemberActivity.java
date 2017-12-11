package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AddMemberContract;
import com.zhongke.weiduo.mvp.presenter.AddMemberPresenter;
import com.zhongke.weiduo.mvp.ui.widget.CustomDialog;
import com.zhongke.weiduo.util.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 添加家庭成员界面
 * Created by llj on 2017/6/21.
 */

public class AddMemberActivity extends BaseMvpActivity implements AddMemberContract, View.OnClickListener {
    private AddMemberPresenter mPresenter;

    @Bind(R.id.add_member_phone_input)
    EditText phoneInput;

    @Bind(R.id.add_member_role_input)
    EditText roleInput;

    @Bind(R.id.add_member_spec_input)
    EditText specInput;

    @Bind(R.id.add_member_add_btn)
    TextView addBtn;

    /**
     * 请稍候... dialog
     */
    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Tools.setStatusColor(this, getResources().getColor(R.color.edit_lay_bg));
        setTitleName(getResources().getString(R.string.add_members));
//        baseTitle.setTitleBackground(R.color.edit_lay_bg);
//        baseTitle.setLeftImgRes(R.drawable.back_img_black);
//        baseTitle.setTitleNameColor(R.color.black_text_color);
        setCenterLay(R.layout.activity_add_members);

        showCenterView();
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        addBtn.setOnClickListener(this);
    }

    @Override
    public void addMemberSuccess() {
        dialog.dismiss();
    }

    @Override
    public void addMemberFailed(int errorCode, String msg) {
        dialog.dismiss();
        Toast.makeText(AddMemberActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new AddMemberPresenter(AddMemberActivity.this, mDataManager, this);
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_member_add_btn:
                // 添加按钮
                // 手机号码
                String phone = phoneInput.getText().toString().trim();
                if (!StringUtils.isPhone(phone)) {
                    Toast.makeText(AddMemberActivity.this, getResources().getString(R.string.register_tips_2), Toast.LENGTH_SHORT).show();
                    return;
                }

                String roleName = roleInput.getText().toString().trim();
                if (StringUtils.isEmpty(roleName)) {
                    Toast.makeText(AddMemberActivity.this, getResources().getString(R.string.tips_1), Toast.LENGTH_SHORT).show();
                    return;
                }

                String spec = specInput.getText().toString().trim();
                if (StringUtils.isEmpty(roleName)) {
                    Toast.makeText(AddMemberActivity.this, getResources().getString(R.string.tips_2), Toast.LENGTH_SHORT).show();
                    return;
                }

                // 得到以空格为分隔的字符串数组
                String[] specs = spec.split(" ");

                // TODO 在组数据的时候要判断具体拆分出来的字符串是不是为""，因为用户可能连续输入多个空格
                for (int i = 0; i < specs.length; i++) {
                    Log.i("llj","分隔"+i+"----->>>"+specs[i]);
                }

                if (dialog == null) {
                    dialog = new CustomDialog(AddMemberActivity.this);
                    dialog.setBtnLayVisible(false);
                    dialog.setTitleVisible(false);
                    dialog.setMessage(getResources().getString(R.string.creating));
                }
                dialog.show();
                mPresenter.addMembers();
                break;
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, AddMemberActivity.class));
    }
}

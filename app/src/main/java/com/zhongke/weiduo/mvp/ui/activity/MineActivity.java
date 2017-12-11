package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MineContract;
import com.zhongke.weiduo.mvp.model.event.ModifyImage;
import com.zhongke.weiduo.mvp.model.event.ModifyNickName;
import com.zhongke.weiduo.mvp.presenter.MinePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Karma on 2017/9/25.
 */

public class MineActivity extends BaseMvpActivity implements MineContract,View.OnClickListener {

    @Bind(R.id.user_constraint)
    ConstraintLayout user_constraint;
    @Bind(R.id.collection_constraint)
    ConstraintLayout collection;
    @Bind(R.id.setting_constraint)
    ConstraintLayout setting;
    @Bind(R.id.activity_mine_head_portrait)
    ImageView headPortrait;
    @Bind(R.id.activity_mine_nick_name)
    TextView nickName;
    @Bind(R.id.activity_mine_user_name)
    TextView userName;

    private String headImageUrl;
    private String nickNameStr;
    private String userNameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_mine);
        showCenterView();
        EventBus.getDefault().register(this);
        baseTitle.setTitleName("我的");
        ButterKnife.bind(this);
        receiverIntent();
        initView();
    }

    private void receiverIntent() {
        headImageUrl = getIntent().getStringExtra("headImgUrl");
        nickNameStr = getIntent().getStringExtra("nickNameStr");
        userNameStr = getIntent().getStringExtra("userName");
    }


    private void initView() {
        Glide.with(this).load(headImageUrl).into(headPortrait);
        nickName.setText(nickNameStr);
        userName.setText("账号 : "+userNameStr);

        user_constraint.setOnClickListener(this);
        collection.setOnClickListener(this);
        setting.setOnClickListener(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        MinePresenter minePresenter = new MinePresenter(MineActivity.this,this);
        return minePresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_constraint:
                // 用户信息
                Intent intent = new Intent(this,PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.collection_constraint:
                // 收藏
                MyCollectionActivity.startActivity(MineActivity.this);
                break;
            case R.id.setting_constraint:
                // 设置
                SettingActivity.startActivity(MineActivity.this);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    public static void startActivity(Context context,String headImgUrl,String nickName,String userName){
        Intent intent = new Intent(context,MineActivity.class);
        intent.putExtra("headImgUrl",headImgUrl);
        intent.putExtra("nickNameStr",nickName);
        intent.putExtra("userName",userName);
        context.startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeImage(ModifyImage event) {
        if (!TextUtils.isEmpty(event.imageUrl)) {
            Glide.with(this).load(event.imageUrl).into(headPortrait);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeNickName(ModifyNickName event) {
        if (!TextUtils.isEmpty(event.nickName)) {
            nickName.setText(event.nickName);
        }

    }
}

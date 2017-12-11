package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.presenter.AddPrizesPresenter;

public class AddPrizesActivity extends BaseMvpActivity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setCenterLay(R.layout.activity_add_prizes);
        baseTitle.setRightVisible(true);
        baseTitle.setTitleName("添加奖品");
        baseTitle.setRightText("添加");
        baseTitle.setRightOnClickListener(addListener);
        showCenterView();
    }

    @Override
    protected BasePresenter createPresenter() {
        AddPrizesPresenter prizesPresenter = new AddPrizesPresenter(context);
        return prizesPresenter;
    }


    private View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //点击添加后上传网络,发放奖品页面发生相应改变
        }
    };

    public static void startActivity(Context context) {
        Intent intent = new Intent(context,AddPrizesActivity.class);
        context.startActivity(intent);

    }
}

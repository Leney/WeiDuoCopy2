package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ExpertDetailsContract;
import com.zhongke.weiduo.mvp.model.entity.NewExpertCourseBean;
import com.zhongke.weiduo.mvp.model.entity.NewExpertDetailBean;
import com.zhongke.weiduo.mvp.presenter.ExpertDetailsPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.ExpertGvAdapter;
import com.zhongke.weiduo.mvp.ui.widget.AdvisoryDialog;
import com.zhongke.weiduo.mvp.ui.widget.ExpertDialog;
import com.zhongke.weiduo.mvp.ui.widget.ListViewForScrollview;
import com.zhongke.weiduo.mvp.ui.widget.MyGridView;

import java.util.List;

import static com.zhongke.weiduo.R.id.tv_collection;

/**
 * Created by ${tanlei} on 2017/9/5.
 * 专家介绍界面
 */

public class ExpertDetailsActivity extends BaseMvpActivity implements ExpertDetailsContract, View.OnClickListener, AdvisoryDialog.OnSubmitClickListener {
    private ListViewForScrollview lv;
    private MyGridView gv;
    /**
     * 专家Id
     */
    private int userId;
    private ExpertGvAdapter gvAdapter;
    //    private ExpertLvAdapter lvAdapter;
    private TextView tvCourse, tvEvaluation, tvDetail, tvAdvisory, tvReservation, tvTag, tvName, tvCollection, expertInfo;
    private ExpertDetailsPresenter expertDetailsPresenter;
    /**
     * 收藏状态
     */
    private int collectedStatus;
    /**
     * 收藏的状态
     */
    private final int COLLECTED = 1;
    /**
     * 未收藏的状态
     */
    private final int UNCOLLECTED = 0;
    private List<NewExpertCourseBean.ExpertCourseBean> list;
    private LinearLayout courseLayout;
    private ImageView arrow;
    private AdvisoryDialog advisoryDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_expert_detail);
        showLoadingView();
        userId = getIntent().getIntExtra("userId", -1);
        initView();
        setListeners();
    }

    @Override
    protected BasePresenter createPresenter() {
        expertDetailsPresenter = new ExpertDetailsPresenter(this, mDataManager);
        return expertDetailsPresenter;
    }

    private void setListeners() {
        tvEvaluation.setOnClickListener(this);
        gv.setOnItemClickListener(courseListener);
        tvCollection.setOnClickListener(this);
        courseLayout.setOnClickListener(this);
    }

    private void initView() {
        setTitleName("专家介绍");
        lv = (ListViewForScrollview) findViewById(R.id.lv);
        gv = (MyGridView) findViewById(R.id.gv);

//        lvAdapter = new ExpertLvAdapter(expertBean.getStudentEvaluationBeanList(), this);
//        lv.setAdapter(lvAdapter);
        tvCollection = (TextView) findViewById(tv_collection);
        tvTag = (TextView) findViewById(R.id.tv_tag);
        tvCourse = (TextView) findViewById(R.id.tv1);
        courseLayout = (LinearLayout) findViewById(R.id.expert_detail_ll_course);
        arrow = (ImageView) findViewById(R.id.expert_detail_course_arrow);
        tvEvaluation = (TextView) findViewById(R.id.tv2);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvDetail = (TextView) findViewById(R.id.tv_detail);
        tvDetail.setOnClickListener(this);
        tvAdvisory = (TextView) findViewById(R.id.tv_advisory);
        tvAdvisory.setOnClickListener(this);
        tvReservation = (TextView) findViewById(R.id.reservation);
        tvReservation.setOnClickListener(this);
        expertInfo = (TextView) findViewById(R.id.expert_info);
        //去除嵌套乱滚动现象
        ScrollView sv = (ScrollView) findViewById(R.id.scroll_view);
        sv.smoothScrollTo(0, 0);
        expertDetailsPresenter.getExpertDetail(userId);
        expertDetailsPresenter.getExpertCourse(userId);
    }

    public static void startActivity(Context context, int userId) {
        Intent intent = new Intent(context, ExpertDetailsActivity.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.expert_detail_ll_course:
                if (gv.getVisibility() == View.VISIBLE) {
                    gv.setVisibility(View.GONE);
                    arrow.setImageResource(R.mipmap.zhankai);
                } else {
                    gv.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.mipmap.shouqi);
                }
                break;
            case R.id.tv2:
                if (lv.getVisibility() == View.VISIBLE) {
                    lv.setVisibility(View.GONE);
                } else {
                    lv.setVisibility(View.VISIBLE);
                }
                break;
            //查看详情
            case R.id.tv_detail:
                ExpertDialog expertDialog = new ExpertDialog(this);
                expertDialog.show();
                break;
            //咨询
            case R.id.tv_advisory:
                SessionActivity2.startActivity(this, SessionActivity2.SESSION_TYPE_PRIVATE, "0", "");
                break;
            //预约事件
            case R.id.reservation:
                advisoryDialog = new AdvisoryDialog(this);
                advisoryDialog.setOnSubmitClickListener(this);
                advisoryDialog.show();
                break;
            case tv_collection:
                if (collectedStatus == 0) {
                    expertDetailsPresenter.requestCollectionExpert(userId);
                } else {
                    expertDetailsPresenter.cancelCollectionExpert(userId);
                }
                break;
            default:
                break;
        }
    }


    private GridView.OnItemClickListener courseListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CourseActivity.startActivity(ExpertDetailsActivity.this, list.get(position).getId());
        }
    };

    /**
     * dialog的回调方法
     *
     * @param phone
     * @param text
     */
    @Override
    public void onSubmitClick(String phone, String text) {
        Toast.makeText(this, "提交成功" + phone + text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示专家详情
     *
     * @param newExpertDetailBean
     */
    @Override
    public void getExpertDetail(NewExpertDetailBean newExpertDetailBean) {
        if (null != newExpertDetailBean) {
            showCenterView();
            collectedStatus = newExpertDetailBean.getExpert().getCollectId();
            Glide.with(this).load(newExpertDetailBean.getExpert().getCoverUrl()).into((ImageView) findViewById(R.id.iv_detail_expert));
//            collectObjectId = newExpertDetailBean.getExpert().getUserId();
            changeCollectionBotton(collectedStatus);
            tvName.setText(newExpertDetailBean.getExpert().getNickName());
            tvTag.setText(newExpertDetailBean.getExpert().getTeachTag());
            expertInfo.setText(newExpertDetailBean.getExpert().getInfo());
        } else {
            showNoDataView();
        }
    }

    @Override
    public void requestFailure() {
        showErrorView();
    }

    @Override
    public void collectionExpert() {
        collectedStatus = COLLECTED;
        changeCollectionBotton(collectedStatus);
    }

    @Override
    public void cancelCollectionExpert() {
        collectedStatus = UNCOLLECTED;
        changeCollectionBotton(collectedStatus);
    }

    /**
     * 专家的课程列表
     *
     * @param newExpertCourseBean
     */
    @Override
    public void showExpertCourse(NewExpertCourseBean newExpertCourseBean) {
        if (null == gvAdapter) {
            this.list = newExpertCourseBean.getExpertCourse();
            gvAdapter = new ExpertGvAdapter(newExpertCourseBean.getExpertCourse(), this);
            gv.setAdapter(gvAdapter);
        }
    }

    private void changeCollectionBotton(int status) {
        if (status == COLLECTED) {
            tvCollection.setText("已收藏");
            Drawable drawableTop = getResources().getDrawable(R.drawable.collection);
            drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(), drawableTop.getMinimumHeight());
            tvCollection.setCompoundDrawables(null, drawableTop, null, null);
        } else {
            tvCollection.setText("收藏");
            Drawable drawableTop = getResources().getDrawable(R.drawable.uncollection);
            drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(), drawableTop.getMinimumHeight());
            tvCollection.setCompoundDrawables(null, drawableTop, null, null);
        }
    }
}

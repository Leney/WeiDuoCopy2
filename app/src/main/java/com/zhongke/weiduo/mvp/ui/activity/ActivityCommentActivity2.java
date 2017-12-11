package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActivityCommentContract;
import com.zhongke.weiduo.mvp.model.entity.ActivityBehaviorBean;
import com.zhongke.weiduo.mvp.model.entity.TaskListBean;
import com.zhongke.weiduo.mvp.presenter.ActivityCommentPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.BehaviorAdapter;
import com.zhongke.weiduo.mvp.ui.widget.FactorEvaluationDialog;
import com.zhongke.weiduo.mvp.ui.widget.ListViewForScrollview;
import com.zhongke.weiduo.mvp.ui.widget.dialog.GoGrandRewardDialog;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * 从ActivityCommentActivity改的评论界面
 */

public class ActivityCommentActivity2 extends BaseMvpActivity implements ActivityCommentContract ,View.OnClickListener{
    private ActivityCommentPresenter presenter;
    private GoGrandRewardDialog dialog;
    private int actionId;
    private int flowId;
    private ListViewForScrollview behaviorLV;
    private List<ActivityBehaviorBean.BehaviorBean> behaviorList;
    private TextView commentTitle;
    private String beginTime;
    private String endTime;
    private String actionName;
    private StringBuilder stringBuilder;
    private StringBuilder behaviorBuilder;
    private StringBuilder behaviorIdBuilder;
    private StringBuilder fNodeIdBuilder;
    private StringBuilder nValueBuilder;
    private StringBuilder runTimeBuilder;
    private StringBuilder doObjectBuilder;
    private BehaviorAdapter adapter;
    private EditText commentEt;
    private String userId;
    private final static int fromChildTaskBean = 1;
    private final static int fromMyTaskBean = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiverIntent();
        stringBuilder = new StringBuilder();

        behaviorBuilder = new StringBuilder();
        behaviorIdBuilder = new StringBuilder();
        fNodeIdBuilder = new StringBuilder();
        nValueBuilder = new StringBuilder();
        runTimeBuilder = new StringBuilder();
        doObjectBuilder = new StringBuilder();

        setCenterLay(R.layout.activity_activitycomment_modify);
        baseTitle.setRightVisible(true);
        baseTitle.setRightText("发布");
        baseTitle.setTitleName("评论");

        baseTitle.setRightOnClickListener(rightListener);
        initView();
        LogUtil.e("actionId-"+actionId);
        LogUtil.e("flowId-"+flowId);
        this.presenter.loadData(actionId+"",flowId+"");
        //showDialog();
    }

    private void receiverIntent() {
        int whereFrom = getIntent().getIntExtra("whereFrom",-1);
        LogUtil.e("whereFrom---"+whereFrom);

        if (whereFrom == fromChildTaskBean) {
            TaskListBean.ChildTaskBean bean = (TaskListBean.ChildTaskBean) getIntent().getSerializableExtra("ChildTaskBean");
            //LogUtil.e("bean---"+bean.toString());
            this.actionId = bean.getActionId();
            LogUtil.e("actionId---"+actionId);
            if (actionId == -1) {
                finish();
                return;
            }
            userId = bean.getUserId()+"";
            flowId = bean.getFlowId();
            beginTime = bean.getBeginTime();
            endTime = bean.getEndTime();
            actionName = bean.getActionName();
            LogUtil.e("flowId--"+flowId+"beginTime--"+beginTime+"endTime--"+endTime+"actionName--"+actionName);
        } else if (whereFrom == fromMyTaskBean) {
            TaskListBean.MyTaskBean bean = (TaskListBean.MyTaskBean) getIntent().getSerializableExtra("myTaskBean");
            //LogUtil.e("bean---"+bean.toString());
            this.actionId = bean.getActionId();
            LogUtil.e("actionId---"+actionId);
            if (actionId == -1) {
                finish();
                return;
            }
            userId = bean.getUserId()+"";
            flowId = bean.getFlowId();
            beginTime = bean.getBeginTime();
            endTime = bean.getEndTime();
            actionName = bean.getActionName();

            LogUtil.e("flowId--"+flowId+"beginTime--"+beginTime+"endTime--"+endTime+"actionName--"+actionName);
        }
    }

    private void showDialog(){
        FactorEvaluationDialog dialog=new FactorEvaluationDialog(this,this);
        dialog.show();
    }

    private ScrollView scrollView;
    private void initView() {
        scrollView=(ScrollView) this.findViewById(R.id.activitycomment_scrollview);
        commentTitle = (TextView) findViewById(R.id.activity_comment_title);
        setCommentTitle();
        commentEt = (EditText) findViewById(R.id.activity_comment_et);
        behaviorLV = (ListViewForScrollview) findViewById(R.id.activity_comment_lv);
        behaviorList = new ArrayList<>();
    }

    private void setCommentTitle() {
        stringBuilder.reverse();
        stringBuilder.append("[");
        stringBuilder.append(beginTime).append("-");
        stringBuilder.append(endTime).append("]").append(" ");
        stringBuilder.append(actionName);

        commentTitle.setText(stringBuilder.toString());
    }

    @Override
    protected BasePresenter createPresenter() {
        this.presenter = new ActivityCommentPresenter(this);
        return presenter;
    }

    @Override
    public void setRewards() {
        scrollView.scrollTo(0,0);
    }

    @Override
    public void loadDataSuccess(ActivityBehaviorBean bean) {
        if (bean.getBehavior().isEmpty()) {
            UIUtils.showToast("获取活动流程行为列表 为空");
        }
        behaviorList.addAll(bean.getBehavior());

        BehaviorAdapter adapter = new BehaviorAdapter(this,behaviorList);
        behaviorLV.setAdapter(adapter);

        for (int i=0,size = behaviorList.size();i < size;i++) {
            ActivityBehaviorBean.BehaviorBean behaviorBean = behaviorList.get(i);
            if (i != size -1) {
                behaviorIdBuilder.append(behaviorBean.getBehaviorId()).append(",");
                fNodeIdBuilder.append(behaviorBean.getFNodeId()).append(",");
                runTimeBuilder.append(behaviorBean.getRunTime()).append(",");
                doObjectBuilder.append(behaviorBean.getDoObject()).append(",");
            } else {
                behaviorIdBuilder.append(behaviorBean.getBehaviorId());
                fNodeIdBuilder.append(behaviorBean.getFNodeId());
                runTimeBuilder.append(behaviorBean.getRunTime());
                doObjectBuilder.append(behaviorBean.getDoObject());
            }
        }
        showCenterView();
    }

    @Override
    public void loadDataFailed(CommonException e) {
        showCenterView();
        UIUtils.showToast("获取活动流程行为列表失败");
    }


    @Override
    public void onClick(View v) {

    }
    //点击发布
    private View.OnClickListener rightListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(commentEt.getText().toString().trim())) {
                UIUtils.showToast("请输入评价内容");
                return;
            }
            LogUtil.e("behaviorIdBuilder--"+behaviorIdBuilder.toString());
            if (TextUtils.isEmpty(behaviorIdBuilder.toString())) {
                UIUtils.showToast("没有获取到相应活动行为");
                return;
            }
            nValueBuilder = new StringBuilder();
            //获取ratingbar的评分
            for (int j = 0,size = behaviorLV.getChildCount();j < size;j++) {
                LinearLayout linearLayout = (LinearLayout) behaviorLV.getChildAt(j);
                RatingBar ratingBar = (RatingBar) linearLayout.findViewById(R.id.item_comment_rating_bar);
                float startValue = ratingBar.getRating();
                int value = (int) Math.ceil(startValue);
                LogUtil.e("value--"+value);
                if (j != size - 1) {
                    nValueBuilder.append(String.valueOf(value)).append(",");
                } else {
                    nValueBuilder.append(String.valueOf(value));
                }
            }
            LogUtil.e("nValueBuilder"+nValueBuilder.toString());
            //发布评价
            String behaviorIds=behaviorIdBuilder.toString();
            String fNodeIds=fNodeIdBuilder.toString();
            String nValues=nValueBuilder.toString();
            String runTimes=runTimeBuilder.toString();
            String doObjects=doObjectBuilder.toString();
            String evaluate=commentEt.getText().toString().trim();

            if (actionId != 0 & flowId != 0 &&!TextUtils.isEmpty(behaviorIds) && !TextUtils.isEmpty(evaluate)) {
                presenter.CommentActivityBehavior(userId,actionId+"",flowId+"",behaviorIds,fNodeIds,nValues,
                        runTimes,doObjects,evaluate);
            }

        }

    };

    @Override
    public void CommentActivitySuccess() {
        UIUtils.showToast("评论成功");
        if (dialog == null) {
            dialog = new GoGrandRewardDialog(ActivityCommentActivity2.this, R.style.dialog_no_title_style);
        }
        dialog.show();
    }

    @Override
    public void CommentActivityFailed(CommonException e) {
        UIUtils.showToast("评论失败"+e.getErrorMsg());

    }

    public static void startActivity(Context context,TaskListBean.MyTaskBean bean) {
        Intent intent = new Intent(context,ActivityCommentActivity2.class);
        intent.putExtra("myTaskBean",bean);
        context.startActivity(intent);
    }

    public static void startActivity(Context context,TaskListBean.ChildTaskBean bean,int whereFrom) {
        Intent intent = new Intent(context,ActivityCommentActivity2.class);
        intent.putExtra("whereFrom",whereFrom);
        intent.putExtra("ChildTaskBean",bean);
        context.startActivity(intent);
    }

}

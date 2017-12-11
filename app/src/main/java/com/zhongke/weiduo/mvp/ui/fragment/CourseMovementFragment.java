package com.zhongke.weiduo.mvp.ui.fragment;

import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpFragment;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CourseMovementContract;
import com.zhongke.weiduo.mvp.model.entity.CurriculumActivityBean;
import com.zhongke.weiduo.mvp.model.entity.CurriculumTemplateBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseActionEntity;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseActionResult;
import com.zhongke.weiduo.mvp.presenter.CourseMovementPresenter;
import com.zhongke.weiduo.mvp.ui.activity.ActivityDetailActivity;
import com.zhongke.weiduo.mvp.ui.adapter.CurriculumActivityAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.CurriculumTemplateAdapter;
import com.zhongke.weiduo.mvp.ui.widget.ListViewForScrollview;
import com.zhongke.weiduo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/27.
 * 日程活动Fragment
 */

public class CourseMovementFragment extends BaseMvpFragment implements CourseMovementContract, CurriculumActivityAdapter.MyOnClickListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = "CourseMovementFragment";
    private CourseMovementPresenter mPresenter;
    private ListViewForScrollview lvCurriculum, lvTemplate;
    private CurriculumActivityAdapter curriculumActivityAdapter;
    private List<CurriculumActivityBean> curriculumActivityBeanList;
    private List<CurriculumTemplateBean> curricelumList;
    private CurriculumTemplateAdapter curriculumTemplateAdapter;
    private LinearLayout llClick;
    private ImageView ivCheck;
    private TextView tvCheck, tvAdd;
    public static boolean IS_CHECK = false;

    /**
     * 课程id
     */
    public int stepId = -1;
    private List<CourseActionResult.RecordsBean> recordsBeanList;
    private List<CourseActionResult.RecordsBean> checkActions;
    private StringBuffer stringBuffer;

    private TextView tv_course_action;
    private RelativeLayout rl_all_check;

    public static CourseMovementFragment newInstance(int id) {
        LogUtil.i("llj", "初始化id--------->>>" + id);
        CourseMovementFragment instance = new CourseMovementFragment();
        instance.stepId = id;
        return instance;
    }

    @Override
    protected BasePresenter createPresenter() {
        return mPresenter = new CourseMovementPresenter(this, mDataManager);
    }

    @Override
    protected void initView(View baseView) {
        super.initView(baseView);
        LogUtil.i("llj", "stepId--------->>>" + stepId);
        if (stepId == -1) {
            return;
        }
        setCenterLay(R.layout.fragment_course_movement);
        checkActions = new ArrayList<>();
        stringBuffer = new StringBuffer();

        CourseActionEntity entity = new CourseActionEntity();
        entity.setCourseId(stepId+"");
        entity.setPageIndex("1");
        //获取课程的活动
        mPresenter.getActDataInfo(entity);

        llClick = (LinearLayout) baseView.findViewById(R.id.rl_click);
        llClick.setOnClickListener(this);
        ivCheck = (ImageView) baseView.findViewById(R.id.imageview);
        tvCheck = (TextView) baseView.findViewById(R.id.tv_check);
        tvAdd = (TextView) baseView.findViewById(R.id.tv_add);
        tvAdd.setOnClickListener(this);
        lvTemplate = (ListViewForScrollview) baseView.findViewById(R.id.lv_template);
        lvCurriculum = (ListViewForScrollview) baseView.findViewById(R.id.listview);
        lvTemplate.setOnItemClickListener(this);
        tv_course_action = (TextView) baseView.findViewById(R.id.fragment_course_text_course_action);
        rl_all_check = (RelativeLayout) baseView.findViewById(R.id.fragment_course_rl_all_check);

        curriculumActivityBeanList = new ArrayList<>();

        //curriculumActivityAdapter = new CurriculumActivityAdapter(curriculumActivityBeanList, getActivity(), this);
        lvCurriculum.setOnItemClickListener(this);

        curricelumList = new ArrayList<>();
        curriculumTemplateAdapter = new CurriculumTemplateAdapter(curricelumList, getActivity());
        lvTemplate.setAdapter(curriculumTemplateAdapter);
        setListViewHeightBasedOnChildren(lvTemplate);
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    public void getActDataInfoSuccess(List<CurriculumActivityBean> actList, List<CurriculumTemplateBean> templateList) {
        this.curriculumActivityBeanList.addAll(actList);
        this.curricelumList.addAll(templateList);
        curriculumActivityAdapter.notifyDataSetChanged();
        curriculumTemplateAdapter.notifyDataSetChanged();
    }

    @Override
    public void getActDataInfoFailed(int errorCode, String msg) {

    }

    //获取课程的活动成功
    @Override
    public void getCourseActionSuccess(CourseActionResult bean) {
        recordsBeanList = new ArrayList<>();
        recordsBeanList.clear();
        recordsBeanList.addAll(bean.getRecords());
        for (CourseActionResult.RecordsBean record : bean.getRecords()) {
            LogUtil.e("record.getAddId---"+record.getAddId());
            if (record.getAddId() == 0) {
                recordsBeanList.add(record);
            }
        }
        if (recordsBeanList!=null && recordsBeanList.size() > 0) {
            rl_all_check.setVisibility(View.VISIBLE);
        }

        curriculumActivityAdapter = new CurriculumActivityAdapter(recordsBeanList, getActivity(), this);
        lvCurriculum.setAdapter(curriculumActivityAdapter);
        setListViewHeightBasedOnChildren(lvCurriculum);

        showCenterView();
    }

    @Override
    public void getCourseActionFailed(CommonException e) {
        showErrorView();
        UIUtils.showToast(e.getErrorMsg());
    }

    //收藏成功
    @Override
    public void collectionActionSuccess() {
        LogUtil.e("收藏成功");
    }

    @Override
    public void collectionActionFailed() {
        ToastUtil.showShort(getActivity(),"收藏失败");
    }

    @Override
    public void cancelCollectionSuccess() {
        LogUtil.e("取消收藏成功");
    }

    @Override
    public void cancelCollectionFailed(CommonException e) {
        LogUtil.e("取消收藏失败");
    }

    @Override
    public void joinCourseActionSuccess() {
        showCenterView();
        curriculumActivityAdapter.notifyDataSetChanged();
    }

    @Override
    public void joinCourseActionFailed(CommonException e) {
        showCenterView();
    }


    @Override
    public void clickListener(int position, String str) {
        //看点击的是哪个按钮
        switch (str) {
            case "shoucang":
                LogUtil.e("click shoucang");
                int actionID = recordsBeanList.get(position).getId();
                if (recordsBeanList.get(position).getCollectActId() == 1) {
                    //取消收藏
                    recordsBeanList.get(position).setCollectActId(0);
                    mPresenter.cancelCollection("4",actionID);
                } else {
                    LogUtil.e(TAG,"position--"+position+"collectObjectId"+actionID);
                    //收藏
                    mPresenter.collection("4",actionID);
                    recordsBeanList.get(position).setCollectActId(1);
                }
                break;
            case "fenxiang":

                break;
            case "xuanzhong":
                if (recordsBeanList.get(position).isCheck()) {
                    //已经是选中状态,取消选中
                    recordsBeanList.get(position).setCheck(false);
                    if (checkActions.contains(recordsBeanList.get(position))) {
                        checkActions.remove(recordsBeanList.get(position));
                    }
                } else {
                    //设置选中
                    recordsBeanList.get(position).setCheck(true);
                    if (!checkActions.contains(recordsBeanList.get(position))) {
                        LogUtil.e("actionid-----"+recordsBeanList.get(position).getId());
                        checkActions.add(recordsBeanList.get(position));
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_click:
                if (!IS_CHECK) {
                    for (int i = 0; i < recordsBeanList.size(); i++) {
                        recordsBeanList.get(i).setCheck(true);
                    }
                    curriculumActivityAdapter.notifyDataSetChanged();
                    ivCheck.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.check));
                    tvCheck.setText("取消全选");

                    checkActions.clear();
                    checkActions.addAll(recordsBeanList);
                    IS_CHECK = true;
                } else {
                    for (int i = 0; i < recordsBeanList.size(); i++) {
                        recordsBeanList.get(i).setCheck(false);
                    }
                    curriculumActivityAdapter.notifyDataSetChanged();
                    ivCheck.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.uncheck));
                    tvCheck.setText("全选");
                    checkActions.clear();
                    IS_CHECK = false;
                }
                break;
            case R.id.tv_add:
                //TODO 点击了添加按钮之后把选中的数据在此界面中移除，并且刷新adapter
                showLoadingView();
                stringBuffer.reverse();
                for (int i = 0,size = checkActions.size();i < size;i++) {
                    if (i != size -1) {
                        stringBuffer.append(checkActions.get(i).getId()).append(",");

                    } else {
                        stringBuffer.append(checkActions.get(i).getId());

                    }

                   int addIndex =  recordsBeanList.indexOf(checkActions.get(i));
                    recordsBeanList.get(addIndex).setAddId(1);
                }
                mPresenter.joinCourseAction(stringBuffer.toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int actionId = recordsBeanList.get(position).getId();
        ActivityDetailActivity.startActivity(getActivity(),actionId,"");
    }

}

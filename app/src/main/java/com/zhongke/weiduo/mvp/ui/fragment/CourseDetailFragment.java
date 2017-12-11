package com.zhongke.weiduo.mvp.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpFragment;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CourseDetailContract;
import com.zhongke.weiduo.mvp.model.entity.CourseDetailBean;
import com.zhongke.weiduo.mvp.model.entity.DetailChildbean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.JoinCourseEntity;
import com.zhongke.weiduo.mvp.presenter.CourseDetailPresenter;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * 课程详情介绍Fragment
 * Created by llj on 2017/9/14.
 */

public class CourseDetailFragment extends BaseMvpFragment implements CourseDetailContract, View.OnClickListener {
    private CourseDetailPresenter mPresenter;
    private TextView name, goodPercent, signUpNum, playNum, info, teacherBtn, addBtn;
    private LinearLayout imgLay;

    public int id;
    private JoinCourseEntity entity;
    private CourseDetailResult courseDetailResult1;


    public static CourseDetailFragment newInstance(int id) {
        CourseDetailFragment instance = new CourseDetailFragment();
        instance.id = id;
        return instance;
    }

    @Override
    protected BasePresenter createPresenter() {
        return mPresenter = new CourseDetailPresenter(this, mDataManager);
    }

    @Override
    protected void initView(View baseView) {
        super.initView(baseView);
        setCenterLay(R.layout.fragment_course_detail);
        mPresenter.getCourseDetail(id+"");

        name = (TextView) baseView.findViewById(R.id.course_detail_name);
        goodPercent = (TextView) baseView.findViewById(R.id.course_detail_good_percent);
        signUpNum = (TextView) baseView.findViewById(R.id.course_detail_sign_up_num);
        playNum = (TextView) baseView.findViewById(R.id.course_detail_play_num);
        info = (TextView) baseView.findViewById(R.id.course_detail_info);
        teacherBtn = (TextView) baseView.findViewById(R.id.course_detail_teacher_introduce);
        teacherBtn.setOnClickListener(this);
        addBtn = (TextView) baseView.findViewById(R.id.course_detail_add_course);
        addBtn.setOnClickListener(this);
        imgLay = (LinearLayout) baseView.findViewById(R.id.course_detail_img_lay);

        entity = new JoinCourseEntity();
        entity.setCourseId(id+"");

    }

    //加入课程成功
    @Override
    public void joinCourseSuccess() {
        //加入成功
        showCenterView();
        addBtn.setText(R.string.already_joined);
        addBtn.setClickable(false);
        showToast("加入课程成功");
    }

    @Override
    public void joinCourseFailed() {

    }

    @Override
    public void getCourseDetailSuccess(CourseDetailResult courseDetailResult) {
//        name.setText(bean.getName());
//        goodPercent.setText(bean.getGoodPercent() + getResources().getString(R.string.good_percent));
//        signUpNum.setText(bean.getSignUpNum() + getResources().getString(R.string.sign_up));
//        playNum.setText(bean.getPlayNum() + getResources().getString(R.string.play_time));
//        info.setText(bean.getInfo());
//
//        int length = bean.getInfoImgs().size();

        name.setText(courseDetailResult.getCourse().getCourseName());
        info.setText(courseDetailResult.getCourse().getInfo());
        if (courseDetailResult.getCourse().getAddId() == 1) {
            addBtn.setText(R.string.already_joined);
            addBtn.setClickable(false);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtils.dip2px(getActivity(),160));
        params.bottomMargin = DisplayUtils.dip2px(getActivity(),15);
        ImageView imageView = new ImageView(getActivity());
        PhotoLoaderUtil.display(getActivity(),imageView,courseDetailResult.getCourse().getCoverUrl(),null);
        imgLay.addView(imageView,params);

        showCenterView();
    }

    @Override
    public void getCourseDetailFailed(CommonException e) {
        showErrorView();
        UIUtils.showToast(e.getErrorMsg());
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        mPresenter.getCourseDetail(id+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.course_detail_teacher_introduce:
                // 讲师介绍
                break;
            case R.id.course_detail_add_course:
                // 加入课程
                mPresenter.joinCourse(entity);
                showLoadingView();
                break;
        }
    }

    public void showToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }
}

package com.zhongke.weiduo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseFragment;
import com.zhongke.weiduo.mvp.model.entity.CourseEvaluationBean;
import com.zhongke.weiduo.mvp.ui.adapter.CourseEvaluationAdapter;
import com.zhongke.weiduo.mvp.ui.widget.view.StarBar;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/14.
 * 课程评价Fragment
 */

public class CourseEvaluationFragment extends BaseFragment {
    private ListView lv;
    /**
     * 评分控件
     */
    private StarBar sb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_evaluation, null);
        lv = (ListView) view.findViewById(R.id.listView);
        View headView = inflater.inflate(R.layout.course_evaluation_head, lv, false);
        sb = (StarBar) headView.findViewById(R.id.sb);
        sb.setStarMark(4.7f);
        List<CourseEvaluationBean> list = CourseEvaluationBean.getData();
        CourseEvaluationAdapter adapter = new CourseEvaluationAdapter(list, getActivity());
        lv.setAdapter(adapter);
        lv.addHeaderView(headView);
        return view;
    }
}

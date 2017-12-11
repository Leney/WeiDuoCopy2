package com.zhongke.weiduo.mvp.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpFragment;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CourseCatalogueContract;
import com.zhongke.weiduo.mvp.model.entity.CatalogueBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CataLogBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;
import com.zhongke.weiduo.mvp.presenter.CourseCataloguePresenter;
import com.zhongke.weiduo.mvp.ui.adapter.CourseCatalogueAdapter;
import com.zhongke.weiduo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程详情目录Fragment
 * Created by llj on 2017/9/14.
 */

public class CourseCatalogueFragment extends BaseMvpFragment implements CourseCatalogueContract {
    private CourseCataloguePresenter mPresenter;
    private ListView listView;
    private CourseCatalogueAdapter mAdapter;
    private TextView totalNum;
    public List<CourseDetailResult.CatalogBean> catalogBeanList;

    public int id;

    public OnChangeClassListener mListener;

    public static CourseCatalogueFragment newInstance(int id,OnChangeClassListener listener) {
        CourseCatalogueFragment instance = new CourseCatalogueFragment();
        instance.id = id;
        instance.mListener = listener;
        return instance;
    }

    @Override
    protected BasePresenter createPresenter() {
        return mPresenter = new CourseCataloguePresenter(this, mDataManager);
    }

    @Override
    protected void initView(View baseView) {
        super.initView(baseView);
        setCenterLay(R.layout.fragement_course_catalogue);
        mPresenter.getCourseCatalog(id+"");

        listView = (ListView) baseView.findViewById(R.id.course_catalogue_list_view);
        totalNum = (TextView) baseView.findViewById(R.id.course_catalogue_total);
        catalogBeanList = new ArrayList<>();
        mAdapter = new CourseCatalogueAdapter(catalogBeanList);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null) {
                    mListener.onChangeClass(position);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void getCourseCatalogSuccess(CourseDetailResult courseDetailResult) {
        List<CourseDetailResult.CatalogBean> catalogs = courseDetailResult.getCatalog();
        int size = catalogs.size();
        totalNum.setText("共"+String.valueOf(size)+"节课");
        catalogBeanList.clear();
        catalogBeanList.addAll(catalogs);
        mAdapter.notifyDataSetChanged();

        showCenterView();
    }

    @Override
    public void getCourseCatalogFailed(CommonException e) {
        showErrorView();
        UIUtils.showToast(e.getErrorMsg());
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        mPresenter.getCourseCatalog(id+"");
    }

    /**
     * 切换播放课程的监听回调
     */
    public interface OnChangeClassListener {
        void onChangeClass(int position);
    }

}

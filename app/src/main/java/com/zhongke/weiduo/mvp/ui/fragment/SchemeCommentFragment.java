package com.zhongke.weiduo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpFragment;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SchemeCommentContract;
import com.zhongke.weiduo.mvp.model.entity.CommentBean;
import com.zhongke.weiduo.mvp.presenter.SchemeCommentPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.SchemeCommentAdapter;
import com.zhongke.weiduo.mvp.ui.widget.ListViewForScrollview;

import java.util.ArrayList;
import java.util.List;

/**
 * 规划详情评论Fragment
 * Created by llj on 2017/9/18.
 */
public class SchemeCommentFragment extends BaseMvpFragment implements SchemeCommentContract {
    private SchemeCommentPresenter mPresenter;
    private ListViewForScrollview mListView;
    private SchemeCommentAdapter mAdapter;

    public int id;
//    public MyScrollView mScrollView;

    private List<CommentBean> mCommentBeanList;

    public static SchemeCommentFragment newInstance(int id) {
        SchemeCommentFragment instance = new SchemeCommentFragment();
        instance.id = id;
        return instance;
    }

//    public void setScrollView(MyScrollView scrollView){
//        mScrollView = scrollView;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scheme_comment, container, false);
        mCommentBeanList = new ArrayList<>();
        mListView = (ListViewForScrollview) view.findViewById(R.id.scheme_comment_list_view);
        mAdapter = new SchemeCommentAdapter(mCommentBeanList);
        mListView.setAdapter(mAdapter);
        mPresenter.getCommentList(id);
        return view;
    }

    @Override
    protected BasePresenter createPresenter() {
        return mPresenter = new SchemeCommentPresenter(getActivity(), mDataManager, this);
    }

    @Override
    public void getCommentListSuccess(List<CommentBean> list) {
        mCommentBeanList.addAll(list);
        mAdapter.notifyDataSetChanged();

//        setListViewHeightBasedOnChildren(mListView);
    }

    @Override
    public void getCommentListFailed(int errorCode, String msg) {

    }

//    public void setListViewHeightBasedOnChildren(ListView listView) {
//        // 获取ListView对应的Adapter
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            return;
//        }
//        int totalHeight = 0;
//        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
//            // listAdapter.getCount()返回数据项的数目
//            View listItem = listAdapter.getView(i, null, listView);
//            // 计算子项View 的宽高
//            listItem.measure(0, 0);
//            // 统计所有子项的总高度
//            totalHeight += listItem.getMeasuredHeight();
//        }
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
////        params.height = 3000;
//        LogUtil.i("llj","计算出的ListView内容高度------>>>"+params.height);
//        // listView.getDividerHeight()获取子项间分隔符占用的高度
//        // params.height最后得到整个ListView完整显示需要的高度
//        listView.setLayoutParams(params);
//    }

//    private void setListViewHeightBasedOnChildren(ListView listView) {
//        // 获取ListView对应的Adapter
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            return;
//        }
//        int totalHeight = 0;
//        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
//            View listItem = listAdapter.getView(i, null, listView);
//            listItem.measure(0, 0); // 计算子项View 的宽高
//            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
//        }
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight
//                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        // listView.getDividerHeight()获取子项间分隔符占用的高度
//        // params.height最后得到整个ListView完整显示需要的高度
//        listView.setLayoutParams(params);
//    }
}

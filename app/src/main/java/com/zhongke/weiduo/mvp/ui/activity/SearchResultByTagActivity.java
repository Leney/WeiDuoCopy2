package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SearchResultByTagContract;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyList;
import com.zhongke.weiduo.mvp.presenter.SearchResultByTagPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.FamilyListByTagAdapter;

import java.util.List;

/**
 * Created by dgg1 on 2017/11/17.
 * 家庭和好友通过标签搜索后的界面
 */

public class SearchResultByTagActivity extends BaseMvpActivity implements SearchResultByTagContract, AdapterView.OnItemClickListener {
    /**
     * 展示数据的列表
     */
    private ListView searchResultList;
    /**
     * presenter
     */
    private SearchResultByTagPresenter presenter;
    /**
     * 从搜索家庭跳转过来的标志（因为从不同的界面跳转至此界面ListView需要加载不同的Adapter）
     */
    public static final int SEARCH_FAMILY_BY_TAG = 0;
    /**
     * 从搜索好友跳转过来的标志
     */
    public static final int SEARCH_FRIEND_BY_TAG = 1;
    /**
     * 从Intent中获取到的标志值
     */
    private int currentTag;
    /**
     * 从Intent中获取到的点击的标签名
     */
    private String tagName;
    /**
     * 家庭列表的适配器
     */
    private FamilyListByTagAdapter byTagAdapter;
    private List<NewFamilyList.FamilyListBean> list;

    @Override
    protected BasePresenter createPresenter() {
        presenter = new SearchResultByTagPresenter(this);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_search_result_by_tag);
        initData();
        initView();
        requestData();
    }

    /**
     * 网络请求数据
     */
    private void requestData() {
        //从不同的界面跳转过来请求不同的接口
        if (currentTag == SEARCH_FAMILY_BY_TAG) {
            presenter.getFamilyList(tagName);
        } else if (currentTag == SEARCH_FRIEND_BY_TAG) {

        }
    }

    /**
     * 初始化View
     */
    private void initView() {
        searchResultList = (ListView) findViewById(R.id.search_result_list);
        searchResultList.setOnItemClickListener(this);
    }

    /**
     * 获取从intent中传递过来的数据
     */
    private void initData() {
        currentTag = getIntent().getIntExtra("tag", SEARCH_FAMILY_BY_TAG);
        tagName = getIntent().getStringExtra("tagName");
        setTitleName(tagName);
    }

    /**
     * 界面跳转的静态方法
     *
     * @param context 上下文
     * @param tagName 标签名
     * @param tag     从哪个界面跳转的标识
     */
    public static void startActivity(Context context, String tagName, int tag) {
        Intent intent = new Intent(context, SearchResultByTagActivity.class);
        intent.putExtra("tagName", tagName);
        intent.putExtra("tag", tag);
        context.startActivity(intent);
    }

    /**
     * 显示家庭列表数据
     *
     * @param newFamilyList
     */
    @Override
    public void showFamilyListByTag(NewFamilyList newFamilyList) {
        if (newFamilyList != null && newFamilyList.getFamilyList().size() > 0) {
            list = newFamilyList.getFamilyList();
            showCenterView();
            if (byTagAdapter == null) {
                byTagAdapter = new FamilyListByTagAdapter(newFamilyList.getFamilyList());
                searchResultList.setAdapter(byTagAdapter);
            } else {
                byTagAdapter.myNotifyDataSetChanged(newFamilyList.getFamilyList());
            }
        } else {
            showNoDataView();
        }
    }

    @Override
    public void showError() {
        showErrorView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FamilyDetailActivity.startActivity(this, list.get(position).getId(), false, false);
    }
}

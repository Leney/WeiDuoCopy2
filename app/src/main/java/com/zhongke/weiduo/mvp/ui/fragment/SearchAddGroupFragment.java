package com.zhongke.weiduo.mvp.ui.fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpFragment;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SearchAddGroupContract;
import com.zhongke.weiduo.mvp.model.entity.NewNearbyGroup;
import com.zhongke.weiduo.mvp.presenter.SearchAddGroupPresenter;
import com.zhongke.weiduo.mvp.ui.activity.GroupIntroduceActivity;
import com.zhongke.weiduo.mvp.ui.adapter.NearbyGroupListAdapter;
import com.zhongke.weiduo.mvp.ui.widget.ListViewForScrollview;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/10/10.
 */

public class SearchAddGroupFragment extends BaseMvpFragment implements SearchAddGroupContract, View.OnClickListener, AdapterView.OnItemClickListener {

    private SearchAddGroupPresenter presenter;
    /**
     * 附近的群列表
     */
    private ListViewForScrollview lv;
    /**
     * 未搜索时默认的布局
     */
    private LinearLayout llDefaultGroup;
    /**
     * 输入框
     */
    private EditText etInputGroup;
    /**
     * 搜索后的返回按钮
     */
    private TextView tvReturn;

    /**
     * 搜索结果放置部分
     */
    private FrameLayout searchResultLay;

    /**
     * 搜索结果Fragment
     */
    private SearchFamilyAndGroupResultFragment resultFragment;
    private List<NewNearbyGroup.RecordsBean> list;

    @Override
    protected void initView(View baseView) {
        setCenterLay(R.layout.fragment_group_search);
        lv = (ListViewForScrollview) baseView.findViewById(R.id.add_group_listView);
        llDefaultGroup = (LinearLayout) baseView.findViewById(R.id.ll_default_group);
        etInputGroup = (EditText) baseView.findViewById(R.id.add_group_search_input);
        tvReturn = (TextView) baseView.findViewById(R.id.tv_group_return);
        searchResultLay = (FrameLayout) baseView.findViewById(R.id.group_search_result_lay);

        init();
        showLoadingView();
        requestData();
    }

    /**
     * 数据请求
     */
    private void requestData() {
        presenter.getNearbyGroup();
    }


    private void init() {
        etInputGroup.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    // 搜索
                    String searchName = etInputGroup.getText().toString();
                    resultFragment.startSearch(searchName);
                    showSearchView();
                    return true;
                }
                return false;
            }
        });
        tvReturn.setOnClickListener(this);
        lv.setOnItemClickListener(this);

        //群组的搜索结果Fragment
        resultFragment = SearchFamilyAndGroupResultFragment.newInstance(SearchFamilyAndGroupResultFragment.TYPE_GROUP);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.group_search_result_lay, resultFragment);
        transaction.commitAllowingStateLoss();

        showDefaultView();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_group_return) {
            showDefaultView();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GroupIntroduceActivity.startActivity(getActivity(), list.get(position).getId());
    }

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new SearchAddGroupPresenter(this);
    }

    /**
     * 显示默认的视图(没有搜索结果的界面)
     */
    private void showDefaultView() {
        llDefaultGroup.setVisibility(View.VISIBLE);
        tvReturn.setVisibility(View.GONE);
        searchResultLay.setVisibility(View.GONE);
        // 置空输入框数据
        etInputGroup.setText("");
    }

    /**
     * 显示搜索结果的视图(有搜索结果)
     */
    private void showSearchView() {
        llDefaultGroup.setVisibility(View.GONE);
        tvReturn.setVisibility(View.VISIBLE);
        searchResultLay.setVisibility(View.VISIBLE);
    }

    /**
     * 显示附近的群组
     *
     * @param newNearbyGroup
     */
    @Override
    public void showNearbyGroup(NewNearbyGroup newNearbyGroup) {
        if (newNearbyGroup != null && newNearbyGroup.getRecords().size() > 0) {
            list = newNearbyGroup.getRecords();
            showCenterView();
            NearbyGroupListAdapter adapter = new NearbyGroupListAdapter(newNearbyGroup.getRecords());
            lv.setAdapter(adapter);
        } else {
            showNoDataView();
        }
    }

    @Override
    public void showError() {
        showErrorView();
    }
}

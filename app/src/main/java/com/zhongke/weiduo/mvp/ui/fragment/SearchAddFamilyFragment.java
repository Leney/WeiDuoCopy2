package com.zhongke.weiduo.mvp.ui.fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpFragment;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SearchAddFamilyContract;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyTag;
import com.zhongke.weiduo.mvp.model.entity.NewNearbyFamilyBean;
import com.zhongke.weiduo.mvp.presenter.SearchAddFamilyPresenter;
import com.zhongke.weiduo.mvp.ui.activity.FamilyDetailActivity;
import com.zhongke.weiduo.mvp.ui.activity.SearchResultByTagActivity;
import com.zhongke.weiduo.mvp.ui.adapter.NearbyFamilyListAdapter;
import com.zhongke.weiduo.mvp.ui.widget.ListViewForScrollview;
import com.zhongke.weiduo.util.SizeUtils;
import com.zhongke.weiduo.util.ViewIdUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/10/10.
 */

public class SearchAddFamilyFragment extends BaseMvpFragment implements View.OnClickListener, AdapterView.OnItemClickListener, SearchAddFamilyContract {
    private SearchAddFamilyPresenter presenter;

    private ListViewForScrollview lv;
    private EditText etSearchFamily;
    /**
     * 默认的家庭界面
     */
    private LinearLayout llDefaultFamily;

    /**
     * 返回按钮
     */
    private TextView tvFamilyReturn;
    private LinearLayout llTag;
    private TextView tvChange;
    private int pageIndex = 1;
    private List<Integer> tagId = new ArrayList<>();
    private List<NewFamilyTag.TagFamilyListBean> list;
    private List<NewNearbyFamilyBean.RecordsBean> nearbyList;
    /**
     * 搜索结果放置部分
     */
    private FrameLayout searchResultLay;

    /**
     * 搜索结果Fragment
     */
    private SearchFamilyAndGroupResultFragment resultFragment;


    @Override
    protected BasePresenter createPresenter() {
        presenter = new SearchAddFamilyPresenter(this);
        return presenter;
    }


    @Override
    protected void initView(View baseView) {
        super.initView(baseView);
        setCenterLay(R.layout.fragment_family_search);
        tvChange = (TextView) baseView.findViewById(R.id.change);
        lv = (ListViewForScrollview) baseView.findViewById(R.id.add_family_listView);
        llTag = (LinearLayout) baseView.findViewById(R.id.add_family_classify_lay);
        etSearchFamily = (EditText) baseView.findViewById(R.id.add_family_search_input);
        llDefaultFamily = (LinearLayout) baseView.findViewById(R.id.ll_default_family);
        tvFamilyReturn = (TextView) baseView.findViewById(R.id.family_return);
        searchResultLay = (FrameLayout) baseView.findViewById(R.id.family_and_group_search_result_lay);
        presenter.getFamilyTag(pageIndex);
        presenter.getNearbyFamily();
        init();
        showCenterView();
    }


    private void init() {
        etSearchFamily.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    // 搜索
                    String searchName = etSearchFamily.getText().toString();
                    resultFragment.startSearch(searchName);
                    showSearchView();
                    return true;
                }
                return false;
            }
        });
        tvFamilyReturn.setOnClickListener(this);
        tvChange.setOnClickListener(this);
        lv.setOnItemClickListener(this);

        //家庭的搜索结果Fragment
        resultFragment = SearchFamilyAndGroupResultFragment.newInstance(SearchFamilyAndGroupResultFragment.TYPE_FAMILY);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.family_and_group_search_result_lay, resultFragment);
        transaction.commitAllowingStateLoss();

        showDefaultView();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.family_return) {
            showDefaultView();
        } else if (v.getId() == R.id.change) {
            pageIndex++;
            presenter.getFamilyTag(pageIndex);
        } else {
            for (int i = 0; i < tagId.size(); ++i) {
                if (v.getId() == tagId.get(i)) {
                    String name = list.get(i).getName();
                    SearchResultByTagActivity.startActivity(getActivity(), name, SearchResultByTagActivity.SEARCH_FAMILY_BY_TAG);
                    break;
                } else {
                    continue;
                }
            }

        }
    }

    /**
     * 动态创建标签布局
     */
    private void createTag(List<NewFamilyTag.TagFamilyListBean> list) {
        this.list = list;
        llTag.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams tvParams2 = new LinearLayout.LayoutParams(SizeUtils.dp2px(getActivity(), 44), SizeUtils.dp2px(getActivity(), 44));

        tvParams2.gravity = Gravity.CENTER;
        tvParams.gravity = Gravity.CENTER;

        tagId.clear();
        for (NewFamilyTag.TagFamilyListBean bean : list) {
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.setGravity(Gravity.CENTER);
            ll.setLayoutParams(params);
            ll.setTag(bean);
            int o = ViewIdUtils.getViewId();
            tagId.add(o);
            ll.setId(o);
            ll.setOnClickListener(this);

            ImageView iv = new ImageView(getActivity());
            iv.setLayoutParams(tvParams2);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getActivity()).load(bean.getCoverUrl()).into(iv);

            TextView tvName = new TextView(getActivity());
            tvName.setText(bean.getName());
            tvName.setLayoutParams(tvParams);
            ll.addView(iv);
            ll.addView(tvName);
            llTag.addView(ll);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FamilyDetailActivity.startActivity(getActivity(), nearbyList.get(position).getId(), false, false);
    }

    /**
     * 显示标签布局
     *
     * @param familyTag
     */
    @Override
    public void showFamilyTag(NewFamilyTag familyTag) {
        if (familyTag.getTagFamilyList() != null && familyTag.getTagFamilyList().size() > 0) {
            createTag(familyTag.getTagFamilyList());
        } else {
            pageIndex = 0;
        }
    }


    /**
     * 显示附近的家庭列表
     *
     * @param newNearbyFamilyBean
     */
    @Override
    public void showNearbyFamilyList(NewNearbyFamilyBean newNearbyFamilyBean) {
        if (newNearbyFamilyBean != null) {
            nearbyList = newNearbyFamilyBean.getRecords();
            NearbyFamilyListAdapter adapter = new NearbyFamilyListAdapter(newNearbyFamilyBean.getRecords());
            lv.setAdapter(adapter);
        }
    }

    /**
     * 显示默认的视图(没有搜索结果的界面)
     */
    private void showDefaultView() {
        llDefaultFamily.setVisibility(View.VISIBLE);
        tvFamilyReturn.setVisibility(View.GONE);
        searchResultLay.setVisibility(View.GONE);
        // 置空输入框数据
        etSearchFamily.setText("");
    }

    /**
     * 显示搜索结果的视图(有搜索结果)
     */
    private void showSearchView() {
        llDefaultFamily.setVisibility(View.GONE);
        tvFamilyReturn.setVisibility(View.VISIBLE);
        searchResultLay.setVisibility(View.VISIBLE);
    }

}

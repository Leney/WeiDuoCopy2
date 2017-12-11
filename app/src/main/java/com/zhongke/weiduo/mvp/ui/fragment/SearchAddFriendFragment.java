package com.zhongke.weiduo.mvp.ui.fragment;

import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
import com.zhongke.weiduo.mvp.contract.SearchAddFriendContract;
import com.zhongke.weiduo.mvp.model.entity.NewNearbyUser;
import com.zhongke.weiduo.mvp.model.entity.NewRecommendUser;
import com.zhongke.weiduo.mvp.presenter.SearchAddFriendPresenter;
import com.zhongke.weiduo.mvp.ui.activity.PersonalDetailsActivity;
import com.zhongke.weiduo.mvp.ui.adapter.NearbyUserListAdapter;
import com.zhongke.weiduo.mvp.ui.widget.ListViewForScrollview;
import com.zhongke.weiduo.util.SizeUtils;
import com.zhongke.weiduo.util.ViewIdUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/10/10.
 * 搜索添加好友Fragment
 */

public class SearchAddFriendFragment extends BaseMvpFragment implements SearchAddFriendContract, View.OnClickListener, AdapterView.OnItemClickListener {
    private SearchAddFriendPresenter presenter;

    private ListViewForScrollview lv;
    private EditText etSearchFriend;
    /**
     * 默认的好友界面
     */
    private LinearLayout llDefaultFriend;
    /**
     * 搜索返回按钮
     */
    private TextView tvFriendReturn;
    private LinearLayout llTag;
    private int pageIndex = 1;
    private List<Integer> tagId = new ArrayList<>();
    /**
     * 搜索结果放置部分
     */
    private FrameLayout searchResultLay;
    private TextView tvChange;
    /**
     * 搜索结果Fragment
     */
    private SearchFriendResultFragment resultFragment;
    private List<NewRecommendUser.RecordsBean> list;
    private List<NewNearbyUser.RecordsBean> nearbyList;

    @Override
    protected void initView(View view) {
        setCenterLay(R.layout.fragment_friend_search);
        tvChange = (TextView) view.findViewById(R.id.change);
        lv = (ListViewForScrollview) view.findViewById(R.id.add_friend_listView);
        etSearchFriend = (EditText) view.findViewById(R.id.add_friend_search_input);
        llDefaultFriend = (LinearLayout) view.findViewById(R.id.ll_default_friend);
        llTag = (LinearLayout) view.findViewById(R.id.add_friend_classify_lay);
        tvFriendReturn = (TextView) view.findViewById(R.id.friend_return);
        searchResultLay = (FrameLayout) view.findViewById(R.id.friend_search_result_lay);
        init();
        showLoadingView();
        requestData();
    }

    private void requestData() {
        presenter.getNearbyUserList();
        presenter.getRecommendUserList(pageIndex);
    }


    private void init() {
        etSearchFriend.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.i("llj", "onKey(),keyCode------>>>" + event.getKeyCode());
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    // 搜索
                    Log.i("llj", "开始搜索！！");
                    String searchName = etSearchFriend.getText().toString();
                    resultFragment.startSearch(searchName);
                    showSearchView();
                    return true;
                }
                return false;
            }
        });
        tvChange.setOnClickListener(this);
        tvFriendReturn.setOnClickListener(this);
        lv.setOnItemClickListener(this);

        resultFragment = new SearchFriendResultFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.friend_search_result_lay, resultFragment);
        transaction.commitAllowingStateLoss();

        showDefaultView();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.friend_return) {
            showDefaultView();
        } else if (v.getId() == R.id.change) {
            pageIndex++;
            presenter.getRecommendUserList(pageIndex);
        } else {
            for (int i = 0; i < tagId.size(); ++i) {
                if (v.getId() == tagId.get(i)) {
                    int id = list.get(i).getId();
                    PersonalDetailsActivity.startActivity(getActivity(), id + "", list.get(i).getFullName() + "", false, false);
                    break;
                }
            }

        }
    }

    /**
     * 动态创建标签布局
     */
    private void createTag(List<NewRecommendUser.RecordsBean> list) {
        this.list = list;
        llTag.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams tvParams2 = new LinearLayout.LayoutParams(SizeUtils.dp2px(getActivity(), 44), SizeUtils.dp2px(getActivity(), 44));

        tvParams2.gravity = Gravity.CENTER;
        tvParams.gravity = Gravity.CENTER;

        tagId.clear();
        for (NewRecommendUser.RecordsBean bean : list) {
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
            Glide.with(getActivity()).load(bean.getHeadImageUrl()).into(iv);

            TextView tvName = new TextView(getActivity());
            if (bean.getFullName() == null || (bean.getFullName() + "").equals("null")) {
                tvName.setText(bean.getUserName() + "");
            } else {
                tvName.setText(bean.getFullName() + "");
            }
            tvName.setLayoutParams(tvParams);
            ll.addView(iv);
            ll.addView(tvName);
            llTag.addView(ll);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PersonalDetailsActivity.startActivity(getActivity(), nearbyList.get(position).getId() + "", nearbyList.get(position).getFullName() + "", false, false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new SearchAddFriendPresenter(this);
    }


    /**
     * 显示默认的视图(没有搜索结果的界面)
     */
    private void showDefaultView() {
        llDefaultFriend.setVisibility(View.VISIBLE);
        tvFriendReturn.setVisibility(View.GONE);
        searchResultLay.setVisibility(View.GONE);
        // 置空输入框数据
        etSearchFriend.setText("");
    }

    /**
     * 显示搜索结果的视图(有搜索结果)
     */
    private void showSearchView() {
        llDefaultFriend.setVisibility(View.GONE);
        tvFriendReturn.setVisibility(View.VISIBLE);
        searchResultLay.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNearbyUserList(NewNearbyUser newNearbyUser) {
        nearbyList = newNearbyUser.getRecords();
        if (newNearbyUser != null && newNearbyUser.getRecords().size() > 0) {
            showCenterView();
            NearbyUserListAdapter adapter = new NearbyUserListAdapter(newNearbyUser.getRecords());
            lv.setAdapter(adapter);
        } else {
            showNoDataView();
        }
    }

    @Override
    public void showErrorViews() {
        showErrorView();
    }

    /**
     * 显示推荐好友
     *
     * @param newRecommendUser
     */
    @Override
    public void showRecommendUser(NewRecommendUser newRecommendUser) {
        if (newRecommendUser.getRecords() != null && newRecommendUser.getRecords().size() > 0) {
            createTag(newRecommendUser.getRecords());
        } else {
            pageIndex = 0;
        }
    }
}

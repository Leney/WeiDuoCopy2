package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SearchFriendContract;
import com.zhongke.weiduo.mvp.model.entity.TabItem;
import com.zhongke.weiduo.mvp.presenter.SearchFriendPresenter;
import com.zhongke.weiduo.mvp.ui.fragment.SearchAddFamilyFragment;
import com.zhongke.weiduo.mvp.ui.fragment.SearchAddFriendFragment;
import com.zhongke.weiduo.mvp.ui.fragment.SearchAddGroupFragment;
import com.zhongke.weiduo.mvp.ui.widget.layout.TabItemChangeLayout;
import com.zhongke.weiduo.util.ViewIdUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索好友群组家庭界面
 */
public class SearchFriendsActivity extends BaseMvpActivity implements ViewPager.OnPageChangeListener, SearchFriendContract {
    private TabItemChangeLayout tabItemChangeLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private SearchFriendPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_search_friends);
        setTitleName("添加朋友");
        showCenterView();
        initView();
    }

    private void initView() {
        fragmentList.add(new SearchAddFamilyFragment());
        fragmentList.add(new SearchAddFriendFragment());
        fragmentList.add(new SearchAddGroupFragment());

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(2);
        tabItemChangeLayout = (TabItemChangeLayout) findViewById(R.id.tab_item_layout);
        List<TabItem> list = new ArrayList<>();
        TabItem ti1 = new TabItem();
        ti1.setViewId(ViewIdUtils.getViewId());
        ti1.setViewName("家庭");
        TabItem ti2 = new TabItem();
        ti2.setViewId(ViewIdUtils.getViewId());
        ti2.setViewName("好友");
        TabItem ti3 = new TabItem();
        ti3.setViewId(ViewIdUtils.getViewId());
        ti3.setViewName("群组");
        list.add(ti1);
        list.add(ti2);
        list.add(ti3);
        tabItemChangeLayout.addItemTab(list);
        tabItemChangeLayout.setChangeListener(new TabItemChangeLayout.TabChangeListener() {
            @Override
            public void changeTab(int currentTab) {
                for (int i = 0; i < list.size(); i++) {
                    if (currentTab == list.get(i).getViewId()) {
                        viewPager.setCurrentItem(i);
                    }
                }
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        presenter = new SearchFriendPresenter(this, mDataManager);
        return presenter;
    }

    public static void startActivity(Context context/*, int stepId, String stepName*/) {
        Intent intent = new Intent(context, SearchFriendsActivity.class);
//        intent.putExtra("stepId", stepId);
//        intent.putExtra("stepName", stepName);
        context.startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabItemChangeLayout.setCurrentSelect(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}

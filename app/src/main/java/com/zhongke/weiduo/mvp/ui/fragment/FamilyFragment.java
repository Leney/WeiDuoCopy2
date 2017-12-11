package com.zhongke.weiduo.mvp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.control.RecentChatListManager;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;
import com.zhongke.weiduo.mvp.ui.activity.ActivityCreateGroupChat;
import com.zhongke.weiduo.mvp.ui.activity.FamilyListActivity;
import com.zhongke.weiduo.mvp.ui.activity.GroupListActivity;
import com.zhongke.weiduo.mvp.ui.activity.HardwareManagementActivity;
import com.zhongke.weiduo.mvp.ui.activity.MipcaActivityCapture;
import com.zhongke.weiduo.mvp.ui.activity.NewFriendActivity;
import com.zhongke.weiduo.mvp.ui.activity.PersonalDetailsActivity;
import com.zhongke.weiduo.mvp.ui.activity.SearchFriendsActivity;
import com.zhongke.weiduo.mvp.ui.adapter.FamilyAdapter;
import com.zhongke.weiduo.mvp.ui.widget.expandablelistview.CustomExpandableListView;
import com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout.ScrollChildSwipeRefreshLayout;
import com.zhongke.weiduo.mvp.ui.widget.view.ContactPopWin;
import com.zhongke.weiduo.util.ColorUtils;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by ${xingen} on 2017/6/23.
 * 联系人Fragment
 */

public class FamilyFragment extends Fragment implements View.OnClickListener
        , ContactPopWin.OnPopWinClickListeners
        , ContactsManager.OnContactsInitFinishListener
        , ContactsManager.OnContactsChangedListener
        , SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = FamilyFragment.class.getSimpleName();

    public static FamilyFragment newInstance() {
        return new FamilyFragment();
    }

    private View rootView;
    private CustomExpandableListView expandableListView;

    private TextView newFriendBtn;

    private TextView addFamilyBtn;

    private TextView addGroupBtn;
    private ImageView ivMore;
    private ScrollView scrollView;
    /**
     * 是否有新的朋友添加请求的标识
     */
    private TextView newFriendPoint;

    private FamilyAdapter mAdapter;
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;
    /**
     * 组名称集合
     */
    private List<String> groupList;
    /**
     * 子条目集合
     */
    private List<List<ContactsListBean>> childList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupList = new ArrayList<>();
        groupList.add("设备");
        groupList.add("好友");

        childList = new ArrayList<>();

        childList.add(new ArrayList<>());
        childList.add(new ArrayList<>());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_family, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        this.newFriendPoint = (TextView) rootView.findViewById(R.id.family_expandablelistview_child_message_iv);
        this.newFriendBtn = (TextView) rootView.findViewById(R.id.family_fragment_new_friend);
        this.addFamilyBtn = (TextView) rootView.findViewById(R.id.family_fragment_add_family);
        this.addGroupBtn = (TextView) rootView.findViewById(R.id.family_fragment_add_group);
        ivMore = (ImageView) rootView.findViewById(R.id.iv_more);
        this.newFriendBtn.setOnClickListener(this);
        this.addFamilyBtn.setOnClickListener(this);
        this.addGroupBtn.setOnClickListener(this);
        ivMore.setOnClickListener(this);

        this.expandableListView = (CustomExpandableListView) rootView.findViewById(R.id.family_expandablelistview);
        this.expandableListView.setChildDivider(new ColorDrawable(ColorUtils.stringToColor("#eeeeee")));
        this.expandableListView.setGroupIndicator(null);
        scrollView = (ScrollView) rootView.findViewById(R.id.scroll_view);

        mAdapter = new FamilyAdapter(groupList, childList);

        swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) rootView.findViewById(R.id.movable_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
        swipeRefreshLayout.setScrollUpChild(scrollView);
        swipeRefreshLayout.setOnRefreshListener(this);
        //自动加载下拉提示框
        swipeRefreshLayout.setLoadingIndicator(true);
        //以上代码不响应onRefresh()，需要手动响应onReFresh()。
        this.onRefresh();
        releaseState();
        //拦截点击是事件
        this.expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;//返回true，进行拦截，
            }
        });
        this.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Log.i("llj", "i--->" + i + "----i1------>>>" + i1);
                switch (i) {
                    case 0:
                        // 设备item
                        // 跳转到设备管理
                        ContactsListBean device = childList.get(0).get(i1);
                        String deviceCode=device.getNickName().substring(device.getNickName().indexOf("[")+1,device.getNickName().indexOf("]"));
                        HardwareManagementActivity.startActivity(getActivity(), device.getId(),deviceCode);
                        break;
                    case 1:
                        // 好友item
                        // 跳转到好友个人详情
//                        ContactsListBean contact = friendsDetail.contactList.get(i1);
                        ContactsListBean contact = childList.get(1).get(i1);
                        PersonalDetailsActivity.startActivity(getActivity(), contact.id + "", contact.nickName, true, false);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        // 设置联系人初始化完成的监听
        ContactsManager.getInstance().registerContactsInitFinishedListener(this);
        // 设置数据联系人数据发生改变的监听
        ContactsManager.getInstance().registerContactsChangedListener(this);
        Log.i("llj", "childList.size()---->>" + childList.size());
        getFriendList();
    }

    /**
     * 释放状态
     */
    private void releaseState() {
        swipeRefreshLayout.setLoadingIndicator(false);
    }

    /**
     * 从本地数据库中获取好友联系人信息
     */
    private void getFriendList() {
        // 获取设备列表信息
        ContactsManager.getInstance().queryContactListByType(ContactsListBean.TYPE_DEVICE, new Action1<List<ContactsListBean>>() {
            @Override
            public void call(List<ContactsListBean> listBeen) {
                childList.get(0).clear();
                childList.get(0).addAll(listBeen);
            }
        });
        // 获取好友列表信息
        ContactsManager.getInstance().queryContactListByType(ContactsListBean.TYPE_FRIEND_PERSON, new Action1<List<ContactsListBean>>() {
            @Override
            public void call(List<ContactsListBean> list) {
                childList.get(1).clear();
                childList.get(1).addAll(list);
                if (expandableListView.getAdapter() == null) {
                    expandableListView.setAdapter(mAdapter);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
                for (int i = 0; i < 2; i++) {
                    expandableListView.expandGroup(i);
                }
            }
        });

        // 获取新添加朋友信息
        boolean isHaveNewFriendMsg = RecentChatListManager.getInstance().isHaveNewFriendAdd();
        newFriendPoint.setVisibility(isHaveNewFriendMsg ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.family_fragment_new_friend:
                // 新朋友
                startActivity(new Intent(getActivity(), NewFriendActivity.class));
                break;
            case R.id.family_fragment_add_family:
                // 家庭
                FamilyListActivity.startActivity(getActivity());
                break;
            case R.id.family_fragment_add_group:
                // 群组
                GroupListActivity.startActivity(getActivity());
                break;
            case R.id.iv_more:
                ContactPopWin popWin = new ContactPopWin(getActivity());
                popWin.showPopupWindow(ivMore);
                popWin.setListeners(this);
                break;

            default:

                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 注销联系人初始化完成的监听
        ContactsManager.getInstance().unregisterContactsInitFinishedListener(this);
    }

    @Override
    public void clickPopItem(int i) {
        switch (i) {
            //添加朋友
            case ContactPopWin.ADD_FRIENDS:
                SearchFriendsActivity.startActivity(getActivity());
                break;

            //添加设备
            case ContactPopWin.ADD_EQUIPMENT:
                // 二维码扫描
                MipcaActivityCapture.openActivity(getActivity());
                break;
            //创建群组
            case ContactPopWin.CREATE_GROUP:
                ActivityCreateGroupChat.startActivity(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    public void onContactsInitFinishListener() {
        // 联系人信息初始化完成
        Log.i("llj", "初始化完成监听！！！");
        getFriendList();
    }

    @Override
    public void onContactsChanged(int type, int id) {
        if (type == ContactsListBean.TYPE_FRIEND_PERSON) {
            // 重新获取好友列表
            Log.i("llj", "联系人数据改变监听！！！");
            getFriendList();
        }
    }

    @Override
    public void onRefresh() {
        releaseState();
        ContactsManager.getInstance().getContactsListFromNetwork(false);
    }
}

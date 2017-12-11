package com.zhongke.weiduo.mvp.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.TimeUtils;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.easyPermission.PermissionManager;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.library.retrofit.RetrofitClient;
import com.zhongke.weiduo.library.rxjava.ObservableUtils;
import com.zhongke.weiduo.library.rxjava.SubscribeUtils;
import com.zhongke.weiduo.mvp.control.RecentChatListManager;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;
import com.zhongke.weiduo.mvp.model.entity.LoginResult;
import com.zhongke.weiduo.mvp.model.entity.TaskListBean;
import com.zhongke.weiduo.mvp.model.event.ModifyImage;
import com.zhongke.weiduo.mvp.model.event.ModifyNickName;
import com.zhongke.weiduo.mvp.ui.activity.ActivityCommentActivity2;
import com.zhongke.weiduo.mvp.ui.activity.ActivityDetailActivity;
import com.zhongke.weiduo.mvp.ui.activity.AnswerActivity;
import com.zhongke.weiduo.mvp.ui.activity.MineActivity;
import com.zhongke.weiduo.mvp.ui.activity.MipcaActivityCapture;
import com.zhongke.weiduo.mvp.ui.activity.RecentMessageActivity;
import com.zhongke.weiduo.mvp.ui.adapter.MicroRudderTaskListAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.MyTaskListAdapter;
import com.zhongke.weiduo.mvp.ui.widget.ListViewForScrollview;
import com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout.ScrollChildSwipeRefreshLayout;
import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.GlideCircleTransform;
import com.zhongke.weiduo.util.TextViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by ${xinGen} on 2017/6/20.
 * 首页Fragment
 */

public class MainFragment extends Fragment implements View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        MicroRudderTaskListAdapter.OnItemClickChangeListener,
        RecentChatListManager.onRecentChatListChangedListener, EasyPermissions.PermissionCallbacks {
    private ImageView myTask;
    private ImageView babyTask;
    private ImageView mineImg;
    private ImageView qdScanImg;
    private ListView listView;
    private MicroRudderTaskListAdapter childAdapter;
    private MyTaskListAdapter myTaskListAdapter;

    private View rootView;
    private ScrollView scrollview;
    private TextView chridren_task_tv, my_task_tv, my_task_togogle, chcildren_task_toggle;
    private LinearLayout listview_layout;
    private RelativeLayout mReMessage;
    private CompositeSubscription compositeSubscription;
    private LinearLayout myTask_Layout_Manager;
    /**
     * 最新消息文本
     */
    private TextView newestMessage;
    /**
     * 最新消息时间
     */
    private TextView newestTime;
    /**
     * 有未读消息的指示点
     */
    private TextView unreadMsgPoint;
    private final String TEXT_TIP1 = "展开";
    private final String TEXT_TIP2 = "收起";
    private RetrofitClient retrofitClient;
    public static final String TAG = MainFragment.class.getSimpleName();
    private String headImageUrl;
    private String nickName;
    private String userName;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.compositeSubscription = new CompositeSubscription();
        this.retrofitClient = RetrofitClient.getInstance();

        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_microrudder, container, false);
        initView();
        return rootView;
    }

    private ListViewForScrollview myTask_listView;
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    /**
     * 初始化
     */
    public void initView() {
        this.mineImg = (ImageView) rootView.findViewById(R.id.image_photo);
        this.myTask_Layout_Manager = (LinearLayout) rootView.findViewById(R.id.microrudder_my_task_listview_layout);
        this.myTask_listView = (ListViewForScrollview) rootView.findViewById(R.id.microrudder_my_task_listview);

        this.mineImg.setOnClickListener(this);
        this.scrollview = (ScrollView) rootView.findViewById(R.id.microrudder_task_srcollView);
        this.listView = (ListView) rootView.findViewById(R.id.microrudder_task_listview);
        this.chridren_task_tv = (TextView) rootView.findViewById(R.id.microrudder_task_message_tv);
        this.my_task_tv = (TextView) rootView.findViewById(R.id.microrudder_mytask_message_tv);
        this.chcildren_task_toggle = (TextView) rootView.findViewById(R.id.microrudder_children_task_toggle);
        this.my_task_togogle = (TextView) rootView.findViewById(R.id.microrudder_my_task_toggle);
        this.listview_layout = (LinearLayout) rootView.findViewById(R.id.microrudder_task_listview_layout);
        this.qdScanImg = (ImageView) rootView.findViewById(R.id.microrudder_qd_scan_btn);
        this.qdScanImg.setOnClickListener(this);

        this.newestMessage = (TextView) rootView.findViewById(R.id.microrudder_message_newest);
        this.newestTime = (TextView) rootView.findViewById(R.id.microrudder_messag_date_tv);
        this.unreadMsgPoint = (TextView) rootView.findViewById(R.id.microrudder_unread_message_point);

        myTask = (ImageView) rootView.findViewById(R.id.microrudder_mytask_large_iv);
        myTask.setOnClickListener(this);
        babyTask = (ImageView) rootView.findViewById(R.id.microrudder_task_large_iv);
        babyTask.setOnClickListener(this);

        rootView.findViewById(R.id.jia_zhang_renwu).setOnClickListener(this);
        mReMessage = (RelativeLayout) rootView.findViewById(R.id.microrudder_message_total_layout);

        this.childAdapter = new MicroRudderTaskListAdapter(getActivity());
        this.childAdapter.setOnItemClickChangeListener(this);
        this.listView.setAdapter(childAdapter);

        this.myTaskListAdapter = new MyTaskListAdapter(getActivity());
        this.myTaskListAdapter.setOnItemClickChangeListener(new MyTaskListAdapter.OnItemClickChangeListener() {
            @Override
            public void itemClick(int position) {
              /*  TaskListBean.MyTaskBean taskListBean = myTaskListAdapter.getTaskList().get(position);
                LogUtil.e("ActionId-" + taskListBean.getActionId());
                LogUtil.e("FlowId-" + taskListBean.getActionId());
                ActivityCommentActivity2.startActivity(getActivity(),taskListBean);*/
            }
        });
        this.myTask_listView.setAdapter(myTaskListAdapter);
        // remeberViewPostion();

        //设置TextView四周的图片
        TextViewUtils.setCompoundDrawables(this.chcildren_task_toggle, 0, 0, R.drawable.microrudder_next_tip, 0);
        TextViewUtils.setCompoundDrawables(this.my_task_togogle, 0, 0, R.drawable.microrudder_next_tip, 0);
        //添加监听器
        this.chcildren_task_toggle.setOnClickListener(this);
        this.my_task_togogle.setOnClickListener(this);
        mReMessage.setOnClickListener(this);
        rootView.findViewById(R.id.image_photo).setOnClickListener(this);
        this.unreadMsgPoint.setVisibility(RecentChatListManager.getInstance().getUnreadMsgNum() > 0 ? View.VISIBLE : View.GONE);
        RecentChatListManager.getInstance().getTotalNewestChatListBean(new Action1<ChatListBean>() {
            @Override
            public void call(ChatListBean listBean) {
                Log.i("llj", "获取到最近联系人消息对象 listBean==null" + (listBean == null));
                if (listBean == null) {

                    return;
                }
                doNewChatBeanList(listBean);
            }
        });
        RecentChatListManager.getInstance().registerOnRecentListChangedListener(this);

        this.swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) rootView.findViewById(R.id.miccrorudder_swipe_refresh_layout);
        this.swipeRefreshLayout.setRefreshColor();
        this.swipeRefreshLayout.setOnRefreshListener(this);
        this.swipeRefreshLayout.setLoadingIndicator(true);
        this.onRefresh();
        this.swipeRefreshLayout.setScrollUpChild(scrollview);
        queryHeadUrl();
    }

    private int view_x = 0;
    private int view_y = 0;

    /**
     * 记住 控件的位置
     */
    public void rememberViewPosition() {
        if (this.scrollview != null) {
            this.view_x = this.scrollview.getScrollX();
            this.view_y = this.scrollview.getScrollY();
            Log.i(TAG, " remeber position" + view_x + " " + view_y);
        }
    }

    /**
     * 恢复滚动位置
     */
    public void recoverViewPosition() {
        if (this.scrollview != null) {
            Log.i(TAG, " scroll position" + view_x + " " + view_y);
            this.scrollview.smoothScrollTo(view_x, view_y);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume ");
        //滚动到指定位置
        recoverViewPosition();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause ");
        rememberViewPosition();
    }

    private final String[] cameraPermission = {Manifest.permission.CAMERA};
    private final int cameraRequestCode = 110;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //孩子的任务
            case R.id.microrudder_children_task_toggle:
                rememberViewPosition();
                if (TEXT_TIP1.equals(chcildren_task_toggle.getText().toString())) {
                    chcildren_task_toggle.setText(TEXT_TIP2);
                    TextViewUtils.setCompoundDrawables(this.chcildren_task_toggle, 0, 0, R.drawable.microrudder_up_tip, 0);
                    this.listview_layout.setVisibility(View.VISIBLE);
                } else {
                    chcildren_task_toggle.setText(TEXT_TIP1);
                    TextViewUtils.setCompoundDrawables(this.chcildren_task_toggle, 0, 0, R.drawable.microrudder_next_tip, 0);
                    this.listview_layout.setVisibility(View.GONE);
                }
                recoverViewPosition();
                break;
            //我的任务展开与收放
            case R.id.microrudder_my_task_toggle:
                rememberViewPosition();
                if (TEXT_TIP1.equals(my_task_togogle.getText().toString())) {
                    my_task_togogle.setText(TEXT_TIP2);
                    TextViewUtils.setCompoundDrawables(this.my_task_togogle, 0, 0, R.drawable.microrudder_up_tip, 0);
                    this.myTask_listView.setVisibility(View.VISIBLE);
                } else {
                    my_task_togogle.setText(TEXT_TIP1);
                    TextViewUtils.setCompoundDrawables(this.my_task_togogle, 0, 0, R.drawable.microrudder_next_tip, 0);
                    this.myTask_listView.setVisibility(View.GONE);
                }
                recoverViewPosition();
                break;
            //消息
            case R.id.microrudder_message_total_layout:
                unreadMsgPoint.setVisibility(View.GONE);
                getActivity().startActivity(new Intent(getActivity(), RecentMessageActivity.class));
                break;
            //家长任务
            case R.id.microrudder_mytask_large_iv:
//                GrantRewardActivity.startActivityForResult(getActivity());
                break;
            //家长任务文字
            case R.id.jia_zhang_renwu:

                break;
            //孩子任务
            case R.id.microrudder_task_large_iv:
//                ActivityAwardActivity.startActivityForResult(getActivity());
                break;
            case R.id.image_photo:
                MineActivity.startActivity(getActivity(), headImageUrl, nickName, userName);
                break;
            case R.id.microrudder_qd_scan_btn:
//                if (PermissionManager.checkPermission(this, cameraPermission)) {
//                    // 二维码扫描
//                    MipcaActivityCapture.openActivity(getActivity());
//                } else {
//                    PermissionManager.requestPermission(this, "扫描二维码需要，开启相机权限", cameraRequestCode, cameraPermission);
//                }
                AnswerActivity.startActivity(getActivity(),109);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlerPermissionResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PermissionManager.DEFAULT_SETTINGS_REQ_CODE:
                if (PermissionManager.checkPermission(this, cameraPermission)) {
                    MipcaActivityCapture.openActivity(getActivity());
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MipcaActivityCapture.QD_SCAN_RESULT_CODE) {
            // 二维码扫描返回
            Toast.makeText(getActivity(), "二维码扫描结果:" + data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 开启其他页面
     *
     * @param mClass
     * @param bundle
     */
    public void openActivity(Class<?> mClass, Bundle bundle) {
        Intent intent = new Intent(getActivity(), mClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        getActivity().startActivity(intent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint  " + isVisibleToUser);
        if (isVisibleToUser) {
            recoverViewPosition();
        } else {
            rememberViewPosition();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.compositeSubscription.clear();
        // 注销监听列表数据发生改变的监听
        RecentChatListManager.getInstance().unregisterOnRecentListChangedListener(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void itemClick(int position) {
        TaskListBean.ChildTaskBean taskListBean = childAdapter.getTaskList().get(position);
        int type = position;
        switch (type) {
            case 0://可监控，直播
                // 跳转到聊天界面
                // "ACTION_" 因为是临时的群聊，所以在活动id前面加入一个前缀标识一下
                //SessionActivity2.startActivity(getActivity(), SessionActivity2.SESSION_TYPE_VIDEO, "ACTION_" + taskListBean.getActionId(), taskListBean.getActionName());

                //暂时进入评价界面
                ActivityCommentActivity2.startActivity(getActivity(),taskListBean,1);
                break;
            case 1://不可监控
                //评论界面
                //ActivityCommentActivity2.startActivity(getActivity(), 1);
                //暂时进入评价界面
                ActivityCommentActivity2.startActivity(getActivity(),taskListBean,1);
                break;
            case 2://纯文本
                ActivityDetailActivity.startActivity(getActivity(), 0, "");
                break;
            default:
                break;
        }
    }

    @Override
    public void onChange(int position) {
        // 最近列表数据发生改变
        Log.i("llj", "最近列表数据发送改变！！！");
        if (position < 0) {
            // 获取最新的一条ChatListBean
            RecentChatListManager.getInstance().getTotalNewestChatListBean(new Action1<ChatListBean>() {
                @Override
                public void call(ChatListBean listBean) {
                    doNewChatBeanList(listBean);
                }
            });
        } else {
            ChatListBean listBean = RecentChatListManager.getInstance().getChatListBean(position);
            doNewChatBeanList(listBean);
        }
    }

    /**
     * 处理最新的最近消息
     */
    private void doNewChatBeanList(ChatListBean listBean) {
        if (listBean == null) {
            newestMessage.setText("暂无消息");
            newestTime.setText("");
            // 根据未读消息数量判断是否要显示红点
            unreadMsgPoint.setVisibility(RecentChatListManager.getInstance().getUnreadMsgNum() > 0 ? View.VISIBLE : View.GONE);
            return;
        }
//        if (listBean.type == ChatListBean.CHAT_TYPE_ADD_FRIEND) {
//            newestMessage.setText(listBean.name + "请求添加好友");
//        } else {
//            if (listBean.newestMessageIsReceiveType) {
//                newestMessage.setText(listBean.name + "：" + listBean.newestMsg);
//            } else {
//                newestMessage.setText("我：" + listBean.newestMsg);
//            }
//        }
//        newestTime.setText(TimeUtils.getMsgFormatTime(listBean.newestMessageTime));

        String message;
        if (listBean.type == ChatListBean.CHAT_TYPE_ADD_FRIEND) {
//            if (listBean.addState == ChatListBean.NEW_ADD_FRIEND_STATE_UN_DO) {
            message = listBean.name + "请求添加好友";
//            } else if (listBean.addState == ChatListBean.NEW_ADD_FRIEND_STATE_PASSED) {
//                message = listBean.name + "的好友申请 已同意";
//            } else if (listBean.addState == ChatListBean.NEW_ADD_FRIEND_STATE_ALREADY_SEND) {
//                message = "已向" + listBean.name + "发送好友申请";
//            } else {
//                message = listBean.name + "的好友申请 已拒绝";
//            }
        } else {
            if (listBean.newestMessageIsReceiveType) {
                message = listBean.name + "：" + listBean.newestMsg;
            } else {
                message = "我：" + listBean.newestMsg;
            }
        }
        newestMessage.setText(message);
        newestTime.setText(TimeUtils.getMsgFormatTime(listBean.newestMessageTime));
        // 根据未读消息数量判断是否要显示红点
        unreadMsgPoint.setVisibility(RecentChatListManager.getInstance().getUnreadMsgNum() > 0 ? View.VISIBLE : View.GONE);
    }

    private void loadData(TaskListBean taskListBean) {
        //设置显示的文字
        int[] colors = {ColorUtils.stringToColor("#999999"), ColorUtils.stringToColor("#ff3f3f"), ColorUtils.stringToColor("#999999")};
        String[] texts = {"今天有", String.valueOf(taskListBean.getMyTask().size()), "个任务待完成"};
        SpannableStringBuilder spannableStringBuilder = TextViewUtils.setColorPan(colors, texts);
        this.my_task_tv.setText(spannableStringBuilder);
        String[] texts2 = {"今天有", String.valueOf(taskListBean.getChildTask().size()), "个任务待完成"};
        SpannableStringBuilder spannableStringBuilder2 = TextViewUtils.setColorPan(colors, texts2);
        this.chridren_task_tv.setText(spannableStringBuilder2);

        chcildren_task_toggle.setText(TEXT_TIP2);
        TextViewUtils.setCompoundDrawables(this.chcildren_task_toggle, 0, 0, R.drawable.microrudder_up_tip, 0);
        this.listview_layout.setVisibility(View.VISIBLE);
        my_task_togogle.setText(TEXT_TIP2);
        TextViewUtils.setCompoundDrawables(this.my_task_togogle, 0, 0, R.drawable.microrudder_up_tip, 0);
        this.myTask_listView.setVisibility(View.VISIBLE);
        myTaskListAdapter.changData(taskListBean.getMyTask());
        childAdapter.changData(taskListBean.getChildTask());
    }

    /**
     * 执行查询活动列表的操作
     */
    private void executeQueryActionListTask() {
        Subscription subscription = retrofitClient.getTaskList(new ResponseResultListener<TaskListBean>() {
            @Override
            public void success(TaskListBean taskListBean) {
                loadData(taskListBean);
                swipeRefreshLayout.setLoadingIndicator(false);
            }

            @Override
            public void failure(CommonException e) {
                swipeRefreshLayout.setLoadingIndicator(false);
                UIUtils.showToast("发生异常" + e.getErrorMsg());
            }
        });
        this.compositeSubscription.add(subscription);
    }

    @Override
    public void onRefresh() {
        executeQueryActionListTask();
    }

    public void queryHeadUrl() {
        SubscribeUtils.toSubscribe(ObservableUtils.createQueryAccountMsg(), new Subscriber<LoginResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LoginResult loginResult) {
                if (loginResult.getSysUser() != null) {
                    //LogUtil.e("loginResult---!=null"+loginResult.getSysUser().toString());
                    headImageUrl = loginResult.getSysUser().getHeadImageUrl();
                    nickName = loginResult.getSysUser().getNickName();
                    userName = loginResult.getSysUser().getUserName();
                    //LogUtil.e("headImageUrl--",headImageUrl);
                    LogUtil.e("token--", loginResult.getToken());
                    Glide.with(getActivity()).load(headImageUrl).transform(new GlideCircleTransform(getActivity())).into(mineImg);
                }
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeHeadImage(ModifyImage event) {
        if (!TextUtils.isEmpty(event.imageUrl)) {
            headImageUrl = event.imageUrl;
            Glide.with(getActivity()).load(event.imageUrl).transform(new GlideCircleTransform(getActivity())).into(mineImg);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getNickName(ModifyNickName event) {
        if (!TextUtils.isEmpty(event.nickName)) {
            nickName = event.nickName;
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == cameraRequestCode) {
            // 二维码扫描
            MipcaActivityCapture.openActivity(getActivity());
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(getActivity().getApplicationContext(), "拍照权限被拒，无法开启扫描二维码界面", Toast.LENGTH_SHORT).show();
        PermissionManager.handlerPermissionRefuseNotTip(this, perms);
    }
}

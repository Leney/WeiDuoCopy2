package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.im.IMClient;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.control.RecentChatListManager;
import com.zhongke.weiduo.mvp.control.UserInfoManager;
import com.zhongke.weiduo.mvp.ui.fragment.FamilyFragment;
import com.zhongke.weiduo.mvp.ui.fragment.FindFragment;
import com.zhongke.weiduo.mvp.ui.fragment.GrowUpFragment;
import com.zhongke.weiduo.mvp.ui.fragment.MainFragment;
import com.zhongke.weiduo.mvp.ui.fragment.SchoolFragment;
import com.zhongke.weiduo.util.ActionConstances;
import com.zhongke.weiduo.util.ActivityUtils;
import com.zhongke.weiduo.util.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * 主页面
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "MainActivity";
    private FragmentManager fragmentManager = null;
    /**
     * 当前所在tab的标识值
     */
    private int curTab = 0;
    private RadioGroup radioGroup;
    /**
     * 5个tab的fragment
     */
    private Fragment[] fragments = new Fragment[5];
    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityUtils.addActivity(this);
        setStatusBar();
        initView();
        LogUtil.i(TAG, "onCreate" + savedInstanceState);
        //系统重建活动
        if (savedInstanceState != null) {
            recoverState(savedInstanceState);
        } else {// 正常开启活动
            // 设置进入应用时 默认跳转的tab
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            showOrAddIndexFragment(0, MainFragment.TAG, transaction);
            curTab = 0;
        }
        // 初始化得到上次退出时的聊天列表信息 (暂时放这里 需要判断是否登录成功和聊天信息权鉴成功)
        RecentChatListManager.getInstance().init(this);
        // 获取联系人信息
        ContactsManager.getInstance().getContactsListFromNetwork(true);
        // 获取用户信息
        UserInfoManager.getInstance().getUserInfoFromNetwork();
        // 注册EventBus广播接收
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage(String action) {
        if (action == null) return;
        switch (action) {
            case ActionConstances.ACTION_ACTIVATE_DEVICE_SUCCESS:
                // 激活设备成功的广播action
                LogUtil.i("llj", "接收到激活设备成功的广播action");
                // 获取联系人信息
                ContactsManager.getInstance().getContactsListFromNetwork(false);
                break;
            case ActionConstances.ACTION_BIND_DEVICE_SUCCESS:
                // 绑定设备成功的广播action
                LogUtil.i("llj", "接收到绑定设备成功的广播action");
                // 获取联系人信息
                ContactsManager.getInstance().getContactsListFromNetwork(false);
                break;
        }
    }

    /**
     * 回复上次的记录,防止页面错乱
     *
     * @param savedInstanceState
     */
    private void recoverState(Bundle savedInstanceState) {
        curTab = savedInstanceState.getInt(TAG);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.length; ++i) {
            String tag;
            switch (i) {
                case 0:
                default:
                    tag = MainFragment.TAG;
                    break;
                case 1:
                    tag = FamilyFragment.TAG;
                    break;
                case 2:
                    tag = GrowUpFragment.TAG;
                    break;
                case 3:
                    tag = SchoolFragment.TAG;
                    break;
                case 4:
                    tag = FindFragment.TAG;
                    break;
            }
            fragments[i] = getSupportFragmentManager().findFragmentByTag(tag);
            if (fragments[i] != null) {
                transaction.hide(fragments[i]);
            }
        }
        transaction.show(fragments[curTab]).commitAllowingStateLoss();
    }

    /**
     * 创建制定tab对应的fragment
     *
     * @param position
     * @return
     */
    public Fragment createIndexFragment(int position) {
        switch (position) {
            case 0:
                return MainFragment.newInstance();
            case 1:
                return FamilyFragment.newInstance();
            case 2:
                return new GrowUpFragment();
            case 3:
                return new SchoolFragment();
            case 4:
                return new FindFragment();
            default:
                return null;
        }
    }

    /**
     * 修改当前页面的statusbar
     */
    private void setStatusBar() {
        Tools.setStatusColor(this, getResources().getColor(R.color.main_color));
    }

    /**
     * 初始化控件
     */
    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.main_tab_radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (checkedId) {
            case R.id.main_tab_1:
                if (curTab != 0) {
                    curTab = 0;
                    showOrAddIndexFragment(curTab, MainFragment.TAG, transaction);
                }
                break;
            case R.id.main_tab_2:
                if (curTab != 1) {
                    curTab = 1;
                    showOrAddIndexFragment(curTab, FamilyFragment.TAG, transaction);
                }
                break;
            case R.id.main_tab_3:
                if (curTab != 2) {
                    curTab = 2;
                    showOrAddIndexFragment(curTab, GrowUpFragment.TAG, transaction);
                }
                break;
            case R.id.main_tab_4:
                if (curTab != 3) {
                    curTab = 3;
                    showOrAddIndexFragment(curTab, SchoolFragment.TAG, transaction);
                }
                break;
            case R.id.main_tab_5:
                curTab = 4;
                showOrAddIndexFragment(curTab, FindFragment.TAG, transaction);
                break;
            default:
                break;
        }
    }

    /**
     * 隐藏全部fragment
     *
     * @param transaction
     */
    public void hideFragment(FragmentTransaction transaction) {
        for (int i = 0; i < fragments.length; ++i) {
            if (fragments[i] != null) {
                transaction.hide(fragments[i]);
            }
        }
    }

    /**
     * 添加或者显示指定的fragment
     *
     * @param position
     * @param tag
     * @param transaction
     */
    public void showOrAddIndexFragment(int position, String tag, FragmentTransaction transaction) {
        if (fragments[position] == null) {
            fragments[position] = createIndexFragment(position);
            transaction.add(R.id.main_tab_fragment_lay, fragments[position], tag);
        } else {
            transaction.show(fragments[position]);
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 还原RadioGroup Tab切换
     */
    private void changeTabBySth() {
        switch (curTab) {
            case 0:
                radioGroup.check(R.id.main_tab_1);
                break;
            case 1:
                radioGroup.check(R.id.main_tab_2);
                break;
            case 2:
                radioGroup.check(R.id.main_tab_3);
                break;
            case 3:
                radioGroup.check(R.id.main_tab_4);
            case 4:
                radioGroup.check(R.id.main_tab_5);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int length = fragments.length;
        for (int i = 0; i < length; i++) {
            if (fragments[i] == null) continue;
            fragments[i].onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TAG, curTab);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {//双击退出
            Toast.makeText(this,
                    getResources().getString(R.string.again_sure_exit),
                    Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            try {
                // 注销即时通讯 TODO 是否需要注销掉需要商榷
                IMClient.getInstance().unInitSdk();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finish();
            System.exit(0);
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

}

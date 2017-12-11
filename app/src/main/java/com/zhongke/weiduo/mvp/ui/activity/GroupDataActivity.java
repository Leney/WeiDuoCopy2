package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.GroupDataContract;
import com.zhongke.weiduo.mvp.model.entity.NewGroupMemberBean;
import com.zhongke.weiduo.mvp.presenter.GroupDataPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.GroupDataAdapter;
import com.zhongke.weiduo.mvp.ui.widget.MyGridView;
import com.zhongke.weiduo.mvp.ui.widget.dialog.ExitGroupDialog;

/**
 * Created by ${tanlei} on 2017/8/16.
 * 群详情界面(已加入群组)
 */

public class GroupDataActivity extends BaseMvpActivity implements View.OnClickListener, GroupDataContract, AdapterView.OnItemClickListener, ExitGroupDialog.ExitGroupListener {
    private MyGridView gridView;
    private GroupDataAdapter groupDataAdapter;
    private GroupDataPresenter groupDataPresenter;
    private TextView tvGroupName;
    private TextView tvAnnouncement;
    private TextView tvFindRecording;
    private TextView tvComplaints;
    private TextView tvClearRecording;
    private TextView tvDropOut;
    private FrameLayout flSwitch;
    private ExitGroupDialog dialog;
    private String groupId;
    private TextView groupName;

    @Override
    protected BasePresenter createPresenter() {
        groupDataPresenter = new GroupDataPresenter(mDataManager, this);
        return groupDataPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_group_data);
        groupId = getIntent().getStringExtra("stepId");
        showCenterView();
        initView();
        setListeners();
        groupDataPresenter.getGroupMemberList(groupId);
    }

    private void setListeners() {
        gridView.setOnItemClickListener(this);
        tvGroupName.setOnClickListener(this);
        tvAnnouncement.setOnClickListener(this);
        tvFindRecording.setOnClickListener(this);
        tvComplaints.setOnClickListener(this);
        tvClearRecording.setOnClickListener(this);
        tvDropOut.setOnClickListener(this);
        flSwitch.setOnClickListener(this);
    }

    private void initView() {
        setTitleName("群昵称");
        gridView = (MyGridView) findViewById(R.id.gridview);
        tvGroupName = (TextView) findViewById(R.id.tv1);
        tvAnnouncement = (TextView) findViewById(R.id.tv2);
        tvFindRecording = (TextView) findViewById(R.id.tv3);
        tvComplaints = (TextView) findViewById(R.id.tv4);
        tvClearRecording = (TextView) findViewById(R.id.tv5);
        tvDropOut = (TextView) findViewById(R.id.tv6);
        flSwitch = (FrameLayout) findViewById(R.id.fl1);
        groupName = (TextView) findViewById(R.id.group_name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1://群名称

                break;
            case R.id.tv2://群公告

                break;
            case R.id.tv3://查找聊天记录

                break;
            case R.id.tv4://投诉

                break;
            case R.id.tv5://清空聊天记录

                break;
            case R.id.tv6://删除并退出
                if (dialog == null) {
                    dialog = new ExitGroupDialog(this, R.style.dialog_no_title_style);
                    dialog.setListener(GroupDataActivity.this);
                }
                dialog.show();
                break;
            case R.id.fl1://消息免打扰开关

                break;
            default:
                break;
        }
    }

    public static void startActivity(Context context, String stepId/*, String stepName*/) {
        Intent intent = new Intent(context, GroupDataActivity.class);
        intent.putExtra("stepId", stepId);
//        intent.putExtra("stepName", stepName);
        context.startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getChildCount() - 1 == position) {//点击了加号
            Toast.makeText(this, "邀请好友", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 移除某个成员群组
     */
    @Override
    public void dropOutGroupSuccess() {

    }

    /**
     * 显示成员列表
     *
     * @param newGroupMemberBean
     */
    @Override
    public void showGroupMemberList(NewGroupMemberBean newGroupMemberBean) {
        if (null != newGroupMemberBean) {
            groupDataAdapter = new GroupDataAdapter(this, newGroupMemberBean.getMemberList());
            gridView.setAdapter(groupDataAdapter);
        } else {
            groupDataAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 退出群组成功
     */
    @Override
    public void quitGroupSuccess() {
        dialog.dismiss();
    }

    @Override
    public void quitGroupFailure(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    /**
     * 退出群组
     */
    @Override
    public void exitGroup() {
        groupDataPresenter.requestQuitGroup(groupId);
    }
}

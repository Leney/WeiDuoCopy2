package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.im.IMClient;
import com.zhongke.weiduo.im.IMConstance;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CreateGroupChatContract;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;
import com.zhongke.weiduo.mvp.model.entity.NewGroupBean;
import com.zhongke.weiduo.mvp.presenter.CreateGroupChatPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.ActivityCreateGroupAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

import static com.zhongke.weiduo.mvp.model.entity.ContactsListBean.TYPE_FRIEND_PERSON;

/**
 * Created by ${tanlei} on 2017/8/11.
 */

public class ActivityCreateGroupChat extends BaseMvpActivity implements CreateGroupChatContract {
    private EditText editText;
    private ListView lv;
    private ActivityCreateGroupAdapter groupAdapter;
    private CreateGroupChatPresenter createGroupChatPresenter;
    private static List<ContactsListBean> contactsListBeen;
    private List<ContactsListBean> newContactsListBeen = new ArrayList<>();

    @Override
    protected BasePresenter createPresenter() {
        createGroupChatPresenter = new CreateGroupChatPresenter(this, mDataManager);
        return createGroupChatPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_create_group);
        showCenterView();
        init();
        setData();
    }

    private void setData() {
        ContactsManager.getInstance().queryContactListByType(TYPE_FRIEND_PERSON, new Action1<List<ContactsListBean>>() {
            @Override
            public void call(List<ContactsListBean> contactsListBeen) {
                ActivityCreateGroupChat.contactsListBeen = contactsListBeen;
                groupAdapter = new ActivityCreateGroupAdapter(ActivityCreateGroupChat.this, contactsListBeen, ActivityCreateGroupChat.this);
                lv.setAdapter(groupAdapter);
            }
        });
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 这两个条件必须同时成立，如果仅仅用了enter判断，就会执行两次
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 执行发送消息等操作
                    newContactsListBeen.clear();
                    String name = editText.getText().toString().trim();
                    for (ContactsListBean bean : contactsListBeen) {
                        if (bean.getNickName().contains(name)) {
                            newContactsListBeen.add(bean);
                        }
                    }
                    groupAdapter.myNotifyDataSetChanged(newContactsListBeen);
                    return true;
                }
                return false;
            }
        });
    }

    private void init() {
        setTitleName("创建群聊");
        setRightVisivle(true);
        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer sb = new StringBuffer();
                for (ContactsListBean bean : contactsListBeen) {
                    if (bean.isCheck()) {
                        sb.append(bean.getId() + ",");
                    }
                }
                String s = sb.toString();
                s = s.substring(0, s.length() - 1);
                createGroupChatPresenter.createGroup(s);
            }
        });
        editText = (EditText) findViewById(R.id.editText);
        lv = (ListView) findViewById(R.id.listview);
    }

    public void setNumber(int num) {
        if (num <= 0) {
            setRightText("确定");
        } else {
            setRightText("确定" + "(" + num + ")");
        }
    }

    public static void startActivity(Context context/*, int stepId, String stepName*/) {
        Intent intent = new Intent(context, ActivityCreateGroupChat.class);
//        intent.putExtra("stepId", stepId);
//        intent.putExtra("stepName", stepName);
        context.startActivity(intent);
    }

    @Override
    public void createGroupSuccess(NewGroupBean newGroupBean) {
        // 创建成功重新获取联系人消息
        ContactsManager.getInstance().getContactsListFromNetwork(false);

        // 发送创建群聊成功的扩展消息
        List<String> memberIds = new ArrayList<>();
        for (ContactsListBean bean : contactsListBeen) {
            if (bean.isCheck()) {
                memberIds.add(bean.id + "");
            }
        }
        IMClient.sendExtMessage(IMConstance.CREATE_GROUP_REQUEST, memberIds,"");
        SessionActivity2.startActivity(ActivityCreateGroupChat.this, SessionActivity2.SESSION_TYPE_GROUP, newGroupBean.getGroup().getId() + "", newGroupBean.getGroup().getGName());
    }
}

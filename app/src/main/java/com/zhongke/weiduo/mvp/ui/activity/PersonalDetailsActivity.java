package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.im.IMClient;
import com.zhongke.weiduo.im.IMConstance;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.PersonalDetailsContract;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.control.RecentChatListManager;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;
import com.zhongke.weiduo.mvp.model.entity.FriendInfoBean;
import com.zhongke.weiduo.mvp.presenter.PersonalDetailsPresenter;
import com.zhongke.weiduo.mvp.ui.widget.dialog.DeleteContactDialog;
import com.zhongke.weiduo.util.FastBlurUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/8/10.
 * 好友资料界面
 */

public class PersonalDetailsActivity extends BaseMvpActivity implements View.OnClickListener, PersonalDetailsContract,
        DeleteContactDialog.ClickDeleteListener {
    private LinearLayout isFriendLay, addFriendLay;
    private TextView tvSendMes, tvDelete, addBtn, albumName;
    private PersonalDetailsPresenter personalDetailsPresenter;
    private ImageView ivBigPhoto, ivPhoto;
    private String id;
    private String name;
    private boolean isFriend;
    private TextView fullName;
    private TextView nickName;
    private TextView sex;
    private DeleteContactDialog dialog;
    private boolean hideBottom;

    @Override
    protected BasePresenter createPresenter() {
        personalDetailsPresenter = new PersonalDetailsPresenter(this, mDataManager);
        return personalDetailsPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_personal_details);
        //showCenterView();
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        isFriend = getIntent().getBooleanExtra("isFriend", false);
        hideBottom = getIntent().getBooleanExtra("hideBottom", false);

        LogUtil.e("id---" + id);
        personalDetailsPresenter.getFriendInfo(id);
        init();
        setListener();
    }

    private void setListener() {
        tvDelete.setOnClickListener(this);
        tvSendMes.setOnClickListener(this);
    }

    private void init() {

        setTitleName(name + "的资料");

        albumName = (TextView) findViewById(R.id.activity_personal_photo_album_name);
        tvSendMes = (TextView) findViewById(R.id.send_mes);
        tvDelete = (TextView) findViewById(R.id.delete);
        ivBigPhoto = (ImageView) findViewById(R.id.iv1);
        ivPhoto = (ImageView) findViewById(R.id.iv2);
        isFriendLay = (LinearLayout) findViewById(R.id.is_friend_lay);
        addFriendLay = (LinearLayout) findViewById(R.id.ll_add);
        addBtn = (TextView) findViewById(R.id.add);
        addBtn.setOnClickListener(this);
        fullName = (TextView) findViewById(R.id.activity_personal_full_name);
        nickName = (TextView) findViewById(R.id.activity_personal_nick_name);
        sex = (TextView) findViewById(R.id.activity_personal_sex);
        albumName.setText(name + "的相册");

        Log.i("llj", "isFriend----->>>" + isFriend);
        if (isFriend) {
            isFriendLay.setVisibility(View.VISIBLE);
            addFriendLay.setVisibility(View.GONE);
        } else {
            isFriendLay.setVisibility(View.GONE);
            addFriendLay.setVisibility(View.VISIBLE);
        }

        if (hideBottom) {
            isFriendLay.setVisibility(View.GONE);
            addFriendLay.setVisibility(View.GONE);
        }
    }

    /**
     * @param context
     * @param id       好友id
     * @param name     好友名称
     * @param isFriend 是否是好友
     */
    public static void startActivity(Context context, String id, String name, boolean isFriend, boolean hideBottom) {
        Intent intent = new Intent(context, PersonalDetailsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("isFriend", isFriend);
        intent.putExtra("hideBottom", hideBottom);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                // 添加好友按钮
                FriendVerificationActivity.openActivity(PersonalDetailsActivity.this, id);
                break;
            case R.id.send_mes:
                // 发送消息
                SessionActivity2.startActivity(PersonalDetailsActivity.this, SessionActivity2.SESSION_TYPE_PRIVATE, id + "", name);
                break;
            case R.id.delete:
                // 删除好友
                //Toast.makeText(this, "是否删除好友:" + name + "?", Toast.LENGTH_SHORT).show();
                if (dialog == null) {
                    dialog = new DeleteContactDialog(this, R.style.dialog_no_title_style, name);
                }
                dialog.show();
                dialog.setClickDeleteListener(this);
                break;
        }
    }

    @Override
    public void clickDelete() {
        personalDetailsPresenter.removeFriend(id);
    }

    //獲取好友詳情成功
    @Override
    public void getFriendInFoSuccess(FriendInfoBean bean) {
        FriendInfoBean.UserBean userBean = bean.getUser();
//        Glide.with(this).load(userBean.getHeadImageUrl()).into(ivPhoto);
        fullName.setText(userBean.getFullName());
        nickName.setText(userBean.getNickName());
        if (userBean.getSex() == 1) {
            sex.setText("男");
        } else if (userBean.getSex() == 1) {
            sex.setText("女");
        }
        String url = userBean.getHeadImageUrl();
        Glide.with(this).load(url).into(ivPhoto);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int scaleRatio = 2;
                // 下面的这个方法必须在子线程中执行
                final Bitmap blurBitmap2 = FastBlurUtil.GetUrlBitmap(url, scaleRatio);

                //  刷新ui必须在主线程中执行
                PersonalDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivBigPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        ivBigPhoto.setImageBitmap(blurBitmap2);

                    }
                });
            }
        }).start();
        showCenterView();
    }

    @Override
    public void getFriendInFoFailed(CommonException e) {

    }

    //移除好友成功
    @Override
    public void removeFriendSuccess() {
        ContactsManager.getInstance().deleteOneContacts(ContactsListBean.TYPE_FRIEND_PERSON, Integer.valueOf(id));
        // 删除聊天记录
        RecentChatListManager.getInstance().deleteRecentChatListBean(ChatListBean.CHAT_TYPE_SINGLE, id);
        // 发送删除好友的消息
        List<String> toUserList = new ArrayList<>();
        toUserList.add(id);
        StringBuilder builder = new StringBuilder();
        builder.append(IMConstance.DELETE_FRIEND_REQUEST)
                .append(",")
                .append(ChatListBean.CHAT_TYPE_SINGLE)
                .append(",")
                .append(IMClient.USER_ID);
        IMClient.sendExtMessage(builder.toString(), toUserList, "");
        UIUtils.showToast("删除成功");
        finish();
    }

    @Override
    public void removeFriendFailed() {

    }

}

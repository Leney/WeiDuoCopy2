package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.FamilyDetailContract;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.db.local.ContactsDBManager;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyOrGroupDetail;
import com.zhongke.weiduo.mvp.presenter.FamilyDetailPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.MemberAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.TagAdapter;
import com.zhongke.weiduo.mvp.ui.widget.dialog.DeleteFamilyDialog;
import com.zhongke.weiduo.mvp.ui.widget.dialog.MyFamilyListDialog;
import com.zhongke.weiduo.mvp.ui.widget.taglibiry.FlowTagLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;


/**
 * 家庭详情界面
 * Created by llj on 2017/6/23.
 */

public class FamilyDetailActivity extends BaseMvpActivity implements FamilyDetailContract, View.OnClickListener, MemberAdapter.OnItemListener, MyFamilyListDialog.OnMyFamilyItemClickListener {

    @Bind(R.id.family_detail_cover)
    ImageView coverImg;

    @Bind(R.id.family_detail_name)
    TextView name;

    @Bind(R.id.family_detail_location)
    TextView address;

    @Bind(R.id.family_detail_back_img)
    ImageView backImg;

    @Bind(R.id.family_detail_labs)
    FlowTagLayout familyLabsLay;

    @Bind(R.id.family_detail_members)
    FlowTagLayout memberLay;

    @Bind(R.id.family_detail_add_btn)
    TextView addFriendFamilyBtn;

    @Bind(R.id.family_detail_add_btn_lay)
    FrameLayout bottomLay;

    private FamilyDetailPresenter mPresenter;

    /**
     * 家庭id
     */
    private int familyId;

    /**
     * 是否是友好家庭
     */
    private boolean isFriend = false;
    /**
     * 设置家规家训
     */
    private TextView tvHouseRules;
    /**
     * 是否加入该家庭
     */
    private boolean isAdd;
    private String familyName;
    private NewFamilyOrGroupDetail newFamilyOrGroupDetail;
    private MemberAdapter memberAdapter;
    private List<NewFamilyOrGroupDetail.MemberListBean> memberList;
    private MyFamilyListDialog myFamilyListDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        // 设置公共的状态栏颜色不设置
        isSetStatusColor = false;
        super.onCreate(savedInstanceState);
        initData();
        baseTitle.setVisibility(View.GONE);
        setCenterLay(R.layout.activity_family_detail);

        tvHouseRules = (TextView) findViewById(R.id.house_rules);

        ButterKnife.bind(this);
        showCenterView();

        init();
        if (isAdd) {
            addFriendFamilyBtn.setText("退出家庭");
        }
        mPresenter.getFamilyDetail(familyId);
    }

    /**
     * 获取从Intent中传递过来的参数
     */
    private void initData() {
        familyId = getIntent().getIntExtra("familyId", -1);
        if (familyId <= -1) {
            finish();
            return;
        }
        isFriend = getIntent().getBooleanExtra("isFriend", false);
        isAdd = getIntent().getBooleanExtra("isAdd", false);
    }

    private void init() {
        backImg.setOnClickListener(this);
        addFriendFamilyBtn.setText(isFriend ? getResources().getString(R.string.create_group_chat) : getResources().getString(R.string.add_family_to_friend));
        addFriendFamilyBtn.setOnClickListener(this);
    }

    /**
     * 获取家庭信息成功
     *
     * @param newFamilyOrGroupDetail
     */
    @Override
    public void getDetailSuccess(NewFamilyOrGroupDetail newFamilyOrGroupDetail) {
        if (newFamilyOrGroupDetail != null) {
            this.newFamilyOrGroupDetail = newFamilyOrGroupDetail;
            this.familyName = newFamilyOrGroupDetail.getFamilyGroup().getGName();
            PhotoLoaderUtil.display(FamilyDetailActivity.this, coverImg, newFamilyOrGroupDetail.getFamilyGroup().getGCoverUrl(), R.drawable.ic_default_banner_large);
            name.setText(newFamilyOrGroupDetail.getFamilyGroup().getGName());
            TagAdapter labAdapter = new TagAdapter();
            familyLabsLay.setAdapter(labAdapter);
            String str = newFamilyOrGroupDetail.getFamilyGroup().getTagList();
            if (!TextUtils.isEmpty(str)) {
                List<String> tagList = Arrays.asList(str.split(","));
                labAdapter.onlyAddAll(tagList);
            }
            tvHouseRules.setText(newFamilyOrGroupDetail.getFamilyGroup().getGRule() + "");
            address.setText(newFamilyOrGroupDetail.getFamilyGroup().getAddress());

            memberAdapter = new MemberAdapter();
            memberAdapter.setOnItemListener(this);
            memberLay.setAdapter(memberAdapter);
            List<NewFamilyOrGroupDetail.MemberListBean> memberList = newFamilyOrGroupDetail.getMemberList();
            this.memberList = memberList;
            List<String> urls = new ArrayList<>();
            for (int i = 0; i < memberList.size(); i++) {
                urls.add(memberList.get(i).getHeadImageUrl());
            }
            memberAdapter.onlyAddAll(urls);
        } else {
            showNoDataView();
        }
    }

    /**
     * 获取数据失败
     */
    @Override
    public void getDetailFailed() {
        showErrorView();
    }

    /**
     * 添加家庭为友好家庭成功
     */
    @Override
    public void addFriendFamilySuccess() {
        createContacts();
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        FamilyListActivity.startActivity(this);
    }

    /**
     * 创建一个友好家庭对象插入数据库中
     */
    private void createContacts() {
        ContactsListBean clb = new ContactsListBean();
        clb.setType(ContactsListBean.TYPE_FRIEND_FAMILY);
        clb.setId(newFamilyOrGroupDetail.getFamilyGroup().getId());
        clb.setCreateTime(newFamilyOrGroupDetail.getFamilyGroup().getCreateTime());
        clb.setHeadUrl(newFamilyOrGroupDetail.getFamilyGroup().getGIconUrl());
        clb.setNickName(newFamilyOrGroupDetail.getFamilyGroup().getGName());
        clb.setCoverUrl(newFamilyOrGroupDetail.getFamilyGroup().getGCoverUrl());
        clb.setInfo(newFamilyOrGroupDetail.getFamilyGroup().getGInfo());
        ContactsDBManager.getInstance().insert(clb);
    }


    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new FamilyDetailPresenter(FamilyDetailActivity.this, mDataManager, this);
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.family_detail_back_img:
                // 返回按钮
                FamilyDetailActivity.this.finish();
                break;
            case R.id.family_detail_add_btn:
                // 加为友好家庭
                if (isFriend) {
                    if (isAdd) {
                        DeleteFamilyDialog dialog = new DeleteFamilyDialog(FamilyDetailActivity.this, R.style.dialog_no_title_style);
                        dialog.show();
                    } else {
                        // 是友好家庭，点击创建群聊
                        ActivityCreateGroupChat.startActivity(FamilyDetailActivity.this);
                    }
                } else {
                    // 还不是友好家庭，点击加为友好家庭
                    ContactsManager.getInstance().queryContactListByType(ContactsListBean.TYPE_MY_FAMILY, new Action1<List<ContactsListBean>>() {
                        @Override
                        public void call(List<ContactsListBean> contactsListBeen) {
                            if (contactsListBeen != null) {
                                if (contactsListBeen.size() == 1) {
                                    mPresenter.addFriendFamily(familyId, familyName, contactsListBeen.get(0).getId() + "", contactsListBeen.get(0).nickName);
                                } else {
                                    myFamilyListDialog = new MyFamilyListDialog(FamilyDetailActivity.this, contactsListBeen, FamilyDetailActivity.this);
                                    myFamilyListDialog.show();
                                }
                            }
                        }
                    });
                }
                break;
        }
    }

    /**
     * @param context
     * @param familyId 家庭id
     * @param isFriend 是否是好友
     */
    public static void startActivity(Context context, int familyId, boolean isFriend, boolean isAdd) {
        Intent intent = new Intent(context, FamilyDetailActivity.class);
        intent.putExtra("familyId", familyId);
        intent.putExtra("isFriend", isFriend);
        intent.putExtra("isAdd", isAdd);
        context.startActivity(intent);
    }

    @Override
    public void onItem(int position) {
//        Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();
        PersonalDetailsActivity.startActivity(this, memberList.get(position).getUserId() + "", memberList.get(position).getUserName(), false, false);
    }

    @Override
    public void ClickItem(String id, String name) {
        mPresenter.addFriendFamily(familyId, familyName, id, name);
    }
}

package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SystemDetailsContract;
import com.zhongke.weiduo.mvp.model.entity.SmallAimsBean;
import com.zhongke.weiduo.mvp.model.entity.SystemBean;
import com.zhongke.weiduo.mvp.model.entity.SystemListDetailBean;
import com.zhongke.weiduo.mvp.presenter.SystemDetailsPresenter;
import com.zhongke.weiduo.mvp.ui.widget.tree.MyNodeViewFactory;
import com.zhongke.weiduo.mvp.ui.widget.tree.treeview.TreeNode;
import com.zhongke.weiduo.mvp.ui.widget.tree.treeview.TreeView;

import java.util.List;


/**
 * Created by ${tanlei} on 2017/9/19.
 * 体系详情界面
 */

public class SystemDetailsActivity extends BaseMvpActivity implements SystemDetailsContract, View.OnClickListener {
    private SystemDetailsPresenter presenter;
    private FrameLayout systemDetails;
    private TextView systemDetailsName, systemDetailsText;
    private ImageView headImg;

    private List<SmallAimsBean> list;

//    /**
//     * 跑马灯控件
//     */
//    private MarqueeView topMarqueeView;

    @Override
    protected BasePresenter createPresenter() {
        presenter = new SystemDetailsPresenter(this, mDataManager);
        return presenter;
    }

    @Override
    public String getBookId() {
        return String.valueOf(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_system_details);
        getData();
        showCenterView();
        init();
        this.presenter.loadData();
    }

    private String name;
    private String text;
    private int id;

    private void getData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
//     id=112;
        name = intent.getStringExtra("name");
        text = intent.getStringExtra("text");
    }

    private void init() {
        setTitleName("体系介绍");
//        topMarqueeView = (MarqueeView) findViewById(R.id.system_detail_marqueeView);
//        topMarqueeView.startMarquee();
//        topMarqueeView.setOnClickListener(this);
        headImg = (ImageView) findViewById(R.id.tv1);
        systemDetails = (FrameLayout) findViewById(R.id.system_details_lv);
        systemDetailsName = (TextView) findViewById(R.id.system_details_name);
        systemDetailsText = (TextView) findViewById(R.id.system_details_text);
        systemDetailsName.setText(name);
        systemDetailsText.setText(text);
        list = SmallAimsBean.getData();
//        adapter = new SystemDetailsAdapter(list, this);


//        PhotoLoaderUtil.display(SystemDetailsActivity.this, headImg, "http://pic2.ooopic.com/10/64/71/63b1OOOPIC56.jpg", null);


 /*       root = TreeNode.root();
//        buildTree();
        for (int i = 0; i < 5; i++) {
            TreeNode treeNode = new TreeNode(new String("核心素养(" + i + ")"));
            treeNode.setLevel(0);
            treeNode.setIconRes(R.mipmap.system_first_tree_point);
            root.addChild(treeNode);
            for (int j = 0; j < 4; j++) {
                TreeNode treeNode2 = new TreeNode(new String("微目标名称(" + j + ")"));
                treeNode2.setLevel(1);
                treeNode2.setIconRes(R.mipmap.system_other_tree_point);
                treeNode2.setType(TreeNode.TYPE_TARGET);
                treeNode2.setValueId(10001);
                treeNode.addChild(treeNode2);
//                for (int k = 0; k < 3; k++) {
//                    TreeNode treeNode3 = new TreeNode(new String("行为名称(" + k + ")"));
//                    treeNode3.setLevel(2);
//                    treeNode3.setIconRes(R.mipmap.system_other_tree_point);
//                    treeNode2.addChild(treeNode3);
//                    for (int l = 0; l < 3; l++) {
//                        TreeNode treeNode4 = new TreeNode(new String("四级核心素养(" + l + ")"));
//                        treeNode4.setLevel(3);
//                        treeNode4.setType(TreeNode.TYPE_TARGET);
//                        treeNode4.setValueId(1003);
//                        treeNode4.setIconRes(R.mipmap.system_other_tree_point);
//                        treeNode3.addChild(treeNode4);
//                        for (int m = 0; m < 3; m++) {
//                            TreeNode treeNode5 = new TreeNode(new String("五级核心素养(" + m + ")"));
//                            treeNode5.setLevel(4);
//                            treeNode5.setType(TreeNode.TYPE_TARGET);
//                            treeNode5.setValueId(1001);
//                            treeNode5.setIconRes(R.mipmap.system_other_tree_point);
//                            treeNode4.addChild(treeNode5);
//                        }
//                    }
//                }
            }

        }

        TreeView treeView = new TreeView(root, this, new MyNodeViewFactory(false, MyNodeViewFactory.LINE_TYPE));
        View view = treeView.getView();
        view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        systemDetails.addView(view);*/

    }


    @Override
    public void createTreeView(TreeNode root) {
        TreeView treeView = new TreeView(root, this, new MyNodeViewFactory(false, MyNodeViewFactory.LINE_TYPE));
        View view = treeView.getView();
        view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        systemDetails.addView(view);
    }


    public static void startActivity(Context context, SystemBean str) {
        Intent intent = new Intent();
        intent.setClass(context, SystemDetailsActivity.class);
        intent.putExtra("data", str);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int id, String name, String text) {
        Intent intent = new Intent(context, SystemDetailsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("text", text);
        context.startActivity(intent);
    }

    @Override
    public void showData(SystemListDetailBean systemListDetailBean) {
        SystemListDetailBean.AbilityBookBean abilityBookBean = systemListDetailBean.getAbility_book().get(0);
        systemDetailsName.setText(abilityBookBean.getBName());
        systemDetailsText.setText(abilityBookBean.getBInfo());
        PhotoLoaderUtil.display(SystemDetailsActivity.this, headImg, abilityBookBean.getCoverUrl(), null);
        this.presenter.addTreeView(systemListDetailBean.getCatalog());

    }


    @Override
    public void showError(CommonException e) {
        UIUtils.showToast(e.getErrorMsg());
        this.showErrorView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.system_detail_marqueeView:
//                FullScreenPlayVideoActivity.startActivity(SystemDetailsActivity.this, "http://baobab.wdjcdn.com/14564977406580.mp4", "");
//                topMarqueeView.setVisibility(View.GONE);
//                break;
            default:
                break;
        }
    }
}

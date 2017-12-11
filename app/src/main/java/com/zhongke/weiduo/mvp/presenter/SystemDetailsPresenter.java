package com.zhongke.weiduo.mvp.presenter;

import android.util.Log;
import android.util.SparseArray;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.library.retrofit.BuilderMap;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SystemDetailsContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.SystemListDetailBean;
import com.zhongke.weiduo.mvp.ui.widget.tree.treeview.TreeNode;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ${tanlei} on 2017/9/19.
 */

public class SystemDetailsPresenter extends BasePresenter {
    private final String tag = SystemDetailsPresenter.class.getSimpleName();
    private SystemDetailsContract systemDetailsContract;
    private DataManager manager;

    public SystemDetailsPresenter(SystemDetailsContract systemDetailsContract, DataManager manager) {
        this.systemDetailsContract = systemDetailsContract;
        this.manager = manager;
    }

    public void loadData() {
        executeTask();
    }

    /**
     * 执行网络请求
     */
    private void executeTask() {
        Subscription subscription = retrofitClient.getSystemListDetail(BuilderMap.buildMapWithSystemDetail(systemDetailsContract.getBookId()), new ResponseResultListener<SystemListDetailBean>() {
            @Override
            public void success(SystemListDetailBean systemListDetailBean) {
                systemDetailsContract.showData(systemListDetailBean);
            }

            @Override
            public void failure(CommonException e) {
                systemDetailsContract.showError(e);
            }
        });
        this.compositeSubscription.add(subscription);
    }

    /**
     * 加载树形的视图
     *
     * @param catalogBeanList
     */
    public void addTreeView(final List<SystemListDetailBean.CatalogBean> catalogBeanList) {
        Subscription subscription = filterTreeNode(catalogBeanList)
                .subscribeOn(Schedulers.computation())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TreeNode>() {
                               @Override
                               public void call(TreeNode treeNode) {
                                   systemDetailsContract.createTreeView(treeNode);
                               }
                           }
                );
        this.compositeSubscription.add(subscription);
    }

    private Observable<TreeNode> filterTreeNode(List<SystemListDetailBean.CatalogBean> catalogBeanList) {
        return Observable.create(new Observable.OnSubscribe<TreeNode>() {
            @Override
            public void call(Subscriber<? super TreeNode> subscriber) {
                TreeNode root = TreeNode.root();
                SparseArray<List<SystemListDetailBean.CatalogBean>> sort = new SparseArray<>();
                //先排列，从0开始，每个位置对应一个list
                for (SystemListDetailBean.CatalogBean catalogBean : catalogBeanList) {
                    int position = catalogBean.getLevel();
                    List<SystemListDetailBean.CatalogBean> childSort = sort.get(position, null);
                    if (childSort == null) {
                        childSort = new ArrayList<>();
                        Log.i(tag, "  分类 " + position + " " + childSort);
                        sort.put(position, childSort);
                    }
                    childSort.add(catalogBean);
                }
                Log.i(tag, "排列的个数" + sort.size());
                SystemDetailsPresenter.this.adTreeNode(root, sort, 0,R.mipmap.system_first_tree_point);
                subscriber.onNext(root);
            }
        });
    }

    /**
     * 递归计算
     *
     * @param root
     * @param sort
     * @param position
     */
    private void adTreeNode(final TreeNode root, final SparseArray<List<SystemListDetailBean.CatalogBean>> sort, final int position,final int resourcesId) {
        Log.i(tag, " adTreeNode" + root + "  " + sort.size() + " " + position);
        List<SystemListDetailBean.CatalogBean> childSort = sort.get(position);
        for (SystemListDetailBean.CatalogBean catalogBean : childSort) {
            Log.i(tag, " 开始匹配level " + root.getBelong() + " " + catalogBean.getLevel() + " " + catalogBean.getPId());
            if (catalogBean.getPId() == root.getValueId()) {
                TreeNode child = new TreeNode(catalogBean.getName());
                child.setLevel(catalogBean.getLevel());
                child.setBookId(catalogBean.getBookId());
                child.setIconRes(resourcesId);
                child.setType(TreeNode.TYPE_TARGET);
                child.setValueId(catalogBean.getId());
                child.setResId(catalogBean.getResId());
                root.addChild(child);
                int index = position;
                index++;
                Log.i(tag, " 准备匹配下一个level " + child.getLevel() + " " + index);
                if (index < sort.size()) {
                    adTreeNode(child, sort, index,R.mipmap.system_other_tree_point);
                }
            } else {
                continue;
            }
        }
    }


}

package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolder;
import com.lqr.adapter.LQRViewHolderForRecyclerView;
import com.lqr.adapter.OnItemClickListener;
import com.lqr.recyclerview.LQRRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.NewMechanismBean;
import com.zhongke.weiduo.mvp.presenter.OrganizationListAtPresent;
import com.zhongke.weiduo.mvp.ui.widget.view.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karma on 2017/9/4.
 * 类描述：机构列表
 */

public class OrganizationListActivity extends BaseMvpActivity {
    private LQRRecyclerView mRvMsg;
    private Banner mBanner;
    private OrganizationListAtPresent present;
    private LQRAdapterForRecyclerView<NewMechanismBean.RecordsBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_organizationlist);
        showLoadingView();
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        present = new OrganizationListAtPresent(this);
        return present;
    }

    public void initView() {
        mRvMsg = (LQRRecyclerView) findViewById(R.id.or_msg);
        mRvMsg.addItemDecoration(new SimpleDividerItemDecoration(this,1));
        mBanner = (Banner) findViewById(R.id.banner);
        present.getOrganizationCarousel();
    }

    public void initData() {
        setTitleName("机构");
        present.getMechanismList();
    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, OrganizationListActivity.class));
    }

    public void showMechanismList(List<NewMechanismBean.RecordsBean> mData) {
        if (mData != null) {
            showCenterView();
            if (adapter == null) {
                adapter = new LQRAdapterForRecyclerView<NewMechanismBean.RecordsBean>(this, mData, R.layout.item_organization_list) {
                    @Override
                    public void convert(LQRViewHolderForRecyclerView helper, NewMechanismBean.RecordsBean item, int position) {
                        helper.setText(R.id.or_title, item.getName());
                        helper.setText(R.id.or_introduct, item.getInfo());
                        Glide.with(OrganizationListActivity.this).load(item.getLogo()).into((ImageView) helper.getView(R.id.or_img));
//                        helper.setText(R.id.or_count, "共12节课");
//                        helper.setText(R.id.or_people, "1927粉丝");
                    }
                };
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(LQRViewHolder helper, ViewGroup parent, View itemView, int position) {
                        OrganizationDataActivity.startActivity(OrganizationListActivity.this, mData.get(position).getId(), mData.get(position).getName());
                    }
                });
            }
            mRvMsg.setAdapter(adapter);
        }
    }

    public void showOrganizationCarousel(CarouselBitmapBean carouselBitmapBean) {
        List<String> bannerUrls = new ArrayList<>();
        if (carouselBitmapBean != null) {
            //请求到了轮播图片
            List<CarouselBitmapBean.RecordsBean> records = carouselBitmapBean.getRecords();
            for (CarouselBitmapBean.RecordsBean recordsBean : records) {
                bannerUrls.add(recordsBean.getImageUrl());
            }
        } else {
            //请求到的轮播图片给空时，给默认图
            bannerUrls.add("http://img1.niutuku.com/psd/02/0257-70.jpg");
            bannerUrls.add("http://pic.ffpic.com/files/2013/0504/20130503psd014.jpg");
            bannerUrls.add("http://image.tupian114.com/20130122/04265055.jpg");
        }
        mBanner.setImages(bannerUrls);
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setDelayTime(2000);
        mBanner.start();
    }
}

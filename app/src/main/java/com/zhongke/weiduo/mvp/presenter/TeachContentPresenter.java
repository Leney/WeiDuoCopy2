package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.TeachContentContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.PlayModeBean;
import com.zhongke.weiduo.mvp.model.entity.TeachContentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/23.
 */

public class TeachContentPresenter extends BasePresenter {
    private static final String TAG = "TeachContentPresenter";
    private TeachContentContract teachContentContract;
    private DataManager dataManager;

    public TeachContentPresenter(TeachContentContract teachContentContract, DataManager dataManager) {
        this.teachContentContract = teachContentContract;
        this.dataManager = dataManager;
    }

    /**
     * 删除某一栏教学内容
     *
     * @param parentPosition 点击删除的某栏在集合中的位置
     */
    public void deleteTeachContentItem(int parentPosition) {

    }

    /**
     * 删除某一栏教学内容中的某一项设备
     *
     * @param chlidPosition  教学内容集合中的某一个设备的位置
     * @param parentPosition 教学内容在集合中的位置
     */
    public void deleteItem(int chlidPosition, int parentPosition) {

    }

    public void showTeachContentData() {
        List<TeachContentBean> teachContentBeans = new ArrayList<>();

        TeachContentBean te = new TeachContentBean();
        te.setTeachContentName("数学课");
        List<PlayModeBean> listss = new ArrayList<>();

        PlayModeBean pmb1 = new PlayModeBean();
        pmb1.setPlayPlace("主");
        pmb1.setPlayAngleDate("33:55:55");
        pmb1.setPlayAngleValue("上 3.0 下500");

        PlayModeBean pmb2 = new PlayModeBean();
        pmb2.setPlayPlace("投影");
        pmb2.setPlayAngleDate("33:55:55");
        pmb2.setPlayAngleValue("上 990 下500");

        listss.add(pmb1);
        listss.add(pmb2);
        te.setPlayModeBeans(listss);

        TeachContentBean te2 = new TeachContentBean();
        te2.setTeachContentName("学课");
        List<PlayModeBean> listsss = new ArrayList<>();
        PlayModeBean pmb3 = new PlayModeBean();
        pmb3.setPlayPlace("主");
        pmb3.setPlayAngleDate("33:55:55");
        PlayModeBean pmb4 = new PlayModeBean();
        pmb4.setPlayPlace("投影");
        pmb4.setPlayAngleDate("33:55:55");
        pmb4.setPlayAngleValue("左 990 右500");
        PlayModeBean pmb5 = new PlayModeBean();
        pmb5.setPlayPlace("主屏投影");
        pmb5.setPlayAngleDate("33:55:55");
        pmb5.setPlayAngleValue("左 990 右500");
        listsss.add(pmb3);
        listsss.add(pmb4);
        listsss.add(pmb5);
        te2.setPlayModeBeans(listsss);
        teachContentBeans.add(te);
        teachContentBeans.add(te2);
        teachContentContract.showData(teachContentBeans);
    }
}
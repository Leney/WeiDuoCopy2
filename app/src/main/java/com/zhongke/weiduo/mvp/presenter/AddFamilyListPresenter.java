package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AddFamilyListContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.FamilyClassifyListBean;
import com.zhongke.weiduo.mvp.model.entity.FamilyInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加家庭列表
 * Created by llj on 2017/6/22.
 */

public class AddFamilyListPresenter extends BasePresenter {
    private static final String TAG = "AddFamilyListPresenter";
    private Context mContext;
    private DataManager mDataManager;
    private AddFamilyListContract mContract;

    public AddFamilyListPresenter(Context context, DataManager dataManager, AddFamilyListContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

    /**
     * 获取家庭分类类别列表
     */
    public void getFamilyClassifyList(){
        FamilyClassifyListBean classifyListBean = new FamilyClassifyListBean();
        classifyListBean.setClassifyList(new ArrayList<FamilyClassifyListBean.ClassifyListBean>());
        FamilyClassifyListBean.ClassifyListBean bean1 = new FamilyClassifyListBean.ClassifyListBean();
        bean1.setId(10021);
        bean1.setName("学霸类");
        FamilyClassifyListBean.ClassifyListBean bean2 = new FamilyClassifyListBean.ClassifyListBean();
        bean2.setId(10022);
        bean2.setName("技能类");
        FamilyClassifyListBean.ClassifyListBean bean3 = new FamilyClassifyListBean.ClassifyListBean();
        bean3.setId(10023);
        bean3.setName("旅游类");
        FamilyClassifyListBean.ClassifyListBean bean4 = new FamilyClassifyListBean.ClassifyListBean();
        bean4.setId(10024);
        bean4.setName("颜值类");
        classifyListBean.getClassifyList().add(bean1);
        classifyListBean.getClassifyList().add(bean2);
        classifyListBean.getClassifyList().add(bean3);
        classifyListBean.getClassifyList().add(bean4);

        mContract.getFamilyClassifySuccess(classifyListBean);
    }

    /**
     * 获取附近家庭列表
     */
    public void getNearbyFamilyList(){
        List<FamilyInfo> list = new ArrayList<>();

        FamilyInfo familyInfo = new FamilyInfo();
        familyInfo.setId(100021);
        familyInfo.setName("小红的家庭");
        familyInfo.setDistance("600m");
        familyInfo.setAddress("深圳市宝安西乡");
        familyInfo.setAltitude(100002.2314);
        familyInfo.setLatitude(154.255);
        familyInfo.setIcon("http://www.uimaker.com/uploads/allimg/110725/1_110725083814_10.png");

        FamilyInfo familyInfo1 = new FamilyInfo();
        familyInfo1.setId(100022);
        familyInfo1.setName("天天的家庭");
        familyInfo1.setDistance("1.2km");
        familyInfo1.setAddress("深圳市南山");
        familyInfo1.setAltitude(100002.2314);
        familyInfo1.setLatitude(154.255);
        familyInfo1.setIcon("http://www.uimaker.com/uploads/allimg/110303/1_110303102329_4_lit.png");

        FamilyInfo familyInfo2 = new FamilyInfo();
        familyInfo2.setId(100023);
        familyInfo2.setName("天天开心家族");
        familyInfo2.setDistance("1.3km");
        familyInfo2.setAddress("深圳市龙华");
        familyInfo2.setAltitude(100002.2314);
        familyInfo2.setLatitude(154.255);
        familyInfo2.setIcon("http://www.th7.cn/d/file/p/2012/02/06/64158e02bff2b6b05362fd336fec4e42.png");

        FamilyInfo familyInfo3 = new FamilyInfo();
        familyInfo3.setId(100024);
        familyInfo3.setName("李氏家族");
        familyInfo3.setDistance("1.5km");
        familyInfo3.setAddress("深圳市宝安西乡");
        familyInfo3.setAltitude(100002.2314);
        familyInfo3.setLatitude(154.255);
        familyInfo3.setIcon("http://www.uimaker.com/uploads/allimg/130225/1_130225101108_3.jpg");

        FamilyInfo familyInfo4 = new FamilyInfo();
        familyInfo4.setId(100025);
        familyInfo4.setName("小红的家庭");
        familyInfo4.setDistance("12.5km");
        familyInfo4.setAddress("深圳市福田");
        familyInfo4.setAltitude(100002.2314);
        familyInfo4.setLatitude(154.255);
        familyInfo4.setIcon("http://www.uimaker.com/uploads/allimg/110915/1_110915092546_9.jpg");

        list.add(familyInfo);
        list.add(familyInfo1);
        list.add(familyInfo2);
        list.add(familyInfo3);
        list.add(familyInfo4);


        mContract.getNearbyFamilyListSuccess(list);
    }
}

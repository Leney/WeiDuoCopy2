package com.zhongke.weiduo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseFragment;
import com.zhongke.weiduo.mvp.contract.AddFamilyListContract;
import com.zhongke.weiduo.mvp.model.entity.FamilyClassifyListBean;
import com.zhongke.weiduo.mvp.model.entity.FamilyInfo;
import com.zhongke.weiduo.mvp.ui.adapter.FamilyListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 添加家庭 显示列表界面
 * Created by llj on 2017/6/22.
 */

public class AddFamilyListActivity extends BaseFragment implements AddFamilyListContract {

    @Bind(R.id.add_family_listView)
    ListView listView;

    @Bind(R.id.add_family_no_data)
    TextView noData;

    private FamilyListAdapter adapter;


    private List<FamilyInfo> familyList;

    private View headerView;

    /**
     * 家庭列表显示区域
     */
    LinearLayout classifiesLay;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_add_family_list, null);
        ButterKnife.bind(getActivity());
        init(v);
        return v;
    }

    private void init(View v) {
        headerView = View.inflate(getActivity(), R.layout.header_add_family_list, null);
        listView = (ListView) v.findViewById(R.id.add_family_listView);
        classifiesLay = (LinearLayout) headerView.findViewById(R.id.add_family_classify_lay);
        listView.addHeaderView(headerView);
        familyList = new ArrayList<>();

        adapter = new FamilyListAdapter(familyList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.i("llj", "i---------->>" + i);
                Log.i("llj", "l---------->>" + l);


                FamilyInfo familyInfo = familyList.get((int) l);
                if (familyInfo == null) {
                    return;
                }
                FamilyDetailActivity.startActivity(getActivity(), familyInfo.getId(),false,false);
            }
        });
    }


    @Override
    public void getFamilyClassifySuccess(FamilyClassifyListBean classifyListBean) {
        int length = classifyListBean.getClassifyList().size();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        for (int i = 0; i < length; i++) {
            FamilyClassifyListBean.ClassifyListBean bean = classifyListBean.getClassifyList().get(i);
            View view = View.inflate(getActivity(), R.layout.family_classify_item_lay, null);
            TextView icon = (TextView) view.findViewById(R.id.family_classify_name_icon);
            TextView name = (TextView) view.findViewById(R.id.family_classify_name_text);
            int index = i % 4;
            switch (index) {
                case 0:
                    icon.setBackgroundResource(R.drawable.circle_blue_bg_shape);
                    break;
                case 1:
                    icon.setBackgroundResource(R.drawable.circle_yellow_bg_shape);
                    break;
                case 2:
                    icon.setBackgroundResource(R.drawable.circle_green_bg_shape);
                    break;
                case 3:
                    icon.setBackgroundResource(R.drawable.circle_red_bg_shape);
                    break;
            }
            icon.setText(bean.getName().substring(0, 1));
            name.setText(bean.getName());
            view.setOnClickListener(classifyClickListener);
            view.setTag(bean);
            classifiesLay.addView(view, params);
        }

    }

    @Override
    public void getFamilyClassifyFailed(int errorCode, String msg) {
        showErrorView();
    }

    @Override
    public void getNearbyFamilyListSuccess(List<FamilyInfo> list) {
        familyList.addAll(list);
        if (familyList.isEmpty()) {
            noData.setVisibility(View.VISIBLE);
        } else {
            noData.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getNearbyFamilyListFailed(int errorCode, String msg) {
        noData.setText(getResources().getString(R.string.get_nearby_family_failed));
        noData.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private View.OnClickListener classifyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FamilyClassifyListBean.ClassifyListBean classifyBean = (FamilyClassifyListBean.ClassifyListBean) view.getTag();
            Toast.makeText(getActivity(), classifyBean.getName(), Toast.LENGTH_SHORT).show();
            // TODO 跳转到根据类别获取的家庭列表界面
        }
    };
}

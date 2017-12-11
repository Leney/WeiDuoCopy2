package com.zhongke.weiduo.mvp.ui.adapter;

import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.LabInfo;

import java.util.List;

/**
 * 标签adapter
 * Created by llj on 2017/6/21.
 */

public class LabAdapter extends BaseAdapter {
    private List<LabInfo> labList;
    private boolean isNeedAdd;
    private OnAddLabListener listener;
    private SparseArray<Boolean> checkArray;

    public LabAdapter(List<LabInfo> labs, boolean isNeedAdd) {
        this.labList = labs;
        this.isNeedAdd = isNeedAdd;
        this.checkArray=new SparseArray<>();
    }

    public void setAddLabListener(OnAddLabListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return labList.size();
    }

    @Override
    public Object getItem(int i) {
        return labList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HolderView holderView;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.adapter_lab_item, null);
            holderView = new HolderView();
            holderView.lab = (TextView) view.findViewById(R.id.lab_adapter_text);
            view.setTag(holderView);
        } else {
            holderView = (HolderView) view.getTag();
        }

        LabInfo labInfo = (LabInfo) getItem(i);
        holderView.lab.setText(labInfo.getName());
        holderView.lab.setTag(labInfo);

        if (isNeedAdd && i == labList.size() - 1) {
            // 最后一条
            holderView.lab.setOnClickListener(addClickListener);
            holderView.lab.setBackgroundResource(R.drawable.lab_add_btn_shape);
            holderView.lab.setTextColor(ContextCompat.getColor(viewGroup.getContext(), R.color.white));
        } else {
            holderView.lab.setOnClickListener(itemClickListener);
            holderView.lab.setBackgroundResource(R.drawable.lab_btn_selector);
            if (labInfo.isCheck()) {
                checkArray.put(i,true);
                holderView.lab.setTextColor(ContextCompat.getColor(viewGroup.getContext(), R.color.main_color));
            } else {
                checkArray.put(i,false);
                holderView.lab.setTextColor(ContextCompat.getColor(viewGroup.getContext(), R.color.black_text_color));
            }
        }
        holderView.lab.setSelected(labInfo.isCheck());

        return view;
    }

    private class HolderView {
        TextView lab;
    }

    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LabInfo labInfo = (LabInfo) view.getTag();
            view.setSelected(!labInfo.isCheck());
            labInfo.setCheck(!labInfo.isCheck());
            notifyDataSetChanged();
        }
    };

    private View.OnClickListener addClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.addLab();
            }
        }
    };
    public interface OnAddLabListener {
        void addLab();
    }

    public String getCheckTab(){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<checkArray.size();++i){
           int key=  checkArray.keyAt(i);
            boolean check=checkArray.get(key);
            if (check){
               stringBuilder.append( labList.get(key).getName());
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

}

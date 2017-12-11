package com.zhongke.weiduo.mvp.ui.adapter;

import android.opengl.GLSurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.CatalogueBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CataLogBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;

import java.util.List;



/**
 * 课程详情目录Adapter
 * Created by llj on 2017/9/15.
 */

public class CourseCatalogueAdapter extends BaseAdapter {
    private List<CourseDetailResult.CatalogBean> list;

    public CourseCatalogueAdapter(List<CourseDetailResult.CatalogBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list !=null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(), R.layout.adapter_course_catalogue,null);
            holderView = new HolderView();
            holderView.init(convertView);
            convertView.setTag(holderView);
        }else {
            holderView = (HolderView) convertView.getTag();
        }

        CourseDetailResult.CatalogBean bean = list.get(position);
        if (list != null) {
            holderView.num.setText(String.valueOf(position+1));
        }
        holderView.name.setText(bean.getName());
        holderView.time.setText(bean.getType()+parent.getResources().getString(R.string.minutes));
        holderView.itemLay.setSelected(bean.isSelect());

        return convertView;
    }


    class HolderView{
        LinearLayout itemLay;
        TextView num,name,time;
        public void init(View baseView){
            itemLay = (LinearLayout) baseView.findViewById(R.id.catalogue_adapter_lay);
            num = (TextView) baseView.findViewById(R.id.catalogue_adapter_num);
            name = (TextView) baseView.findViewById(R.id.catalogue_adapter_name);
            time = (TextView) baseView.findViewById(R.id.catalogue_adapter_time);
        }
    }
}

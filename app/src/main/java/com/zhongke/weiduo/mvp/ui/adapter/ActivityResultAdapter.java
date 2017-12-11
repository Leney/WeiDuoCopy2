package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.zhongke.weiduo.R;

/**
 * Created by ${xingen} on 2017/6/23.
 */

public class ActivityResultAdapter extends BaseAdapter {
    private Context context;
    public ActivityResultAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (position){
            case 0:
                return View.inflate(context, R.layout.activityresult_listview_item0,null);
            case 1:
                return createPrizeView();

            case 2:
                return View.inflate(context, R.layout.activityresult_listview_item2,null);
            case 3:
                return View.inflate(context, R.layout.activityresult_listview_item3,null);

            default:
                return null;
        }
    }

    /**
     * 创建活动奖品展示
     * @return
     */
    public View createPrizeView(){
        View rootView= View.inflate(context, R.layout.activityresult_listview_item1,null);
        LinearLayout linearLayout=(LinearLayout) rootView.findViewById(R.id.activityresult_item_content_layout);
        View childrenView=View.inflate(context,R.layout.activityprizeshow_item,null);
        GridView gridView=(GridView) childrenView.findViewById(R.id.activityprizeshow_item_gridView);
        gridView.setAdapter(new ActivityPrizeAdapter(context));
        linearLayout.addView(childrenView);
        return rootView;
    }

}

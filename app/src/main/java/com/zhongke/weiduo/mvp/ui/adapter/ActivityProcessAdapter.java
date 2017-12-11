package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.StringUtils;

/**
 * Created by ${xingen} on 2017/6/21.
 */

public class ActivityProcessAdapter extends BaseAdapter {
    private Context context;
    private View.OnClickListener onClickListener;

    public ActivityProcessAdapter(Context context, View.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener=onClickListener;
    }

    @Override
    public int getCount() {
        return 3;
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
        switch (position) {
            case 0:
                convertView= View.inflate(context, R.layout.activityprocess_listview_item_ready, null);
                convertView.findViewById(R.id.activityprocess_listview_item_person_tv).setOnClickListener(onClickListener);
                convertView.findViewById(R.id.activityprocess_listview_item_funds_tv).setOnClickListener(onClickListener);
                convertView.findViewById(R.id.activityprocess_listview_item_use_tv).setOnClickListener(onClickListener);
            break;
            case 1:
              convertView=View.inflate(context, R.layout.activityprocess_listview_item_gonging, null);
                convertView.findViewById(R.id.activityprocess_listview_item_step1_tv).setOnClickListener(onClickListener);

                break;
            case 2:
               convertView=View.inflate(context, R.layout.activityprocess_listview_item_finish, null);
               TextView reward_tv=(TextView) (convertView.findViewById(R.id.activityprocess_listview_item_reward_tv));
                reward_tv.setText(StringUtils.getIndexString(R.string.reward_set));
                reward_tv.setOnClickListener(onClickListener);
                break;
            default:
              convertView=null;
        }
        return  convertView;
    }

}

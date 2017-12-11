package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.mvp.ui.fragment.EventDetailsFragment;
import com.zhongke.weiduo.mvp.ui.fragment.NotFinishActivityFragment;
import com.zhongke.weiduo.mvp.ui.widget.BaseTitleView;
import com.zhongke.weiduo.mvp.ui.widget.layout.TransparentBGTextView;
import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.DisplayUtils;
import com.zhongke.weiduo.util.Tools;

/**
 * Created by ${xingen} on 2017/6/21.
 * <p>
 * 活动详情
 */

public class EventDetailsActivity extends AppCompatActivity {
    private TransparentBGTextView habit_tv, able_tv, project_tv, hearth_tv;
    private ImageView share_iv, collection_iv;
    public static final String TYPE = "type";
    public static final String STATE = "state";
    private int type, state;
    public  static  final  String TAG=EventDetailsActivity.class.getSimpleName();
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Tools.setStatusColor(this, ColorUtils.stringToColor("#1cbf61"));
        initView();
        //acceptIntent();
    }
    private int view_x=0,view_y=0;
    public void scrollTop(){
        LogUtil.i(TAG,"activity  scrollTop() ");
        if(this.scrollView!=null){
            this.scrollView.smoothScrollTo(view_x,view_y);
        }
    }
    public void remeberScrollTop(){
        if (this.scrollView!=null){
            view_x=this.scrollView.getScrollX();
            view_y=this.scrollView.getScrollY();
            LogUtil.i(TAG,"remeber scrollTop() "+view_x+" "+view_y);
        }
    }
    private void acceptIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.type = bundle.getInt(TYPE);
            this.state = bundle.getInt(STATE);
        }
        addFragment();
    }
    private BaseTitleView titleView;
    private ScrollView scrollView;
    private ImageView bg_tv;
    /**
     * 初始化控件
     */
    private void initView() {
        habit_tv = (TransparentBGTextView) this.findViewById(R.id.event_details_habit_tv);
        able_tv = (TransparentBGTextView) this.findViewById(R.id.event_details_able_tv);
        project_tv = (TransparentBGTextView) this.findViewById(R.id.event_details_project_tv);
        hearth_tv = (TransparentBGTextView) this.findViewById(R.id.event_details_hearth_tv);
        share_iv = (ImageView) this.findViewById(R.id.event_details_share_iv);
        collection_iv = (ImageView) this.findViewById(R.id.event_details_collection_iv);
        this.titleView=(BaseTitleView) this.findViewById(R.id.event_details_top_layout);
        this.scrollView=(ScrollView) this.findViewById(R.id.event_details_habit_tv_scroollview);
        this.bg_tv=(ImageView) this.findViewById(R.id.event_details_bg_tv);
        expandableListView = (ExpandableListView) findViewById(R.id.event_detail_expandablelistview);
//        ActivityDetailExpandableAdapter expandableAdapter = new ActivityDetailExpandableAdapter(this, ActivityDetailBean.createInstance().dataList);
//        expandableListView.setAdapter(expandableAdapter);

        this.titleView.setTitleName("活动详情");

        int size[] = {DisplayUtils.dip2px(this, 10), DisplayUtils.dip2px(this, 12), DisplayUtils.dip2px(this, 10)};
        TransparentBGTextView.Message message1 =new TransparentBGTextView.Message();
        message1.content="习惯";
        message1.percentage="30%";

        TransparentBGTextView.Message message2 =new TransparentBGTextView.Message();
        message2.content="能力";
        message2.percentage="49%";

        TransparentBGTextView.Message message3 =new TransparentBGTextView.Message();
        message3.content="安全指数";
        message3.percentage="90%";

        TransparentBGTextView.Message message4 =new TransparentBGTextView.Message();
        message4.content="身体机能";
        message4.percentage="30%";
        habit_tv.addData(message1);
        able_tv.addData(message2);
        project_tv.addData(message3);
        hearth_tv.addData(message4);

        Glide.with(this).load(R.drawable.event_detail_bg1).asBitmap().fitCenter().into(bg_tv);
    }

    /**
     * 添加视图Fragment
     */
    private void addFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(EventDetailsActivity.STATE, state);
        bundle.putInt(EventDetailsActivity.TYPE, type);
        if (type == 1) {
            switch (state) {
                case 0:
                    addIndexFragment(EventDetailsFragment.newInstance(bundle), EventDetailsFragment.TAG);
                    break;
                case 1:
                    addIndexFragment(EventDetailsFragment.newInstance(bundle), EventDetailsFragment.TAG);
                    break;
                case 6://活动已经结束
                    addIndexFragment(EventDetailsFragment.newInstance(bundle), EventDetailsFragment.TAG);
                    break;
            }
        } else if (type == 2) {//纯文本
            switch (state) {
                case 0://未开始
                    addIndexFragment(EventDetailsFragment.newInstance(bundle), EventDetailsFragment.TAG);
                    break;
                case 1://进行中
                    break;
                case 2://延迟
                    addIndexFragment(NotFinishActivityFragment.newIntance(bundle), NotFinishActivityFragment.TAG);
                    break;
                case 3://取消
                    addIndexFragment(NotFinishActivityFragment.newIntance(bundle), NotFinishActivityFragment.TAG);
                    break;
                case 4://未完成
                    addIndexFragment(NotFinishActivityFragment.newIntance(bundle), NotFinishActivityFragment.TAG);
                    break;
            }
        }
    }
    /**
     * 添加指定的Fragment
     *
     * @param fragment
     * @param tag
     */
    public void addIndexFragment(Fragment fragment, String tag) {
        //getSupportFragmentManager().beginTransaction().add(R.id.event_details_content_layout, fragment, tag).commitAllowingStateLoss();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,EventDetailsActivity.class));

    }
}

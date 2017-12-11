package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.ActivityFlowBean;

import java.util.List;

/**
 * Created by hyx on 2017/10/24.
 *
 */

public class ActivityDetailExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
//    private List<ActivityDetailBean.DetailBean> data;

    /**
     * 关键行为
     */
    private static final int CHILD_TYPE_KEY_BEHAVIOUR = 0;
    /**
     * 活动资料
     */
    private static final int CHILD_TYPE_DATA = 1;
//    private static final int CHILD_TYPE_PROCESS = 2;
    /**
     * 活动奖励
     */
    private static final int CHILD_TYPE_REWARD = 3;
    /**
     * 活动道具
     */
    private static final int CHILD_TYPE_PROP = 4;
//    private static final int CHILD_TYPE_INVITE = 5;
    /**
     * 活动评论
     */
    private static final int CHILD_TYPE_COMMENT = 6;

    private List<String> groupList;
    private List<List<ActivityFlowBean.FlowDataBean>> childList;


    public ActivityDetailExpandableAdapter(Context context, List<String> groups, List<List<ActivityFlowBean.FlowDataBean>> childs) {
        this.context = context;
//        this.data = data;
//        this.behaviourList = behaviours;
        this.groupList = groups;
        this.childList = childs;

    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupHolder;
        if (convertView == null) {
            groupHolder = new GroupViewHolder();
            convertView = View.inflate(context, R.layout.item_activity_detail_group, null);
            groupHolder.init(convertView);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupViewHolder) convertView.getTag();
        }
        groupHolder.name.setText((String) getGroup(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        int type = getChildType(groupPosition, childPosition);
        switch (type) {
            case CHILD_TYPE_KEY_BEHAVIOUR:
                // 关键行为
                if(convertView == null){
                    convertView = View.inflate(context, R.layout.item_activity_detail_behaviour, null);
                    viewHolder = new BehaviorViewHolder();
                    ((BehaviorViewHolder) viewHolder).init(convertView);
                    convertView.setTag(viewHolder);
                }else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                break;
            case CHILD_TYPE_DATA:
                // 活动资料
                if(convertView == null){
                    convertView = View.inflate(context, R.layout.item_activity_detail_behaviour, null);
                    viewHolder = new InfoViewHolder();
                    ((InfoViewHolder) viewHolder).init(convertView);
                    convertView.setTag(viewHolder);
                }else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                break;
            case CHILD_TYPE_REWARD:
                // 活动奖励
                if(convertView == null){
                    convertView = View.inflate(context, R.layout.item_activity_detail_behaviour, null);
                    viewHolder = new RewardViewHolder();
                    ((RewardViewHolder) viewHolder).init(convertView);
                    convertView.setTag(viewHolder);
                }else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                break;
            case CHILD_TYPE_PROP:
                // 活动道具
                if(convertView == null){
                    convertView = View.inflate(context, R.layout.item_activity_detail_behaviour, null);
                    viewHolder = new PropViewHolder();
                    ((PropViewHolder) viewHolder).init(convertView);
                    convertView.setTag(viewHolder);
                }else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                break;
            case CHILD_TYPE_COMMENT:
                ChildCommentHolder childHolder;
                if (convertView == null) {
                    childHolder = new ChildCommentHolder();
                    convertView = View.inflate(context, R.layout.adapter_scheme_comment_item, null);

//                    childHolder.child_comment_lv = (ListViewForScrollview)
//                            convertView.findViewById(R.id.item_activity_detail_child_comment_lv);
                    convertView.setTag(childHolder);
                } else {
                    childHolder = (ChildCommentHolder) convertView.getTag();
                }

//                ActivityDeatailCommentAdapter adapter = new ActivityDeatailCommentAdapter(context);
//                childHolder.child_comment_lv.setAdapter(adapter);
//                return convertView;
//            default:
//                return null;
        }

        ActivityFlowBean.FlowDataBean bean = (ActivityFlowBean.FlowDataBean) getChild(groupPosition,childPosition);
        switch (type){
            case CHILD_TYPE_KEY_BEHAVIOUR:
                // 关键行为
                BehaviorViewHolder behaviorViewHolder = (BehaviorViewHolder) viewHolder;
                behaviorViewHolder.name.setText(bean.getBehaviorName());
                break;
            case CHILD_TYPE_DATA:
                // 活动资料
                InfoViewHolder infoViewHolder = (InfoViewHolder) viewHolder;
                infoViewHolder.name.setText(bean.getFNodeName());
                break;
            case CHILD_TYPE_REWARD:
                // 活动奖励
                RewardViewHolder rewardViewHolder = (RewardViewHolder) viewHolder;
                rewardViewHolder.name.setText(bean.getAwardName());
                break;
            case CHILD_TYPE_PROP:
                // 活动道具
                PropViewHolder propViewHolder = (PropViewHolder) viewHolder;
                propViewHolder.name.setText(bean.getToolsName());
                break;
            case CHILD_TYPE_COMMENT:
                // 活动评价
                break;
            default:
                break;
        }

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        ActivityFlowBean.FlowDataBean bean = childList.get(groupPosition).get(childPosition);
        switch (bean.getFNodeType()) {
            case "behavior":
                // 关键行为
                return CHILD_TYPE_KEY_BEHAVIOUR;
            case "award":
                // 活动奖励
                return CHILD_TYPE_REWARD;
            case "tools":
                // 活动道具
                return CHILD_TYPE_PROP;
            case "evaluate":
                // 活动评论
                return CHILD_TYPE_COMMENT;
            case "actDetail":
                // 活动资料
                return CHILD_TYPE_DATA;
            default:
                return CHILD_TYPE_DATA;
        }
    }

    @Override
    public int getChildTypeCount() {
        return 7;
    }

    private class GroupViewHolder {
        TextView name;

        public void init(View rootView) {
            name = (TextView) rootView.findViewById(R.id.item_activity_detail_title);
        }
    }


    private class ChildCommentHolder extends ViewHolder {
//        private ListViewForScrollview child_comment_lv;
//
//        public ChildCommentHolder() {
//
//        }
    }


    /**
     * childItem帮助类的超类
     */
    public abstract class ViewHolder {
    }

    /**
     * 关键行为viewHolder
     */
    private class BehaviorViewHolder extends ViewHolder {
        TextView name;

        public void init(View rootView) {
            name = (TextView) rootView.findViewById(R.id.behaviour_behaviour_name);
        }
    }

    /**
     * 活动奖励viewHolder
     */
    private class RewardViewHolder extends ViewHolder {
        TextView name;

        public void init(View rootView) {
            name = (TextView) rootView.findViewById(R.id.behaviour_behaviour_name);
        }
    }

    /**
     * 活动道具viewHolder
     */
    private class PropViewHolder extends ViewHolder {
        TextView name;

        public void init(View rootView) {
            name = (TextView) rootView.findViewById(R.id.behaviour_behaviour_name);
        }
    }

    /**
     * 活动资料viewHolder
     */
    private class InfoViewHolder extends ViewHolder {
        TextView name;

        public void init(View rootView) {
            name = (TextView) rootView.findViewById(R.id.behaviour_behaviour_name);
        }
    }


}

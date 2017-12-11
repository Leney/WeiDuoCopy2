package com.zhongke.weiduo.mvp.base;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.zhongke.weiduo.app.common.SearchTypeEnum;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.StringUtil;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by  karma on 2017/06/19.
 * 类描述：adapter基类
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    private static final String TAG = "AbstractAdapter";
    protected Context mContext;
    protected BaseActivity activity;
    protected FragmentActivity fragmentActivity;
    protected LinkedList<T> datalist;

    public BaseAdapter(Context mContext, BaseActivity activity) {
        this.mContext = mContext;
        this.activity = activity;
        this.datalist = new LinkedList<T>();
    }

    public BaseAdapter(Context mContext) {
        this.mContext = mContext;
        this.datalist = new LinkedList<T>();
    }

    public BaseAdapter(Context mContext, FragmentActivity activity) {
        this.mContext = mContext;
        this.fragmentActivity = activity;
        this.datalist = new LinkedList<T>();
    }

    @Override
    public int getCount() {
        if (datalist.size() > 0) {
            return datalist.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected String getStringValue(String value) {
        if (StringUtil.isNull(value)) {
            return "";
        }
        return value;
    }

    /**
     * 添加或替换原有的数据
     *
     * @param data
     * @param maxLoadSize
     * @param cacheSize
     */
    protected void addOrReplaceData(List<T> data, SearchTypeEnum searchType,
                                    int maxLoadSize, int cacheSize) {
        LogUtil.e(TAG, "<<<<<<<<<<<<<<<<< addOrReplaceData "
                + (data == null ? 0 : data.size()) + "   cacheSize = "
                + cacheSize + "   maxLoadSize = " + maxLoadSize);

        if (data == null) {
            return;
        }
        LogUtil.e(TAG, "######<<<<<<<<<<<<<<<<<< searchType == " + searchType.getMessage());

        if (searchType == SearchTypeEnum.NEW) { // 上拉
            clearDataList();
            addItemTop(data, cacheSize);
        } else if (searchType == SearchTypeEnum.OLD) { //加载更多
            addItemLast(data, cacheSize);
        } else { // 初始化
            replaceAllItem(data);
        }
    }

    public abstract void addOrReplaceData(List<T> data, SearchTypeEnum searchType);

    public void replaceAllItem(List<T> data) {
        datalist.clear();
        if (data == null) {
            return;
        }

        datalist.addAll(data);
    }

    /**
     * 添加数据到顶部
     *
     * @param data
     * @param cacheSize
     */
    public void addItemTop(List<T> data, int cacheSize) {

        LogUtil.e(TAG, "<<<<<<<<<<<<<<<<< addItemTop "
                + (data == null ? 0 : data.size()) + "   cacheSize = "
                + cacheSize);
        if (data == null) {
            return;
        }

        for (int i = data.size() - 1; i >= 0; i--) {
            datalist.addFirst(data.get(i));
        }
    }

    /**
     * 添加数据到底部
     *
     * @param data
     * @param cacheSize
     */
    public void addItemLast(List<T> data, int cacheSize) {
        LogUtil.e(TAG, "<<<<<<<<<<<<<<<<< addItemLast "
                + (data == null ? 0 : data.size()) + "   cacheSize = "
                + cacheSize);
        if (data == null) {
            return;
        }

        datalist.addAll(data);
    }

    public List getDataList() {
        return datalist;
    }

    public void clearDataList() {
        datalist.clear();
    }

}

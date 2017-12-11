package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;
import com.zhongke.weiduo.mvp.ui.adapter.MyFamilyListAdapter;
import com.zhongke.weiduo.util.DisplayUtils;

import java.util.List;

/**
 * Created by hyx on 2017/9/26.
 */

public class MyFamilyListDialog extends Dialog implements AdapterView.OnItemClickListener {

    private Context mContext;
    private List<ContactsListBean> list;
    private ListView myFamilyLv;
    private MyFamilyListAdapter adapter;
    private OnMyFamilyItemClickListener listener;

    public MyFamilyListDialog(Context context, List<ContactsListBean> list, OnMyFamilyItemClickListener listener) {
        this(context,0);
        this.mContext = context;
        this.list = list;
        this.listener = listener;
    }

    public MyFamilyListDialog(Context context, int themeResID) {
        super(context, R.style.dialog_no_title_style);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext, R.layout.dialog_my_family_list, null);
        setContentView(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.height = DisplayUtils.dip2px(mContext, 380);
        lp.width = DisplayUtils.dip2px(mContext, 280);
        win.setAttributes(lp);
        myFamilyLv = (ListView) view.findViewById(R.id.my_family_list);
        adapter = new MyFamilyListAdapter(mContext, list);
        myFamilyLv.setAdapter(adapter);
        myFamilyLv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listener.ClickItem(list.get(position).getId() + "", list.get(position).getNickName());
    }

    public interface OnMyFamilyItemClickListener {
        void ClickItem(String id, String name);
    }
}

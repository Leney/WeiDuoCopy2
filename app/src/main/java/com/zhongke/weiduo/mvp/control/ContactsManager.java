package com.zhongke.weiduo.mvp.control;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.im.IMClient;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.library.retrofit.RetrofitClient;
import com.zhongke.weiduo.library.rxjava.ObservableUtils;
import com.zhongke.weiduo.library.rxjava.SubscribeUtils;
import com.zhongke.weiduo.mvp.db.local.ContactsDBManager;
import com.zhongke.weiduo.mvp.model.entity.ContactsBean;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;
import com.zhongke.weiduo.mvp.model.entity.LoginResult;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 联系人管理类，好友、友好家庭、我的家庭、群组列表
 * Created by llj on 2017/11/15.
 */

public class ContactsManager {
    private static ContactsManager instance;

    /**
     * 初始化完成的监听器列表
     */
    private List<OnContactsInitFinishListener> mInitListeners;

    /**
     * 数据发生变化的监听器列表
     */
    private List<OnContactsChangedListener> mChangedListeners;

    /**
     * 群组id(包括我的家庭、友好家庭、群组的id)
     */
    private List<String> groupIdList;

    private ContactsManager() {
        mInitListeners = new ArrayList<>();
        mChangedListeners = new ArrayList<>();
        groupIdList = new ArrayList<>();
    }

    public static ContactsManager getInstance() {
        if (instance == null) {
            synchronized (ContactsManager.class) {
                if (instance == null) {
                    instance = new ContactsManager();
                }
            }
        }
        return instance;
    }

    /**
     * 注册联系人数据初始化完成的监听
     *
     * @param listener
     */
    public void registerContactsInitFinishedListener(OnContactsInitFinishListener listener) {
        if (listener == null) return;
        mInitListeners.add(listener);
    }

    /**
     * 取消监听联系人数据发生改变的广播
     *
     * @param listener
     */
    public void unregisterContactsInitFinishedListener(OnContactsInitFinishListener listener) {
        if (listener == null) return;
        mInitListeners.remove(listener);
    }

    /**
     * 注册联系人信息发生改变的监听
     *
     * @param listener
     */
    public void registerContactsChangedListener(OnContactsChangedListener listener) {
        if (listener == null) return;
        mChangedListeners.add(listener);
    }

    /**
     * 取消注册联系人发生改变的监听
     *
     * @param listener
     */
    public void unregisterContactsChangedListener(OnContactsChangedListener listener) {
        if (listener == null) return;
        mChangedListeners.remove(listener);
    }


    /**
     * 网络获取联系人列表
     */
    public void getContactsListFromNetwork(boolean isInitIM) {
//        String token = ZkApplication.getInstance().getToken();
//        if (token.isEmpty()) {
//            return;
//        }
//        token = "";
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        retrofitClient.getContactsList(new ResponseResultListener<ContactsBean>() {
            @Override
            public void success(ContactsBean contactsBean) {
                saveContactsList(contactsBean, isInitIM);
            }

            @Override
            public void failure(CommonException e) {
            }
        });
    }


    /**
     * 重组请求回来的联系人列表并插入到数据库
     *
     * @param bean
     * @param isInitIM 是否需要初始化IM即时通讯
     */
    private void saveContactsList(final ContactsBean bean, boolean isInitIM) {

        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                List<ContactsListBean> list = new ArrayList<>();
                // 好友
                int length = bean.getFriendList().size();
                for (int i = 0; i < length; i++) {
                    ContactsBean.FriendListBean friendListBean = bean.getFriendList().get(i);
                    ContactsListBean contactsListBean = new ContactsListBean();
                    contactsListBean.type = ContactsListBean.TYPE_FRIEND_PERSON;
                    contactsListBean.id = friendListBean.getFriendId();
                    contactsListBean.createTime = friendListBean.getCreateTime();
                    contactsListBean.headUrl = friendListBean.getHeadImageUrl();
                    String friendNickName = friendListBean.getFriendNickName();
                    String nickName = friendListBean.getNickName();
                    String userName = friendListBean.getUserName();
                    if (friendNickName != null && nickName != null && userName != null) {

                        contactsListBean.nickName = !friendNickName.isEmpty() ? friendNickName : !nickName.isEmpty() ? nickName : !userName.isEmpty() ? userName : "";
                    }
                    list.add(contactsListBean);
                }

                // 友好家庭
                int length2 = bean.getFriendFamilyList().size();
                for (int i = 0; i < length2; i++) {
                    ContactsBean.FriendFamilyListBean friendFamilyListBean = bean.getFriendFamilyList().get(i);
                    ContactsListBean contactsListBean = new ContactsListBean();
                    contactsListBean.type = ContactsListBean.TYPE_FRIEND_FAMILY;
                    contactsListBean.id = friendFamilyListBean.getFriendGroupId();
                    contactsListBean.createTime = friendFamilyListBean.getCreateTime();
                    contactsListBean.headUrl = friendFamilyListBean.getGIconUrl();
                    contactsListBean.coverUrl = friendFamilyListBean.getGCoverUrl();
                    contactsListBean.info = friendFamilyListBean.getGInfo();
                    contactsListBean.createUserId = friendFamilyListBean.getCreateUserId();
                    String friendNickName = friendFamilyListBean.getFriendNickName();
                    String nickName = friendFamilyListBean.getGroupNickName();
                    String userName = friendFamilyListBean.getGName();
                    contactsListBean.nickName = !friendNickName.isEmpty() ? friendNickName : !nickName.isEmpty() ? nickName : !userName.isEmpty() ? userName : "";
                    list.add(contactsListBean);
                    // 添加id到群组id集合
                    groupIdList.add(String.valueOf(contactsListBean.id));
                }

                // 群组
                int length3 = bean.getGroupList().size();
                for (int i = 0; i < length3; i++) {
                    ContactsBean.GroupListBean groupListBean = bean.getGroupList().get(i);
                    ContactsListBean contactsListBean = new ContactsListBean();
                    contactsListBean.type = ContactsListBean.TYPE_GROUP;
                    contactsListBean.id = groupListBean.getGroupId();
                    contactsListBean.createTime = groupListBean.getCreateTime();
                    contactsListBean.headUrl = (String) groupListBean.getGIconUrl();
                    contactsListBean.coverUrl = (String) groupListBean.getGCoverUrl();
                    contactsListBean.info = groupListBean.getGInfo();
                    contactsListBean.createUserId = groupListBean.getCreateUserId();

//                    String friendNickName = groupListBean.getNickName();
//                    String userName = groupListBean.getGName();
                    contactsListBean.nickName = groupListBean.getGName();
                    list.add(contactsListBean);
                    // 添加id到群组id集合
                    groupIdList.add(String.valueOf(contactsListBean.id));
                }

                // 设备
                int length4 = bean.getDeviceList().size();
                for (int i = 0; i < length4; i++) {
                    ContactsBean.DeviceListBean deviceListBean = bean.getDeviceList().get(i);
                    ContactsListBean contactsListBean = new ContactsListBean();
                    contactsListBean.type = ContactsListBean.TYPE_DEVICE;
                    contactsListBean.id = deviceListBean.getId();
                    String sex = deviceListBean.getSex() == 1 ? "儿子" : "女儿";
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(deviceListBean.getNickName());
                    stringBuilder.append(sex);
                    stringBuilder.append("[");
                    stringBuilder.append(deviceListBean.getDeviceCode());
                    stringBuilder.append("]");
                    contactsListBean.nickName = stringBuilder.toString();
                    contactsListBean.headUrl = deviceListBean.getHeadImageUrl();
                    contactsListBean.info = deviceListBean.getUserName();
                    list.add(contactsListBean);
                }

                // 我的家庭
                int length5 = bean.getMyFamilyList().size();
                for (int i = 0; i < length5; i++) {
                    ContactsBean.MyFamilyListBean myFamily = bean.getMyFamilyList().get(i);
                    ContactsListBean contactsListBean = new ContactsListBean();
                    contactsListBean.type = ContactsListBean.TYPE_MY_FAMILY;
                    contactsListBean.id = myFamily.getId();
                    contactsListBean.nickName = myFamily.getGName();
                    contactsListBean.createTime = myFamily.getCreateTime();
                    contactsListBean.headUrl = (String) myFamily.getGIconUrl();
                    contactsListBean.coverUrl = (String) myFamily.getGCoverUrl();
                    contactsListBean.info = myFamily.getGInfo();
                    contactsListBean.createUserId = myFamily.getCreateUserId();
                    list.add(contactsListBean);
                    // 添加id到群组id集合
                    groupIdList.add(String.valueOf(contactsListBean.id));
                }


//                ContactsBean.MyFamilyBean myFamily = bean.getMyFamily();
//                if (myFamily != null) {
//                    ContactsListBean contactsListBean = new ContactsListBean();
//                    contactsListBean.type = ContactsListBean.TYPE_MY_FAMILY;
//                    contactsListBean.id = myFamily.getId();
//                    contactsListBean.nickName = myFamily.getGName();
//                    contactsListBean.createTime = myFamily.getCreateTime();
//                    contactsListBean.headUrl = (String) myFamily.getGIconUrl();
//                    contactsListBean.coverUrl = (String) myFamily.getGCoverUrl();
//                    contactsListBean.info = myFamily.getGInfo();
//                    contactsListBean.createUserId = myFamily.getCreateUserId();
//                    list.add(contactsListBean);
//                    // 添加id到群组id集合
//                    groupIdList.add(String.valueOf(contactsListBean.id));
//                }

                // 删除全部数据
                ContactsDBManager.getInstance().deleteAll();
                // 插入全部数据
                ContactsDBManager.getInstance().insertAll(list);
                subscriber.onNext(true);
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        callBackInitListeners();
                        if (isInitIM) {
                            // 需要初始化即时通讯
                            // 初始化即时通讯聊天相关
                            initIM();
                        } else {
                            // 不需要初始化的时候去再去加入群组
                            IMClient.joinGroups(groupIdList);
                        }
                    }
                });
    }


    /**
     * 初始化即时通讯、聊天相关
     */
    private void initIM() {
        SubscribeUtils.toSubscribe(ObservableUtils.createQueryAccountMsg(), new Subscriber<LoginResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LoginResult loginResult) {
                LoginResult.SysUserBean bean = loginResult.getSysUser();
                if (bean != null) {
                    // 有用户登录信息
                    LogUtil.i("llj", "有用户登录信息，初始化聊天相关！！！，groupList.size()-------->>>" + groupIdList.size());
                    IMClient.getInstance().initSdk(bean, groupIdList);
                }
            }
        });
    }


    /**
     * 根据类型查询出联系人列表
     *
     * @param type
     * @param subscriber
     */
    public void queryContactListByType(int type, Action1<List<ContactsListBean>> subscriber) {
        Observable.create(new Observable.OnSubscribe<List<ContactsListBean>>() {
            @Override
            public void call(Subscriber<? super List<ContactsListBean>> subscriber) {
                subscriber.onNext(ContactsDBManager.getInstance().queryContactListByType(type));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

//    /**
//     * 通过类型和联系人id查找出联系人相关信息
//     *
//     * @param type
//     * @param id
//     * @param subscriber
//     */
//    public void queryContactsByTypeAndId(int type, int id, Action1<ContactsListBean> subscriber) {
//        Observable.create(new Observable.OnSubscribe<ContactsListBean>() {
//            @Override
//            public void call(Subscriber<? super ContactsListBean> subscriber) {
//                subscriber.onNext(ContactsDBManager.getInstance().queryContactBeanByType(type, id));
//            }
//        }).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

//    /**
//     * 通过群组、我的家庭、友好家庭id查找出联系人相关信息
//     * @param id 群组、我的家庭、家庭id(其实都是群组，id不会重复)
//     * @param subscriber
//     */
//    public void queryGroupContactsByIdFromAll(int id, Action1<ContactsListBean> subscriber) {
//        Observable.create(new Observable.OnSubscribe<ContactsListBean>() {
//            @Override
//            public void call(Subscriber<? super ContactsListBean> subscriber) {
//                subscriber.onNext(ContactsDBManager.getInstance().queryGroupContactsBeanByIdFromAll(id));
//            }
//        }).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

    /**
     * 插入一条新的联系人信息
     *
     * @param bean
     */
    public void insertContactList(ContactsListBean bean) {
        if (bean == null) return;
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(ContactsDBManager.getInstance().insert(bean));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (!aBoolean) return;
                        callBackChangedListeners(bean.type, bean.id);
                    }
                });
    }

    /**
     * 更新一条新的联系人信息
     *
     * @param bean
     */
    public void updateOneContacts(ContactsListBean bean) {
        if (bean == null) return;
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(ContactsDBManager.getInstance().update(bean));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (!aBoolean) return;
                        callBackChangedListeners(bean.type, bean.id);
                    }
                });
    }

    /**
     * 删除一条新的联系人信息
     *
     * @param type 联系人类型
     * @param id   联系人id
     */
    public void deleteOneContacts(int type, int id) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(ContactsDBManager.getInstance().delete(type, id));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (!aBoolean) return;
                        callBackChangedListeners(type, id);
                    }
                });
    }

    /**
     * 清除联系人信息
     */
    public void clear() {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                ContactsDBManager.getInstance().deleteAll();
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe();
    }


    /**
     * 回调所有的初始化监听
     */
    private void callBackInitListeners() {
        int length = mInitListeners.size();
        for (int i = 0; i < length; i++) {
            mInitListeners.get(i).onContactsInitFinishListener();
        }
    }

    /**
     * 回调所有的数据改变的监听
     *
     * @param type 联系人类型
     * @param id   联系人id
     */
    private void callBackChangedListeners(int type, int id) {
        int length = mChangedListeners.size();
        for (int i = 0; i < length; i++) {
            mChangedListeners.get(i).onContactsChanged(type, id);
        }
    }


    /**
     * 联系人列表数据初始化完成监听
     */
    public interface OnContactsInitFinishListener {
        void onContactsInitFinishListener();
    }

    /**
     * 联系人数据发生改变
     */
    public interface OnContactsChangedListener {
        void onContactsChanged(int type, int id);
    }

}

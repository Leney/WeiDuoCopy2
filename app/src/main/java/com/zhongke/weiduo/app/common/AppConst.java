package com.zhongke.weiduo.app.common;

import com.lqr.emoji.LQREmotionKit;
import com.zhongke.weiduo.app.utils.FileUtils;
import com.zhongke.weiduo.mvp.model.entity.LoginModelBean;
import com.zhongke.weiduo.mvp.model.entity.UserInfoBean;
import com.zk.ZkRetCode;

/**
 * Created by Karma on 2017/6/26.
 * 类描述：全局变量
 */

public class AppConst {
    //语音存放位置
    public static final String AUDIO_SAVE_DIR = FileUtils.getDir("audio");
    public static final int DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND = 60;
    //视频存放位置
    public static final String VIDEO_SAVE_DIR = FileUtils.getDir("video");
    //照片存放位置
    public static final String PHOTO_SAVE_DIR = FileUtils.getDir("photo");
    //头像保存位置
    public static final String HEADER_SAVE_DIR = FileUtils.getDir("header");

    public static final int SYSTEM_UI_FLAG_HIDE_NAVIGATION = 0x00000002;  //隐藏导航栏
    public static final int SYSTEM_UI_FLAG_FULLSCREEN = 0x00000004;  //字面意思是全屏显示，实际是状态栏会被隐藏而导航栏未作处理
    public static final int SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION = 0x00000200;  //导航栏不会被隐藏但布局会扩展到导航栏所在位置
    public static final int SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN = 0x00000400;  //状态栏不会被隐藏但布局会扩展到状态栏所在位置
    public static final int SYSTEM_UI_FLAG_IMMERSIVE = 0x00000800;  //配合SYSTEM_UI_FLAG_HIDE_NAVIGATION使用，如果只有
    //SYSTEM_UI_FLAG_HIDE_NAVIGATION而不设置SYSTEM_UI_FLAG_IMMERSIVE，那么用户交互后会自动清除SYSTEM_UI_FLAG_HIDE_NAVIGATION这个flag；
    public static final int SYSTEM_UI_FLAG_IMMERSIVE_STICKY = 0x00001000; // 配合SYSTEM_UI_FLAG_HIDE_NAVIGATION和（或）SYSTEM_UI_FLAG_FULLSCREEN使用，
    //设置这个flag之后，用户在屏幕顶部下滑或者在底部上滑调出状态栏、导航栏之后它们仍会自动隐藏；

    public static boolean isFirst = false;

    public static final int IMAGE_TYPE = 10;
    public static final int GIF_TYPE = 11;

    public static final int SESSION_SEND = 0;//代表发送
    public static final int SESSION_RECEIVE = 1;//代表接收



    public static final int SENDING = 5;
    public static final int FAILED = ZkRetCode.SEND_MSG_FAILED;
    public static final int SENT = ZkRetCode.SEND_MSG_SECCUSS;

    public static final String TEST_ENDPOINT = "http://oss-cn-shenzhen.aliyuncs.com/";
    public static final String ACCES_KEYID = "LTAItE8B6L5hKhiC ";
    public static final String ACCES_KEYSECRET = "3jPrS2SEQPdaMwcVcSQW8YB7nwi5FB";
    public static final String TEST_BUCKET = "zhongke";
    public static final String DOWND_PHOTO = "http://zhongke.oss-cn-shenzhen.aliyuncs.com/";
    public static final String STICKER_PATH = LQREmotionKit.getStickerPath();

    public static final String TEST_PUSH_URL = "rtmp://pe260adcf.live.126.net/live/84b2b2cd9135412892f13243f0380388?wsSecret=48d43f33804d10eab07406fa33359bee&wsTime=1501153079";
    public static final String TEST_PULL_URL = "rtmp://ve260adcf.live.126.net/live/84b2b2cd9135412892f13243f0380388";

    public final static String roomid = "roomid";
    public final static String meetingName = "meetingName";
    public final static String orientation = "orientation";
    /**
     * 聊天室类型， -1表示未开始直播；1，音频直播；2，视频直播
     */
    public final static String type = "type";
    /**
     * 连麦请求类型，请求语音连麦或者视频连麦。
     */
    public final static String style = "style";
    /**
     * 控制命令, 指令类型参考
     */
    public final static String command = "command";
    /**
     * 连麦状态，指令类型参考
     */
    public final static String state = "state";

    /**
     * 其他信息
     **/
    public final static String info = "info";
    public final static String nick = "nick";
    public final static String avatar = "avatar";
    /**
     * 登录成功后接口返回的登录信息对象
     */
    public static LoginModelBean loginModelBean;

    public static LoginModelBean getLoginModelBean() {
        return loginModelBean;
    }

    public static void setLoginModelBean(LoginModelBean loginModelBean) {
        AppConst.loginModelBean = loginModelBean;
    }

    /**
     * 从服务器获取到的用户信息对象
     */
    public static UserInfoBean userInfoBean;

    public static UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public static void setUserInfoBean(UserInfoBean userInfoBean) {
        AppConst.userInfoBean = userInfoBean;
    }


    /**
     * 消息属于别人的
     */
    public static final int type_other = 1;
    /**
     * 消息属于自己的
     */
    public static final int type_own = 2;

    /**
     * 当前正在聊天的标志，可能是一个人，可能是一个群。
     */
    public static final int chat_one = 1;
    public static final int chat_group = 2;
    public static final int chat_system = 3;
    public static final int chat_add_friend = 4;
}

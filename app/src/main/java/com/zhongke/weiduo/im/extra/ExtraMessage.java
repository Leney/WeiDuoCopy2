package com.zhongke.weiduo.im.extra;

/**
 * Created by ${xingen} on 2017/12/8.
 */

public class ExtraMessage<T> {
    public int code;
    public T message;
    /**
     * 活动开始
     */
    public static final  int activity_start=100;
    /**
     * 抢答结果
     */
    public static final  int question_result=101;
    /**
     * 题目抢答
     */
    public static final  int qustion_scramble=102;
    /**
     * 许愿通知，许愿绑定活动通知，奖励通知
     */
    public static final int  wish_bind_activiy=103;
    /**
     * 加入活动
     */
    public static final int join_activity=104;

}

package com.zhongke.weiduo.im.extra.message;

/**
 * Created by ${xingen} on 2017/12/8.
 * <p>
 * 一个题目的答题结果
 */

public class QuestionResult {
    /**
     * 答题人员的Id
     */
    public int userId;
    /**
     * 抢答人员当前的积分
     */
    public int userCurScroe;
    /**
     * 当前的题目Id
     */
    public int currentQuestionId;
    /**
     * 下一题执行的题目的Id
     */
    public int nextQuestionId;
    /**
     * 抢答人员的答案，答案的id
     * (-1=答题超时)
     */
    public int answerResult = -1;
    /**
     * 正确答案的id,（0,3）
     */
    public int rightResult;

    /**
     * 活动Id
     */
    public String activityId;
    /**
     * 开始下一题的倒计时 ，值为0 没下一题，抢答活动已经结束
     */
    public int coutdownTime;
}

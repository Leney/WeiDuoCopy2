package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AnswerContract;
import com.zhongke.weiduo.mvp.model.entity.JoinMember;
import com.zhongke.weiduo.mvp.model.entity.Paper;

/**
 * Created by llj on 2017/8/31.
 */

public class AnswerPresenter extends BasePresenter {
    private static final String TAG = "AnswerPresenter";
    private AnswerContract mContract;

    public AnswerPresenter(AnswerContract contract) {
        this.mContract = contract;
    }

    /**
     * 获取试卷信息(题库)
     */
    public void getTopicList(int actionId) {
//        // 测试数据
//        List<TopicBean> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            TopicBean topicBean = new TopicBean();
//            topicBean.setTitle(i + ". 李白《下终南山》“长歌吟松风，曲尽河星稀”中，“松风”指的是？");
//            String[] options = {"A.答案选项(" + i + ")", "B.答案选项(" + i + ")", "C.答案选项(" + i + ")", "D.答案选项(" + i + ")"};
//            topicBean.setOptions(options);
//            topicBean.setRightPosition(i % 4);
//            list.add(topicBean);
//        }
//        mContract.getTopicListSuccess(list);


        retrofitClient.getPaper(actionId, new ResponseResultListener<Paper>() {
            @Override
            public void success(Paper paper) {
                mContract.getTopicListSuccess(paper);
            }

            @Override
            public void failure(CommonException e) {
                mContract.getTopicListFailed(e);
            }
        });
    }

    /**
     * 获取参与人员列表(包含观战人员)
     *
     * @param actionId
     */
    public void getMembersList(int actionId) {
        retrofitClient.getJoinMemberList(actionId, new ResponseResultListener<JoinMember>() {
            @Override
            public void success(JoinMember member) {
                mContract.getJoinMemberSuccess(member);
            }

            @Override
            public void failure(CommonException e) {
                mContract.getJoinMemberFailed(e);
            }
        });
    }
}

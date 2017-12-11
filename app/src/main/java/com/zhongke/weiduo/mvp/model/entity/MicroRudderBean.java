package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by ${xingen} on 2017/6/26.
 *  首页的微舵的实体
 */

public class MicroRudderBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 宝贝任务
         * taskCount : 3
         * taskList : [{"id":12021,"title":"爬山 ---培养:运动能力观察能力","managerUserId":1001,"safety":3,"import":0,"type":0,"state":5,"address":"深圳南山区","start":65594664666,"end":65594905566,"judgeType":0,"userCount":200,"curCount":182,"onlyOneName":"难得糊涂","isComment":0,"comment":"此次活动表现很棒，下次继续加油！","totalUser":20,"giftUserList":["http://www.1dfdfdf.jpg","http://www.2dfdfdf.jpg","http://www.3dfdfdf.jpg"]}]
         */

        private String name;
        private int taskCount;
        private List<TaskListBean> taskList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTaskCount() {
            return taskCount;
        }

        public void setTaskCount(int taskCount) {
            this.taskCount = taskCount;
        }

        public List<TaskListBean> getTaskList() {
            return taskList;
        }

        public void setTaskList(List<TaskListBean> taskList) {
            this.taskList = taskList;
        }

        public static class TaskListBean {
            /**
             * id : 12021
             * title : 爬山 ---培养:运动能力观察能力
             * managerUserId : 1001
             * safety : 3
             * import : 0
             * type : 0
             * state : 5
             * address : 深圳南山区
             * start : 65594664666
             * end : 65594905566
             * judgeType : 0
             * userCount : 200
             * curCount : 182
             * onlyOneName : 难得糊涂
             * isComment : 0
             * comment : 此次活动表现很棒，下次继续加油！
             * totalUser : 20
             * giftUserList : ["http://www.1dfdfdf.jpg","http://www.2dfdfdf.jpg","http://www.3dfdfdf.jpg"]
             */

            private int id;
            private String title;
            private int managerUserId;
            private int safety;

            private int isImport;
            private int type;
            private int state;
            private String address;
            private long start;
            private long end;
            private int judgeType;
            private int userCount;
            private int curCount;
            private String onlyOneName;
            private int isComment;
            private String comment;
            private int totalUser;
            private List<String> giftUserList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getManagerUserId() {
                return managerUserId;
            }

            public void setManagerUserId(int managerUserId) {
                this.managerUserId = managerUserId;
            }

            public int getSafety() {
                return safety;
            }

            public void setSafety(int safety) {
                this.safety = safety;
            }

            public int getIsImport() {
                return isImport;
            }

            public void setIsImport(int isImport) {
                this.isImport = isImport;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public long getStart() {
                return start;
            }

            public void setStart(long start) {
                this.start = start;
            }

            public long getEnd() {
                return end;
            }

            public void setEnd(long end) {
                this.end = end;
            }

            public int getJudgeType() {
                return judgeType;
            }

            public void setJudgeType(int judgeType) {
                this.judgeType = judgeType;
            }

            public int getUserCount() {
                return userCount;
            }

            public void setUserCount(int userCount) {
                this.userCount = userCount;
            }

            public int getCurCount() {
                return curCount;
            }

            public void setCurCount(int curCount) {
                this.curCount = curCount;
            }

            public String getOnlyOneName() {
                return onlyOneName;
            }

            public void setOnlyOneName(String onlyOneName) {
                this.onlyOneName = onlyOneName;
            }

            public int getIsComment() {
                return isComment;
            }

            public void setIsComment(int isComment) {
                this.isComment = isComment;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public int getTotalUser() {
                return totalUser;
            }

            public void setTotalUser(int totalUser) {
                this.totalUser = totalUser;
            }

            public List<String> getGiftUserList() {
                return giftUserList;
            }

            public void setGiftUserList(List<String> giftUserList) {
                this.giftUserList = giftUserList;
            }
        }
    }
}

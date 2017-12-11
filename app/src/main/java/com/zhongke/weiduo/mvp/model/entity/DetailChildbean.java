package com.zhongke.weiduo.mvp.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyx on 2017/10/26.
 */

public class DetailChildbean {

    List<DetailChildbean.Comment> commentList = new ArrayList<>();

    public DetailChildbean() {

    }



    public static class Behaviour extends DetailChildbean {
        public String behaviourName;

        @Override
        public String toString() {
            return "Behaviour{" +
                    "behaviourName='" + behaviourName + '\'' +
                    '}';
        }
    }

    public static class Data extends DetailChildbean{
        public String activityTime;
        public String activityAddress;
        public int activityCount;
    }


    public static class Process extends DetailChildbean {

    }

    public static class Reward extends DetailChildbean{
        public String rewardName;

        @Override
        public String toString() {
            return "Reward{" +
                    "rewardName='" + rewardName + '\'' +
                    '}';
        }
    }

    public static class Props extends DetailChildbean{
        public String propsName;

        @Override
        public String toString() {
            return "Props{" +
                    "propsName='" + propsName + '\'' +
                    '}';
        }
    }

    public static class Invite extends DetailChildbean {

    }

    public static class Comment extends DetailChildbean {

    }
}

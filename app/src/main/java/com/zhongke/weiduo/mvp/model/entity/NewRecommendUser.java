package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by dgg1 on 2017/11/18.
 * 推荐的好友实体
 */

public class NewRecommendUser {

    /**
     * pageTotal : 2
     * pageIndex : 1
     * records : [{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":1,"userName":"admin"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":2,"userName":"zadmin"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":87,"userName":"szxx"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":88,"userName":"cxx"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/PictureFile/8cc405d996aa4a8b8196b0899ddbb14e/1510733658085.jpg","fullName":"das","id":90,"userName":"testAdmin"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":91,"userName":"小可爱"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":92,"userName":"cxxun"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":93,"userName":"cxixun"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":99,"userName":"xiaohui"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":101,"userName":"qq音乐"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":102,"userName":"刘老师"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":103,"userName":"网络"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":104,"userName":"网盘"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":105,"userName":"暴风"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":106,"userName":"ting"},{"headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","fullName":null,"id":107,"userName":"搜狗"}]
     * recordTotal : 26
     */

    private int pageTotal;
    private int pageIndex;
    private int recordTotal;
    private List<RecordsBean> records;

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * headImageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png
         * fullName : null
         * id : 1
         * userName : admin
         */

        private String headImageUrl;
        private Object fullName;
        private int id;
        private String userName;

        public String getHeadImageUrl() {
            return headImageUrl;
        }

        public void setHeadImageUrl(String headImageUrl) {
            this.headImageUrl = headImageUrl;
        }

        public Object getFullName() {
            return fullName;
        }

        public void setFullName(Object fullName) {
            this.fullName = fullName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}

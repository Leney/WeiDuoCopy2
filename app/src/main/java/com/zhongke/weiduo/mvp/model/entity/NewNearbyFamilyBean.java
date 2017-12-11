package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by dgg1 on 2017/11/17.
 * 附近的家庭列表
 */

public class NewNearbyFamilyBean {

    /**
     * pageTotal : 2
     * pageIndex : 1
     * records : [{"gIconUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/jnWfYAMxE7TrWpxeQHFr3XaNN1508729362137.jpg","gName":"gfdfsd","createUserId":1,"gCoverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Sef36t5EfDm6rCcNEzN3Ckpe61508729364445.png","id":58},{"gIconUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Bs8GdwApzQ3J55ZdTTYYFwGYc1508729631619.gif","gName":"画画家庭","createUserId":90,"gCoverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/npi2PXK43r2KX4n8Cbf8zaDZW1508729639264.bmp","id":64},{"gIconUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Bs8GdwApzQ3J55ZdTTYYFwGYc1508729631619.gif","gName":"晴明家庭","createUserId":90,"gCoverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/npi2PXK43r2KX4n8Cbf8zaDZW1508729639264.bmp","id":68},{"gIconUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Bs8GdwApzQ3J55ZdTTYYFwGYc1508729631619.gif","gName":"圆圆家庭","createUserId":90,"gCoverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/npi2PXK43r2KX4n8Cbf8zaDZW1508729639264.bmp","id":69},{"gIconUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/CzSX8CRnirjHRZ7tWmN8H2Par1508981630003.png","gName":"哈哈家庭","createUserId":1,"gCoverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/PdYe2z4dpckxsPCMQXfWyA8ct1508981633548.jpg","id":73},{"gIconUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/3QGbSAxXWXKdacEpWMKPQHbxc1509698185392.gif","gName":"南瓜","createUserId":90,"gCoverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/b634RpcSEFfXGfnTG8bsiQYP71509698190826.gif","id":74},{"gIconUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/HPG3xWX7MxWwGMJtyKirXss7P1509791168940.png","gName":"好妈妈","createUserId":90,"gCoverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/ka3Q8wZdXNwRA5R2ddD2mDcKY1509791172478.bmp","id":75},{"gIconUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/S5BchB2mX2G4ZMnGErhiefeXD1510715681014.jpg","gName":"大叔的家庭","createUserId":1,"gCoverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/zEZ2ZksWwFmy6p36wj8dat85x1510715704554.jpg","id":76},{"gIconUrl":null,"gName":"null的家庭","createUserId":1,"gCoverUrl":null,"id":77},{"gIconUrl":null,"gName":"null的家庭","createUserId":1,"gCoverUrl":null,"id":78},{"gIconUrl":null,"gName":"null的家庭","createUserId":1,"gCoverUrl":null,"id":79},{"gIconUrl":null,"gName":"null的家庭","createUserId":1,"gCoverUrl":null,"id":80},{"gIconUrl":null,"gName":"null的家庭","createUserId":1,"gCoverUrl":null,"id":81},{"gIconUrl":null,"gName":"null的家庭","createUserId":90,"gCoverUrl":null,"id":82},{"gIconUrl":null,"gName":"null的家庭","createUserId":1,"gCoverUrl":null,"id":83},{"gIconUrl":null,"gName":"null的家庭","createUserId":1,"gCoverUrl":null,"id":84}]
     * recordTotal : 19
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
         * gIconUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/jnWfYAMxE7TrWpxeQHFr3XaNN1508729362137.jpg
         * gName : gfdfsd
         * createUserId : 1
         * gCoverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Sef36t5EfDm6rCcNEzN3Ckpe61508729364445.png
         * id : 58
         */

        private String gIconUrl;
        private String gName;
        private int createUserId;
        private String gCoverUrl;
        private int id;

        public String getGIconUrl() {
            return gIconUrl;
        }

        public void setGIconUrl(String gIconUrl) {
            this.gIconUrl = gIconUrl;
        }

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getGCoverUrl() {
            return gCoverUrl;
        }

        public void setGCoverUrl(String gCoverUrl) {
            this.gCoverUrl = gCoverUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

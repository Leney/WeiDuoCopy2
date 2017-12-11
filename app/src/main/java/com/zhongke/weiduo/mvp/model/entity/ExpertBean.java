package com.zhongke.weiduo.mvp.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/4.
 * 专家实体类
 */

public class ExpertBean implements Serializable{
    /**
     * 专家头像
     */
    private String photoUrl;
    /**
     * 专家名
     */
    private String name;
    /**
     * 专家详情
     */
    private String introduce;
    /**
     * 专家电话
     */
    private String phone;
    /**
     * 课程数
     */
    private int curriculum;
    /**
     * 粉丝数
     */
    private int fans;
    /**
     * 专家id（专家的唯一标识符）
     */
    private int id;
    /**
     * 专家擅长
     */
    private String goodAt;
    /**
     * 专家价格
     */
    private String price;
    /**
     * 专家教龄
     */
    private int teachAge;
    /**
     * 专家学生数
     */
    private int studentMun;
    /**
     * 专家教学时长
     */
    private int teachDuration;
    /**
     * 专家点赞数
     */
    private int praise;
    /**
     * 是否为平台优选 （true为是，false为否）
     */
    private boolean preferred;
    /**
     * 是否为资金保障（true为是，false为否）
     */
    private boolean guarantee;
    /**
     * 是否收藏（true为是，false为否）
     */
    private boolean collection;
    /**
     * 是否为好友（true为是，false为否）
     */
    private boolean friend;
    /**
     * 专家课程对象集合
     */
    private List<ExpertCourseBean> expertCourseBeanList;
    /**
     * 专家被评价集合
     */
    private List<StudentEvaluationBean> studentEvaluationBeanList;

    public ExpertBean() {
    }

    public ExpertBean(String photoUrl, String name, String introduce, String phone, int curriculum, int fans, int id, String goodAt, String price, int teachAge, int studentMun, int teachDuration, int praise, boolean preferred, boolean guarantee, boolean collection, boolean friend, List<ExpertCourseBean> expertCourseBeanList, List<StudentEvaluationBean> studentEvaluationBeanList) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.introduce = introduce;
        this.phone = phone;
        this.curriculum = curriculum;
        this.fans = fans;
        this.id = id;
        this.goodAt = goodAt;
        this.price = price;
        this.teachAge = teachAge;
        this.studentMun = studentMun;
        this.teachDuration = teachDuration;
        this.praise = praise;
        this.preferred = preferred;
        this.guarantee = guarantee;
        this.collection = collection;
        this.friend = friend;
        this.expertCourseBeanList = expertCourseBeanList;
        this.studentEvaluationBeanList = studentEvaluationBeanList;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCurriculum() {
        if (expertCourseBeanList != null) {
            return expertCourseBeanList.size();
        }
        return curriculum;
    }

    public void setCurriculum(int curriculum) {
        this.curriculum = curriculum;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodAt() {
        return goodAt;
    }

    public void setGoodAt(String goodAt) {
        this.goodAt = goodAt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getTeachAge() {
        return teachAge;
    }

    public void setTeachAge(int teachAge) {
        this.teachAge = teachAge;
    }

    public int getStudentMun() {
        return studentMun;
    }

    public void setStudentMun(int studentMun) {
        this.studentMun = studentMun;
    }

    public int getTeachDuration() {
        return teachDuration;
    }

    public void setTeachDuration(int teachDuration) {
        this.teachDuration = teachDuration;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

    public boolean isGuarantee() {
        return guarantee;
    }

    public void setGuarantee(boolean guarantee) {
        this.guarantee = guarantee;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }

    public List<ExpertCourseBean> getExpertCourseBeanList() {
        return expertCourseBeanList;
    }

    public void setExpertCourseBeanList(List<ExpertCourseBean> expertCourseBeanList) {
        this.expertCourseBeanList = expertCourseBeanList;
    }

    public List<StudentEvaluationBean> getStudentEvaluationBeanList() {
        return studentEvaluationBeanList;
    }

    public void setStudentEvaluationBeanList(List<StudentEvaluationBean> studentEvaluationBeanList) {
        this.studentEvaluationBeanList = studentEvaluationBeanList;
    }

    @Override
    public String toString() {
        return "ExpertBean{" +
                "photoUrl='" + photoUrl + '\'' +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", phone='" + phone + '\'' +
                ", curriculum=" + curriculum +
                ", fans=" + fans +
                ", id=" + id +
                ", goodAt='" + goodAt + '\'' +
                ", price='" + price + '\'' +
                ", teachAge=" + teachAge +
                ", studentMun=" + studentMun +
                ", teachDuration=" + teachDuration +
                ", praise=" + praise +
                ", preferred=" + preferred +
                ", guarantee=" + guarantee +
                ", collection=" + collection +
                ", friend=" + friend +
                ", expertCourseBeanList=" + expertCourseBeanList +
                ", studentEvaluationBeanList=" + studentEvaluationBeanList +
                '}';
    }

    public static List<ExpertBean> getData() {
        List<ExpertBean> list = new ArrayList<>();
        List<ExpertCourseBean> ecb1 = new ArrayList<>();
        ecb1.add(new ExpertCourseBean("直播，秒破小学应用题", "http://px.thea.cn/Public/Upload/2971417/Intro/1470305329.png", "9.9", 1));
        ecb1.add(new ExpertCourseBean("图说数学-初级班-上", "http://www.mobanku.com/uploads/allimg/140212/1_021214293630Z.jpg", "免费", 0));
        ecb1.add(new ExpertCourseBean("英语口语教学", "http://pic2.ooopic.com/11/23/19/14b1OOOPIC2b.jpg", "188", 0));
        ecb1.add(new ExpertCourseBean("函数解答", "http://www.keedu.cn/static/717/pic/201504061708283699061.jpg", "9.9", 1));
        List<StudentEvaluationBean> seb1 = new ArrayList<>();
        seb1.add(new StudentEvaluationBean("哆啦A梦", "http://img.jsqq.net/uploads/allimg/150210/1-150210161I90-L.jpg", 4, "这个老师非常好", 55555555555555L, 555));
        seb1.add(new StudentEvaluationBean("梦梦", "http://www.feizl.com/upload2007/2014_06/140625163961254.jpg", 2, "这个老师非常好棒棒哒", 7777777L, 258));
        seb1.add(new StudentEvaluationBean("哆多", "http://diy.qqjay.com/u2/2013/0401/58c07b3c81b46df4180826b700449c93.jpg", 3, "这个老师非常好，世界第一老是", 55555555555555L, 755));
        seb1.add(new StudentEvaluationBean("啦", "http://www.qqpk.cn/article/uploadfiles/201201/20120108155251256.jpg", 4, "这个老师非常好", 888888L, 333));

        List<ExpertCourseBean> ecb2 = new ArrayList<>();
        ecb2.add(new ExpertCourseBean("直播，秒破小学应用题", "http://px.thea.cn/Public/Upload/2971417/Intro/1470305329.png", "9.9", 1));
        ecb2.add(new ExpertCourseBean("图说数学-初级班-上", "http://www.mobanku.com/uploads/allimg/140212/1_021214293630Z.jpg", "免费", 0));
        ecb2.add(new ExpertCourseBean("英语口语教学", "http://pic2.ooopic.com/11/23/19/14b1OOOPIC2b.jpg", "188", 0));
        ecb2.add(new ExpertCourseBean("函数解答", "http://www.keedu.cn/static/717/pic/201504061708283699061.jpg", "9.9", 1));
        List<StudentEvaluationBean> seb2 = new ArrayList<>();
        seb2.add(new StudentEvaluationBean("哆啦A梦", "http://img.jsqq.net/uploads/allimg/150210/1-150210161I90-L.jpg", 4, "这个老师非常好", 55555555555555L, 555));
        seb2.add(new StudentEvaluationBean("梦梦", "http://www.feizl.com/upload2007/2014_06/140625163961254.jpg", 2, "这个老师非常好棒棒哒", 7777777L, 258));
        seb2.add(new StudentEvaluationBean("哆多", "http://diy.qqjay.com/u2/2013/0401/58c07b3c81b46df4180826b700449c93.jpg", 3, "这个老师非常好，世界第一老是", 55555555555555L, 755));
        seb2.add(new StudentEvaluationBean("啦", "http://www.qqpk.cn/article/uploadfiles/201201/20120108155251256.jpg", 4, "这个老师非常好", 888888L, 333));

        List<ExpertCourseBean> ecb3 = new ArrayList<>();
        ecb3.add(new ExpertCourseBean("直播，秒破小学应用题", "http://px.thea.cn/Public/Upload/2971417/Intro/1470305329.png", "9.9", 1));
        ecb3.add(new ExpertCourseBean("图说数学-初级班-上", "http://www.mobanku.com/uploads/allimg/140212/1_021214293630Z.jpg", "免费", 0));
        ecb3.add(new ExpertCourseBean("英语口语教学", "http://pic2.ooopic.com/11/23/19/14b1OOOPIC2b.jpg", "188", 0));
        ecb3.add(new ExpertCourseBean("函数解答", "http://www.keedu.cn/static/717/pic/201504061708283699061.jpg", "9.9", 1));
        List<StudentEvaluationBean> seb3 = new ArrayList<>();
        seb3.add(new StudentEvaluationBean("哆啦A梦", "http://img.jsqq.net/uploads/allimg/150210/1-150210161I90-L.jpg", 4, "这个老师非常好", 55555555555555L, 555));
        seb3.add(new StudentEvaluationBean("梦梦", "http://www.feizl.com/upload2007/2014_06/140625163961254.jpg", 2, "这个老师非常好棒棒哒", 7777777L, 258));
        seb3.add(new StudentEvaluationBean("哆多", "http://diy.qqjay.com/u2/2013/0401/58c07b3c81b46df4180826b700449c93.jpg", 3, "这个老师非常好，世界第一老是", 55555555555555L, 755));
        seb3.add(new StudentEvaluationBean("啦", "http://www.qqpk.cn/article/uploadfiles/201201/20120108155251256.jpg", 4, "这个老师非常好", 888888L, 333));

        List<ExpertCourseBean> ecb4 = new ArrayList<>();
        ecb4.add(new ExpertCourseBean("直播，秒破小学应用题", "http://px.thea.cn/Public/Upload/2971417/Intro/1470305329.png", "9.9", 1));
        ecb4.add(new ExpertCourseBean("图说数学-初级班-上", "http://www.mobanku.com/uploads/allimg/140212/1_021214293630Z.jpg", "免费", 0));
        ecb4.add(new ExpertCourseBean("英语口语教学", "http://pic2.ooopic.com/11/23/19/14b1OOOPIC2b.jpg", "188", 0));
        ecb4.add(new ExpertCourseBean("函数解答", "http://www.keedu.cn/static/717/pic/201504061708283699061.jpg", "9.9", 1));
        List<StudentEvaluationBean> seb4 = new ArrayList<>();
        seb4.add(new StudentEvaluationBean("哆啦A梦", "http://img.jsqq.net/uploads/allimg/150210/1-150210161I90-L.jpg", 4, "这个老师非常好", 55555555555555L, 555));
        seb4.add(new StudentEvaluationBean("梦梦", "http://www.feizl.com/upload2007/2014_06/140625163961254.jpg", 2, "这个老师非常好棒棒哒", 7777777L, 258));
        seb4.add(new StudentEvaluationBean("哆多", "http://diy.qqjay.com/u2/2013/0401/58c07b3c81b46df4180826b700449c93.jpg", 3, "这个老师非常好，世界第一老是", 55555555555555L, 755));
        seb4.add(new StudentEvaluationBean("啦", "http://www.qqpk.cn/article/uploadfiles/201201/20120108155251256.jpg", 4, "这个老师非常好", 888888L, 333));

        List<ExpertCourseBean> ecb5 = new ArrayList<>();
        ecb5.add(new ExpertCourseBean("直播，秒破小学应用题", "http://px.thea.cn/Public/Upload/2971417/Intro/1470305329.png", "9.9", 1));
        ecb5.add(new ExpertCourseBean("图说数学-初级班-上", "http://www.mobanku.com/uploads/allimg/140212/1_021214293630Z.jpg", "免费", 0));
        ecb5.add(new ExpertCourseBean("英语口语教学", "http://pic2.ooopic.com/11/23/19/14b1OOOPIC2b.jpg", "188", 0));
        ecb5.add(new ExpertCourseBean("函数解答", "http://www.keedu.cn/static/717/pic/201504061708283699061.jpg", "9.9", 1));
        List<StudentEvaluationBean> seb5 = new ArrayList<>();
        seb5.add(new StudentEvaluationBean("哆啦A梦", "http://img.jsqq.net/uploads/allimg/150210/1-150210161I90-L.jpg", 4, "这个老师非常好", 55555555555555L, 555));
        seb5.add(new StudentEvaluationBean("梦梦", "http://www.feizl.com/upload2007/2014_06/140625163961254.jpg", 2, "这个老师非常好棒棒哒", 7777777L, 258));
        seb5.add(new StudentEvaluationBean("哆多", "http://diy.qqjay.com/u2/2013/0401/58c07b3c81b46df4180826b700449c93.jpg", 3, "这个老师非常好，世界第一老是", 55555555555555L, 755));
        seb5.add(new StudentEvaluationBean("啦", "http://www.qqpk.cn/article/uploadfiles/201201/20120108155251256.jpg", 4, "这个老师非常好", 888888L, 333));

        List<ExpertCourseBean> ecb6 = new ArrayList<>();
        ecb6.add(new ExpertCourseBean("直播，秒破小学应用题", "http://px.thea.cn/Public/Upload/2971417/Intro/1470305329.png", "9.9", 1));
        ecb6.add(new ExpertCourseBean("图说数学-初级班-上", "http://www.mobanku.com/uploads/allimg/140212/1_021214293630Z.jpg", "免费", 0));
        ecb6.add(new ExpertCourseBean("英语口语教学", "http://pic2.ooopic.com/11/23/19/14b1OOOPIC2b.jpg", "188", 0));
        ecb6.add(new ExpertCourseBean("函数解答", "http://www.keedu.cn/static/717/pic/201504061708283699061.jpg", "9.9", 1));
        List<StudentEvaluationBean> seb6 = new ArrayList<>();
        seb6.add(new StudentEvaluationBean("哆啦A梦", "http://img.jsqq.net/uploads/allimg/150210/1-150210161I90-L.jpg", 4, "这个老师非常好", 55555555555555L, 555));
        seb6.add(new StudentEvaluationBean("梦梦", "http://www.feizl.com/upload2007/2014_06/140625163961254.jpg", 2, "这个老师非常好棒棒哒", 7777777L, 258));
        seb6.add(new StudentEvaluationBean("哆多", "http://diy.qqjay.com/u2/2013/0401/58c07b3c81b46df4180826b700449c93.jpg", 3, "这个老师非常好，世界第一老是", 55555555555555L, 755));
        seb6.add(new StudentEvaluationBean("啦", "http://www.qqpk.cn/article/uploadfiles/201201/20120108155251256.jpg", 4, "这个老师非常好", 888888L, 333));

        List<ExpertCourseBean> ecb7 = new ArrayList<>();
        ecb7.add(new ExpertCourseBean("直播，秒破小学应用题", "http://px.thea.cn/Public/Upload/2971417/Intro/1470305329.png", "9.9", 1));
        ecb7.add(new ExpertCourseBean("图说数学-初级班-上", "http://www.mobanku.com/uploads/allimg/140212/1_021214293630Z.jpg", "免费", 0));
        ecb7.add(new ExpertCourseBean("英语口语教学", "http://pic2.ooopic.com/11/23/19/14b1OOOPIC2b.jpg", "188", 0));
        ecb7.add(new ExpertCourseBean("函数解答", "http://www.keedu.cn/static/717/pic/201504061708283699061.jpg", "9.9", 1));
        List<StudentEvaluationBean> seb7 = new ArrayList<>();
        seb7.add(new StudentEvaluationBean("哆啦A梦", "http://img.jsqq.net/uploads/allimg/150210/1-150210161I90-L.jpg", 4, "这个老师非常好", 55555555555555L, 555));
        seb7.add(new StudentEvaluationBean("梦梦", "http://www.feizl.com/upload2007/2014_06/140625163961254.jpg", 2, "这个老师非常好棒棒哒", 7777777L, 258));
        seb7.add(new StudentEvaluationBean("哆多", "http://diy.qqjay.com/u2/2013/0401/58c07b3c81b46df4180826b700449c93.jpg", 3, "这个老师非常好，世界第一老是", 55555555555555L, 755));
        seb7.add(new StudentEvaluationBean("啦", "http://www.qqpk.cn/article/uploadfiles/201201/20120108155251256.jpg", 4, "这个老师非常好", 888888L, 333));

        List<ExpertCourseBean> ecb8 = new ArrayList<>();
        ecb8.add(new ExpertCourseBean("直播，秒破小学应用题", "http://px.thea.cn/Public/Upload/2971417/Intro/1470305329.png", "9.9", 1));
        ecb8.add(new ExpertCourseBean("图说数学-初级班-上", "http://www.mobanku.com/uploads/allimg/140212/1_021214293630Z.jpg", "免费", 0));
        ecb8.add(new ExpertCourseBean("英语口语教学", "http://pic2.ooopic.com/11/23/19/14b1OOOPIC2b.jpg", "188", 0));
        ecb8.add(new ExpertCourseBean("函数解答", "http://www.keedu.cn/static/717/pic/201504061708283699061.jpg", "9.9", 1));
        List<StudentEvaluationBean> seb8 = new ArrayList<>();
        seb8.add(new StudentEvaluationBean("哆啦A梦", "http://img.jsqq.net/uploads/allimg/150210/1-150210161I90-L.jpg", 4, "这个老师非常好", 55555555555555L, 555));
        seb8.add(new StudentEvaluationBean("梦梦", "http://www.feizl.com/upload2007/2014_06/140625163961254.jpg", 2, "这个老师非常好棒棒哒", 7777777L, 258));
        seb8.add(new StudentEvaluationBean("哆多", "http://diy.qqjay.com/u2/2013/0401/58c07b3c81b46df4180826b700449c93.jpg", 3, "这个老师非常好，世界第一老是", 55555555555555L, 755));
        seb8.add(new StudentEvaluationBean("啦", "http://www.qqpk.cn/article/uploadfiles/201201/20120108155251256.jpg", 4, "这个老师非常好", 888888L, 333));

        List<ExpertCourseBean> ecb9 = new ArrayList<>();
        ecb9.add(new ExpertCourseBean("直播，秒破小学应用题", "http://px.thea.cn/Public/Upload/2971417/Intro/1470305329.png", "9.9", 1));
        ecb9.add(new ExpertCourseBean("图说数学-初级班-上", "http://www.mobanku.com/uploads/allimg/140212/1_021214293630Z.jpg", "免费", 0));
        ecb9.add(new ExpertCourseBean("英语口语教学", "http://pic2.ooopic.com/11/23/19/14b1OOOPIC2b.jpg", "188", 0));
        ecb9.add(new ExpertCourseBean("函数解答", "http://www.keedu.cn/static/717/pic/201504061708283699061.jpg", "9.9", 1));
        List<StudentEvaluationBean> seb9 = new ArrayList<>();
        seb9.add(new StudentEvaluationBean("哆啦A梦", "http://img.jsqq.net/uploads/allimg/150210/1-150210161I90-L.jpg", 4, "这个老师非常好", 55555555555555L, 555));
        seb9.add(new StudentEvaluationBean("梦梦", "http://www.feizl.com/upload2007/2014_06/140625163961254.jpg", 2, "这个老师非常好棒棒哒", 7777777L, 258));
        seb9.add(new StudentEvaluationBean("哆多", "http://diy.qqjay.com/u2/2013/0401/58c07b3c81b46df4180826b700449c93.jpg", 3, "这个老师非常好，世界第一老是", 55555555555555L, 755));
        seb9.add(new StudentEvaluationBean("啦", "http://pic36.nipic.com/20131227/8605868_174931007356_2.jpg", 4, "这个老师非常好", 888888L, 333));

        List<ExpertCourseBean> ecb10 = new ArrayList<>();
        ecb10.add(new ExpertCourseBean("直播，秒破小学应用题", "http://px.thea.cn/Public/Upload/2971417/Intro/1470305329.png", "9.9", 1));
        ecb10.add(new ExpertCourseBean("图说数学-初级班-上", "http://www.mobanku.com/uploads/allimg/140212/1_021214293630Z.jpg", "免费", 0));
        ecb10.add(new ExpertCourseBean("英语口语教学", "http://pic2.ooopic.com/11/23/19/14b1OOOPIC2b.jpg", "188", 0));
        ecb10.add(new ExpertCourseBean("函数解答", "http://www.keedu.cn/static/717/pic/201504061708283699061.jpg", "9.9", 1));
        List<StudentEvaluationBean> seb10 = new ArrayList<>();
        seb10.add(new StudentEvaluationBean("哆啦A梦", "http://img.jsqq.net/uploads/allimg/150210/1-150210161I90-L.jpg", 4, "这个老师非常好", 55555555555555L, 555));
        seb10.add(new StudentEvaluationBean("梦梦", "http://www.feizl.com/upload2007/2014_06/140625163961254.jpg", 2, "这个老师非常好棒棒哒", 7777777L, 258));
        seb10.add(new StudentEvaluationBean("哆多", "http://diy.qqjay.com/u2/2013/0401/58c07b3c81b46df4180826b700449c93.jpg", 3, "这个老师非常好，世界第一老是", 55555555555555L, 755));
        seb10.add(new StudentEvaluationBean("啦", "http://www.qqpk.cn/article/uploadfiles/201201/20120108155251256.jpg", 4, "这个老师非常好", 888888L, 333));

        list.add(new ExpertBean("http://www.feizl.com/upload2007/2013_04/130417155379408.jpg", "维克托", "世界第一物理老师世界第一物理老师世界第一物理老师世界第一物理老师世界第一物老师", "12306", 555555, 520, 0, "擅长物理，物理，牛顿定律", "$500-580", 6, 333, 888854555, 250, true, true, false, false, ecb1, seb1));
        list.add(new ExpertBean("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2444635535,3591479252&fm=27&gp=0.jpg", "维克托", "世界第一物理老师世界第一物理老师世界第一物理老师世界第一物理老师世界第一物老师", "12306", 555555, 520, 0, "擅长物理，物理，牛顿定律", "$500-580", 6, 333, 888854555, 250, true, true, false, false, ecb2, seb2));
        list.add(new ExpertBean("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1504590903&di=ded2800e556b65f54620232fbd3ac446&src=http://www.mqxsyxx.com/uploads/allimg/150430/1-150430114S4345.jpg", "维克托", "世界第一物理老师世界第一物理老师世界第一物理老师世界第一物理老师世界第一物老师", "12306", 555555, 520, 0, "擅长物理，物理，牛顿定律", "$500-580", 6, 333, 888854555, 250, true, true, false, false, ecb3, seb3));
        list.add(new ExpertBean("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3170115785,2803811232&fm=27&gp=0.jpg", "维克托", "世界第一物理老师世界第一物理老师世界第一物理老师世界第一物理老师世界第一物老师", "12306", 555555, 520, 0, "擅长物理，物理，牛顿定律", "$500-580", 6, 333, 888854555, 250, true, true, false, false, ecb4, seb4));
        list.add(new ExpertBean("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1504590903&di=03ae71e4bb8d6b9d0edfe0fa48af80d9&src=http://img839.ph.126.net/8Vg1EhlA_wdtFyVKsp5Sww==/787848459815063545.jpg", "维克托", "世界第一物理老师世界第一物理老师世界第一物理老师世界第一物理老师世界第一物老师", "12306", 555555, 520, 0, "擅长物理，物理，牛顿定律", "$500-580", 6, 333, 888854555, 250, true, true, false, false, ecb5, seb5));
        list.add(new ExpertBean("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1504590903&di=b72ad81b2a2975e3f85d26fa5d022968&src=http://news.51sxue.com/upload1/news2014-01/201401201750179143.jpg", "维克托", "世界第一物理老师世界第一物理老师世界第一物理老师世界第一物理老师世界第一物老师", "12306", 555555, 520, 0, "擅长物理，物理，牛顿定律", "$500-580", 6, 333, 888854555, 250, true, true, false, false, ecb6, seb6));
        list.add(new ExpertBean("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1504590903&di=25e836821518fff2ec0670fb4844c050&src=http://img.taopic.com/uploads/allimg/110821/1942-110R110431925.jpg", "维克托", "世界第一物理老师世界第一物理老师世界第一物理老师世界第一物理老师世界第一物老师", "12306", 555555, 520, 0, "擅长物理，物理，牛顿定律", "$500-580", 6, 333, 888854555, 250, true, true, false, false, ecb7, seb7));
        list.add(new ExpertBean("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=240764175,1200836936&fm=27&gp=0.jpg", "维克托", "世界第一物理老师世界第一物理老师世界第一物理老师世界第一物理老师世界第一物老师", "12306", 555555, 520, 0, "擅长物理，物理，牛顿定律", "$500-580", 6, 333, 888854555, 250, true, true, false, false, ecb8, seb8));
        list.add(new ExpertBean("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1504590903&di=541c9b8b4b4f0613a2d338b47e6d7b26&src=http://imgsrc.baidu.com/forum/pic/item/9f510fb30f2442a75975a0e6d143ad4bd113027d.jpg", "维克托", "世界第一物理老师世界第一物理老师世界第一物理老师世界第一物理老师世界第一物老师", "12306", 555555, 520, 0, "擅长物理，物理，牛顿定律", "$500-580", 6, 333, 888854555, 250, true, true, false, false, ecb9, seb9));
        list.add(new ExpertBean("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1504590903&di=d93422cf5e9bcf20bcee5410e8f67861&src=http://image.eduau.com/2014/04/20140409135743333.jpg", "维克托", "世界第一物理老师世界第一物理老师世界第一物理老师世界第一物理老师世界第一物老师", "12306", 555555, 520, 0, "擅长物理，物理，牛顿定律", "$500-580", 6, 333, 888854555, 250, true, true, false, false, ecb10, seb10));
        return list;
    }
}

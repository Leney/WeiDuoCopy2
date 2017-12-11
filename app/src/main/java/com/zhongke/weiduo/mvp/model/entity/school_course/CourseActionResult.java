package com.zhongke.weiduo.mvp.model.entity.school_course;

import java.util.List;

/**
 * Created by hyx on 2017/11/7.
 * 获得课程的活动列表
 */

public class CourseActionResult {


    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"createUserId":90,"checkMsg":null,"actionKindId":174,"flowJson":"{\"title\":\"向日葵\",\"nodes\":{\"1509963562161\":{\"name\":\"开始\",\"left\":55,\"top\":323,\"type\":\"start round mix\",\"width\":28,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1509963565984\":{\"name\":\"结束\",\"left\":906,\"top\":328,\"type\":\"end round\",\"width\":28,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137837776\":{\"name\":\"关键行为\",\"left\":275,\"top\":9,\"type\":\"behavior\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137839821\":{\"name\":\"发送通知消息\",\"left\":275,\"top\":48,\"type\":\"sendMessage\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137845845\":{\"name\":\"视频\",\"left\":276,\"top\":92,\"type\":\"video\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137850254\":{\"name\":\"音频\",\"left\":276,\"top\":136,\"type\":\"audio\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137856396\":{\"name\":\"情景灯\",\"left\":276,\"top\":179,\"type\":\"sceneLight\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137862396\":{\"name\":\"摄像头\",\"left\":275,\"top\":223,\"type\":\"camera\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137867092\":{\"name\":\"麦克风\",\"left\":276,\"top\":265,\"type\":\"microphone\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137935004\":{\"name\":\"台灯\",\"left\":276,\"top\":305,\"type\":\"lamp\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137943844\":{\"name\":\"红外检测\",\"left\":276,\"top\":350,\"type\":\"infrared\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137953605\":{\"name\":\"温度\",\"left\":276,\"top\":394,\"type\":\"temperature\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137957615\":{\"name\":\"湿度\",\"left\":275,\"top\":435,\"type\":\"humidity\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137966143\":{\"name\":\"光照强度\",\"left\":276,\"top\":478,\"type\":\"lightIntensity\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137969502\":{\"name\":\"活动道具\",\"left\":274,\"top\":523,\"type\":\"tools\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137972860\":{\"name\":\"活动邀请\",\"left\":276,\"top\":562,\"type\":\"invitation\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137981244\":{\"name\":\"活动聊天\",\"left\":275,\"top\":601,\"type\":\"actionChat\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510137990676\":{\"name\":\"活动抢答\",\"left\":275,\"top\":642,\"type\":\"answer\",\"width\":104,\"height\":28,\"color\":\"#B6F700\",\"alt\":true},\"1510137996133\":{\"name\":\"上传活动成果\",\"left\":547,\"top\":232,\"type\":\"uploadResult\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510138004822\":{\"name\":\"活动评价\",\"left\":550,\"top\":330,\"type\":\"evaluate\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1510138019893\":{\"name\":\"活动奖励\",\"left\":553,\"top\":426,\"type\":\"award\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true}},\"lines\":{\"1510138099093\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137837776\",\"name\":\"\",\"dash\":false},\"1510138103011\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137839821\",\"name\":\"\",\"dash\":false},\"1510138104924\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137845845\",\"name\":\"\",\"dash\":false},\"1510138106725\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137850254\",\"name\":\"\",\"dash\":false},\"1510138108901\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137862396\",\"name\":\"\",\"dash\":false},\"1510138110724\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137856396\",\"name\":\"\",\"dash\":false},\"1510138112371\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137867092\",\"name\":\"\",\"dash\":false},\"1510138115270\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137935004\",\"name\":\"\",\"dash\":false},\"1510138117428\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137943844\",\"name\":\"\",\"dash\":false},\"1510138119212\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137953605\",\"name\":\"\",\"dash\":false},\"1510138120940\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137957615\",\"name\":\"\",\"dash\":false},\"1510138122820\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137966143\",\"name\":\"\",\"dash\":false},\"1510138124900\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137969502\",\"name\":\"\",\"dash\":false},\"1510138127252\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137972860\",\"name\":\"\",\"dash\":false},\"1510138129540\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137981244\",\"name\":\"\",\"dash\":false},\"1510138134164\":{\"type\":\"sl\",\"from\":\"1509963562161\",\"to\":\"1510137990676\",\"name\":\"\",\"dash\":false},\"1510138138405\":{\"type\":\"sl\",\"from\":\"1510137837776\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138150461\":{\"type\":\"sl\",\"from\":\"1510137996133\",\"to\":\"1509963565984\",\"name\":\"\",\"dash\":false},\"1510138157325\":{\"type\":\"sl\",\"from\":\"1510137990676\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138159542\":{\"type\":\"sl\",\"from\":\"1510137990676\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138162309\":{\"type\":\"sl\",\"from\":\"1510137990676\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138165925\":{\"type\":\"sl\",\"from\":\"1510137981244\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138171797\":{\"type\":\"sl\",\"from\":\"1510137981244\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138179214\":{\"type\":\"sl\",\"from\":\"1510137969502\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138181029\":{\"type\":\"sl\",\"from\":\"1510137935004\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138182989\":{\"type\":\"sl\",\"from\":\"1510137953605\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138185717\":{\"type\":\"sl\",\"from\":\"1510137953605\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138187261\":{\"type\":\"sl\",\"from\":\"1510137953605\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138189245\":{\"type\":\"sl\",\"from\":\"1510137957615\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138191229\":{\"type\":\"sl\",\"from\":\"1510137957615\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138193141\":{\"type\":\"sl\",\"from\":\"1510137957615\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138194926\":{\"type\":\"sl\",\"from\":\"1510137862396\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138196813\":{\"type\":\"sl\",\"from\":\"1510137862396\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138198360\":{\"type\":\"sl\",\"from\":\"1510137862396\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138199724\":{\"type\":\"sl\",\"from\":\"1510137856396\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138200966\":{\"type\":\"sl\",\"from\":\"1510137856396\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138202239\":{\"type\":\"sl\",\"from\":\"1510137856396\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138205028\":{\"type\":\"sl\",\"from\":\"1510137850254\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138206398\":{\"type\":\"sl\",\"from\":\"1510137850254\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138211284\":{\"type\":\"sl\",\"from\":\"1510137850254\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138213309\":{\"type\":\"sl\",\"from\":\"1510137845845\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138214708\":{\"type\":\"sl\",\"from\":\"1510137845845\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138219420\":{\"type\":\"sl\",\"from\":\"1510137845845\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138222660\":{\"type\":\"sl\",\"from\":\"1510137839821\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138224844\":{\"type\":\"sl\",\"from\":\"1510137839821\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138227669\":{\"type\":\"sl\",\"from\":\"1510137839821\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138232644\":{\"type\":\"sl\",\"from\":\"1510137837776\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138235734\":{\"type\":\"sl\",\"from\":\"1510137837776\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138240356\":{\"type\":\"sl\",\"from\":\"1510137966143\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138242172\":{\"type\":\"sl\",\"from\":\"1510137966143\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138244917\":{\"type\":\"sl\",\"from\":\"1510137966143\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138247196\":{\"type\":\"sl\",\"from\":\"1510137972860\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138249516\":{\"type\":\"sl\",\"from\":\"1510137972860\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138252916\":{\"type\":\"sl\",\"from\":\"1510137972860\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138258556\":{\"type\":\"sl\",\"from\":\"1510137943844\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138260549\":{\"type\":\"sl\",\"from\":\"1510137943844\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138262469\":{\"type\":\"sl\",\"from\":\"1510137943844\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138264541\":{\"type\":\"sl\",\"from\":\"1510137935004\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138275629\":{\"type\":\"sl\",\"from\":\"1510137935004\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138277837\":{\"type\":\"sl\",\"from\":\"1510137867092\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false},\"1510138279357\":{\"type\":\"sl\",\"from\":\"1510137867092\",\"to\":\"1510137996133\",\"name\":\"\",\"dash\":false},\"1510138285391\":{\"type\":\"sl\",\"from\":\"1510137867092\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138304941\":{\"type\":\"sl\",\"from\":\"1510138019893\",\"to\":\"1509963565984\",\"name\":\"\",\"dash\":false},\"1510138306662\":{\"type\":\"sl\",\"from\":\"1510138004822\",\"to\":\"1509963565984\",\"name\":\"\",\"dash\":false},\"1510138312621\":{\"type\":\"sl\",\"from\":\"1510137969502\",\"to\":\"1510138019893\",\"name\":\"\",\"dash\":false},\"1510138317653\":{\"type\":\"sl\",\"from\":\"1510137969502\",\"to\":\"1510138004822\",\"name\":\"\",\"dash\":false}},\"areas\":{},\"initNum\":106}","actionState":1,"latitude":22.562342,"checkUserId":null,"collectActId":0,"title":"献爱心，快乐行","resKind":2,"orgId":1,"actionImport":1,"videoUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/3Y43eF5a5SExFHnGPPPkKrXH51509963281527.wmv","aInfo":"活动描述","actionKindName":"木槿","sceneId":null,"checkState":1,"id":109,"beginTime":"2017-11-09 18:14:52","flowId":49,"courseId":81,"longitude":114.066852,"resGrade":2,"cost":0,"planCount":300,"address":"深圳","attendanceCount":0,"sceneName":null,"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/BfxxitjFykAE4cbKSEKMcep7x1509963257395.jpg","actionType":1,"createTime":"2017-11-06 18:15:15","organizer":90,"signupCount":0,"actionSafety":1,"endTime":"2017-11-22 18:14:54","thumbUpCount":null}]
     * recordTotal : 1
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
         * createUserId : 90
         * checkMsg : null
         * actionKindId : 174
         * flowJson : {"title":"向日葵","nodes":{"1509963562161":{"name":"开始","left":55,"top":323,"type":"start round mix","width":28,"height":28,"color":"#C0CCDA","alt":true},"1509963565984":{"name":"结束","left":906,"top":328,"type":"end round","width":28,"height":28,"color":"#C0CCDA","alt":true},"1510137837776":{"name":"关键行为","left":275,"top":9,"type":"behavior","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137839821":{"name":"发送通知消息","left":275,"top":48,"type":"sendMessage","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137845845":{"name":"视频","left":276,"top":92,"type":"video","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137850254":{"name":"音频","left":276,"top":136,"type":"audio","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137856396":{"name":"情景灯","left":276,"top":179,"type":"sceneLight","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137862396":{"name":"摄像头","left":275,"top":223,"type":"camera","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137867092":{"name":"麦克风","left":276,"top":265,"type":"microphone","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137935004":{"name":"台灯","left":276,"top":305,"type":"lamp","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137943844":{"name":"红外检测","left":276,"top":350,"type":"infrared","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137953605":{"name":"温度","left":276,"top":394,"type":"temperature","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137957615":{"name":"湿度","left":275,"top":435,"type":"humidity","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137966143":{"name":"光照强度","left":276,"top":478,"type":"lightIntensity","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137969502":{"name":"活动道具","left":274,"top":523,"type":"tools","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137972860":{"name":"活动邀请","left":276,"top":562,"type":"invitation","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137981244":{"name":"活动聊天","left":275,"top":601,"type":"actionChat","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510137990676":{"name":"活动抢答","left":275,"top":642,"type":"answer","width":104,"height":28,"color":"#B6F700","alt":true},"1510137996133":{"name":"上传活动成果","left":547,"top":232,"type":"uploadResult","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510138004822":{"name":"活动评价","left":550,"top":330,"type":"evaluate","width":104,"height":28,"color":"#C0CCDA","alt":true},"1510138019893":{"name":"活动奖励","left":553,"top":426,"type":"award","width":104,"height":28,"color":"#C0CCDA","alt":true}},"lines":{"1510138099093":{"type":"sl","from":"1509963562161","to":"1510137837776","name":"","dash":false},"1510138103011":{"type":"sl","from":"1509963562161","to":"1510137839821","name":"","dash":false},"1510138104924":{"type":"sl","from":"1509963562161","to":"1510137845845","name":"","dash":false},"1510138106725":{"type":"sl","from":"1509963562161","to":"1510137850254","name":"","dash":false},"1510138108901":{"type":"sl","from":"1509963562161","to":"1510137862396","name":"","dash":false},"1510138110724":{"type":"sl","from":"1509963562161","to":"1510137856396","name":"","dash":false},"1510138112371":{"type":"sl","from":"1509963562161","to":"1510137867092","name":"","dash":false},"1510138115270":{"type":"sl","from":"1509963562161","to":"1510137935004","name":"","dash":false},"1510138117428":{"type":"sl","from":"1509963562161","to":"1510137943844","name":"","dash":false},"1510138119212":{"type":"sl","from":"1509963562161","to":"1510137953605","name":"","dash":false},"1510138120940":{"type":"sl","from":"1509963562161","to":"1510137957615","name":"","dash":false},"1510138122820":{"type":"sl","from":"1509963562161","to":"1510137966143","name":"","dash":false},"1510138124900":{"type":"sl","from":"1509963562161","to":"1510137969502","name":"","dash":false},"1510138127252":{"type":"sl","from":"1509963562161","to":"1510137972860","name":"","dash":false},"1510138129540":{"type":"sl","from":"1509963562161","to":"1510137981244","name":"","dash":false},"1510138134164":{"type":"sl","from":"1509963562161","to":"1510137990676","name":"","dash":false},"1510138138405":{"type":"sl","from":"1510137837776","to":"1510137996133","name":"","dash":false},"1510138150461":{"type":"sl","from":"1510137996133","to":"1509963565984","name":"","dash":false},"1510138157325":{"type":"sl","from":"1510137990676","to":"1510138004822","name":"","dash":false},"1510138159542":{"type":"sl","from":"1510137990676","to":"1510138019893","name":"","dash":false},"1510138162309":{"type":"sl","from":"1510137990676","to":"1510137996133","name":"","dash":false},"1510138165925":{"type":"sl","from":"1510137981244","to":"1510138004822","name":"","dash":false},"1510138171797":{"type":"sl","from":"1510137981244","to":"1510138019893","name":"","dash":false},"1510138179214":{"type":"sl","from":"1510137969502","to":"1510137996133","name":"","dash":false},"1510138181029":{"type":"sl","from":"1510137935004","to":"1510137996133","name":"","dash":false},"1510138182989":{"type":"sl","from":"1510137953605","to":"1510137996133","name":"","dash":false},"1510138185717":{"type":"sl","from":"1510137953605","to":"1510138004822","name":"","dash":false},"1510138187261":{"type":"sl","from":"1510137953605","to":"1510138019893","name":"","dash":false},"1510138189245":{"type":"sl","from":"1510137957615","to":"1510138004822","name":"","dash":false},"1510138191229":{"type":"sl","from":"1510137957615","to":"1510137996133","name":"","dash":false},"1510138193141":{"type":"sl","from":"1510137957615","to":"1510138019893","name":"","dash":false},"1510138194926":{"type":"sl","from":"1510137862396","to":"1510137996133","name":"","dash":false},"1510138196813":{"type":"sl","from":"1510137862396","to":"1510138004822","name":"","dash":false},"1510138198360":{"type":"sl","from":"1510137862396","to":"1510138019893","name":"","dash":false},"1510138199724":{"type":"sl","from":"1510137856396","to":"1510137996133","name":"","dash":false},"1510138200966":{"type":"sl","from":"1510137856396","to":"1510138004822","name":"","dash":false},"1510138202239":{"type":"sl","from":"1510137856396","to":"1510138019893","name":"","dash":false},"1510138205028":{"type":"sl","from":"1510137850254","to":"1510137996133","name":"","dash":false},"1510138206398":{"type":"sl","from":"1510137850254","to":"1510138004822","name":"","dash":false},"1510138211284":{"type":"sl","from":"1510137850254","to":"1510138019893","name":"","dash":false},"1510138213309":{"type":"sl","from":"1510137845845","to":"1510137996133","name":"","dash":false},"1510138214708":{"type":"sl","from":"1510137845845","to":"1510138004822","name":"","dash":false},"1510138219420":{"type":"sl","from":"1510137845845","to":"1510138019893","name":"","dash":false},"1510138222660":{"type":"sl","from":"1510137839821","to":"1510137996133","name":"","dash":false},"1510138224844":{"type":"sl","from":"1510137839821","to":"1510138004822","name":"","dash":false},"1510138227669":{"type":"sl","from":"1510137839821","to":"1510138019893","name":"","dash":false},"1510138232644":{"type":"sl","from":"1510137837776","to":"1510138004822","name":"","dash":false},"1510138235734":{"type":"sl","from":"1510137837776","to":"1510138019893","name":"","dash":false},"1510138240356":{"type":"sl","from":"1510137966143","to":"1510138019893","name":"","dash":false},"1510138242172":{"type":"sl","from":"1510137966143","to":"1510138004822","name":"","dash":false},"1510138244917":{"type":"sl","from":"1510137966143","to":"1510137996133","name":"","dash":false},"1510138247196":{"type":"sl","from":"1510137972860","to":"1510138019893","name":"","dash":false},"1510138249516":{"type":"sl","from":"1510137972860","to":"1510138004822","name":"","dash":false},"1510138252916":{"type":"sl","from":"1510137972860","to":"1510137996133","name":"","dash":false},"1510138258556":{"type":"sl","from":"1510137943844","to":"1510138004822","name":"","dash":false},"1510138260549":{"type":"sl","from":"1510137943844","to":"1510137996133","name":"","dash":false},"1510138262469":{"type":"sl","from":"1510137943844","to":"1510138019893","name":"","dash":false},"1510138264541":{"type":"sl","from":"1510137935004","to":"1510138004822","name":"","dash":false},"1510138275629":{"type":"sl","from":"1510137935004","to":"1510138019893","name":"","dash":false},"1510138277837":{"type":"sl","from":"1510137867092","to":"1510138004822","name":"","dash":false},"1510138279357":{"type":"sl","from":"1510137867092","to":"1510137996133","name":"","dash":false},"1510138285391":{"type":"sl","from":"1510137867092","to":"1510138019893","name":"","dash":false},"1510138304941":{"type":"sl","from":"1510138019893","to":"1509963565984","name":"","dash":false},"1510138306662":{"type":"sl","from":"1510138004822","to":"1509963565984","name":"","dash":false},"1510138312621":{"type":"sl","from":"1510137969502","to":"1510138019893","name":"","dash":false},"1510138317653":{"type":"sl","from":"1510137969502","to":"1510138004822","name":"","dash":false}},"areas":{},"initNum":106}
         * actionState : 1
         * latitude : 22.562342
         * checkUserId : null
         * collectActId : 0
         * title : 献爱心，快乐行
         * resKind : 2
         * orgId : 1
         * actionImport : 1
         * videoUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/3Y43eF5a5SExFHnGPPPkKrXH51509963281527.wmv
         * aInfo : 活动描述
         * actionKindName : 木槿
         * sceneId : null
         * checkState : 1
         * id : 109
         * beginTime : 2017-11-09 18:14:52
         * flowId : 49
         * courseId : 81
         * longitude : 114.066852
         * resGrade : 2
         * cost : 0
         * planCount : 300
         * address : 深圳
         * attendanceCount : 0
         * sceneName : null
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/BfxxitjFykAE4cbKSEKMcep7x1509963257395.jpg
         * actionType : 1
         * createTime : 2017-11-06 18:15:15
         * organizer : 90
         * signupCount : 0
         * actionSafety : 1
         * endTime : 2017-11-22 18:14:54
         * thumbUpCount : null
         */

        private int createUserId;
        private Object checkMsg;
        private int actionKindId;
        private String flowJson;
        private int actionState;
        private double latitude;
        private Object checkUserId;
        private int collectActId;
        private int addId;
        private String title;
        private int resKind;
        private int orgId;
        private int actionImport;
        private String videoUrl;
        private String aInfo;
        private String actionKindName;
        private Object sceneId;
        private int checkState;
        private int id;
        private String beginTime;
        private int flowId;
        private int courseId;
        private double longitude;
        private int resGrade;
        private int cost;
        private int planCount;
        private String address;
        private int attendanceCount;
        private Object sceneName;
        private String coverUrl;
        private int actionType;
        private String createTime;
        private int organizer;
        private int signupCount;
        private int actionSafety;
        private String endTime;
        private Object thumbUpCount;
        private boolean check;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public Object getCheckMsg() {
            return checkMsg;
        }

        public void setCheckMsg(Object checkMsg) {
            this.checkMsg = checkMsg;
        }

        public int getActionKindId() {
            return actionKindId;
        }

        public void setActionKindId(int actionKindId) {
            this.actionKindId = actionKindId;
        }

        public String getFlowJson() {
            return flowJson;
        }

        public void setFlowJson(String flowJson) {
            this.flowJson = flowJson;
        }

        public int getActionState() {
            return actionState;
        }

        public void setActionState(int actionState) {
            this.actionState = actionState;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public Object getCheckUserId() {
            return checkUserId;
        }

        public void setCheckUserId(Object checkUserId) {
            this.checkUserId = checkUserId;
        }

        public int getCollectActId() {
            return collectActId;
        }

        public void setCollectActId(int collectActId) {
            this.collectActId = collectActId;
        }

        public int getAddId() {
            return addId;
        }

        public void setAddId(int addId) {
            this.addId = addId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getResKind() {
            return resKind;
        }

        public void setResKind(int resKind) {
            this.resKind = resKind;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getActionImport() {
            return actionImport;
        }

        public void setActionImport(int actionImport) {
            this.actionImport = actionImport;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getAInfo() {
            return aInfo;
        }

        public void setAInfo(String aInfo) {
            this.aInfo = aInfo;
        }

        public String getActionKindName() {
            return actionKindName;
        }

        public void setActionKindName(String actionKindName) {
            this.actionKindName = actionKindName;
        }

        public Object getSceneId() {
            return sceneId;
        }

        public void setSceneId(Object sceneId) {
            this.sceneId = sceneId;
        }

        public int getCheckState() {
            return checkState;
        }

        public void setCheckState(int checkState) {
            this.checkState = checkState;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public int getFlowId() {
            return flowId;
        }

        public void setFlowId(int flowId) {
            this.flowId = flowId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public int getResGrade() {
            return resGrade;
        }

        public void setResGrade(int resGrade) {
            this.resGrade = resGrade;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getPlanCount() {
            return planCount;
        }

        public void setPlanCount(int planCount) {
            this.planCount = planCount;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAttendanceCount() {
            return attendanceCount;
        }

        public void setAttendanceCount(int attendanceCount) {
            this.attendanceCount = attendanceCount;
        }

        public Object getSceneName() {
            return sceneName;
        }

        public void setSceneName(Object sceneName) {
            this.sceneName = sceneName;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public int getActionType() {
            return actionType;
        }

        public void setActionType(int actionType) {
            this.actionType = actionType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getOrganizer() {
            return organizer;
        }

        public void setOrganizer(int organizer) {
            this.organizer = organizer;
        }

        public int getSignupCount() {
            return signupCount;
        }

        public void setSignupCount(int signupCount) {
            this.signupCount = signupCount;
        }

        public int getActionSafety() {
            return actionSafety;
        }

        public void setActionSafety(int actionSafety) {
            this.actionSafety = actionSafety;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public Object getThumbUpCount() {
            return thumbUpCount;
        }

        public void setThumbUpCount(Object thumbUpCount) {
            this.thumbUpCount = thumbUpCount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            RecordsBean that = (RecordsBean) o;

            if (createUserId != that.createUserId) return false;
            if (actionKindId != that.actionKindId) return false;
            if (actionState != that.actionState) return false;
            if (Double.compare(that.latitude, latitude) != 0) return false;
            if (collectActId != that.collectActId) return false;
            if (resKind != that.resKind) return false;
            if (orgId != that.orgId) return false;
            if (actionImport != that.actionImport) return false;
            if (checkState != that.checkState) return false;
            if (id != that.id) return false;
            if (flowId != that.flowId) return false;
            if (courseId != that.courseId) return false;
            if (Double.compare(that.longitude, longitude) != 0) return false;
            if (resGrade != that.resGrade) return false;
            if (cost != that.cost) return false;
            if (planCount != that.planCount) return false;
            if (attendanceCount != that.attendanceCount) return false;
            if (actionType != that.actionType) return false;
            if (organizer != that.organizer) return false;
            if (signupCount != that.signupCount) return false;
            if (actionSafety != that.actionSafety) return false;
            if (check != that.check) return false;
            if (checkMsg != null ? !checkMsg.equals(that.checkMsg) : that.checkMsg != null)
                return false;
            if (flowJson != null ? !flowJson.equals(that.flowJson) : that.flowJson != null)
                return false;
            if (checkUserId != null ? !checkUserId.equals(that.checkUserId) : that.checkUserId != null)
                return false;
            if (title != null ? !title.equals(that.title) : that.title != null) return false;
            if (videoUrl != null ? !videoUrl.equals(that.videoUrl) : that.videoUrl != null)
                return false;
            if (aInfo != null ? !aInfo.equals(that.aInfo) : that.aInfo != null) return false;
            if (actionKindName != null ? !actionKindName.equals(that.actionKindName) : that.actionKindName != null)
                return false;
            if (sceneId != null ? !sceneId.equals(that.sceneId) : that.sceneId != null)
                return false;
            if (beginTime != null ? !beginTime.equals(that.beginTime) : that.beginTime != null)
                return false;
            if (address != null ? !address.equals(that.address) : that.address != null)
                return false;
            if (sceneName != null ? !sceneName.equals(that.sceneName) : that.sceneName != null)
                return false;
            if (coverUrl != null ? !coverUrl.equals(that.coverUrl) : that.coverUrl != null)
                return false;
            if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null)
                return false;
            if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null)
                return false;
            return thumbUpCount != null ? thumbUpCount.equals(that.thumbUpCount) : that.thumbUpCount == null;

        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = createUserId;
            result = 31 * result + (checkMsg != null ? checkMsg.hashCode() : 0);
            result = 31 * result + actionKindId;
            result = 31 * result + (flowJson != null ? flowJson.hashCode() : 0);
            result = 31 * result + actionState;
            temp = Double.doubleToLongBits(latitude);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (checkUserId != null ? checkUserId.hashCode() : 0);
            result = 31 * result + collectActId;
            result = 31 * result + (title != null ? title.hashCode() : 0);
            result = 31 * result + resKind;
            result = 31 * result + orgId;
            result = 31 * result + actionImport;
            result = 31 * result + (videoUrl != null ? videoUrl.hashCode() : 0);
            result = 31 * result + (aInfo != null ? aInfo.hashCode() : 0);
            result = 31 * result + (actionKindName != null ? actionKindName.hashCode() : 0);
            result = 31 * result + (sceneId != null ? sceneId.hashCode() : 0);
            result = 31 * result + checkState;
            result = 31 * result + id;
            result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
            result = 31 * result + flowId;
            result = 31 * result + courseId;
            temp = Double.doubleToLongBits(longitude);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + resGrade;
            result = 31 * result + cost;
            result = 31 * result + planCount;
            result = 31 * result + (address != null ? address.hashCode() : 0);
            result = 31 * result + attendanceCount;
            result = 31 * result + (sceneName != null ? sceneName.hashCode() : 0);
            result = 31 * result + (coverUrl != null ? coverUrl.hashCode() : 0);
            result = 31 * result + actionType;
            result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
            result = 31 * result + organizer;
            result = 31 * result + signupCount;
            result = 31 * result + actionSafety;
            result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
            result = 31 * result + (thumbUpCount != null ? thumbUpCount.hashCode() : 0);
            result = 31 * result + (check ? 1 : 0);
            return result;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "createUserId=" + createUserId +
                    ", checkMsg=" + checkMsg +
                    ", actionKindId=" + actionKindId +
                    ", flowJson='" + flowJson + '\'' +
                    ", actionState=" + actionState +
                    ", latitude=" + latitude +
                    ", checkUserId=" + checkUserId +
                    ", collectActId=" + collectActId +
                    ", addId=" + addId +
                    ", title='" + title + '\'' +
                    ", resKind=" + resKind +
                    ", orgId=" + orgId +
                    ", actionImport=" + actionImport +
                    ", videoUrl='" + videoUrl + '\'' +
                    ", aInfo='" + aInfo + '\'' +
                    ", actionKindName='" + actionKindName + '\'' +
                    ", sceneId=" + sceneId +
                    ", checkState=" + checkState +
                    ", id=" + id +
                    ", beginTime='" + beginTime + '\'' +
                    ", flowId=" + flowId +
                    ", courseId=" + courseId +
                    ", longitude=" + longitude +
                    ", resGrade=" + resGrade +
                    ", cost=" + cost +
                    ", planCount=" + planCount +
                    ", address='" + address + '\'' +
                    ", attendanceCount=" + attendanceCount +
                    ", sceneName=" + sceneName +
                    ", coverUrl='" + coverUrl + '\'' +
                    ", actionType=" + actionType +
                    ", createTime='" + createTime + '\'' +
                    ", organizer=" + organizer +
                    ", signupCount=" + signupCount +
                    ", actionSafety=" + actionSafety +
                    ", endTime='" + endTime + '\'' +
                    ", thumbUpCount=" + thumbUpCount +
                    ", check=" + check +
                    '}';
        }
    }
}

package com.zhongke.weiduo.mvp.model.entity;

/**
 * 硬件控制命令实体
 * Created by llj on 2017/12/2.
 */

public class HardwareOrder {
    /**
     * 台灯_黄灯
     */
    public static final int ORDER_TYPE_TABLE_LAMP_YELLOW = 0;
    /**
     * 台灯_白灯
     */
    public static final int ORDER_TYPE_TABLE_LAMP_WHITE = 1;
    /**
     * 音量调节
     */
    public static final int ORDER_TYPE_VOLUME = 2;
    /**
     * 情景灯颜色
     */
    public static final int ORDER_TYPE_SCENE_LIGHT_COLOR = 3;
    /**
     * 硬件表情
     */
    public static final int ORDER_TYPE_HARDWARE_FACE = 4;

    /**
     * 命令类型
     */
    public int type;
    /**
     * 命令值
     */
    public String value1;
    public String value2;
    public String value3;
    public String value4;
    public String value5;
    public String value6;
    public String value7;
    public String value8;
    public String value9;

//    /**
//     * json String转换成HardwareOrder对象
//     *
//     * @param content
//     * @return
//     */
//    public static HardwareOrder toEntry(String content) {
//        HardwareOrder order;
//        try {
//            order = new HardwareOrder();
//            JSONObject jsonObject = new JSONObject(content);
//            order.type = jsonObject.getInt("type");
//            order.value = jsonObject.getString("value");
//        } catch (JSONException e) {
//            e.printStackTrace();
//            order = null;
//        }
//        return order;
//    }
//
//    /**
//     * HardwareOrder 对象转换成Json string
//     *
//     * @param order
//     * @return
//     */
//    public static String toJsonString(HardwareOrder order) {
//        if (order == null) return "";
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("type", order.type);
//            jsonObject.put("value", order.value);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return jsonObject.toString();
//    }
}

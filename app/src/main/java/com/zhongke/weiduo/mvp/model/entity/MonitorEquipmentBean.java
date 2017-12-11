package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${tanlei} on 2017/6/21.
 */

public class MonitorEquipmentBean {
    /**
     * 监控设备名称
     */
    private String monitorEquipmentName;
    /**
     * 监控设备角度
     */
    private String monitorEquipmentAngle;
    /**
     * 监控设备时间
     */
    private String monitorEquipmentDate;
    private boolean isCheck;
    /**
     * 设置是否开关
     */
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public MonitorEquipmentBean(String monitorEquipmentName, String monitorEquipmentAngle, String monitorEquipmentDate, boolean isCheck) {
        this.monitorEquipmentName = monitorEquipmentName;
        this.monitorEquipmentAngle = monitorEquipmentAngle;
        this.monitorEquipmentDate = monitorEquipmentDate;
        this.isCheck = isCheck;
    }

    public String getMonitorEquipmentName() {
        return monitorEquipmentName;
    }

    public void setMonitorEquipmentName(String monitorEquipmentName) {
        this.monitorEquipmentName = monitorEquipmentName;
    }

    public String getMonitorEquipmentAngle() {
        return monitorEquipmentAngle;
    }

    public void setMonitorEquipmentAngle(String monitorEquipmentAngle) {
        this.monitorEquipmentAngle = monitorEquipmentAngle;
    }

    public String getMonitorEquipmentDate() {
        return monitorEquipmentDate;
    }

    public void setMonitorEquipmentDate(String monitorEquipmentDate) {
        this.monitorEquipmentDate = monitorEquipmentDate;
    }
}

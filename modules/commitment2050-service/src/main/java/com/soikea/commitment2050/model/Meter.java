package com.soikea.commitment2050.model;

import java.io.Serializable;

/**
 *  Meter defines trackable goal for operation
 */
public class Meter implements Serializable {

    private static final long serialVersionUID = -8457578981589303870L;
    private String meterId;
    private String meterType_fi_FI;
    private String meterType_en_US;
    private String meterType_sv_SE;

    private MeterValueType meterValueType;

    private String startingLevel;
    private String targetLevel;
    private MeterChartType meterChartType;

    private String meterCategory;

    public Meter() {
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getMeterType_fi_FI() {
        return meterType_fi_FI;
    }

    public void setMeterType_fi_FI(String meterType_fi_FI) {
        this.meterType_fi_FI = meterType_fi_FI;
    }

    public String getMeterType_en_US() {
        return meterType_en_US;
    }

    public void setMeterType_en_US(String meterType_en_US) {
        this.meterType_en_US = meterType_en_US;
    }

    public String getMeterType_sv_SE() {
        return meterType_sv_SE;
    }

    public void setMeterType_sv_SE(String meterType_sv_SE) {
        this.meterType_sv_SE = meterType_sv_SE;
    }

    public MeterValueType getMeterValueType() {
        return meterValueType;
    }

    public void setMeterValueType(MeterValueType meterValueType) {
        this.meterValueType = meterValueType;
    }

    public String getStartingLevel() {
        return startingLevel;
    }

    public void setStartingLevel(String startingLevel) {
        this.startingLevel = startingLevel;
    }

    public String getTargetLevel() {
        return targetLevel;
    }

    public void setTargetLevel(String targetLevel) {
        this.targetLevel = targetLevel;
    }

    public MeterChartType getMeterChartType() {
        return meterChartType;
    }

    public void setMeterChartType(MeterChartType meterChartType) {
        this.meterChartType = meterChartType;
    }

    public String getMeterCategory() {
        return meterCategory;
    }

    public void setMeterCategory(String meterCategory) {
        this.meterCategory = meterCategory;
    }
}

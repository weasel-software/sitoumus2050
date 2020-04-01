package com.soikea.commitment2050.model;

import java.io.Serializable;

/**
 * ReportMeter is reported value of operation meter
 */
public class ReportMeter implements Serializable {
    private static final long serialVersionUID = -1330623909643011394L;
    private String meterType_fi_FI;
    private String meterType_en_US;
    private String meterType_sv_SE;

    private String startingLevel;
    private String targetLevel;
    private String currentLevel;
    private MeterValueType meterValueType;

    private String commitmentOperationMeterRefId;

    public String getCommitmentOperationMeterRefId() {
        return commitmentOperationMeterRefId;
    }

    public void setCommitmentOperationMeterRefId(String commitmentOperationMeterRefId) {
        this.commitmentOperationMeterRefId = commitmentOperationMeterRefId;
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

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public MeterValueType getMeterValueType() {
        return meterValueType;
    }

    public void setMeterValueType(MeterValueType meterValueType) {
        this.meterValueType = meterValueType;
    }
}

package com.soikea.commitment2050.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class MeterTemplate implements Serializable {


    private static final long serialVersionUID = -8584074517691621737L;
    private String id;
    private String meterType_fi_FI;
    private String meterType_en_US;
    private String meterType_sv_SE;

    private MeterValueType meterValueType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}

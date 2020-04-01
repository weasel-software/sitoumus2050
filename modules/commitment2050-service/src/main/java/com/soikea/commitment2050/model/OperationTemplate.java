package com.soikea.commitment2050.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class OperationTemplate implements Serializable {

    private static final long serialVersionUID = -5799752612964860070L;
    private String id;
    private String operationTitle_fi_FI;
    private String operationTitle_en_US;
    private String operationTitle_sv_SE;

    private List<MeterTemplate> meters;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperationTitle_fi_FI() {
        return operationTitle_fi_FI;
    }

    public void setOperationTitle_fi_FI(String operationTitle_fi_FI) {
        this.operationTitle_fi_FI = operationTitle_fi_FI;
    }

    public String getOperationTitle_en_US() {
        return operationTitle_en_US;
    }

    public void setOperationTitle_en_US(String operationTitle_en_US) {
        this.operationTitle_en_US = operationTitle_en_US;
    }

    public String getOperationTitle_sv_SE() {
        return operationTitle_sv_SE;
    }

    public void setOperationTitle_sv_SE(String operationTitle_sv_SE) {
        this.operationTitle_sv_SE = operationTitle_sv_SE;
    }

    public List<MeterTemplate> getMeters() {
        return meters;
    }

    public void setMeters(List<MeterTemplate> meters) {
        this.meters = meters;
    }
}

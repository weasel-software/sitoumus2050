package com.soikea.commitment2050.model;

import java.io.Serializable;
import java.util.List;

/**
 * Operation ("tavoite" or "toimenpide"). Every commitment should have at least one operation
 */
public class Operation implements Serializable {
    private static final long serialVersionUID = -935088866767706917L;
    private String operationId;
    private String operationTitle_fi_FI;
    private String operationTitle_en_US;
    private String operationTitle_sv_SE;

    private String operationDescription_fi_FI;
    private String operationDescription_en_US;
    private String operationDescription_sv_SE;

    private List<Meter> meters;
    private List<Report> reports;

    private String operationCategory;

    public Operation() {
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
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

    public String getOperationDescription_fi_FI() {
        return operationDescription_fi_FI;
    }

    public void setOperationDescription_fi_FI(String operationDescription_fi_FI) {
        this.operationDescription_fi_FI = operationDescription_fi_FI;
    }

    public String getOperationDescription_en_US() {
        return operationDescription_en_US;
    }

    public void setOperationDescription_en_US(String operationDescription_en_US) {
        this.operationDescription_en_US = operationDescription_en_US;
    }

    public String getOperationDescription_sv_SE() {
        return operationDescription_sv_SE;
    }

    public void setOperationDescription_sv_SE(String operationDescription_sv_SE) {
        this.operationDescription_sv_SE = operationDescription_sv_SE;
    }

    public List<Meter> getMeters() {
        return meters;
    }

    public void setMeters(List<Meter> meters) {
        this.meters = meters;
    }

    public String getOperationCategory() {
        return operationCategory;
    }

    public void setOperationCategory(String operationCategory) {
        this.operationCategory = operationCategory;
    }
}

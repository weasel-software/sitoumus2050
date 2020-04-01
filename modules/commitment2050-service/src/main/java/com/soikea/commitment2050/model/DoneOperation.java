package com.soikea.commitment2050.model;

import java.io.Serializable;

public class DoneOperation implements Serializable {
    private static final long serialVersionUID = -935088366767706917L;

    private String operationId;
    private String operationTitle_fi_FI;
    private String operationTitle_en_US;
    private String operationTitle_sv_SE;

    private String operationCategory;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
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

    public String getOperationCategory() {
        return operationCategory;
    }

    public void setOperationCategory(String operationCategory) {
        this.operationCategory = operationCategory;
    }
}

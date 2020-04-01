package com.soikea.commitment2050.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Commitment's operation report
 */
@XmlRootElement
public class Report implements Serializable {

    private static final long serialVersionUID = -8648983343355427242L;
    private String id;
    private String commitmentArticleId;
    private long createdByUserId;
    private String organizationName;
    private String createdByUserName;

    private String commitmentOperationRefId;
    private String operationTitle_fi_FI;
    private String operationTitle_en_US;
    private String operationTitle_sv_SE;


    private String reportStartDate;
    private String reportEndDate;
    private String reportTitle_fi_FI;
    private String reportTitle_en_US;
    private String reportTitle_sv_SE;

    private List<ReportMeter> reportMeters;
    private ProgressType progress;
    private String reportText_fi_FI;
    private String reportText_en_US;
    private String reportText_sv_SE;

    /**
     * reportstatus is legacy boolean value from old system
     */
    private boolean reportStatus;

    private double version;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommitmentOperationRefId() {
        return commitmentOperationRefId;
    }

    public void setCommitmentOperationRefId(String commitmentOperationRefId) {
        this.commitmentOperationRefId = commitmentOperationRefId;
    }

    public String getCommitmentArticleId() {
        return commitmentArticleId;
    }

    public void setCommitmentArticleId(String commitmentArticleId) {
        this.commitmentArticleId = commitmentArticleId;
    }

    public String getReportStartDate() {
        return reportStartDate;
    }

    public void setReportStartDate(String reportStartDate) {
        this.reportStartDate = reportStartDate;
    }

    public String getReportEndDate() {
        return reportEndDate;
    }

    public void setReportEndDate(String reportEndDate) {
        this.reportEndDate = reportEndDate;
    }

    public List<ReportMeter> getReportMeters() {
        return reportMeters;
    }

    public void setReportMeters(List<ReportMeter> reportMeters) {
        this.reportMeters = reportMeters;
    }

    public ProgressType getProgress() {
        return progress;
    }

    public void setProgress(ProgressType progress) {
        this.progress = progress;
    }

    public boolean isReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(boolean reportStatus) {
        this.reportStatus = reportStatus;
    }

    public long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(long createdByUserId) {
        this.createdByUserId = createdByUserId;
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

    public String getReportTitle_fi_FI() {
        return reportTitle_fi_FI;
    }

    public void setReportTitle_fi_FI(String reportTitle_fi_FI) {
        this.reportTitle_fi_FI = reportTitle_fi_FI;
    }

    public String getReportTitle_en_US() {
        return reportTitle_en_US;
    }

    public void setReportTitle_en_US(String reportTitle_en_US) {
        this.reportTitle_en_US = reportTitle_en_US;
    }

    public String getReportTitle_sv_SE() {
        return reportTitle_sv_SE;
    }

    public void setReportTitle_sv_SE(String reportTitle_sv_SE) {
        this.reportTitle_sv_SE = reportTitle_sv_SE;
    }

    public String getReportText_fi_FI() {
        return reportText_fi_FI;
    }

    public void setReportText_fi_FI(String reportText_fi_FI) {
        this.reportText_fi_FI = reportText_fi_FI;
    }

    public String getReportText_en_US() {
        return reportText_en_US;
    }

    public void setReportText_en_US(String reportText_en_US) {
        this.reportText_en_US = reportText_en_US;
    }

    public String getReportText_sv_SE() {
        return reportText_sv_SE;
    }

    public void setReportText_sv_SE(String reportText_sv_SE) {
        this.reportText_sv_SE = reportText_sv_SE;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }
}

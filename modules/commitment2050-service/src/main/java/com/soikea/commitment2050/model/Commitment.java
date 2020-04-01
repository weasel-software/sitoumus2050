package com.soikea.commitment2050.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Commitment implements Serializable {
    private static final long serialVersionUID = 588991831306693583L;

    public Commitment() {
    }

    /**
     * id of commitment (same as Liferay's articleId for this commitment)
     */
    private String id;
    private long groupId;
    private String title_fi_FI;
    private String title_en_US;
    private String title_sv_SE;

    private String startDate;
    private String endDate;
    private Date updated;
    private Date created;
    private String innovation_fi_FI;
    private String innovation_en_US;
    private String innovation_sv_SE;

    private String backgroundInformation_fi_FI;
    private String backgroundInformation_en_US;
    private String backgroundInformation_sv_SE;

    private String shortDescription_fi_FI;
    private String shortDescription_en_US;
    private String shortDescription_sv_SE;


    private List<Operation> operations;
    private List<DoneOperation> doneOperations;

    private String organizationName;
    private String organizationId;
    private long createdByUserId;
    private String createdByUserName;

    /**
     * Url of creator portrait image
     */
    private String creatorPortrait;
    /**
     * Url of organization logo image
     */
    private String organizationLogo;

    private String address;
    private Double longitude;
    private Double latitude;

    private CommitmentType commitmentType;

    private ReportingInterval reportingInterval;

    private boolean reportReminder;
    private boolean acceptCriterias;
    private List<Long> categoryIds;
    private double version;
    private String status;

    private int joined=0;
    private int likes=0;

    private List<Report> genericReports;

    private String commitmentImageUrl;


    private String creatorEmail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public CommitmentType getCommitmentType() {
        return commitmentType;
    }

    public void setCommitmentType(CommitmentType commitmentType) {
        this.commitmentType = commitmentType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ReportingInterval getReportingInterval() {
        return reportingInterval;
    }

    public void setReportingInterval(ReportingInterval reportingInterval) {
        this.reportingInterval = reportingInterval;
    }

    public boolean isReportReminder() {
        return reportReminder;
    }

    public void setReportReminder(boolean reportReminder) {
        this.reportReminder = reportReminder;
    }

    public boolean isAcceptCriterias() {
        return acceptCriterias;
    }

    public void setAcceptCriterias(boolean acceptCriterias) {
        this.acceptCriterias = acceptCriterias;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getTitle_fi_FI() {
        return title_fi_FI;
    }

    public void setTitle_fi_FI(String title_fi_FI) {
        this.title_fi_FI = title_fi_FI;
    }

    public String getTitle_en_US() {
        return title_en_US;
    }

    public void setTitle_en_US(String title_en_US) {
        this.title_en_US = title_en_US;
    }

    public String getTitle_sv_SE() {
        return title_sv_SE;
    }

    public void setTitle_sv_SE(String title_sv_SE) {
        this.title_sv_SE = title_sv_SE;
    }

    public String getInnovation_fi_FI() {
        return innovation_fi_FI;
    }

    public void setInnovation_fi_FI(String innovation_fi_FI) {
        this.innovation_fi_FI = innovation_fi_FI;
    }

    public String getInnovation_en_US() {
        return innovation_en_US;
    }

    public void setInnovation_en_US(String innovation_en_US) {
        this.innovation_en_US = innovation_en_US;
    }

    public String getInnovation_sv_SE() {
        return innovation_sv_SE;
    }

    public void setInnovation_sv_SE(String innovation_sv_SE) {
        this.innovation_sv_SE = innovation_sv_SE;
    }

    public String getBackgroundInformation_fi_FI() {
        return backgroundInformation_fi_FI;
    }

    public void setBackgroundInformation_fi_FI(String backgroundInformation_fi_FI) {
        this.backgroundInformation_fi_FI = backgroundInformation_fi_FI;
    }

    public String getBackgroundInformation_en_US() {
        return backgroundInformation_en_US;
    }

    public void setBackgroundInformation_en_US(String backgroundInformation_en_US) {
        this.backgroundInformation_en_US = backgroundInformation_en_US;
    }

    public String getBackgroundInformation_sv_SE() {
        return backgroundInformation_sv_SE;
    }

    public void setBackgroundInformation_sv_SE(String backgroundInformation_sv_SE) {
        this.backgroundInformation_sv_SE = backgroundInformation_sv_SE;
    }

    public String getShortDescription_fi_FI() {
        return shortDescription_fi_FI;
    }

    public void setShortDescription_fi_FI(String shortDescription_fi_FI) {
        this.shortDescription_fi_FI = shortDescription_fi_FI;
    }

    public String getShortDescription_en_US() {
        return shortDescription_en_US;
    }

    public void setShortDescription_en_US(String shortDescription_en_US) {
        this.shortDescription_en_US = shortDescription_en_US;
    }

    public String getShortDescription_sv_SE() {
        return shortDescription_sv_SE;
    }

    public void setShortDescription_sv_SE(String shortDescription_sv_SE) {
        this.shortDescription_sv_SE = shortDescription_sv_SE;
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

    public List<Report> getGenericReports() {
        return genericReports;
    }

    public void setGenericReports(List<Report> genericReports) {
        this.genericReports = genericReports;
    }

    public int getJoined() {
        return joined;
    }

    public void setJoined(int joined) {
        this.joined = joined;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getCreatorPortrait() {
        return creatorPortrait;
    }

    public void setCreatorPortrait(String creatorPortrait) {
        this.creatorPortrait = creatorPortrait;
    }

    public String getOrganizationLogo() {
        return organizationLogo;
    }

    public void setOrganizationLogo(String organizationLogo) {
        this.organizationLogo = organizationLogo;
    }

    public String getCommitmentImageUrl() {
        return commitmentImageUrl;
    }

    public void setCommitmentImageUrl(String commitmentImageUrl) {
        this.commitmentImageUrl = commitmentImageUrl;
    }


    public List<DoneOperation> getDoneOperations() {
        return doneOperations;
    }

    public void setDoneOperations(List<DoneOperation> doneOperations) {
        this.doneOperations = doneOperations;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }
}

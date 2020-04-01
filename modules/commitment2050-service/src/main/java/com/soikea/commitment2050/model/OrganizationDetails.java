package com.soikea.commitment2050.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

import com.soikea.commitment2050.model.WebsiteUrlTypeid;

@XmlRootElement
public class OrganizationDetails implements Serializable {
    private static final long serialVersionUID = 588991831306693583L;

    public OrganizationDetails() {
    }

    private String comments;
    private String companyId;
    private String countryId;
    private String createDate;
    private String logId;
    private String modifiedDate;
    private String mvccVersion;
    private String name;
    private String organizationId;
    private String parentOrganizationId;
    private boolean recursable;
    private String regionId;
    private String statusId;
    private String treePath;
    private String type;
    private String userId;
    private String userName;
    private String uuid;
    private List<WebsiteUrlTypeid> websites;
    private List<EmailAddress> emailAddresses;
    private String organizationTypeId;
    private String organizationSubTypeId;
    private String organizationSizeId;



    private String logo;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getMvccVersion() {
        return mvccVersion;
    }

    public void setMvccVersion(String mvccVersion) {
        this.mvccVersion = mvccVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getParentOrganizationId() {
        return parentOrganizationId;
    }

    public void setParentOrganizationId(String parentOrganizationId) {
        this.parentOrganizationId = parentOrganizationId;
    }

    public boolean isRecursable() {
        return recursable;
    }

    public void setRecursable(boolean recursable) {
        this.recursable = recursable;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<WebsiteUrlTypeid> getWebsites() {
        return websites;
    }

    public void setWebsites(List<WebsiteUrlTypeid> websites) {
        this.websites = websites;
    }

    public List<EmailAddress> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<EmailAddress> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String getOrganizationTypeId() {
        return organizationTypeId;
    }

    public void setOrganizationTypeId(String organizationTypeId) {
        this.organizationTypeId = organizationTypeId;
    }

    public String getOrganizationSubTypeId() {
        return organizationSubTypeId;
    }

    public void setOrganizationSubTypeId(String organizationSubTypeId) {
        this.organizationSubTypeId = organizationSubTypeId;
    }

    public String getOrganizationSizeId() {
        return organizationSizeId;
    }

    public void setOrganizationSizeId(String organizationSizeId) {
        this.organizationSizeId = organizationSizeId;
    }
}

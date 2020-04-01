package com.soikea.commitment2050.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Helper class to present statistics values
 *
 */
public class Category implements Serializable {
    private static final long serialVersionUID = 7500675260510654105L;
    private String categoryId;
    private String companyId;
    private String createDate;
    private String description;
    private String descriptionCurrentValue;
    private String leftCategoryId;
    private String groupId;
    private String name;
    private String rightCategoryId;
    private String parentCategoryId;
    private String titleCurrentValue;
    private String uuid;
    private String vocabularyId;

    public Category() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionCurrentValue() {
        return descriptionCurrentValue;
    }

    public void setDescriptionCurrentValue(String descriptionCurrentValue) {
        this.descriptionCurrentValue = descriptionCurrentValue;
    }

    public String getLeftCategoryId() {
        return leftCategoryId;
    }

    public void setLeftCategoryId(String leftCategoryId) {
        this.leftCategoryId = leftCategoryId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRightCategoryId() {
        return rightCategoryId;
    }

    public void setRightCategoryId(String rightCategoryId) {
        this.rightCategoryId = rightCategoryId;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getTitleCurrentValue() {
        return titleCurrentValue;
    }

    public void setTitleCurrentValue(String titleCurrentValue) {
        this.titleCurrentValue = titleCurrentValue;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(String vocabularyId) {
        this.vocabularyId = vocabularyId;
    }
}

package com.soikea.commitment2050.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class CommitmentCountByMainGoal implements Serializable {

    private String categoryname;
    private long categoryId;
    private int amountOfCommitments;

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public int getAmountOfCommitments() {
        return amountOfCommitments;
    }

    public void setAmountOfCommitments(int amountOfCommitments) {
        this.amountOfCommitments = amountOfCommitments;
    }
}

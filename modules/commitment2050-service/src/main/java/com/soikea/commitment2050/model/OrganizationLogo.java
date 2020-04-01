package com.soikea.commitment2050.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Organization logo
 */
@XmlRootElement
public class OrganizationLogo implements Serializable {
    private static final long serialVersionUID = 3576263407070856896L;

    /**
     * Organization id
     */
    private long organizationId;

    /**
     * Organization logo, Base64 encoded string
     */
    private String logo;

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}

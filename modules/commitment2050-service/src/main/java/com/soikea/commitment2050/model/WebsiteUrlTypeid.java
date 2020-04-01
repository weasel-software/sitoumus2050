package com.soikea.commitment2050.model;

import java.io.Serializable;

/**
 * Helper class to present websites
 *
 */
public class WebsiteUrlTypeid implements Serializable {
    private static final long serialVersionUID = 7500675260510654105L;
    private long typeId;
    private String url;

    public WebsiteUrlTypeid() {
    }

    public WebsiteUrlTypeid(long typeId, String url) {
        this.typeId = typeId;
        this.url = url;
    }

    public long getTypeId() {
        return typeId;
    }

    public String getUrl() {
        return url;
    }
}

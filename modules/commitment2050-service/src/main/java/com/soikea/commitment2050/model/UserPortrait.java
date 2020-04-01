package com.soikea.commitment2050.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * User portrait
 */
@XmlRootElement
public class UserPortrait implements Serializable {
    private static final long serialVersionUID = -310467232970680767L;
    /**
     * User id
     */
    private long userId;
    /**
     * User's portrait data, base64 encoded string
     */
    private String portrait;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}

package com.soikea.commitment2050.model;

import java.io.Serializable;

/**
 * Helper class to present EmailAddresses
 *
 */
public class EmailAddress implements Serializable {
    private static final long serialVersionUID = 7500675260510654105L;
    private long typeId;
    private String address;

    public EmailAddress() {
    }

    public EmailAddress(long typeId, String address) {
        this.typeId = typeId;
        this.address = address;
    }

    public long getTypeId() {
        return typeId;
    }

    public String getAddress() {
        return address;
    }
}

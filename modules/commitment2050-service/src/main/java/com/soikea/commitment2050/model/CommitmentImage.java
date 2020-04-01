package com.soikea.commitment2050.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class CommitmentImage implements Serializable {
    private static final long serialVersionUID = -197966463825230420L;
    /**
     * filename
     */
    private String fileName;
    /**
     * Mimetype of image
     */
    private String mimeType;

    /**
     * Commitment image data, base64 encoded string
     */
    private String imageData;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

}

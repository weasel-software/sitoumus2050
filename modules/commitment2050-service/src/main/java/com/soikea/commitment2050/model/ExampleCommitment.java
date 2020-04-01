package com.soikea.commitment2050.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class ExampleCommitment implements Serializable {

    private static final long serialVersionUID = 2624323310419435531L;

    private String id;
    private String content_fi_FI;
    private String content_en_US;
    private String content_sv_SE;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent_fi_FI() {
        return content_fi_FI;
    }

    public void setContent_fi_FI(String content_fi_FI) {
        this.content_fi_FI = content_fi_FI;
    }

    public String getContent_en_US() {
        return content_en_US;
    }

    public void setContent_en_US(String content_en_US) {
        this.content_en_US = content_en_US;
    }

    public String getContent_sv_SE() {
        return content_sv_SE;
    }

    public void setContent_sv_SE(String content_sv_SE) {
        this.content_sv_SE = content_sv_SE;
    }
}

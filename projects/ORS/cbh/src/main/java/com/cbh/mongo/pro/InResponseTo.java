package com.cbh.mongo.pro;

import javax.persistence.Embeddable;

/**
 * Created by Tommy on 2017/4/22.
 */
@Embeddable
public class InResponseTo {

    private String text;
    private String occurrence;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }




}

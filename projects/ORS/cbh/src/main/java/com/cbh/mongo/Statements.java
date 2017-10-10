package com.cbh.mongo;


import com.cbh.mongo.pro.InResponseTo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import java.util.List;

/**
 * Created by Tommy on 2017/4/22.
 */
@Document(collection="statements")
public class Statements {

    @Id
    private String id;
    private String occurrence;
    private String text;

    @Embedded
    private List<InResponseTo> in_response_to;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<InResponseTo> getIn_response_to() {
        return in_response_to;
    }

    public void setIn_response_to(List<InResponseTo> in_response_to) {
        this.in_response_to = in_response_to;
    }
}

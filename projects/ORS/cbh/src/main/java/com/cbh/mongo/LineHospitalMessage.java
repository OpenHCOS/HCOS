package com.cbh.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Tommy on 2017/5/8.
 */
@Document(collection="lineHospitalMessagesLog")
public class LineHospitalMessage extends Message{

    private String userLineId;

    public String getUserLineId() {
        return userLineId;
    }

    public void setUserLineId(String userLineId) {
        this.userLineId = userLineId;
    }
}

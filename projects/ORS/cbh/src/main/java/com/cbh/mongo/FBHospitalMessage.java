package com.cbh.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Tommy on 2017/5/13.
 */
@Document(collection="fbHospitalMessagesLog")
public class FBHospitalMessage extends Message{

    private String userFBId;

    public String getUserFBId() {
        return userFBId;
    }

    public void setUserFBId(String userFBId) {
        this.userFBId = userFBId;
    }
}

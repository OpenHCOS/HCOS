package com.cbh.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Tommy on 2017/4/23.
 */
@Document(collection="messagesLog")
public class LineMessage extends Message{

    private String userLineId;


    public String getUserLineId() {
        return userLineId;
    }

    public void setUserLineId(String userLineId) {
        this.userLineId = userLineId;
    }


}

package com.cbh.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Tommy on 2017/5/1.
 */
@Document(collection="fbMessagesLog")
public class FBMessage  extends Message{

    private String messagerId;

    public String getMessagerId() {
        return messagerId;
    }

    public void setMessagerId(String messagerId) {
        this.messagerId = messagerId;
    }
}

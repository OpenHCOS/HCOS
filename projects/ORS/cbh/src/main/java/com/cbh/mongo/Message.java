package com.cbh.mongo;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Tommy on 2017/5/1.
 */
public class Message {

    @Id
    private String id;

    private String askMessage;
    private String responseMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAskMessage() {
        return askMessage;
    }

    public void setAskMessage(String askMessage) {
        this.askMessage = askMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

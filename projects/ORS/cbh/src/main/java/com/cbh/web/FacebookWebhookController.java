package com.cbh.web;

import com.cbh.entity.Disease;
import com.cbh.mongo.FBHospitalMessage;
import com.cbh.service.FBHospitalMessageMongoService;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.restfb.*;
import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.Message;
import com.restfb.types.send.SendResponse;
import com.restfb.types.webhook.WebhookEntry;
import com.restfb.types.webhook.WebhookObject;
import com.restfb.types.webhook.messaging.MessagingAttachment;
import com.restfb.types.webhook.messaging.MessagingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Tommy on 2017/4/26.
 */
@RequestMapping("/facebook")
@Controller
public class FacebookWebhookController {

    @Autowired
    FBHospitalMessageMongoService fbHospitalMessageMongoService;

    @RequestMapping(value = "/webhook",method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public String gallery(HttpServletRequest httpServletRequest, @RequestParam(value = "ask",required = false)String ask,
                                                                 @RequestParam(value = "hub.mode",required = false)String mode,
                                                                 @RequestParam(value = "hub.challenge",required = false)String challenge,
                                                                 @RequestParam(value = "hub.verify_token",required = false)String verifyToken) {
        System.out.println("httpServletRequest.getQueryString() : "+httpServletRequest.getQueryString());
        try{
        JsonMapper mapper = new DefaultJsonMapper();
        WebhookObject webhookObject =
                mapper.toJavaObject(httpServletRequest.getQueryString(), WebhookObject.class);
        for(WebhookEntry webhookEntry: webhookObject.getEntryList()){
            webhookEntry.getClass();
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(mode);
        System.out.println(challenge);
        System.out.println(verifyToken);
        return challenge;
    }

    @RequestMapping(value = "/webhook",method = RequestMethod.POST, produces = "text/plain")
    @ResponseBody
    public String getMessenger(HttpServletRequest httpServletRequest, @RequestParam(value = "ask",required = false)String ask,
                          @RequestParam(value = "hub.mode",required = false)String mode,
                          @RequestParam(value = "hub.challenge",required = false)String challenge,
                          @RequestParam(value = "hub.verify_token",required = false)String verifyToken) {

        try{
            String request=getRequestBodyAsString(httpServletRequest);
            System.out.println("getRequestBodyAsString(httpServletRequest) : "+request);
            JsonMapper mapper = new DefaultJsonMapper();
            WebhookObject webhookObject =
                    mapper.toJavaObject(request, WebhookObject.class);
            for(WebhookEntry webhookEntry: webhookObject.getEntryList()){
                for(MessagingItem messageItem:webhookEntry.getMessaging()){
                    System.out.println("messageItem.getSender() : "+messageItem.getSender().getId());
                    System.out.println("messageItem.getRecipient() : "+messageItem.getRecipient().getId());
                    if(messageItem.getMessage()!=null){
                        System.out.println("messageItem.getText() : "+messageItem.getMessage().getText());
                        String sentence=messageItem.getMessage().getText();
                        if(messageItem.getRecipient().getId().equals("218654758629408")) {
                            JiebaSegmenter segmenter = new JiebaSegmenter();
                            System.out.println(segmenter.process(sentence, JiebaSegmenter.SegMode.SEARCH).toString());
                            List<SegToken> segTokenList = segmenter.process(sentence, JiebaSegmenter.SegMode.SEARCH);
                            List<Disease> diseaseList = null;
                            for (SegToken segToken : segTokenList) {
                                System.out.println(segToken.word.toString());
                                diseaseList = Disease.findAllLikeDiseases(segToken.word.trim());
                                if (diseaseList.size() > 0) {
                                    break;
                                }
                            }
                            if (diseaseList != null) {
                                for (Disease disease : diseaseList) {
                                    for (SegToken segToken : segTokenList) {
                                        if (disease.getSymptom().contains(segToken.word)) {
                                            disease.setOrderDis(disease.getOrderDis() + 1);
                                        }
                                    }
                                }
                            }

                            Collections.sort(diseaseList, new Comparator<Disease>() {
                                @Override
                                public int compare(Disease lhs, Disease rhs) {
                                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                                    return lhs.getOrderDis() > rhs.getOrderDis() ? -1 : (lhs.getOrderDis() < rhs.getOrderDis()) ? 1 : 0;
                                }
                            });
                            String responseCat = "";
                            for (Disease disease : diseaseList) {
                                responseCat += "" + disease.getCategory() + ",";
                                System.out.println("症狀 : "+disease.getCategory());
                            }
                            IdMessageRecipient recipient = new IdMessageRecipient(messageItem.getSender().getId());
                            if ("".equals(responseCat)) {
                                responseCat = "對不起,目前資料不足無法分析!";
                            }
                            Message simpleTextMessage = new Message(responseCat);

                            String pageAccessToken = "EAAFPgrP0FZC4BAAcZCw66JNdgnZC5sBp8DVBZCrHggdS3hLONKsPLWb6tfZCv7PHEEXZAPjcWZCp0v2QyGC3oqMs7atPC1H5bAvSEnopTOpY3fe4glJIlXa8WwaYyXxJtRXQ2pc7qX2ZAWelsR7iIbsvh2Y8wiIgec6JUOxPOZBvoKgZDZD";

                            // create a version 2.8 client
                            FacebookClient pageClient = new DefaultFacebookClient(pageAccessToken, Version.VERSION_2_8);
                            SendResponse resp = pageClient.publish("me/messages", SendResponse.class,
                                    Parameter.with("recipient", recipient), // the id or phone recipient
                                    Parameter.with("message", simpleTextMessage));

                            FBHospitalMessage fbHospitalMessage=new FBHospitalMessage();
                            fbHospitalMessage.setAskMessage(ask);
                            fbHospitalMessage.setCreateDate(new Date());
                            fbHospitalMessage.setUserFBId(recipient.getId());
                            fbHospitalMessage.setResponseMessage(simpleTextMessage.getText());
                            fbHospitalMessageMongoService.save(fbHospitalMessage);

                            if (messageItem.getMessage().getAttachments() != null) {
                                for (MessagingAttachment messagingAttachment : messageItem.getMessage().getAttachments()) {
                                    System.out.println("messageItem.getRecipient() : " + messagingAttachment.getPayload());
                                }

                            }
                        }
                    }

                }
                System.out.println(webhookEntry.getClass().getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(mode);
        System.out.println(challenge);
        System.out.println(verifyToken);
        return challenge;
    }

    protected String getRequestBodyAsString(HttpServletRequest req) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

}

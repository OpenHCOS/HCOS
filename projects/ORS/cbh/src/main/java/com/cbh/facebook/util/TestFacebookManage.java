package com.cbh.facebook.util;

import com.cbh.mongo.HospitalGeoResponse;
import com.cbh.service.HospitalGeoMongoService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.send.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tommy on 2017/5/14.
 */
public class TestFacebookManage {
    @Autowired
    HospitalGeoMongoService hospitalGeoMongoService;

    public static void main(String[] args) {

        String pageAccessToken = "EAAFPgrP0FZC4BAAcZCw66JNdgnZC5sBp8DVBZCrHggdS3hLONKsPLWb6tfZCv7PHEEXZAPjcWZCp0v2QyGC3oqMs7atPC1H5bAvSEnopTOpY3fe4glJIlXa8WwaYyXxJtRXQ2pc7qX2ZAWelsR7iIbsvh2Y8wiIgec6JUOxPOZBvoKgZDZD";

        // create a version 2.8 client
        FacebookClient pageClient = new DefaultFacebookClient(pageAccessToken, Version.VERSION_2_8);
        List<PersistentMenuModify> persistentMenuList=new ArrayList<PersistentMenuModify>();
        PersistentMenuModify persistentMenu=new PersistentMenuModify("default");
        WebButton menuItem=new WebButton("test Title","https://www.google.com");
        menuItem.setMessengerExtensions(false,"");
        menuItem.setWebviewHeightRatio(WebviewHeightEnum.tall);
        NestedButtonModify nested = new NestedButtonModify("My Account");
        nested.addCallToAction(new PostbackButton("Pay Bill", "PAYBILL_PAYLOAD"));
        nested.addCallToAction(new PostbackButton("History", "HISTORY_PAYLOAD"));
        nested.addCallToAction(new PostbackButton("Contact Info", "CONTACT_INFO_PAYLOAD"));

        persistentMenu.setComposerInputDisabled(false);
        persistentMenu.addCallToAction(nested);
     //   persistentMenu.addCallToAction(menuItem);
        persistentMenuList.add(persistentMenu);

        JsonObject resp = pageClient.publish("me/messenger_profile", JsonObject.class,
                Parameter.with("persistent_menu", persistentMenuList));
        System.out.println(resp.toString());

    }
}

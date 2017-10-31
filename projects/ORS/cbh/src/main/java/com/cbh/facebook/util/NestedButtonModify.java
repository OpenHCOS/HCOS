package com.cbh.facebook.util;

import com.restfb.Facebook;
import com.restfb.types.send.MenuItem;
import com.restfb.types.send.NestedButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tommy on 2017/5/14.
 */
public class NestedButtonModify extends NestedButton {

    @Facebook("call_to_actions")
    private List<MenuItem> callToActions;

    public NestedButtonModify(String s) {
        super(s);
    }

    public void addCallToAction(MenuItem var1) {
        if(this.callToActions == null) {
            this.callToActions = new ArrayList();
        }

        this.callToActions.add(var1);
    }

    public List<MenuItem> getCallToActions() {
        return this.callToActions;
    }
}

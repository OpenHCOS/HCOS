package com.cbh.facebook.util;

import com.restfb.Facebook;
import com.restfb.types.send.MenuItem;
import com.restfb.types.send.PersistentMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Tommy on 2017/5/14.
 */
public class PersistentMenuModify extends PersistentMenu {

    public PersistentMenuModify(){
        super();
    }

    public PersistentMenuModify(String var1) {
        super(var1);
    }

    public PersistentMenuModify(Locale var1) {
        this(var1.toString().toLowerCase());
    }

    @Facebook("call_to_actions")
    private List<MenuItem> callToActions;

    @Override
    public void addCallToAction(MenuItem var1) {
        if(this.callToActions == null) {
            this.callToActions = new ArrayList();
        }

        this.callToActions.add(var1);
    }

    @Override
    public List<MenuItem> getCallToActions() {
        return this.callToActions;
    }
}

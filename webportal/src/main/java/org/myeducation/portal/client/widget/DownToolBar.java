package org.myeducation.portal.client.widget;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 28.03.13
 * Time: 0:15
 * To change this template use File | Settings | File Templates.
 */
public class DownToolBar extends VLayout {
    public DownToolBar(){
        Label label = new Label("MyEducation.com");
        label.setAlign(Alignment.CENTER);
        addMember(label);
    }
}

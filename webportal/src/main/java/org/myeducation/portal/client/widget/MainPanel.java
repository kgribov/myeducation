package org.myeducation.portal.client.widget;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 28.03.13
 * Time: 0:12
 * To change this template use File | Settings | File Templates.
 */
public class MainPanel extends VLayout {

    private VLayout container = new VLayout();

    public MainPanel(){
        setMembersMargin(40);
        setWidth100();
        setHeight100();

        addMember(new LoginToolBar());
        addMember(container);
        addMember(new DownToolBar());
    }

    public void setContent(Widget content){
        container.clear();
        container.addMember(content);
    }
}

package org.myeducation.portal.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ClientService implements EntryPoint {

    public void onModuleLoad() {
        RootPanel.get().add(createToolBar());
    }

    public Layout createToolBar(){

        String color = "#CCFF99";

        HLayout panel = new HLayout();
        panel.setHeight("50dp");
        panel.setWidth100();

        Label label = new Label();
        //label.setAlign(Alignment.LEFT);
        label.setContents("Banner panel");
        label.setBackgroundColor(color);
        label.setWidth100();

        HLayout buttonsPanel = new HLayout();
        buttonsPanel.setBackgroundColor(color);
        buttonsPanel.setLayoutMargin(7);
        buttonsPanel.setMembersMargin(7);
        buttonsPanel.setDefaultLayoutAlign(VerticalAlignment.CENTER);

        Label aboutProject = new Label("О проекте");


        Label userName = new Label("Грибов Кирилл");

        buttonsPanel.addMember(userName);
        buttonsPanel.addMember(aboutProject);


        panel.addMember(label);
        panel.addMember(buttonsPanel);

        return panel;
    }
}

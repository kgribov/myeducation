package org.myeducation.portal.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.MouseDownEvent;
import com.smartgwt.client.widgets.events.MouseDownHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.HStack;


/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 13.02.13
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class LoginToolBar extends HLayout{

    //will be changed to css style
    private String color = "black";
    private String devMode = "?gwt.codesvr=127.0.0.1:9997";
    //private String devMode = "";

    public LoginToolBar(){
        init();
    }

    private void init(){
        setDefaultLayoutAlign(VerticalAlignment.CENTER);
        setBackgroundColor(color);
        setLayoutMargin(10);
        setHeight("50dp");
        setWidth100();

        Label banner = new Label();
        banner.setContents("<font color='white' size='4em' family='Geneva'>" +
                            "MYeducation" +
                            "</font>");
        banner.setWidth100();
        banner.setBackgroundColor(color);

        HStack linkPanel = new HStack();
        linkPanel.setBackgroundColor(color);
        linkPanel.setMembersMargin(35);
        linkPanel.setLayoutRightMargin(20);

        HyperLinkButton userLogin = new HyperLinkButton("Log in");
        userLogin.addMouseDownHandler(new MouseDownHandler() {
            public void onMouseDown(MouseDownEvent mouseDownEvent) {
                Window.Location.assign(GWT.getHostPageBaseURL()+"Login.html"+devMode);
            }
        });

        HyperLinkButton faqLink = new HyperLinkButton("FAQ");
        faqLink.addMouseDownHandler(new MouseDownHandler() {
            public void onMouseDown(MouseDownEvent mouseDownEvent) {
                Window.Location.assign(GWT.getHostPageBaseURL()+"FAQ.html"+devMode);
            }
        });
        HyperLinkButton aboutLink = new HyperLinkButton("О проекте");
        aboutLink.addMouseDownHandler(new MouseDownHandler() {
            public void onMouseDown(MouseDownEvent mouseDownEvent) {
                Window.Location.assign(GWT.getHostPageBaseURL()+"About.html"+devMode);
            }
        });

        linkPanel.addMember(userLogin);
        linkPanel.addMember(faqLink);
        linkPanel.addMember(aboutLink);

        addMember(banner);
        addMember(linkPanel);
    }
}

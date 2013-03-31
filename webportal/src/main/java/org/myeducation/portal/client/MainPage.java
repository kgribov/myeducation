package org.myeducation.portal.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.Label;
import org.myeducation.portal.client.widget.DownloadWidget;
import org.myeducation.portal.client.widget.LoginToolBar;
import org.myeducation.portal.client.widget.MainPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainPage implements EntryPoint {

    private MainPanel panel = new MainPanel();

    public void onModuleLoad() {
        //Window.Location.assign(GWT.getHostPageBaseURL() + "Login.html");
        RootPanel.get().add(panel);
    }
}

package org.myeducation.portal.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import org.myeducation.portal.client.widget.LoginToolBar;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainPage implements EntryPoint {

    public void onModuleLoad() {
        RootPanel.get().add(new LoginToolBar());
    }
}

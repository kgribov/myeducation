package org.myeducation.portal.client.widget;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 13.02.13
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
public class HyperLinkButton extends Label{
    public HyperLinkButton(String content){
        super(content);
        init();
    }

//  Wrong to hardcore colors!!! Change!!!
    private void init(){
        addStyleName("customLink");
        setWrap(false);
        setWidth(getContents().length()*6);
        setAlign(Alignment.CENTER);
        addMouseOverHandler(new MouseOverHandler() {
            public void onMouseOver(MouseOverEvent mouseOverEvent) {
                addStyleName("linkOnMouseOver");
                setCursor(Cursor.HAND);
            }
        });

        addMouseOutHandler(new MouseOutHandler() {
            public void onMouseOut(MouseOutEvent mouseOutEvent) {
                addStyleName("customLink");
                setCursor(Cursor.DEFAULT);
            }
        });
    }
}

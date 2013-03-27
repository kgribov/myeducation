package org.myeducation.portal.client.widget;


import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 27.03.13
 * Time: 23:16
 * To change this template use File | Settings | File Templates.
 */
public class DownloadWidget extends HLayout {

    private TextItem fileArea = new TextItem();
    private Button chooseFile = new Button("Выбрать");

    public DownloadWidget(){
        setMembersMargin(10);
        fileArea.setTitle("Файл");
        fileArea.setRequired(true);
        fileArea.setWidth(300);

        DynamicForm form = new DynamicForm();
        form.setFields(fileArea);
        addMember(form);
        addMember(chooseFile);
    }
}

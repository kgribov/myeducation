package org.myeducation.portal.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.AutoFitWidthApproach;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import org.myeducation.portal.client.dto.TaskInfo;
import org.myeducation.portal.client.dto.TaskSendDto;
import org.myeducation.portal.client.dto.TaskSendResult;
import org.myeducation.portal.client.widget.MainPanel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainPage implements EntryPoint {

    private MainPanel panel = new MainPanel();

    public void onModuleLoad() {
        HLayout hLayout = new HLayout();

        final TextItem testName = new TextItem();
        testName.setWidth("*");
        testName.setTitle("Тест");
        testName.setCanEdit(false);

        final TextAreaItem description = new TextAreaItem();
        description.setWidth("*");
        description.setHeight("400px");
        description.setTitle("Описание");
        description.setCanEdit(false);

        final TextAreaItem fileUpload = new TextAreaItem();
        fileUpload.setWidth("*");
        fileUpload.setHeight("600px");
        fileUpload.setTitle("Введите данные");

        ButtonItem sendButton = new ButtonItem();
        sendButton.setTitle("Отправить");

        DynamicForm form = new DynamicForm();
        form.setFields(testName, description, fileUpload, sendButton);

        hLayout.addMember(form);

        //grid
        final ListGrid grid = new ListGrid();

        ListGridField userField = new ListGridField("user");
        userField.setAutoFitWidthApproach(AutoFitWidthApproach.VALUE);
        userField.setTitle("Пользователь");

        ListGridField pointsField = new ListGridField("points");
        pointsField.setAutoFitWidthApproach(AutoFitWidthApproach.VALUE);
        pointsField.setTitle("Баллы");

        ListGridField minPoints = new ListGridField("minPoints");
        minPoints.setAutoFitWidthApproach(AutoFitWidthApproach.VALUE);
        minPoints.setTitle("Мин. кол-во баллов");

        ListGridField summaryField = new ListGridField("summary");
        summaryField.setAutoFitWidthApproach(AutoFitWidthApproach.VALUE);
        summaryField.setTitle("Статус");

        ListGridField timeField = new ListGridField("time");
        timeField.setAutoFitWidthApproach(AutoFitWidthApproach.VALUE);
        timeField.setTitle("Время");


        grid.setFields(userField, pointsField, minPoints, summaryField, timeField);
        grid.setData(new ResultRecord[]{});

        hLayout.addMember(grid);

        panel.setContent(hLayout);
        RootPanel.get().add(panel);

        //load task info
        TaskLoad.App.getInstance().getTaskInfo(1, new AsyncCallback<TaskInfo>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(TaskInfo taskInfo) {
                testName.setValue(taskInfo.getName());
                description.setValue(taskInfo.getDescription());
            }
        });

        Timer timer = new Timer() {
            @Override
            public void run() {
                TaskLoad.App.getInstance().getResults(1, new AsyncCallback<List<TaskSendResult>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onSuccess(List<TaskSendResult> taskSendResults) {
                        ResultRecord[] records = new ResultRecord[taskSendResults.size()];
                        int index = 0;
                        for (TaskSendResult taskSendResult : taskSendResults){
                            records[index++] = new ResultRecord(taskSendResult);
                        }
                        grid.setData(records);
                    }
                });
            }
        };
        timer.scheduleRepeating(3000);
        timer.run();

        sendButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                TaskSendDto dto = new TaskSendDto();
                dto.setTaskId(1);
                dto.setUserId(1);
                HashMap<Long, Object> map = new HashMap<Long, Object>();

                map.put(new Long(1), fileUpload.getValue());

                dto.setAttachDatas(map);

                TaskLoad.App.getInstance().sendTaskSend(dto, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        Window.alert("Решение было отправлено");
                    }
                });
            }
        });
    }

    public class ResultRecord extends ListGridRecord{
        public ResultRecord(TaskSendResult result){
            setAttribute("user", result.getUser());
            setAttribute("points", result.getPoints());
            setAttribute("summary", result.getStatus());
            setAttribute("minPoints", result.getMinPoints());
            setAttribute("time", DateTimeFormat.getFormat("dd.MM.yy 'at' HH:mm:ss").format(new Date(result.getTime())));
        }
    }
}

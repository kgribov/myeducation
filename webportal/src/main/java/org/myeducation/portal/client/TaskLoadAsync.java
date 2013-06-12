package org.myeducation.portal.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.myeducation.portal.client.dto.TaskInfo;
import org.myeducation.portal.client.dto.TaskSendDto;
import org.myeducation.portal.client.dto.TaskSendResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 31.05.13
 * Time: 1:11
 * To change this template use File | Settings | File Templates.
 */
public interface TaskLoadAsync {
    void getTaskInfo(long id, AsyncCallback<TaskInfo> async);

    void getResults(long taskId, AsyncCallback<List<TaskSendResult>> async);

    void sendTaskSend(TaskSendDto taskSendDto, AsyncCallback<Void> async);
}

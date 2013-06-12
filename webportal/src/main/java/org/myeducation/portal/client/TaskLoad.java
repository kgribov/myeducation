package org.myeducation.portal.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
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
@RemoteServiceRelativePath("TaskLoad")
public interface TaskLoad extends RemoteService {
    /**
     * Utility/Convenience class.
     * Use TaskLoad.App.getInstance() to access static instance of TaskLoadAsync
     */
    public TaskInfo getTaskInfo(long id);
    public List<TaskSendResult> getResults(long taskId);
    public void sendTaskSend(TaskSendDto taskSendDto);

    public static class App {
        private static final TaskLoadAsync ourInstance = (TaskLoadAsync) GWT.create(TaskLoad.class);

        public static TaskLoadAsync getInstance() {
            return ourInstance;
        }
    }
}

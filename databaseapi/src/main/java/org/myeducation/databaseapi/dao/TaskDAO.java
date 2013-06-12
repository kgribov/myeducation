package org.myeducation.databaseapi.dao;

import org.myeducation.databaseapi.entities.*;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 17.02.13
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
public interface TaskDAO {
    void addTask(Task task);
    void addTaskSend(TaskSend taskSend);
    void addTestResult(TestDataResult result);
    Task getTask(long id);
    TaskSend getTaskSend(long id);
    List<Object[]> getNotProcessTestDatas();
    Object[] getExecuteData(Long dataId, Long testsId);
    List<TestDataResult> getResults(long taskId);
    AttachData getAttachData(long id);
    AttachDataType getAttachDataType(long id);
}

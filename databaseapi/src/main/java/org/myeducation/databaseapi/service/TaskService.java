package org.myeducation.databaseapi.service;

import org.myeducation.databaseapi.entities.Task;
import org.myeducation.databaseapi.entities.TestDataResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 02.06.13
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */
public interface TaskService {
    public Task getTask(long id);
    public List<TestDataResult> getResults(long taskId);
}

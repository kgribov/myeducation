package org.myeducation.databaseapi.service.local;

import org.myeducation.databaseapi.dao.Dao;
import org.myeducation.databaseapi.entities.Task;
import org.myeducation.databaseapi.entities.TestDataResult;
import org.myeducation.databaseapi.service.TaskService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 02.06.13
 * Time: 17:49
 * To change this template use File | Settings | File Templates.
 */
public class TaskServiceLocal implements TaskService {

    public Task getTask(long id) {
        return Dao.getFactory().createTaskDao().getTask(id);
    }

    public List<TestDataResult> getResults(long taskId){
        return Dao.getFactory().createTaskDao().getResults(taskId);
    }
}

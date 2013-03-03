package org.myeducation.databaseapi.dao;

import org.myeducation.databaseapi.entities.Task;
import org.myeducation.databaseapi.entities.TestData;

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
}

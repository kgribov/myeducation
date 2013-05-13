package org.myeducation.databaseapi.dao.hibernate;

import org.myeducation.databaseapi.dao.TaskDAO;
import org.myeducation.databaseapi.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 17.02.13
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
//TODO
//    fix critic situations!!!
public class TaskHibernateDAO implements TaskDAO {

    private EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("server");

    public void addTask(Task task){
        storeObject(task);
    }

    public void addTaskSend(TaskSend taskSend) {
        mergeObject(taskSend);
    }

    public void addTestResult(TestDataResult result) {
        mergeObject(result);
    }

    public Task getTask(long id) {
        EntityManager manager = managerFactory.createEntityManager();
        Task task = (Task)manager.createQuery("select task from Task as task where task.id=:id").setParameter("id", id).getSingleResult();
        manager.close();
        return task;
    }

    public TaskSend getTaskSend(long id) {
        EntityManager manager = managerFactory.createEntityManager();
        TaskSend tasksend = (TaskSend)manager.createQuery("select tasksend from TaskSend as tasksend where tasksend.id=:id").setParameter("id", id).getSingleResult();
        manager.close();
        return tasksend;
    }

    public List<Object[]> getNotProcessTestDatas(){
        EntityManager manager = managerFactory.createEntityManager();
        List<Object[]> result = manager.createQuery("select file.id, test.id from AttachData file, TestDatas test " +
                                                    "where not exists (from TestDataResult as result where result.attachData=file and result.testData in elements(test.testDatas))").getResultList();
        manager.close();
        return result;
    }

    public Object[] getExecuteData(Long dataId, Long testsId){
        EntityManager manager = managerFactory.createEntityManager();
        Object[] result = (Object[])manager.createQuery("select file, tests from AttachData as file, TestDatas as tests where file.id=:dataId and tests.id=:testsId")
                                                    .setParameter("dataId", dataId).setParameter("testsId", testsId).getSingleResult();
        manager.close();
        return result;
    }

    protected void storeObject(Object o){
        EntityManager manager = managerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(o);
        transaction.commit();
        manager.close();
    }

    protected void mergeObject(Object o){
        EntityManager manager = managerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.merge(o);
        transaction.commit();
        manager.close();
    }
}

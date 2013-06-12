package org.myeducation.databaseapi.dao.hibernate;

import org.myeducation.databaseapi.dao.TaskDAO;
import org.myeducation.databaseapi.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;
import java.util.Collections;
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

    private EntityManager manager;

    public TaskHibernateDAO(EntityManager manager){
        this.manager = manager;
    }

    public void addTask(Task task){
        storeObject(task);
    }

    public void addTaskSend(TaskSend taskSend) {
        mergeObject(taskSend);
    }

    public void addTestResult(TestDataResult result) {
        mergeObject(result);
    }

    public AttachData getAttachData(long id){
        return (AttachData)manager.createQuery("select data from AttachData data where id=:id").setParameter("id", id).getSingleResult();
    }

    public AttachDataType getAttachDataType(long id){
        return (AttachDataType)manager.createQuery("select data from AttachDataType data where id=:id").setParameter("id", id).getSingleResult();
    }

    public Task getTask(long id) {
        Task task = (Task)manager.createQuery("select task from Task as task where task.id=:id").setParameter("id", id).getSingleResult();
        return task;
    }

    public TaskSend getTaskSend(long id) {
        TaskSend tasksend = (TaskSend)manager.createQuery("select tasksend from TaskSend as tasksend where tasksend.id=:id").setParameter("id", id).getSingleResult();
        return tasksend;
    }

    public List<Object[]> getNotProcessTestDatas(){
        List<Object[]> result = manager.createQuery("select file.id, test.id from AttachData file, TestDatas test " +
                                                    "where not exists (from TestDataResult as result where result.attachData=file and result.testData in elements(test.testDatas))").getResultList();
        return result;
    }

    public Object[] getExecuteData(Long dataId, Long testsId){
        Object[] result = (Object[])manager.createQuery("select file, tests from AttachData as file, TestDatas as tests where file.id=:dataId and tests.id=:testsId")
                                                    .setParameter("dataId", dataId).setParameter("testsId", testsId).getSingleResult();
        if (result==null)
            result = new Object[]{};

        return result;
    }

    public List<TestDataResult> getResults(long taskId) {
        List result = manager.createQuery("select result from TestDataResult result where result.testData.testDatas.attachDataType.task.id=:taskId").setParameter("taskId", taskId).getResultList();
        return (result!=null ? result : Collections.EMPTY_LIST);
    }

    protected void storeObject(Object o){
        EntityTransaction transaction = manager.getTransaction();
        if (!transaction.isActive())
            transaction.begin();

        manager.persist(o);
        transaction.commit();
    }

    protected void mergeObject(Object o){
        EntityTransaction transaction = manager.getTransaction();
        if (!transaction.isActive())
            transaction.begin();

        manager.merge(o);
        transaction.commit();
    }
}

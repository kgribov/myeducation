package org.myeducation.databaseapi.dao.hibernate;

import org.myeducation.databaseapi.dao.TaskDAO;
import org.myeducation.databaseapi.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("server");

    public void addTask(Task task){
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(task);
        manager.getTransaction().commit();
    }
}

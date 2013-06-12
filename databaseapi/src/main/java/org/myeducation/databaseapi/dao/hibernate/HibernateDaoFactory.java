package org.myeducation.databaseapi.dao.hibernate;

import org.myeducation.databaseapi.dao.DaoFactory;
import org.myeducation.databaseapi.dao.TaskDAO;
import org.myeducation.databaseapi.dao.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 17.02.13
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
public class HibernateDaoFactory implements DaoFactory {

    private static EntityManager manager = Persistence.createEntityManagerFactory("server").createEntityManager();

    private static UserDAO userDAO = new UserHibernateDAO(manager);
    private static TaskDAO taskDAO = new TaskHibernateDAO(manager);

    public UserDAO createUserDao() {
        return userDAO;
    }

    public TaskDAO createTaskDao() {
        return taskDAO;
    }
}

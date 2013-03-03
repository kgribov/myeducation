package org.myeducation.databaseapi.dao.hibernate;

import org.myeducation.databaseapi.dao.DaoFactory;
import org.myeducation.databaseapi.dao.TaskDAO;
import org.myeducation.databaseapi.dao.UserDAO;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 17.02.13
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
public class HibernateDaoFactory implements DaoFactory {
    public UserDAO createUserDao() {
        return new UserHibernateDAO();
    }

    public TaskDAO createTaskDao() {
        return new TaskHibernateDAO();
    }
}

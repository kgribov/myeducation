package org.myeducation.databaseapi.dao;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 17.02.13
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
public interface DaoFactory {
    UserDAO createUserDao();
    TaskDAO createTaskDao();
}

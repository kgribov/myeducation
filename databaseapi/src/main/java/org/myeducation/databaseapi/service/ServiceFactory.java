package org.myeducation.databaseapi.service;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 17.02.13
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
public interface ServiceFactory {
    AuthService authService();
    ExecutorSaveService executorSaveService();
}

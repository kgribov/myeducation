package org.myeducation.databaseapi.dao;

import org.myeducation.databaseapi.entities.User;
import org.myeducation.databaseapi.entities.UserLogin;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 12.02.13
 * Time: 23:03
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO {

    public void addUser(String login, String password, User data);

    public void updateUserInfo(String login, User user);

    public void updateUserLogin(String oldLogin, String newLogin);

    public void removeUser(int id);

    public void removeUser(String login);

    public UserLogin getLogin(long id);

    public UserLogin getLogin(String login);
}

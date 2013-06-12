package org.myeducation.databaseapi.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.myeducation.databaseapi.dao.UserDAO;
import org.myeducation.databaseapi.entities.User;
import org.myeducation.databaseapi.entities.UserLogin;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 11.02.13
 * Time: 0:05
 * To change this template use File | Settings | File Templates.
 */
//TODO
//    fix critic situations!!!
public class UserHibernateDAO implements UserDAO{

    private EntityManager manager;

    public UserHibernateDAO(EntityManager manager){
        this.manager = manager;
    }

    public void addUser(String login, String password, User userInfo){

        manager.getTransaction().begin();

        UserLogin userLogin = new UserLogin();
        userLogin.setLogin(login);
        userLogin.setUser(userInfo);
        userLogin.setPassword(password);

        manager.persist(userLogin);

        manager.getTransaction().commit();
    }

    public void updateUserInfo(String login, User user){

    }

    public void updateUserLogin(String oldLogin, String newLogin){

    }

    public void removeUser(int id){
        manager.getTransaction().begin();

        UserLogin login = getLogin(id);

        manager.remove(login);

        manager.getTransaction().commit();
    }

    public void removeUser(String login){
        manager.getTransaction().begin();
        UserLogin user = getLogin(login);
        UserLogin removeUser = manager.find(UserLogin.class, user.getId());
        manager.remove(removeUser);
        manager.getTransaction().commit();
    }

    public UserLogin getLogin(long id){
        UserLogin userLogin = (UserLogin)manager.createQuery("select login from UserLogin login where id=:id").setParameter("id" , (int)id).getSingleResult();
        return userLogin;
    }

    public UserLogin getLogin(String login){
        UserLogin userLogin = (UserLogin)manager.createQuery("select login from UserLogin login where login=:login").setParameter("login" , login).getSingleResult();
        return userLogin;
    }
}

package org.myeducation.databaseapi.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.myeducation.databaseapi.dao.UserDAO;
import org.myeducation.databaseapi.entities.User;
import org.myeducation.databaseapi.entities.UserLogin;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 11.02.13
 * Time: 0:05
 * To change this template use File | Settings | File Templates.
 */
public class UserHibernateDAO implements UserDAO{

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void addUser(String login, String password, User userInfo){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();

            UserLogin userLogin = new UserLogin();
            userLogin.setLogin(login);

            User user = userInfo;

            userLogin.setUser(user);
            userLogin.setPassword(password);

            session.save(userLogin);
            session.save(userInfo);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public void updateUserInfo(String login, User user){

    }

    public void updateUserLogin(String oldLogin, String newLogin){

    }

    public void removeUser(int id){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();

            UserLogin login = getLogin(id);
            User data = login.getUser();

            session.delete(login);
            session.delete(data);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public void removeUser(String login){
        removeUser(getLogin(login).getId());
    }

    public UserLogin getLogin(int id){
        UserLogin login = null;
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();

            login = (UserLogin)session.get(UserLogin.class, id);

            session.getTransaction().commit();
            return login;
        }finally {
            if(session!=null) session.close();
        }
    }

    public UserLogin getLogin(String login){
        UserLogin userLogin = null;
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();

            userLogin = (UserLogin)session.createCriteria(UserLogin.class).
                    add(Restrictions.eq("login", login)).uniqueResult();

            session.getTransaction().commit();
            return userLogin;
        }finally {
            if (session!=null) session.close();
        }
    }

}

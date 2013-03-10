package org.myeducation.databaseapi.dao;

import org.myeducation.databaseapi.dao.hibernate.HibernateDaoFactory;
import org.myeducation.properties.PropertiesFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 17.02.13
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class Dao {

    private static Logger logger = Logger.getLogger(Dao.class.getName());

    private static volatile DaoFactory daoFactory = null;

    private Dao(){
    }

    public static DaoFactory getFactory(){
        DaoFactory factory = daoFactory;
        if (factory == null){
            synchronized (DaoFactory.class){
                factory = daoFactory;
                if (factory == null){
                    Properties properties = PropertiesFactory.getProperties("dao");
                    String daoType = (String)properties.get("dao.factory.type");
                    if (daoType.equals("hibernate")){
                        daoFactory = factory = new HibernateDaoFactory();
                        return daoFactory;
                    }
                }
            }
        }
        return daoFactory;
    }
}

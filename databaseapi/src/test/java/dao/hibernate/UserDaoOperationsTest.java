package dao.hibernate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.myeducation.databaseapi.dao.Dao;
import org.myeducation.databaseapi.dao.UserDAO;
import org.myeducation.databaseapi.entities.User;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 10.02.13
 * Time: 20:07
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoOperationsTest {

    private UserDAO userDAO;

    private String login = "login";
    private String password = "password";

    private String firstName = "Kirill";
    private String secondName = "Gribov";
    private String telephone = "+79602873422";

    @Before
    public void init(){
        userDAO = Dao.getFactory().createUserDao();
    }

    @Test
    public void checkCreateUser(){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(secondName);
        user.setCreateDate(System.currentTimeMillis());
        user.setPhoneNumber(telephone);
        userDAO.addUser(login, password, user);
    }

    @Test
    public void checkUserPassword(){
        String userPassword = userDAO.getLogin(login).getPassword();
        Assert.assertEquals(userPassword, password);
    }

    @Test
    public void checkUserData(){
        User user = userDAO.getLogin(login).getUser();

        Assert.assertEquals(user.getFirstName(), firstName);
        Assert.assertEquals(user.getLastName(), secondName);
        Assert.assertEquals(user.getPhoneNumber(), telephone);
    }

    @Test
    public void checkRemoveUser(){
        userDAO.removeUser(login);
    }
}

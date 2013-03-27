import junit.framework.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created with IntelliJ IDEA.
 * UserLogin: kirilkadurilka
 * Date: 10.02.13
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 */
public class DbConnectionTest {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("server");

    @Test
    public void testConnection(){
        EntityManager manager = factory.createEntityManager();
        Assert.assertNotNull(manager);
        manager.close();
    }
}

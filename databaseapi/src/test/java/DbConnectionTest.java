import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * UserLogin: kirilkadurilka
 * Date: 10.02.13
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 */
public class DbConnectionTest {

    @Test
    public void testConnection(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Assert.assertNotNull(factory);

        Session session = factory.openSession();
        Assert.assertNotNull(session);

        session.close();
        factory.close();
    }
}

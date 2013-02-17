package org.myeducation.databaseapi.service.local;

import org.myeducation.databaseapi.service.AuthService;
import org.myeducation.databaseapi.service.ServiceFactory;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 17.02.13
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */
public class LocalServiceFactory implements ServiceFactory {
    public AuthService createAuthService() {
        return new AuthServiceLocal();
    }
}

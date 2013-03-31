package org.myeducation.databaseapi.service.local;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestData;
import org.myeducation.databaseapi.service.ExecutorService;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 29.03.13
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class ExecutorServiceLocal implements ExecutorService {
    public void storeResult(Serializable result, AttachData attachData, TestData testData) {

    }

    public List<AttachData> getAttachDatas(List<Long> ids) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

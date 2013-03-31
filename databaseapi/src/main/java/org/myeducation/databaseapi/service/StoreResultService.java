package org.myeducation.databaseapi.service;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestData;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 29.03.13
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
public interface StoreResultService {
    public void storeResult(Serializable result, AttachData attachData, TestData testData);
}

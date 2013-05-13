package org.myeducation.databaseapi.service;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.ProcessorResult;
import org.myeducation.databaseapi.entities.TestData;
import org.myeducation.databaseapi.model.ExecutorData;
import org.myeducation.databaseapi.model.ExecutorDataDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 29.03.13
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
public interface ExecutorSaveService {
    public void storeResult(ProcessorResult result, AttachData attachData, TestData testData);
    public List<ExecutorDataDto> getNewExecutorData();
    public List<ExecutorData> getExecuterData(List<ExecutorDataDto> dto);
}

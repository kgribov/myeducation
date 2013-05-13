package org.myeducation.databaseapi.service.local;

import org.myeducation.databaseapi.dao.Dao;
import org.myeducation.databaseapi.dao.TaskDAO;
import org.myeducation.databaseapi.entities.*;
import org.myeducation.databaseapi.model.ExecutorData;
import org.myeducation.databaseapi.model.ExecutorDataDto;
import org.myeducation.databaseapi.service.ExecutorSaveService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 29.03.13
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class ExecutorSaveServiceLocal implements ExecutorSaveService {

    private TaskDAO dao = Dao.getFactory().createTaskDao();

    public void storeResult(ProcessorResult result, AttachData attachData, TestData testData) {
        TestDataResult testDataResult = new TestDataResult();

        //serialize result
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(result.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] array = stream.toByteArray();
        if ( array == null || array.length == 0 )  return;
        String resultData = new String(array);

        testDataResult.setSuccess(result.isSuccess());
        testDataResult.setAttachData(attachData);
        testDataResult.setResult(resultData);
        testDataResult.setTestData(testData);

        dao.addTestResult(testDataResult);
    }

    public List<ExecutorData> getExecuterData(List<ExecutorDataDto> dto) {
        List<ExecutorData> result = new ArrayList<ExecutorData>(dto.size());
        for (ExecutorDataDto dataDto : dto){
            Object[] temp = dao.getExecuteData(dataDto.getDataId(), dataDto.getTestsId());
            ExecutorData data = new ExecutorData();
            data.setData((AttachData)temp[0]);
            data.setTests((TestDatas)temp[1]);
        }
        return result;
    }

    public List<ExecutorDataDto> getNewExecutorData() {
        List<Object[]> data = dao.getNotProcessTestDatas();
        ArrayList<ExecutorDataDto> result = new ArrayList(data.size());
        for (Object[] dataElement : data){
            ExecutorDataDto dataDto = new ExecutorDataDto();
            dataDto.setDataId(((BigInteger)dataElement[0]).longValue());
            dataDto.setTestsId(((BigInteger)dataElement[1]).longValue());
        }
        return result;
    }
}

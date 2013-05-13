package dao.hibernate;

import org.junit.Before;
import org.junit.Test;
import org.myeducation.databaseapi.dao.Dao;
import org.myeducation.databaseapi.dao.TaskDAO;
import org.myeducation.databaseapi.entities.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 17.02.13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public class TaskDaoTest {

    private TaskDAO dao;

    @Before
    public void init(){
        dao = Dao.getFactory().createTaskDao();
    }

    @Test
    public void createTask_createTaskSend_createTestResult(){
        //test task create
        Task task = new Task();
        task.setName("test simple alone task");
        task.setDescription("this is description of simple task");

        AttachDataType attachDataType1 = new AttachDataType();
        attachDataType1.setNamePattern("example1.java");
        attachDataType1.setTask(task);

        TestDatas testDatas = new TestDatas();
        testDatas.setExecType("java");
        testDatas.setMinPoints(new Long(10));
        testDatas.setTimeOut(new Long(4*1000));
        testDatas.setAttachDataType(attachDataType1);

        TestData testData1 = new TestData();
        testData1.setInputData("world");
        testData1.setOutputData("Hello world");
        testData1.setPoints(10);
        testData1.setTestDatas(testDatas);

        Set<TestData> testDataSet = new HashSet<TestData>();
        testDataSet.add(testData1);
        testDatas.setTestDatas(testDataSet);

        Set<TestDatas> testDatasSet = new HashSet<TestDatas>();
        testDatasSet.add(testDatas);
        attachDataType1.setTestDatas(testDatasSet);

        Set<AttachDataType> attachDataTypes = new HashSet<AttachDataType>();
        attachDataTypes.add(attachDataType1);
        task.setAttachDataTypes(attachDataTypes);

        dao.addTask(task);

        // create task send
        Task task1 = dao.getTask(1);

        for (AttachDataType attachDataType : task1.getAttachDataTypes()){
            TaskSend send = new TaskSend();

            AttachData attachData = new AttachData();
            attachData.setType(attachDataType);
            attachData.setTaskSend(send);
            attachData.setContent("file:example1.java");

            ArrayList<AttachData> attachDatas = new ArrayList<AttachData>();
            attachDatas.add(attachData);

            send.setTimeSend(System.currentTimeMillis());
            send.setAttachDatas(attachDatas);

            dao.addTaskSend(send);
        }


        // get not processed datas
        List<Object[]> notProcessed =  dao.getNotProcessTestDatas();
        for (Object[] data : notProcessed){
            Long dataId = (Long)data[0];
            Long testsId = (Long)data[0];
            Object[] executeData = dao.getExecuteData(dataId, testsId);

            AttachData attachData = (AttachData)executeData[0];
            TestDatas tests = (TestDatas)executeData[1];

            for (TestData testData : tests.getTestDatas()){
                TestDataResult result = new TestDataResult();
                result.setAttachData(attachData);
                result.setTestData(testData);
                result.setTime(System.currentTimeMillis());
                result.setResult("Some result");
                result.setSuccess(true);
                dao.addTestResult(result);
            }
        }
    }
}

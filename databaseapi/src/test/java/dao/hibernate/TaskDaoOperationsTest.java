package dao.hibernate;

import org.junit.Before;
import org.junit.Test;
import org.myeducation.databaseapi.dao.Dao;
import org.myeducation.databaseapi.dao.TaskDAO;
import org.myeducation.databaseapi.entities.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 17.02.13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public class TaskDaoOperationsTest {

    private TaskDAO dao;

    @Before
    public void init(){
        dao = Dao.getFactory().createTaskDao();
    }

    @Test
    public void createTask(){
        Task task = new Task();
        task.setName("test simple alone task");
        task.setDescription("this is description of simple task");

        AttachDataType attachDataType1 = new AttachDataType();
        attachDataType1.setNamePattern("javafile.java");

        TestDatas testDatas = new TestDatas();
        testDatas.setExecType("java");

        TestData testData1 = new TestData();
        testData1.setInputData("hello");
        testData1.setOutputData("hello world");

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
    }
}

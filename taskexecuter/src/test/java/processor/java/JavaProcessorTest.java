package processor.java;

import org.junit.Before;
import org.junit.Test;
import org.myeducation.databaseapi.dao.Dao;
import org.myeducation.databaseapi.entities.*;
import org.myeducation.taskexecuter.core.Executor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 04.03.13
 * Time: 23:05
 * To change this template use File | Settings | File Templates.
 */
public class JavaProcessorTest {

    private Executor executor = new Executor();

    private AttachData data1;
    private TestDatas tests1;

//    @Before
//    public void initTest(){
//        Task task = new Task();
//        task.setDescription("test task description");
//        task.setName("test task");
//
//        AttachDataType type = new AttachDataType();
//        type.setTask(task);
//
//        data1 = new AttachData();
//        data1.setType(type);
//        data1.setContent("example1.java");
//
//        tests1 = new TestDatas();
//        tests1.setTimeOut(new Long(3000));
//        tests1.setExecType("java");
//
//        Set<TestData> testDataSet = new HashSet<TestData>();
//
//        TestData testData1;
//        TestData testData2;
//
//        testData1 = new TestData();
//        testData1.setInputData("world");
//        testData1.setOutputData("Hello world");
//
//        testData2 = new TestData();
//        testData2.setInputData("man");
//        testData2.setOutputData("Hello man");
//
//        testDataSet.add(testData1);
//        testData1.setTestDatas(tests1);
//        testDataSet.add(testData2);
//        testData2.setTestDatas(tests1);
//
//        tests1.setTestDatas(testDataSet);
//
//        type.setTestDatas(new HashSet<TestDatas>(Arrays.asList(tests1)));
//        tests1.setAttachDataType(type);
//
//        Dao.getFactory().createTaskDao().addTask(task);
//    }
//
//
//    @Test
//    public void testHelloWorld(){
//        executor.processData(data1, tests1);
//        executor.shutDown();
//    }

    @Test
    public void exampleTest(){
        new String("nothing");
    }
}

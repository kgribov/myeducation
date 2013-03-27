package processor.java;

import org.junit.Before;
import org.junit.Test;
import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestData;
import org.myeducation.databaseapi.entities.TestDatas;
import org.myeducation.taskexecuter.core.processor.AbstractProcessor;
import org.myeducation.taskexecuter.core.processor.java.JavaProcessor;

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

    private AbstractProcessor processor;

    private AttachData data1;
    private TestData testData1;
    private TestData testData2;

    @Before
    public void initMethod(){
        processor = new JavaProcessor();
        data1 = new AttachData();
        data1.setContent("example1.java");

        testData1 = new TestData();
        testData1.setInputData("clever");
        testData1.setOutputData("Masha is clever");

        testData2 = new TestData();
        testData2.setInputData("super");
        testData2.setOutputData("Masha is super");
    }


    @Test
    public void testHelloWorld(){
        TestDatas datas = new TestDatas();
        datas.setTimeOut(3000);

        Set<TestData> testDataSet = new HashSet<TestData>();
        testDataSet.add(testData1);
        testData1.setTestDatas(datas);

        testDataSet.add(testData2);
        testData2.setTestDatas(datas);

        datas.setTestDatas(testDataSet);

        processor.execute(data1, datas);
    }
}

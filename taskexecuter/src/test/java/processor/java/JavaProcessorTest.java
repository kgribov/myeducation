package processor.java;

import org.junit.Before;
import org.junit.Test;
import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.AttachDataType;
import org.myeducation.databaseapi.entities.TestData;
import org.myeducation.databaseapi.entities.TestDatas;
import org.myeducation.taskexecuter.core.processor.AbstractProcessor;
import org.myeducation.taskexecuter.core.processor.JavaProcessor;

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

    @Before
    public void initMethod(){
        processor = new JavaProcessor();
        data1 = new AttachData();
        data1.setContent("example1.java");
        testData1 = new TestData();
        testData1.setOutputData("Hello world");
        testData1.setInputData("Hello world");
    }


    @Test
    public void testHelloWorld(){
        TestDatas datas = new TestDatas();
        Set<TestData> testDataSet = new HashSet<TestData>();
        testDataSet.add(testData1);
        datas.setTestDatas(testDataSet);
        processor.execute(data1, datas);
    }
}

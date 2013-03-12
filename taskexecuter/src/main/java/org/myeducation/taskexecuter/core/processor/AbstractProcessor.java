package org.myeducation.taskexecuter.core.processor;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestData;
import org.myeducation.databaseapi.entities.TestDatas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 03.03.13
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractProcessor {

    private final ExecutorService executorService;

    public AbstractProcessor(int cores){
        executorService = Executors.newFixedThreadPool(cores);
    }

    public final void execute(AttachData data, TestDatas testDatas){

        final AttachData attachData = data;
        final TestDatas tests = testDatas;

        Runnable processorJob = new Runnable() {
            @Override
            public void run() {
                for (TestData testData : tests.getTestDatas()){
                    try{
                        boolean result = validateResult(attachData, testData);
                        storeResult(result, attachData, testData);
                    }catch (Exception ex){
                        //catch exception
                        processException(ex, attachData, testData);
                        break;
                    }
                }
            }
        };

        executorService.execute(processorJob);
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    protected abstract boolean validateResult(AttachData data, TestData testData) throws Exception;

    protected abstract void processException(Exception ex, AttachData data, TestData testData);

    protected abstract void storeResult(boolean result, AttachData attachData, TestData testData);

    public abstract String getProcessorName();
}

package org.myeducation.taskexecuter.core.processor;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestData;
import org.myeducation.databaseapi.entities.TestDatas;
import org.myeducation.databaseapi.service.*;
import org.myeducation.properties.PropertiesFactory;

import java.io.Serializable;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 03.03.13
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractProcessor<T extends Serializable> {

    private final java.util.concurrent.ExecutorService executorService;
    private static final java.util.concurrent.ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    protected Properties properties = PropertiesFactory.getProperties("processors");
    protected String processorPrefix = "processor."+getProcessorName()+".";

    public AbstractProcessor(int cores){
        executorService = Executors.newFixedThreadPool(cores);

    }

    public void execute(AttachData data, TestDatas testDatas){

        final AttachData attachData = data;
        final TestDatas tests = testDatas;

        Runnable processorJob = new Runnable() {
            @Override
            public void run() {
                for (TestData testData : tests.getTestDatas()){
                    try{
                        T result = validate(attachData, testData);
                        storeResult(result, attachData, testData);
                        if (needBreakPointResult(result)){
                            break;
                        }
                    }catch (Exception ex){
                        T result = processException(ex, attachData, testData);
                        storeResult(result, attachData, testData);
                        if (needBreakPointException(ex)){
                            break;
                        }
                    }
                }
            }
        };

        executorService.execute(processorJob);
    }

    private T validate(AttachData data, TestData testData) throws Exception{
        final AttachData tempData = data;
        final TestData tempTestData = testData;

        FutureTask<T> task = new FutureTask<T>(new Callable<T>() {
            @Override
            public T call() throws Exception{
                return getResult(tempData, tempTestData);
            }
        });

        String timeOutProp = processorPrefix+"maxtimeout";
        long maxTimeout = Long.parseLong(properties.getProperty(timeOutProp));
        long timeOut = tempTestData.getTestDatas().getTimeOut();
        if (timeOut > maxTimeout) timeOut = maxTimeout;

        cachedThreadPool.execute(task);

        try{
            return task.get(timeOut, TimeUnit.MILLISECONDS);
        }catch (TimeoutException ex){
            task.cancel(true);
            throw ex;
        }
    }

    protected void storeResult(T result, AttachData attachData, TestData testData){
        org.myeducation.databaseapi.service.ExecutorService service = Service.getFactory().storeResultService();
        service.storeResult(result, attachData, testData);
    }

    public void shutDown(){

    }

    protected abstract T getResult(AttachData data, TestData testData) throws Exception;

    protected abstract T processException(Exception ex, AttachData data, TestData testData);

    protected abstract boolean needBreakPointResult(T result);

    protected abstract boolean needBreakPointException(Exception ex);

    public abstract String getProcessorName();
}

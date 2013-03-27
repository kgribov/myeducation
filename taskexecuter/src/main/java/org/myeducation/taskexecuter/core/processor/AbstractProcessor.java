package org.myeducation.taskexecuter.core.processor;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestData;
import org.myeducation.databaseapi.entities.TestDatas;
import org.myeducation.properties.PropertiesFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 03.03.13
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractProcessor<T> {

    private final ExecutorService executorService;
    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

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
                ArrayList<T> aggregatedResult = new ArrayList<T>(tests.getTestDatas().size());
                Exception exitException = null;
                for (TestData testData : tests.getTestDatas()){
                    try{
                        T result = validate(attachData, testData);
                        aggregatedResult.add(result);
                        storeResult(result, attachData, testData);
                        if (needBreakPointResult(result)){
                            break;
                        }
                    }catch (Exception ex){
                        T result = processException(ex, attachData, testData);
                        aggregatedResult.add(result);
                        if (needBreakPointException(ex)){
                            exitException = ex;
                            break;
                        }
                    }
                }
                if(exitException == null)
                    storeAggregatedResult(aggregatedResult, attachData, tests);
            }
        };

        executorService.execute(processorJob);

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private T validate(AttachData data, TestData testData) throws Exception{
        final AttachData tempData = data;
        final TestData tempTestData = testData;

        FutureTask<T> task = new FutureTask<T>(new Callable<T>() {
            @Override
            public T call() throws Exception{
                return validateResult(tempData, tempTestData);
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

    protected abstract T validateResult(AttachData data, TestData testData) throws Exception;

    protected abstract T processException(Exception ex, AttachData data, TestData testData);

    protected abstract void storeResult(T result, AttachData attachData, TestData testData);

    protected abstract void storeAggregatedResult(List<T> result, AttachData attachData, TestDatas testData);

    protected abstract boolean needBreakPointResult(T result);

    protected abstract boolean needBreakPointException(Exception ex);

    public abstract String getProcessorName();
}

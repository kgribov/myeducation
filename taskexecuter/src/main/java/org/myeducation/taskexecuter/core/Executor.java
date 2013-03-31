package org.myeducation.taskexecuter.core;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestDatas;
import org.myeducation.databaseapi.model.ExecutorData;
import org.myeducation.databaseapi.model.ExecutorDataDto;
import org.myeducation.taskexecuter.core.processor.AbstractProcessor;
import org.myeducation.taskexecuter.core.processor.program.java.JavaProcessor;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 31.03.13
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
public class Executor {
    private HashMap<String, AbstractProcessor> avalibleProcessors = new HashMap<String, AbstractProcessor>();

    public Executor(){
        AbstractProcessor javaProcessor = new JavaProcessor();
        avalibleProcessors.put(javaProcessor.getProcessorName(), javaProcessor);
    }

    public void processTestDatas(List<ExecutorDataDto> datas){
        List<ExecutorData> list = getTestDatas(datas);
        for (ExecutorData data : list){
            processData(data.getData(), data.getTests());
        }
    }

    public void processData(AttachData data, TestDatas datas){
        String execType = datas.getExecType();
        AbstractProcessor processor = avalibleProcessors.get(execType);
        processor.execute(data, datas);
    }

    protected List<ExecutorData> getTestDatas(List<ExecutorDataDto> datas){
        return null;
    }

    public void shutDown(){
        for (AbstractProcessor processor : avalibleProcessors.values()){
            processor.shutDown();
        }
    }
}

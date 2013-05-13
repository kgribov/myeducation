package org.myeducation.taskexecuter.core;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestDatas;
import org.myeducation.databaseapi.model.ExecutorData;
import org.myeducation.databaseapi.model.ExecutorDataDto;
import org.myeducation.databaseapi.service.Service;
import org.myeducation.taskexecuter.core.processor.AbstractProcessor;
import org.myeducation.taskexecuter.core.processor.KeywordProcessor;
import org.myeducation.taskexecuter.core.processor.program.java.JavaProcessor;

import java.util.Collections;
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
    private HashMap<String, AbstractProcessor> availableProcessors = new HashMap<String, AbstractProcessor>();

    public Executor(){
        //init processors
        AbstractProcessor javaProcessor = new JavaProcessor();
        AbstractProcessor keywordProcessor = new KeywordProcessor();

        availableProcessors.put(javaProcessor.getProcessorName(), javaProcessor);
        availableProcessors.put(keywordProcessor.getProcessorName(), keywordProcessor);
    }

    public void processTestDatas(List<ExecutorDataDto> datas){
        List<ExecutorData> list = getTestDatas(datas);
        for (ExecutorData data : list){
            processData(data.getData(), data.getTests());
        }
    }

    public void processData(AttachData data, TestDatas datas){
        String execType = datas.getExecType();
        AbstractProcessor processor = availableProcessors.get(execType);
        if (processor != null){
            processor.execute(data, datas);
        }else{
            //no processors found
        }
    }

    //load data to execute
    private List<ExecutorData> getTestDatas(List<ExecutorDataDto> datas){
        return Service.getFactory().executorSaveService().getExecuterData(datas);
    }

    public void shutDown(){
        for (AbstractProcessor processor : availableProcessors.values()){
            processor.shutDown();
        }
    }
}

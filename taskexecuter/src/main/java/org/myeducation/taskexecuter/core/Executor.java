package org.myeducation.taskexecuter.core;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestDatas;
import org.myeducation.taskexecuter.core.processor.AbstractProcessor;
import org.myeducation.taskexecuter.core.processor.program.java.JavaProcessor;

import java.util.HashMap;

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

    public void processData(AttachData data){
        for (TestDatas datas : data.getType().getTestDatas()){
            String execType = datas.getExecType();
            AbstractProcessor processor = avalibleProcessors.get(execType);
            processor.execute(data, datas);
        }
    }

    public void shutDown(){
        for (AbstractProcessor processor : avalibleProcessors.values()){
            processor.shutDown();
        }
    }
}

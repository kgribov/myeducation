package org.myeducation.taskexecuter.core.processor;

import org.myeducation.databaseapi.entities.AttachData;

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

    public final void execute(AttachData data){
        final AttachData temp = data;
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                executeData(temp);
            }
        });
    }

    public abstract void executeData(AttachData data);

    public abstract String getProcessorName();
}

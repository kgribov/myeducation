package org.myeducation.taskexecuter.core.processor;

import org.myeducation.databaseapi.entities.AttachData;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 03.03.13
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
public class JavaProcessor extends AbstractProcessor {

    public JavaProcessor(){
        //need to change to read from props
        super(50);
    }

    @Override
    public void executeData(AttachData data) {
        //here we will run java
    }

    @Override
    public String getProcessorName() {
        return "JAVA_PROCESSOR";
    }
}

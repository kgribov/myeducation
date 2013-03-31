package org.myeducation.taskexecuter.core.processor.program;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestData;
import org.myeducation.taskexecuter.core.processor.AbstractProcessor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 29.03.13
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */
public abstract class ProgramProcessor extends AbstractProcessor<ProgramResult> {

    public ProgramProcessor(int cores){
        super(cores);
    }


    @Override
    protected ProgramResult processException(Exception ex, AttachData data, TestData testData) {
        ex.printStackTrace();
        return new ProgramResult(false, 0 ,"wrong answer");
    }

    @Override
    protected boolean needBreakPointResult(ProgramResult result){
        return false;
    }

    @Override
    protected boolean needBreakPointException(Exception ex){
        return false;
    }
}

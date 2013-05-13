package org.myeducation.taskexecuter.core.processor.program;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.ProcessorResult;
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

    @Override
    protected ProcessorResult processException(Exception ex, AttachData data, TestData testData) {
        ProcessorResult<ProgramResult> result = new ProcessorResult<ProgramResult>();
        result.setSuccess(false);
        result.setResult(new ProgramResult(false, 0 ,"wrong answer"));
        return result;
    }

    @Override
    protected boolean needBreakPointResult(ProcessorResult result){
        return false;
    }

    @Override
    protected boolean needBreakPointException(Exception ex){
        return false;
    }
}

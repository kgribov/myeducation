package org.myeducation.taskexecuter.core.processor;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.ProcessorResult;
import org.myeducation.databaseapi.entities.TestData;
import org.myeducation.properties.PropertiesFactory;
import org.myeducation.taskexecuter.core.util.DataSourceUtil;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 12.05.13
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
public class KeywordProcessor extends AbstractProcessor {

    private Properties properties = PropertiesFactory.getProperties("processors");

    @Override
    protected ProcessorResult getResult(AttachData data, TestData testData) throws Exception {
        String content = (String)DataSourceUtil.getSource(data.getContent());
        String[] words =  testData.getInputData().split(properties.getProperty("processor.keyword.separator"));

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected ProcessorResult processException(Exception ex, AttachData data, TestData testData) {
        ProcessorResult<String> result = new ProcessorResult<String>();
        result.setSuccess(false);
        result.setResult(new String("some exception was"));
        return result;
    }

    @Override
    protected boolean needBreakPointResult(ProcessorResult result) {
        return false;
    }

    @Override
    protected boolean needBreakPointException(Exception ex) {
        return true;
    }

    @Override
    public String getProcessorName() {
        return "keyword";
    }

    @Override
    public int getCores() {
        return Integer.parseInt(PropertiesFactory.getProperties("processors").getProperty("processor.keyword.cores"));
    }
}

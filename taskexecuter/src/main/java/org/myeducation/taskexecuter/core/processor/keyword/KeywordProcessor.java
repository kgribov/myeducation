package org.myeducation.taskexecuter.core.processor.keyword;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.ProcessorResult;
import org.myeducation.databaseapi.entities.TestData;
import org.myeducation.properties.PropertiesFactory;
import org.myeducation.taskexecuter.core.processor.AbstractProcessor;
import org.myeducation.taskexecuter.core.util.DataSourceUtil;

import java.util.HashMap;
import java.util.Map;
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

        ProcessorResult<KeywordResult> processorResult = new ProcessorResult<KeywordResult>();
        processorResult.setSuccess(true);
        KeywordResult result = new KeywordResult();
        processorResult.setResult(result);

        Map<String, Integer> map = new HashMap<String, Integer>();
        result.setWordsPositions(map);
        for (String word : words){
            Integer position = content.indexOf(word);
            if (position == -1){
                processorResult.setSuccess(false);
            }
            map.put(word, position);
        }
        return processorResult;
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

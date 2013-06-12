package org.myeducation.taskexecuter.core.processor.keyword;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 29.05.13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class KeywordResult implements Serializable {
    private Map<String, Integer> wordsPositions;

    public Map<String, Integer> getWordsPositions() {
        return wordsPositions;
    }

    public void setWordsPositions(Map<String, Integer> wordsPositions) {
        this.wordsPositions = wordsPositions;
    }
}

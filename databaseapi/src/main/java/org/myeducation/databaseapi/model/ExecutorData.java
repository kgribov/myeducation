package org.myeducation.databaseapi.model;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestDatas;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 31.03.13
 * Time: 23:53
 * To change this template use File | Settings | File Templates.
 */
public class ExecutorData implements Serializable{
    private AttachData data;
    private TestDatas tests;

    public AttachData getData() {
        return data;
    }

    public void setData(AttachData data) {
        this.data = data;
    }

    public TestDatas getTests() {
        return tests;
    }

    public void setTests(TestDatas tests) {
        this.tests = tests;
    }
}

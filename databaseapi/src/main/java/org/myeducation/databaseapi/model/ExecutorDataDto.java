package org.myeducation.databaseapi.model;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 31.03.13
 * Time: 23:55
 * To change this template use File | Settings | File Templates.
 */
public class ExecutorDataDto {
    private long dataId;
    private long testsId;


    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public long getTestsId() {
        return testsId;
    }

    public void setTestsId(long testsId) {
        this.testsId = testsId;
    }
}

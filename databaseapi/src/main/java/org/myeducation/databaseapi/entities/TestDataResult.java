package org.myeducation.databaseapi.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 03.04.13
 * Time: 0:13
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "testdataresult")
public class TestDataResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testdataresult_id")
    private long id;

    @Column(name = "testdataresult_success")
    private boolean success;

    @Column(name = "testdataresult_result", columnDefinition = "LONGTEXT")
    private String result;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "testdataresult_testdata")
    private TestData testData;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "testdataresult_attachdata")
    private AttachData attachData;

    @Column(name = "testdataresult_time")
    private long time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public TestData getTestData() {
        return testData;
    }

    public void setTestData(TestData testData) {
        this.testData = testData;
    }

    public AttachData getAttachData() {
        return attachData;
    }

    public void setAttachData(AttachData attachData) {
        this.attachData = attachData;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

package org.myeducation.databaseapi.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 26.02.13
 * Time: 23:42
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "testdata")
public class TestData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name = "testdata_inputdata", columnDefinition = "LONGTEXT")
    private String inputData;

    @Column(name = "testdata_outputdata", columnDefinition = "LONGTEXT")
    private String outputData;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "testdata_testdatas")
    private TestDatas testDatas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getOutputData() {
        return outputData;
    }

    public void setOutputData(String outputData) {
        this.outputData = outputData;
    }

    public TestDatas getTestDatas() {
        return testDatas;
    }

    public void setTestDatas(TestDatas testDatas) {
        this.testDatas = testDatas;
    }
}

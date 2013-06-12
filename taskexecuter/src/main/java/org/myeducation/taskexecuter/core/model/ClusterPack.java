package org.myeducation.taskexecuter.core.model;

import org.myeducation.databaseapi.model.ExecutorDataDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 06.04.13
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public class ClusterPack implements Serializable{

    private long time;
    private ArrayList<ExecutorDataDto> data;


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ArrayList<ExecutorDataDto> getData() {
        return data;
    }

    public void setData(ArrayList<ExecutorDataDto> data) {
        this.data = data;
    }
}

package org.myeducation.portal.client.dto;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 05.06.13
 * Time: 21:49
 * To change this template use File | Settings | File Templates.
 */
public class TaskSendDto implements Serializable {
    private long userId;
    private long taskId;
    private HashMap<Long, Object> attachDatas;
    private long time;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public HashMap<Long, Object> getAttachDatas() {
        return attachDatas;
    }

    public void setAttachDatas(HashMap<Long, Object> attachDatas) {
        this.attachDatas = attachDatas;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

package org.myeducation.portal.client.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 31.05.13
 * Time: 0:14
 * To change this template use File | Settings | File Templates.
 */
public class TaskSendResult implements Serializable {

    private long taskId;
    private String user;
    private Long points;
    private Long minPoints;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    private Long time;
    private String status;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPoints() {

        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getUser() {

        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(Long minPoints) {
        this.minPoints = minPoints;
    }
}

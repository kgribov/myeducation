package org.myeducation.taskexecuter.core.processor.program;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 29.03.13
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ProgramResult implements Serializable {

    private boolean success;
    private long time;
    private String message;

    public ProgramResult(boolean  success, long time){
        this.success = success;
        this.time = time;
    }

    public ProgramResult(boolean success, long  time, String message){
        this.success = success;
        this.time = time;
        this.setMessage(message);
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

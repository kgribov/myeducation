package org.myeducation.databaseapi.entities;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 12.05.13
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class ProcessorResult<T extends Serializable> {

    private boolean success;
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

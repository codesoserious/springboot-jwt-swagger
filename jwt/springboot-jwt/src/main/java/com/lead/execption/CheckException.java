package com.lead.execption;

/**
 * @author mac
 */
public class CheckException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String msg;

    public CheckException(String msg) {
        super(msg);
        this.msg = msg;
    }


}

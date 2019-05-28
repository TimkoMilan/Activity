package com.globallogic.activitygame.exception;


public class ActivityGameException extends RuntimeException {

    public ActivityGameException() {
        super();
    }

    public ActivityGameException(String s) {
        super(s);
    }

    public ActivityGameException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ActivityGameException(Throwable throwable) {
        super(throwable);
    }

    protected ActivityGameException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}

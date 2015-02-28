package com.bingzer.android.patterns.implementor;

public class ImplementorException extends Exception {
    public ImplementorException() {
    }

    public ImplementorException(String detailMessage) {
        super(detailMessage);
    }

    public ImplementorException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ImplementorException(Throwable throwable) {
        super(throwable);
    }
}

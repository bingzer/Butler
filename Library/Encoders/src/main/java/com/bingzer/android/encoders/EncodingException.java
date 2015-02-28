package com.bingzer.android.encoders;

public class EncodingException extends Exception {
    public EncodingException() {
    }

    public EncodingException(String detailMessage) {
        super(detailMessage);
    }

    public EncodingException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public EncodingException(Throwable throwable) {
        super(throwable);
    }
}

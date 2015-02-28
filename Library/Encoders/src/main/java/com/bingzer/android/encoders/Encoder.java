package com.bingzer.android.encoders;

import android.graphics.Bitmap;

import java.io.File;

public interface Encoder {

    /**
     * Prepare the encoder
     * @throws EncodingException
     */
    void start(File target) throws EncodingException;

    /**
     * Adds bitmap
     * @param bitmap the bitmap to add
     * @throws EncodingException
     */
    void addFrame(Bitmap bitmap) throws EncodingException;

    /**
     * Close this and free resources
     * @throws EncodingException
     */
    void close() throws EncodingException;

}

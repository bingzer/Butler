package com.bingzer.android.encoders;

import android.graphics.Bitmap;
import android.util.Log;

import com.bingzer.android.encoders.video.VideoEncoder;
import com.bingzer.android.encoders.video.jcodec.api.android.SequenceEncoder;

import java.io.File;
import java.io.IOException;

class VideoEncoderImpl implements VideoEncoder {
    private static final String TAG = "VideoEncoder";

    private File targetFile;
    private SequenceEncoder encoder;

    @Override
    public void start(File target) throws EncodingException {
        try{
            Log.i(TAG, "starting target = " + target);
            targetFile = target;
            encoder = new SequenceEncoder(targetFile);
        } catch (IOException e){
            throw new EncodingException(e);
        }
    }

    @Override
    public void addFrame(Bitmap bitmap) throws EncodingException{
        validateEncoder();
        try {
            Log.i(TAG, "Adding Frame");
            encoder.encodeImage(bitmap);
        } catch (IOException e) {
            throw new EncodingException(e);
        }
    }

    @Override
    public void close() throws EncodingException {
        Log.i(TAG, "Close");
        targetFile = null;
        encoder = null;
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    private void validateEncoder() throws EncodingException {
        if (targetFile == null)
            throw new EncodingException(new NullPointerException("targetFile"));
        if (encoder == null)
            throw new EncodingException(new NullPointerException("encoder"));
    }
}

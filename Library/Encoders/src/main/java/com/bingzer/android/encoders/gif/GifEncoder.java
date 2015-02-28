package com.bingzer.android.encoders.gif;

import com.bingzer.android.encoders.Encoder;

public interface GifEncoder extends Encoder {

    /**
     * Sets the delay
     * @param ms millis
     */
    void setDelay(int ms);

    void setDispose(int code);

    void setRepeat(int iter);

    void setTransparent(int c);

    void setFrameRate(float fps);

    void setQuality(int quality);

    void setSize(int w, int h);

    void setPosition(int x, int y);
}

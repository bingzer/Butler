package com.example;

import com.bingzer.android.encoders.EncoderFactory;
import com.bingzer.android.encoders.gif.GifEncoder;
import com.bingzer.android.encoders.video.VideoEncoder;

public class TestUsage {
    public void test() throws Exception{
        EncoderFactory factory = new EncoderFactory();
        GifEncoder encoder = factory.getImplementation(GifEncoder.class);
        VideoEncoder videoEncoder = factory.getImplementation(VideoEncoder.class);
    }
}

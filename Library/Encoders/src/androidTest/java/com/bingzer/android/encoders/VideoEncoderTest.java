package com.bingzer.android.encoders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.AndroidTestCase;

import com.bingzer.android.encoders.video.VideoEncoder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class VideoEncoderTest extends AndroidTestCase {

    private EncoderFactory factory;

    @Override
    protected void setUp() throws Exception {
        factory = new EncoderFactory();
    }

    public void testClass() throws Exception{
        VideoEncoder encoder = factory.getImplementation(VideoEncoder.class);
        assertNotNull(encoder);
    }

    public void testAddFrame() throws Exception {
        File temp = File.createTempFile("VideoEncoderTest", "");

        VideoEncoder encoder = factory.getImplementation(VideoEncoder.class);
        encoder.start(temp);
        encoder.addFrame(getBitmap("001.png"));
        encoder.addFrame(getBitmap("002.png"));
        encoder.addFrame(getBitmap("003.png"));
        encoder.close();

//        File target = new File(Environment.getExternalStorageDirectory() + "/Movies", temp.getName() + ".mp4");
//        Path.copyFile(temp, target);
//        assertTrue(target.exists());

//        MediaPlayer player = new MediaPlayer();
//        player.setDataSource("file://" + temp.getAbsolutePath());
//        player.prepare();
//
//        assertTrue(player.getVideoHeight() > 0);
//        assertTrue(player.getVideoWidth() > 0);
//        player.release();
    }

    private Bitmap getBitmap(String assetName) throws IOException {
        InputStream input = getContext().getAssets().open(assetName);
        Bitmap bitmap = BitmapFactory.decodeStream(input);
        input.close();

        return bitmap;
    }
}

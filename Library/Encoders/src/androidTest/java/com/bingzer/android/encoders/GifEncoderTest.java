package com.bingzer.android.encoders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.AndroidTestCase;

import com.bingzer.android.encoders.gif.GifEncoder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GifEncoderTest extends AndroidTestCase {

    private EncoderFactory factory;

    @Override
    protected void setUp() throws Exception {
        factory = new EncoderFactory();
    }

    public void testClass() throws Exception{
        GifEncoder encoder = factory.getImplementation(GifEncoder.class);
        assertNotNull(encoder);
    }

    public void testAddFrame() throws Exception {
        File temp = File.createTempFile("GifEncoderTest", "");

        GifEncoder encoder = factory.getImplementation(GifEncoder.class);
        encoder.start(temp);
        encoder.addFrame(getBitmap("001.png"));
        encoder.addFrame(getBitmap("002.png"));
        encoder.addFrame(getBitmap("003.png"));
        encoder.close();

        Bitmap bitmap = BitmapFactory.decodeFile(temp.getAbsolutePath());

        assertNotNull(bitmap);
        assertTrue(bitmap.getHeight() > 0);
        assertTrue(bitmap.getWidth() > 0);
    }

    private Bitmap getBitmap(String assetName) throws IOException{
        InputStream input = getContext().getAssets().open(assetName);
        Bitmap bitmap = BitmapFactory.decodeStream(input);
        input.close();

        return bitmap;
    }
}

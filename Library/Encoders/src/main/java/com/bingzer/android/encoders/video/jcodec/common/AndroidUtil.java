package com.bingzer.android.encoders.video.jcodec.common;

import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.ColorUtil;
import org.jcodec.scale.Transform;

import android.graphics.Bitmap;

import com.bingzer.android.encoders.video.jcodec.scale.BitmapUtil;

/**
 * This class is part of JCodec ( www.jcodec.org ) This software is distributed
 * under FreeBSD License
 * 
 * @author The JCodec project
 * 
 */
public class AndroidUtil {

	public static Bitmap toBitmap(Picture pic) {
		Transform transform = ColorUtil.getTransform(pic.getColor(),
				ColorSpace.RGB);
		Picture rgb = Picture.create(pic.getWidth(), pic.getHeight(),
				ColorSpace.RGB, pic.getCrop());
		transform.transform(pic, rgb);
		return BitmapUtil.toBitmap(rgb);
	}
	
	public static void toBitmap(Picture pic, Bitmap out) {
		Transform transform = ColorUtil.getTransform(pic.getColor(),
				ColorSpace.RGB);
		Picture rgb = Picture.create(pic.getWidth(), pic.getHeight(),
				ColorSpace.RGB, pic.getCrop());
		transform.transform(pic, rgb);
		BitmapUtil.toBitmap(rgb, out);
	}
}
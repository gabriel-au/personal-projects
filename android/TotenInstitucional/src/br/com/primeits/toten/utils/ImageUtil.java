package br.com.primeits.toten.utils;

import android.graphics.Bitmap;

public class ImageUtil {
	
	public Bitmap resizeBitmap(Bitmap srcBmp, int width, int height) {
		Bitmap dstBmp = null;
		Bitmap tmpBmp = srcBmp;
		
		int targetWidth = width;
		int targetHeight = height;
		
		int bmpScale = 1;
		
		if (srcBmp.getWidth() > targetWidth && srcBmp.getHeight() > targetHeight) {
			int bmpScaleX = srcBmp.getWidth() / targetWidth;
			int bmpScaleY = srcBmp.getHeight() / targetHeight;
			
			bmpScale = bmpScaleX;
			
			int bmpWidthNew = tmpBmp.getWidth()/bmpScale;
			int bmpHeightNew = tmpBmp.getHeight()/bmpScale;
			
			if ((bmpHeightNew-targetHeight) < 0 || bmpScaleX == 0) {
				bmpScale = bmpScaleY;
				bmpWidthNew = tmpBmp.getWidth()/bmpScale;
				bmpHeightNew = tmpBmp.getHeight()/bmpScale;
			}
			
			tmpBmp = Bitmap.createScaledBitmap(tmpBmp, bmpWidthNew, bmpHeightNew, false);
			dstBmp = Bitmap.createBitmap(tmpBmp, ((tmpBmp.getWidth()-targetWidth)/2), ((tmpBmp.getHeight()-targetHeight)/2), targetWidth, targetHeight);
		} else {
			dstBmp = Bitmap.createBitmap(tmpBmp, 0, 0, targetWidth, targetHeight);
		}
		
		return dstBmp;
	}

}

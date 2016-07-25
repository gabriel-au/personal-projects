package br.com.primeits.toten.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.sax.StartElementListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	int defaultItemBackground;
	private Context galleryContext;
	private File[] imageFiles = null;
	private File[] videoFiles = null;
	//private String imageDir = "";
	//private String videoDir = "";
	//private String imagePath = "";
	private ArrayList<Bitmap> arrayBitmaps;
	private ArrayList<File> arrayImageFiles;
	//private Bitmap[] imageBitmaps;
	private int positionLastViewd = 0;
	private int imgWidth = 0;
	private int imgHeight = 0;
	private int borderSize = 0;
	private String borderColor = "#000000";
	
	private int pageCount = 1;
	private int lastPageViewed = 0;
	private int countItems = 0;
	
	private Bitmap bitmap;
	File file;
	
	public ImageAdapter(Context c, String path, int imgWidth, int imgHeight, int countItems, boolean hasVideo) throws OutOfMemoryError  {
		this.galleryContext = c;
		//this.imageDir = path + "/";
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		
		file = new File(Environment.getExternalStorageDirectory() + "/" + path);
		imageFiles = file.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return !name.startsWith(".");
		    }
		});
		
		if (hasVideo) {
			file = new File(Environment.getExternalStorageDirectory() + "/" + "totenres/content/media/galeria_de_videos/videos");
			videoFiles = file.listFiles(new FilenameFilter() {
			    public boolean accept(File dir, String name) {
			        return !name.startsWith(".");
			    }
			});
		}
		
		//imageBitmaps = new Bitmap[imageFiles.length];
		arrayBitmaps = new ArrayList<Bitmap>();
		arrayImageFiles = new ArrayList<File>();
		
		int imgInitPos = 0;
		int imgLastPos = 0;
		int arrayImageSize = imageFiles.length;
		
		if (countItems > 0 && countItems <= arrayImageSize) {
			this.countItems = countItems;
			pageCount = arrayImageSize / countItems;
			
			if ((arrayImageSize % countItems) > 0) {
				pageCount++;
			}
			
			lastPageViewed = 1;
			
			imgInitPos = (lastPageViewed-1) * countItems;
			imgLastPos = lastPageViewed * countItems;
		}
		
		if (imgLastPos > arrayImageSize || imgLastPos == 0) {
			imgLastPos = arrayImageSize;
		}
		
		for (int i=imgInitPos; i < imgLastPos; i++) {
			Bitmap tmpBitmap = BitmapFactory.decodeFile(imageFiles[i].getAbsolutePath());
			
			if (tmpBitmap != null) {
				bitmap = resizeBitmap(tmpBitmap, imgWidth, imgHeight);
				arrayBitmaps.add(bitmap);
				arrayImageFiles.add(imageFiles[i]);
			}
		}
		
		
//		for (int i = 0; i < imageFiles.length; i++) {
//			Bitmap tmpBitmap = BitmapFactory.decodeFile(imageFiles[i].getAbsolutePath());
//			
//			if (tmpBitmap != null) {
//				bitmap = resizeBitmap(tmpBitmap, imgWidth, imgHeight);
//				arrayBitmaps.add(bitmap);
//				arrayImageFiles.add(imageFiles[i]);
//			}
//		}
        
	}
	
	public void viewPage(int id) {
		this.lastPageViewed = id;
		
		arrayBitmaps.clear();
		arrayBitmaps = new ArrayList<Bitmap>();
		
		arrayImageFiles.clear();
		arrayImageFiles = new ArrayList<File>();
		
		int imgInitPos = 0;
		int imgLastPos = 0;
		int arrayImageSize = imageFiles.length;
		
		imgInitPos = (id-1) * countItems;
		imgLastPos = id * countItems;
		
		if (imgLastPos > arrayImageSize) {
			imgLastPos = arrayImageSize;
		}
		
		for (int i=imgInitPos; i < imgLastPos; i++) {
			Bitmap tmpBitmap = BitmapFactory.decodeFile(imageFiles[i].getAbsolutePath());
			
			if (tmpBitmap != null) {
				bitmap = resizeBitmap(tmpBitmap, imgWidth, imgHeight);
				arrayBitmaps.add(bitmap);
				arrayImageFiles.add(imageFiles[i]);
			}
		}
		
		notifyDataSetChanged();
	}
	
	public int getLastViewedPage() {
		return this.lastPageViewed;
	}
	
	public int pageCount() {
		return this.pageCount;
	}
	
	public void setSizeImgGrid(int x, int y) {
		this.imgWidth = x;
		this.imgHeight = y;
	}
	
	public void setBorder(int size, String color) {
		this.borderSize = size;
		this.borderColor = color;
	}

	public int getCount() {
		//imageBitmaps.length;
		return arrayBitmaps.size(); 
	}
	
	public int getLastViewed() {
		return positionLastViewd;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;

		if (convertView == null) {
			imageView = new ImageView(galleryContext);

			imageView.setLayoutParams(new GridView.LayoutParams(imgWidth, imgHeight));
			//imageView.setAdjustViewBounds(true);
			
			if (borderSize > 0) {
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			}
			
			imageView.setBackgroundColor(Color.parseColor(borderColor));
			imageView.setPadding(borderSize, borderSize, borderSize, borderSize);

		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageBitmap(arrayBitmaps.get(position));

		return imageView;
	}

	private Bitmap resizeBitmap(Bitmap srcBmp, int width, int height) throws OutOfMemoryError {
		Bitmap dstBmp = null;
		
		if (srcBmp == null) {
			return srcBmp;
		}
		
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

	public Bitmap getBitmap(int position) throws OutOfMemoryError {
		this.positionLastViewd = position;
		return BitmapFactory.decodeFile(arrayImageFiles.get(position).getAbsolutePath());
	}
	
	public File getVideoFile(int position) {
		return videoFiles[position];
	}
	
	public void removeItems() {
		if (arrayBitmaps.size() > 0) {
			for (int i=0; i < arrayBitmaps.size(); i++) {
				arrayBitmaps.get(i).recycle();
			}
		}
		
		arrayBitmaps.clear();
		arrayImageFiles.clear();
		
		//System.gc();
	}
}
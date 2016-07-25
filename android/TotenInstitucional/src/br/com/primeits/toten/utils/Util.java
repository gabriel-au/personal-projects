package br.com.primeits.toten.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;

public class Util {
	
	public String readFromFile(String file) {

	    String ret = "";
	    
	    try {
	        InputStream inputStream = new FileInputStream(Environment.getExternalStorageDirectory() + "/" + file);

	        if (inputStream != null) {
	            //InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	        	
	        	Reader reader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(reader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append(receiveString);
	            }

	            inputStream.close();
	            ret = stringBuilder.toString();
	            
	            //System.out.println("Length: " + ret.length());
	        }
	    }
	    catch (FileNotFoundException e) {
	        Log.e("UTIL", "File not found: " + e.toString());
	    } catch (IOException e) {
	        Log.e("UTIL", "Can not read file: " + e.toString());
	    } catch (OutOfMemoryError e) {
	    	Log.e("UTIL", "OutOfMemoryError: " + e.toString());
	    }

	    return ret;
	}
	
	public void unbindDrawables(View view) {
//    	if (view instanceof ImageView) {
//            Drawable drawable = ((ImageView) view).getDrawable();
//            
//            if (drawable instanceof BitmapDrawable) {
//                BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
//                bitmapDrawable.getBitmap().recycle();
//            }
//        }
    	
    	if (view instanceof WebView) {
    		((WebView) view).stopLoading();
    		((WebView) view).freeMemory();
    		((WebView) view).clearCache(true);
    		((WebView) view).clearView();
    		((WebView) view).removeAllViews();
//    	    ((WebView) view).destroy();
    	}
    	
    	if (view.getBackground() != null) {
        	view.getBackground().setCallback(null);
        }
    	
        if (view instanceof ViewGroup && !(view instanceof AdapterView<?>)) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
            	unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }
}

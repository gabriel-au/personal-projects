package net.guiadagula.mobileapp;

import android.graphics.Color;
import android.os.Bundle;

import org.apache.cordova.*;

public class MainActivity extends DroidGap
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setStringProperty("errorUrl", "file:///android_asset/www/error.html");
        super.setIntegerProperty("splashscreen", R.drawable.startup);
        super.setIntegerProperty("backgroundColor", Color.parseColor("#690601"));
        super.loadUrl("http://m.guiadagula.net", 5000);
    }
}


package br.com.brq.unimed.webview;

import android.os.Bundle;
import com.phonegap.*;

public class UnimedActivity extends DroidGap {
    /** Called when the activity is first created. */
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
        //setContentView(R.layout.main);
    }
}
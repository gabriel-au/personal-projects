package br.com.martins.vendas.activity;

import android.os.Bundle;
import android.os.Environment;

import com.brq.mobile.framework.core.Config;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.FileUtil;
import com.phonegap.DroidGap;

public class InicialActivity extends DroidGap {

	private static final String TAG = InicialActivity.class.getName();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
//			setStringProperty("android:windowSoftInputMode", "adjustPan");
			
			Config.startAndroid(this);
			
			FileUtil.copyDatabaseFromSD(this, Environment.getExternalStorageDirectory(), "/martins/databases/", "martins.sqlite");
			
			FileUtil.copyDatabase(this, "databases/martins.sqlite", "martins.sqlite");
			
//			DatabaseFactory.getInstance().executeSQL("VACUUM");
//			DatabaseFactory.getInstance().executeSQL("REINDEX");
			
			super.loadUrl("file:///android_asset/www/martins/index.html");

		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}
}
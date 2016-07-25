package com.brq.mobile.framework.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.brq.mobile.framework.core.ConfigAccess;
import com.brq.mobile.framework.log.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String TAG = DatabaseHelper.class.getName();

	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, ConfigAccess.getConfig().diretoryData() + ConfigAccess.getConfig().dataBaseName(), factory, version);
		try {
			SQLiteDatabase dataBase = this.getWritableDatabase();
			dataBase.close();
		} catch (SQLiteException e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}

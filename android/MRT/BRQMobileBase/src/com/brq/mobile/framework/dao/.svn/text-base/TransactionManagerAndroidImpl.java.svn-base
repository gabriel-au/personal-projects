package com.brq.mobile.framework.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.AndroidRuntimeException;

import com.brq.mobile.framework.log.Log;

public class TransactionManagerAndroidImpl implements TransactionManager {

	private SQLiteDatabase database;
	private static final String TAG = TransactionManagerAndroidImpl.class.getName();

	/**
	 * Contrutor que recebe conex�o
	 * 
	 * encapsulamento protected para que apenas as classes de banco de dados
	 * possam instanciar essa classe
	 * 
	 * @param database representa conex�o com o banco de dados no android
	 */
	protected TransactionManagerAndroidImpl(SQLiteDatabase database) {	
		if (database == null) {
			throw new IllegalArgumentException("Parameter database can not be null!");
		}

		this.database = database;
	}

	public void beginTransaction() {
		if (!database.isOpen()) {
			
			database = SQLiteDatabase.openDatabase(database.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
			
		}
		database.beginTransaction();
	}

	public void commitTransaction() {
		database.setTransactionSuccessful();
		database.endTransaction();
	}

	public void rollbackTransaction() {
		database.endTransaction();
	}

	@Override
	public void endTransaction() throws SQLException {
		database.close();
	}

	@Override
	public void insert(String tableName, Map<String, String> values) throws SQLException {
		try {
			ContentValues contentValues = new ContentValues();
			for (Iterator<Entry<String, String>> iterator = values.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, String> entry = (Entry<String, String>) iterator.next();
				contentValues.put(entry.getKey(), entry.getValue());
			}
			database.insertOrThrow(tableName, null, contentValues);

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		}
	}

	@Override
	public int update(String tableName, Map<String, String> values, String whereClause, String[] whereValues) throws SQLException {
		try {
			ContentValues contentValues = new ContentValues();
			for (Iterator<Entry<String, String>> iterator = values.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, String> entry = (Entry<String, String>) iterator.next();
				contentValues.put(entry.getKey(), entry.getValue());
			}

			return database.update(tableName, contentValues, whereClause, whereValues);
		} catch (AndroidRuntimeException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		}
	}

	@Override
	public int delete(String tableName, String whereClause, String[] whereValues) throws SQLException {
		try {
			return database.delete(tableName, whereClause, whereValues);
		} catch (AndroidRuntimeException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		}
	}

	@Override
	public boolean inTransaction() throws SQLException {
		return database.inTransaction();
	}
}
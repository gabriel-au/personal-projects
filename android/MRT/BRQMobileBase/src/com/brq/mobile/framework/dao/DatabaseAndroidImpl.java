package com.brq.mobile.framework.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import com.brq.mobile.framework.core.Config;
import com.brq.mobile.framework.core.ConfigAccess;
import com.brq.mobile.framework.log.Log;

public class DatabaseAndroidImpl implements Database {

	private static final String TAG = DatabaseAndroidImpl.class.getName();

	private static final Config CONFIG = ConfigAccess.getConfig();

	private static final String DATABASE = CONFIG.diretoryData() + CONFIG.dataBaseName();

	private static SQLiteDatabase connection = null;

	public int count(String table) {

		SQLiteDatabase myDb = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);

		Cursor cursor = myDb.rawQuery("select * from " + table, null);
		int count = cursor.getCount();
		return count;

	}

	public void update(String table, List<Map<String, String>> list, String whereClause, String[] whereArgs) {

		SQLiteDatabase myDb = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);

		ContentValues values = new ContentValues();

		for (Map<String, String> map : list) {

			for (Entry<String, String> parameter : map.entrySet()) {
				values.put(parameter.getKey(), parameter.getValue());
			}

			myDb.update(table, values, whereClause, whereArgs);
		}

	}

	/**
	 * Metodo responsavel pela insercao de dados em uma tabela do banco de dados, atraves da execucao em batch.
	 * 
	 * @param table objeto do tipo <code>java.lang.String</code>, referente ao nome da tabela.
	 * @param list objeto do tipo <code>java.util.List<Map<String, String>></code>, referente a lista de instrucoes SQL que serao executadas.
	 */
	public void insertBatch(String table, List<Map<String, String>> list) {
		SQLiteDatabase myDb = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
		ContentValues values = new ContentValues();
		myDb.beginTransaction();

		for (Map<String, String> map : list) {

			for (Entry<String, String> parameter : map.entrySet()) {
				values.put(parameter.getKey(), parameter.getValue());
			}

			myDb.insert(table, null, values);
		}

		myDb.setTransactionSuccessful();

		myDb.endTransaction();

		myDb.close();

	}

	/**
	 * Metodo responsavel pela insercao de dados em uma tabela do banco de dados.
	 * 
	 * @param table objeto do tipo <code>java.lang.String</code>, referente ao nome da tabela.
	 * @param parameters objeto do tipo <code>java.util.Map<String, String></code>, referente aos parametros necessarios para tal insercao.
	 */
	public void insert(String table, Map<String, String> parameters) {

		SQLiteDatabase myDb = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);

		ContentValues values = new ContentValues();

		for (Entry<String, String> map : parameters.entrySet()) {
			values.put(map.getKey(), map.getValue());
		}

		long returnCode = myDb.insert(table, null, values);
		Log.i(TAG, "rows inserted: " + returnCode);
		
		closeSQLiteDatabase(myDb);

	}

	/**
	 * Fecha conexao com banco de dados
	 * 
	 * @param database representa conexao com o banco de dados
	 */
	private void closeSQLiteDatabase(SQLiteDatabase database) {
		try {
			if (database != null && database.isOpen() && !database.inTransaction()) {
				database.close();
			}
		} catch (SQLiteException e) {
			Log.e(TAG, e.getMessage());
		}
	}

	/**
	 * Fecha cursor do banco de dados
	 * 
	 * @param cursor representa cursor do banco de dados
	 * @throws SQLException
	 */
	private void closeSQLiteCursor(Cursor cursor) throws SQLException {
		try {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		} catch (SQLiteException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		}
	}

	/**
	 * Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data. Metodo responsavel por executar uma instrucao SQL.
	 * 
	 * @param query objeto do tipo <code>java.lang.String</code>, referente a instrucao SQL.
	 * @throws SQLException objeto do tipo <code>java.sql.SQLException</code>.
	 */
	@Override
	public void executeSQL(String sql) throws SQLException {
		SQLiteDatabase db = getConnection();
		try {
			db.execSQL(sql);
			closeSQLiteDatabase(db);
		} catch (SQLiteException ex) {
			Log.e(TAG, ex.getMessage());
			throw new SQLException(ex);
		}
	}
	
	@Override
	public int executeUpdate(String sql, String[] parameters) throws SQLException {		
		SQLiteDatabase dataBase = null;
		Cursor cursor = null;
		try {	
			dataBase = getConnection();
			
			SQLiteStatement compile = dataBase.compileStatement(sql);
			compile.bindAllArgsAsStrings(parameters);
			return compile.executeUpdateDelete();
		} catch (SQLiteException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		} finally {
			closeSQLiteCursor(cursor);
			closeSQLiteDatabase(dataBase);
		}
	}

	/**
	 * Metodo responsavel por apagar todos os dados de uma determinada tabela.
	 * 
	 * @param tableName objeto do tipo <code>java.lang.String</code>, referente ao nome da tabela.
	 * @throws SQLException objeto do tipo <code>java.sql.SQLException</code>.
	 */
	@Override
	public void eraseTable(String tableName) throws SQLException {
		SQLiteDatabase database = null;
		try {
			database = getConnection();
			database.delete(tableName, null, null);
		} catch (SQLiteException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		} finally {
			closeSQLiteDatabase(database);
		}
	}

	/**
	 * Metodo responsavel por executar uma instrucao SQL.
	 * 
	 * @param query objeto do tipo <code>java.lang.String</code> referente a instrucao SQL.
	 * @return objeto do tipo <code>java.util.List<Map<String, String>></code>, referente ao resultado da execucao da instrucao passada como argumento.
	 * @throws SQLException objeto do tipo <code>java.sql.SQLException</code>.
	 */
	@Override
	public List<Map<String, String>> executeQuery(String query) throws SQLException {
		return executeQuery(query, (String[]) null);
	}

	/**
	 * Executa intrucao SQL
	 * 
	 * @param query representa SQL query
	 * 
	 * @param parameters parametros para a query
	 * 
	 * @return [list of map] onde cada elemento da lista e composto de um map e cada item do map corresponde campo;valor de retorno da instrucao SQL
	 * 
	 * @throws SQLException em caso de erro na execucao da instrucao
	 */
	@Override
	public List<Map<String, String>> executeQuery(String query, String... parameters) throws SQLException {
		SQLiteDatabase dataBase = null;
		Cursor cursor = null;
		try {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map = null;

			dataBase = getConnection();
			cursor   = dataBase.rawQuery(query, parameters);
				
			String[] columnNames = cursor.getColumnNames();
			int columnCount = cursor.getColumnCount();
			
			while (cursor.moveToNext()) {

				map = new HashMap<String, String>(columnCount);
				for (int i = 0; i < columnCount; i++) {
					map.put(columnNames[i], cursor.getString(i));
				}
				list.add(map);
			}			
			return list;
		} catch (SQLiteException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		} finally {
			closeSQLiteCursor(cursor);
			closeSQLiteDatabase(dataBase);
		}
	}

	/**
	 * Metodo responsavel por obter o gerenciamento da transacao do banco de dados.
	 * 
	 * @return objeto do tipo <code>com.brq.mobile.framework.dao.TransactionManager</code>.
	 * @throws SQLException objeto do tipo <code>java.sql.SQLException</code>.
	 */
	@Override
	public TransactionManager getTransactionManager() throws SQLException {
		return new TransactionManagerAndroidImpl(getConnection());
	}

	private synchronized SQLiteDatabase getConnection() {
		
		if (connection == null) {
			connection = SQLiteDatabase.openDatabase(DATABASE, null, SQLiteDatabase.OPEN_READWRITE);
			return connection;
		}
		
		if (!connection.inTransaction()) {
			/*
			 * TODO: IMPORTANTE
			 * Evitar concorrência de bando de dados enquanto temos problema de performace
			 */
			while (connection.isOpen()) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {}
			}
			
			connection = SQLiteDatabase.openDatabase(DATABASE, null, SQLiteDatabase.OPEN_READWRITE);
		}
		
		return connection;
	}

	/**
	 * 
	 */
	@Override
	public int executeCount(String query, String... parameters) throws SQLException {
		SQLiteDatabase dataBase = null;
		Cursor cursor = null;
		try {
			dataBase = getConnection();
			cursor   = dataBase.rawQuery(query, parameters);
			cursor.moveToNext();
			return cursor.getInt(0);
		} catch (SQLiteException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		} finally {
			closeSQLiteCursor(cursor);
			closeSQLiteDatabase(dataBase);
		}
	}
}
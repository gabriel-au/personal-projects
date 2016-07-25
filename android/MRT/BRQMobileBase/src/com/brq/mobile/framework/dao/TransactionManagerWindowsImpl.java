package com.brq.mobile.framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.brq.mobile.framework.log.Log;

public class TransactionManagerWindowsImpl implements TransactionManager {

	private Map<String, String> insertTemplate = new HashMap<String, String>();
	private Map<String, String> updateTemplate = new HashMap<String, String>();
	private Map<String, String> deleteTemplate = new HashMap<String, String>();

	private static final String TAG = TransactionManagerWindowsImpl.class.getName();

	/**
	 * representa conex�o
	 */
	private Connection connection = null;

	/**
	 * Contrutor que recebe conex�o
	 * 
	 * encapsulamento protected para que apenas as classes de banco de dados
	 * possam instanciar essa classe
	 * 
	 * @param connection representa conex�o com o banco de dados no windows
	 */
	protected TransactionManagerWindowsImpl(Connection connection) throws SQLException {
		if (connection == null) {
			throw new IllegalArgumentException("connection cannot be null");
		}
		this.connection = connection;
	}

	@Override
	public void beginTransaction() throws SQLException {
		connection.setAutoCommit(false);
	}

	@Override
	public void commitTransaction() throws SQLException {
		connection.commit();
		connection.setAutoCommit(true);
	}

	@Override
	public void rollbackTransaction() throws SQLException {
		connection.rollback();
		connection.setAutoCommit(true);
	}

	@Override
	public boolean inTransaction() throws SQLException {
		return !connection.getAutoCommit();
	}

	@Override
	public void endTransaction() throws SQLException {
		connection.close();
	}

	@Override
	public void insert(String tableName, Map<String, String> values) throws SQLException {
		PreparedStatement prepareStatement = null;
		try {
			if (insertTemplate.get(tableName) == null) {

				StringBuilder sql = new StringBuilder();
				StringBuilder sqlFields = new StringBuilder();
				StringBuilder sqlValues = new StringBuilder();

				sql.append(" INSERT INTO ");
				sql.append(tableName);
				//
				for (Iterator<Entry<String, String>> iterator = values.entrySet().iterator(); iterator.hasNext();) {
					Entry<String, String> entry = (Entry<String, String>) iterator.next();

					sqlFields.append(entry.getKey());
					sqlValues.append("?");
					if (iterator.hasNext()) {
						sqlFields.append(", ");
						sqlValues.append(", ");
					}

				}
				sql.append("(").append(sqlFields).append(")");
				sql.append(" VALUES ");
				sql.append("(").append(sqlValues).append(")");

				insertTemplate.put(tableName, sql.toString());
			}

			int indexParameter = 0;
			prepareStatement = connection.prepareStatement(insertTemplate.get(tableName));
			for (Iterator<Entry<String, String>> iterator = values.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, String> entry = (Entry<String, String>) iterator.next();
				prepareStatement.setString(++indexParameter, entry.getValue());
			}

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		} finally {
			if (prepareStatement != null) {
				prepareStatement.close();
			}
		}

	}

	@Override
	public int update(String tableName, Map<String, String> values, String whereClause, String[] whereValues) throws SQLException {
		PreparedStatement prepareStatement = null;
		try {
			if (updateTemplate.get(tableName) == null) {

				StringBuilder sql = new StringBuilder();
				sql.append(" UPDATE ");
				sql.append(tableName);
				sql.append(" SET ");
				//
				for (Iterator<Entry<String, String>> iterator = values.entrySet().iterator(); iterator.hasNext();) {
					Entry<String, String> entry = (Entry<String, String>) iterator.next();
					sql.append(entry.getKey()).append(" = ? ");
					if (iterator.hasNext()) {
						sql.append(", ");
					}
				}
				//
				sql.append(" WHERE ");
				sql.append(whereClause);
				updateTemplate.put(tableName, sql.toString());
			}

			prepareStatement = connection.prepareStatement(updateTemplate.get(tableName));

			int indexParameter = 0;
			for (Iterator<Entry<String, String>> iterator = values.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, String> entry = (Entry<String, String>) iterator.next();
				prepareStatement.setString(++indexParameter, entry.getValue());
			}
			for (int count = 0; count < whereValues.length; count++) {
				prepareStatement.setString(++indexParameter, whereValues[count]);
			}

			return prepareStatement.executeUpdate();

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		} finally {
			if (prepareStatement != null) {
				prepareStatement.close();
			}
		}
	}

	@Override
	public int delete(String tableName, String whereClause, String[] whereValues) throws SQLException {
		PreparedStatement prepareStatement = null;
		try {

			if (deleteTemplate.get(tableName) == null) {

				StringBuilder sql = new StringBuilder();
				sql.append(" DELETE FROM ");
				sql.append(tableName);
				sql.append(" WHERE ");
				sql.append(whereClause);

				deleteTemplate.put(tableName, sql.toString());
			}

			prepareStatement = connection.prepareStatement(deleteTemplate.get(tableName));
			int indexParameter = 0;
			for (int count = 0; count < whereValues.length; count++) {
				prepareStatement.setString(++indexParameter, whereValues[count]);
			}

			return prepareStatement.executeUpdate();

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		} finally {
			if (prepareStatement != null) {
				prepareStatement.close();
			}

		}
	}
}
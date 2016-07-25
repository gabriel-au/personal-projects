package com.brq.mobile.framework.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brq.mobile.framework.core.Config;
import com.brq.mobile.framework.core.ConfigAccess;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.StringUtil;

public class DatabaseWindowsImpl implements Database {

	private static final String TAG = DatabaseWindowsImpl.class.getName();
	private static final Config CONFIG = ConfigAccess.getConfig();
	private static final String DATABASE = CONFIG.diretoryData() + CONFIG.dataBaseName();

	@Override
	public void insert(String table, Map<String, String> parameters) throws SQLException {
		String keys = StringUtil.join(parameters.keySet(), ",");
		List<String> values = new ArrayList<String>();

		for (String value : parameters.values()) {
			String str = "'" + value + "'";
			values.add(str);
		}

		String params = StringUtil.join(values, ",");
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ");
		sb.append(table);
		sb.append("(");
		sb.append(keys);
		sb.append(")");
		sb.append(" values(");
		sb.append(params);
		sb.append(")");
		sb.append(";");

		Connection connection = null;
		Statement statement = null;

		try {

			connection = getConnection(DATABASE);
			statement = connection.createStatement();
			statement.executeUpdate(sb.toString());

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}

	}

	@Override
	public void insertBatch(String table, List<Map<String, String>> list) throws SQLException {
		Connection connection = null;
		Statement statement = null;

		try {

			connection = getConnection(DATABASE);
			statement = connection.createStatement();
			connection.setAutoCommit(false);

			int batchSize = 2000;
			int count = 1;

			for (Map<String, String> map : list) {

				String keys = StringUtil.join(map.keySet(), ",");
				List<String> values = new ArrayList<String>();

				for (String value : map.values()) {
					String str = "'" + value + "'";
					values.add(str);
				}

				String params = StringUtil.join(values, ",");
				StringBuilder sb = new StringBuilder();
				sb.append("insert into ");
				sb.append(table);
				sb.append("(");
				sb.append(keys);
				sb.append(")");
				sb.append(" values(");
				sb.append(params);
				sb.append(")");
				sb.append(";");

				statement.addBatch(sb.toString());

				if ((++count % batchSize) == 0) {
					// System.out.println(Runtime.getRuntime().freeMemory() /
					// 1048576 + " MB livres");
					statement.executeBatch();
					statement.clearBatch();
					statement.clearWarnings();
				}
				// -Xmx4048m
			}

			statement.executeBatch();
			connection.commit();
			connection.setAutoCommit(true);

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());

			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				Log.e(TAG, e1.getMessage());
				throw new SQLException(e1);
			}

			throw new SQLException(e);

		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}

	}

	/**
	 * Recupera conexao com o banco de dados
	 * 
	 * @param dataBase url jdbc
	 * 
	 * @return interface Connection representando conexao com o banco de dados
	 * 
	 * @throws SQLException em caso de falha ao recuperar a conexao
	 */
	protected Connection getConnection(String dataBase) throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:".concat(dataBase));
		} catch (ClassNotFoundException ex) {
			Log.e(TAG, ex.getMessage());
			throw new SQLException(ex);
		} catch (SQLException ex) {
			Log.e(TAG, ex.getMessage());
			throw new SQLException(ex);
		}
	}

	/**
	 * Fecha conexao com o banco de dados
	 * 
	 * @param connection interface Connection representando conexao com o banco
	 *            de dados
	 * 
	 * @throws SQLException
	 */
	public static void closeConnection(Connection connection) throws SQLException {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		}
	}

	/**
	 * Fecha o statement
	 * 
	 * @param statement statement ativo
	 * @throws SQLException
	 */
	private void closeStatement(Statement statement) throws SQLException {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		}
	}

	/**
	 * Fecha o resultSet
	 * 
	 * @param statement resultSet ativo
	 * @throws SQLException
	 */
	private void closeResultSet(ResultSet resultSet) throws SQLException {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new SQLException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.brq.mobile.framework.dao.Database#executeSQL(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void executeSQL(String sql) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection(DATABASE);
			statement = connection.createStatement();
			statement.execute(sql);
		} catch (SQLException ex) {
			Log.e(TAG, ex.getMessage());
			throw new SQLException(ex);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}
	}
	
	@Override
	public int executeUpdate(String sql, String[] parameters) throws SQLException {
		Connection connection       = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection(DATABASE);
			statement  = connection.prepareStatement(sql);
			if (parameters != null) {
				for (int count = 0; count < parameters.length; count++) {
					statement.setString(count+1, parameters[count]);
				}
			}
			return statement.executeUpdate();
		} catch (SQLException ex) {
			Log.e(TAG, ex.getMessage());
			throw new SQLException(ex);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.brq.mobile.framework.dao.Database#eraseTable(java.lang.String)
	 */
	public void eraseTable(String tableName) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection(DATABASE);
			statement = connection.createStatement();
			statement.execute("DELETE FROM ".concat(tableName));
		} catch (SQLException ex) {
			Log.e(TAG, ex.getMessage());
			throw new SQLException(ex);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.brq.mobile.framework.dao.Database#executeQuery(java.lang.String)
	 */
	@Override
	public List<Map<String, String>> executeQuery(String query) throws SQLException {
		return executeQuery(query, (String[]) null);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.brq.mobile.framework.dao.Database#executeQuery(java.lang.String, java.lang.String ...)
	 */
	@Override
	public List<Map<String, String>> executeQuery(String query, String... parameters) throws SQLException {
		Connection connection       = null;
		PreparedStatement statement = null;
		ResultSet resultSet         = null;
		try {
			connection = getConnection(DATABASE);
			statement  = connection.prepareStatement(query);
			
			if (parameters != null) {
				for (int count = 0; count < parameters.length; count++) {
					statement.setString(count+1, parameters[count]);
				}
			}

			resultSet = statement.executeQuery();
			
			ResultSetMetaData metaData = resultSet.getMetaData();
			final int columnCount      = metaData.getColumnCount();

			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map = null;
			while (resultSet.next()) {
				map = new HashMap<String, String>();
				for (int count = 1; count <= columnCount; count++) {
					int type = metaData.getColumnType(count);
					if (type == Types.INTEGER) {
						map.put(metaData.getColumnName(count), String.valueOf(resultSet.getInt(count)));
					} else {
						map.put(metaData.getColumnName(count), resultSet.getString(count));
					}
				}
				list.add(map);
			}

			return list;

		} catch (SQLException ex) {
			Log.e(TAG, ex.getMessage());
			throw new SQLException(ex);
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.brq.mobile.framework.dao.Database#getTransactionManager()
	 */
	public TransactionManager getTransactionManager() throws SQLException {
		return new TransactionManagerWindowsImpl(getConnection(DATABASE));
	}

	@Override
	public int executeCount(String query, String... parameters) throws SQLException {
		Connection connection       = null;
		PreparedStatement statement = null;
		ResultSet resultSet         = null;
		try {
			connection = getConnection(DATABASE);
			statement  = connection.prepareStatement(query);
			
			if (parameters != null) {
				for (int count = 0; count < parameters.length; count++) {
					statement.setString(count+1, parameters[count]);
				}
			}

			resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
			
		} catch (SQLException ex) {
			Log.e(TAG, ex.getMessage());
			throw new SQLException(ex);
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
	}
	
	
	
	@Override
	public int count(String table) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(String table, List<Map<String, String>> list, String whereClause, String[] whereArgs) {
		// TODO Auto-generated method stub
		
	}

}
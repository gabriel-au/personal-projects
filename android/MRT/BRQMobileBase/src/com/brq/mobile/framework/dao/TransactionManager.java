package com.brq.mobile.framework.dao;

import java.sql.SQLException;
import java.util.Map;

public interface TransactionManager {

	/**
	 * Abre transacao
	 * 
	 * @throws SQLException em caso de erro ao chamar a operacao
	 */
	void beginTransaction() throws SQLException;

	/**
	 * Executa commit na transa��o
	 * 
	 * @throws SQLException em caso de erro ao chamar a opera��o
	 */
	void commitTransaction() throws SQLException;

	/**
	 * Executa rollback na transa��o
	 * 
	 * @throws SQLException em caso de erro ao chamar a opera��o
	 */
	void rollbackTransaction() throws SQLException;

	/**
	 * Finaliza transa��o
	 * 
	 * @throws SQLException em caso de erro ao chamar a opera��o
	 */
	void endTransaction() throws SQLException;

	/**
	 * Identifica se a transa��o esta ativa
	 * 
	 * @return <code>true</code>se a transa��o estiver ativa, <code>false</code>
	 *         caso contr�rio.
	 * 
	 * @throws SQLException em caso de erro ao chamar a opera��o
	 */
	boolean inTransaction() throws SQLException;

	/**
	 * Executa SQL insert
	 * 
	 * @param tableName nome da tabela
	 * 
	 * @param values [map] que representa a rela��o [campo,valor] que deve ser
	 *            inserido
	 * 
	 * @throws SQLException em caso de erro ao chamar a opera��o
	 */
	void insert(String tableName, Map<String, String> values) throws SQLException;

	/**
	 * Executa SQL update
	 * 
	 * @param tableName nome da tabela
	 * 
	 * @param values [map] que representa a rela��o [campo,valor] que deve ser
	 *            persistida no banco de dados
	 * 
	 * @param whereClause representa a clausula where, exemplo: CAMPO = ? AND
	 *            CAMPO = ? AND CAMPO = ?
	 * 
	 * @param whereValues representa os valores que ser�o utilizados na clausula
	 *            where. <br />
	 *            <strong>� necess�rio que os valores estejam na mesma ordem do
	 *            parametro [whereClause]</strong>
	 * 
	 * @return n�mero de registros afetados pela execu��o do commando UPDATE
	 * 
	 * @throws SQLException em caso de erro ao chamar a opera��o
	 */
	int update(String tableName, Map<String, String> values, String whereClause, String[] whereValues) throws SQLException;

	/**
	 * Executa SQL delete
	 * 
	 * @param tableName nome da tabela
	 * 
	 * @param whereClause representa a clausula where, exemplo: CAMPO = ? AND
	 *            CAMPO = ? AND CAMPO = ?
	 * 
	 * @param whereValues representa os valores que ser�o utilizados na clausula
	 *            where. <br />
	 *            <strong>� necess�rio que os valores estejam na mesma ordem do
	 *            parametro [whereClause]</strong>
	 * 
	 * @return n�mero de registros afetados pela execu��o do commando DELETE
	 * 
	 * @throws SQLException em caso de erro ao chamar a opera��o
	 */
	int delete(String tableName, String whereClause, String[] whereValues) throws SQLException;

}
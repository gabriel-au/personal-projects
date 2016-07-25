package com.brq.mobile.framework.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Database {

	public void update(String table, List<Map<String, String>> list, String whereClause, String[] whereArgs);	
	int count(String table);
	
	/**
	 * Metodo responsavel pela insercao de dados em uma tabela do banco de
	 * dados, atraves da execucao em batch.
	 * 
	 * @param table objeto do tipo <code>java.lang.String</code>, referente ao
	 *            nome da tabela.
	 * @param list objeto do tipo
	 *            <code>java.util.List<Map<String, String>></code>, referente a
	 *            lista de instrucoes SQL que serao executadas.
	 */
	void insertBatch(String table, List<Map<String, String>> list) throws SQLException;

	/**
	 * Metodo responsavel pela insercao de dados em uma tabela do banco
	 * de dados.
	 * 
	 * @param table objeto do tipo <code>java.lang.String</code>, referente ao
	 *            nome da tabela.
	 * @param parameters objeto do tipo
	 *            <code>java.util.Map<String, String></code>, referente ao
	 *            parametros necessarios para tal insercao.
	 */

	void insert(String table, Map<String, String> parameters) throws SQLException;

	/**
	 * Metodo responsavel por obter o gerenciamento da transacao do banco de dados.
	 * 
	 * @return objeto do tipo
	 *         <code>com.brq.mobile.framework.dao.TransactionManager</code>.
	 * @throws SQLException objeto do tipo <code>java.sql.SQLException</code>.
	 */
	TransactionManager getTransactionManager() throws SQLException;

	/**
	 * Executa instrucao sql diretamente no banco de dados.
	 * 
	 * @param sql representa instrucao SQL
	 */
	public void executeSQL(String sql) throws SQLException;
	
	/**
	 * Executa instrucao sql diretamente no banco de dados.
	 * 
	 * @param sql representa instrucao SQL
	 */
	public int executeUpdate(String sql, String[] parameters) throws SQLException;

	/**
	 * Apaga todos os dados de uma tabela
	 * 
	 * @param tableName representa o nome da tabela
	 * 
	 * @throws SQLException no caso de erro de execucao na operacao
	 */
	public void eraseTable(String tableName) throws SQLException;

	/**
	 * Executa intrucao SQL
	 * 
	 * @param query representa SQL query
	 * 
	 * 
	 * @return [list of map] onde cada elemento da lista e composto de um map e
	 *         cada item do map corresponde campo;valor de retorno da instrucao
	 *         SQL
	 * 
	 * @throws SQLException em caso de erro na execucao da instrucao
	 */
	public List<Map<String, String>> executeQuery(String query) throws SQLException;
	
	/**
	 * Executa intrucao SQL
	 * 
	 * @param query representa SQL query
	 *
	 * @param parameters parametros para a query
	 * 
	 * @return [list of map] onde cada elemento da lista e composto de um map e
	 *         cada item do map corresponde campo;valor de retorno da instrucao
	 *         SQL
	 * 
	 * @throws SQLException em caso de erro na execucao da instrucao
	 */
	public List<Map<String, String>> executeQuery(String query, String ... parameters) throws SQLException;
	
	/**
	 * 
	 * @param query
	 * @param parameters
	 * @return
	 * @throws SQLException 
	 */
	int executeCount(String query, String... parameters) throws SQLException;
}

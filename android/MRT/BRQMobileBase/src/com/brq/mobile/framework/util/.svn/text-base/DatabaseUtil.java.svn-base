package com.brq.mobile.framework.util;


public class DatabaseUtil {
	
	public static final String REGEX_ESPACO = "\\s+";

	/**
	 * <p>
	 * Metodo responsavel por montar a query com parametros
	 * </p>
	 * <p>
	 * Os parametros ser�o demarcados com o simbolo <b>%s</b> e dever�o ser passados na <b><u>ORDEM</u></b> que os mesmos estao na query. Caso o parametro que deseja passar seja uma string, colocar aspas simples no simbolo de parametros, ex: <b>'%s'</b>.
	 * </p>
	 * <p>
	 * Exemplo de query com parametros: <i>SELECT x FROM tabela WHERE x = '%s' and y = %s </i>
	 * </p>
	 * Continua ... </br>
	 * <p>
	 * Para queries dinamicas(onde a quantidade de parametros pode mudar) as linhas que ser�o "opcionais" terao de ser demarcadas com o simbolo <b>#</b>.
	 * <p>
	 * Exemplo de query dinamica com parametros: <i>SELECT x FROM tabela WHERE x = '%s' </i> <b>#</b> <i>and y = %s </i> <b>#</b> <i>and z = %s </i>
	 * 
	 * @param query - Query a ser montada
	 * @param parameters - parametros da query
	 * @return query parametrizada.
	 */
	public static String montaQuery(String query, Object... parameters) {
		int length = parameters.length;

		for (int i = 1; i < length; i++) {
			query = query.replaceFirst("#", " ");
		}

		StringBuilder formattedQuery = new StringBuilder(query);

		if (query.contains("#")) {
			formattedQuery = formattedQuery.delete(query.indexOf("#"), (query.length() - 1));
		}

		return String.format(formattedQuery.toString(), parameters).replaceAll(REGEX_ESPACO, StringUtil.SPACE);
	}

	/**
	 * <p>
	 * Metodo responsavel por montar a query com parametros
	 * </p>
	 * <p>
	 * Os parametros ser�o demarcados com o simbolo <b>%s</b> e dever�o ser passados na <b><u>ORDEM</u></b> que os mesmos estao na query. Caso o parametro que deseja passar seja uma string, colocar aspas simples no simbolo de parametros, ex: <b>'%s'</b>.
	 * </p>
	 * <p>
	 * Exemplo de query com parametros: <i>SELECT x FROM tabela WHERE x = '%s' and y = %s </i>
	 * </p>
	 * Continua ... </br>
	 * <p>
	 * Para queries dinamicas(onde a quantidade de parametros pode mudar) as linhas que ser�o "opcionais" terao de ser demarcadas com o simbolo <b>#</b>.
	 * <p>
	 * Exemplo de query dinamica com parametros: <i>SELECT x FROM tabela WHERE x = '%s' </i> <b>#</b> <i>and y = %s </i> <b>#</b> <i>and z = %s </i>
	 * 
	 * @param query - Query a ser montada
	 * @param parameters - parametros da query
	 * @return query parametrizada.
	 */
	public static String montaQuery(StringBuilder query, Object... parameters) {
		String sql = query.toString();

		for (int i = 1; i < parameters.length; i++) {
			sql = sql.replaceFirst("#", " ");
		}

		StringBuilder formattedQuery = new StringBuilder(sql);
		
		if (sql.contains("#")) {
			formattedQuery = formattedQuery.delete(sql.indexOf("#"), (sql.length() - 1));
		}

		return String.format(formattedQuery.toString(), parameters).replaceAll(REGEX_ESPACO, StringUtil.SPACE);
	}
	
	public static String montaQueryGenerica(StringBuilder query, Object... parameters) {
		String sql = query.toString();
		for (int i = 1; i < parameters.length; i++) {
			sql = sql.replaceFirst("#", " ");
		}

		StringBuilder formattedQuery = new StringBuilder(sql.replaceAll("%s", "\\'%s\\'"));
		if (sql.contains("#")) {
			formattedQuery = formattedQuery.delete(sql.indexOf("#"), (sql.length() - 1));
		}

		return String.format(formattedQuery.toString().replaceAll("\\''", "\\'"), parameters).replaceAll("\'null\'", "null").replaceAll(REGEX_ESPACO, StringUtil.SPACE);
	}
	
}

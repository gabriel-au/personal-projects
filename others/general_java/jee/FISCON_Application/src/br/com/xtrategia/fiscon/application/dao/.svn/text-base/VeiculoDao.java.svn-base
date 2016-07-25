package br.com.xtrategia.fiscon.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import br.com.xtrategia.fiscon.application.ConexaoBanco;
import br.com.xtrategia.fiscon.application.LogApplicacao;
import br.com.xtrategia.fiscon.application.pojo.VeiculoPojo;

public class VeiculoDao extends ConexaoBanco<VeiculoPojo> {

	private String consultaMarcaModelo = "select id, codigo from marca_modelo";
	private String consultaCor = "select id, codigo from cor";
	private String consultaTipo = "select id, codigo from tipo";
	private String consultaCategoriaVeiculo = "select id, codigo from categoria";
	private String consultaEspecie = "select id, codigo from especie";
	private String consultaMunicipio = "select id, codigo from municipio";

	private Statement pstmMarcaModelo;
	private Statement pstmCor;
	private Statement pstmTipo;
	private Statement pstmCategoriaVeiculo;
	private Statement pstmEspecie;
	private Statement pstmMunicipio;

	private Map<Integer, String> mapaMarcaModelo = new HashMap<Integer, String>();
	private Map<Integer, String> mapaCor = new HashMap<Integer, String>();
	private Map<Integer, String> mapaTipo = new HashMap<Integer, String>();
	private Map<Integer, String> mapaCategoriaVeiculo = new HashMap<Integer, String>();
	private Map<Integer, String> mapaEspecie = new HashMap<Integer, String>();
	private Map<Integer, String> mapaMunicipio = new HashMap<Integer, String>();
	private Map<String, Integer> mapaMarcaModeloPorCodigo = new HashMap<String, Integer>();
	private Map<String, Integer> mapaCorPorCodigo = new HashMap<String, Integer>();
	private Map<String, Integer> mapaTipoPorCodigo = new HashMap<String, Integer>();
	private Map<String, Integer> mapaCategoriaVeiculoPorCodigo = new HashMap<String, Integer>();
	private Map<String, Integer> mapaEspeciePorCodigo = new HashMap<String, Integer>();
	private Map<String, Integer> mapaMunicipioPorCodigo = new HashMap<String, Integer>();

	@Override
	public void iniciar() throws SQLException {
		// Inativar todos os registros
		LogApplicacao.gravar("Processo de Inativar os registros");
		String sql = "update veiculo set ativo= false where ativo=true";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);

		LogApplicacao.gravar("preparando as consultas auxiliares");

		pstmMarcaModelo = conn.createStatement();
		popularMapa(pstmMarcaModelo, consultaMarcaModelo, mapaMarcaModelo,
				mapaMarcaModeloPorCodigo);

		pstmCor = conn.createStatement();
		popularMapa(pstmCor, consultaCor, mapaCor, mapaCorPorCodigo);

		pstmTipo = conn.createStatement();
		popularMapa(pstmTipo, consultaTipo, mapaTipo, mapaTipoPorCodigo);

		pstmCategoriaVeiculo = conn.createStatement();
		popularMapa(pstmCategoriaVeiculo, consultaCategoriaVeiculo,
				mapaCategoriaVeiculo, mapaCategoriaVeiculoPorCodigo);

		pstmEspecie = conn.createStatement();
		popularMapa(pstmEspecie, consultaEspecie, mapaEspecie,
				mapaEspeciePorCodigo);

		pstmMunicipio = conn.createStatement();
		popularMapa(pstmMunicipio, consultaMunicipio, mapaMunicipio,
				mapaMunicipioPorCodigo);
	}

	private void popularMapa(Statement stmt, String sql,
			Map<Integer, String> mapa, Map<String, Integer> mapaPorCodigo)
			throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			mapa.put(rs.getInt(1), rs.getString(2));
			mapaPorCodigo.put(rs.getString(2), rs.getInt(1));
		}
	}

	@Override
	public void executar(VeiculoPojo pojo) throws SQLException {
		// consultar se existe
		pstmConsulta.setString(1, pojo.getPlaca());
		ResultSet rs = pstmConsulta.executeQuery();
		// se existir
		if (rs.next()) {
			VeiculoPojo pojoOld = new VeiculoPojo();
			pojoOld.setId(rs.getInt(1));

			buscarRelacionamentos(rs, pojoOld);

			pojoOld.setAtivo(rs.getBoolean(8));
			pojoOld.setDataModificacao(rs.getTimestamp(9));
			pojoOld.setPlaca(rs.getString(10));
			pojoOld.setAnoModelo(rs.getInt(11));
			pojoOld.setAnoFabricacao(rs.getInt(12));

			// verificar se é diferente e gravar o historio
			if (!pojoOld.equals(pojo)) {
				pstmInsertHistorico.setInt(1, pojoOld.getId());
				pstmInsertHistorico.setInt(2, pojoOld.getCor().getId());
				pstmInsertHistorico.setInt(3, pojoOld.getCategoria().getId());
				pstmInsertHistorico.setInt(4, pojoOld.getMarcaModelo().getId());
				pstmInsertHistorico.setInt(5, pojoOld.getMunicipio().getId());
				pstmInsertHistorico.setInt(6, pojoOld.getEspecie().getId());
				pstmInsertHistorico.setInt(7, pojoOld.getTipo().getId());
				
				pstmInsertHistorico.setBoolean(8, pojoOld.isAtivo());
				pstmInsertHistorico.setTimestamp(9, pojoOld.getDataModificacao());
				pstmInsertHistorico.setString(10, pojoOld.getPlaca());
				pstmInsertHistorico.setInt(11, pojoOld.getAnoModelo());
				pstmInsertHistorico.setInt(12, pojoOld.getAnoFabricacao());
				pstmInsertHistorico.execute();
			}
			// atualiza o registro para ficar ativo novamente
			buscarRelacionamentos(pojo);
			pstmUpdate.setInt(1, pojo.getCor().getId());
			pstmUpdate.setInt(2, pojo.getCategoria().getId());
			pstmUpdate.setInt(3, pojo.getMarcaModelo().getId());
			pstmUpdate.setInt(4, pojo.getMunicipio().getId());
			pstmUpdate.setInt(5, pojo.getEspecie().getId());
			pstmUpdate.setInt(6, pojo.getTipo().getId());
			pstmUpdate.setBoolean(7, true);
			pstmUpdate.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			pstmUpdate.setString(9, pojo.getPlaca());
			pstmUpdate.setInt(10, pojo.getAnoModelo());
			pstmUpdate.setInt(11, pojo.getAnoFabricacao());
			pstmUpdate.setInt(12, pojo.getId());
			pstmUpdate.execute();
		} else {
			// se não existir, inserir
			buscarRelacionamentos(pojo);

			pstmInsert.setInt(1, pojo.getCor().getId());
			pstmInsert.setInt(2, pojo.getCategoria().getId());
			pstmInsert.setInt(3, pojo.getMarcaModelo().getId());
			pstmInsert.setInt(4, pojo.getMunicipio().getId());
			pstmInsert.setInt(5, pojo.getEspecie().getId());
			pstmInsert.setInt(6, pojo.getTipo().getId());
			pstmInsert.setBoolean(7, true);
			pstmInsert.setTimestamp(8,
					new Timestamp(System.currentTimeMillis()));
			pstmInsert.setString(9, pojo.getPlaca());
			pstmInsert.setInt(10, pojo.getAnoModelo());
			pstmInsert.setInt(11, pojo.getAnoFabricacao());
			pstmInsert.execute();
		}

	}

	private void buscarRelacionamentos(ResultSet rs, VeiculoPojo pojoOld)
			throws SQLException {
		String  codigo;

		codigo = mapaCor.get(rs.getInt(2));
		pojoOld.getCor().setCodigo(codigo);

		codigo = mapaCategoriaVeiculo.get(rs.getInt(3));
		pojoOld.getCategoria().setCodigo(codigo);

		codigo = mapaMarcaModelo.get(rs.getInt(4));
		pojoOld.getMarcaModelo().setCodigo(codigo);

		codigo = mapaMunicipio.get(rs.getInt(5));
		pojoOld.getMunicipio().setCodigo(codigo);

		codigo = mapaEspecie.get(rs.getInt(6));
		pojoOld.getEspecie().setCodigo(codigo);

		codigo = mapaTipo.get(rs.getInt(7));
		pojoOld.getTipo().setCodigo(codigo);
	}

	private void buscarRelacionamentos(VeiculoPojo pojo) throws SQLException {
		int id;
		try {
			id = mapaCorPorCodigo.get(pojo.getCor().getCodigo());
		} catch (Exception e) {
			id = 1;
		}
		pojo.getCor().setId(id);

		try {
			id = mapaCategoriaVeiculoPorCodigo.get(pojo.getCategoria()
					.getCodigo());
		} catch (Exception e) {
			id = 1;
		}
		pojo.getCategoria().setId(id);

		try {
			id = mapaMarcaModeloPorCodigo
					.get(pojo.getMarcaModelo().getCodigo());
		} catch (Exception e) {
			id = 1;
		}
		pojo.getMarcaModelo().setId(id);

		try {
			id = mapaMunicipioPorCodigo.get(pojo.getMunicipio().getCodigo());
		} catch (Exception e) {
			id = 1;
		}
		pojo.getMunicipio().setId(id);

		try {
			id = mapaEspeciePorCodigo.get(pojo.getEspecie().getCodigo());
		} catch (Exception e) {
			id = 1;
		}
		pojo.getEspecie().setId(id);

		try {
			id = mapaTipoPorCodigo.get(pojo.getTipo().getCodigo());
		} catch (Exception e) {
			id = 1;
		}
		pojo.getTipo().setId(id);
	}

	@Override
	public String getSqlConsulta() {
		return "Select id, id_cor, id_categoria, id_marca_modelo, id_municipio, id_especie, id_tipo,"
				+ "ativo, data_modificacao, veiculo_placa, ano_modelo, ano_fabricacao"
				+ " from veiculo where veiculo_placa = ?";
	}

	@Override
	public String getSqlInsert() {
		return "insert into veiculo (id_cor, id_categoria, id_marca_modelo, id_municipio, id_especie, id_tipo,"
				+ "ativo, data_modificacao, veiculo_placa, ano_modelo, ano_fabricacao)"
				+ "values (?,?,?,?,?,?,   ?,?,?,?,?)";

	}

	@Override
	public String getSqlInsertHistorico() {
		return "insert into historico_veiculo (id, id_cor, id_categoria, id_marca_modelo, id_municipio, id_especie, id_tipo,"
				+ "ativo, data_modificacao, veiculo_placa, ano_modelo, ano_fabricacao)"
				+ "values (?,?,?,?,?,?,?,   ?,?,?,?,?)";
	}

	@Override
	public String getSqlUpdate() {
		return "update veiculo set id_cor=?, id_categoria=?, id_marca_modelo=?, "
				+ "id_municipio=?, id_especie=?, id_tipo=?, "
				+ "ativo=?, data_modificacao=?, veiculo_placa=?, "
				+ "ano_modelo=?, ano_fabricacao=? where id = ?";
	}

}

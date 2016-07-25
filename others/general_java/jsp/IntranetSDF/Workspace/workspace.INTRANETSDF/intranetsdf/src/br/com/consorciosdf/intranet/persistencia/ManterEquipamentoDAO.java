package br.com.consorciosdf.intranet.persistencia;

import br.com.consorciosdf.intranet.modelo.Equipamento;
import br.com.consorciosdf.intranet.modelo.Paginacao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ManterEquipamentoDAO {

	private Conexao conexao;
	private Connection connection;

	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public Equipamento recuperarEquipamentos(int id) throws SQLException,
			ParseException {
		List list = null;

		PreparedStatement pstm = null;
		Equipamento equipamento;
		ResultSet rs;

		if (conexao == null || connection == null) {
			conexao = new Conexao();
			connection = conexao.abreConexao();
		}

		try {
			String sql = "SELECT * FROM equipamentos " + "WHERE ativo = 'y' "
					+ "AND id = ? "
					+ " ORDER BY tipo DESC, num_equipamento ASC";

			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			if (rs != null) {
				list = new ArrayList();

				// Date dataInclusao = null;

				while (rs.next()) {

					equipamento = new Equipamento();
					equipamento.setIdEquipamento(rs.getInt("id"));
					equipamento.setNomeEquipamento(rs.getString("nome"));
					equipamento.setDescricaoEquipamento(rs
							.getString("descricao"));
					equipamento.setEnderecoLogradouroEquipamento(rs
							.getString("end_logradouro"));
					equipamento.setEnderecoCidadeEquipamento(rs
							.getString("end_cidade"));
					equipamento.setEnderecoEstadoEquipamento(rs
							.getString("end_estado"));
					equipamento.setIdPai(rs.getInt("id_pai"));
					equipamento.setDataInclusao(dateFormat.parse(rs
							.getString("data_inclusao")));
					equipamento.setTipo(rs.getString("tipo"));

					list.add(equipamento);
				}
				return (Equipamento) list.get(0);
			}
		} catch (SQLException e) {
			System.out
					.println("Erro(CheckEquipamentoDAO.recuperarEquipamentos): "
							+ e.getMessage());
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (connection != null) {
					conexao.fechaConexao(connection);
					conexao = null;
					connection = null;
				}
			} catch (SQLException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}

		return (Equipamento) list.get(0);
	}

	public List recuperarEquipamentos() throws SQLException {
		List list = null;

		PreparedStatement pstm = null;
		Equipamento equipamento;
		ResultSet rs;

		if (conexao == null || connection == null) {
			conexao = new Conexao();
			connection = conexao.abreConexao();
		}

		try {
			String sql = "SELECT * FROM equipamentos " + "WHERE ativo = 'y' "
					+ "ORDER BY nome ASC"; //tipo DESC, num_equipamento ASC";

			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs != null) {
				list = new ArrayList();

				while (rs.next()) {
					equipamento = new Equipamento();
					equipamento.setIdEquipamento(rs.getInt("id"));
					equipamento.setNomeEquipamento(rs.getString("nome"));
					equipamento.setDescricaoEquipamento(rs
							.getString("descricao"));
					equipamento.setEnderecoLogradouroEquipamento(rs
							.getString("end_logradouro"));
					equipamento.setEnderecoCidadeEquipamento(rs
							.getString("end_cidade"));
					equipamento.setEnderecoEstadoEquipamento(rs
							.getString("end_estado"));
					// equipamento.setDataInclusao(rs.)

					list.add(equipamento);
				}
				return list;
			}
		} catch (SQLException e) {
			System.out
					.println("Erro(CheckEquipamentoDAO.recuperarEquipamentos): "
							+ e.getMessage());
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (connection != null) {
					conexao.fechaConexao(connection);
					conexao = null;
					connection = null;
				}
			} catch (SQLException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}

		return list;
	}

	public List recuperarEquipamentosPag(String ativo, String equi,
			String endereco, String cidade, int pageNumber, int pageLimit,
			String orderBy, String order) throws SQLException {
		List list = null;

		PreparedStatement pstm = null;
		Equipamento equipamento;
		ResultSet rs;
		Paginacao paginacao;

		// Filtro
		String filtro = "";

		if (ativo != null) {
			if (ativo.trim().equals("y")) {
				filtro = " WHERE ativo = 'y' ";
			} else if (ativo.trim().equals("n")) {
				filtro = " WHERE ativo = 'n' ";
			}
		}

		if (equi != null && !equi.trim().equals("")) {
			if (filtro.equals("")) {
				filtro += " WHERE nome LIKE '%" + equi + "%'";
			} else {
				filtro += " AND nome LIKE '%" + equi + "%'";
			}
		}

		if (endereco != null && !endereco.trim().equals("")) {
			if (filtro.equals("")) {
				filtro += " WHERE end_logradouro LIKE '%" + endereco + "%'";
			} else {
				filtro += " AND end_logradouro LIKE '%" + endereco + "%'";
			}
		}
		if (cidade != null && !cidade.trim().equals("")) {
			if (filtro.equals("")) {
				filtro += " WHERE end_cidade LIKE '%" + cidade + "%'";
			} else {
				filtro += " AND end_cidade LIKE '%" + cidade + "%'";
			}
		}

		// ordenacao
		String ordenacao = " ORDER BY nome ASC";

		if (orderBy != null) {
			if (!orderBy.trim().equals("")) {
				if (orderBy.trim().equals("equipamento")) {
					ordenacao = " ORDER BY tipo DESC, num_equipamento ASC "
							+ order;
				} else if (orderBy.trim().equals("endereco")) {
					ordenacao = " ORDER BY end_logradouro " + order;
				} else if (orderBy.trim().equals("cidade")) {
					ordenacao = " ORDER BY end_cidade " + order;
				}
			}
		}

		// paginacao
		int pageInit = 0;
		int resultSize = 0;
		int pageNavigator = 0;
		int pageLeft = 0;

		pageInit = pageNumber * pageLimit;

		if (conexao == null || connection == null) {
			conexao = new Conexao();
			connection = conexao.abreConexao();
		}

		try {
			String sql = "SELECT * FROM equipamentos" + filtro + ordenacao;

			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs != null) {
				rs.last();
				resultSize = rs.getRow();
				rs.beforeFirst();

				if (resultSize > pageLimit) {
					sql += " LIMIT " + pageInit + ", " + pageLimit;
					pageNavigator = resultSize / pageLimit;
					pageLeft = resultSize % pageLimit;

					if (pageLeft > 0) {
						pageNavigator++;
					}

					pstm = connection.prepareStatement(sql);
					rs = pstm.executeQuery();
				} else {
					pageNavigator = 1;
				}

				list = new ArrayList();

				while (rs.next()) {
					equipamento = new Equipamento();
					paginacao = new Paginacao();

					paginacao.setPageInit(pageInit);
					paginacao.setPageLeft(pageLeft);
					paginacao.setPageLimit(pageLimit);
					paginacao.setPageNavigator(pageNavigator);
					paginacao.setPageNumber(pageNumber);
					paginacao.setResultSize(resultSize);

					equipamento.setIdEquipamento(rs.getInt("id"));
					equipamento.setNomeEquipamento(rs.getString("nome"));
					equipamento.setDescricaoEquipamento(rs
							.getString("descricao"));
					equipamento.setEnderecoLogradouroEquipamento(rs
							.getString("end_logradouro"));
					equipamento.setEnderecoCidadeEquipamento(rs
							.getString("end_cidade"));
					equipamento.setEnderecoEstadoEquipamento(rs
							.getString("end_estado"));
					// equipamento.setDataInclusao(rs.)

					equipamento.setPaginacao(paginacao);

					list.add(equipamento);
				}
				return list;
			}
		} catch (SQLException e) {
			System.out
					.println("Erro(CheckEquipamentoDAO.recuperarEquipamentos): "
							+ e.getMessage());
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (connection != null) {
					conexao.fechaConexao(connection);
					conexao = null;
					connection = null;
				}
			} catch (SQLException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}

		return list;
	}

	public int recuperarQuantidadeEquipamentos(boolean ativo, String tipo)
			throws SQLException {
		PreparedStatement pstm = null;
		ResultSet rs;

		if (conexao == null || connection == null) {
			conexao = new Conexao();
			connection = conexao.abreConexao();
		}

		String ativoVerify = "";

		if (ativo) {
			ativoVerify = "y";
		} else {
			ativoVerify = "n";
		}

		try {
			String sql = "SELECT COUNT(*) as qtd " + "FROM equipamentos "
					+ "WHERE tipo = '" + tipo + "' " + "AND ativo = '"
					+ ativoVerify + "'";

			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs != null) {
				rs.last();

				return rs.getInt("qtd");
			}

		} catch (SQLException e) {
			System.out.println("Erro(OSDAO.recuperarQuantidadeEquipamentos): "
					+ e.getMessage());
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (connection != null) {
					conexao.fechaConexao(connection);
					conexao = null;
					connection = null;
				}
			} catch (SQLException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}

		return 0;
	}

	public boolean salvarEquipamento(Equipamento equipamento)
			throws SQLException {
		PreparedStatement pstm = null;
		ResultSet rs;
		java.util.Date dtNow = new java.util.Date();
		String dataInclusao = dateFormat.format(dtNow);

		// String dataTem = dateFormat.format(dtNow);

		if (conexao == null || connection == null) {
			conexao = new Conexao();
			connection = conexao.abreConexao();
		}

		try {
			String sql = "INSERT INTO equipamentos (nome, tipo, descricao, end_logradouro, "
					+ "end_estado, end_cidade, data_inclusao, id_pai, num_equipamento) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
			// for(int i=0; i< equipamentos.size(); i++){
			// sql+= "(";
			// sql += "'"+equipamentos.get(i).getNomeEquipamento()+"',";
			// sql += "'"+equipamentos.get(i).getTipo()+"',";
			// sql += "'"+equipamentos.get(i).getDescricaoEquipamento()+"',";
			// sql +=
			// "'"+equipamentos.get(i).getEnderecoLogradouroEquipamento()+"',";
			// sql +=
			// "'"+equipamentos.get(i).getEnderecoEstadoEquipamento()+"',";
			// sql +=
			// "'"+equipamentos.get(i).getEnderecoCidadeEquipamento()+"',";
			// sql += "'"+dataInclusao+"',";
			// sql +="'y'";
			// sql+=")";
			// if(i!=equipamentos.size()){
			// sql+=",";
			// }else{
			// sql+=";";
			// }
			//
			// }
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, equipamento.getNomeEquipamento());
			pstm.setString(2, equipamento.getTipo());
			pstm.setString(3, equipamento.getDescricaoEquipamento());
			pstm.setString(4, equipamento.getEnderecoLogradouroEquipamento());
			pstm.setString(5, equipamento.getEnderecoEstadoEquipamento());
			pstm.setString(6, equipamento.getEnderecoCidadeEquipamento());
			pstm.setString(7, dataInclusao);
			pstm.setInt(8, equipamento.getIdPai());
			pstm.setInt(9, equipamento.getNumSerie());

			pstm.execute();
			// equipamentos.clear();
			return true;

		} catch (Exception e) {
			System.out.println("Erro(ManterEquipamentoDAO.salvarEquipamento): "
					+ e.getMessage());
		} finally {
			try {
				if (pstm == null) {
					pstm.close();

				}
				if (connection != null) {
					conexao.fechaConexao(connection);
					conexao = null;
					connection = null;

				}

			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}

		return false;
	}

	public boolean alterarEquipamento(Equipamento equipamento)
			throws SQLException {
		PreparedStatement pstm = null;
		ResultSet rs;
		java.util.Date dtNow = new java.util.Date();
		String dataAlteracao = dateFormat.format(dtNow);
		// CheckEquipamento checkEquipamento;

		String idEquipamento = "";
		String cidade = "";
		String nome = "";
		String descricao = "";
		String endereco = "";
		String estado = "";
		// String equipamento = "";

		// checkEquipamento = os.getCheckEquipamento();

		if (conexao == null || connection == null) {
			conexao = new Conexao();
			connection = conexao.abreConexao();
		}

		try {
			String sql = "";

			sql = "UPDATE equipamentos SET nome='"
					+ equipamento.getNomeEquipamento() + "', descricao ='"
					+ equipamento.getDescricaoEquipamento()
					+ "', end_cidade ='"
					+ equipamento.getEnderecoCidadeEquipamento()
					+ "', end_estado ='"
					+ equipamento.getEnderecoEstadoEquipamento()
					+ "', end_logradouro ='"
					+ equipamento.getEnderecoLogradouroEquipamento()
					+ "', id_pai ='" + equipamento.getIdPai()
					+ "', data_alteracao ='" + dataAlteracao + "' WHERE id = "
					+ equipamento.getIdEquipamento();

			pstm = connection.prepareStatement(sql);
			pstm.execute();

			return true;
		} catch (SQLException e) {
			System.out.println("Erro(EquipamentoDAO.alterarEquipamento): "
					+ e.getMessage());
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (connection != null) {
					conexao.fechaConexao(connection);
					conexao = null;
					connection = null;
				}
			} catch (SQLException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}

		return false;
	}

	public List<String> recuperaEquipamentosCidades()throws SQLException {
		List<String> list = null;
		Statement stm = null;
		ResultSet rs = null;

		if (conexao == null || connection == null) {
			conexao = new Conexao();
			connection = conexao.abreConexao();
		}
		
		String sql = "SELECT DISTINCT end_cidade FROM equipamentos";
		
		try {
			stm = connection.createStatement();
			rs = stm.executeQuery(sql);
			list = new ArrayList<String>();
			
			while (rs.next()) {
				list.add(rs.getString("end_cidade").replace("Í", "I").replace("Á", "A").replace("É", "E")
						.replace("Õ", "O").replace("Ú", "U").replace("Ã", "A"));
			}
			
		} catch (Exception e) {
			System.out.println("Erro(EquipamentoDAO.recuperaEquipamentosCidades): "
					+ e.getMessage());
		}finally{
			if (connection != null) {
				conexao.fechaConexao(connection);
				conexao = null;
				connection = null;
			}
		}
		
		return list;

	}

}

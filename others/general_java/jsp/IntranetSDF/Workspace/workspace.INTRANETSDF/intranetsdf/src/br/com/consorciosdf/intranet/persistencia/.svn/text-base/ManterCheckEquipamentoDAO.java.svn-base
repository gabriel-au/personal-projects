package br.com.consorciosdf.intranet.persistencia;

import br.com.consorciosdf.intranet.modelo.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ManterCheckEquipamentoDAO {
    
    private Conexao conexao;
    private Connection connection;
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public int salvarChecagem(CheckEquipamento checkEquipamento) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs;
        
        Equipamento equipamento = checkEquipamento.getEquipamento();
        Usuario usuario = checkEquipamento.getUsuario();
        
        String dataInicio = dateFormat.format(checkEquipamento.getDataInicio());
        String dataFim = dateFormat.format(checkEquipamento.getDataFim());
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql_01 = "INSERT INTO checklist (id_equipamento, " +
                    "matricula_usuario, num_serie, num_upr, num_camera, num_flash, " +
                    "obs, exec_coleta, data_inicio, data_fim) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            pstm = connection.prepareStatement(sql_01);
            pstm.setInt(1, equipamento.getIdEquipamento());
            pstm.setString(2, usuario.getMatriculaUsuario());
            pstm.setInt(3, checkEquipamento.getNumSerie());
            pstm.setInt(4, checkEquipamento.getNumUPR());
            pstm.setInt(5, checkEquipamento.getNumCamera());
            pstm.setString(6, checkEquipamento.getNumFlash());
            pstm.setString(7, checkEquipamento.getObservacao());
            pstm.setString(8, checkEquipamento.getExecColeta());
            pstm.setString(9, dataInicio);
            pstm.setString(10, dataFim);
            pstm.execute();
            
            String sql_02 = "SELECT id FROM checklist " +
                    "WHERE id_equipamento = " + equipamento.getIdEquipamento() + " " +
                    "AND matricula_usuario = '" + usuario.getMatriculaUsuario() + "' " +
                    "AND data_inicio = '" + dataInicio + "' " +
                    "AND data_fim = '" + dataFim + "'";
            
            pstm = connection.prepareStatement(sql_02);
            rs = pstm.executeQuery();
            
            if (rs != null) {
                rs.last();
                
                return rs.getInt("id");
            }
        } catch (SQLException e) {
                System.out.println("Erro(CheckEquipamentoDAO.salvarChecagem): " + e.getMessage());
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
    
    public boolean salvarProcedimentoChecagem(CheckEquipamento checkEquipamento) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs;
        
        Procedimento procedimento = checkEquipamento.getProcedimento();
        StatusProcedimento statusProcedimentoEnt = checkEquipamento.getStatusProcedimentoEnt();
        StatusProcedimento statusProcedimentoSai = checkEquipamento.getStatusProcedimentoSai();
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql = "";
            
            if (statusProcedimentoSai != null) {
                sql = "INSERT INTO checklist_x_procedimentos (id_check, id_procedimento, " +
                    "id_status_ent, id_status_sai, data_inicio, data_fim) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
                
                pstm = connection.prepareStatement(sql);
                
                pstm.setInt(1, checkEquipamento.getId());
                pstm.setInt(2, procedimento.getIdProcedimento());
                pstm.setInt(3, statusProcedimentoEnt.getIdStatusProcedimento());
                pstm.setInt(4, statusProcedimentoSai.getIdStatusProcedimento());
                pstm.setString(5, dateFormat.format(checkEquipamento.getDataInicio()));
                pstm.setString(6, dateFormat.format(checkEquipamento.getDataFim()));
                
                pstm.execute();
                
            } else {
                sql = "INSERT INTO checklist_x_procedimentos (id_check, id_procedimento, " +
                    "id_status_ent, data_inicio, data_fim) " +
                    "VALUES (?, ?, ?, ?, ?)";
                
                pstm = connection.prepareStatement(sql);
                
                pstm.setInt(1, checkEquipamento.getId());
                pstm.setInt(2, procedimento.getIdProcedimento());
                pstm.setInt(3, statusProcedimentoEnt.getIdStatusProcedimento());
                pstm.setString(4, dateFormat.format(checkEquipamento.getDataInicio()));
                pstm.setString(5, dateFormat.format(checkEquipamento.getDataFim()));
                
                pstm.execute();
                
            }
            
            return true;
        } catch (SQLException e) {
                System.out.println("Erro(CheckEquipamentoDAO.salvarProcedimentosChecagem): " + e.getMessage());
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
    
    public List recuperarPerfis() throws SQLException {
        List list = null;
        
        PreparedStatement pstm = null;
        CheckEquipamentoPerfil checkEquipamentoPerfil = null;
        ResultSet rs;
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql = "SELECT * FROM checklist_pefil " +
                    "WHERE ativo = 'y'";
            
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
        
            if (rs != null) {
                list = new ArrayList();
                
                while(rs.next()) {
                    checkEquipamentoPerfil = new CheckEquipamentoPerfil();
                    checkEquipamentoPerfil.setId(rs.getInt("id"));
                    checkEquipamentoPerfil.setNome(rs.getString("nome"));
                    
                    list.add(checkEquipamentoPerfil);
                }
                return list;
            }
        } catch (SQLException e) {
                System.out.println("Erro(CheckEquipamentoDAO.recuperarPerfis): " + e.getMessage());
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
            String sql = "SELECT * FROM equipamentos " +
                    "WHERE ativo = 'y'";
            
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
        
            if (rs != null) {
                list = new ArrayList();
                
                while(rs.next()) {
                    equipamento = new Equipamento();
                    equipamento.setIdEquipamento(rs.getInt("id"));
                    equipamento.setNomeEquipamento(rs.getString("nome"));
                    equipamento.setDescricaoEquipamento(rs.getString("descricao"));
                    equipamento.setEnderecoLogradouroEquipamento(rs.getString("end_logradouro"));
                    equipamento.setEnderecoCidadeEquipamento(rs.getString("end_cidade"));
                    equipamento.setEnderecoEstadoEquipamento(rs.getString("end_estado"));
                    
                    list.add(equipamento);
                }
                return list;
            }
        } catch (SQLException e) {
                System.out.println("Erro(CheckEquipamentoDAO.recuperarEquipamentos): " + e.getMessage());
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
    
    public List recuperarProcedimentos(int perfil) throws SQLException {
        List list = null;
        
        PreparedStatement pstm = null;
        Procedimento procedimento;
        ResultSet rs;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "SELECT b.* " +
                    "FROM procedimentos_x_perfil a, " +
                    "procedimentos b " +
                    "WHERE a.id_proc = b.id AND a.id_perfil = " + perfil + " " +
                    "ORDER BY a.ordem_procedimento";

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs != null) {
                list = new ArrayList();

                while(rs.next()) {
                    procedimento = new Procedimento();
                    procedimento.setIdProcedimento(rs.getInt("id"));
                    procedimento.setNomeProcedimento(rs.getString("nome_procedimento"));
                    procedimento.setDescricaoProcedimento(rs.getString("descricao_procedimento"));

                    list.add(procedimento);
                }
                return list;
            }
        } catch (SQLException e) {
                System.out.println("Erro(CheckEquipamentoDAO.recuperarProcedimentos): " + e.getMessage());
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
    
    public List recuperarStatusProcedimentos(String tipo) throws SQLException {
        List list = null;
        
        if (tipo != null) {
            PreparedStatement pstm = null;
            StatusProcedimento statusProcedimento;
            ResultSet rs;

            if (conexao == null || connection == null) {
                conexao = new Conexao();
                connection = conexao.abreConexao();
            }

            try {
                String sql = "";
                
                if (tipo.equals("ent") || tipo.equals("sai")) {
                    sql = "SELECT * FROM procedimentos_status " +
                        "WHERE tipo = '" + tipo + "' AND ativo = 'y'";
                } else {
                    throw new SQLException("Tipo de status nao definido.");
                }

                pstm = connection.prepareStatement(sql);
                rs = pstm.executeQuery();

                if (rs != null) {
                    list = new ArrayList();

                    while(rs.next()) {
                        statusProcedimento = new StatusProcedimento();
                        statusProcedimento.setIdStatusProcedimento(rs.getInt("id"));
                        statusProcedimento.setDescricaoStatusProcedimento(rs.getString("descricao"));
                        statusProcedimento.setTipoStatusProcedimento(tipo);

                        list.add(statusProcedimento);
                    }
                    return list;
                }
            } catch (SQLException e) {
                    System.out.println("Erro(CheckEquipamentoDAO.recuperarStatusProcedimentos): " + e.getMessage());
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
        }
        
        return list;
    }
    
    public List recuperarChecagens() throws SQLException {
        List list = null;
        
        PreparedStatement pstm = null;
        ResultSet rs;
        
        CheckEquipamento checkEquipamento;
        Equipamento equipamento;
        Usuario usuario;
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "SELECT a.*, b.nome as nome_equipamento, c.user, c.nome, c.sobrenome " +
                    "FROM checklist a, " +
                    "equipamentos b, " +
                    "usuario c " +
                    "WHERE a.id_equipamento = b.id " +
                    "AND a.matricula_usuario = c.matricula " +
                    "ORDER BY data_inicio DESC"; // LIMIT 30 ";

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs != null) {
                
                list = new ArrayList();

                while(rs.next()) {
                    checkEquipamento = new CheckEquipamento();
                    equipamento = new Equipamento();
                    usuario = new Usuario();
                    
                    checkEquipamento.setId(rs.getInt("id"));
                    equipamento.setIdEquipamento(rs.getInt("id_equipamento"));
                    equipamento.setNomeEquipamento(rs.getString("nome_equipamento"));
                    usuario.setMatriculaUsuario(rs.getString("matricula_usuario"));
                    usuario.setUser(rs.getString("user"));
                    usuario.setNomeUsuario(rs.getString("nome"));
                    usuario.setSobrenomeUsuario(rs.getString("sobrenome"));
                    checkEquipamento.setDataInicio(dateFormat.parse(rs.getString("data_inicio")));
                    checkEquipamento.setDataFim(dateFormat.parse(rs.getString("data_fim")));
                    
                    checkEquipamento.setEquipamento(equipamento);
                    checkEquipamento.setUsuario(usuario);
                    
                    list.add(checkEquipamento);
                }
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro(CheckEquipamentoDAO.recuperarChecagens): " + e.getMessage());
        } catch (ParseException pe) {
            System.out.println("Erro(CheckEquipamentoDAO.recuperarChecagens): " + pe.getMessage());
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
    
    public List recuperarChecagensPag(int pageNumber, int pageLimit, String orderBy, String order) throws SQLException {
        List list = null;
        
        PreparedStatement pstm = null;
        ResultSet rs;
        
        CheckEquipamento checkEquipamento;
        Equipamento equipamento;
        Usuario usuario;
        Paginacao paginacao;
        
        //ordenacao
        String ordenacao = " ORDER BY a.data_inicio DESC";
        
        if (orderBy != null) {
            if (!orderBy.trim().equals("")) {
                if (orderBy.trim().equals("id")) {
                    ordenacao = " ORDER BY a.id " + order;
                } else if (orderBy.trim().equals("equipamento")) {
                    ordenacao = " ORDER BY b.nome " + order;
                } else if (orderBy.trim().equals("tecnico")) {
                    ordenacao = " ORDER BY c.nome " + order;
                } else if (orderBy.trim().equals("data_inicial")) {
                    ordenacao = " ORDER BY a.data_inicio " + order;
                } else if (orderBy.trim().equals("data_final")) {
                    ordenacao = " ORDER BY a.data_fim " + order;
                }
            }
        }
        
        //paginacao
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
            String sql = "SELECT a.*, b.nome as nome_equipamento, c.user, c.nome, c.sobrenome " +
                    "FROM checklist a, " +
                    "equipamentos b, " +
                    "usuario c " +
                    "WHERE a.id_equipamento = b.id " +
                    "AND a.matricula_usuario = c.matricula" + ordenacao;

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

                while(rs.next()) {
                    checkEquipamento = new CheckEquipamento();
                    equipamento = new Equipamento();
                    usuario = new Usuario();
                    paginacao = new Paginacao();
                    
                    paginacao.setPageInit(pageInit);
                    paginacao.setPageLeft(pageLeft);
                    paginacao.setPageLimit(pageLimit);
                    paginacao.setPageNavigator(pageNavigator);
                    paginacao.setPageNumber(pageNumber);
                    paginacao.setResultSize(resultSize);
                    
                    checkEquipamento.setId(rs.getInt("id"));
                    equipamento.setIdEquipamento(rs.getInt("id_equipamento"));
                    equipamento.setNomeEquipamento(rs.getString("nome_equipamento"));
                    usuario.setMatriculaUsuario(rs.getString("matricula_usuario"));
                    usuario.setUser(rs.getString("user"));
                    usuario.setNomeUsuario(rs.getString("nome"));
                    usuario.setSobrenomeUsuario(rs.getString("sobrenome"));
                    checkEquipamento.setDataInicio(dateFormat.parse(rs.getString("data_inicio")));
                    checkEquipamento.setDataFim(dateFormat.parse(rs.getString("data_fim")));
                    
                    checkEquipamento.setEquipamento(equipamento);
                    checkEquipamento.setUsuario(usuario);
                    
                    checkEquipamento.setPaginacao(paginacao);
                    
                    list.add(checkEquipamento);
                }
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro(CheckEquipamentoDAO.recuperarChecagens): " + e.getMessage());
        } catch (ParseException pe) {
            System.out.println("Erro(CheckEquipamentoDAO.recuperarChecagens): " + pe.getMessage());
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
    
    public List recuperarChecagem(int idCheck) throws SQLException {
        CheckEquipamento checkEquipamento = null;
        Usuario usuario;
        Equipamento equipamento;
        Procedimento procedimento;
        StatusProcedimento statusProcedimentoEnt;
        StatusProcedimento statusProcedimentoSai;
        
        List list = null;
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        if (checkEquipamento == null && idCheck != 0) {
            if (conexao == null || connection == null) {
                conexao = new Conexao();
                connection = conexao.abreConexao();
            }
            try {
                /*String sql = "SELECT c.nome as nome_equipamento, d.nome_procedimento, " +
                        "f.descricao as status_ent, g.descricao as status_sai, b.data_inicio, b.data_fim " +
                        "FROM checklist a, checklist_x_procedimentos b, " +
                        "equipamentos c, procedimentos d, procedimentos_x_perfil e, " +
                        "procedimentos_status f, procedimentos_status g " +
                        "WHERE a.id = " + idCheck + " " +
                        "AND a.id_equipamento = c.id " +
                        "AND b.id_check = a.id " +
                        "AND b.id_procedimento = d.id " +
                        "AND b.id_procedimento = e.id_proc " +
                        "AND b.id_status_ent = f.id " +
                        "AND b.id_status_sai = g.id " +
                        "ORDER BY e.ordem_procedimento";*/
                
                String sql = "SELECT chklist.id as id_checagem, " +
                                       "equip.nome as nome_equipamento, " +
                                       "equip.end_logradouro, " +
                                       "tec.nome as nome_tecnico, " +
                                       "tec.sobrenome as sobrenome_tecnico, " +
                                       "proc.nome_procedimento, " +
                                       "procs1.descricao as status_ent, " +
                                       "procs2.descricao as status_sai, " +
                                       "chklist.num_serie, " +
                                       "chklist.num_upr, " +
                                       "chklist.num_camera, " +
                                       "chklist.num_flash, " +
                                       "chklist.exec_coleta, " +
                                       "chklist.obs, " +
                                       "chkpro.data_inicio, " +
                                       "chkpro.data_fim " +
                                "FROM checklist chklist, " +
                                     "checklist_x_procedimentos chkpro, " +
                                     "equipamentos equip, " +
                                     "procedimentos proc, " +
                                     "procedimentos_status procs1, " +
                                     "procedimentos_status procs2, " +
                                     "usuario tec " + 
                                "WHERE chklist.id = " + idCheck + " " +
                                "AND chklist.id_equipamento = equip.id " +
                                "AND chklist.matricula_usuario = tec.matricula " +
                                "AND chkpro.id_check = chklist.id " +
                                "AND chkpro.id_procedimento = proc.id " +
                                "AND chkpro.id_status_ent = procs1.id " +
                                "AND chkpro.id_status_sai = procs2.id " +
                                "ORDER BY chkpro.id";
                
                pstm = connection.prepareStatement(sql);
                rs = pstm.executeQuery();

                if (rs != null) {
                    list = new ArrayList();
                    
                    while (rs.next()) {
                        checkEquipamento = new CheckEquipamento();
                        usuario = new Usuario();
                        equipamento = new Equipamento();
                        equipamento = new Equipamento();
                        procedimento = new Procedimento();
                        statusProcedimentoEnt = new StatusProcedimento();
                        statusProcedimentoSai = new StatusProcedimento();

                        usuario.setNomeUsuario(rs.getString("nome_tecnico"));
                        usuario.setSobrenomeUsuario(rs.getString("sobrenome_tecnico"));
                        equipamento.setNomeEquipamento(rs.getString("nome_equipamento"));
                        equipamento.setEnderecoLogradouroEquipamento(rs.getString("end_logradouro"));
                        procedimento.setNomeProcedimento(rs.getString("nome_procedimento"));
                        statusProcedimentoEnt.setDescricaoStatusProcedimento(rs.getString("status_ent"));
                        statusProcedimentoSai.setDescricaoStatusProcedimento(rs.getString("status_sai"));
                        
                        checkEquipamento.setUsuario(usuario);
                        checkEquipamento.setEquipamento(equipamento);
                        checkEquipamento.setProcedimento(procedimento);
                        checkEquipamento.setStatusProcedimentoEnt(statusProcedimentoEnt);
                        checkEquipamento.setStatusProcedimentoSai(statusProcedimentoSai);
                        checkEquipamento.setId(rs.getInt("id_checagem"));
                        checkEquipamento.setNumSerie(rs.getInt("num_serie"));
                        checkEquipamento.setNumUPR(rs.getInt("num_upr"));
                        checkEquipamento.setNumCamera(rs.getInt("num_camera"));
                        checkEquipamento.setNumFlash(rs.getString("num_flash"));
                        checkEquipamento.setExecColeta(rs.getString("exec_coleta"));
                        checkEquipamento.setObservacao(rs.getString("obs"));
                        checkEquipamento.setDataInicio(dateFormat.parse(rs.getString("data_inicio")));
                        checkEquipamento.setDataFim(dateFormat.parse(rs.getString("data_fim")));
                        
                        list.add(checkEquipamento);
                    }
                    
                    return list;
                }
            } catch (SQLException e) {
                System.out.println("Erro(CheckEquipamentoDAO.recuperarChecagem): " + e.getMessage());
            } catch (ParseException pe) {
                System.out.println("Erro(CheckEquipamentoDAO.recuperarChecagem): " + pe.getMessage());
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
        }
        
        return list;
    }
    
}

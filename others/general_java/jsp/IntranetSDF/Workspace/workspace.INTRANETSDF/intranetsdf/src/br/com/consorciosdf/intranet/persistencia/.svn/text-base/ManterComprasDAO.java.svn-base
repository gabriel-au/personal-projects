package br.com.consorciosdf.intranet.persistencia;

import br.com.consorciosdf.intranet.modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManterComprasDAO {
    
    private Conexao conexao;
    private Connection connection;
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public boolean salvarArquivo(ComprasArquivo comprasArquivo) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs;
        
        Compras compras = comprasArquivo.getCompras();
        ComprasArquivoTipo comprasArquivoTipo = comprasArquivo.getComprasArquivoTipo();
        Usuario usuarioInclusao = comprasArquivo.getUsuarioInclusao();
        
        Date dtInclusao = new Date();
        String dataInclusao = dateFormat.format(dtInclusao);
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql = "INSERT INTO compras_arq (id_tipo, id_compra, " +
                    "descricao, path, usuario_inclusao, data_inclusao) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, comprasArquivoTipo.getId());
            pstm.setInt(2, compras.getId());
            pstm.setString(3, comprasArquivo.getDescricao());
            pstm.setString(4, comprasArquivo.getFileName());
            pstm.setString(5, usuarioInclusao.getMatriculaUsuario());
            pstm.setString(6, dataInclusao);
            
            pstm.execute();
            
            return true;
        } catch (SQLException e) {
                System.out.println("Erro(ManterArchiveDAO.salvarArquivo): " + e.getMessage());
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
    
    public List recuperarComprasPag(int pageNumber, int pageLimit, String orderBy, String order) throws SQLException {
        List list = null;
        
        PreparedStatement pstm = null;
        ResultSet rs;
        
        Compras compras;
        Usuario usuarioInclusao;
        Usuario usuarioAlteracao;
        Paginacao paginacao;
        
        //ordenacao
        String ordenacao = " ORDER BY data_inclusao DESC";
        
        if (orderBy != null) {
            if (!orderBy.trim().equals("")) {
                if (orderBy.trim().equals("id")) {
                    ordenacao = " ORDER BY id " + order;
                } else if (orderBy.trim().equals("descricao")) {
                    ordenacao = " ORDER BY descricao " + order;
                } else if (orderBy.trim().equals("usuario")) {
                    ordenacao = " ORDER BY us.nome " + order;
                } else if (orderBy.trim().equals("data_inclusao")) {
                    ordenacao = " ORDER BY data_inclusao " + order;
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
            String sql = "SELECT * FROM compras" + ordenacao;
            
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
                    compras = new Compras();
                    paginacao = new Paginacao();
                    usuarioInclusao = new Usuario();
                    usuarioAlteracao = new Usuario();
                    
                    paginacao.setPageInit(pageInit);
                    paginacao.setPageLeft(pageLeft);
                    paginacao.setPageLimit(pageLimit);
                    paginacao.setPageNavigator(pageNavigator);
                    paginacao.setPageNumber(pageNumber);
                    paginacao.setResultSize(resultSize);
                    
                    Date dataInclusao = null;
                    String strDataInclusao = rs.getString("data_inclusao");
                    
                    if (!strDataInclusao.equals("0000-00-00 00:00:00")) {
                        dataInclusao = dateFormat.parse(strDataInclusao);
                    }
                    
                    usuarioInclusao.setMatriculaUsuario(rs.getString("usuario_inclusao"));
                    
                    compras.setId(rs.getInt("id"));
                    compras.setDescricao(rs.getString("descricao"));
                    compras.setEmpresasParticipantes(rs.getString("empresas_participantes"));
                    compras.setUsuarioInclusao(usuarioInclusao);
                    compras.setDataInclusao(dataInclusao);
                    
                    compras.setPaginacao(paginacao);
                    
                    list.add(compras);
                }
                
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro(ManterComprasDAO.recuperarComprasPag): " + e.getMessage());
        } catch (ParseException pe) {
            System.out.println("Erro(ManterComprasDAO.recuperarComprasPag): " + pe.getMessage());
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
    
    public Compras recuperarCompras(int id) throws SQLException {
        PreparedStatement pstm = null;
        PreparedStatement pstmAux = null;
        ResultSet rs;
        ResultSet rsAux;
        
        Compras compras = null;
        ComprasArquivo comprasArquivo = null;
        ComprasArquivoTipo comprasArquivoTipo = null;
        Usuario usuarioInclusao = null;
        Usuario usuarioAlteracao = null;
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql = "SELECT com.*, us.nome, us.sobrenome " +
                    "FROM compras com " +
                    "LEFT JOIN usuario us " +
                    "ON com.usuario_inclusao = us.matricula " +
                    "WHERE com.id = " + id;
            
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            if (rs != null) {
                rs.last();
                
                compras = new Compras();
                usuarioInclusao = new Usuario();
                usuarioAlteracao = new Usuario();
                
                Date dataInclusao = null;
                String strDataInclusao = rs.getString("data_inclusao");

                if (!strDataInclusao.equals("0000-00-00 00:00:00")) {
                    dataInclusao = dateFormat.parse(strDataInclusao);
                }

                usuarioInclusao.setMatriculaUsuario(rs.getString("com.usuario_inclusao"));
                usuarioInclusao.setNomeUsuario(rs.getString("us.nome"));
                usuarioInclusao.setSobrenomeUsuario(rs.getString("us.sobrenome"));

                compras.setId(rs.getInt("id"));
                compras.setEmpresasParticipantes(rs.getString("empresas_participantes"));
                compras.setDescricao(rs.getString("descricao"));
                compras.setUsuarioInclusao(usuarioInclusao);
                compras.setDataInclusao(dataInclusao);
                
                String sqlAux = "SELECT arq.*, arqt.descricao " +
                        "FROM compras_arq arq " +
                        "JOIN compras_arq_tipo arqt " +
                        "ON arq.id_tipo = arqt.id " +
                        "WHERE arq.id_compra = " + id;
                
                pstmAux = connection.prepareStatement(sqlAux);
                rsAux = pstmAux.executeQuery();
                
                if (rsAux != null) {
                    List list = new ArrayList();
                    
                    while(rsAux.next()) {
                        comprasArquivo = new ComprasArquivo();
                        comprasArquivoTipo = new ComprasArquivoTipo();
                        
                        comprasArquivoTipo.setDescricao(rsAux.getString("arqt.descricao"));
                        
                        comprasArquivo.setId(rsAux.getInt("arq.id"));
                        comprasArquivo.setDescricao(rsAux.getString("arq.descricao"));
                        comprasArquivo.setFileName(rsAux.getString("arq.path"));
                        
                        comprasArquivo.setComprasArquivoTipo(comprasArquivoTipo);
                        
                        list.add(comprasArquivo);
                    }
                    
                    compras.setComprasArquivo(list);
                }
                
                return compras;
            }
            
        } catch (SQLException e) {
                System.out.println("Erro(ManterComprasDAO.recuperarCompras): " + e.getMessage());
        } catch (ParseException pe) {
            System.out.println("Erro(ManterComprasDAO.recuperarCompras): " + pe.getMessage());
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
        
        return compras;
    }
    
    public boolean salvarCompra(Compras compras) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs;
        
        Date dtNow = new Date();
        String dataInclusao = dateFormat.format(dtNow);
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql = "INSERT INTO compras (descricao, empresas_participantes, " +
                    "usuario_inclusao, data_inclusao) " +
                    "VALUES (?, ?, ?, ?)";
                
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, compras.getDescricao());
            pstm.setString(2, compras.getEmpresasParticipantes());
            pstm.setString(3, compras.getUsuarioInclusao().getMatriculaUsuario());
            pstm.setString(4, dataInclusao);
            
            pstm.execute();
            
            return true;
        } catch (SQLException e) {
                System.out.println("Erro(ManterComprasDAO.salvarCompra): " + e.getMessage());
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
    
    public List recuperarTipos() throws SQLException {
        List list = null;
        
        PreparedStatement pstm = null;
        ResultSet rs;
        
        ComprasArquivoTipo comprasArquivoTipo;
        Usuario usuarioInclusao;
        Usuario usuarioAlteracao;
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql = "SELECT * FROM compras_arq_tipo " +
                    "WHERE ativo = 'y' " +
                    "ORDER BY descricao";
            
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
        
            if (rs != null) {
                list = new ArrayList();
                
                while(rs.next()) {
                    comprasArquivoTipo = new ComprasArquivoTipo();
                    usuarioInclusao = new Usuario();
                    usuarioAlteracao = new Usuario();
                    
                    Date dataInclusao = null;
                    String strDataInclusao = rs.getString("data_inclusao");
                    
                    if (!strDataInclusao.equals("0000-00-00 00:00:00")) {
                        dataInclusao = dateFormat.parse(strDataInclusao);
                    }
                    
                    usuarioInclusao.setMatriculaUsuario(rs.getString("usuario_inclusao"));
                    
                    comprasArquivoTipo.setId(rs.getInt("id"));
                    comprasArquivoTipo.setDescricao(rs.getString("descricao"));
                    comprasArquivoTipo.setUsuarioInclusao(usuarioInclusao);
                    comprasArquivoTipo.setDataInclusao(dataInclusao);
                    
                    list.add(comprasArquivoTipo);
                }
                
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro(ManterArchiveDAO.recuperarTipos): " + e.getMessage());
        } catch (ParseException pe) {
            System.out.println("Erro(ManterArchiveDAO.recuperarTipos): " + pe.getMessage());
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
    
    public String recuperarPadraoArquivo(int idFileType) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs;
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql = "SELECT padrao_arquivo FROM compras_arq_tipo " +
                    "WHERE id = " + idFileType;
            
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            if (rs != null) {
                rs.last();
                
                return rs.getString("padrao_arquivo");
            }
            
        } catch (SQLException e) {
                System.out.println("Erro(ManterArchiveDAO.recuperarPadraoArquivo): " + e.getMessage());
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
        
        return "";
    }
}

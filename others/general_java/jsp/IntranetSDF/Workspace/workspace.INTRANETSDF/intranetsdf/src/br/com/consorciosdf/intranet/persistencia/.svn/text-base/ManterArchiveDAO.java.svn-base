package br.com.consorciosdf.intranet.persistencia;

import br.com.consorciosdf.intranet.modelo.Arquivo;
import br.com.consorciosdf.intranet.modelo.ArquivoTipo;
import br.com.consorciosdf.intranet.modelo.Paginacao;
import br.com.consorciosdf.intranet.modelo.Usuario;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ManterArchiveDAO {
    
    private Conexao conexao;
    private Connection connection;
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public boolean salvarArquivo(Arquivo arquivo) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs;
        
        ArquivoTipo arquivoTipo = arquivo.getArquivoTipo();
        Usuario usuarioInclusao = arquivo.getUsuarioInclusao();
        
        Date dtInclusao = new Date();
        String dataInclusao = dateFormat.format(dtInclusao);
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql = "INSERT INTO arquivos (id_tipo, " +
                    "descricao, path, usuario_inclusao, data_inclusao) " +
                    "VALUES (?, ?, ?, ?, ?)";
            
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, arquivoTipo.getId());
            pstm.setString(2, arquivo.getDescricao());
            pstm.setString(3, arquivo.getFileName());
            pstm.setString(4, usuarioInclusao.getMatriculaUsuario());
            pstm.setString(5, dataInclusao);
            
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
    
    public String recuperarPadraoArquivo(int idFileType) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs;
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql = "SELECT padrao_arquivo FROM arquivos_tipo " +
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
    
    public Arquivo recuperarArquivo(int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs;
        
        Arquivo arquivo = null;
        ArquivoTipo arquivoTipo = null;
        Usuario usuarioInclusao = null;
        Usuario usuarioAlteracao = null;
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql = "SELECT arq.*, arqt.descricao as tipo, us.nome, us.sobrenome " +
                    "FROM arquivos arq " +
                    "LEFT JOIN usuario us " +
                    "ON arq.usuario_inclusao = us.matricula " +
                    "LEFT JOIN arquivos_tipo arqt " +
                    "ON arq.id_tipo = arqt.id " +
                    "WHERE arq.id = " + id;
            
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            if (rs != null) {
                rs.last();
                
                arquivo = new Arquivo();
                arquivoTipo = new ArquivoTipo();
                usuarioInclusao = new Usuario();
                usuarioAlteracao = new Usuario();
                
                Date dataInclusao = null;
                String strDataInclusao = rs.getString("data_inclusao");

                if (!strDataInclusao.equals("0000-00-00 00:00:00")) {
                    dataInclusao = dateFormat.parse(strDataInclusao);
                }

                usuarioInclusao.setMatriculaUsuario(rs.getString("arq.usuario_inclusao"));
                usuarioInclusao.setNomeUsuario(rs.getString("us.nome"));
                usuarioInclusao.setSobrenomeUsuario(rs.getString("us.sobrenome"));
                arquivoTipo.setId(rs.getInt("id_tipo"));
                arquivoTipo.setDescricao(rs.getString("tipo"));

                arquivo.setId(rs.getInt("id"));
                arquivo.setArquivoTipo(arquivoTipo);
                arquivo.setFileName(rs.getString("path"));
                arquivo.setDescricao(rs.getString("descricao"));
                arquivo.setUsuarioInclusao(usuarioInclusao);
                arquivo.setDataInclusao(dataInclusao);
                
                return arquivo;
            }
            
        } catch (SQLException e) {
                System.out.println("Erro(ManterArchiveDAO.recuperarArquivo): " + e.getMessage());
        } catch (ParseException pe) {
            System.out.println("Erro(ManterArchiveDAO.recuperarArquivo): " + pe.getMessage());
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
        
        return arquivo;
    }
    
    public List recuperarArquivosPag(int pageNumber, int pageLimit, String orderBy, String order) throws SQLException {
        List list = null;
        
        PreparedStatement pstm = null;
        ResultSet rs;
        
        Arquivo arquivo;
        ArquivoTipo arquivoTipo;
        Usuario usuarioInclusao;
        Usuario usuarioAlteracao;
        Paginacao paginacao;
        
        //ordenacao
        String ordenacao = " ORDER BY arq.data_inclusao DESC";
        
        if (orderBy != null) {
            if (!orderBy.trim().equals("")) {
                if (orderBy.trim().equals("id")) {
                    ordenacao = " ORDER BY arq.id " + order;
                } else if (orderBy.trim().equals("tipo")) {
                    ordenacao = " ORDER BY arqt.descricao " + order;
                } else if (orderBy.trim().equals("descricao")) {
                    ordenacao = " ORDER BY arq.descricao " + order;
                } else if (orderBy.trim().equals("usuario")) {
                    ordenacao = " ORDER BY us.nome " + order;
                } else if (orderBy.trim().equals("data_inclusao")) {
                    ordenacao = " ORDER BY arq.data_inclusao " + order;
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
            String sql = "SELECT arq.*, arqt.descricao as tipo, us.nome, us.sobrenome " +
                    "FROM arquivos arq " +
                    "LEFT JOIN usuario us " +
                    "ON arq.usuario_inclusao = us.matricula " +
                    "LEFT JOIN arquivos_tipo arqt " +
                    "ON arq.id_tipo = arqt.id" + ordenacao;
            
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
                    arquivo = new Arquivo();
                    arquivoTipo = new ArquivoTipo();
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
                    arquivoTipo.setId(rs.getInt("id_tipo"));
                    arquivoTipo.setDescricao(rs.getString("tipo"));
                    
                    arquivo.setId(rs.getInt("id"));
                    arquivo.setArquivoTipo(arquivoTipo);
                    arquivo.setFileName(rs.getString("path"));
                    arquivo.setDescricao(rs.getString("descricao"));
                    arquivo.setUsuarioInclusao(usuarioInclusao);
                    arquivo.setDataInclusao(dataInclusao);
                    
                    arquivo.setPaginacao(paginacao);
                    
                    list.add(arquivo);
                }
                
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro(ManterArchiveDAO.recuperarArquivosPag): " + e.getMessage());
        } catch (ParseException pe) {
            System.out.println("Erro(ManterArchiveDAO.recuperarArquivosPag): " + pe.getMessage());
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
    
    public List recuperarTipos() throws SQLException {
        List list = null;
        
        PreparedStatement pstm = null;
        ResultSet rs;
        
        ArquivoTipo arquivoTipo;
        Usuario usuarioInclusao;
        Usuario usuarioAlteracao;
        
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql = "SELECT * FROM arquivos_tipo " +
                    "ORDER BY descricao";
            
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
        
            if (rs != null) {
                list = new ArrayList();
                
                while(rs.next()) {
                    arquivoTipo = new ArquivoTipo();
                    usuarioInclusao = new Usuario();
                    usuarioAlteracao = new Usuario();
                    
                    Date dataInclusao = null;
                    String strDataInclusao = rs.getString("data_inclusao");
                    
                    if (!strDataInclusao.equals("0000-00-00 00:00:00")) {
                        dataInclusao = dateFormat.parse(strDataInclusao);
                    }
                    
                    usuarioInclusao.setMatriculaUsuario(rs.getString("usuario_inclusao"));
                    
                    arquivoTipo.setId(rs.getInt("id"));
                    arquivoTipo.setDescricao(rs.getString("descricao"));
                    arquivoTipo.setUsuarioInclusao(usuarioInclusao);
                    arquivoTipo.setDataInclusao(dataInclusao);
                    
                    list.add(arquivoTipo);
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
    
    public boolean apagarArquivo(int id) throws SQLException {
        PreparedStatement pstm = null;
                
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            String sql = "DELETE FROM arquivos " +
                    "WHERE id = " + id;
            
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            
            return true;
        } catch (SQLException e) {
                System.out.println("Erro(ManterArchiveDAO.apagarArquivo): " + e.getMessage());
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
    
}

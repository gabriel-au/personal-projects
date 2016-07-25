package br.com.consorciosdf.intranet.controle;

import br.com.consorciosdf.intranet.modelo.Compras;
import br.com.consorciosdf.intranet.modelo.ComprasArquivo;
import br.com.consorciosdf.intranet.persistencia.ManterComprasDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.commons.fileupload.FileItem;

public class ManterComprasControl {
    
    private ManterComprasDAO dao;    
    
    private String caminho = "/sdf/arquivos/intranetsdf/compras";
    private Date dataNow = new Date();
    
    public boolean uploadArchive(FileItem item, ComprasArquivo comprasArquivo) {
        if (dao == null) {
            dao = new ManterComprasDAO();
        }

        try {
            // Cria o diretorio caso ele nao exista
            File diretorio = new File(caminho);
            if (!diretorio.exists()){
                diretorio.mkdir();
            }

            // Mandar o arquivo para o diret�rio informado
            String nome = item.getName();
            String arq[] = nome.split("\\\\");

            for (int i = 0; i < arq.length; i++) {
                nome = arq[i];
            }
            
            String fileName = dao.recuperarPadraoArquivo(comprasArquivo.getComprasArquivoTipo().getId()) + 
                    "_" + dataNow.getTime() + ".pdf";
            comprasArquivo.setFileName(fileName);
            
            try {
                File file = new File(diretorio, fileName);
                FileOutputStream output = new FileOutputStream(file);
                InputStream is = item.getInputStream();
                byte[] buffer = new byte[10240]; //10MB
                int nLidos;
                while ((nLidos = is.read(buffer)) >= 0) {
                    output.write(buffer, 0, nLidos);
                }

                output.flush();
                output.close();
            } catch (IOException e) {
                System.out.println("*Erro(ManterArchiveControl.uploadArchive): " + e.getMessage());
            }
            
            return dao.salvarArquivo(comprasArquivo);
        } catch (SQLException e) {
            System.out.println("*Erro(ManterArchiveControl.uploadArchive): " + e.getMessage());
        }
        
        return false;
    }
    
    public List recuperarComprasPag(int pageNumber, int pageLimit, String orderBy, String order) {
        List list = null;
        
        if (dao == null) {
            dao = new ManterComprasDAO();
        }
        
        try {
            list = dao.recuperarComprasPag(pageNumber, pageLimit, orderBy, order);
        } catch (SQLException e) {
            System.out.println("*Erro(ManterComprasControl.recuperarComprasPag): " + e.getMessage());
        }
        
        return list;
    }
    
    public List recuperarTipos() {
        List list = null;
        
        if (dao == null) {
            dao = new ManterComprasDAO();
        }
        
        try {
            list = dao.recuperarTipos();
        } catch (SQLException e) {
            System.out.println("*Erro(ManterComprasControl.recuperarComprasPag): " + e.getMessage());
        }
        
        return list;
    }
    
    public Compras recuperarCompras(int id) {
        Compras compras = null;
        
        if (dao == null) {
            dao = new ManterComprasDAO();
        }
        
        try {
            compras = dao.recuperarCompras(id);
        } catch (SQLException e) {
            System.out.println("*Erro(ManterComprasControl.recuperarCompras): " + e.getMessage());
        }
        
        return compras;
    }
    
    public boolean salvarCompra(Compras compras) {
        if (dao == null) {
            dao = new ManterComprasDAO();
        }
        
        try {
            return dao.salvarCompra(compras);
        } catch (SQLException e) {
            System.out.println("*Erro(ManterComprasControl.salvarCompra): " + e.getMessage());
        }
        
        return false;
    }
    
    /*public List recuperarArquivosPag(int pageNumber, int pageLimit, String orderBy, String order) {
        List list = null;
        
        if (dao == null) {
            dao = new ManterArchiveDAO();
        }
        
        try {
            list = dao.recuperarArquivosPag(pageNumber, pageLimit, orderBy, order);
        } catch (SQLException e) {
            System.out.println("*Erro(ManterArchiveControl.recuperarArquivosPag): " + e.getMessage());
        }
        
        return list;
    }*/
    
}

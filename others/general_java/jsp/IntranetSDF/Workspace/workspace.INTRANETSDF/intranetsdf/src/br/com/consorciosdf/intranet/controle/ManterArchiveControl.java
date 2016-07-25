package br.com.consorciosdf.intranet.controle;

import br.com.consorciosdf.intranet.modelo.Arquivo;
import br.com.consorciosdf.intranet.persistencia.ManterArchiveDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.commons.fileupload.FileItem;

public class ManterArchiveControl {
    
    private ManterArchiveDAO dao;
    
    //Endereco do diretorio padrao
    private String caminho = "/sdf/arquivos/intranetsdf";
    private Date dataNow = new Date();
    
    public boolean uploadArchive(FileItem item, Arquivo arquivo) {
        if (dao == null) {
            dao = new ManterArchiveDAO();
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
            
            String fileName = dao.recuperarPadraoArquivo(arquivo.getArquivoTipo().getId()) + 
                    "_" + dataNow.getTime() + ".pdf";
            arquivo.setFileName(fileName);
            
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
            
            return dao.salvarArquivo(arquivo);
        } catch (SQLException e) {
            System.out.println("*Erro(ManterArchiveControl.uploadArchive): " + e.getMessage());
        }
        
        return false;
    }
    
    public Arquivo recuperarArquivo(int id) {
        Arquivo arquivo = null;
        
        if (dao == null) {
            dao = new ManterArchiveDAO();
        }
        
        try {
            arquivo = dao.recuperarArquivo(id);
        } catch (SQLException e) {
            System.out.println("*Erro(ManterArchiveControl.recuperarArquivo): " + e.getMessage());
        }
        
        return arquivo;
    }
    
    public List recuperarArquivosPag(int pageNumber, int pageLimit, String orderBy, String order) {
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
    }
    
    public List recuperarTipos() {
        List list = null;
        
        if (dao == null) {
            dao = new ManterArchiveDAO();
        }
        
        try {
            list = dao.recuperarTipos();
        } catch (SQLException e) {
            System.out.println("*Erro(ManterArchiveControl.recuperarTipos): " + e.getMessage());
        }
        
        return list;
    }
    
    public boolean apagarArquivo(int id) {
        if (dao == null) {
            dao = new ManterArchiveDAO();
        }
        
        try {
            return dao.apagarArquivo(id);
        } catch (SQLException e) {
            System.out.println("*Erro(ManterArchiveControl.apagarArquivo): " + e.getMessage());
        }
        
        return false;
    }
    
}
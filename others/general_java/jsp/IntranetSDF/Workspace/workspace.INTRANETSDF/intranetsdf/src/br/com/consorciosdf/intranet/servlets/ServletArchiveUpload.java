package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterArchiveControl;
import br.com.consorciosdf.intranet.modelo.Arquivo;
import br.com.consorciosdf.intranet.modelo.ArquivoTipo;
import br.com.consorciosdf.intranet.modelo.Usuario;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class ServletArchiveUpload extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
                       throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        HttpSession session = request.getSession();
        
        ManterArchiveControl manterArchiveControl = null;
        Arquivo arquivo = null;
        ArquivoTipo arquivoTipo = null;
        Usuario usuarioInclusao = null;
        
        int idFileType = 0;
        String descricao = "";
        
        if (isMultiPart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(10*1024*1024); //10MB
            //String formulario = "";
            
            try {
                List items = upload.parseRequest(request);
                Iterator iter = items.iterator();
                FileItem file = null;
                
                manterArchiveControl = new ManterArchiveControl();
                arquivo = new Arquivo();
                arquivoTipo = new ArquivoTipo();
                usuarioInclusao = new Usuario();
                
                idFileType = Integer.parseInt((String) session.getAttribute("arquivoTipo"));
                descricao = (String) session.getAttribute("arquivoDescricao");
                
                usuarioInclusao.setMatriculaUsuario((String) session.getAttribute("userMatricula"));
                
                arquivoTipo.setId(idFileType);
                arquivo.setDescricao(descricao);
                arquivo.setArquivoTipo(arquivoTipo);
                arquivo.setUsuarioInclusao(usuarioInclusao);
                
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    /*if (item.getFieldName().equals("tipoForm")) {
                        formulario = item.getString();
                    }*/
                    
                    if (!item.isFormField()) {
                        if (item.getName().length() > 0) {
                            if (item.getContentType().equals("application/pdf")) {
                                file = item;
                            } else {
                                response.sendRedirect(request.getContextPath() + "/archiveUpload.jsp?sucess=3");
                            }
                        }
                    }
                }
                
                if(manterArchiveControl.uploadArchive(file, arquivo)) {
                    //Double fileSize = Double.parseDouble(String.valueOf(item.getSize()));
                    //Double fileSizeResult = 0.01;

                    /*if (fileSize > 1024) {
                        fileSizeResult = fileSize / 1024 / 1024; //MB
                    }

                    String fileName = item.getName();
                    String arq[] = fileName.split("\\\\");

                    for (int i = 0; i < arq.length; i++) {
                        fileName = arq[i];
                    }*/

                    response.sendRedirect(request.getContextPath() + "/archiveUpload.jsp?sucess=1"); //&fileName=" + fileName + "&fileSize=" + fileSizeResult);
                } else {
                    response.sendRedirect(request.getContextPath() + "/archiveUpload.jsp?sucess=2");
                }
            } catch (FileUploadException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
}

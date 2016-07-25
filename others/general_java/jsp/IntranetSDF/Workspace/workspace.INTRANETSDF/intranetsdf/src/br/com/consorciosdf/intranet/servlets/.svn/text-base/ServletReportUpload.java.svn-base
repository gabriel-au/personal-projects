package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterReportControl;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

public class ServletReportUpload extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
                       throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        
        if (isMultiPart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(5*1024*1024); //5MB
            String formulario = "";
            
            try {
                List items = upload.parseRequest(request);
                Iterator iter = items.iterator();
                
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.getFieldName().equals("tipoForm")) {
                        formulario = item.getString();
                    }
                    
                    if (!item.isFormField()) {
                        if (item.getName().length() > 0) {
                            ManterReportControl manterReportControl = new ManterReportControl();
                            
                            //this.saveArchive(item)
                            if(manterReportControl.uploadReport(item, getServletContext().getRealPath("/tmp"))) {
                                Double fileSize = Double.parseDouble(String.valueOf(item.getSize()));
                                Double fileSizeResult = fileSize / 1024 / 1024; //MB
                                
                                String fileName = item.getName();
                                String arq[] = fileName.split("\\\\");

                                for (int i = 0; i < arq.length; i++) {
                                    fileName = arq[i];
                                }
                                
                                response.sendRedirect(request.getContextPath() + 
                                        "/reportImport.jsp?sucess=1&fileName=" + 
                                        fileName + "&fileSize=" +
                                        fileSizeResult);
                            } else {
                                response.sendRedirect(request.getContextPath() + "/reportImport.jsp?sucess=2");
                            }
                        }
                    }
                }
            } catch (FileUploadException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
}

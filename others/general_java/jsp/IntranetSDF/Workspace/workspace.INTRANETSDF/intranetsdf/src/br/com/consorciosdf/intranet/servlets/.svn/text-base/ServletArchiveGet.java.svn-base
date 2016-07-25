package br.com.consorciosdf.intranet.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

public class ServletArchiveGet extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
                       throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("file") != null) {
            if (!request.getParameter("file").trim().equals("")) {
                String caminho = "";
                
                if (!request.getParameter("tipo").trim().equals("")) {
                    if (request.getParameter("tipo").equals("2")) {
                        caminho = "/sdf/arquivos/intranetsdf/compras";
                    } else if (request.getParameter("tipo").equals("1")) {
                        caminho = "/sdf/arquivos/intranetsdf";
                    }
                }
                
                File file = new File(caminho, request.getParameter("file"));
                FileInputStream input = new FileInputStream(file);
                
                OutputStream output = response.getOutputStream();
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition","attachment; filename=\"" + file.getName() +" \"");
                
                byte[] buffer = new byte[10240]; //10MB
                int nLidos;
                while ((nLidos = input.read(buffer)) >= 0) {
                    output.write(buffer, 0, nLidos);
                }
            }
        }
    }
    
}

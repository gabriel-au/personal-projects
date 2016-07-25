<%@ page import="br.com.consorciosdf.intranet.controle.ManterUserControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.Usuario" %>
<%@ page import="br.com.consorciosdf.intranet.seguranca.Criptography;" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request" />

<%

            if ((request.getParameter("user")) != null && (request.getParameter("pass")) != null) {
                if (!request.getParameter("user").equals("") || !request.getParameter("pass").equals("")) {
                    ManterUserControl manterUserControl = new ManterUserControl();

                    Usuario userLogin = new Usuario();
                    userLogin.setUser(request.getParameter("user"));
                    userLogin.setPassword(Criptography.criptografar(request.getParameter("pass")));

                    usuario = manterUserControl.loginWeb(userLogin);
                    request.getSession(true).setAttribute("usuarioFilter",usuario);
                    if (usuario != null) {
                        session.setAttribute("user", request.getParameter("user"));
                        session.setAttribute("userCod", String.valueOf(usuario.getCodUsuario()));
                        session.setAttribute("userMatricula", String.valueOf(usuario.getMatriculaUsuario()));
                        session.setAttribute("userName", usuario.getNomeUsuario());
                        session.setAttribute("userLastname", usuario.getSobrenomeUsuario());
                        session.setAttribute("userEmail", usuario.getEmailUsuario());
                        session.setAttribute("userPerfil", String.valueOf(usuario.getPerfilUsuario()));
                        session.setAttribute("userModify", usuario.getModificarPrefUsuario());

                        if (!request.getParameter("urlDest").equals("")) {
                            response.sendRedirect(request.getParameter("urlDest"));
                        } else {
                            response.sendRedirect(request.getContextPath() + "/main.jsp");
                        }
                    } else {
                        if (!request.getParameter("urlDest").equals("")) {
                            response.sendRedirect(request.getParameter("urlDest"));
                        } else {
                            response.sendRedirect(request.getContextPath() + "/index.jsp?erro=2");
                        }
                    }
                } else {
                    if (!request.getParameter("urlDest").equals("")) {
                        response.sendRedirect(request.getParameter("urlDest"));
                    } else {
                        response.sendRedirect(request.getContextPath() + "/index.jsp?erro=1");
                    }
                }
            } else {
                if (!request.getParameter("urlDest").equals("")) {
                    response.sendRedirect(request.getParameter("urlDest"));
                } else {
                    response.sendRedirect(request.getContextPath() + "/index.jsp?erro=5");
                }
            }

%>
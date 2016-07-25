package br.com.consorciosdf.intranet.controle;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consorciosdf.intranet.modelo.Usuario;

/**
 * Servlet Filter implementation class UserFilter
 */
public class UserFilter implements Filter {


    public UserFilter() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		// Busca o usuario na sessão
		Usuario usuarioAtual = (Usuario)servletRequest.getSession().getAttribute("usuarioFilter");
		// Recupera a url atual
		String uri = servletRequest.getRequestURI();	
		// Verifica se o usuario é nulo
		if(usuarioAtual == null){
			// Algumas restriçoes caso o usuario seja nulo
			if(uri.equals("/intranetsdf/") || uri.equals("/intranetsdf/userVerify.jsp")
					|| uri.startsWith("/intranetsdf/index.jsp") || uri.startsWith("/intranetsdf/mobile/index.jsp")
					|| uri.startsWith("/intranetsdf/imagens/") || uri.startsWith("/intranetsdf/estilos/") 
					|| uri.startsWith("/intranetsdf/contraLogin")){
				chain.doFilter(request, response);	
			} else{// Se não tiver dentre as restriçoes.
				if(uri.startsWith("/intranetsdf/os")){
					((HttpServletResponse)response).sendRedirect(servletRequest.getContextPath()+"/index.jsp?erro=6");
				} else if(uri.startsWith("/intranetsdf/mobile")){
					((HttpServletResponse)response).sendRedirect(servletRequest.getContextPath()+"/mobile/index.jsp?erro=4");
				}else{
					((HttpServletResponse)response).sendRedirect(servletRequest.getContextPath()+"/index.jsp?erro=3");
				}	
			}			
		} else {// Se o usuario não for nulo.
			chain.doFilter(request, response);
		}		
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}

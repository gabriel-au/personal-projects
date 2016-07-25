package br.jus.stj.site.iphone.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.site.iphone.PadraoPaginas;
import br.jus.stj.site.iphone.util.UtilString;

/**
 * Servlet implementation class Pagina
 */
public class Pagina extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UtilString.setFullPath(request.getContextPath());
		response.setContentType("text/html; charset=ISO-8859-1;");
		//response.setCharacterEncoding("ISO-8859-1");

		PrintWriter out = response.getWriter();
		// ler qual a página
		String parametro = request.getParameter("p");
		// ler qual o metodo
		String metodo = request.getParameter("m");
		
		Map<String, String> listaParametros= new HashMap<String, String>();
		Enumeration itens = request.getParameterNames();
		while (itens.hasMoreElements()) {
			String nome = (String) itens.nextElement();
			String valor = UtilString.decodeUtf8(request.getParameter(nome));
			listaParametros.put(nome, valor);
		}
		
		HttpSession session = request.getSession();
		try {
			if (parametro == null || parametro.equals("")) {
				out.write(getPagina());
			} else {
				out.write(getPagina(parametro, metodo,listaParametros, session));
			}
		} catch (SystemException e) {
			throw new ServletException(e.getMessage());
		}
	}

	/**
	 * Método que cria a página inicial
	 * @return
	 * @throws SystemException
	 */
	private String getPagina() throws SystemException {
		PaginaIndex pg = new PaginaIndex();
		return pg.gerarPagina();
	}

	/**
	 * Este método cria uma instância dinâmica da classe que cmonta a página
	 * @param parametro
	 * @param metodo
	 * @return
	 * @throws SystemException
	 */
	private String getPagina(String parametro, String metodo,Map<String, String> listaParametros,HttpSession session)
			throws SystemException {
		if (metodo == null || metodo.equals("")) {
			metodo = "gerarPagina";
		}
		String classeNome = "br.jus.stj.site.iphone.servlet.Pagina" + parametro;
		try {
			Object[] contrutoraParam = new Object[0];
			Object objeto = Class.forName(classeNome).getConstructors()[0]
					.newInstance(contrutoraParam);

			PadraoPaginas pagina = (PadraoPaginas) objeto;

			return pagina.gerarPagina(metodo,listaParametros, session);

		} catch (Exception e) {
			throw new SystemException("Erro: " + e.getMessage());
		}
	}

}

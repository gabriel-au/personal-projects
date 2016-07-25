package br.com.xtrategia.fiscon.web.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import br.com.xtrategia.fiscon.web.WebAction;

/**
 * Servlet implementation class Transacao
 */
public class Transacao extends WebAction {

	private static final long serialVersionUID = 1L;

	private String resultado;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public String executar() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		/*
		 * Enumeration<String> paramNames = request.getParameterNames(); while
		 * (paramNames.hasMoreElements()) { String param = (String)
		 * paramNames.nextElement(); String valor = request.getParameter(param);
		 * out.println(param + "=" + valor + "<br>"); }
		 */
		String transacao = request.getParameter("transacao");
		if (transacao == null || transacao.equals("")) {
			resultado = "ERRO=Falha Consulta Inválida";
		} else if (transacao.equals("login")) {
			//é o único que não precisa de login
			Login cv = new Login();
			resultado = cv.consultar(request.getParameter("usuario"), request
					.getParameter("senha"));
			request.getSession(true); 
			request.getSession().setAttribute("logado", resultado);
			response.addCookie(new Cookie("Set-cookie", request.getSession().getId()));
			
			response.setHeader("Set-cookie", request.getSession().getId());
			
			System.out.println(request.getSession().getId());
			
			
		} else if (transacao.equals("consultarVeiculo")) {
			ConsultarVeiculo cv = new ConsultarVeiculo();
			resultado = cv.consultar(request.getParameter("placa"));
		} else if (transacao.equals("consultarCNH")) {
			ConsultarCNH cv = new ConsultarCNH();
			resultado = cv.consultar(request.getParameter("cnh"), request
					.getParameter("dataNascimento"));
		} else if (transacao.equals("consultarCNHporCPF")) {
			ConsultarCNH cv = new ConsultarCNH();
			resultado = cv.consultarPorCPF(request.getParameter("cpf"));
		} else if (transacao.equals("consultarMunicipio")) {
			ConsultarMunicipio cv = new ConsultarMunicipio();
			resultado = cv.consultar(request.getParameter("nome"));
		} else if (transacao.equals("consultarBairro")) {
			ConsultarBairro cv = new ConsultarBairro();
			resultado = cv.consultar(request.getParameter("idMunicipio"));
		} else if (transacao.equals("consultarInfracaoTipo")) {
			ConsultarInfracaoTipo cv = new ConsultarInfracaoTipo();
			resultado = cv.consultar();
		} else if (transacao.equals("recuperarEndereco")) {
			RecuperarEndereco re = new RecuperarEndereco();
			resultado = re.consultar(request.getParameter("latitude"), request.getParameter("longitude"));
		} else if (transacao.equals("inserirInfracao")) {
			IncluirAutoInfracao iai = new IncluirAutoInfracao();
			resultado = iai.incluir(request);
		} else if (transacao.equals("cancelarInfracao")) {
			CancelarAutoInfracao cai = new CancelarAutoInfracao();
			resultado = cai.cancelar(request);
		} else {
			resultado = "ERRO=Falha Consulta Inválida";
		}

		return SUCCESS;
	}
	
	@Override
	public boolean isLoggedInUserRequired() {
		return false;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}

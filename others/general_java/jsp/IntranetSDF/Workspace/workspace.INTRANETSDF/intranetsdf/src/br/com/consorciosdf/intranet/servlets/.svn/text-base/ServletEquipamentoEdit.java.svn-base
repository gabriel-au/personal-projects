package br.com.consorciosdf.intranet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consorciosdf.intranet.controle.ManterEquipamentoControl;
import br.com.consorciosdf.intranet.modelo.Equipamento;

/**
 * Servlet implementation class ServletEquipamentoEdit
 */
public class ServletEquipamentoEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		if (!request.getParameter("descricao").trim().equals("")
				|| !request.getParameter("nome").trim().equals("")
				|| !request.getParameter("tipo").trim().equals("")
				|| !request.getParameter("endereco").trim().equals("")
				|| !request.getParameter("cidade").trim().equals("")
				|| !request.getParameter("estado").trim().equals("")
				|| !request.getParameter("equipamento").trim().equals("")) {

			String nome = request.getParameter("nome").toUpperCase();
			String descricao = request.getParameter("descricao").trim();
			String tipo = request.getParameter("tipo").toUpperCase();
			String endereco = request.getParameter("endereco").toUpperCase().trim();
			String cidade = request.getParameter("cidade").toUpperCase();
			String estado = request.getParameter("estado");
			
			String equipamentoPaiAux = request.getParameter("equipamento");
			int equipamentoPai = 0;
			if (equipamentoPaiAux != null && !equipamentoPaiAux.equals("")) {
				equipamentoPai = Integer.parseInt(equipamentoPaiAux);
			}

			Equipamento equipamento = new Equipamento();
			ManterEquipamentoControl control = new ManterEquipamentoControl();

			equipamento.setNomeEquipamento(nome);
			equipamento.setTipo(tipo);
			equipamento.setDescricaoEquipamento(descricao);
			equipamento.setEnderecoCidadeEquipamento(cidade);
			equipamento.setEnderecoEstadoEquipamento(estado);
			equipamento.setEnderecoLogradouroEquipamento(endereco);
			equipamento.setIdPai(equipamentoPai);
			equipamento.setIdEquipamento(Integer.parseInt(id));

			if (control.alterarEquipamento(equipamento)) {
				response.sendRedirect(request.getContextPath()
						+ "/equipamentoEdit.jsp?sucess=1&id="+id);
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/equipamentoEdit.jsp?sucess=3&id="+id);
				}

		} else {

			response.sendRedirect(request.getContextPath()
					+ "/equipamentoEdit.jsp?sucess=2&id="+id);

		}
		
	}

}

package br.com.consorciosdf.intranet.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consorciosdf.intranet.controle.ManterEquipamentoControl;
import br.com.consorciosdf.intranet.modelo.Equipamento;

/**
 * Servlet implementation class ServletEquipamentosSave
 */
public class ServletEquipamentosSave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (!request.getParameter("descricao").trim().equals("")
				|| !request.getParameter("nome").trim().equals("")
				|| !request.getParameter("tipo").trim().equals("")
				|| !request.getParameter("endereco").trim().equals("")
				|| !request.getParameter("endereco").trim().equals("")
				|| !request.getParameter("cidade").trim().equals("")
				|| !request.getParameter("estado").trim().equals("")
				|| !request.getParameter("equipamento").trim().equals("")
				|| !request.getParameter("numEquip").trim().equals("")) {
			
			
			int numEquip = 0;
			if(request.getParameter("numEquip") != null && !request.getParameter("numEquip").trim().equals("")){
				numEquip = Integer.parseInt(request.getParameter("numEquip"));
			}
			
			String nome = request.getParameter("nome").toUpperCase();
			String descricao = request.getParameter("descricao");
			String tipo = request.getParameter("tipo").toUpperCase();
			String endereco = request.getParameter("endereco").toUpperCase();
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
			equipamento.setNumSerie(numEquip);

			if (control.salvarEquipamento(equipamento)) {
				response.sendRedirect(request.getContextPath()
						+ "/equipamentoAdd.jsp?sucess=1");
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/equipamentoAdd.jsp?sucess=3");
				}

		} else {

			response.sendRedirect(request.getContextPath()
					+ "/equipamentoAdd.jsp?sucess=2");

		}

	}
}

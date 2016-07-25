package br.com.consorciosdf.intranet.controle;

import java.sql.SQLException;
import java.util.List;

import br.com.consorciosdf.intranet.modelo.UsuarioRule;
import br.com.consorciosdf.intranet.persistencia.ManterUserRuleDAO;

public class ManterUserRulesControl {

	ManterUserRuleDAO userRuleDAO = null;

	public List<UsuarioRule> listUserRules(int idUser) {
		List<UsuarioRule> lista = null;

		userRuleDAO = new ManterUserRuleDAO();
		try {
			lista = userRuleDAO.listUserRules(idUser);
		} catch (SQLException e) {
			System.out.println("Erro(UserRuleDao.listUserRuleDao)"
					+ e.getMessage());
		}

		return lista;
	}

}

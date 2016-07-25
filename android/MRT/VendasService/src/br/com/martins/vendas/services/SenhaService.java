package br.com.martins.vendas.services;

import org.json.JSONException;
import org.json.JSONObject;

import com.brq.mobile.framework.util.StringUtil;

public class SenhaService {

	public static JSONObject gerarSenha(int codRep, String preSenha) throws JSONException {

		int var1;
		int var2;
		int aux1;
		int aux2;

		String strVar1;
		String strVar2;
		String strVar3;

		final String letras = "WLRCSTAMZ";
		final String replaceNove = "9";
		final String replaceSete = "7";
		final String decimal = "%d";

		StringBuilder result = new StringBuilder("");

		if (!preSenha.equals("")) {

			var1 = codRep;

			if (codRep < 100)
				var1 += 87600;

			else if (codRep < 1000)
				var1 += 87000;

			else if (codRep < 10000)
				var1 += 80000;

			strVar1 = StringUtil.preencheZerosEsquerda(var1 + "", 5);
			strVar1 = strVar1.replace(replaceNove, replaceSete);

			var1 = Integer.parseInt(strVar1);

			strVar1 = StringUtil.preencheZerosEsquerda(var1 + "", 5);
			strVar2 = StringUtil.preencheZerosEsquerda(preSenha, 5);

			int count = 1;

			for (int i = 0; i < 5; i++) {
				aux1 = Integer.parseInt(strVar1.substring(i, count));
				aux2 = Integer.parseInt(strVar2.substring(i, count));
				count++;
				if (aux1 == 0)
					var2 = aux2;
				else
					var2 = aux1 * aux2;

				if (var2 < 10) {

					int temp = 0;

					if ((var2 - 1) <= -1)

						strVar3 = letras.substring(temp, (var2 + 1));
					else

						strVar3 = letras.substring(temp = (var2 - 1), var2);

				} else {
					while (var2 > 9) {

						strVar3 = String.format(decimal, var2);
						aux1 = Integer.parseInt((strVar3.substring(0, 1)));
						aux2 = Integer.parseInt((strVar3.substring(1, 2)));
						var2 = aux1 + aux2;
					}

					strVar3 = String.format(decimal, var2);
				}
				result.append(strVar3);
			}
			return new JSONObject().put("senha", result.toString());
		}
		return new JSONObject().put("mensagem", "Pré-senha é obrigatória.");
	}
}
package br.com.prime.fiscon.mobile.negocio;

import java.util.Hashtable;

import br.com.codequest.mobile.util.ServerConnect;
import br.com.codequest.mobile.util.StringUtils;
import br.com.prime.fiscon.mobile.pojo.VeiculoPojo;

public class ConsultarVeiculoNegocio extends ServerConnect{

	private VeiculoPojo veiculo;
	
	public boolean consultar(Hashtable atributos){
		addProperty("transacao", "consultarVeiculo");
		addProperty("placa", (String) atributos.get("placa"));
		
		try {
			String resultado =connect();
			if (resultado.startsWith("Falha")) {
				return false;
			}
			
			Hashtable mapa = StringUtils.stringToHashtable(resultado);
			
			if (mapa.get("Erro")!=null) {
				return false;
			}
			veiculo = new VeiculoPojo();
			veiculo.setCor((String)mapa.get("cor"));
			veiculo.setCategoria((String)mapa.get("categoria"));
			veiculo.setMunicipio((String)mapa.get("municipio"));
			veiculo.setUf((String)mapa.get("uf"));
			veiculo.setTipo((String)mapa.get("tipo"));
			veiculo.setEspecie((String)mapa.get("especie"));
			veiculo.setMarcaModelo((String)mapa.get("marcaModelo"));
			veiculo.setVeiculoPlaca((String)mapa.get("veiculoPlaca"));
			veiculo.setAnoModelo((String)mapa.get("anoModelo"));
			veiculo.setAnoFabricacao((String)mapa.get("anoFabricacao"));
			veiculo.setRestricao((String)mapa.get("restricao"));
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	public VeiculoPojo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(VeiculoPojo veiculo) {
		this.veiculo = veiculo;
	}
	
}

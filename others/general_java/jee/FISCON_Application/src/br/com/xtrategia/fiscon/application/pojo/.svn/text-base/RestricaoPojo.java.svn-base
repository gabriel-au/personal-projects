package br.com.xtrategia.fiscon.application.pojo;

import br.com.xtrategia.fiscon.application.Pojo;

public class RestricaoPojo extends Pojo {

    private String codigo; //7
    private String placa; //2
    private VeiculoPojo veiculo;
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof RestricaoPojo) {
    		RestricaoPojo pojo = (RestricaoPojo)obj;
    		return pojo.getCodigo()==codigo && pojo.getPlaca().equals(placa);
		}else{
			return false;
		}
    }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public VeiculoPojo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(VeiculoPojo veiculo) {
		this.veiculo = veiculo;
	}

    
	
	
}

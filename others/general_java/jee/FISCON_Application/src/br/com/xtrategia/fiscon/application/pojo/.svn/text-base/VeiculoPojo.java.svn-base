package br.com.xtrategia.fiscon.application.pojo;

import br.com.xtrategia.fiscon.application.Pojo;

public class VeiculoPojo extends Pojo {

    private String placa; //7
    private int anoFabricacao; //4
    private int anoModelo; //4
    
    private MarcaModeloPojo marcaModelo= new MarcaModeloPojo();
    private CorPojo cor= new CorPojo();
    private TipoVeiculoPojo tipo= new TipoVeiculoPojo();
    private CategoriaVeiculoPojo categoria= new CategoriaVeiculoPojo();
    private EspeciePojo especie= new EspeciePojo();
    private MunicipioPojo municipio= new MunicipioPojo();
    private RestricaoPojo restricao = new RestricaoPojo();
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof VeiculoPojo) {
    		VeiculoPojo pojo = (VeiculoPojo)obj;
    		return pojo.getPlaca()==placa ;
		}else{
			return false;
		}
    }

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public int getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}

	public MarcaModeloPojo getMarcaModelo() {
		return marcaModelo;
	}

	public void setMarcaModelo(MarcaModeloPojo marcaModelo) {
		this.marcaModelo = marcaModelo;
	}

	public CorPojo getCor() {
		return cor;
	}

	public void setCor(CorPojo cor) {
		this.cor = cor;
	}

	public TipoVeiculoPojo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVeiculoPojo tipo) {
		this.tipo = tipo;
	}

	public CategoriaVeiculoPojo getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaVeiculoPojo categoria) {
		this.categoria = categoria;
	}

	public EspeciePojo getEspecie() {
		return especie;
	}

	public void setEspecie(EspeciePojo especie) {
		this.especie = especie;
	}

	public MunicipioPojo getMunicipio() {
		return municipio;
	}

	public void setMunicipio(MunicipioPojo municipio) {
		this.municipio = municipio;
	}

	public RestricaoPojo getRestricao() {
		return restricao;
	}

	public void setRestricao(RestricaoPojo restricao) {
		this.restricao = restricao;
	}



}

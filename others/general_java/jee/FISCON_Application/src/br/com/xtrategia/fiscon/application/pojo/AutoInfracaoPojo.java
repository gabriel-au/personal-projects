package br.com.xtrategia.fiscon.application.pojo;

import br.com.xtrategia.fiscon.application.Pojo;

public class AutoInfracaoPojo extends Pojo {

	public static final String versao="1.0.0";
	
	private String dataInfracao;
	private String dataEnvio;
	private String orgaoAutuador;
	private String codigoOrgaoAutuador;
	private String numeroAutoInfracao;
	private String enderecoLogradouro;
	private String enderecoComplemento;
	private String enderecoBairro;
	private String enderecoUF;
	
	private String equipInstrumento;
	private String certificado;
	private String dataVerificacao;
	private String medicaoRealizada;
	private String limiteRegulamentado;
	private String valorConsiderado;
	private String codigoRenainf;
	private String dataExpedicao;
	private String valor;
	private String latitude;
	private String longitude;
	
	//relacionamentos
	private String veiculoPlaca;
	private String usuarioMatricula;
	private String usuarioNome;
	private String municipioCodigo;
	private String municipioNome;
	private String infracaoCodigo;
	private String infracaoNome;
	private String infracaoAmparoLegal;
	private String infracaoInfrator;
	private String infracaoGravidade;
	private String infracaoOrgao;;
	private String cnh;

	//dados de controle
	private String dataHomologacao;
	private String usuarioHomologacaoMatricula;
	private String chaveExtracao;
	
	private String foto;

	@Override
	public String toString() {
		return super.toString();
	}

	public String getVeiculoPlaca() {
		return veiculoPlaca;
	}

	public void setVeiculoPlaca(String veiculoPlaca) {
		this.veiculoPlaca = veiculoPlaca;
	}

	public String getUsuarioMatricula() {
		return usuarioMatricula;
	}

	public void setUsuarioMatricula(String usuarioMatricula) {
		this.usuarioMatricula = usuarioMatricula;
	}

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	public String getMunicipioCodigo() {
		return municipioCodigo;
	}

	public void setMunicipioCodigo(String municipioCodigo) {
		this.municipioCodigo = municipioCodigo;
	}
	
	public String getMunicipioNome() {
		return municipioNome;
	}
	
	public void setMunicipioNome(String municipioNome) {
		this.municipioNome = municipioNome;
	}

	public String getInfracaoCodigo() {
		return infracaoCodigo;
	}

	public void setInfracaoCodigo(String infracaoCodigo) {
		this.infracaoCodigo = infracaoCodigo;
	}

	public String getInfracaoNome() {
		return infracaoNome;
	}

	public void setInfracaoNome(String infracaoNome) {
		this.infracaoNome = infracaoNome;
	}

	public String getInfracaoAmparoLegal() {
		return infracaoAmparoLegal;
	}

	public void setInfracaoAmparoLegal(String infracaoAmparoLegal) {
		this.infracaoAmparoLegal = infracaoAmparoLegal;
	}

	public String getInfracaoInfrator() {
		return infracaoInfrator;
	}

	public void setInfracaoInfrator(String infracaoInfrator) {
		this.infracaoInfrator = infracaoInfrator;
	}

	public String getInfracaoGravidade() {
		return infracaoGravidade;
	}

	public void setInfracaoGravidade(String infracaoGravidade) {
		this.infracaoGravidade = infracaoGravidade;
	}

	public String getInfracaoOrgao() {
		return infracaoOrgao;
	}

	public void setInfracaoOrgao(String infracaoOrgao) {
		this.infracaoOrgao = infracaoOrgao;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public String getDataInfracao() {
		return dataInfracao;
	}

	public void setDataInfracao(String dataInfracao) {
		this.dataInfracao = dataInfracao;
	}

	public String getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getOrgaoAutuador() {
		return orgaoAutuador;
	}

	public void setOrgaoAutuador(String orgaoAutuador) {
		this.orgaoAutuador = orgaoAutuador;
	}

	public String getCodigoOrgaoAutuador() {
		return codigoOrgaoAutuador;
	}

	public void setCodigoOrgaoAutuador(String codigoOrgaoAutuador) {
		this.codigoOrgaoAutuador = codigoOrgaoAutuador;
	}

	public String getNumeroAutoInfracao() {
		return numeroAutoInfracao;
	}

	public void setNumeroAutoInfracao(String numeroAutoInfracao) {
		this.numeroAutoInfracao = numeroAutoInfracao;
	}

	public String getEnderecoLogradouro() {
		return enderecoLogradouro;
	}

	public void setEnderecoLogradouro(String enderecoLogradouro) {
		this.enderecoLogradouro = enderecoLogradouro;
	}

	public String getEnderecoComplemento() {
		return enderecoComplemento;
	}

	public void setEnderecoComplemento(String enderecoComplemento) {
		this.enderecoComplemento = enderecoComplemento;
	}

	public String getEnderecoBairro() {
		return enderecoBairro;
	}

	public void setEnderecoBairro(String enderecoBairro) {
		this.enderecoBairro = enderecoBairro;
	}

	public String getEnderecoUF() {
		return enderecoUF;
	}

	public void setEnderecoUF(String enderecoUF) {
		this.enderecoUF = enderecoUF;
	}

	public String getEquipInstrumento() {
		return equipInstrumento;
	}

	public void setEquipInstrumento(String equipInstrumento) {
		this.equipInstrumento = equipInstrumento;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getDataVerificacao() {
		return dataVerificacao;
	}

	public void setDataVerificacao(String dataVerificacao) {
		this.dataVerificacao = dataVerificacao;
	}

	public String getMedicaoRealizada() {
		return medicaoRealizada;
	}

	public void setMedicaoRealizada(String medicaoRealizada) {
		this.medicaoRealizada = medicaoRealizada;
	}

	public String getLimiteRegulamentado() {
		return limiteRegulamentado;
	}

	public void setLimiteRegulamentado(String limiteRegulamentado) {
		this.limiteRegulamentado = limiteRegulamentado;
	}

	public String getValorConsiderado() {
		return valorConsiderado;
	}

	public void setValorConsiderado(String valorConsiderado) {
		this.valorConsiderado = valorConsiderado;
	}

	public String getCodigoRenainf() {
		return codigoRenainf;
	}

	public void setCodigoRenainf(String codigoRenainf) {
		this.codigoRenainf = codigoRenainf;
	}

	public String getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(String dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDataHomologacao() {
		return dataHomologacao;
	}

	public void setDataHomologacao(String dataHomologacao) {
		this.dataHomologacao = dataHomologacao;
	}

	public String getUsuarioHomologacaoMatricula() {
		return usuarioHomologacaoMatricula;
	}

	public void setUsuarioHomologacaoMatricula(String usuarioHomologacaoMatricula) {
		this.usuarioHomologacaoMatricula = usuarioHomologacaoMatricula;
	}

	public String getChaveExtracao() {
		return chaveExtracao;
	}

	public void setChaveExtracao(String chaveExtracao) {
		this.chaveExtracao = chaveExtracao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
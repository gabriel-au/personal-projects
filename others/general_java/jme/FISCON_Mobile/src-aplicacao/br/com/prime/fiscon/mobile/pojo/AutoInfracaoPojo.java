package br.com.prime.fiscon.mobile.pojo;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import br.com.codequest.mobile.util.BancoDados;
import br.com.codequest.mobile.util.MD5;
import br.com.codequest.mobile.util.StringUtils;
import br.com.prime.fiscon.mobile.nucleo.Usuario;

public class AutoInfracaoPojo extends TransactionObject {
	// private static final Logger log =
	// LoggerFactory.getLogger(AutoInfracaoFoto1.class);

	public static final String OBSERVACAO = "OBSERVACAO";
	public static final String PRIMEIRAFOTO = "PRIMEIRAFOTO";
	public static final String SEGUNDAFOTO = "SEGUNDAFOTO";
	public static final String NUMEROAUTOINFRACAO = "NUMEROAUTOINFRACAO";
	public static final String DATAINFRACAO = "DATAINFRACAO";
	public static final String FOTO = "FOTO";
	public static final String FLAG_ENDERECO = "FLAG_ENDERECO";
	public static final String FLAG_VEICULO = "FLAG_VEICULO";
	public static final String FLAG_CONDUTOR = "FLAG_CONDUTOR";
	public static final String STATUS = "STATUS";
	public static final String ENVIADO = "0";
	public static final String IMPRESSO = "1";

	private static AutoInfracaoPojo pojo;
	private String observacao;
	private byte[] primeiraFoto;
	private byte[] segundaFoto;
	private FotoPojo primeiraFotoPojo;
	private FotoPojo segundaFotoPojo;
	private String numeroAutoInfracao;
	private VeiculoPojo veiculoPojo;
	private UsuarioPojo usuarioPojo;
	private InfracaoTipoPojo infracaoTipoPojo;
	private Date dataInfracao;
	private boolean foto = false;
	private GlobalPosition globalPosition;
	private CNHPojo cnh;
	private EnderecoPojo endereco;
	private boolean flag_endereco = false;
	private boolean flag_veiculo = false;
	private boolean flag_condutor = false;
	private boolean flag_serie = false;
	private boolean flag_consultaV = false;
	private String status;

	private Vector veiculos;
	private Integer indice;

	/*
	 * private void configureMicroLog() { BluetoothSerialAppender
	 * bluetoothSerialAppender = newBluetoothSerialAppender(
	 * "btspp://001F81000250:3;authenticate=false;encrypt=false;master=false");
	 * PatternFormatter parFormatter = new PatternFormatter();
	 * parFormatter.setPattern("%c [%P] %m %T");
	 * bluetoothSerialAppender.setFormatter(parFormatter);
	 * log.addAppender(bluetoothSerialAppender); }
	 */

	public String getStatus() {
		return status;
	}

	public boolean isFlag_consultaV() {
		return flag_consultaV;
	}

	public void setFlag_consultaV(boolean flagConsultaV) {
		flag_consultaV = flagConsultaV;
	}

	public Integer getIndice() {
		if (this.indice == null)
			this.setIndice(new Integer(0));
		return indice;
	}
	
	public Integer incIndice() {
		this.setIndice(this.getIndice().intValue() + 1);
		if (this.getVeiculos() != null && this.getIndice().intValue() > this.getVeiculos().size()-1)
			return new Integer(-1);
		return this.getIndice();
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	public void setIndice(int indice) {
		this.indice = new Integer(indice);
	}

	public Vector getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(Vector veiculos) {
		this.veiculos = veiculos;
	}

	public boolean isFlag_serie() {
		return flag_serie;
	}

	public void setFlag_serie(boolean flagSerie) {
		flag_serie = flagSerie;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private AutoInfracaoPojo() {
		// configureMicroLog();
	}

	public static AutoInfracaoPojo getInstance() {
		if (pojo == null) {
			pojo = new AutoInfracaoPojo();
		}
		return pojo;
	}

	public String getNumeroAutoInfracao() {
		return numeroAutoInfracao;
	}

	public void setNumeroAutoInfracao(String numeroAutoInfracao) {
		this.numeroAutoInfracao = numeroAutoInfracao;
	}

	public VeiculoPojo getVeiculoPojo() {
		return veiculoPojo;
	}

	public void setVeiculoPojo(VeiculoPojo veiculoPojo) {
		System.out.println("CHEGUEI");
		this.veiculoPojo = veiculoPojo;
		System.out.println("SETEI");
	}

	public UsuarioPojo getUsuarioPojo() {
		return usuarioPojo;
	}

	public void setUsuarioPojo(UsuarioPojo usuarioPojo) {
		this.usuarioPojo = usuarioPojo;
	}

	public InfracaoTipoPojo getInfracaoTipoPojo() {
		return infracaoTipoPojo;
	}

	public void setInfracaoTipoPojo(InfracaoTipoPojo infracaoTipoPojo) {
		this.infracaoTipoPojo = infracaoTipoPojo;
	}

	public Date getDataInfracao() {
		return dataInfracao;
	}

	public void setDataInfracao(Date dataInfracao) {
		this.dataInfracao = dataInfracao;
	}

	public boolean getFoto() {
		return foto;
	}

	public void setFoto(boolean foto) {
		this.foto = foto;
	}

	public byte[] getPrimeiraFoto() {
		return primeiraFoto;
	}

	public void setPrimeiraFoto(byte[] primeiraFoto) {
		this.primeiraFoto = primeiraFoto;
	}

	public byte[] getSegundaFoto() {
		return segundaFoto;
	}

	public void setSegundaFoto(byte[] segundaFoto) {
		this.segundaFoto = segundaFoto;
	}

	public GlobalPosition getGlobalPosition() {
		return globalPosition;
	}

	public void setGlobalPosition(GlobalPosition globalPosition) {
		this.globalPosition = globalPosition;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public CNHPojo getCnh() {
		return cnh;
	}

	public void setCnh(CNHPojo cnh) {
		this.cnh = cnh;
	}

	public EnderecoPojo getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoPojo endereco) {
		this.endereco = endereco;
	}

	public boolean isFlag_endereco() {
		return flag_endereco;
	}

	public void setFlag_endereco(boolean flagEndereco) {
		flag_endereco = flagEndereco;
	}

	public boolean isFlag_veiculo() {
		return flag_veiculo;
	}

	public void setFlag_veiculo(boolean flagVeiculo) {
		flag_veiculo = flagVeiculo;
	}

	public boolean isFlag_condutor() {
		return flag_condutor;
	}

	public void setFlag_condutor(boolean flagCondutor) {
		flag_condutor = flagCondutor;
	}

	public void setPrimeiraFotoPojo(FotoPojo primeiraFotoPojo) {
		this.primeiraFotoPojo = primeiraFotoPojo;
	}

	public FotoPojo getPrimeiraFotoPojo() {
		return primeiraFotoPojo;
	}

	public void setSegundaFotoPojo(FotoPojo segundaFotoPojo) {
		this.segundaFotoPojo = segundaFotoPojo;
	}

	public FotoPojo getSegundaFotoPojo() {
		return segundaFotoPojo;
	}

	public static void limparCampos() {
		pojo = new AutoInfracaoPojo();
		AutoInfracaoPojo.setId("");
		pojo.setFlag_serie(false);
	}

	public Hashtable serializarHashtable() throws Exception {
		Hashtable atributos = new Hashtable();

		try {
			atributos.put("identificador", AutoInfracaoPojo.getId());
			// log.debug("identificador");
			if (getPrimeiraFotoPojo() != null)
				atributos.put("foto1", new String(MD5
						.toBase64(getPrimeiraFotoPojo().getDado())));
			// log.debug("foto1");
			if (getSegundaFotoPojo() != null)
				atributos.put("foto2", new String(MD5
						.toBase64(getSegundaFotoPojo().getDado())));
			// log.debug("foto2");
			if (getInfracaoTipoPojo() != null) {
				atributos.put("codigoInfracao", getInfracaoTipoPojo()
						.getCodigo());
				// log.debug("codigoInfracao");
				atributos.put("nomeinfracao", getInfracaoTipoPojo().getNome());
				// log.debug("nomeinfracao");
			}
			if (getCnh() != null) {
				atributos.put("registrocnh", getCnh().getRegistro());
				// log.debug("registrocnh");
				atributos.put("nomecnh", getCnh().getNome());
				// log.debug("nomecnh");
			}
			/*
			 * atributos.put("categoriacnh", getCnh().getCategoria());
			 * atributos.put("nomecnh", getCnh().getNome());
			 * atributos.put("datavalidadecnh", getCnh().getDataValidade());
			 */
			if (getVeiculoPojo() != null)
				atributos.put("placa", getVeiculoPojo().getVeiculoPlaca());
			// log.debug("placa");
			if (getObservacao() != null)
				atributos.put("observacao", getObservacao());
			// log.debug("observacao");
			if (getEndereco().getComplemento() != null)
				atributos.put("complemento", getEndereco().getComplemento());
			// log.debug("complemento");
			if (getEndereco() != null) {
				atributos.put("bairro", getEndereco().getBairro());
				// log.debug("bairro");
				atributos.put("logradouro", getEndereco().getEndereco());
				// log.debug("logradouro");
			}
			if (getDataInfracao() != null)
				atributos.put("datainfracao", Long.toString(getDataInfracao()
						.getTime()));
			// log.debug("datainfracao");
			if (getGlobalPosition() != null) {
				atributos.put("latitude", Double.toString(getGlobalPosition()
						.getLatitude()));
				// log.debug("latitude");
				atributos.put("longitude", Double.toString(getGlobalPosition()
						.getLongitude()));
				// log.debug("longitude");
			}
			if (Usuario.getInstance().getMatricula() != null)
				atributos.put("matriculausuario", Usuario.getInstance()
						.getMatricula());
			// log.debug("matriculausuario");
			if (System.getProperty("com.nokia.IMEI") != null) {
				atributos.put("equipInstrumento", System
						.getProperty("com.nokia.IMEI"));
				// log.debug("equipInstrumento");
			} else if (System.getProperty("phone.imei") != null) {
				atributos.put("equipInstrumento", System
						.getProperty("phone.imei"));
				// log.debug("equipInstrumento");
			}

		} catch (Exception e) {
			// log.trace("Falha na criação da lista de atributos ", e);
			throw new Exception("Falha na criação da lista de atributos "
					+ atributos.toString());
		}
		return atributos;
	}

	public Hashtable serializarCancelamentoHashtable() throws Exception {
		Hashtable atributos = new Hashtable();

		try {
			atributos.put(AutoInfracaoPojo.NUMEROAUTOINFRACAO, AutoInfracaoPojo
					.getInstance().getNumeroAutoInfracao());

		} catch (Exception e) {
			// log.trace("Falha na criação da lista de atributos ", e);
			throw new Exception("Falha na criação da lista de atributos "
					+ atributos.toString());
		}
		return atributos;
	}

	/*
	 * public Hashtable serializar() throws Exception {
	 * 
	 * Hashtable atributos = new Hashtable();
	 * 
	 * try { atributos.put("identificador", getId()); if (getPrimeiraFoto() !=
	 * null) atributos.put("foto1", new String(MD5
	 * .toBase64(getPrimeiraFoto()))); if (getSegundaFoto() != null)
	 * atributos.put("foto2", new String(MD5 .toBase64(getSegundaFoto()))); if
	 * (getInfracaoTipoPojo() != null) {
	 * if(getInfracaoTipoPojo().getCodigo()!=null)
	 * atributos.put("codigoInfracao", getInfracaoTipoPojo() .getCodigo());
	 * if(getInfracaoTipoPojo().getNome()!=null) atributos.put("nomeinfracao",
	 * getInfracaoTipoPojo().getNome()); } if (getCnh() != null) {
	 * if(getCnh().getRegistro()!=null) atributos.put("registrocnh",
	 * getCnh().getRegistro()); if(getCnh().getNome()!=null)
	 * atributos.put("nomecnh", getCnh().getNome()); }
	 * 
	 * if (getVeiculoPojo() != null) atributos.put("placa",
	 * getVeiculoPojo().getVeiculoPlaca()); if (getObservacao() != null)
	 * atributos.put("observacao", getObservacao()); if
	 * (getEndereco().getComplemento() != null) atributos.put("complemento",
	 * getEndereco().getComplemento()); if (getEndereco() != null) {
	 * if(getEndereco().getBairro()!=null) atributos.put("bairro",
	 * getEndereco().getBairro()); if(getEndereco().getEndereco()!=null)
	 * atributos.put("logradouro", getEndereco().getEndereco()); } if
	 * (getDataInfracao() != null) atributos.put("datainfracao",
	 * Long.toString(getDataInfracao() .getTime())); if (getGlobalPosition() !=
	 * null) { if(getGlobalPosition().getLatitude()!=0)
	 * atributos.put("latitude", Double.toString(getGlobalPosition()
	 * .getLatitude())); if(getGlobalPosition().getLongitude()!=0)
	 * atributos.put("longitude", Double.toString(getGlobalPosition()
	 * .getLongitude())); } if (Usuario.getInstance().getMatricula() != null)
	 * atributos.put("matriculausuario", Usuario.getInstance() .getMatricula());
	 * if (System.getProperty("com.nokia.IMEI") != null) {
	 * atributos.put("equipInstrumento", System .getProperty("com.nokia.IMEI"));
	 * } else if (System.getProperty("phone.imei") != null) {
	 * atributos.put("equipInstrumento", System .getProperty("phone.imei")); }
	 * 
	 * } catch (Exception e) { throw new
	 * Exception("Falha na criação da lista de atributos"); } return atributos;
	 * }
	 */
	public Hashtable getHashtable() {
		Hashtable registro = new Hashtable();
		if (getId() != null)
			registro.put(AutoInfracaoPojo.ID, getId());
		if (getDataInfracao() != null)
			registro.put(AutoInfracaoPojo.DATAINFRACAO, String
					.valueOf(getDataInfracao().getTime()));

		registro.put(AutoInfracaoPojo.FOTO, String.valueOf(getFoto()));
		registro.put(AutoInfracaoPojo.FLAG_CONDUTOR, String
				.valueOf(isFlag_condutor()));
		registro.put(AutoInfracaoPojo.FLAG_ENDERECO, String
				.valueOf(isFlag_endereco()));
		registro.put(AutoInfracaoPojo.FLAG_VEICULO, String
				.valueOf(isFlag_veiculo()));
		if (getStatus() != null)
			registro.put(AutoInfracaoPojo.STATUS, getStatus());
		if (getObservacao() != null)
			registro.put(AutoInfracaoPojo.OBSERVACAO, getObservacao());
		if (getNumeroAutoInfracao() != null)
			registro.put(AutoInfracaoPojo.NUMEROAUTOINFRACAO,
					getNumeroAutoInfracao());

		return registro;
	}

	public void carregar(Hashtable autoInfracaoPojo) {
		if (autoInfracaoPojo.get(AutoInfracaoPojo.ID) != null)
			setId((String) autoInfracaoPojo.get(AutoInfracaoPojo.ID));
		if (autoInfracaoPojo.get(AutoInfracaoPojo.DATAINFRACAO) != null)
			setDataInfracao(new Date(Long.parseLong((String) autoInfracaoPojo
					.get(AutoInfracaoPojo.DATAINFRACAO))));
		if (autoInfracaoPojo.get(AutoInfracaoPojo.NUMEROAUTOINFRACAO) != null)
			setNumeroAutoInfracao((String) autoInfracaoPojo
					.get(AutoInfracaoPojo.NUMEROAUTOINFRACAO));
		if (autoInfracaoPojo.get(AutoInfracaoPojo.OBSERVACAO) != null)
			setObservacao((String) autoInfracaoPojo
					.get(AutoInfracaoPojo.OBSERVACAO));
		if (autoInfracaoPojo.get(AutoInfracaoPojo.FOTO) != null)
			setFoto(StringUtils.toBoolean((String) autoInfracaoPojo
					.get(AutoInfracaoPojo.FOTO)));
		if (autoInfracaoPojo.get(AutoInfracaoPojo.FLAG_CONDUTOR) != null)
			setFlag_condutor(StringUtils.toBoolean((String) autoInfracaoPojo
					.get(AutoInfracaoPojo.FLAG_CONDUTOR)));
		if (autoInfracaoPojo.get(AutoInfracaoPojo.FLAG_ENDERECO) != null)
			setFlag_endereco(StringUtils.toBoolean((String) autoInfracaoPojo
					.get(AutoInfracaoPojo.FLAG_ENDERECO)));
		if (autoInfracaoPojo.get(AutoInfracaoPojo.FLAG_VEICULO) != null)
			setFlag_veiculo(StringUtils.toBoolean((String) autoInfracaoPojo
					.get(AutoInfracaoPojo.FLAG_VEICULO)));
		if (autoInfracaoPojo.get(AutoInfracaoPojo.STATUS) != null)
			setStatus((String) autoInfracaoPojo.get(AutoInfracaoPojo.STATUS));
	}

	public String getBD() {
		return BancoDados.AUTOINFRACAO;
	}

	public static void setPojo(AutoInfracaoPojo pojo) {
		AutoInfracaoPojo.pojo = pojo;
	}

	public String toString() {

		return getId() + " : " + getVeiculoPojo().getVeiculoPlaca();
	}
}

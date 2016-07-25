package br.com.codequest.mobile.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.prime.fiscon.mobile.pojo.FotoPojo;
import br.com.prime.fiscon.mobile.pojo.TransactionObject;
/**
 * 
 * @author Gustavo Luis da Costa
 *
 */


public class BancoDados {
	private RecordStore rs;
	public static final String AUTOINFRACAO = "AUTOINFRACAO";
	public static final String VEICULO = "VEICULO";
	public static final String CNH = "CNH";
	public static final String USUARIO = "USUARIO";
	public static final String PASSO = "PASSO";
	public static final String FOTO = "FOTO";
	public static final String GLOBALPOSITION = "GLOBALPOSITION";
	public static final String ENDERECO = "ENDERECO";
	public static final String INFRACAOTIPO = "INFRACAOTIPO";
	public static final String IMPRESSORA = "IMPRESSORA";
	public static final String RECUPERACAO = "RECUPERACAO";
	public static final String IDENTIFICADOR = "identificador";
	public static final String AUTOINFRACAOENVIADAS = "AUTOINFRACAOENVIADAS";
	private String parameters;
	private String bd;

	public BancoDados(String bd) {
		this.bd = bd;
	}

	public void abrirBD() throws BancoDadosException {
		try {
			rs = RecordStore.openRecordStore(bd, true);
		} catch (RecordStoreException e) {
			throw new BancoDadosException();
		}
	}

	public void fecharBD() throws BancoDadosException {

		try {
			rs.closeRecordStore();
		} catch (RecordStoreException e) {
			throw new BancoDadosException();
		}

	}

	public Vector listar() throws BancoDadosException {
		Vector registros = new Vector();
		try {
			abrirBD();
			RecordEnumeration re;

			re = rs.enumerateRecords(null, null, false);
			while (re.hasNextElement()) {
				registros.addElement(StringUtils.stringToHashtable(new String(
						re.nextRecord())));
			}
			fecharBD();
		} catch (Exception e) {
			throw new BancoDadosException();
		}
		return registros;
	}

	public void apagar(Hashtable registro) throws BancoDadosException {

		try {
			abrirBD();
			IdentificadorFiltro filtro = new IdentificadorFiltro(
					(String) registro.get(IDENTIFICADOR));
			RecordEnumeration re;
			re = rs.enumerateRecords(filtro, null, false);
			while (re.hasNextElement()) {
				rs.deleteRecord(re.nextRecordId());
			}
			fecharBD();
		} catch (Exception e) {
			throw new BancoDadosException();
		}

	}
	public void apagar(TransactionObject obj) throws BancoDadosException {
		Hashtable registro = obj.getHashtable();
		try {
			abrirBD();
			Filtro filtro =null;
			
			if(obj instanceof FotoPojo){
				 filtro = new FotoIdentificadorFiltro(
						(String) registro.get(IDENTIFICADOR),(String) registro.get(FotoPojo.TIPOFOTO));
			}else{
				 filtro = new IdentificadorFiltro(
						(String) registro.get(IDENTIFICADOR));
			}
			RecordEnumeration re;
			re = rs.enumerateRecords(filtro, null, false);
			while (re.hasNextElement()) {
				//System.out.println("Apagar "+registro);
				rs.deleteRecord(re.nextRecordId());
			}
			fecharBD();
		} catch (Exception e) {
			throw new BancoDadosException();
		}

	}

	public Vector procurarById(Hashtable registro) throws BancoDadosException {
		Vector registros = new Vector();
		try {
			if(registro.get(IDENTIFICADOR)==null){
				throw new BancoDadosException();
			}
			
			abrirBD();
			IdentificadorFiltro filtro = new IdentificadorFiltro(
					(String) registro.get(IDENTIFICADOR));
			RecordEnumeration re = rs.enumerateRecords(filtro, null, false);

			while (re.hasNextElement()) {
				registros.addElement(StringUtils.stringToHashtable(new String(
						re.nextRecord())));
			}
			fecharBD();
		} catch (Exception e) {
			throw new BancoDadosException();
		}
		return registros;

	}
	
	public Vector procurarById(TransactionObject obj) throws BancoDadosException {
		Hashtable registro = obj.getHashtable();
		Vector registros = new Vector();
		try {
			if(registro.get(IDENTIFICADOR)==null){
				throw new BancoDadosException();
			}
			
			abrirBD();
			Filtro filtro=null;
			if(obj instanceof FotoPojo){
				 filtro = new FotoIdentificadorFiltro(
						(String) registro.get(IDENTIFICADOR),(String) registro.get(FotoPojo.TIPOFOTO));
			}else{
				filtro = new IdentificadorFiltro(
						(String) registro.get(IDENTIFICADOR));
			}
			
			RecordEnumeration re = rs.enumerateRecords(filtro, null, false);

			while (re.hasNextElement()) {
				registros.addElement(StringUtils.stringToHashtable(new String(
						re.nextRecord())));
			}
			fecharBD();
		} catch (Exception e) {
			throw new BancoDadosException();
		}
		return registros;

	}
	

	public void salvarOuAtulizar(Hashtable registro) throws BancoDadosException {
		try {
			if (procurarById(registro).size() > 0) {
				atualizar(registro);
			} else {
				salvar(registro);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BancoDadosException();
			
		}
	}
	public void salvarOuAtulizar(TransactionObject obj) throws BancoDadosException {
		try {
			//Hashtable registro = obj.getHashtable();
			if (procurarById(obj).size() > 0) {
				atualizar(obj);
			} else {
				salvar(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BancoDadosException();
			
		}
	}
	public void salvar(Hashtable registro) throws BancoDadosException {
		try {
			abrirBD();
			Enumeration keys = registro.keys();
			parameters = "";
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				addProperty((String) key, (String) registro.get(key));
			}

			rs
					.addRecord(parameters.getBytes(), 0,
							parameters.getBytes().length);
			fecharBD();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BancoDadosException();
		}
	}

	
	public void salvar(TransactionObject obj) throws BancoDadosException {
		try {
			abrirBD();
			Hashtable registro = obj.getHashtable();
			Enumeration keys = registro.keys();
			parameters = "";
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				addProperty((String) key, (String) registro.get(key));
			}

			rs
					.addRecord(parameters.getBytes(), 0,
							parameters.getBytes().length);
			fecharBD();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BancoDadosException();
		}
	}

	
	public void atualizar(Hashtable registro) throws BancoDadosException {
		try {
			abrirBD();
			IdentificadorFiltro filtro = new IdentificadorFiltro(
					(String) registro.get(IDENTIFICADOR));
			RecordEnumeration re = rs.enumerateRecords(filtro, null, false);

			while (re.hasNextElement()) {
				parameters = "";
				int keyRecord = re.nextRecordId();
				
				Enumeration keys = registro.keys();
				while (keys.hasMoreElements()) {
					String key = (String) keys.nextElement();
					addProperty((String) key, (String) registro.get(key));
				}
				
				rs.setRecord(keyRecord, parameters.getBytes(), 0, parameters.getBytes().length);

			}
			fecharBD();
		} catch (Exception e) {
			throw new BancoDadosException();
		}
	}

	public void atualizar(TransactionObject obj) throws BancoDadosException {
		try {
			abrirBD();
			
			Hashtable registro = obj.getHashtable();
			IdentificadorFiltro filtro = new IdentificadorFiltro(
					(String) registro.get(IDENTIFICADOR));
			RecordEnumeration re = rs.enumerateRecords(filtro, null, false);

			while (re.hasNextElement()) {
				parameters = "";
				int keyRecord = re.nextRecordId();
				
				Enumeration keys = registro.keys();
				while (keys.hasMoreElements()) {
					String key = (String) keys.nextElement();
					addProperty((String) key, (String) registro.get(key));
				}
				
				rs.setRecord(keyRecord, parameters.getBytes(), 0, parameters.getBytes().length);

			}
			fecharBD();
		} catch (Exception e) {
			throw new BancoDadosException();
		}
	}
	
	public void addProperty(String key, String value) {
		if (parameters == null||parameters.equals("")) {
			parameters = key + "=" + value;
		} else {
			parameters += ";" + key + "=" + value;
		}
	}

	public void setBd(String bd) {
		this.bd = bd;
	}
	
	public void apagaBD() throws BancoDadosException{
		try {
			RecordStore.deleteRecordStore(bd);
		} catch (RecordStoreException e) {
			e.printStackTrace();
			throw new BancoDadosException();
		}
	}
	public static void apagaBD(String bdI) throws BancoDadosException{
		try {
			RecordStore.deleteRecordStore(bdI);
		} catch (RecordStoreException e) {
			e.printStackTrace();
			throw new BancoDadosException();
		}
	}
	public int getNumeroRegistro() throws BancoDadosException{
		try {
			abrirBD();
			int num = rs.getNumRecords();
			fecharBD();
			return num;
		} catch (RecordStoreNotOpenException e) {
			e.printStackTrace();
			throw new BancoDadosException();
		}
	}
}

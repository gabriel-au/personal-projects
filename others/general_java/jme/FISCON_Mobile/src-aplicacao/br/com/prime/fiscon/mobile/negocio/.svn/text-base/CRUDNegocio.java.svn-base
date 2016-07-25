package br.com.prime.fiscon.mobile.negocio;

import java.util.Hashtable;
import java.util.Vector;

import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.util.BancoDados;
import br.com.prime.fiscon.mobile.pojo.TransactionObject;

public class CRUDNegocio {
/*	private static final Logger log = LoggerFactory
			.getLogger(AutoInfracaoFoto1.class);
*/
	public CRUDNegocio() {
		//configureMicroLog();
	}

	/*private void configureMicroLog() {
		BluetoothSerialAppender bluetoothSerialAppender = new BluetoothSerialAppender(
				"btspp://001F81000250:3;authenticate=false;encrypt=false;master=false");
		PatternFormatter parFormatter = new PatternFormatter();
		parFormatter.setPattern("%d %c [%P] %m %T");
		bluetoothSerialAppender.setFormatter(parFormatter);
		log.addAppender(bluetoothSerialAppender);
	}*/

	public void gravar(TransactionObject obj) throws BancoDadosException {
		BancoDados bd = new BancoDados(obj.getBD());
		try {
			/*log.debug(obj.getClass().getName() + " Salvar ou atualizar: "
					+ obj.getHashtable());*/
			bd.salvarOuAtulizar(obj);

		} catch (BancoDadosException e) {
			//log.trace(getClass().getName(), e);
			throw new BancoDadosException();
		}
	}

	public void apagar(TransactionObject obj) throws Exception {
		if (obj != null) {

			BancoDados bd = new BancoDados(obj.getBD());
			try {
				/*log.debug(obj.getClass().getName() + " Apaga: "
						+ obj.getHashtable());*/
				bd.apagar(obj);
			} catch (BancoDadosException e) {
				//log.trace(getClass().getName(), e);
				throw new Exception();
			}
		}
	}

	public TransactionObject getObj(TransactionObject obj)
			throws BancoDadosException {
		BancoDados bd = new BancoDados(obj.getBD());

		Vector lista;
		try {
			lista = bd.procurarById(obj.getHashtable());
		} catch (BancoDadosException e) {
			//log.trace(getClass().getName(), e);
			throw new BancoDadosException();
		}
		for (int i = 0; i < lista.size(); i++) {
			obj.carregar((Hashtable) lista.elementAt(i));
		}
		//log.debug(obj.getClass().getName() + " getObj: " + obj.getHashtable());
		return obj;

	}

}

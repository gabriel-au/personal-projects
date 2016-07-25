package br.com.codequest.mobile;

/**
 * Classe de execução de regras
 * @author Gustavo
 *
 */
public abstract class ControllThread implements Runnable{

	private MobileDialog dialog;
	
	/**
	 * Construtor padrão com o objeto MobileDialog, caso o objeto
	 * seja fornecido será executado o repaint da tela
	 */
	public ControllThread(MobileDialog dialog){
		this.dialog=dialog;
	}
	
	
	public void executar(){
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {
		executarRegra();
		dialog.removeAlert();
		dialog.invalidate();
		dialog.repaint();
	}
	
	public abstract void executarRegra();


	public MobileDialog getDialog() {
		return dialog;
	}
}


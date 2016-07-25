package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.AguardeForm;
import br.com.codequest.mobile.util.DiscoverDeviceBluetooth;

public class AguardeConfigurarImpressora extends AguardeForm {
	private DiscoverDeviceBluetooth discoverDeviceBluetooth;
	private MobileDialog dialogInterno;
	private boolean automaticReturn=false;
	public AguardeConfigurarImpressora(MobileDialog dialog, boolean automaticReturn) {
		super(dialog);
		dialogInterno = dialog;
		this.automaticReturn = automaticReturn;

	}

	public void processar() {
		
		discoverDeviceBluetooth = new DiscoverDeviceBluetooth();
		discoverDeviceBluetooth.setTelaDevice(new ConfigurarImpressora(dialogInterno, this.automaticReturn));
		discoverDeviceBluetooth.start();
		setMenuText("Cancelar", "");
	}

	public void executarLeft() {
		discoverDeviceBluetooth.cancelarProcura();
		discoverDeviceBluetooth = null;
		new MenuConfiguracoes().show();
	}
}

package com.brq.mobile.framework.util.battery;

import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.EnviromentIdentifier;

/**
 * Classe respons�vel por verificar e gerenciar os ciclos de vida da bateria.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class BatteryManager {

	private static final String TAG = BatteryManager.class.getName();

	/**
	 * M�todo est�tico para obter uma inst�ncia da classe
	 * <code>com.brq.mobile.framework.util.battery.BatteryManager</code>.
	 * 
	 * @return valor do tipo <code>int</code> referente ao n�vel da bateria.
	 */
	public static int getInstance() {
		// Caso o ambiente operacional seja Windows
		if (EnviromentIdentifier.isWindows()) {
			BatteryManagerWindowsImpl battery = new BatteryManagerWindowsImpl();
			return battery.getBatteryLevel();

			// Caso o ambiente operacional seja Android
		} else if (EnviromentIdentifier.isAndroid()) {
			return BatteryManagerAndroidImpl.BATTERY_LEVEL;

			// Caso o ambiente operacional n�o seja Windows, nem Android.
		} else {
			Log.e(TAG, "Sistema operacional desconhecido > Vc esta utilizando o Macosx ? ; - )");
			throw new RuntimeException();
		}
	}
}
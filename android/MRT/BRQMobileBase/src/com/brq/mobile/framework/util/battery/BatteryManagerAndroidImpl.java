package com.brq.mobile.framework.util.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.brq.mobile.framework.log.Log;

/**
 * Classe respons�vel por obter informa��es relacionadas ao gerenciamento de bateria do dispositivo
 * no ambiente operacional Android.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class BatteryManagerAndroidImpl extends BroadcastReceiver {

	public static int			BATTERY_LEVEL;

	/**
	 * M�todo respons�vel por verificar as mensagens relacionadas ao ciclo de vida da bateria no
	 * Broadcast do ambiente Android.
	 * 
	 * Ap�s verifica��o, obtem o status da bateria em valor do tipo <code>int</code> e efetiva tal
	 * valor no atributo est�tico <i>BATTERY_LEVEL</i>.
	 * 
	 * @param context
	 *            objeto do tipo <code>android.content.Context</code>.
	 * @param intent
	 *            objeto do tipo <code>android.content.Intent</code>.
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		int scale = -1;
		int level = -1;
		int voltage = -1;
		int temp = -1;

		level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
		temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
		voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
		Log.i("BatteryManager", "level is " + level + "/" + scale + ", temp is " + temp + ", voltage is " + voltage);

		BATTERY_LEVEL = level;
	}

}

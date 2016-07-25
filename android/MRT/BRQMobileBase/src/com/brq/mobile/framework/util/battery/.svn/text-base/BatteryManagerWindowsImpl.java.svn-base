package com.brq.mobile.framework.util.battery;


/**
 * Classe respons�vel por obter informa��es relacionadas ao gerenciamento de
 * bateria do dispositivo no ambiente operacional Windows.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class BatteryManagerWindowsImpl implements Kernel32 {

	/**
	 * M�todo respons�vel por retornar um valor do tipo <code>int</code>
	 * relacionado ao status de energia do sistema.
	 */
	@Override
	public int GetSystemPowerStatus(SYSTEM_POWER_STATUS result) {
		Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
		Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
		return Integer.valueOf(batteryStatus.toString());
	}

	/**
	 * M�todo respons�vel por retornar o n�vel atual da bateria.
	 * 
	 * @return retona um valor do tipo <code>int</code> que indica o n�vel de
	 *         carga da bateria.
	 */
	public int getBatteryLevel() {
		return GetSystemPowerStatus(null);
	}

}
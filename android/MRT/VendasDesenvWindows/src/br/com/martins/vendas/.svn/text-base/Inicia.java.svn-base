package br.com.martins.vendas;

import java.io.File;

import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.win.WindowsControl;

public class Inicia {

	private static final String TAG = Inicia.class.getName();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final File html = new File("../VendasDesenvAndroid/assets/www/martins/index.html");
		try {
			WindowsControl.start("Vendas Martins", 800, 600, "file:///".concat(html.getAbsolutePath()));
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}
}

package com.brq.mobile.framework.win;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.json.JSONArray;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.core.Config;
import com.brq.mobile.framework.core.ConfigAccess;
import com.brq.mobile.framework.core.DispatcherBase;
import com.brq.mobile.framework.log.Log;

/**
 * Classe respons�vel pelo controle da execu��o do sistema em um ambiente
 * operacional Windows.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class WindowsControl extends JPanel {

	private static final long serialVersionUID = 6730700959054360825L;

	private static final String TAG = WindowsControl.class.getName();

	/**
	 * M�todo respons�vel pela execu��o inicial do sistema.
	 * 
	 * @param winTitle t�tulo da janela do sistema.
	 * @param x valor num�rico do eixo x da posi��o inicial da cria��o da
	 *            janela.
	 * @param y valor num�rico do eixo y da posi��o inicial da cria��o da
	 *            janela.
	 * @param indexLocation localiza��o da tela HTML inicial do sistema.
	 */
	public static void start(final String winTitle, final int x, final int y, final String indexLocation) {
		initConfig();
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				WindowsControl windowControl = new WindowsControl(indexLocation);
				
				JFrame frame = new JFrame(winTitle);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.getContentPane().add(windowControl, BorderLayout.CENTER);
				frame.setSize(x, y);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
				
				/*
				 * Assegurar que a janela ser� fechada
				 */
				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});

			}
		});
		
		NativeInterface.runEventPump();

	}

	private static void initConfig() {
		try {
			
			InputStream inStream = ClassLoader.getSystemResourceAsStream("config.properties");
			Properties properties = new Properties();
			properties.load(inStream);
			ConfigAccess.setConfig(new Config(properties));

		} catch (Exception e) {
			Log.e(TAG, "Arquivo config.properties n�o encontrado", e);
			System.exit(-1);
		}
	}

	/**
	 * Construtor.
	 * 
	 * @param indexLocation localiza��o da tela HTML inicial do sistema.
	 */
	public WindowsControl(String indexLocation) {
		super(new BorderLayout());

		JWebBrowser jWebBrowser = new JWebBrowser(JWebBrowser.useWebkitRuntime());
		
		jWebBrowser.setBarsVisible(false);
		jWebBrowser.setDefaultPopupMenuRegistered(false);
		
		jWebBrowser.navigate(indexLocation);

		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.add(jWebBrowser, BorderLayout.CENTER);

		this.add(jPanel, BorderLayout.CENTER);

		jWebBrowser.addWebBrowserListener(new WindowsWebBrowerAdpter(jWebBrowser));

	}

	class WindowsWebBrowerAdpter extends WebBrowserAdapter {

		private JWebBrowser jWebBrowser = null;

		private Map<String, String> plugins = new HashMap<String, String>();

		WindowsWebBrowerAdpter(JWebBrowser jWebBrowser) {

			this.jWebBrowser = jWebBrowser;

			try {
				plugins.putAll(PluginRead.readXML());
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}

		@SuppressWarnings("rawtypes")
		@Override
		public void commandReceived(WebBrowserCommandEvent e) {
			
			DispatcherBase plugin = null;

			String pluginName = e.getCommand();
			Object[] parameters = e.getParameters();
			String action = (String) parameters[0];
			String successFunction = (String) parameters[1];
			String failFunction = (String) parameters[2];
			String args = (String) parameters[3];

			String dispacherClassName = plugins.get(pluginName);

			try {

				if (dispacherClassName != null) {

					Class cls = Class.forName(dispacherClassName);
					plugin = (DispatcherBase) cls.newInstance();

				} else {

					String retFunc = failFunction + "('Plugin " + pluginName + " Nao Encontrado')";
					jWebBrowser.executeJavascript(retFunc);
					return;
				}

				JSONArray json = null;

				if (args == null) {
					json = new JSONArray();
				} else {
					json = new JSONArray(args);
				}

				ActionResult result = new ActionResult(Status.INSTANTIATION_EXCEPTION);

				if (plugin != null) {
					result = plugin.execute(action, json, "1");
				}

				String funcaoRetorno = successFunction.concat(String.format("(%s)", result.getMessage()));

				if (result.getStatus() != Status.OK.ordinal()) {
					funcaoRetorno = failFunction.concat(String.format("(%s)", result.getMessage()));
				}

				jWebBrowser.executeJavascript(funcaoRetorno);

			} catch (Exception exception) {
				Log.e(TAG, exception.getMessage());
			}
		}

	}
}

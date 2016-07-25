package br.com.martins.vendas.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import br.com.martins.vendas.R;

import com.brq.mobile.framework.core.Updater;
import com.brq.mobile.framework.log.Log;
import com.phonegap.DroidGap;

public class AtualizaActivity extends DroidGap {

	private static final String	TAG							= AtualizaActivity.class.getName();

	private static final String	PATH_JARS					= "/martins/jars";

	private static final String	PATH_HTML					= "/martins/html/www";

	private static final String	PATH_PLUGINS				= "/martins/plugins";

	public static final String	EXTRA_INFO_RESULT_UPDATE	= "EXTRA_INFO_RESULT_UPDATE";

	private boolean				updateSucessful				= false;

	private EStatusUpdate		eStatusUpdate				= EStatusUpdate.STOPED;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_screen);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		updateSucessful = false;
		try {
			
//			if (EStatusUpdate.STOPED.equals(eStatusUpdate)) {
//
//				Intent intent = new Intent(Intent.ACTION_CALL);
//				startActivityForResult(intent, Updater.IPC_ID);
//			}
			eStatusUpdate = EStatusUpdate.SUCESS_NEXXERA;
			
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();
		//
		monitoring();
	}

	/*
	 * Chamado no retorno da aplicação nexxera.
	 * 
	 * (non-Javadoc)
	 * @see com.phonegap.DroidGap#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (resultCode == RESULT_OK) {

			eStatusUpdate = EStatusUpdate.SUCESS_NEXXERA;

		} else if (resultCode == RESULT_CANCELED) {

			eStatusUpdate = EStatusUpdate.FAIL_NEXXERA;
		}
	}

	/*
	 * Destroy activity atual e chama a activity principal
	 * 
	 * (non-Javadoc)
	 * @see com.phonegap.DroidGap#onDestroy()
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();

		new Thread(new Runnable() {
			@Override
			public void run() {
				String stringExtra = getIntent().getStringExtra("ACT");

				Intent intent = new Intent(stringExtra);
				intent.putExtra(EXTRA_INFO_RESULT_UPDATE, updateSucessful);

				startActivity(intent);
			}

		}).start();
	}

	/**
	 * Monitoramento
	 */
	public void monitoring() {

		//TODO: Remove FAIL_NEXXERA
		final Activity activity = this;
		if (EStatusUpdate.SUCESS_NEXXERA.equals(eStatusUpdate) || EStatusUpdate.FAIL_NEXXERA.equals(eStatusUpdate)) {
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					
					try {

						Updater.execute(activity, PATH_JARS, PATH_PLUGINS, PATH_HTML);
						updateSucessful = true;

					} catch (Exception e) {

						Log.e(TAG, e.getLocalizedMessage());
						updateSucessful = true;

					}
					
					activity.finish();
				}

			}).start();

		} else if (EStatusUpdate.FAIL_NEXXERA.equals(eStatusUpdate)) {
			
			updateSucessful = false;
			activity.finish();
		}
	}

	enum EStatusUpdate {
		STOPED, SUCESS_NEXXERA, FAIL_NEXXERA }
}
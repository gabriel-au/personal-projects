package br.com.primeits.toten.ui.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class MenuActivity extends Activity {
	
	private LinearLayout linearLayout;
	private Timer timer;
	private int timerDelay = 3 * 60 * 1000; //3 minutos
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		getWindow().getDecorView().setSystemUiVisibility(8);
		
		linearLayout = (LinearLayout) findViewById(R.id.linearLayoutMenu);
		linearLayout.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/totenres/content/media/structure/menu.jpg")));
		
		ImageButton buttonInstitucional = (ImageButton) findViewById(R.id.buttonInstitucional);        
		buttonInstitucional.setOnClickListener(institucionalListener);
		
		ImageButton buttonLinhaTempo = (ImageButton) findViewById(R.id.buttonLinhaTempo);        
		buttonLinhaTempo.setOnClickListener(linhaTempoListener);
		
		ImageButton buttonProjetos = (ImageButton) findViewById(R.id.buttonProjetos);        
		buttonProjetos.setOnClickListener(projetosListener);
		
		ImageButton buttonGaleriaFotos = (ImageButton) findViewById(R.id.buttonGaleriaFotos);        
		buttonGaleriaFotos.setOnClickListener(galeriaFotosListener);
		
		ImageButton buttonGaleriaVideos = (ImageButton) findViewById(R.id.buttonGaleriaVideos);        
		buttonGaleriaVideos.setOnClickListener(galeriaVideosListener);
	}
	
	private OnClickListener institucionalListener = new OnClickListener() {
		public void onClick(View v) {
			timer.cancel();
			Intent i = new  Intent(MenuActivity.this, InstitucionalActivity.class);
			startActivity(i);
		}
	};
	
	private OnClickListener linhaTempoListener = new OnClickListener() {
		public void onClick(View v) {
			timer.cancel();
			Intent i = new  Intent(MenuActivity.this, LinhaTempoActivity.class);
			startActivity(i);
		}
	};
	
	private OnClickListener projetosListener = new OnClickListener() {
		public void onClick(View v) {
			timer.cancel();
			Intent i = new  Intent(MenuActivity.this, ProjetosActivity.class);
			startActivity(i);
		}
	};
	
	private OnClickListener galeriaFotosListener = new OnClickListener() {
		public void onClick(View v) {
			timer.cancel();
			Intent i = new  Intent(MenuActivity.this, GaleriaFotosActivity.class);
			startActivity(i);
		}
	};
	
	private OnClickListener galeriaVideosListener = new OnClickListener() {
		public void onClick(View v) {
			timer.cancel();
			Intent i = new  Intent(MenuActivity.this, GaleriaVideosActivity.class);
			startActivity(i);
		}
	};
	
	@Override
	protected void onResume() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				finish();
			}
		}, timerDelay);
		
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
}

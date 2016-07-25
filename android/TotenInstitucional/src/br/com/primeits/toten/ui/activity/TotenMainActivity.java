package br.com.primeits.toten.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
//import android.view.Window;
//import android.view.WindowManager;
import android.widget.RelativeLayout;

public class TotenMainActivity extends Activity implements AnimationListener {

//	private static String logtag = "TotenInstitucional";
	
	private RelativeLayout relativeLayout;
	private ImageButton buttonEnter;
	
	private Animation blink;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toten_main);
		
		getWindow().getDecorView().setSystemUiVisibility(8);
		
		relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutMain);
		relativeLayout.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/totenres/content/media/structure/intro.jpg")));

		buttonEnter = (ImageButton) findViewById(R.id.buttonEnter);
		
		blink = AnimationUtils.loadAnimation(getApplicationContext(), R.drawable.blink);
		blink.setAnimationListener(this);
		
		buttonEnter.setBackgroundResource(R.drawable.intro_toque);
		buttonEnter.setOnClickListener(menuListener);
		
		buttonEnter.startAnimation(blink);
		
		//TESTE
//		Intent i = new Intent(TotenMainActivity.this, GaleriaFotosActivity.class);
		//i.putExtra("activity", "institucionalListener");
//		startActivity(i);
		
		// listener with the implementation above
//		buttonMenu.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				if (event.getAction() == MotionEvent.ACTION_DOWN) {
//					Display display = getWindowManager().getDefaultDisplay();
//					final int width = getWindow().getDecorView().getWidth(); //display.getWidth();
//					final int height = getWindow().getDecorView().getHeight(); //display.getHeight();
//					
//					String text = "Size: " + width + "x" + height
//							+ "\nTouch coordinates : "
//							+ String.valueOf(event.getX()) + "x"
//							+ String.valueOf(event.getY());
//
//					Toast.makeText(TotenMainActivity.this, text,
//							Toast.LENGTH_LONG).show();
//					// makeText(this, text, Toast.LENGTH_LONG).show();
//				}
//				return true;
//			}
//		});

	}

	 private OnClickListener menuListener = new OnClickListener() { 
		 public void onClick(View v) {
			 Intent i = new Intent(TotenMainActivity.this, MenuActivity.class);
			 startActivity(i);
		 }
	 };
	 

	// @Override
	// public boolean onTouchEvent(MotionEvent event) {
	// // MotionEvent object holds X-Y values
	// String text = "You click at x = " + event.getX() + " and y = " +
	// event.getY();
	// Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	//
	// return super.onTouchEvent(event);
	// }

	@Override
	protected void onStart() {
//		Log.d(logtag, "onStart() called");
		super.onStart();
	}

	@Override
	protected void onResume() {
//		Log.d(logtag, "onResume() called");
		super.onResume();

	}

	@Override
	protected void onPause() { 
//		Log.d(logtag, "onPause() called");
		super.onPause();

	}

	@Override
	protected void onStop() {
//		Log.d(logtag, "onStop() called");
		super.onStop();

	}

	@Override
	protected void onDestroy() {
//		Log.d(logtag, "onDestroy() called");
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}

	@Override
	public void onAnimationStart(Animation animation) {
	}

}

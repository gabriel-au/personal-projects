package br.com.primeits.toten.ui.activity;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import br.com.primeits.toten.utils.ImageAdapter;
import br.com.primeits.toten.utils.Util;

public class GaleriaFotosActivity extends Activity {

	private LinearLayout linearLayout;
	private RelativeLayout relativeLayoutParent;
	private RelativeLayout relativeLayout;
	private GridView gridView;
	private ImageView imageView;
	private ImageAdapter imageAdapter;
	private WebView iv;
	private Context context;
	
	private Timer timer;
	
	private long timerDelay = 10 * 60 * 1000; //10 minutos
	private long timeLastTouch = new Date().getTime();
	
	private ImageButton btLeft;
	private ImageButton btRight;
	
	private ImageButton btLeftPage;
	private ImageButton btRightPage;
	
	private int imgPerPage = 28;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_galeria_fotos);
		context = this;

		getWindow().getDecorView().setSystemUiVisibility(8);
		
		Bitmap bitmap = null;
		
//		try {
			bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/totenres/content/media/structure/fundo_internas.jpg");
//		} catch (OutOfMemoryError ome) {
//    		reloadActivity();
//    	}
		
		linearLayout = (LinearLayout) findViewById(R.id.linearLayoutGaleriaFotos);
		
		if (bitmap != null) { 
        	linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }
		
		relativeLayoutParent = (RelativeLayout) findViewById(R.id.relativeLayoutGaleriaFotos);
		relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutContentFotos);
		gridView = (GridView) findViewById(R.id.gridViewPictures);
		
		new PrefetchData().execute();
		
		// BUTTONS
		ImageButton button1 = (ImageButton) findViewById(R.id.button1);
		button1.setOnClickListener(linhaTempoListener);

		ImageButton button2 = (ImageButton) findViewById(R.id.button2);
		button2.setOnClickListener(projetosListener);

		ImageButton button4 = (ImageButton) findViewById(R.id.button4);
		button4.setOnClickListener(galeriaVideosListener);
		
		ImageButton button5 = (ImageButton) findViewById(R.id.button5);        
        button5.setOnClickListener(institucionalListener);

		ImageButton buttonBack = (ImageButton) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(backListener);

	}
	
	private void viewImage(int position) { 
		//VIEW BUTTONS
		if (position > 0) {
			btLeft.setVisibility(View.VISIBLE);
		} else {
			btLeft.setVisibility(View.INVISIBLE);
		}
		
		if (position < (imageAdapter.getCount()-1)) {
			btRight.setVisibility(View.VISIBLE);
		} else {
			btRight.setVisibility(View.INVISIBLE);
		}
		
		try {
			imageView.setImageBitmap(imageAdapter.getBitmap(position));
			imageView.setVisibility(View.VISIBLE);
			
			btLeftPage.setVisibility(View.INVISIBLE);
			btRightPage.setVisibility(View.INVISIBLE);
		} catch (OutOfMemoryError ome) {
    		reloadActivity();
    	}
	}
	
	private void viewPage(int id) {
		iv.requestFocus();
		iv.setVisibility(View.VISIBLE);
		
		if (id > 1) {
			btLeftPage.setVisibility(View.VISIBLE);
		} else {
			btLeftPage.setVisibility(View.INVISIBLE);
		}
		
		if (id < imageAdapter.pageCount()) {
			btRightPage.setVisibility(View.VISIBLE);
		} else {
			btRightPage.setVisibility(View.INVISIBLE);
		}
		
		imageAdapter.viewPage(id);
		iv.setVisibility(View.INVISIBLE);
	}

	private OnClickListener linhaTempoListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new Intent(GaleriaFotosActivity.this, LinhaTempoActivity.class);
			startActivity(i);
			finish();
		}
	};

	private OnClickListener projetosListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new Intent(GaleriaFotosActivity.this, ProjetosActivity.class);
			startActivity(i);
			finish();
		}
	};

	private OnClickListener galeriaVideosListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new Intent(GaleriaFotosActivity.this, GaleriaVideosActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener institucionalListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(GaleriaFotosActivity.this, InstitucionalActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener hideImage = new OnClickListener() {
		public void onClick(View v) {
			registerLastTouch();
			v.setVisibility(View.INVISIBLE);
			
			btLeft.setVisibility(View.INVISIBLE);
			btRight.setVisibility(View.INVISIBLE);
			
			if (imageAdapter.getLastViewedPage() > 0) {
				btLeftPage.setVisibility(View.VISIBLE);
			}
			
			if (imageAdapter.getLastViewedPage() < imageAdapter.pageCount()) {
				btRightPage.setVisibility(View.INVISIBLE);
			}
		}
	};
	
	private OnClickListener leftImage = new OnClickListener() {
		public void onClick(View v) {
			registerLastTouch();
			v.setVisibility(View.INVISIBLE);
			viewImage((imageAdapter.getLastViewed()-1));
		}
	};
	
	private OnClickListener rightImage = new OnClickListener() {
		public void onClick(View v) {
			registerLastTouch();
			v.setVisibility(View.INVISIBLE);
			viewImage((imageAdapter.getLastViewed()+1));
		}
	};
	
	private OnClickListener nextPage = new OnClickListener() {
		public void onClick(View v) {
			registerLastTouch();
			//v.setVisibility(View.INVISIBLE);
			//imageAdapter.viewPage(imageAdapter.getLastViewedPage()+1);
			viewPage(imageAdapter.getLastViewedPage()+1);
		}
	};
	
	private OnClickListener previousPage = new OnClickListener() {
		public void onClick(View v) {
			registerLastTouch();
			//v.setVisibility(View.INVISIBLE);
			//imageAdapter.viewPage(imageAdapter.getLastViewedPage()+1);
			viewPage(imageAdapter.getLastViewedPage()-1);
		}
	};

	private OnClickListener backListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
	
	private void registerLastTouch() {
		this.timeLastTouch = new Date().getTime();
	}
	
	private void checkTimeToFinish() {
		long checkLastTouch = new Date().getTime() - timeLastTouch;
		
		if (checkLastTouch >= timerDelay) {
			finish();
		} else {
			timer.cancel();
			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					checkTimeToFinish();
				}
			}, timerDelay);
		}
	}

	@Override
	protected void onResume() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				checkTimeToFinish();
			}
		}, timerDelay);
		
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.galeria_fotos, menu);
		return true;
	}
	
	@Override
    protected void onDestroy() {
	    super.onDestroy();
	    
	    imageAdapter.removeItems();
	    imageAdapter.notifyDataSetChanged();
	    
	    new Util().unbindDrawables(linearLayout);
	    
	    //System.gc();
    }
    
    private void reloadActivity() {
		//finish();
		
		Intent i = new Intent(context, context.getClass());
		startActivity(i);
		
		finish();
    }
    
	private class PrefetchData extends AsyncTask<Void, Void, Void> {
		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        
	        iv = new WebView(context);
	        iv.loadUrl("file:///" + Environment.getExternalStorageDirectory() + "/totenres/content/media/other/loading.gif");
	        
	        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(128, 128);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
	        
	        iv.setLayoutParams(params);
			iv.setBackgroundColor(getResources().getColor(android.R.color.transparent));
			iv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
			iv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
			iv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

	        relativeLayout.addView(iv);
	    }

	    @Override
	    protected Void doInBackground(Void... params) {
	    	try {
	    		imageAdapter = new ImageAdapter(context, "totenres/content/media/galeria_de_fotos/imgs", 150, 150, 28, false);
	    	} catch (OutOfMemoryError ome) {
	    		reloadActivity();
	    	}
			
			return null;
	    }

	    @Override
	    protected void onPostExecute(Void result) {
	        super.onPostExecute(result);
	        iv.setVisibility(View.INVISIBLE);
	        
	        imageAdapter.setBorder(5, "#545454");
	        gridView.setAdapter(imageAdapter);
	        
	        relativeLayout.setBackgroundResource(R.drawable.rounded_text_box_gray_light);
	    	gridView.setVisibility(View.VISIBLE);
	    	
	    	RelativeLayout.LayoutParams params;
	    	
	    	//IMAGE VIEW
			imageView = new ImageView(context);
			
			params = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			imageView.setLayoutParams(params);
			imageView.setBackgroundColor(Color.parseColor("#80000000"));
			//imageView.setImageBitmap(imageAdapter.getBitmap(position));
			imageView.setVisibility(View.INVISIBLE);
			imageView.setOnClickListener(hideImage);
			
			relativeLayout.addView(imageView);
	    	
	    	//BUTTON LEFT - VIEW IMAGE
	    	btLeft = new ImageButton(context);
			
			params = new RelativeLayout.LayoutParams(100, 100);
			params.addRule(RelativeLayout.CENTER_VERTICAL);
			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			
			btLeft.setLayoutParams(params);
			btLeft.setBackgroundColor(Color.parseColor("#00000000"));
			
			try {
				btLeft.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/totenres/content/media/structure/bt_left.png"));
			} catch (OutOfMemoryError ome) {
	    		reloadActivity();
	    	}
			
			btLeft.setVisibility(View.INVISIBLE);
			btLeft.setOnClickListener(leftImage);
			
			relativeLayout.addView(btLeft);
			
			//BUTTON RIGHT - VIEW IMAGE
			btRight = new ImageButton(context);
			
			params = new RelativeLayout.LayoutParams(100, 100);
			params.addRule(RelativeLayout.CENTER_VERTICAL);
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			
			btRight.setLayoutParams(params);
			btRight.setBackgroundColor(Color.parseColor("#00000000"));
			
			try {
				btRight.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/totenres/content/media/structure/bt_right.png"));
			} catch (OutOfMemoryError ome) {
	    		reloadActivity();
	    	}
			
			btRight.setVisibility(View.INVISIBLE);
			btRight.setOnClickListener(rightImage);
			
			relativeLayout.addView(btRight);
			
			//BUTTON LEFT - VIEW PAGES
	    	btLeftPage = new ImageButton(context);
			
			params = new RelativeLayout.LayoutParams(75, 75);
			//params.addRule(RelativeLayout.CENTER_VERTICAL);
			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			params.topMargin = 550;
			params.leftMargin = 520;
			
			btLeftPage.setLayoutParams(params);
			btLeftPage.setBackgroundColor(Color.parseColor("#00000000"));
			
			try {
				btLeftPage.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/totenres/content/media/structure/bt_left_page.png"));
			} catch (OutOfMemoryError ome) {
	    		reloadActivity();
	    	}
			
			btLeftPage.setVisibility(View.INVISIBLE);
			btLeftPage.setOnClickListener(previousPage);
			
			relativeLayoutParent.addView(btLeftPage);
			
			btRightPage = new ImageButton(context);
			
			params = new RelativeLayout.LayoutParams(75, 75);
			//params.addRule(RelativeLayout.CENTER_VERTICAL);
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			params.topMargin = 550;
			params.rightMargin = 55;
			
			btRightPage.setLayoutParams(params);
			btRightPage.setBackgroundColor(Color.parseColor("#00000000"));
			
			try {
				btRightPage.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/totenres/content/media/structure/bt_right_page.png"));
			} catch (OutOfMemoryError ome) {
	    		reloadActivity();
	    	}
			
			btRightPage.setVisibility(View.VISIBLE);
			btRightPage.setOnClickListener(nextPage);
			
			relativeLayoutParent.addView(btRightPage);
			
			gridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					registerLastTouch();
					viewImage(position);
				}
			});
	    }
	}

}



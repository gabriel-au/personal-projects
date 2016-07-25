package br.com.primeits.toten.ui.activity;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import br.com.primeits.toten.utils.ImageAdapter;
import br.com.primeits.toten.utils.Util;

public class GaleriaVideosActivity extends Activity {
	
	private LinearLayout linearLayout;
	private RelativeLayout relativeLayout;
	private GridView gridView;
	private VideoView videoView;
	private ImageAdapter imageAdapter;
	private Context context;
	
	private ImageButton btPlayPause;
//	private Button btStop;
	
	private File clip;
	private File videoFile = null;
	private MediaController ctlr;
	
	private int videoPlay = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_galeria_videos);
		context = this;
		
		getWindow().getDecorView().setSystemUiVisibility(8);
		
		Bitmap bitmap = null;
		
//		try {
			bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/totenres/content/media/structure/fundo_internas.jpg");
//		} catch (OutOfMemoryError ome) {
//    		reloadActivity();
//    	}
		
		linearLayout = (LinearLayout) findViewById(R.id.linearLayoutGaleriaVideos);

		if (bitmap != null) {
        	linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }
		
		relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutContentVideos);
		gridView = (GridView) findViewById(R.id.gridViewVideos);
		videoView = (VideoView) findViewById(R.id.videoView);
		
		bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/totenres/content/media/structure/bt_play_pause.png");
		
		btPlayPause = (ImageButton) findViewById(R.id.btVideoPlayPause);
		btPlayPause.setBackgroundDrawable(new BitmapDrawable(bitmap));
		btPlayPause.setOnClickListener(playPauseVideoListener);
		
//		btStop = (Button) findViewById(R.id.btVideoStop);
//		btStop.setOnClickListener(stopVideoListener);
		
		videoView.setVisibility(View.INVISIBLE);
		
		//File clip = new File(Environment.getExternalStorageDirectory(), "/totenres/content/media/galeria_de_videos/videos/1.mp4");

		//ctlr = new MediaController(context);
		//ctlr.setMediaPlayer(videoView);
		
		//videoView.setMediaController(ctlr);
		//commands
		
//		if (clip.exists()) {
			//videoView.setVideoPath(clip.getAbsolutePath());
			
			
//			videoView.start();
//		}
		
//		MediaController mediaController = new MediaController(this);
//		mediaController.setAnchorView(videoView);
//		videoView.setMediaController(mediaController);
		
		new PrefetchData().execute();
		
		ImageButton button1 = (ImageButton) findViewById(R.id.button1);        
        button1.setOnClickListener(linhaTempoListener);
		
		ImageButton button2 = (ImageButton) findViewById(R.id.button2);        
		button2.setOnClickListener(projetosListener);
		
		ImageButton button3 = (ImageButton) findViewById(R.id.button3);        
		button3.setOnClickListener(galeriaFotosListener);
		
		ImageButton button5 = (ImageButton) findViewById(R.id.button5);        
        button5.setOnClickListener(institucionalListener);
		
		ImageButton buttonBack = (ImageButton) findViewById(R.id.buttonBack);        
        buttonBack.setOnClickListener(backListener);
        
	}
	
	private OnClickListener linhaTempoListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(GaleriaVideosActivity.this, LinhaTempoActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener projetosListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(GaleriaVideosActivity.this, ProjetosActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener galeriaFotosListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(GaleriaVideosActivity.this, GaleriaFotosActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener institucionalListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(GaleriaVideosActivity.this, InstitucionalActivity.class);
			startActivity(i);
			finish();
		}
	};
    
    private OnClickListener backListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
	
	private OnClickListener stopVideoListener = new OnClickListener() {
		public void onClick(View v) {
			videoView.stopPlayback();
		}
	};
	
	private OnClickListener playPauseVideoListener = new OnClickListener() {
		public void onClick(View v) {
			if (videoView.isPlaying()) {
				videoView.pause();
			} else {
				videoView.start();
			}
			
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.galeria_videos, menu);
		return true;
	}
	
	@Override
    protected void onDestroy() {
	    super.onDestroy();
	
	    imageAdapter.removeItems();
	    imageAdapter.notifyDataSetChanged();
	    
	    new Util().unbindDrawables(linearLayout);
	    
//	    System.gc();
    }
    
    private void reloadActivity() {
		//finish();
		
		Intent i = new Intent(context, context.getClass());
		startActivity(i);
		
		finish();
    }
	
	private void videoPlayList() {
		if (videoPlay >= imageAdapter.getCount()) {
			videoPlay = 0;
		}
		
		videoFile = imageAdapter.getVideoFile(videoPlay);
		
		videoView.setVideoPath(videoFile.getAbsolutePath());
		videoView.start();
		
		videoPlay++;
	}
	
	private void videoPlayId(int id) {
		videoFile = imageAdapter.getVideoFile(id);
		//imageAdapter.getView(position, convertView, parent)
		
		videoView.setVideoPath(videoFile.getAbsolutePath());
		videoView.start();
		
		videoPlay = id + 1;
	}
	
	private class PrefetchData extends AsyncTask<Void, Void, Void> {
		WebView iv = null;
		
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
	    		imageAdapter = new ImageAdapter(context, "totenres/content/media/galeria_de_videos/imgs", 200, 100, 0, true);
	    	} catch (OutOfMemoryError ome) {
	    		reloadActivity();
	    	}
			
			return null;
	    }

	    @Override
	    protected void onPostExecute(Void result) {
	        super.onPostExecute(result);
	        iv.setVisibility(View.INVISIBLE);
	        
	        videoView.setVisibility(View.VISIBLE);
	        
	        //videoFile = imageAdapter.getVideoFile(0);
	        
	        videoPlayList();
	        
			if (videoFile != null) {
				
				if (imageAdapter.getCount() > 1) {
					videoView.setOnCompletionListener(new OnCompletionListener() {
				        @Override
						public void onCompletion(MediaPlayer mp) {
				            mp.stop();
				            videoPlayList();
				        }
				    });
				} else {
					videoView.setOnPreparedListener(new OnPreparedListener() {
					    @Override
					    public void onPrepared(MediaPlayer mp) {
					        mp.setLooping(true);
					    }
					});
				}
		        
				videoView.requestFocus();
				
				videoView.setVideoPath(videoFile.getAbsolutePath());
				videoView.start();
			}
	        
	        gridView.setAdapter(imageAdapter);
	        
	        relativeLayout.setBackgroundResource(R.drawable.rounded_text_box_gray);
	    	gridView.setVisibility(View.VISIBLE);
//	    	imageView.setImageBitmap(imageAdapter.getBitmap(0));
	    	videoView.setVisibility(View.VISIBLE);
			
			gridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					videoPlayId(position);
					
//					videoFile = imageAdapter.getVideoFile(position);
//					
//					if (videoFile != null) {
//						videoView.setVideoPath(videoFile.getAbsolutePath());
//						videoView.start();
//					}
				}
			});
	    }
	}

}

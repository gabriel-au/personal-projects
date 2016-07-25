package br.com.primeits.toten.ui.activity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import br.com.primeits.toten.utils.Util;
import br.com.primeits.toten.utils.XMLParser;
import br.com.primeits.toten.vo.Image;
import br.com.primeits.toten.vo.TotenInstitucional;

public class InstitucionalActivity extends Activity {

	public final String KEY_SECTION = "section";
	public final String KEY_NAME = "name";
	public final String KEY_ITEM = "item";
	public final String KEY_ID = "id";
	public final String KEY_TITLE = "title";
	public final String KEY_IMAGE = "image";
	public final String KEY_IMAGE_NAME = "name";
	public final String KEY_IMAGE_PATH = "path";
	public final String KEY_CONTENT = "content";
	
	private Context context;
	private LinearLayout linearLayout;
	private RelativeLayout relativeLayout;
	private TextView tvTitle;
	private WebView wvBoxMissao;
    private WebView wvBoxTexto1;
    private WebView wvBoxTexto2;
	
	private TotenInstitucional institucional;
	private Image image;
	private ArrayList<TotenInstitucional> arrayInstitucional;
	
	private String institucionalTitle = "";
	private String htmlMissao = null;
	private String htmlVisao1 = null;
	private String htmlVisao2 = null;
	private String htmlImage = null;
	public String readedFromFile = "";
	int divisor = 0;
	
	private Timer timer;
	
	private long timerDelay = 10 * 60 * 1000; //10 minutos
	private long timeLastTouch = new Date().getTime();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institucional);
        this.context = this;
        
        getWindow().getDecorView().setSystemUiVisibility(8);
        
        Bitmap bitmap = null; 
        
//        try {
        	bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/totenres/content/media/structure/fundo_internas.jpg");
//        } catch (OutOfMemoryError ome) {
//    		reloadActivity();
//    	}
        
        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutInstitucional);
		
        if (bitmap != null) { 
        	linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutContentInstitucional);
        tvTitle = (TextView) findViewById(R.id.textViewInstitucionalTitle);
        wvBoxMissao = (WebView) findViewById(R.id.webviewBoxMissao);
        wvBoxTexto1 = (WebView) findViewById(R.id.webViewBoxTexto1);
        wvBoxTexto2 = (WebView) findViewById(R.id.webViewBoxTexto2);
        
        new PrefetchData().execute();
        
        ImageButton button1 = (ImageButton) findViewById(R.id.button1);
        button1.setOnClickListener(linhaTempoListener);
		
		ImageButton button2 = (ImageButton) findViewById(R.id.button2);        
		button2.setOnClickListener(projetosListener);
		
		ImageButton button3 = (ImageButton) findViewById(R.id.button3);        
		button3.setOnClickListener(galeriaFotosListener);
		
		ImageButton button4 = (ImageButton) findViewById(R.id.button4);        
		button4.setOnClickListener(galeriaVideosListener);
		
		ImageButton buttonBack = (ImageButton) findViewById(R.id.buttonBack);        
        buttonBack.setOnClickListener(backListener);
        
	}
    
	//@SuppressLint("SetJavaScriptEnabled")
	private void setWebViewContent(WebView wv, String file, String image, boolean javascript) {
        
        if (file == null) {
        	relativeLayout.removeView(wv);
        }
		
        if (image != null) {
        	image =  "<img src=\"file:///" + Environment.getExternalStorageDirectory() + "/totenres/content/media/institucional/imgs/" + image + "\" align=\"left\" width=\"200px\" style=\"padding: 0px 10px 5px 0px;\">";
        } else {
        	image = "";
        }
        
        if (javascript) {
        	//JsUtil js = new JsUtil();
        	
			//wv.getSettings().setJavaScriptEnabled(true);
			//wv.addJavascriptInterface(InstitucionalActivity.this, "js");
			
			image = "<a href=\"#\" onClick=\"js.test();\" border=0>" + image + "</a>";
		}
        
		String text = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" >"
			+"<link href=\"file:///" + Environment.getExternalStorageDirectory() + "/totenres/content/css/institucional.css\" type=\"text/css\" rel=\"stylesheet\"></head>"
			+ "<body style=\"background-color:transparent;\">" //<p align=\"justify\">" 
			+ image + file
			+ "</body></html>";
		
		//System.out.println("TEXT: " + text);
        
		wv.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
		
		wv.setBackgroundColor(0x00000000);
		wv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//		wv.setHorizontalScrollBarEnabled(false);
//		wv.setVerticalScrollBarEnabled(false);
		
		wv.setOnTouchListener(new View.OnTouchListener() {
		    public boolean onTouch(View v, MotionEvent event) {
		    	registerLastTouch();
		    	return true;
		    }
		});
		
//		wv.setOnTouchListener(new View.OnTouchListener() {
//		    public boolean onTouch(View v, MotionEvent event) {
//		      return (event.getAction() == MotionEvent.ACTION_MOVE);
//		    }
//		});
    }
	
    private void reloadActivity() {
		//finish();
		
		Intent i = new Intent(context, context.getClass());
		startActivity(i);
		
		finish();
    }
    
    private String readFromFile(String file) {

	    String ret = "";

	    try {
	        InputStream inputStream = getAssets().open(file);

	        if (inputStream != null) {
	            //InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	        	
	        	Reader reader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(reader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ((receiveString = bufferedReader.readLine()) != null) {
	                stringBuilder.append(receiveString);
	            }

	            inputStream.close();
	            ret = stringBuilder.toString();
	            
	            //System.out.println("Length: " + ret.length());
	        }
	    }
	    catch (FileNotFoundException e) {
	        Log.e("Institucional activity", "File not found: " + e.toString());
	    } catch (IOException e) {
	        Log.e("Institucional activity", "Can not read file: " + e.toString());
	    }

	    return ret;
	}
    
    private void viewImage() {
    	Toast.makeText(this, "FOTO", Toast.LENGTH_LONG).show();
    	System.out.println("IMAGE");
    }
    
	private OnClickListener linhaTempoListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(InstitucionalActivity.this, LinhaTempoActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener projetosListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(InstitucionalActivity.this, ProjetosActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener galeriaFotosListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(InstitucionalActivity.this, GaleriaFotosActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener galeriaVideosListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(InstitucionalActivity.this, GaleriaVideosActivity.class);
			startActivity(i);
			finish();
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.institucional, menu);
        return true;
    }
    
    @Override
    protected void onDestroy() {
	    super.onDestroy();
	    
	    new Util().unbindDrawables(linearLayout);
//	    unbindDrawables(linearLayout);
	    
//	    System.gc();
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
	    	XMLParser parser = new XMLParser();
	        Util util = new Util();
	        
	        arrayInstitucional = new ArrayList<TotenInstitucional>();
	        
	        String xml = util.readFromFile("totenres/content/xml/institucional.xml");
	        
	        Document doc = parser.getDomElement(xml);
	        
	        NodeList nlTitle = doc.getElementsByTagName(KEY_SECTION);
	        if (nlTitle.getLength() > 0) {
	        	Element e = (Element) nlTitle.item(0);
	        	institucionalTitle = parser.getValue(e, KEY_NAME);
	        }
	        
			NodeList nl = doc.getElementsByTagName(KEY_ITEM);
			
			for (int i=0; i < nl.getLength(); i++) {
				Element e = (Element) nl.item(i);
				
				institucional = new TotenInstitucional();
				institucional.setId(parser.getValue(e, KEY_ID));
				institucional.setTitle(parser.getValue(e, KEY_TITLE));
//				institucional.setImagePath(parser.getValue(e, KEY_IMAGE));
				institucional.setContent(parser.getValue(e, KEY_CONTENT));
				
				NodeList nlNew = e.getElementsByTagName(KEY_IMAGE);
				
				ArrayList<Image> arrayListImage = new ArrayList<Image>();
				
				for (int x=0; x < nlNew.getLength(); x++) {
					Element eNew = (Element) nlNew.item(x);
					
					image = new Image();
					image.setName(parser.getValue(eNew, KEY_IMAGE_NAME));
					image.setPath(parser.getValue(eNew, KEY_IMAGE_PATH));
					
					arrayListImage.add(image);
				}
				
				institucional.setImage(arrayListImage);
				
				arrayInstitucional.add(institucional);
			}
			
			String tmpSpace = "";
			int stringLenght = 2000;
			
			institucional = arrayInstitucional.get(0);
			if (!institucional.getContent().trim().equals("")) {
				//String html = "<div style='padding-top:10px;padding-left:10px;>" + ;
				
				//setWebViewContent(wvBoxMissao, institucional.getContent(), null, false);
				htmlMissao = institucional.getContent();
			} else {
				//setWebViewContent(wvBoxMissao, null, null, false);
				htmlMissao = null;
				//wvBoxTexto1.setMinimumHeight(700);
				tmpSpace += "<br/><br/>";
				stringLenght = 3000;
			}
	        
	        
	        institucional = arrayInstitucional.get(1);
	        image = institucional.getImage().get(0);
	        
	        if (institucional.getContent().length() > 2000) {
	        	readedFromFile = institucional.getContent().substring(0, stringLenght);
	        	
	        	readedFromFile = tmpSpace + readedFromFile;
		        
		        divisor = readedFromFile.lastIndexOf(".") + 1;
		        
		        readedFromFile = readedFromFile.substring(0, divisor);
		        if (!image.getPath().trim().equals("")) {
					//setWebViewContent(wvBoxTexto1, readedFromFile, image.getPath(), true);
		        	htmlVisao1 = readedFromFile;
		        	htmlImage = image.getPath();
				} else {
					//setWebViewContent(wvBoxTexto1, readedFromFile, null, true);
					htmlVisao1 = readedFromFile;
		        	htmlImage = null;
				}
		        
		        readedFromFile = institucional.getContent().substring(divisor);
		        
		        divisor = readedFromFile.lastIndexOf(".") + 1;
		        
		        readedFromFile = readedFromFile.substring(0, divisor);
		        readedFromFile = readedFromFile.replaceFirst("<br><br>", "");
		        readedFromFile = "<div id=\"box_texto\">" + readedFromFile; 
		        //setWebViewContent(wvBoxTexto2, readedFromFile, null, false);
		        htmlVisao2 = readedFromFile;
	        } else {
	        	readedFromFile = institucional.getContent();
	        	
	        	if (!image.getPath().trim().equals("")) {
//					setWebViewContent(wvBoxTexto1, readedFromFile, image.getPath(), true);
	        		htmlVisao1 = readedFromFile;
		        	htmlImage = image.getPath();
				} else {
//					setWebViewContent(wvBoxTexto1, readedFromFile, null, true);
					htmlVisao1 = readedFromFile;
		        	htmlImage = null;
				}
	        }
	        
			return null;
	    }

	    @Override
	    protected void onPostExecute(Void result) {
	        super.onPostExecute(result);
	        
	        setWebViewContent(wvBoxMissao, htmlMissao, null, false);
	        setWebViewContent(wvBoxTexto1, htmlVisao1, htmlImage, true);
	        setWebViewContent(wvBoxTexto2, htmlVisao2, null, false);
	        
	        tvTitle.setText(institucionalTitle);
	        
	        wvBoxMissao.setVisibility(View.VISIBLE);
	        wvBoxTexto1.setVisibility(View.VISIBLE);
	        wvBoxTexto2.setVisibility(View.VISIBLE);
	        
	        iv.setVisibility(View.INVISIBLE);
	    }
	}
}
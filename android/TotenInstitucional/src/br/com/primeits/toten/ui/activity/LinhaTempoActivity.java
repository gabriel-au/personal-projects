package br.com.primeits.toten.ui.activity;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import br.com.primeits.toten.utils.Util;
import br.com.primeits.toten.utils.XMLParser;
import br.com.primeits.toten.vo.Image;
import br.com.primeits.toten.vo.TotenInstitucional;

public class LinhaTempoActivity extends Activity {

	public final String KEY_ITEM = "item";
	public final String KEY_ID = "id";
	public final String KEY_YEAR = "year";
	public final String KEY_TITLE = "title";
	public final String KEY_IMAGE = "image";
	public final String KEY_IMAGE_NAME = "name";
	public final String KEY_IMAGE_PATH = "path";
	public final String KEY_CONTENT = "content";
	
	private Context context;
	private LinearLayout linearLayout;
	private RelativeLayout relativeLayout;
	private TotenInstitucional linhaTempo;
	private Image image;
	private ArrayList<TotenInstitucional> arrayLinhaTempo;
	private WebView wvBoxContent;
	
	private String textContent = "";
	private String lastTopic = "";
	private int positionContent = 5;
	private int positionBlockCenter = 40;
	private int positionBlockTopic = 105;
	private int positionBlockInner = 95;
	private int positionDif = 50;
	private boolean leftContent = true;
	
//	private ArrayList<TotenInstitucional> arrayLinhaTempo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linha_tempo);
		this.context = this;
		
		getWindow().getDecorView().setSystemUiVisibility(8);
		
		Bitmap bitmap = null;
		
//		try {
			bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/totenres/content/media/structure/fundo_internas.jpg");
//		} catch (OutOfMemoryError ome) {
//    		reloadActivity();
//    	}
		
		linearLayout = (LinearLayout) findViewById(R.id.linearLayoutLinhaTempo);

		if (bitmap != null) { 
        	linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }
		
		relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutContentLinhaTempo);
		wvBoxContent = (WebView) findViewById(R.id.webviewBoxContent);
		
		new PrefetchData().execute();
		
		ImageButton button2 = (ImageButton) findViewById(R.id.button2);        
		button2.setOnClickListener(projetosListener);
		
		ImageButton button3 = (ImageButton) findViewById(R.id.button3);        
		button3.setOnClickListener(galeriaFotosListener);
		
		ImageButton button4 = (ImageButton) findViewById(R.id.button4);        
		button4.setOnClickListener(galeriaVideosListener);
		
		ImageButton button5 = (ImageButton) findViewById(R.id.button5);        
        button5.setOnClickListener(institucionalListener);
		
		ImageButton buttonBack = (ImageButton) findViewById(R.id.buttonBack);        
        buttonBack.setOnClickListener(backListener);
        
	}
	
	private String addContent(TotenInstitucional linhaTempo) {
		String textContent = "";
		
		if (!this.lastTopic.equals(linhaTempo.getYear().trim())) {
			this.lastTopic = linhaTempo.getYear().trim();
			this.leftContent = !leftContent;
			//this.positionContent += 30;
			
			textContent += "<div id='block_center' style='top:" + (this.positionContent+positionBlockCenter) + "px'>" + linhaTempo.getYear().trim() + "</div>";
		} else {
			this.positionContent -= 50;
		}
		
		textContent += "<div id='block_topic' style='top:" + (this.positionContent+positionBlockTopic) + "px'></div>";
		
		if (leftContent) {
			textContent += "<div id='block_left' style='top:" + (this.positionContent+positionBlockInner) + "px'>"
				+ "<div id='horizontal_line_left'></div>"
				+ "<div id='block_content_left'>";
		} else {
			textContent += "<div id='block_right' style='top:" + (this.positionContent+positionBlockInner) + "px'>"
					+ "<div id='horizontal_line_right'></div>"
					+ "<div id='block_content_right'>";
		}
		
		String htmlImages = getImageWithPath(linhaTempo.getImage());
		
		textContent += "<div>";
		
		if (!linhaTempo.getTitle().trim().equals("")) {
			textContent += "<b><u>" + linhaTempo.getTitle() + "</u></b><br/>";
		}
		
		textContent += linhaTempo.getContent() + "</div>"
				+ "<div style='text-align:center'>" + htmlImages + "</div>"
				+ "</div></div></div>";
		
		this.positionContent += positionBlockTopic + positionDif;
		
		this.positionContent += (linhaTempo.getContent().length()/5);
		
		if (!htmlImages.trim().equals("")) {
			this.positionContent += 120;
		}
		
		return textContent;
	}
	
	private String getImageWithPath(ArrayList<Image> images) {
		String image = "";
		
		if (images != null) {
			for (int i=0; i < images.size(); i++) {
				if (!images.get(i).getPath().trim().equals("")) {
		        	image +=  "<img src=\"file:///" + Environment.getExternalStorageDirectory() + "/totenres/content/media/linha_do_tempo/imgs/"
		        			+ images.get(i).getPath() + "\" align=\"center\" style=\"width:150px;height:auto;padding-top:20px;padding-left:10px;\">";
		        } else {
		        	image += "";
		        }
			}
		}
		
		return image;
	}
	
	private String getHtmlFromText(String content) {
		String htmlContent = "<html><head><meta http-equiv='Content-Type' content='text/html;charset=utf-8' >"
				+"<link href='file:///" + Environment.getExternalStorageDirectory() + "/totenres/content/css/linha_do_tempo.css' type='text/css' rel='stylesheet'></head>"
				+ "<body style='background-color:transparent;'>" 
				+ "<div id='content_body'>"
				+ "<div id='vertical_line' style='height:" + (this.positionContent+50) + "px;margin-top:5px;'></div>"
				+ "<div id='block_topic' style='top:5px;'></div>"
				+ content
				+ "<div id='block_topic' style='top:" + (this.positionContent+50) + "px;'></div>"
				+ "</div>"
				+ "</body></html>";
		
		//System.out.println("HTML: " + htmlContent);
		
		return htmlContent;
	}
	
	private OnClickListener projetosListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(LinhaTempoActivity.this, ProjetosActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener galeriaFotosListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(LinhaTempoActivity.this, GaleriaFotosActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener galeriaVideosListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(LinhaTempoActivity.this, GaleriaVideosActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener institucionalListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(LinhaTempoActivity.this, InstitucionalActivity.class);
			startActivity(i);
			finish();
		}
	};
    
    private OnClickListener backListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.linha_tempo, menu);
		return true;
	}
	
	@Override
    protected void onDestroy() {
	    super.onDestroy();
	
	    new Util().unbindDrawables(linearLayout);
	    
//	    System.gc();
    }
    
    private void reloadActivity() {
		//finish();
		
		Intent i = new Intent(context, context.getClass());
		startActivity(i);
		
		finish();
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
	        
	        String xml = util.readFromFile("totenres/content/xml/linha_do_tempo.xml");
	        
	        Document doc = parser.getDomElement(xml);
			NodeList nl = doc.getElementsByTagName(KEY_ITEM);
			
			arrayLinhaTempo = new ArrayList<TotenInstitucional>();
			
			for (int i=0; i < nl.getLength(); i++) {
				Element e = (Element) nl.item(i);
				
				linhaTempo = new TotenInstitucional();
				linhaTempo.setId(parser.getValue(e, KEY_ID));
				linhaTempo.setYear(parser.getValue(e, KEY_YEAR));
				linhaTempo.setTitle(parser.getValue(e, KEY_TITLE));
				linhaTempo.setContent(parser.getValue(e, KEY_CONTENT));
				
				NodeList nlNew = e.getElementsByTagName(KEY_IMAGE);
				
				ArrayList<Image> arrayListImage = new ArrayList<Image>();
				
				for (int x=0; x < nlNew.getLength(); x++) {
					Element eNew = (Element) nlNew.item(x);
					
					image = new Image();
					image.setName(parser.getValue(eNew, KEY_IMAGE_NAME));
					image.setPath(parser.getValue(eNew, KEY_IMAGE_PATH));
					
					arrayListImage.add(image);
				}
				
				linhaTempo.setImage(arrayListImage);
				
				textContent += addContent(linhaTempo);
				
				arrayLinhaTempo.add(linhaTempo);
			}
			
			return null;
	    }

	    @Override
	    protected void onPostExecute(Void result) {
	        super.onPostExecute(result);
	        
	        wvBoxContent.loadDataWithBaseURL(null, getHtmlFromText(textContent), "text/html", "utf-8", null);
			
			wvBoxContent.setBackgroundColor(0x00000000);
			wvBoxContent.setHorizontalScrollBarEnabled(false);
			wvBoxContent.setVerticalScrollBarEnabled(true);
			wvBoxContent.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
			wvBoxContent.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
			wvBoxContent.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
	        
	        wvBoxContent.setVisibility(View.VISIBLE);
	        iv.setVisibility(View.INVISIBLE);
	    }
	}

}

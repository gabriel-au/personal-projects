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
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import br.com.primeits.toten.utils.Util;
import br.com.primeits.toten.utils.XMLParser;
import br.com.primeits.toten.vo.Image;
import br.com.primeits.toten.vo.TotenInstitucional;

public class ProjetosActivity extends Activity {

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
	private TotenInstitucional projeto;
	private TotenInstitucional projetoSelected;
	private Image image;
	private ArrayList<TotenInstitucional> arrayProjeto;
	private ArrayList<Image> arrayImage;
	private WebView wvBoxContent;
	
	private Button buttonTemp;
	private Button button;
	private ArrayList<Button> arrayButton;
	
	private int buttonY = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_projetos);
		this.context = this;
		
		getWindow().getDecorView().setSystemUiVisibility(8);
		
		Bitmap bitmap = null; 
		
//		try {
			bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/totenres/content/media/structure/fundo_internas.jpg");
//		} catch (OutOfMemoryError ome) {
//    		reloadActivity();
//    	}
		
		linearLayout = (LinearLayout) findViewById(R.id.linearLayoutProjetos);

		if (bitmap != null) { 
        	linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }
		
		relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutContentProjetos);
		wvBoxContent = (WebView) findViewById(R.id.webviewBoxContentProjeto);
		
		new PrefetchData().execute();
		
		ImageButton button1 = (ImageButton) findViewById(R.id.button1);        
        button1.setOnClickListener(linhaTempoListener);
		
		ImageButton button3 = (ImageButton) findViewById(R.id.button3);        
		button3.setOnClickListener(galeriaFotosListener);
		
		ImageButton button4 = (ImageButton) findViewById(R.id.button4);        
		button4.setOnClickListener(galeriaVideosListener);
		
		ImageButton button5 = (ImageButton) findViewById(R.id.button5);        
        button5.setOnClickListener(institucionalListener);
		
		ImageButton buttonBack = (ImageButton) findViewById(R.id.buttonBack);        
        buttonBack.setOnClickListener(backListener);
        
	}
	
	private void setWebViewContent(WebView wv, String title, String file, ArrayList<Image> images) {
        
		String imgReaded = "";
		
        if (images != null) {
        	for (int i = 0; i < images.size(); i++) {
        		this.image = images.get(i);
        		imgReaded += "<img src=\"file:///" + Environment.getExternalStorageDirectory() + "/totenres/content/media/projetos/imgs/"
        				+ image.getPath() + "\" align=\"center\" style=\"width:310px;height:auto;padding-right:15px;padding-top:10px;\">";
        	}
        }
        
		String text = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" >"
			+"<link href=\"file:///" + Environment.getExternalStorageDirectory() + "/totenres/content/css/projetos.css\" type=\"text/css\" rel=\"stylesheet\"></head>"
			+ "<body style=\"background-color:transparent;\">" //<p align=\"justify\">" 
			+ "<div id=\"content_title\"><div id=\"content_title_text\">" + title + "</div></div>"
			+ "<div id=\"content_body\" style='text-align:justify;'>" + file + "</div>"
			+ "<div id='content_body'>" + imgReaded + "</div>" //style=\"position:absolute;;margin\"
			+ "</body></html>";
		
		wv.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
		
		wv.setBackgroundColor(0x00000000);
		wv.setHorizontalScrollBarEnabled(false);
		wv.setVerticalScrollBarEnabled(true);
		wv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
		
		wv.setOnTouchListener(new View.OnTouchListener() {
		    public boolean onTouch(View v, MotionEvent event) {
		      return (event.getAction() == MotionEvent.ACTION_MOVE);
		    }
		});
		
		wvBoxContent.setVisibility(View.VISIBLE);
    }
	
	private void selectProject(int id) {
		this.projetoSelected = arrayProjeto.get(id);
		
		if (projetoSelected != null && wvBoxContent != null) {
			setWebViewContent(wvBoxContent, projetoSelected.getTitle(), projetoSelected.getContent(), projetoSelected.getImage());
		}
	}
	
	private void selectProject(View v) {
		this.projetoSelected = arrayProjeto.get(v.getId());
		
		if (projetoSelected != null && wvBoxContent != null) {
			setWebViewContent(wvBoxContent, projetoSelected.getTitle(), projetoSelected.getContent(), projetoSelected.getImage());
		}
	}
	
	private OnClickListener linhaTempoListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(ProjetosActivity.this, LinhaTempoActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener galeriaFotosListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(ProjetosActivity.this, GaleriaFotosActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener galeriaVideosListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(ProjetosActivity.this, GaleriaVideosActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	private OnClickListener institucionalListener = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new  Intent(ProjetosActivity.this, InstitucionalActivity.class);
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
		getMenuInflater().inflate(R.menu.projetos, menu);
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
			wvBoxContent.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
			wvBoxContent.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
			wvBoxContent.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

	        relativeLayout.addView(iv);
	        
	    }

	    @Override
	    protected Void doInBackground(Void... params) {
	    	XMLParser parser = new XMLParser();
	        Util util = new Util();
	        
	        // TODO substituir &amp; por & no texto
	        String xml = util.readFromFile("totenres/content/xml/projetos.xml");
	        
	        Document doc = parser.getDomElement(xml);
			NodeList nl = doc.getElementsByTagName(KEY_ITEM);
			
			arrayProjeto = new ArrayList<TotenInstitucional>();
			arrayButton = new ArrayList<Button>();
			
			for (int i=0; i < nl.getLength(); i++) {
				Element e = (Element) nl.item(i);
				
				projeto = new TotenInstitucional();
				projeto.setId(parser.getValue(e, KEY_ID));
				projeto.setTitle(parser.getValue(e, KEY_TITLE).replaceAll("&amp;", "&"));
				projeto.setImagePath(parser.getValue(e, KEY_IMAGE));
				projeto.setContent(parser.getValue(e, KEY_CONTENT));
				
				
				RelativeLayout.LayoutParams paramsButton = new RelativeLayout.LayoutParams(360, 70);
				paramsButton.leftMargin = 20;
				paramsButton.topMargin = buttonY;
				
				button = new Button(context);
				button.setText(projeto.getTitle());
				button.setTextColor(Color.parseColor("#FFFFFF"));
				button.setTextSize(16);
				button.setTypeface(Typeface.SANS_SERIF);
				button.setMinHeight(70);
				button.setPadding(10, 7, 10, 7);
				button.setId(i);
				button.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_button_gray));
				button.setLayoutParams(paramsButton);
				button.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
				button.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						selectProject(v);
					}
				});
								
				buttonY += button.getLayoutParams().height;
				arrayButton.add(button);
				
				buttonY += 10;
				
				NodeList nlNew = e.getElementsByTagName(KEY_IMAGE);
				
				ArrayList<Image> arrayListImage = new ArrayList<Image>();
				
				for (int x=0; x < nlNew.getLength(); x++) {
					Element eNew = (Element) nlNew.item(x);
					
					String strImgPath = parser.getValue(eNew, KEY_IMAGE_PATH).trim();
					
					if (!strImgPath.equals("")) {
						image = new Image();
						image.setName(parser.getValue(eNew, KEY_IMAGE_NAME));
						image.setPath(parser.getValue(eNew, KEY_IMAGE_PATH));
						arrayListImage.add(image);
					}
				}
				
				if (arrayListImage.size() == 0) {
					arrayListImage = null;
				}
				
				projeto.setImage(arrayListImage);
				
				
				
				arrayProjeto.add(projeto);
			}
			
			return null;
	    }

	    @Override
	    protected void onPostExecute(Void result) {
	        super.onPostExecute(result);
	        
	        for (int i=0; i < arrayButton.size(); i++) {
	        	relativeLayout.addView(arrayButton.get(i));
	        }
	        
	        selectProject(0);
	        
	        iv.setVisibility(View.INVISIBLE);
	    }
	}

}

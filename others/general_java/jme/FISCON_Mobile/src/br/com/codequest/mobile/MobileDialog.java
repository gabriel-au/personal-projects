package br.com.codequest.mobile;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.j4me.ui.Dialog;
import org.j4me.ui.Theme;
import org.j4me.ui.UIManager;
import org.j4me.ui.components.Component;
import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;
import org.j4me.ui.components.TextBox;

import br.com.codequest.mobile.ui.ErroDialog;
import br.com.codequest.mobile.ui.Logout;
import br.com.codequest.mobile.ui.MensagemDialog;
import br.com.codequest.mobile.ui.components.TextNumerico;
import br.com.codequest.mobile.util.StringUtils;

public abstract class MobileDialog extends Dialog {
	
	/**imagem padrão do logotipo */
	protected static Picture picture;

	/** tela anterior */
	private MobileDialog prevScreen;
	/** tela posterior */
	private MobileDialog lastScreen;
	
	/**mapear os parametros da tela*/
	private Hashtable mapa = new Hashtable();
	
	private String menuLeft;
	private String menuRight;
	
	protected static String SAIR="Sair"; 
	protected static String VOLTAR="Voltar"; 
	protected static String ENTRAR="Entrar";
	
	private Alerta alerta;
	
	//protected static final Logger log = LoggerFactory.getLogger(AutoInfracaoFoto1.class);
	/*public void configureMicroLog()
	{
		BluetoothSerialAppender bluetoothSerialAppender = new BluetoothSerialAppender("btspp://001F81000250:3;authenticate=false;encrypt=false;master=false");
		PatternFormatter parFormatter = new PatternFormatter();
		parFormatter.setPattern("%d %c [%P] %m %T");
		bluetoothSerialAppender.setFormatter(parFormatter);
		log.addAppender(bluetoothSerialAppender);
	}*/
	/**
	 * Construtor padrão
	 */
	public MobileDialog() {
	}
	public MobileDialog(String titulo) {
		//configureMicroLog();
		initDialog(titulo);
	}

	private void initDialog(String titulo) {
		setMargin(0);
		setSpacing(0);
		definirMenu();
		adicionarLogo();
		setTitulo(titulo);		

		init();
	}

	//******Métodos de implementação obrigatórios
	protected abstract void init();

	//******Métodos de Layout
	protected void definirMenu() {
		setMenuText("Voltar", "Próximo");
		invalidate();
	}
	public void setMenuText(String left, String right){
		menuLeft = left;
		menuRight = right;
		super.setMenuText(left, right);
	}
	
	protected void setTitulo(String titulo) {
		if(titulo!=null){
			Label lbTitulo =new Label(titulo); 
			lbTitulo.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
			lbTitulo.setHorizontalAlignment(Graphics.HCENTER);
			append(lbTitulo);
		}
	}
	protected void adicionarLogo() {
		try {
			if (picture == null) {
				picture = new Picture();
				picture.setImage(Image.createImage("/icones/LogoFiscon5.png"));
				picture.setHorizontalAlignment(Graphics.LEFT);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		//append(picture);
	}
	public void append(Component component) {
		super.append(component);
		controleMapeamento(component);
	}
	public void append(Component component, boolean foco) {
		super.append(component);
		controleMapeamento(component);
		setSelected(component);
	}
    public void appendPicture(String src, int alignment) {
    	Picture picture = new Picture();
        
        try {
            picture.setImage(Image.createImage(src));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        picture.setHorizontalAlignment(alignment);

        append(picture);
    }
    public void appendPassword(String label) {
        TextNumerico textBoxPassword = new TextNumerico(label);
        textBoxPassword.setForNumericOnly();
        textBoxPassword.setPassword(true);
        append(textBoxPassword);
    }
	//*****Métodos de manipulção de eventos
	/**
	 * ler e executar eventos com o resultado das teclas prescionadas
	 */
	public void keyPressed(int keyCode) {
		if(alerta!=null && alerta.isExibir() ){
			if(alerta.isMensagem() ){
				removeAlert();
				invalidate();
				repaint();
			}
			return;
		}
		
		switch (keyCode) {
		case (MENU_LEFT): {
			executarLeft();
			break;
		}
		case (MENU_RIGHT): {
			executarRight();
			break;
		}
		default: {
			super.keyPressed(keyCode);
		}
		}

	}
	/**
	 * chamado quando executa o botão sair "Exit".
	 */
	public void sair() {
		MobileMain.exit();
	}
	public MobileDialog getPrevScreen() {
		return prevScreen;
	}

	public void setPrevScreen(MobileDialog prevScreen) {
		this.prevScreen = prevScreen;
	}

	public MobileDialog getLastScreen() {
		return lastScreen;
	}

	public void setLastScreen(MobileDialog lastScreen) {
		this.lastScreen = lastScreen;
	}

	
	public void executarLeft() {
		if(menuLeft.equals(SAIR)){
			Logout l = new Logout();
			l.setPrevScreen(this);
			l.show();
		}else if (getPrevScreen() != null) {
			getPrevScreen().show();
		}
	}

	public void executarRight() {
		if(menuRight.equals(SAIR)){
			Logout l = new Logout();
			l.setPrevScreen(this);
			l.show();
		}else if (getLastScreen() != null) {
			getLastScreen().show();
		}
	}
	
	//*****controle de mapeamentos de atributos
	/**
	 * criar o mapeamentos dos objetos das telas
	 */
	public void controleMapeamento(Component component) {
		if(component instanceof TextBox){
			if(((TextBox) component).getLabel()!=null){
				mapa.put(StringUtils.calculaStringCanonica(((TextBox) component).getLabel()), component);
			}
		}
	}
	
	public Hashtable getMapeamentoAtributos(){
		Hashtable valores = new Hashtable();
		
		Enumeration enumeration = mapa.keys();
		while (enumeration.hasMoreElements()) {
			String nome = (String) enumeration.nextElement();
			
			valores.put(nome, ((TextBox)mapa.get(nome)).getString());
		}
		
		return valores;
	}
	
	public void showMensagem(String titulo, String texto, MobileDialog form) {
		new MensagemDialog(titulo,texto, form).show();
	}
	public void showMensagemErro(String titulo, String texto, MobileDialog form) {
		new ErroDialog(titulo,texto, form).show();
	}
	

	
	public int getTamanho(){
		int heigth=0;
		
		Enumeration e = components();
		
		while ( e.hasMoreElements() )
		{
			Component c = (Component)e.nextElement();
			
			if(c instanceof Label){
				heigth +=((Label) c).getFont().getHeight();	
			}else if(c instanceof Picture){
				heigth+=((Picture) c).getImage().getHeight();				
			}else{
				heigth +=c.getHeight();
			}
			
			
		}
		
		return heigth;
	}
	
	public void carregarTela(Hashtable objeto){
		
	}
	
	public void ativarScroll(boolean down){
		scroll(down);
	}
	
	public void showAlert(String titulo, String mensagem, ControllThread controll){
		alerta = new Alerta(titulo, mensagem);
		alerta.exibir();
		repaint();
		controll.executar();
	}
	public void showAlert(ControllThread controll){
		alerta = new Alerta("Aguarde", "Em Processamento..","/icones/aguarde.png", false);
		alerta.exibir();
		repaint();
		controll.executar();
	}
	
	public void showMensagem(String titulo, String texto) {
		alerta = new Alerta(titulo, texto,"/icones/transacao_efetivada.png", true);
		alerta.exibir();
		repaint();
	}
	public void showMensagemErro(String titulo, String texto) {
		alerta = new Alerta(titulo, texto,"/icones/transacao_erro.png", true);
		alerta.exibir();
		repaint();
	}
	
	public void removeAlert(){
		alerta.fechar();
	}
	
	protected synchronized void paint(Graphics g) {
		super.paint(g);
		if(alerta!=null && alerta.isExibir()){
			alerta.paint(g, this);
		}
	}
}


class Alerta extends Label{
	
	private String mensagem;
	private String titulo;
	private Image imagem;
	private boolean exibir;
	private boolean tipoMensagem;
	
	public Alerta(String titulo, String mensagem) {
		this.titulo = titulo;
		this.mensagem = mensagem;
	}
	public Alerta(String titulo, String mensagem, String srcImage, boolean tipoMensagem) {
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.tipoMensagem = tipoMensagem;
		try {
			this.imagem= Image.createImage(srcImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exibir(){
		exibir = true;
	}
	
	public void paint(Graphics g, MobileDialog dialog) {
		
		Theme theme = UIManager.getTheme();
		Font f = theme.getFont();
		
		int width = dialog.getWidth();
		int height = dialog.getHeight();
		int fontHeight = f.getHeight();
		
		int offset=2;
		int largura = 10;
		
		//limpar a área total
		g.setColor(theme.getBackgroundColor());
		g.fillRect(largura, largura , width-largura*2, height-largura*2);
		
		//borda e caixa com texto
		g.setColor(theme.getBorderColor());
		g.fillRect(largura, largura, width-largura*2, fontHeight+offset*2);
		g.drawRect(largura, largura, width-largura*2, height-largura*2);
		
		g.setColor(theme.getBackgroundColor());
		int size = f.charsWidth(titulo.toCharArray(), 0, titulo.length());
		g.drawString(titulo, (width-size)/2 , largura+offset , 0 );
		
		//caixa da mensagem
		g.setColor(theme.getBorderColor());
		int topo = fontHeight+offset+largura;
		int y =topo+offset;
		
		String [] lines = breakIntoLines( f, mensagem, width-largura*2 - offset*2 );
		//g.drawString(mensagem, largura+offset , topo+offset , 0 );
		
		for ( int i = 0; i < lines.length; i++ )
		{
			g.drawString( lines[i], largura+offset, y, 0 );
				
			y += (fontHeight+offset*2);
		}
		//pular uma linha
		y += (fontHeight+offset*2);
		
		if(imagem!=null){
			g.drawImage(imagem,(width - imagem.getWidth())/2, y, 0);
		}
		
	}

	public boolean isMensagem(){
		return tipoMensagem;
	}
	
	public boolean isExibir() {
		return exibir;
	}
	public void fechar() {
		exibir=false;
	}
	
	
}

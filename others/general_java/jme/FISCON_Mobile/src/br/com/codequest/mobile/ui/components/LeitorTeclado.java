package br.com.codequest.mobile.ui.components;


public class LeitorTeclado extends Thread {

	private int tempo = 3000;
	public static boolean iniciar;
	public static int ultimaTecla;
	public static int tempoRestante;
	public static int contador;
	private static int acento=0;

	public void run() {
		tempoRestante = tempo;
		while (iniciar) {
			try {
				Thread.sleep(100);
				tempoRestante -= 100;
				if (tempoRestante <= 0)
					iniciar = false;
			} catch (InterruptedException e) {
			}
		}
	}

	public static String getTeclaFinal(String contents, int keyCode) {
		if (ultimaTecla == keyCode && iniciar) {
			return getTecla(contents, keyCode);
		}else{
			return contents + new Character((char) keyCode).toString();
		}
		
	}
	
	public static String getTecla(String contents, int keyCode) {

		if (keyCode >= 50 && keyCode <= 57) {

			if (ultimaTecla == keyCode && iniciar) {
				contents = contents.substring(0, contents.length() - 1);
				contador++;
			} else {
				iniciar = true;
				LeitorTeclado l = new LeitorTeclado();
				l.start();
				contador = 0;
				ultimaTecla = keyCode;
			}
			int somador = keyCode + contador;
			switch (keyCode) {
			case 50:// a=65, b, c=67
				if (somador >= 53) {
					somador = keyCode + 15;
					contador = 0;
				} else {
					somador += 15;
				}
				break;
			case 51:// d=68, e, f=70
				if (somador >= 54) {
					somador = keyCode + 17;
					contador = 0;
				} else {
					somador += 17;
				}
				break;
			case 52:// g=71, h, i=73
				if (somador >= 55) {
					somador = keyCode + 19;
					contador = 0;
				} else {
					somador += 19;
				}
				break;
			case 53:// j=74, k, l=76
				if (somador >= 56) {
					somador = keyCode + 21;
					contador = 0;
				} else {
					somador += 21;
				}
				break;
			case 54:// m, n=78, o=79
				if (somador >= 57) {
					somador = keyCode + 23;
					contador = 0;
				} else {
					somador += 23;
				}
				break;
			case 55:// p=80, q=81, r, s=83
				if (somador >= 59) {
					somador = keyCode + 25;
					contador = 0;
				} else {
					somador += 25;
				}
				break;
			case 56:// t=84, u, v=86
				if (somador >= 60) {
					somador = keyCode + 28;
					contador = 0;
				} else {
					somador += 28;
				}
				break;
			case 57:// w=87, x, y, z=90
				if (somador >= 62) {
					somador = keyCode + 30;
					contador = 0;
				} else {
					somador += 30;
				}
				break;
			default:
				break;
			}

			keyCode = somador;
			return contents + new Character((char) keyCode).toString();

		} else if (keyCode >= 49) {
			iniciar = false;
			return contents + new Character((char) keyCode).toString();
		} else {
			iniciar = false;
			return contents;
		}
		/*´ = 61441
` = 61442
~ = 61443
^ = 61444
a = 97
e = 101
i = 105
o = 111
u = 117
A = 65
E = 69 
I = 73
O = 79
U = 85
		String a[] = {"á", "à", "ã", "â"};
		String e[] = {"é", "è", "ê"};
		String i[] = {"í", "ì", "î"};
		String o[] = {"ó", "ò", "õ", "ô"};
		String u[] = {"ú", "ù", "û"};
		Hashtable letrasAcentuadas = new Hashtable();
		
		letrasAcentuadas.put(new Integer(61441+97), a[0]);
		letrasAcentuadas.put(new Integer(61442+97), a[1]);
		letrasAcentuadas.put(new Integer(61443+97), a[2]);
		letrasAcentuadas.put(new Integer(61444+97), a[3]);
		
		letrasAcentuadas.put(new Integer(61441+65), a[0].toUpperCase());
		letrasAcentuadas.put(new Integer(61442+65), a[1].toUpperCase());
		letrasAcentuadas.put(new Integer(61443+65), a[2].toUpperCase());
		letrasAcentuadas.put(new Integer(61444+65), a[3].toUpperCase());
		
		letrasAcentuadas.put(new Integer(61441+101), e[0]);
		letrasAcentuadas.put(new Integer(61442+101), e[1]);
		letrasAcentuadas.put(new Integer(61444+101), e[2]);
		                                             
		letrasAcentuadas.put(new Integer(61441+69), e[0].toUpperCase());
		letrasAcentuadas.put(new Integer(61442+69), e[1].toUpperCase());
		letrasAcentuadas.put(new Integer(61444+69), e[2].toUpperCase());
		
		letrasAcentuadas.put(new Integer(61441+105), i[0]);
		letrasAcentuadas.put(new Integer(61442+105), i[1]);
		letrasAcentuadas.put(new Integer(61444+105), i[2]);
		
		letrasAcentuadas.put(new Integer(61441+73), i[0].toUpperCase());
		letrasAcentuadas.put(new Integer(61442+73), i[1].toUpperCase());
		letrasAcentuadas.put(new Integer(61444+73), i[2].toUpperCase());
		
		letrasAcentuadas.put(new Integer(61441+111), o[0]);
		letrasAcentuadas.put(new Integer(61442+111), o[1]);
		letrasAcentuadas.put(new Integer(61443+111), o[2]);
		letrasAcentuadas.put(new Integer(61444+111), o[3]);
		
		letrasAcentuadas.put(new Integer(61441+79), o[0].toUpperCase());
		letrasAcentuadas.put(new Integer(61442+79), o[1].toUpperCase());
		letrasAcentuadas.put(new Integer(61443+79), o[2].toUpperCase());
		letrasAcentuadas.put(new Integer(61444+79), o[3].toUpperCase());
		
		letrasAcentuadas.put(new Integer(61441+117), u[0]);
		letrasAcentuadas.put(new Integer(61442+117), u[1]);
		letrasAcentuadas.put(new Integer(61444+117), u[2]);
		
		letrasAcentuadas.put(new Integer(61441+85), u[0].toUpperCase());
		letrasAcentuadas.put(new Integer(61442+85), u[1].toUpperCase());
		letrasAcentuadas.put(new Integer(61444+85), u[2].toUpperCase());
		
		
		if(keyCode>=61441 && keyCode<=61444){
			acento=keyCode;
		}
		
		
		if(keyCode!=8 && !(keyCode>=61441 && keyCode<=61444) && keyCode!=10 && keyCode!=-8){
			if(acento>0){
				String temp;
				if ((temp = (String)letrasAcentuadas.get(new Integer(acento+keyCode))) !=null){
					acento=0;
					return contents + temp;
				}
			}else{
				return contents + new Character((char) keyCode).toString();
			}
		}else if(keyCode==8){
			return contents.substring(0, contents.length()-1);
		}
		return contents;
		*/
	}

}

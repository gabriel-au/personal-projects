package br.com.codequest.mobile.util;



/**
*
* Esta classe é usado para codificar e descodificar dados no formato Base64
*
*/
public class Base64Coder {


private static char[]    map1 = new char[64];
static {
   int i=0;
   for (char c='A'; c<='Z'; c++) map1[i++] = c;
   for (char c='a'; c<='z'; c++) map1[i++] = c;
   for (char c='0'; c<='9'; c++) map1[i++] = c;
   map1[i++] = '+'; map1[i++] = '/'; }

private static byte[]    map2 = new byte[128];
static {
   for (int i=0; i<map2.length; i++) map2[i] = -1;
   for (int i=0; i<64; i++) map2[map1[i]] = (byte)i; }

/**
* Codificar String no formato Base64
* @param s  String à ser codificada.
* @return   String contendo dado codificado em Base64.
*/
public static String encodeString (String s) {
return new String(encode(s.getBytes())); }



/**
* Codifica um array de bytes em formato Base64
* @param in  Um array contendo o dado em array de bytes
* @return    Um array de caracter contando o dado codificado em Base64
*/
public static char[] encode (byte[] in) {
return encode(in, 0, in.length); }

/**
* Codifica um array de bytes em formato Base64
* @param in    Um array contendo o dado em array de bytes
* @param iLen  Numero de bytes a serem processados <code>in</code>.
* @return      Um array de caracter contando o dado codificado em Base64
*/
public static char[] encode (byte[] in, int iLen) {
return encode(in, 0, iLen); }

/**
* Codifica um array de bytes em formato Base64
* @param in    Um array contendo o dado em array de bytes
* @param iOff  Inicio dos primeiro byte a ser processado.
* @param iLen  Numero de bytes a serem processados <code>in</code>, iniciando em <code>iOff</code>.
* @return      Um array de caracter contando o dado codificado em Base64
*/
public static char[] encode (byte[] in, int iOff, int iLen) {
int oDataLen = (iLen*4+2)/3;       // output length without padding
int oLen = ((iLen+2)/3)*4;         // output length including padding
char[] out = new char[oLen];
int ip = iOff;
int iEnd = iOff + iLen;
int op = 0;
while (ip < iEnd) {
   int i0 = in[ip++] & 0xff;
   int i1 = ip < iEnd ? in[ip++] & 0xff : 0;
   int i2 = ip < iEnd ? in[ip++] & 0xff : 0;
   int o0 = i0 >>> 2;
   int o1 = ((i0 &   3) << 4) | (i1 >>> 4);
   int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
   int o3 = i2 & 0x3F;
   out[op++] = map1[o0];
   out[op++] = map1[o1];
   out[op] = op < oDataLen ? map1[o2] : '='; op++;
   out[op] = op < oDataLen ? map1[o3] : '='; op++; }
return out; }

/**
* Decodifica uma string em Base64.
* @param s  Uma string em Base64 a ser decodificada.
* @return   String contendo dado decodificado.
* @throws   IllegalArgumentException Se a entrada não for um dado válido em Base64
*/
public static String decodeString (String s) {
return new String(decode(s)); }

/**
* Decodifica um array de bytes formatado em Base64 e ignora separador de linha, tabs e espaço em branco.
* Esse método é compatível com <code>sun.misc.BASE64Decoder.decodeBuffer(String)</code>.
* @param s  Uma string em Base64 à ser decodificada
* @return   Um array contendo o dado decodificado em bytes
* @throws   IllegalArgumentException Se a entrada não for um dado válido em Base64
*/
public static byte[] decodeLines (String s) {
char[] buf = new char[s.length()];
int p = 0;
for (int ip = 0; ip < s.length(); ip++) {
   char c = s.charAt(ip);
   if (c != ' ' && c != '\r' && c != '\n' && c != '\t')
      buf[p++] = c; }
return decode(buf, 0, p); }

/**
* Decodifica um array de bytes formatado em Base64
* @param s  Uma string em Base64 à ser decodificada
* @return   Um array contendo o dado decodificado em bytes
* @throws   IllegalArgumentException Se a entrada não for um dado válido em Base64
*/
public static byte[] decode (String s) {
return decode(s.toCharArray()); }

/**
* Decodifica um array de bytes formatado em Base64
* @param in  Uma string em Base64 à ser decodificada
* @return    Um array contendo o dado decodificado em bytes
* @throws    IllegalArgumentException Se a entrada não for um dado válido em Base64
*/
public static byte[] decode (char[] in) {
return decode(in, 0, in.length); }

/**
* Decodifica um array de bytes formatado em Base64
* @param in    Um array de caracter contendo o dado codificado em Base64
* @param iOff  Primeiro caracter a ser processado
* @param iLen  Numero de bytes a serem processados <code>in</code>, iniciando em <code>iOff</code>.
* @return      Um array contendo o dado decodificado em bytes
* @throws      IllegalArgumentException If the input is not valid Base64 encoded data.
*/
public static byte[] decode (char[] in, int iOff, int iLen) {
if (iLen%4 != 0) throw new IllegalArgumentException ("Tamanho da entrada em Base64 não é multiplo de 4.");
while (iLen > 0 && in[iOff+iLen-1] == '=') iLen--;
int oLen = (iLen*3) / 4;
byte[] out = new byte[oLen];
int ip = iOff;
int iEnd = iOff + iLen;
int op = 0;
while (ip < iEnd) {
   int i0 = in[ip++];
   int i1 = in[ip++];
   int i2 = ip < iEnd ? in[ip++] : 'A';
   int i3 = ip < iEnd ? in[ip++] : 'A';
   if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127)
      throw new IllegalArgumentException ("Caracter inválido codificado em Base64.");
   int b0 = map2[i0];
   int b1 = map2[i1];
   int b2 = map2[i2];
   int b3 = map2[i3];
   if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
      throw new IllegalArgumentException ("Caracter inválido codificado em Base64.");
   int o0 = ( b0       <<2) | (b1>>>4);
   int o1 = ((b1 & 0xf)<<4) | (b2>>>2);
   int o2 = ((b2 &   3)<<6) |  b3;
   out[op++] = (byte)o0;
   if (op<oLen) out[op++] = (byte)o1;
   if (op<oLen) out[op++] = (byte)o2; }
return out; }

private Base64Coder() {}

} 

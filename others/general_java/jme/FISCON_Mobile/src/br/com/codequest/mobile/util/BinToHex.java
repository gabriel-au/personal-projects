package br.com.codequest.mobile.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BinToHex {
	private final static String[] hexSymbols = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public final static int BITS_PER_HEX_DIGIT = 4;

	public static String toHexFromByte(final byte b) {
		byte leftSymbol = (byte) ((b >>> BITS_PER_HEX_DIGIT) & 0x0f);
		byte rightSymbol = (byte) (b & 0x0f);

		return (hexSymbols[leftSymbol] + hexSymbols[rightSymbol]);
	}
	public static String toHexFromBytesForImage(final InputStream in) throws IOException{
		return toHexFromBytesForImage(toInputStreamFromByte(in));
	}
	
	public static String toHexFromBytesForImage(final byte[] bytes){
		
		if (bytes == null || bytes.length == 0) {
			return ("");
		}

		// there are 2 hex digits per byte
		String hexBuffer = new String();

		// for each byte, convert it to hex and append it to the buffer
		for (int i = 0; i < bytes.length; i++) {
			try{
				Integer.parseInt(toHexFromByte(bytes[i]).substring(0, 1));
						hexBuffer +="0";
			}catch(NumberFormatException e){
				hexBuffer +="F";
			}
			try{
				Integer.parseInt(toHexFromByte(bytes[i]).substring(1, 2));
						hexBuffer +="0";
			}catch(NumberFormatException e){
				hexBuffer +="F";
			}
		}
		return hexBuffer;
	}
	public static String toHexFromBytes(final byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return ("");
		}

		// there are 2 hex digits per byte
		String hexBuffer = new String();

		// for each byte, convert it to hex and append it to the buffer
		for (int i = 0; i < bytes.length; i++) {
			hexBuffer += toHexFromByte(bytes[i]);
		}

		return (hexBuffer.toString());
	}
	public static byte[] toInputStreamFromByte(InputStream in) throws IOException {
		byte[] array = in.toString().getBytes();
		byte[] x = new byte[array.length];
		for (int i = 0; i < array.length; i++) {
			x[i]=array[i];
		}
		return x;
	}
	public static byte[] toInputStreamFromByteArray(InputStream in) throws IOException {
		int x;
		ByteArrayOutputStream out = new ByteArrayOutputStream(); 
		 while ((x = in.read())!= -1) {
			 out.write(x);
		 }
		
		return out.toByteArray();

	}
	
	public static String toByteFromHex(InputStream in) throws IOException {
		String hex = new String();
		int x;
		 while ((x = in.read())!= -1) {
			 hex += Integer.toHexString(x);
		 }
		
		return hex;

	}

}

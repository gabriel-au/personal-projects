package br.com.martins.vendas.services.calculodepreco;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculoUtil {
	
	public static final BigDecimal CEM = BigDecimal.valueOf(100);
	
	/**
	 * Arredonda um valor com 2 casas decimais
	 * @param value
	 * @param scale
	 * @return
	 */
	public static BigDecimal roundUP(BigDecimal value, Integer scale){
		return value.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Arredonda um valor com 2 casas decimais
	 * @param value
	 * @param scale
	 * @return
	 */
	public static BigDecimal roundHalfEven(BigDecimal value, Integer scale){
		return value.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
	}
	
	/**
	 * Arredonda um valor com 2 casas decimais
	 * @param value
	 * @param scale
	 * @return
	 */
	public static BigDecimal roundHalfEven(Double value, Integer scale){
		return roundHalfEven(BigDecimal.valueOf(value), scale);
	}
	
	/**
	 * Arredonda um valor com 2 casas decimais
	 * @param value
	 * @param scale
	 * @return
	 */
	public static BigDecimal roundUP(Double value, Integer scale){
		return BigDecimal.valueOf(value).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
	
	
	/**
	 * Arredonda um valor com 2 casas decimais
	 * @param value
	 * @param scale
	 * @return
	 */
	public static BigDecimal roundDown(Double value, int scale){
		return BigDecimal.valueOf(value).setScale(scale, BigDecimal.ROUND_DOWN);
	}
	
	/**
	 * Arredonda um valor com 2 casas decimais
	 * Semelhante ao double AFX_EXT_CLASS Truncar(double  valor, int  decimais ) da classe Funcoes.cpp
	 * @param value
	 * @param scale
	 * @return
	 */
	public static BigDecimal roundFloor(Double value, int scale){
		int i = 0;
		int elevado = 1;
		while (i < scale){
			elevado *= 10;
			i++;
		}
		return BigDecimal.valueOf(BigDecimal.valueOf(value.doubleValue() * elevado +.5).setScale(0, RoundingMode.FLOOR).doubleValue() / elevado);
	}
	
	/**
	 * Arredonda um valor com 2 casas decimais
	 * Semelhante ao double ROUNDDBL(d, ndec) da classe PrgVahRn.h
	 * @param value
	 * @param scale
	 * @return
	 */
	public static BigDecimal roundDbl(Double d, int ndec){
		return BigDecimal.valueOf((roundDown(d * Math.pow((double) 10, (double) ndec) + .5, 2).doubleValue() / Math.pow((double) 10, (double) ndec))).setScale(ndec, RoundingMode.FLOOR);
	}
	
	/**
	 * 
	 * @param value
	 * @param scale
	 * @return
	 */
	public static BigDecimal roundDown (BigDecimal value, int scale){
		return value.setScale(scale, BigDecimal.ROUND_DOWN);
	}

}

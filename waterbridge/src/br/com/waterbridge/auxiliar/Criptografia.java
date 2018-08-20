package br.com.waterbridge.auxiliar;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
	
	private static String aux1;
	
	private static String aux2;
	
	private static String aux3;
    
    
	
	public static String gerarCriptografiaSHA(String valor) throws NoSuchAlgorithmException, IOException {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(valor.getBytes("UTF-8"));
		 
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		return hexString.toString();
	}
	
	/**
     * Recebe a criptografia em MD5 e modifica com valores aleatorios
     * no meio para gravar no banco.
     */
    public static String gerarCriptografiaMD5(String valor) throws NoSuchAlgorithmException {
         
        String resultado = criptografiaMD5(valor);
         
        aux1 = resultado.substring(0, 10) + (1000 + (int) (9000 * Math.random()));
         
        aux2 = resultado.substring(10, 20) + (1000 + (int) (9000 * Math.random()));
         
        aux3 = resultado.substring(20, resultado.length()) + (1000 + (int) (9000 * Math.random()));
         
        return aux1+aux2+aux3;
    }
	
	/**
     * Gera criptografia original em MD5.
     */
    public static String criptografiaMD5(String criptografia) throws NoSuchAlgorithmException {  
         
        MessageDigest md = MessageDigest.getInstance("MD5");
         
        BigInteger hash = new BigInteger(1, md.digest(criptografia.getBytes()));
         
        String texto = hash.toString(16);
         
        if (texto.length() %2 != 0) {
            texto = "0" + texto;
        }
         
        return texto;
    }
     
    public static String comparaCriptografia(String valor) {
         
        aux1 = valor.substring(0, 10);
         
        aux2 = valor.substring(14, 24);
         
        aux3 = valor.substring(28, valor.length()-4);
         
        return aux1+aux2+aux3;
    }
     
    public static void main(String args[]) throws NoSuchAlgorithmException, IOException {
    	String texto = gerarCriptografiaSHA("13579rafael");
    	System.out.println(texto);
    }
}

package br.com.joao.teste.util;

public class Util {
    public static String formatPessoa(String[] dados) {
    	String format = "";

    	if (!isNullOrEmpty(dados[0])) format += "Name: " + dados[0];
    	if (!isNullOrEmpty(dados[1])) format += ", Surname: " + dados[1];
    	if (!isNullOrEmpty(dados[2])) format += ", Function: " + dados[2];
    	if (!isNullOrEmpty(dados[3])) format += ", Company: " + dados[3];
    	if (!isNullOrEmpty(dados[4])) format += ", Vehicle: " + dados[4];
    	if (!isNullOrEmpty(dados[5])) format += ", Weight: " + dados[5];
    	if (!isNullOrEmpty(dados[6])) format += ", Height: " + dados[6];
    	if (!isNullOrEmpty(dados[7])) format += ", Postal_code: " + dados[7];
    	
    	format += ".";
    	
    	return format.substring(0, 1).equals(",") ? format.substring(1) : format;
    }
    
    public static String formatNamePessoa(String[] dados) {
    	String format = "";

    	if (!isNullOrEmpty(dados[0])) format += dados[0] + " ";
    	if (!isNullOrEmpty(dados[1])) format += dados[1];
    	
    	return format.trim();
    }
    
    public static boolean isNullOrEmpty(String str) {
    	return str == null || str.isEmpty();
    }
    
	public static String extractNumber(String str) {
		if (str == null)
			return null;

		return str.replaceAll("[^0123456789]", "");
	}
}

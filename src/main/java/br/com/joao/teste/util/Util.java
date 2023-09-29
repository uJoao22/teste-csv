package br.com.joao.teste.util;

public class Util {
    public static String formatPessoa(String[] dados) {
    	String format = "";

    	if (!isNullOrEmpty(dados[0])) format += "Nome: " + dados[0];
    	if (!isNullOrEmpty(dados[1])) format += ", Sobrenome: " + dados[1];
    	if (!isNullOrEmpty(dados[2])) format += ", Função: " + dados[2];
    	if (!isNullOrEmpty(dados[3])) format += ", Empresa: " + dados[3];
    	if (!isNullOrEmpty(dados[4])) format += ", Veículo: " + dados[4];
    	if (!isNullOrEmpty(dados[5])) format += ", Peso: " + dados[5] + " kg";
    	if (!isNullOrEmpty(dados[6])) format += ", Altura: " + dados[6] + " cm";
    	if (!isNullOrEmpty(dados[7])) format += ", CEP: " + dados[7];
    	
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

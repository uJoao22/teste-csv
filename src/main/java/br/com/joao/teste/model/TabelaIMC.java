package br.com.joao.teste.model;

import java.util.ArrayList;
import java.util.List;

import br.com.joao.teste.type.FaixaImc;

public class TabelaIMC {
	private int quantidadeMagreza;
	private int quantidadeNormal;
	private int quantidadeSobrepeso;
	private int quantidadeObesidade;
	private int quantidadeObesidadeGrave;
	private List<String> pessoasObesas;
	
	public TabelaIMC() {
		this.pessoasObesas = new ArrayList<String>();
	}
	
	public void calculaIMC(Double peso, Double altura, String nome) {
    	FaixaImc faixa = FaixaImc.defineFaixa(peso / ((altura/100) * (altura/100)));
    	incrementaQuantidade(faixa, nome);
    }
	
	public void incrementaQuantidade(FaixaImc faixa, String nome) {
		if (faixa == null)
			return;
		
		if (this.pessoasObesas == null) 
			this.pessoasObesas = new ArrayList<String>();
		
		switch (faixa) {
			case MAGREZA: 
				quantidadeMagreza++; 
				break;
				
			case NORMAL: 
				quantidadeNormal++; 
				break;
				
			case SOBREPESO: 
				quantidadeSobrepeso++; 
				break;
				
			case OBESIDADE: 
				quantidadeObesidade++; 
				pessoasObesas.add(nome);
				break;
				
			case OBESIDADE_GRAVE: 
				quantidadeObesidadeGrave++; 
				pessoasObesas.add(nome);
				break;
				
			default: break;
		}
	}
	
	public void imprimeDados() {
		System.out.println(String.format("\n%s pessoas estão na faixa da %s.", quantidadeMagreza, FaixaImc.MAGREZA.getLabel()));
		System.out.println(String.format("%s pessoas estão na faixa da %s.", quantidadeNormal, FaixaImc.NORMAL.getLabel()));
		System.out.println(String.format("%s pessoas estão na faixa da %s.", quantidadeSobrepeso, FaixaImc.SOBREPESO.getLabel()));
		System.out.println(String.format("%s pessoas estão na faixa da %s.", quantidadeObesidade, FaixaImc.OBESIDADE.getLabel()));
		System.out.println(String.format("%s pessoas estão na faixa da %s.", quantidadeObesidadeGrave, FaixaImc.OBESIDADE_GRAVE.getLabel()));
		
    	System.out.println("\nNome e sobrenome " + (pessoasObesas.size() == 1 ? "da pessoa obesa:" : "das pessoas obesas:"));
    	System.out.println(pessoasObesas.toString().substring(1, pessoasObesas.toString().length() - 1));
	}
}

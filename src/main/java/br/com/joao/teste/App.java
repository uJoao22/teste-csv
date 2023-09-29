package br.com.joao.teste;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;

import br.com.joao.teste.dto.CepDTO;
import br.com.joao.teste.util.HttpUtil;
import br.com.joao.teste.util.Util;

public class App {
    public static void main(String[] args ) {
        try {
        	CSVReader reader = new CSVReader(new FileReader("database.csv"));
        	List<String[]> list = reader.readAll();

        	Double maiorPeso = 0.00;
        	List<String> listPesos = new ArrayList<String>();

        	TabelaIMC tabelaImc = new TabelaIMC();
        	
        	Map<String, Integer> funcoes = new HashMap<String, Integer>();
        	Map<String, Integer> cidades = new HashMap<String, Integer>();
        	
        	System.out.println("Aguarde...");
        	for (String[] pessoa : list.subList(1, list.size())) {
        		maiorPeso = resolveMaiorPeso(pessoa, maiorPeso, listPesos);

        		if (!Util.isNullOrEmpty(pessoa[5]) && !Util.isNullOrEmpty(pessoa[6]))
        			tabelaImc.calculaIMC(Double.valueOf(pessoa[5]), Double.valueOf(pessoa[6]), Util.formatNamePessoa(pessoa));
        		
        		if (!Util.isNullOrEmpty(pessoa[3]))
        			agrupaInMap(funcoes, pessoa[3]);
        		
        		if (!Util.isNullOrEmpty(pessoa[7])) {
        			CepDTO c = HttpUtil.fetchCEP(Util.extractNumber(pessoa[7]));

        			if (c.getErro() == null || !c.getErro())
        				agrupaInMap(cidades, (c.getLocalidade() + " - " + c.getUf()));
        		}
        	}

        	System.out.println("O maior peso é de: " + maiorPeso + " kg. " + (listPesos.size() == 1 ? "E pertence ao registro:" : "E pertencem aos registros:"));
        	listPesos.forEach(p -> System.out.println(p));
        	
        	tabelaImc.imprimeDados();
        	imprimeFuncaoMaisEncontrada(funcoes);
        	imprimePopulacaoCidade(cidades);
        	
        	reader.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    private static Double resolveMaiorPeso(String[] pessoa, Double maior, List<String> listPesos) {
    	Double maiorPeso = maior;
    	
  	    if (!Util.isNullOrEmpty(pessoa[5]) && (Double.valueOf(pessoa[5]) > maiorPeso || Double.valueOf(pessoa[5]).equals(maiorPeso))) {
	    	listPesos.add(Util.formatPessoa(pessoa));
	    	
	    	if (Double.valueOf(pessoa[5]) > maiorPeso) {
	    		maiorPeso = Double.valueOf(pessoa[5]);
	    		listPesos.clear();
	    		listPesos.add(Util.formatPessoa(pessoa));
	    	}
	  	}

  	    return maiorPeso;
    }
    
    private static void agrupaInMap(Map<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			map.put(key, map.get(key) + 1);
		} else {
			map.put(key, 1);
		}
    }

    private static void imprimeFuncaoMaisEncontrada(Map<String, Integer> funcoes) {
    	Integer maior = 0;
    	List<String> listFuncoes = new ArrayList<String>();
    	
    	for (Map.Entry<String, Integer> pair : funcoes.entrySet()) {
    		if (pair.getValue() > maior || pair.getValue().equals(maior)) {
    			listFuncoes.add(pair.getKey());
    	    	
    	    	if (pair.getValue() > maior) {
    	    		maior = pair.getValue();
    	    		listFuncoes.clear();
    	    		listFuncoes.add(pair.getKey());
    	    	}
    		}
    	}
    	
    	if (listFuncoes.size() == 1) {
    		System.out.println("\nA função que mais se repete, é encontrada " + maior + " vezes. Sendo:");
    	} else {
    		System.out.println("\nAs funções que mais se repetem, são encontradas " + maior + " vezes. São elas:");
    	}
    	
    	System.out.println(listFuncoes.toString().substring(1, listFuncoes.toString().length() - 1));
    }
    
    private static void imprimePopulacaoCidade(Map<String, Integer> cidades) {
    	System.out.print("\n");
    	
    	for (Map.Entry<String, Integer> pair : cidades.entrySet())
    		System.out.println(pair.getKey() + ": " + pair.getValue() + (pair.getValue() == 1 ? " pessoa." : " pessoas."));
    }
}

package br.com.joao.teste;

public enum FaixaImc {
	MAGREZA(0),
	NORMAL(1),
	SOBREPESO(2),
	OBESIDADE(3),
	OBESIDADE_GRAVE(4);
	
	private final int key;

	FaixaImc(int key) {
		this.key = key;
	}

	public String getLabel() {
		switch (key) {
			case 0: return "Magreza";
			case 1: return "Normal";
			case 2: return "Sobrepeso";
			case 3: return "Obesidade";
			case 4: return "Obesidade grave";
		}

		return null;
	}

	public int getKey() {
		return key;
	}
	
	public static FaixaImc defineFaixa(Double imc) {
    	if (imc < 18.5)
    		return FaixaImc.MAGREZA;
    	
    	if (imc >= 18.5 && imc <= 24.9)
    		return FaixaImc.NORMAL;

    	if (imc >= 25.0 && imc <= 29.9)
    		return FaixaImc.SOBREPESO;

    	if (imc >= 30.0 && imc <= 39.9)
    		return FaixaImc.OBESIDADE;
    	
    	if (imc >= 40.0)
    		return FaixaImc.OBESIDADE_GRAVE;
    	
    	return null;
	}
	
	@Override
	public String toString() {
		return getLabel();
	}
}

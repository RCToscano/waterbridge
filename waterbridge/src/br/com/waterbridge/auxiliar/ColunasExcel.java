package br.com.waterbridge.auxiliar;

import java.util.ArrayList;
import java.util.List;

public class ColunasExcel {
	
	
	@SuppressWarnings("serial")
	private List<String> colunasRelPressaoResumo = new ArrayList<String>(){{
	    add("EMPRESA");
	    add("CONDOMINIO");
	    add("BRIDGE");
	    add("DATA_INICIO");
	    add("DATA_FIM");
	}};
	
	@SuppressWarnings("serial")
	private List<String> colunasRelPressaoDados = new ArrayList<String>(){{
		add("Nº");
		add("DATA");
		add("PRESSAO");
		add("BATERIA");
		add("ALARME");
		add("TEMPERATURA");
	}};

	public List<String> getColunasRelPressaoResumo() {
		return colunasRelPressaoResumo;
	}

	public List<String> getColunasRelPressaoDados() {
		return colunasRelPressaoDados;
	}
	
}

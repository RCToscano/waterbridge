package br.com.waterbridge.auxiliar;

import java.util.ArrayList;
import java.util.List;

public class ColunasExcel {
	
	
	@SuppressWarnings("serial")
	private List<String> colunasRelPressaoResumo = new ArrayList<String>(){{
	    add("EMPRESA");
	    add("LOCAL");
	    add("BRIDGE");
	    add("DATA_INICIO");
	    add("DATA_FIM");
	}};
	
	@SuppressWarnings("serial")
	private List<String> colunasRelPressaoDados = new ArrayList<String>(){{
		add("Nº");
		add("DATA");
		add("HORA");
		add("PRESSAO");
		add("BATERIA");
		add("ALARME");
		add("TEMPERATURA");
	}};
	
	@SuppressWarnings("serial")
	private List<String> colunasConsumoUserEmpresa = new ArrayList<String>(){{
		add("Nº");
		add("EMPRESA");
	    add("LOCAL");
		add("DATA");
		add("HORA");
		add("PRESSAO");
		add("BATERIA");
		add("ALARME");
		add("TEMPERATURA");
	}};
	
	@SuppressWarnings("serial")
	private List<String> colunasConsumoEmpresa = new ArrayList<String>(){{
		add("Nº");
		add("EMPRESA");
	    add("BRIDGE");
	}};
	
	@SuppressWarnings("serial")
	private List<String> colunasRelMedidorResumo = new ArrayList<String>(){{
		add("EMPRESA");
		add("LOCAL");
		add("BRIDGE");
		add("MEDIDOR");
		add("DATA_INICIO");
		add("DATA_FIM");
	}};
	
	@SuppressWarnings("serial")
	private List<String> colunasRelMedidorDados = new ArrayList<String>(){{
		add("Nº");
		add("DATA");
		add("HORA");
		add("VOLUME");
		add("PRESSAO");
		add("ALARME");
		add("BATERIA");
		add("TEMPERATURA");
	}};
	
	@SuppressWarnings("serial")
	private List<String> colunasRelCondominioResumo = new ArrayList<String>(){{
		add("EMPRESA");
		add("LOCAL");
		add("DATA_INICIO");
		add("DATA_FIM");
	}};
	
	@SuppressWarnings("serial")
	private List<String> colunasRelCondominioDados = new ArrayList<String>(){{
		add("Nº");
		add("BRIDGE");
		add("MEDIDOR");
		add("ENDERECO");
		add("VOLUME_INICIAL(m³)");
		add("VOLUME_FINAL(m³)");
		add("CONSUMO");
		add("CONSUMIDOR");
	}};

	public List<String> getColunasRelPressaoResumo() {
		return colunasRelPressaoResumo;
	}

	public List<String> getColunasRelPressaoDados() {
		return colunasRelPressaoDados;
	}

	public List<String> getColunasConsumoUserEmpresa() {
		return colunasConsumoUserEmpresa;
	}
	
	public List<String> getColunasConsumoEmpresa() {
		return colunasConsumoEmpresa;
	}

	public List<String> getColunasRelMedidorResumo() {
		return colunasRelMedidorResumo;
	}

	public List<String> getColunasRelMedidorDados() {
		return colunasRelMedidorDados;
	}

	public List<String> getColunasRelCondominioResumo() {
		return colunasRelCondominioResumo;
	}

	public List<String> getColunasRelCondominioDados() {
		return colunasRelCondominioDados;
	}
	
	
}

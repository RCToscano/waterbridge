package br.com.waterbridge.modelo.bo;

import java.util.HashMap;
import java.util.List;

import br.com.waterbridge.relmodelo.RelConsumoPressaoExcel;

public class RelConsumoPressaoExcelBO {
	
	private HashMap<Integer, List<RelConsumoPressaoExcel>> map;
	
	private List<String> abas;

	public HashMap<Integer, List<RelConsumoPressaoExcel>> getMap() {
		return map;
	}

	public void setMap(HashMap<Integer, List<RelConsumoPressaoExcel>> map) {
		this.map = map;
	}

	public List<String> getAbas() {
		return abas;
	}

	public void setAbas(List<String> abas) {
		this.abas = abas;
	}
	
}

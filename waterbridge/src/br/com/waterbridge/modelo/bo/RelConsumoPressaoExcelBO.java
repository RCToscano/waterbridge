package br.com.waterbridge.modelo.bo;

import java.util.HashMap;
import java.util.List;

import br.com.waterbridge.relmodelo.RelConsumoPressaoExcel;

public class RelConsumoPressaoExcelBO {
	
	private HashMap<Integer, List<RelConsumoPressaoExcel>> map;
	
	private List<String> abas;
	
	private HashMap<Integer, List<RelConsumoPressaoExcel>> mapEmp;

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

	public HashMap<Integer, List<RelConsumoPressaoExcel>> getMapEmp() {
		return mapEmp;
	}

	public void setMapEmp(HashMap<Integer, List<RelConsumoPressaoExcel>> mapEmp) {
		this.mapEmp = mapEmp;
	}
	
}

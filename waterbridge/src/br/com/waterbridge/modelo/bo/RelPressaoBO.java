package br.com.waterbridge.modelo.bo;

import java.util.List;

import br.com.waterbridge.relmodelo.RelPressao;

public class RelPressaoBO {
	
	String bridge;
	
	String dtInicio;
	
	String dtFim;
	
	List<RelPressao> listRelPressao;
	
	List<String> listData;
	
	List<Double> listPressao;
	
	

	public String getBridge() {
		return bridge;
	}

	public void setBridge(String bridge) {
		this.bridge = bridge;
	}

	public String getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}

	public String getDtFim() {
		return dtFim;
	}

	public void setDtFim(String dtFim) {
		this.dtFim = dtFim;
	}

	public List<RelPressao> getListRelPressao() {
		return listRelPressao;
	}

	public void setListRelPressao(List<RelPressao> listRelPressao) {
		this.listRelPressao = listRelPressao;
	}

	public List<String> getListData() {
		return listData;
	}

	public void setListData(List<String> listData) {
		this.listData = listData;
	}

	public List<Double> getListPressao() {
		return listPressao;
	}

	public void setListPressao(List<Double> listPressao) {
		this.listPressao = listPressao;
	}

}

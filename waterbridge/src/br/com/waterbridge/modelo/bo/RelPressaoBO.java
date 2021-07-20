package br.com.waterbridge.modelo.bo;

import java.util.List;

import br.com.andwaterbridge.modelo.MetaPressao;
import br.com.waterbridge.relmodelo.RelPressao;

public class RelPressaoBO {
	
	String bridge;
	
	Long idBridgeTp;
	
	String dtInicio;
	
	String dtFim;
	
	List<RelPressao> listRelPressao;
	
	List<String> listData;
	
	List<Double> listPressao;
	
	List<Double> listVazao;
	
	MetaPressao metaPressao;

	public String getBridge() {
		return bridge;
	}

	public void setBridge(String bridge) {
		this.bridge = bridge;
	}

	public Long getIdBridgeTp() {
		return idBridgeTp;
	}

	public void setIdBridgeTp(Long idBridgeTp) {
		this.idBridgeTp = idBridgeTp;
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

	public List<Double> getListVazao() {
		return listVazao;
	}

	public void setListVazao(List<Double> listVazao) {
		this.listVazao = listVazao;
	}

	public MetaPressao getMetaPressao() {
		return metaPressao;
	}

	public void setMetaPressao(MetaPressao metaPressao) {
		this.metaPressao = metaPressao;
	}

}

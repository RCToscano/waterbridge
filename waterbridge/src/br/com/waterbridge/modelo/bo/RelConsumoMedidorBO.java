package br.com.waterbridge.modelo.bo;

import java.util.List;

import br.com.waterbridge.relmodelo.RelConsumoMedidor;

public class RelConsumoMedidorBO {
	
	private String bridge;
	
	private String medidor;
	
	private String dtInicio;
	
	private String dtFim;
	
	private List<RelConsumoMedidor> listRelConsumoMedidor;

	
	
	
	public String getBridge() {
		return bridge;
	}

	public void setBridge(String bridge) {
		this.bridge = bridge;
	}

	public String getMedidor() {
		return medidor;
	}

	public void setMedidor(String medidor) {
		this.medidor = medidor;
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

	public List<RelConsumoMedidor> getListRelConsumoMedidor() {
		return listRelConsumoMedidor;
	}

	public void setListRelConsumoMedidor(List<RelConsumoMedidor> listRelConsumoMedidor) {
		this.listRelConsumoMedidor = listRelConsumoMedidor;
	}

}

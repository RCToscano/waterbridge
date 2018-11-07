package br.com.waterbridge.modelo.bo;

import java.util.List;

import br.com.waterbridge.relmodelo.RelConsumoCondominio;

public class RelConsumoCondominioBO {
	
	private String nomeCondominio;
	
	private String dtInicio;
	
	private String dtFim;

	private List<RelConsumoCondominio> listRelConsumoCondominio;

	
	
	public String getNomeCondominio() {
		return nomeCondominio;
	}

	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
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

	public List<RelConsumoCondominio> getListRelConsumoCondominio() {
		return listRelConsumoCondominio;
	}

	public void setListRelConsumoCondominio(List<RelConsumoCondominio> listConsumoCondominio) {
		this.listRelConsumoCondominio = listConsumoCondominio;
	}
	
}

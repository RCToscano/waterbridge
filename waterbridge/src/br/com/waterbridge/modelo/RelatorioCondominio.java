package br.com.waterbridge.modelo;

import java.math.BigDecimal;

public class RelatorioCondominio {
	
	private Long idCondominio;
	private String condominio;
	private String medidor;
	private String data;
	private String consumidor;
	private BigDecimal consumo;
	
	
	public Long getIdCondominio() {
		return idCondominio;
	}
	public void setIdCondominio(Long idCondominio) {
		this.idCondominio = idCondominio;
	}
	public String getCondominio() {
		return condominio;
	}
	public void setCondominio(String condominio) {
		this.condominio = condominio;
	}
	public String getMedidor() {
		return medidor;
	}
	public void setMedidor(String medidor) {
		this.medidor = medidor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getConsumidor() {
		return consumidor;
	}
	public void setConsumidor(String consumidor) {
		this.consumidor = consumidor;
	}
	public BigDecimal getConsumo() {
		return consumo;
	}
	public void setConsumo(BigDecimal consumo) {
		this.consumo = consumo;
	}
	
}

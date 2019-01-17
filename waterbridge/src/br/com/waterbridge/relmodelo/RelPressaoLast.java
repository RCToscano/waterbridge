package br.com.waterbridge.relmodelo;

public class RelPressaoLast {

	private Long idEmpresa;
	private String empresa;
	private Long idCondominio;
	private String condominio;		
	private Long idBridge;
	private String deviceNum;	
	private Long idConsumo;
	private Double pressao;	
	private Double pressaoMinBaixa;
	private Double pressaoMin;
	private Double pressaoMax;
	private Double pressaoMaxAlta;	
	private String dtInsert;
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
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
	public Long getIdBridge() {
		return idBridge;
	}
	public void setIdBridge(Long idBridge) {
		this.idBridge = idBridge;
	}
	public String getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	public Long getIdConsumo() {
		return idConsumo;
	}
	public void setIdConsumo(Long idConsumo) {
		this.idConsumo = idConsumo;
	}
	public Double getPressao() {
		return pressao;
	}
	public void setPressao(Double pressao) {
		this.pressao = pressao;
	}
	public Double getPressaoMinBaixa() {
		return pressaoMinBaixa;
	}
	public void setPressaoMinBaixa(Double pressaoMinBaixa) {
		this.pressaoMinBaixa = pressaoMinBaixa;
	}
	public Double getPressaoMin() {
		return pressaoMin;
	}
	public void setPressaoMin(Double pressaoMin) {
		this.pressaoMin = pressaoMin;
	}
	public Double getPressaoMax() {
		return pressaoMax;
	}
	public void setPressaoMax(Double pressaoMax) {
		this.pressaoMax = pressaoMax;
	}
	public Double getPressaoMaxAlta() {
		return pressaoMaxAlta;
	}
	public void setPressaoMaxAlta(Double pressaoMaxAlta) {
		this.pressaoMaxAlta = pressaoMaxAlta;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}

}

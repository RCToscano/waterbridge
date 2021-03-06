package br.com.andwaterbridge.modelo;

public class MetaPressao {

	private Long idMetaPressao;
	private Long idUser;
	private Long idEmpresa;
	private Long idCondominio;
	private Long idBridge;
	private Long idMedidor;
	private Long meterPosition;
	private Double pressaoMinBaixa;
	private Double pressaoMin;
	private Double pressaoMax;
	private Double pressaoMaxAlta;
	private String dtInsert;
	
	public Long getIdMetaPressao() {
		return idMetaPressao;
	}
	public void setIdMetaPressao(Long idMetaPressao) {
		this.idMetaPressao = idMetaPressao;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Long getIdCondominio() {
		return idCondominio;
	}
	public void setIdCondominio(Long idCondominio) {
		this.idCondominio = idCondominio;
	}
	public Long getIdBridge() {
		return idBridge;
	}
	public void setIdBridge(Long idBridge) {
		this.idBridge = idBridge;
	}
	public Long getIdMedidor() {
		return idMedidor;
	}
	public void setIdMedidor(Long idMedidor) {
		this.idMedidor = idMedidor;
	}
	public Long getMeterPosition() {
		return meterPosition;
	}
	public void setMeterPosition(Long meterPosition) {
		this.meterPosition = meterPosition;
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

package br.com.waterbridge.modelo;

public class Medidor {
	
	private Long idMedidor;
	private Long idBridge;
	private String deviceNum;
	private String fabricante;
	private String modelo;
	private String serie;
	private String tipo;
	private String chaveDeCripto;
	private int validBateria;
	private String numero;
	private int meterPosition;
	private String obs;
	private String situacao;
	private String dtInsert;
	
	
	
	public String getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	public Long getIdBridge() {
		return idBridge;
	}
	public void setIdBridge(Long idBridge) {
		this.idBridge = idBridge;
	}
	public int getMeterPosition() {
		return meterPosition;
	}
	public void setMeterPosition(int meterPosition) {
		this.meterPosition = meterPosition;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Long getIdMedidor() {
		return idMedidor;
	}
	public void setIdMedidor(Long idMedidor) {
		this.idMedidor = idMedidor;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getChaveDeCripto() {
		return chaveDeCripto;
	}
	public void setChaveDeCripto(String chaveDeCripto) {
		this.chaveDeCripto = chaveDeCripto;
	}
	public int getValidBateria() {
		return validBateria;
	}
	public void setValidBateria(int validBateria) {
		this.validBateria = validBateria;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
}

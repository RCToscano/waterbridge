package br.com.waterbridge.modelo;

public class Message {

	private Long idMessage;
	private Long idUser;
	private String data;
	private String version;
	private Long meterId;
	private Long volume;
	private Long temperature;
	private Double battery;
	private Long alarm;
	private Long consumo;
	private Long vazao;
	private String dtInsert;
	
	public Long getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Long getMeterId() {
		return meterId;
	}
	public void setMeterId(Long meterId) {
		this.meterId = meterId;
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public Long getTemperature() {
		return temperature;
	}
	public void setTemperature(Long temperature) {
		this.temperature = temperature;
	}
	public Double getBattery() {
		return battery;
	}
	public void setBattery(Double battery) {
		this.battery = battery;
	}
	public Long getAlarm() {
		return alarm;
	}
	public void setAlarm(Long alarm) {
		this.alarm = alarm;
	}
	public Long getConsumo() {
		return consumo;
	}
	public void setConsumo(Long consumo) {
		this.consumo = consumo;
	}
	public Long getVazao() {
		return vazao;
	}
	public void setVazao(Long vazao) {
		this.vazao = vazao;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
	
}

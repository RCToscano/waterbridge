package br.com.waterbridge.modelo;

public class Message {

	private Long idMessage;
	private Long idUser;
	private String device;
	private String data;
	private String version;
	private Long meterPosition;
	private Double volume;
	private Double pressure;
	private Long flow;
	private Long temperature;
	private Double battery;
	private Long alarm;
	private Double consumo;
	private Double vazao;
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
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
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
	public Long getMeterPosition() {
		return meterPosition;
	}
	public void setMeterPosition(Long meterPosition) {
		this.meterPosition = meterPosition;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public Double getPressure() {
		return pressure;
	}
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}
	public Long getFlow() {
		return flow;
	}
	public void setFlow(Long flow) {
		this.flow = flow;
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
	public Double getConsumo() {
		return consumo;
	}
	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}
	public Double getVazao() {
		return vazao;
	}
	public void setVazao(Double vazao) {
		this.vazao = vazao;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
	
}
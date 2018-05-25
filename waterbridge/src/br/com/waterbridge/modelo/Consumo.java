package br.com.waterbridge.modelo;

public class Consumo {

	private Long idConsumo;
	private Long idUser;
	private Long idMedidor;
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
	private String dtInsert;
	
	public Long getIdConsumo() {
		return idConsumo;
	}
	public void setIdConsumo(Long idConsumo) {
		this.idConsumo = idConsumo;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Long getIdMedidor() {
		return idMedidor;
	}
	public void setIdMedidor(Long idMedidor) {
		this.idMedidor = idMedidor;
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
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}

}

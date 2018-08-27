package br.com.waterbridge.modelo;

public class EmailAlarm {
	
	private Long idEmailAlarm;
	private Long idAlarm;
	private String device;
	private String dtInsert;
	
	
	
	public Long getIdEmailAlarm() {
		return idEmailAlarm;
	}
	public void setIdEmailAlarm(Long idEmailAlarm) {
		this.idEmailAlarm = idEmailAlarm;
	}
	public Long getIdAlarm() {
		return idAlarm;
	}
	public void setIdAlarm(Long idAlarm) {
		this.idAlarm = idAlarm;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}

}

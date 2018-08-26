package br.com.waterbridge.modelo;

public class Alarm {
	
	private Long idAlarm;
	private String alarm;
	private String descricao;
	
	
	public Long getIdAlarm() {
		return idAlarm;
	}
	public void setIdAlarm(Long idAlarm) {
		this.idAlarm = idAlarm;
	}
	public String getAlarm() {
		return alarm;
	}
	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

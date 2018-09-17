package br.com.andwaterbridge.modelo;

public class AlarmPressaoApp {

	private Long idAlarmPressaoApp;
	private Long idAlarmPressao;
	private Long idUser;
	private String dtInsert;
	
	public Long getIdAlarmPressaoApp() {
		return idAlarmPressaoApp;
	}
	public void setIdAlarmPressaoApp(Long idAlarmPressaoApp) {
		this.idAlarmPressaoApp = idAlarmPressaoApp;
	}
	public Long getIdAlarmPressao() {
		return idAlarmPressao;
	}
	public void setIdAlarmPressao(Long idAlarmPressao) {
		this.idAlarmPressao = idAlarmPressao;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}

}

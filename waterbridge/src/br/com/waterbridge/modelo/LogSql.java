package br.com.waterbridge.modelo;

public class LogSql {

	private Long idLogSql;
	private Long idUser;
	private String logSql;
	private String texto;
	private String dtInsert;
	
	public Long getIdLogSql() {
		return idLogSql;
	}
	public void setIdLogSql(Long idLogSql) {
		this.idLogSql = idLogSql;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getLogSql() {
		return logSql;
	}
	public void setLogSql(String logSql) {
		this.logSql = logSql;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
	
}

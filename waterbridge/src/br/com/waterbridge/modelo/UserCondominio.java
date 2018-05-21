package br.com.waterbridge.modelo;

public class UserCondominio {

	private Long idUserCondominio;
	private Long idInsert;
	private Long idUser;
	private Long idCondominio;
	private String situacao;
	private String dtInicio;
	private String dtFim;
	
	public Long getIdUserCondominio() {
		return idUserCondominio;
	}
	public void setIdUserCondominio(Long idUserCondominio) {
		this.idUserCondominio = idUserCondominio;
	}
	public Long getIdInsert() {
		return idInsert;
	}
	public void setIdInsert(Long idInsert) {
		this.idInsert = idInsert;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Long getIdCondominio() {
		return idCondominio;
	}
	public void setIdCondominio(Long idCondominio) {
		this.idCondominio = idCondominio;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}
	public String getDtFim() {
		return dtFim;
	}
	public void setDtFim(String dtFim) {
		this.dtFim = dtFim;
	}
	
}

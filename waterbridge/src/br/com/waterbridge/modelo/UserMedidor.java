package br.com.waterbridge.modelo;

public class UserMedidor {

	private Long idUserMedidor;
	private Long idInsert;
	private Long idUser;
	private Long idMedidor;
	private String situacao;
	private String dtInicio;
	private String dtFim;
	
	public Long getIdUserMedidor() {
		return idUserMedidor;
	}
	public void setIdUserMedidor(Long idUserMedidor) {
		this.idUserMedidor = idUserMedidor;
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
	public Long getIdMedidor() {
		return idMedidor;
	}
	public void setIdMedidor(Long idMedidor) {
		this.idMedidor = idMedidor;
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

package br.com.waterbridge.modelo;

public class UserEmpresa {
	
	private Long idUserEmpresa;
	private Long idInsert;
	private Long idUser;
	private Long idEmpresa;
	private String obs;
	private String situacao;
	private String dtInicio;
	private String dtFim;
	
	public Long getIdUserEmpresa() {
		return idUserEmpresa;
	}
	public void setIdUserEmpresa(Long idUserEmpresa) {
		this.idUserEmpresa = idUserEmpresa;
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
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
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

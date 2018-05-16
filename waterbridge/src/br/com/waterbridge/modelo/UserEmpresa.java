package br.com.waterbridge.modelo;

public class UserEmpresa {
	
	private Long idUserEmpresa;
	private Long idUser;
	private Long idEmpresa;
	private String dtInicio;
	private String dtFim;
	private String situacao;
	
	public Long getIdUserEmpresa() {
		return idUserEmpresa;
	}
	public void setIdUserEmpresa(Long idUserEmpresa) {
		this.idUserEmpresa = idUserEmpresa;
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
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}

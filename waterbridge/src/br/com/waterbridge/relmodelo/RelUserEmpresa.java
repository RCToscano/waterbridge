package br.com.waterbridge.relmodelo;

public class RelUserEmpresa {

	private Long idUserEmpresa;
	private Long idInsert;
	private String nomeInsert;
	private Long idUser;
	private String nomeUser;
	private String cpfUser;
	private Long idEmpresa;
	private Long cnp;
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
	public String getNomeInsert() {
		return nomeInsert;
	}
	public void setNomeInsert(String nomeInsert) {
		this.nomeInsert = nomeInsert;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getNomeUser() {
		return nomeUser;
	}
	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}
	public String getCpfUser() {
		return cpfUser;
	}
	public void setCpfUser(String cpfUser) {
		this.cpfUser = cpfUser;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Long getCnp() {
		return cnp;
	}
	public void setCnp(Long cnp) {
		this.cnp = cnp;
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

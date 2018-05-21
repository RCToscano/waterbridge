package br.com.waterbridge.relmodelo;

import java.util.List;

public class RelCondominio {

	private Long idEmpresa;
	private String empresa;
	private Long idCondominio;	
	private Long idUser;
	private Long idCnpTp;
	private String cnpTp;
	private String nome;
	private String cnp;
	private String telFixo;
	private String telCel;
	private String email;
	private String endereco;
	private Long numero;
	private String compl;
	private String municipio;
	private String uf;
	private String cep;
	private String coordX;
	private String coordY;
	private String responsavel;
	private String contratoNum;
	private Long contaCiclo;
	private String situacao;
	private String dtInsert;
	List<RelUserCondominio> listRelUserCondominio;
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Long getIdCondominio() {
		return idCondominio;
	}
	public void setIdCondominio(Long idCondominio) {
		this.idCondominio = idCondominio;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Long getIdCnpTp() {
		return idCnpTp;
	}
	public void setIdCnpTp(Long idCnpTp) {
		this.idCnpTp = idCnpTp;
	}
	public String getCnpTp() {
		return cnpTp;
	}
	public void setCnpTp(String cnpTp) {
		this.cnpTp = cnpTp;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public String getTelFixo() {
		return telFixo;
	}
	public void setTelFixo(String telFixo) {
		this.telFixo = telFixo;
	}
	public String getTelCel() {
		return telCel;
	}
	public void setTelCel(String telCel) {
		this.telCel = telCel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getCompl() {
		return compl;
	}
	public void setCompl(String compl) {
		this.compl = compl;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCoordX() {
		return coordX;
	}
	public void setCoordX(String coordX) {
		this.coordX = coordX;
	}
	public String getCoordY() {
		return coordY;
	}
	public void setCoordY(String coordY) {
		this.coordY = coordY;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public String getContratoNum() {
		return contratoNum;
	}
	public void setContratoNum(String contratoNum) {
		this.contratoNum = contratoNum;
	}
	public Long getContaCiclo() {
		return contaCiclo;
	}
	public void setContaCiclo(Long contaCiclo) {
		this.contaCiclo = contaCiclo;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
	public List<RelUserCondominio> getListRelUserCondominio() {
		return listRelUserCondominio;
	}
	public void setListRelUserCondominio(List<RelUserCondominio> listRelUserCondominio) {
		this.listRelUserCondominio = listRelUserCondominio;
	}
	
}

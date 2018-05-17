package br.com.waterbridge.modelo;

public class Bridge {
	
	private Long idBridge;
	private Long idUser;
	private Long idCondominio;
	private String deviceNum;
	private String dtAtivacao;
	private String validadeToken;
	private BridgeTp bridgeTp;
	private BridgeTpAlim bridgeTpAlim;
	private Double custoMensal;
	private Long taxaEnvio;
	private String descricao;
	private String situacao;
	private String dtInsert;
	
	public Long getIdBridge() {
		return idBridge;
	}
	public void setIdBridge(Long idBridge) {
		this.idBridge = idBridge;
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
	public String getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	public String getDtAtivacao() {
		return dtAtivacao;
	}
	public void setDtAtivacao(String dtAtivacao) {
		this.dtAtivacao = dtAtivacao;
	}
	public String getValidadeToken() {
		return validadeToken;
	}
	public void setValidadeToken(String validadeToken) {
		this.validadeToken = validadeToken;
	}
	public BridgeTp getBridgeTp() {
		return bridgeTp;
	}
	public void setBridgeTp(BridgeTp bridgeTp) {
		this.bridgeTp = bridgeTp;
	}
	public BridgeTpAlim getBridgeTpAlim() {
		return bridgeTpAlim;
	}
	public void setBridgeTpAlim(BridgeTpAlim bridgeTpAlim) {
		this.bridgeTpAlim = bridgeTpAlim;
	}
	public Double getCustoMensal() {
		return custoMensal;
	}
	public void setCustoMensal(Double custoMensal) {
		this.custoMensal = custoMensal;
	}
	public Long getTaxaEnvio() {
		return taxaEnvio;
	}
	public void setTaxaEnvio(Long taxaEnvio) {
		this.taxaEnvio = taxaEnvio;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

}

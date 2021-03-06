package br.com.waterbridge.relmodelo;

public class RelConta {

	private Long idConta;
	private Long idEmpresa;
	private Long idCondominio;
	private Long idUser;
	private String dtLeituraAtual;
	private String dtLeituraAnterior;
	private Double valor;
	private Double consumo;
	private String obs;
	private Long rateio;
	private String dtInsert;
	
	public Long getIdConta() {
		return idConta;
	}
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
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
	public String getDtLeituraAtual() {
		return dtLeituraAtual;
	}
	public void setDtLeituraAtual(String dtLeituraAtual) {
		this.dtLeituraAtual = dtLeituraAtual;
	}
	public String getDtLeituraAnterior() {
		return dtLeituraAnterior;
	}
	public void setDtLeituraAnterior(String dtLeituraAnterior) {
		this.dtLeituraAnterior = dtLeituraAnterior;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getConsumo() {
		return consumo;
	}
	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public Long getRateio() {
		return rateio;
	}
	public void setRateio(Long rateio) {
		this.rateio = rateio;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
	
}

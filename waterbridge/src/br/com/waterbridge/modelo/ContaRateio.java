package br.com.waterbridge.modelo;

public class ContaRateio {

	private Long idContaRateio;
	private Long idConta;
	private Long idEmpresa;
	private Long idCondominio;
	private Long idMedidor;
	private Long idUser;
	private Double volumeInicial;
	private Double volumeFinal;
	private Double consumoReal;
	private Double consumoRateio;
	private Double valorRateio;
	private Double percRateio;
	private String obs;
	private String dtInsert;
	
	public Long getIdContaRateio() {
		return idContaRateio;
	}
	public void setIdContaRateio(Long idContaRateio) {
		this.idContaRateio = idContaRateio;
	}
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
	public Long getIdMedidor() {
		return idMedidor;
	}
	public void setIdMedidor(Long idMedidor) {
		this.idMedidor = idMedidor;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Double getVolumeInicial() {
		return volumeInicial;
	}
	public void setVolumeInicial(Double volumeInicial) {
		this.volumeInicial = volumeInicial;
	}
	public Double getVolumeFinal() {
		return volumeFinal;
	}
	public void setVolumeFinal(Double volumeFinal) {
		this.volumeFinal = volumeFinal;
	}
	public Double getConsumoReal() {
		return consumoReal;
	}
	public void setConsumoReal(Double consumoReal) {
		this.consumoReal = consumoReal;
	}
	public Double getConsumoRateio() {
		return consumoRateio;
	}
	public void setConsumoRateio(Double consumoRateio) {
		this.consumoRateio = consumoRateio;
	}
	public Double getValorRateio() {
		return valorRateio;
	}
	public void setValorRateio(Double valorRateio) {
		this.valorRateio = valorRateio;
	}
	public Double getPercRateio() {
		return percRateio;
	}
	public void setPercRateio(Double percRateio) {
		this.percRateio = percRateio;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
}

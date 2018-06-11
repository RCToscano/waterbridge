package br.com.waterbridge.relmodelo;

import java.util.List;

public class RelContaRateio {

	private Long idContaRateio;
	private Long idConta;
	private String dtLeituraAtual;
	private String dtLeituraAnterior;
	private Double contaValor;
	private Double contaConsumo;
	private Long idEmpresa;
	private String empresa;
	private Long idCondominio;
	private String condominio;
	private Long idMedidor;
	private String numeroMedidor;
	private String endereco;
    private Long numero;
    private String compl;
    private String municipio;
    private String uf;
    private String cep;
	private String coordX;
    private String coordY;
    private Long idUser;
	private Double volumeInicial;
	private Double volumeFinal;
	private Double consumoReal;
	private Double consumoRateio;
	private Double valorRateio;
	private Double percRateio;
	private String obs;
	private String dtInsert;
	private List<RelUserMedidor> listRelUserMedidor;
	
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
	public Double getContaValor() {
		return contaValor;
	}
	public void setContaValor(Double contaValor) {
		this.contaValor = contaValor;
	}
	public Double getContaConsumo() {
		return contaConsumo;
	}
	public void setContaConsumo(Double contaConsumo) {
		this.contaConsumo = contaConsumo;
	}
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
	public String getCondominio() {
		return condominio;
	}
	public void setCondominio(String condominio) {
		this.condominio = condominio;
	}
	public Long getIdMedidor() {
		return idMedidor;
	}
	public void setIdMedidor(Long idMedidor) {
		this.idMedidor = idMedidor;
	}
	public String getNumeroMedidor() {
		return numeroMedidor;
	}
	public void setNumeroMedidor(String numeroMedidor) {
		this.numeroMedidor = numeroMedidor;
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
	public List<RelUserMedidor> getListRelUserMedidor() {
		return listRelUserMedidor;
	}
	public void setListRelUserMedidor(List<RelUserMedidor> listRelUserMedidor) {
		this.listRelUserMedidor = listRelUserMedidor;
	}
	
}

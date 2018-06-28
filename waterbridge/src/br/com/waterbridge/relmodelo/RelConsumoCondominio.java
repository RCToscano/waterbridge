package br.com.waterbridge.relmodelo;

import java.util.List;

public class RelConsumoCondominio {

	private Long idMedidor;
	private String device;
	private String meterId;
	private String endereco;
	private Long numero;
	private String compl;
	private String municipio;
	private String uf;
	private String cep;
	private String coordX;
	private String coordY;
	private Double volumeInicio;
	private Double volumeFim;
	private Double consumo;
	private List<RelUserMedidor> listRelUserMedidor;
	
	public Long getIdMedidor() {
		return idMedidor;
	}
	public void setIdMedidor(Long idMedidor) {
		this.idMedidor = idMedidor;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getMeterId() {
		return meterId;
	}
	public void setMeterId(String meterId) {
		this.meterId = meterId;
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
	public Double getVolumeInicio() {
		return volumeInicio;
	}
	public void setVolumeInicio(Double volumeInicio) {
		this.volumeInicio = volumeInicio;
	}
	public Double getVolumeFim() {
		return volumeFim;
	}
	public void setVolumeFim(Double volumeFim) {
		this.volumeFim = volumeFim;
	}
	public Double getConsumo() {
		return consumo;
	}
	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}
	public List<RelUserMedidor> getListRelUserMedidor() {
		return listRelUserMedidor;
	}
	public void setListRelUserMedidor(List<RelUserMedidor> listRelUserMedidor) {
		this.listRelUserMedidor = listRelUserMedidor;
	}
	
}

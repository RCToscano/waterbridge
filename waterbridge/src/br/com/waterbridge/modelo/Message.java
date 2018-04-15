package br.com.waterbridge.modelo;

import java.math.BigInteger;

public class Message {

	private Long idMessage;
	private Long idUser;
	private String data;
	private BigInteger consumo;
	private BigInteger vazao;
	private BigInteger biVersion;
	private BigInteger biMeterId;
	private BigInteger biVolume;
	private BigInteger biTemperature;
	private BigInteger biBattery;
	private BigInteger biAlarme;
	private String dtInsert;
	
	public Long getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public BigInteger getConsumo() {
		return consumo;
	}
	public void setConsumo(BigInteger consumo) {
		this.consumo = consumo;
	}
	public BigInteger getVazao() {
		return vazao;
	}
	public void setVazao(BigInteger vazao) {
		this.vazao = vazao;
	}
	public BigInteger getBiVersion() {
		return biVersion;
	}
	public void setBiVersion(BigInteger biVersion) {
		this.biVersion = biVersion;
	}
	public BigInteger getBiMeterId() {
		return biMeterId;
	}
	public void setBiMeterId(BigInteger biMeterId) {
		this.biMeterId = biMeterId;
	}
	public BigInteger getBiVolume() {
		return biVolume;
	}
	public void setBiVolume(BigInteger biVolume) {
		this.biVolume = biVolume;
	}
	public BigInteger getBiTemperature() {
		return biTemperature;
	}
	public void setBiTemperature(BigInteger biTemperature) {
		this.biTemperature = biTemperature;
	}
	public BigInteger getBiBattery() {
		return biBattery;
	}
	public void setBiBattery(BigInteger biBattery) {
		this.biBattery = biBattery;
	}
	public BigInteger getBiAlarme() {
		return biAlarme;
	}
	public void setBiAlarme(BigInteger biAlarme) {
		this.biAlarme = biAlarme;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
	
}

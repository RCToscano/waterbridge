package br.com.waterbridge.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

public class Exportacao {

	private Long idConsumo;
	private Long idEmpresa;
	private String empresa;
	private Long idCondominio;
	private Long idBridge;
	private String device;
	private Double pressure;
	private Double flow;
	private String endereco;
	private Long numero;
	private String compl;
	private String coordX;
	private String coordY;
	private String dtInsert;
	private Long idArquivo;

	public Long getIdConsumo() {
		return idConsumo;
	}
	public void setIdConsumo(Long idConsumo) {
		this.idConsumo = idConsumo;
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
	public Long getIdBridge() {
		return idBridge;
	}
	public void setIdBridge(Long idBridge) {
		this.idBridge = idBridge;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public Double getPressure() {
		return pressure;
	}
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}	
	public Double getFlow() {
		return flow;
	}
	public void setFlow(Double flow) {
		this.flow = flow;
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
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
	public Long getIdArquivo() {
		return idArquivo;
	}
	public void setIdArquivo(Long idArquivo) {
		this.idArquivo = idArquivo;
	}
}

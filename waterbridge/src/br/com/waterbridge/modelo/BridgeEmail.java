package br.com.waterbridge.modelo;

public class BridgeEmail {
	
	private Long idBridgeEmail;
	private Long idBridge;
	private Long idUser;
	private String email;
	private String dtInsert;
	
	
	
	public Long getIdBridgeEmail() {
		return idBridgeEmail;
	}
	public void setIdBridgeEmail(Long idBridgeEmail) {
		this.idBridgeEmail = idBridgeEmail;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
}

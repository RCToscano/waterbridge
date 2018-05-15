package br.com.waterbridge.modelo;

public class FabricMedidor {

	private Long idFabricMedidor;
	private Long idUser;
	private String fabricante;
	private String situacao;
	private String dtInsert;
	
	public Long getIdFabricMedidor() {
		return idFabricMedidor;
	}
	public void setIdFabricMedidor(Long idFabricMedidor) {
		this.idFabricMedidor = idFabricMedidor;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
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

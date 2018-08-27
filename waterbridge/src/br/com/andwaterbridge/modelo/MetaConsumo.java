package br.com.andwaterbridge.modelo;

public class MetaConsumo {
	
	private Long idMetaConsumo;
	private Long idUser;
	private Long idMedidor;
	private Double meta;
	private String dtInsert;
	
	public Long getIdMetaConsumo() {
		return idMetaConsumo;
	}
	public void setIdMetaConsumo(Long idMetaConsumo) {
		this.idMetaConsumo = idMetaConsumo;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Long getIdMedidor() {
		return idMedidor;
	}
	public void setIdMedidor(Long idMedidor) {
		this.idMedidor = idMedidor;
	}
	public Double getMeta() {
		return meta;
	}
	public void setMeta(Double meta) {
		this.meta = meta;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
	
}

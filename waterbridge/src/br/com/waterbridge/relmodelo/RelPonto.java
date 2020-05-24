package br.com.waterbridge.relmodelo;

public class RelPonto {
	
	private Long idPonto;
	private Long idEmpresa;
	private Long idPontoTp;
	private String descricaoPonto;
	private String descricaoPontoTp;
	private String coordX;
	private String coordY;
	private String dtInsert;
	
	public Long getIdPonto() {
		return idPonto;
	}
	public void setIdPonto(Long idPonto) {
		this.idPonto = idPonto;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Long getIdPontoTp() {
		return idPontoTp;
	}
	public void setIdPontoTp(Long idPontoTp) {
		this.idPontoTp = idPontoTp;
	}
	public String getDescricaoPonto() {
		return descricaoPonto;
	}
	public void setDescricaoPonto(String descricaoPonto) {
		this.descricaoPonto = descricaoPonto;
	}
	public String getDescricaoPontoTp() {
		return descricaoPontoTp;
	}
	public void setDescricaoPontoTp(String descricaoPontoTp) {
		this.descricaoPontoTp = descricaoPontoTp;
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

}

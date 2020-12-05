package br.com.waterbridge.modelo;

public class Arquivo {

	private Long idArquivo;
	private String nomeArquivo;
	private Integer remessa;
	private Integer qtde;
	private String dtInsert;
	
	public Long getIdArquivo() {
		return idArquivo;
	}
	public void setIdArquivo(Long idArquivo) {
		this.idArquivo = idArquivo;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public Integer getRemessa() {
		return remessa;
	}
	public void setRemessa(Integer remessa) {
		this.remessa = remessa;
	}
	public Integer getQtde() {
		return qtde;
	}
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}
}

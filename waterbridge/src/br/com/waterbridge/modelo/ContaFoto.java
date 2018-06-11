package br.com.waterbridge.modelo;

public class ContaFoto {
	
	private Long idContaFoto;
	private Long idConta;
	private Long idUser;
	private String diretorio;
	private String nome;
	private String dtInsert;
	
	
	
	public Long getIdContaFoto() {
		return idContaFoto;
	}
	public void setIdContaFoto(Long idContaFoto) {
		this.idContaFoto = idContaFoto;
	}
	public Long getIdConta() {
		return idConta;
	}
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getDiretorio() {
		return diretorio;
	}
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDtInsert() {
		return dtInsert;
	}
	public void setDtInsert(String dtInsert) {
		this.dtInsert = dtInsert;
	}

}

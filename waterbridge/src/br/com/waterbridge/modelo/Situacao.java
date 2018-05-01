package br.com.waterbridge.modelo;

public class Situacao {

	private Long idSituacao;
	private String situacao;
	private String descricao;
	
	public Long getIdSituacao() {
		return idSituacao;
	}
	public void setIdSituacao(Long idSituacao) {
		this.idSituacao = idSituacao;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

package br.com.waterbridge.modelo;

import java.util.Date;

public class Sessao {
	
	private long idSessao;
	private long idUser;
	private Date dtLogin;
	private Date dtLogout;
	
	
	public long getIdSessao() {
		return idSessao;
	}
	public void setIdSessao(long idSessao) {
		this.idSessao = idSessao;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public Date getDtLogin() {
		return dtLogin;
	}
	public void setDtLogin(Date dtLogin) {
		this.dtLogin = dtLogin;
	}
	public Date getDtLogout() {
		return dtLogout;
	}
	public void setDtLogout(Date dtLogout) {
		this.dtLogout = dtLogout;
	}

}

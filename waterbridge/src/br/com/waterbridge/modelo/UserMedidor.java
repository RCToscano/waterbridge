package br.com.waterbridge.modelo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.waterbridge.auxiliar.Email;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.UserDAO;

public class UserMedidor {

	private Long idUserMedidor;
	private Long idInsert;
	private Long idUser;
	private Long idMedidor;
	private String obs;
	private String situacao;
	private String dtInicio;
	private String dtFim;
	
	public Long getIdUserMedidor() {
		return idUserMedidor;
	}
	public void setIdUserMedidor(Long idUserMedidor) {
		this.idUserMedidor = idUserMedidor;
	}
	public Long getIdInsert() {
		return idInsert;
	}
	public void setIdInsert(Long idInsert) {
		this.idInsert = idInsert;
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
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}
	public String getDtFim() {
		return dtFim;
	}
	public void setDtFim(String dtFim) {
		this.dtFim = dtFim;
	}
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		
		UserDAO userDAO = new UserDAO(connection);
		User user = userDAO.buscarPorCpf("090.427.638-41");
		System.out.println("user " + user.getCpf() + " " + user.getNome());
		Email.enviarEmail("WaterBridge - Acesso", Email.corpoAcessoUsuario(user), user.getEmail());
		System.out.println("envio ok ");
		connection.close();
	}

}

package br.com.waterbridge.bo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.SessaoDAO;
import br.com.waterbridge.modelo.Sessao;
import br.com.waterbridge.modelo.User;


public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		Connection connection = null;
		try {
			User userSessao = (User) se.getSession().getAttribute("user");
			if(userSessao != null) {
				connection = ConnectionFactory.getConnection();
				SessaoDAO sessaoDAO = new SessaoDAO(connection);
				Sessao sessao = sessaoDAO.buscarPorId(userSessao.getIdSessao());
				if(sessao.getDtLogout() == null) {
					sessaoDAO.alterar(userSessao.getIdSessao());
				}
			}
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
            if(connection != null) {
                try {connection.close();} catch (SQLException ex) {}
            }
        }
	}
}
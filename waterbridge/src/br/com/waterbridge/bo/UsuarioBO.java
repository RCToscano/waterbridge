package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.waterbridge.dao.PerfilDAO;
import br.com.waterbridge.enuns.SexoEnum;
import br.com.waterbridge.modelo.Perfil;

public class UsuarioBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String relat = "";
		Connection connection = null;
        try {
            if (req.getParameter("acao") != null) {
                relat = req.getParameter("acao");
            }

            if (relat.equals("cadUsuario")) {
            	PerfilDAO perfilDAO = new PerfilDAO(connection);
            	List<Perfil> listaPerfil = new ArrayList<>();
            	try {
            		listaPerfil = perfilDAO.listarTodos();
				} 
            	finally {
            		List<SexoEnum> listaSexo = SexoEnum.listCodigos();
            		req.setAttribute("listaSexo", listaSexo);
            		req.setAttribute("listaPerfil", listaPerfil);
            		req.getRequestDispatcher("/jsp/usuario/usuario.jsp").forward(req, res);
				}
            } 
        }
        catch (Exception e) {
            req.setAttribute("erro", e.toString());
            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
        }
        finally {
            if(connection != null) {
                try {connection.close();} catch (SQLException ex) {}
            }
        }
    }
}


package br.com.andwaterbridge.bo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.andwaterbridge.dao.CondominioDAO;
import br.com.andwaterbridge.modelo.Condominio;
import br.com.waterbridge.connection.ConnectionFactory;

public class AndCondominioBO extends HttpServlet {

	private static final long serialVersionUID = 1L;


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    
    	if(req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {
    	
    		Connection connection = null;
    		
    		try {
         		
	    		connection = ConnectionFactory.getConnection();
	    		
	    		CondominioDAO condominioDAO = new CondominioDAO(connection);
	    		
	    		req.getParameter("idUser");
	    		req.getParameter("idPerfil");

                if (req.getParameter("idPerfil") != null && req.getParameter("idPerfil").equals("1")) {//PERFIL PROGRAMADOR
                	
                	List<Condominio> listCondominio = condominioDAO.listarParaProgramador();
                	write(res, new Gson().toJson(listCondominio));
                }  
                else if (req.getParameter("idPerfil") != null && req.getParameter("idPerfil").equals("2")) {//PERFIL GERENCIAL

                	List<Condominio> listCondominio = condominioDAO.listarParaGerente();
                	write(res, new Gson().toJson(listCondominio));
                }  
                else if (req.getParameter("idPerfil") != null && req.getParameter("idPerfil").equals("3")) {//PERFIL REPRESENTANTE

                	List<Condominio> listCondominio = condominioDAO.listarParaRepresentante(Long.parseLong(req.getParameter("idUser")));
                	write(res, new Gson().toJson(listCondominio));
                } 
                else if (req.getParameter("idPerfil") != null && req.getParameter("idPerfil").equals("4")) {//PERFIL ADMINISTRADOR LOCAL

                	List<Condominio> listCondominio = condominioDAO.listarParaAdministradorLocal(Long.parseLong(req.getParameter("idUser")));
                	write(res, new Gson().toJson(listCondominio));
                } 
                else if (req.getParameter("idPerfil") != null && req.getParameter("idPerfil").equals("5")) {//PERFIL CONSUMIDOR
                	
                	List<Condominio> listCondominio = condominioDAO.listarParaConsumidor(Long.parseLong(req.getParameter("idUser")));
                	write(res, new Gson().toJson(listCondominio));
                }                 
	        }
	        catch (Exception e) {
	        	write(res, "erro " + e.toString());
	        }
	        finally {
	            if(connection != null) {
	                try {connection.close();} catch (SQLException ex) {}
	            }
	        }
    	}
    }
    
    private void write(HttpServletResponse response, String resposta) throws IOException {

        response.setContentType("text/html");
        //response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print(resposta);
        writer.flush();
        writer.close();
    }
}


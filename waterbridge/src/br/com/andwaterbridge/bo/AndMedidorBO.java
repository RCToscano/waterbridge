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

import br.com.andwaterbridge.dao.MedidorDAO;
import br.com.andwaterbridge.modelo.Medidor;
import br.com.waterbridge.connection.ConnectionFactory;

public class AndMedidorBO extends HttpServlet {

	private static final long serialVersionUID = 1L;


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    
    	if(req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {
    	
    		Connection connection = null;
    		String sql = "";
    		
    		try {
         		
	    		connection = ConnectionFactory.getConnection();
	    		
	    		MedidorDAO medidorDAO = new MedidorDAO(connection);
	    		
	    		req.getParameter("idUser");
	    		req.getParameter("idPerfil");

	    		//SE PERFIL NAO FOR PROGRAMADOR OU GERENCIAL 
                if (req.getParameter("idPerfil") != null 
                		&& !req.getParameter("idPerfil").equals("1")
                		&& !req.getParameter("idPerfil").equals("2")) {
		    		sql = 
		    		"LEFT JOIN VW_VINCULOBRIDGEUSER " +
		    		"ON        VW_MEDIDORAPP.ID_BRIDGE = VW_VINCULOBRIDGEUSER.ID_BRIDGE " +
		    		"LEFT JOIN VW_VINCULOMEDIDORUSER " +
		    		"ON        VW_MEDIDORAPP.ID_MEDIDOR = VW_VINCULOMEDIDORUSER.ID_MEDIDOR " +  
		    		"WHERE     VW_VINCULOBRIDGEUSER.ID_USER = " + req.getParameter("idUser") + " " +
		    		"OR        VW_VINCULOMEDIDORUSER.ID_USER = " + req.getParameter("idUser") + " " ;
                }
                
                List<Medidor> listMedidor = medidorDAO.listar(sql);
            	write(res, new Gson().toJson(listMedidor));             
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



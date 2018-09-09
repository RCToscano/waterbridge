
package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.reldao.RelMapaConsumoPressaoDAO;
import br.com.waterbridge.relmodelo.RelMapaConsumoPressao;

public class MapaConsumoPressaoBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                   
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {//ENTRA NA MAPA
			
			Connection connection = null;
			String sql = "";
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				sql +=
	            "LEFT JOIN VW_ID_CONSUMOMAX " +
                "ON        VW_MAPACONSUMOPRESSAO.ID_CONSUMO = VW_ID_CONSUMOMAX.ID_CONSUMO " +
                "WHERE     VW_ID_CONSUMOMAX.ID_CONSUMO IS NOT NULL " ;
				
				RelMapaConsumoPressaoDAO relMapaConsumoPressaoDAO = new RelMapaConsumoPressaoDAO(connection);
				List<RelMapaConsumoPressao> listRelMapaConsumoPressao = relMapaConsumoPressaoDAO.listar(sql);
				
				Gson gson = new Gson();
                String jsonMapaConsumoPressao = gson.toJson(listRelMapaConsumoPressao);
				
				req.setAttribute("listRelMapaConsumoPressao", listRelMapaConsumoPressao);
				req.setAttribute("jsonMapaConsumoPressao", jsonMapaConsumoPressao);
				req.getRequestDispatcher("/jsp/mapa/mapaconsumopressao.jsp").forward(req, res);
			}
	        catch (Exception e) {
	            req.setAttribute("erro", e.toString());
	            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
	        }
			finally {
				if(connection != null) {
					try {connection.close();} catch (SQLException e) {}
				}
			}
        } 		
    }
}


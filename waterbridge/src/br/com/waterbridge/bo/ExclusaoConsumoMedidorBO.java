
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

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.ConsumoDAO;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.Consumo;

public class ExclusaoConsumoMedidorBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                   
		//ENTRA NA TELA LISTA USUARIO X MEDIDOR
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {
			
			Connection connection = null;
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				List<Bridge> listBridge = bridgeDAO.listarTodos();
				
				req.setAttribute("listBridge", listBridge);
        		req.getRequestDispatcher("/jsp/consumoMedidor/consulta.jsp").forward(req, res);
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
		
		//LISTAR CONSUMO MEDIDOR DIA
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("2")) {

			Connection connection = null;
            String sql = "";
            String json = "";
			
			try {

				sql += 	"WHERE DEVICE = '" + req.getParameter("device") + "' " +
						"AND   DTINSERT >= '" + Auxiliar.formataDtBanco(req.getParameter("dtInicio")) + " 00:00' " +
						"AND   DTINSERT <= '" + Auxiliar.formataDtBanco(req.getParameter("dtInicio")) + " 23:59' ";

				connection = ConnectionFactory.getConnection();
				
				ConsumoDAO consumoDAO = new ConsumoDAO(connection);
				List<Consumo> listConsumo = consumoDAO.buscarDtInsert(sql);
				
				json = new Gson().toJson(listConsumo);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);   
			}
	        catch (Exception e) {
	        	System.out.println("erro: " + e.toString());
	            req.setAttribute("erro", e.toString());
	            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
	        }
			finally {
				if(connection != null) {
					try {connection.close();} catch (SQLException e) {}
				}
			}	
        }
		
		//EXCLUI CONSUMO MEDIDOR DIA
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("3")) {
			Connection connection = null;
			try {
				int cont = Integer.parseInt(req.getParameter("cont"));
	
				String listaId = ""; 
				 
				for(int i=0; i<=cont; i++){
	                if(req.getParameter("check"+i) != null){
	                	listaId += req.getParameter("idConsumo"+i)+",";
	                }
				}
				
				listaId = listaId.substring(0, listaId.length()-1);
				
				connection = ConnectionFactory.getConnection();
				ConsumoDAO consumoDAO = new ConsumoDAO(connection);
				consumoDAO.deletar(listaId);
				
				req.setAttribute("sucesso", "Registros deletados com sucesso!");
        		req.getRequestDispatcher("/jsp/consumoMedidor/consulta.jsp").forward(req, res);
				
			} 
			catch (Exception e) {
	        	System.out.println("erro: " + e.toString());
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


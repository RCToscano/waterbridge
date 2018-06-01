
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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.auxiliar.Constantes;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.EmpresaDAO;
import br.com.waterbridge.dao.LogSqlDAO;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.Empresa;
import br.com.waterbridge.modelo.User;
import br.com.waterbridge.reldao.RelConsumoMedidorDAO;
import br.com.waterbridge.reldao.RelPressaoDAO;
import br.com.waterbridge.relmodelo.RelConsumoMedidor;
import br.com.waterbridge.relmodelo.RelPressao;

public class RelatorioPressaoBO extends HttpServlet {

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
        	connection = ConnectionFactory.getConnection();
        	HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
		
			if (req.getParameter("acao") != null) {
	            relat = req.getParameter("acao");
	        }
			
			if (relat.equals("1")) {
				try {
					EmpresaDAO empresaDAO = new EmpresaDAO(connection);
					List<Empresa> listEmpresa = empresaDAO.listarPorUsuario(user.getIdUser());
					
					req.setAttribute("listEmpresa", listEmpresa);
					req.setAttribute("display", "none");
	        		req.getRequestDispatcher("/jsp/relatorios/relatorioPressao.jsp").forward(req, res);
				}
		        catch (Exception e) {
		            req.setAttribute("erro", e.toString());
		            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
		        }
	        } 
			
			//POPULA COMBO CONDOMINIO
			else if (relat.equals("2")) {
				try {
					CondominioDAO condominioDAO = new CondominioDAO(connection);
					List<Condominio> listCondominio = new ArrayList<Condominio>();		
					listCondominio = condominioDAO.listarPorUsuario(user.getIdUser(),
							Long.parseLong(req.getParameter("idEmpresa")));
					
					String json = new Gson().toJson(listCondominio);
					
					res.setContentType("application/json");
					res.setCharacterEncoding("UTF-8");
					res.getWriter().write(json);   
				}
		        catch (Exception e) {
		        	System.out.println(e);
	        		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
	        		req.setAttribute("display", "block");
		            req.getRequestDispatcher("/jsp/relatorios/relatorioPressao.jsp").forward(req, res);
		        }
	        }
			
			//POPULA COMBO BRIDGE
			else if (relat.equals("3")) {
				try {
					BridgeDAO bridgeDAO = new BridgeDAO(connection);
					List<Bridge> listBridge = new ArrayList<Bridge>();	
					listBridge = bridgeDAO.listarPressureBridgePorUsuario(user.getIdUser(),
							Long.parseLong(req.getParameter("idCondominio")));
	
					String json = new Gson().toJson(listBridge);
					
					res.setContentType("application/json");
					res.setCharacterEncoding("UTF-8");
					res.getWriter().write(json);   
				}
		        catch (Exception e) {
		        	System.out.println(e);
	        		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
	        		req.setAttribute("display", "block");
		            req.getRequestDispatcher("/jsp/relatorios/relatorioPressao.jsp").forward(req, res);
		        }
	        }
			
			//LISTAR
			else if (relat.equals("5")) {
				try {
					String sql = "WHERE ID_BRIDGETP <> 3 " ;

					if(req.getParameter("idEmpresa") != null && !req.getParameter("idEmpresa").equals("")) {
						sql += "AND   ID_EMPRESA = " + req.getParameter("idEmpresa") + " ";
					}
					if(req.getParameter("idCondominio") != null && !req.getParameter("idCondominio").equals("")) {
						sql += "AND   ID_CONDOMINIO = " + req.getParameter("idCondominio") + " ";
					}
					if(req.getParameter("idBridge") != null && !req.getParameter("idBridge").equals("")) {
						sql += "AND   ID_BRIDGE = " + req.getParameter("idBridge") + " ";
					}
					if(req.getParameter("dtInicio") != null && !req.getParameter("dtInicio").equals("")) {
						
						sql += "AND   DTINSERT >= '" + Auxiliar.formataDtBanco(req.getParameter("dtInicio")) + " 00:00' " +
							   "AND   DTINSERT <= '" + Auxiliar.formataDtBanco(req.getParameter("dtFim")) + " 23:59' " ;
					}
					sql += "ORDER BY DTINSERT ";
	
					RelPressaoDAO relPressaoDAODAO = new RelPressaoDAO(connection);
					List<RelPressao> listRelPressao = relPressaoDAODAO.listar(sql);
					
					String json = new Gson().toJson(listRelPressao);
					
					res.setContentType("application/json");
					res.setCharacterEncoding("UTF-8");
					res.getWriter().write(json);   
				}
		        catch (Exception e) {
		        	System.out.println(e);
	        		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
	        		req.setAttribute("display", "block");
		            req.getRequestDispatcher("/jsp/relatorios/relatorioPressao.jsp").forward(req, res);
		        }
	        }
			
			//GRAFICO
			else if (relat.equals("6")) {
				try {
					String sql = "WHERE ID_BRIDGETP <> 3 " ;
					
					if(req.getParameter("idEmpresa") != null && !req.getParameter("idEmpresa").equals("")) {
						sql += "AND   ID_EMPRESA = " + req.getParameter("idEmpresa") + " ";
					}
					if(req.getParameter("idCondominio") != null && !req.getParameter("idCondominio").equals("")) {
						sql += "AND   ID_CONDOMINIO = " + req.getParameter("idCondominio") + " ";
					}
					if(req.getParameter("idBridge") != null && !req.getParameter("idBridge").equals("")) {
						sql += "AND   ID_BRIDGE = " + req.getParameter("idBridge") + " ";
					}
					if(req.getParameter("dtInicio") != null && !req.getParameter("dtInicio").equals("")) {
						
						sql += "AND   DTINSERT >= '" + Auxiliar.formataDtBanco(req.getParameter("dtInicio")) + " 00:00' " +
							   "AND   DTINSERT <= '" + Auxiliar.formataDtBanco(req.getParameter("dtFim")) + " 23:59' " ;
					}
					sql += "ORDER BY DTINSERT ";
	
					RelPressaoDAO relPressaoDAODAO = new RelPressaoDAO(connection);
					List<RelPressao> listRelPressao = relPressaoDAODAO.listar(sql);
					
					String bridge = "";
					List<String> listData = new ArrayList<String>();
					List<Double> listPressao = new ArrayList<Double>();
					for(int i = 0; i < listRelPressao.size(); i++) {
						
						RelPressao relPressao = listRelPressao.get(i);
	
						if(i == 0) {
							bridge = relPressao.getDevice();
						}
						
						listData.add(relPressao.getDtInsert());
						listPressao.add(relPressao.getPressure());
					}
					
					req.setAttribute("bridge", bridge);
					req.setAttribute("dtInicio", req.getParameter("dtInicio"));
					req.setAttribute("dtFim", req.getParameter("dtFim"));
					req.setAttribute("listData", listData);
					req.setAttribute("listPressao", listPressao);
					req.setAttribute("listRelPressao", listRelPressao);
	        		req.getRequestDispatcher("/jsp/relatorios/relatorioPressaoGrafico.jsp").forward(req, res);   
				}
		        catch (Exception e) {
		        	System.out.println(e);
		        	try {
						new LogSqlDAO(connection).inserir(((User) req.getSession().getAttribute("user")).getIdUser(),
								"", e.getMessage(), "UsuarioBO", relat);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
	        		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
	        		req.setAttribute("display", "block");
		            req.getRequestDispatcher("/jsp/relatorios/relatorioPressao.jsp").forward(req, res);
		        }
	        }
		}
        catch (Exception e) {
            req.setAttribute("erro", e.toString());
            try {
				new LogSqlDAO(connection).inserir(((User) req.getSession().getAttribute("user")).getIdUser(),
						"", e.getMessage(), "RelatorioPressaoBO", relat);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
        }
        finally {
            if(connection != null) {
                try {connection.close();} catch (SQLException ex) {}
            }
        }
	}
}


package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.PontoDAO;
import br.com.waterbridge.dao.PontoTpDAO;
import br.com.waterbridge.modelo.Ponto;
import br.com.waterbridge.modelo.PontoTp;
import br.com.waterbridge.reldao.RelMapaConsumoPressaoDAO;
import br.com.waterbridge.reldao.RelPontoDAO;
import br.com.waterbridge.relmodelo.RelMapaConsumoPressao;
import br.com.waterbridge.relmodelo.RelPonto;

@WebServlet("/PontoBO")
public class PontoBO extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {			
		switch(Integer.parseInt(req.getParameter("acao"))){
	        case 1:
	        	listarPontos(req, res);//LISTAR PONTOS
	            break;
	        case 2:
	        	listarPontosPorId(req, res);//LISTAR PONTOS POR ID_PONTOTP
	            break;
	        case 3:
	        	buscarPonto(req, res);//BUSCAR PONTOS POR ID_PONTO
	            break;
	        case 4:
	        	cadastrarAlterarPonto(req, res);//ALTERAR PONTO
	            break;
	        case 5:
	        	buscarPontoTp(req, res);//ALTERAR PONTO
	            break;
	        case 6:
	        	excluirPonto(req, res);//EXCLUIR PONTO
	            break;    
	        default:
	            break;
	    }		
	}
		
	public void listarPontos(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		Connection connection = null;		
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			RelPontoDAO relPontoDAO = new RelPontoDAO(connection);			
			List<RelPonto> listRelPonto = relPontoDAO.listar("");
			
			RelMapaConsumoPressaoDAO relMapaConsumoPressaoDAO = new RelMapaConsumoPressaoDAO(connection);
			List<RelMapaConsumoPressao> listRelMapaConsumoPressao = relMapaConsumoPressaoDAO.listarPressureBridgeEmpresa(4l);
			for(int i = 0; i < listRelMapaConsumoPressao.size(); i++ ) {
				listRelMapaConsumoPressao.set(i, relMapaConsumoPressaoDAO.buscar(
						listRelMapaConsumoPressao.get(i).getIdEmpresa(), 
						listRelMapaConsumoPressao.get(i).getIdCondominio(), listRelMapaConsumoPressao.get(i).getIdBridge()));
			}
			listRelMapaConsumoPressao.remove(null);
			
			req.setAttribute("listRelPonto", new Gson().toJson(listRelPonto));
			req.setAttribute("listRelMapaConsumoPressao", new Gson().toJson(listRelMapaConsumoPressao));
			req.getRequestDispatcher("/jsp/mapa/mapaponto.jsp").forward(req, res);
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
	
	public void listarPontosPorId(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		Connection connection = null;
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			RelPontoDAO relPontoDAO = new RelPontoDAO(connection);
			
			String sql = "";
			if(req.getParameter("idPontoTp") != null && !req.getParameter("idPontoTp").equals("0")) {
				sql = "WHERE  ID_PONTOTP = " + req.getParameter("idPontoTp") ;
			}						
			List<RelPonto> listRelPonto = relPontoDAO.listar(sql);
			
			RelMapaConsumoPressaoDAO relMapaConsumoPressaoDAO = new RelMapaConsumoPressaoDAO(connection);
			List<RelMapaConsumoPressao> listRelMapaConsumoPressao = relMapaConsumoPressaoDAO.listarPressureBridgeEmpresa(4l);
			for(int i = 0; i < listRelMapaConsumoPressao.size(); i++ ) {
				listRelMapaConsumoPressao.set(i, relMapaConsumoPressaoDAO.buscar(
						listRelMapaConsumoPressao.get(i).getIdEmpresa(), 
						listRelMapaConsumoPressao.get(i).getIdCondominio(), listRelMapaConsumoPressao.get(i).getIdBridge()));
			}
			listRelMapaConsumoPressao.remove(null);
			
			List<Object> listObject = new ArrayList<>();	
			listObject.add(listRelPonto);
			listObject.add(listRelMapaConsumoPressao);
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new Gson().toJson(listObject));
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
	
	public void buscarPonto(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		Connection connection = null;
		
		try {
			
			connection = ConnectionFactory.getConnection();			
			
			PontoTpDAO pontoTpDAO = new PontoTpDAO(connection);
			List<PontoTp> listPontoTp = pontoTpDAO.listar();
			
			RelPontoDAO relPontoDAO = new RelPontoDAO(connection);							
			RelPonto relPonto = relPontoDAO.buscarPorId(Long.parseLong(req.getParameter("idPonto")));
			
			List<Object> listObject = new ArrayList<>();
			listObject.add(listPontoTp);
			listObject.add(relPonto);
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new Gson().toJson(listObject));
		}
        catch (Exception e) {
        	System.out.println("Falha " + e.toString());
        	res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new Gson().toJson(e.toString()));
        }
		finally {
			if(connection != null) {
				try {connection.close();} catch (SQLException e) {}
			}
		}
	}
	
	public void cadastrarAlterarPonto(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		Connection connection = null;
		
		try {
			
		    Ponto ponto = new Ponto();
		    
		    ponto.setIdEmpresa(4l);
		    ponto.setIdPontoTp(Long.parseLong(req.getParameter("idPontoTp")));
		    ponto.setDescricao(req.getParameter("descricao"));
		    ponto.setCoordX(req.getParameter("coordX"));
		    ponto.setCoordY(req.getParameter("coordY"));
		    ponto.setDtInsert(null);
		    
		    connection = ConnectionFactory.getConnection();
		    
		    PontoDAO pontoDAO = new PontoDAO(connection);
		    if(req.getParameter("idPonto") != null && !req.getParameter("idPonto").isEmpty()) {
		    	ponto.setIdPonto(Long.parseLong(req.getParameter("idPonto")));
		    	pontoDAO.alterar(ponto);
		    }
		    else {
		    	pontoDAO.inserir(ponto);
		    }
		    
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new Gson().toJson("ok"));
		}
        catch (Exception e) {
        	System.out.println("Falha " + e.toString());
        	res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new Gson().toJson(e.toString()));
        }
		finally {
			if(connection != null) {
				try {connection.close();} catch (SQLException e) {}
			}
		}
	}
	
	public void buscarPontoTp(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		Connection connection = null;
		
		try {
			
		    connection = ConnectionFactory.getConnection();
		    
		    PontoTpDAO pontoTpDAO = new PontoTpDAO(connection);
			List<PontoTp> listPontoTp = pontoTpDAO.listar();
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new Gson().toJson(listPontoTp));
		}
        catch (Exception e) {
        	System.out.println("Falha " + e.toString());
        	res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new Gson().toJson(e.toString()));
        }
		finally {
			if(connection != null) {
				try {connection.close();} catch (SQLException e) {}
			}
		}
	}
	
	public void excluirPonto(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		Connection connection = null;
		
		try {
			
		    connection = ConnectionFactory.getConnection();		    
		    PontoDAO pontoDAO = new PontoDAO(connection);		    
		    pontoDAO.excluir(Long.parseLong(req.getParameter("idPonto")));
		    
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new Gson().toJson("ok"));
		}
        catch (Exception e) {
        	System.out.println("Falha " + e.toString());
        	res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new Gson().toJson(e.toString()));
        }
		finally {
			if(connection != null) {
				try {connection.close();} catch (SQLException e) {}
			}
		}
	}
	
}

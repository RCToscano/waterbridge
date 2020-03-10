
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

import com.google.gson.Gson;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.auxiliar.ColunasExcel;
import br.com.waterbridge.auxiliar.GeradorExcel;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.ConsumoDAO;
import br.com.waterbridge.dao.EmpresaDAO;
import br.com.waterbridge.dao.MedidorDAO;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.Empresa;
import br.com.waterbridge.modelo.Medidor;
import br.com.waterbridge.modelo.User;
import br.com.waterbridge.modelo.bo.RelConsumoCondominioBO;
import br.com.waterbridge.reldao.RelMedidorDAO;
import br.com.waterbridge.relmodelo.RelConsumoCondominio;
import br.com.waterbridge.relmodelo.RelMedidor;

public class ConsumoCondominioBO extends HttpServlet {

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
			User user = (User) req.getSession().getAttribute("user");
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				EmpresaDAO empresaDAO = new EmpresaDAO(connection);
				List<Empresa> listEmpresa = empresaDAO.listarPorUsuario(user.getIdUser());
				
				req.setAttribute("listEmpresa", listEmpresa);
        		req.getRequestDispatcher("/jsp/relatorios/consumocondominiodia.jsp").forward(req, res);
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
		
		//POPULA COMBO CONDOMINIO
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("2")) {
			Connection connection = null;
			User user = (User) req.getSession().getAttribute("user");
			try {
				connection = ConnectionFactory.getConnection();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = new ArrayList<Condominio>();		
				listCondominio = condominioDAO.listarPorUsuario(user.getIdUser(), Long.parseLong(req.getParameter("idEmpresa")));
				
				String json = new Gson().toJson(listCondominio);
				
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);   
			}
	        catch (Exception e) {
	        	System.out.println("erro " + e.toString());
	            req.setAttribute("erro", e.toString());
	            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
	        }
			finally {
				if(connection != null) {
					try {connection.close();} catch (SQLException e) {}
				}
			}	
        }
		
		//POPULA COMBO BRIDGE
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("3")) {
			Connection connection = null;
			User user = (User) req.getSession().getAttribute("user");
			try {
				connection = ConnectionFactory.getConnection();
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				List<Bridge> listBridge = new ArrayList<Bridge>();	
				listBridge = bridgeDAO.listarPorUsuario(user.getIdUser(), Long.parseLong(req.getParameter("idCondominio")));

				String json = new Gson().toJson(listBridge);
				
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);   
			}
	        catch (Exception e) {
	        	System.out.println("erro " + e.toString());
	            req.setAttribute("erro", e.toString());
	            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
	        }
			finally {
				if(connection != null) {
					try {connection.close();} catch (SQLException e) {}
				}
			}	
        }
		
		//POPULA COMBO MEDIDOR
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("4")) {
			Connection connection = null;
			User user = (User) req.getSession().getAttribute("user");
			try {
				connection = ConnectionFactory.getConnection();
				
				MedidorDAO medidorDAO = new MedidorDAO(connection);
				List<Medidor> listMedidor = new ArrayList<Medidor>();		
				listMedidor = medidorDAO.listarPorUsuario(user.getIdUser(), Long.parseLong(req.getParameter("idBridge")));

				String json = new Gson().toJson(listMedidor);
				
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);   
			}
	        catch (Exception e) {
	        	System.out.println("erro " + e.toString());
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("5")) {
			Connection connection = null;
            String sql = "";
            String json = "";
			
			try {

				sql += "WHERE ID_EMPRESA > 0 " ;
				if(req.getParameter("idEmpresa") != null && !req.getParameter("idEmpresa").equals("")) {
					sql += "AND   ID_EMPRESA = " + req.getParameter("idEmpresa") + " ";
				}
				if(req.getParameter("idCondominio") != null && !req.getParameter("idCondominio").equals("")) {
					sql += "AND   ID_CONDOMINIO = " + req.getParameter("idCondominio") + " ";
				}
				if(req.getParameter("idBridge") != null && !req.getParameter("idBridge").equals("")) {
					sql += "AND   ID_BRIDGE = " + req.getParameter("idBridge") + " ";
				}
				if(req.getParameter("idMedidor") != null && !req.getParameter("idMedidor").equals("")) {
					sql += "AND   ID_MEDIDOR = " + req.getParameter("idMedidor") + " ";
				}
				sql += "ORDER BY METERID ";

				connection = ConnectionFactory.getConnection();
				
				ConsumoDAO consumoDAO = new ConsumoDAO(connection);
				
				RelMedidorDAO relMedidorDAO = new RelMedidorDAO(connection);
				List<RelMedidor> listRelMedidor = relMedidorDAO.listar(sql);
				
				List<RelConsumoCondominio> listRelConsumoCondominio = new ArrayList<RelConsumoCondominio>();
				for(RelMedidor relMedidor: listRelMedidor) {
							
					RelConsumoCondominio relConsumoCondominio = new RelConsumoCondominio();
					relConsumoCondominio.setIdMedidor(relMedidor.getIdMedidor());
					relConsumoCondominio.setMeterId(relMedidor.getMeterId());
					relConsumoCondominio.setEndereco(relMedidor.getEndereco());
					relConsumoCondominio.setNumero(relMedidor.getNumero());
					relConsumoCondominio.setCompl(relMedidor.getCompl());
					relConsumoCondominio.setMunicipio(relMedidor.getMunicipio());
					relConsumoCondominio.setUf(relMedidor.getUf());
					relConsumoCondominio.setCep(relMedidor.getCep());
					relConsumoCondominio.setCoordX(relMedidor.getCoordX());
					relConsumoCondominio.setCoordY(relMedidor.getCoordY());
					relConsumoCondominio.setVolumeInicio(consumoDAO.buscarVolumeInicio(relMedidor.getIdMedidor(), Auxiliar.formataDtBanco(req.getParameter("dtInicio")) + " 00:00"));
					relConsumoCondominio.setVolumeFim(consumoDAO.buscarVolumeFim(relMedidor.getIdMedidor(), Auxiliar.formataDtBanco(req.getParameter("dtFim")) + " 23:59"));
					relConsumoCondominio.setConsumo(relConsumoCondominio.getVolumeFim() - relConsumoCondominio.getVolumeInicio());
					relConsumoCondominio.setListRelUserMedidor(relMedidor.getListRelUserMedidor());
					
					listRelConsumoCondominio.add(relConsumoCondominio);
				}
				
				
				RelConsumoCondominioBO condominioBO = new RelConsumoCondominioBO();
				if(!listRelMedidor.isEmpty())
					condominioBO.setNomeCondominio(listRelMedidor.get(0).getCondominio());
				condominioBO.setDtInicio(req.getParameter("dtInicio"));
				condominioBO.setDtFim(req.getParameter("dtFim"));
				condominioBO.setListRelConsumoCondominio(listRelConsumoCondominio);
				
				json = new Gson().toJson(condominioBO);
				
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
		
		//LISTAR CONSUMO MEDIDOR DIA GRAFICO
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("6")) {

			Connection connection = null;
            String sql = "";
			
			try {

				sql += "WHERE ID_EMPRESA > 0 " ;
				if(req.getParameter("idEmpresa") != null && !req.getParameter("idEmpresa").equals("")) {
					sql += "AND   ID_EMPRESA = " + req.getParameter("idEmpresa") + " ";
				}
				if(req.getParameter("idCondominio") != null && !req.getParameter("idCondominio").equals("")) {
					sql += "AND   ID_CONDOMINIO = " + req.getParameter("idCondominio") + " ";
				}
				if(req.getParameter("idBridge") != null && !req.getParameter("idBridge").equals("")) {
					sql += "AND   ID_BRIDGE = " + req.getParameter("idBridge") + " ";
				}
				if(req.getParameter("idMedidor") != null && !req.getParameter("idMedidor").equals("")) {
					sql += "AND   ID_MEDIDOR = " + req.getParameter("idMedidor") + " ";
				}
				sql += "ORDER BY METERID ";

				connection = ConnectionFactory.getConnection();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				Condominio condominio = condominioDAO.buscarPorId(Long.parseLong(req.getParameter("idCondominio")));
				
				ConsumoDAO consumoDAO = new ConsumoDAO(connection);
				
				RelMedidorDAO relMedidorDAO = new RelMedidorDAO(connection);
				List<RelMedidor> listRelMedidor = relMedidorDAO.listar(sql);
				
				List<RelConsumoCondominio> listRelConsumoCondominio = new ArrayList<RelConsumoCondominio>();
				for(RelMedidor relMedidor: listRelMedidor) {
							
					RelConsumoCondominio relConsumoCondominio = new RelConsumoCondominio();
					relConsumoCondominio.setIdMedidor(relMedidor.getIdMedidor());
					relConsumoCondominio.setMeterId(relMedidor.getMeterId());
					relConsumoCondominio.setEndereco(relMedidor.getEndereco());
					relConsumoCondominio.setNumero(relMedidor.getNumero());
					relConsumoCondominio.setCompl(relMedidor.getCompl());
					relConsumoCondominio.setMunicipio(relMedidor.getMunicipio());
					relConsumoCondominio.setUf(relMedidor.getUf());
					relConsumoCondominio.setCep(relMedidor.getCep());
					relConsumoCondominio.setCoordX(relMedidor.getCoordX());
					relConsumoCondominio.setCoordY(relMedidor.getCoordY());
					relConsumoCondominio.setVolumeInicio(consumoDAO.buscarVolumeInicio(relMedidor.getIdMedidor(), Auxiliar.formataDtBanco(req.getParameter("dtInicio")) + " 00:00"));
					relConsumoCondominio.setVolumeFim(consumoDAO.buscarVolumeFim(relMedidor.getIdMedidor(), Auxiliar.formataDtBanco(req.getParameter("dtFim")) + " 23:59"));
					relConsumoCondominio.setConsumo(relConsumoCondominio.getVolumeFim() - relConsumoCondominio.getVolumeInicio());
					relConsumoCondominio.setListRelUserMedidor(relMedidor.getListRelUserMedidor());
					
					listRelConsumoCondominio.add(relConsumoCondominio);
				}
				
				req.setAttribute("condominio", condominio);
				req.setAttribute("dtInicio", req.getParameter("dtInicio"));
				req.setAttribute("dtFim", req.getParameter("dtFim"));
				req.setAttribute("listRelConsumoCondominio", listRelConsumoCondominio);
        		req.getRequestDispatcher("/jsp/relatorios/consumocondominiodiagrafico.jsp").forward(req, res);   
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
		

		//Excel
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("excel")) {
			Connection connection = null;
			try {

				String sql = "WHERE ID_EMPRESA > 0 " ;
				if(req.getParameter("idEmpresa") != null && !req.getParameter("idEmpresa").equals("")) {
					sql += "AND   ID_EMPRESA = " + req.getParameter("idEmpresa") + " ";
				}
				if(req.getParameter("idCondominio") != null && !req.getParameter("idCondominio").equals("")) {
					sql += "AND   ID_CONDOMINIO = " + req.getParameter("idCondominio") + " ";
				}
				if(req.getParameter("idBridge") != null && !req.getParameter("idBridge").equals("")) {
					sql += "AND   ID_BRIDGE = " + req.getParameter("idBridge") + " ";
				}
				if(req.getParameter("idMedidor") != null && !req.getParameter("idMedidor").equals("")) {
					sql += "AND   ID_MEDIDOR = " + req.getParameter("idMedidor") + " ";
				}
				sql += "ORDER BY METERID ";

				connection = ConnectionFactory.getConnection();
				
				ConsumoDAO consumoDAO = new ConsumoDAO(connection);
				
				RelMedidorDAO relMedidorDAO = new RelMedidorDAO(connection);
				List<RelMedidor> listRelMedidor = relMedidorDAO.listar(sql);
				
				String empresa = "";
				String condominio = "";
				List<RelConsumoCondominio> listRelConsumoCondominio = new ArrayList<RelConsumoCondominio>();
				for(RelMedidor relMedidor: listRelMedidor) {
					empresa = relMedidor.getEmpresa();
					condominio = relMedidor.getCondominio();
					
					RelConsumoCondominio relConsumoCondominio = new RelConsumoCondominio();
					relConsumoCondominio.setIdMedidor(relMedidor.getIdMedidor());
					relConsumoCondominio.setDevice(relMedidor.getDeviceNum());
					relConsumoCondominio.setMeterId(relMedidor.getMeterId());
					relConsumoCondominio.setEndereco(relMedidor.getEndereco());
					relConsumoCondominio.setNumero(relMedidor.getNumero());
					relConsumoCondominio.setCompl(relMedidor.getCompl());
					relConsumoCondominio.setMunicipio(relMedidor.getMunicipio());
					relConsumoCondominio.setUf(relMedidor.getUf());
					relConsumoCondominio.setCep(relMedidor.getCep());
					relConsumoCondominio.setCoordX(relMedidor.getCoordX());
					relConsumoCondominio.setCoordY(relMedidor.getCoordY());
					relConsumoCondominio.setVolumeInicio(consumoDAO.buscarVolumeInicio(relMedidor.getIdMedidor(), Auxiliar.formataDtBanco(req.getParameter("dtInicio")) + " 00:00"));
					relConsumoCondominio.setVolumeFim(consumoDAO.buscarVolumeFim(relMedidor.getIdMedidor(), Auxiliar.formataDtBanco(req.getParameter("dtFim")) + " 23:59"));
					relConsumoCondominio.setConsumo(relConsumoCondominio.getVolumeFim() - relConsumoCondominio.getVolumeInicio());
					relConsumoCondominio.setListRelUserMedidor(relMedidor.getListRelUserMedidor());
					listRelConsumoCondominio.add(relConsumoCondominio);
				}
				
				List<List<String>> colunas = new ArrayList<>();
				List<String> abas = new ArrayList<String>();
				List<List<List<String>>> listaFinal = new ArrayList<>();
				
        		//Dados
	        	abas.add("Dados");
	        	
	        	colunas.add(new ColunasExcel().getColunasRelCondominioDados());
	        	
	        	List<List<String>> lista2 = new ArrayList<>();
	        	for (int i = 0; i < listRelConsumoCondominio.size(); i++) {
	        		List<String> listaValores2 = new ArrayList<>();
	        		recuperaDados(listRelConsumoCondominio, i, listaValores2);
	        		lista2.add(listaValores2);
	        	}
	        	listaFinal.add(lista2);
	        	
	        	//Aba Resumo
        		abas.add("Resumo");
        		
        		colunas.add(new ColunasExcel().getColunasRelCondominioResumo());
        		
        		List<List<String>> lista1 = new ArrayList<>();
    			List<String> listaValores1 = new ArrayList<>();
    			listaValores1.add(empresa);
    			listaValores1.add(condominio);
    			listaValores1.add(req.getParameter("dtInicio"));
    			listaValores1.add(req.getParameter("dtFim"));
    			lista1.add(listaValores1);
        		listaFinal.add(lista1);
        		
        		String nomeCondominio = "Relatorio_Consumo_Local";
        		nomeCondominio = GeradorExcel.nomeArquivo(condominio);
        		String nomeArquivo = nomeCondominio+"_"+Auxiliar.dataAtual()+".xlsx";
        		
	        	GeradorExcel.gerar2Abas(res, nomeArquivo, abas, colunas, listaFinal);
				
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
	
	private void recuperaDados(List<RelConsumoCondominio> listaView, int i, List<String> listaValores2) {
		listaValores2.add(String.valueOf(i+1));
		listaValores2.add(listaView.get(i).getDevice());
		listaValores2.add(listaView.get(i).getMeterId());
		listaValores2.add(listaView.get(i).getEndereco() + " " +listaView.get(i).getNumero() + " " + listaView.get(i).getCompl());
		listaValores2.add(String.valueOf(listaView.get(i).getVolumeInicio()));
		listaValores2.add(String.valueOf(listaView.get(i).getVolumeFim()));
		listaValores2.add(String.valueOf(listaView.get(i).getConsumo()));
		listaValores2.add("");
	}
	
}


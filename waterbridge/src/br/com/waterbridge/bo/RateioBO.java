
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
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.ConsumoDAO;
import br.com.waterbridge.dao.ContaDAO;
import br.com.waterbridge.dao.ContaRateioDAO;
import br.com.waterbridge.dao.EmpresaDAO;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.Conta;
import br.com.waterbridge.modelo.ContaRateio;
import br.com.waterbridge.modelo.Empresa;
import br.com.waterbridge.modelo.User;
import br.com.waterbridge.reldao.RelMedidorDAO;
import br.com.waterbridge.relmodelo.RelConsumoCondominio;
import br.com.waterbridge.relmodelo.RelMedidor;

public class RateioBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                   
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {//ENTRA NA TELA RATEIO LISTA
			
			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				EmpresaDAO empresaDAO = new EmpresaDAO(connection);
				List<Empresa> listEmpresa = empresaDAO.listarPorUsuario(user.getIdUser());
				
				req.setAttribute("listEmpresa", listEmpresa);
        		req.getRequestDispatcher("/jsp/rateio/rateiocondominioexecutar.jsp").forward(req, res);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("2")) {//POPULA COMBO CONDOMINIO

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String sql = "";
            String json = "";
			
			try {
			
				connection = ConnectionFactory.getConnection();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = new ArrayList<Condominio>();		
				listCondominio = condominioDAO.listarPorUsuario(user.getIdUser(), Long.parseLong(req.getParameter("idEmpresa")));
				
				json = new Gson().toJson(listCondominio);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("3")) { //POPULA COMBO CONTA

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String json = "";
			
			try {
			
				connection = ConnectionFactory.getConnection();
				
				ContaDAO contaDAO = new ContaDAO(connection);
				List<Conta> listConta = new ArrayList<Conta>();	
				listConta = contaDAO.listarPorCondominio(Long.parseLong(req.getParameter("idCondominio")));

				json = new Gson().toJson(listConta);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("4")) { //LISTAR CONSUMO MEDIDOR RATEIO

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
				
				ContaDAO contaDAO = new ContaDAO(connection);
				Conta conta = contaDAO.buscar(Long.parseLong(req.getParameter("idConta")));
				
				String dtInicio = contaDAO.addDiaData(Auxiliar.formataDtBanco(conta.getDtLeituraAnterior()), 1l);
				String dtFim = Auxiliar.formataDtBanco(conta.getDtLeituraAtual());
				
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
					relConsumoCondominio.setVolumeInicio(consumoDAO.buscarVolumeInicio(relMedidor.getIdMedidor(), dtInicio + " 00:00"));
					relConsumoCondominio.setVolumeFim(consumoDAO.buscarVolumeFim(relMedidor.getIdMedidor(), dtFim + " 23:59"));
					relConsumoCondominio.setConsumo(relConsumoCondominio.getVolumeFim() - relConsumoCondominio.getVolumeInicio());
					relConsumoCondominio.setListRelUserMedidor(relMedidor.getListRelUserMedidor());
					
					listRelConsumoCondominio.add(relConsumoCondominio);
				}
				
				json = new Gson().toJson(listRelConsumoCondominio);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("5")) { //EXECUTAR RATEIO

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				ContaRateioDAO contaRateioDAO = new ContaRateioDAO(connection);
				
				ContaDAO contaDAO = new ContaDAO(connection);
				Conta conta = contaDAO.buscar(Long.parseLong(req.getParameter("idConta")));
				
				Integer qtdeMedidorRateioS = 0;
				Integer qtdeMedidorRateioN = 0;
				Double consumoTotal = Double.parseDouble(req.getParameter("consumoTotal"));
				Double consumoComum = 0d;
				Double consumoReal = 0d;
				Double consumoRateio = 0d;
				Double valorRateio = 0d;
				Double percRateio = 0d;
				
				for(int i = 1; i < Integer.parseInt(req.getParameter("cont")); i++ ) {
					
					if(req.getParameter("ckMedidor" + i) == null) {
						
						System.out.println("NAO INCLUIR");
						System.out.println("idMedidor " + req.getParameter("idMedidor" + i));
						System.out.println("idEmpresa " + req.getParameter("idEmpresa" + i));
						System.out.println("idCondominio " + req.getParameter("idCondominio" + i));
						System.out.println("volumeInicio " + req.getParameter("volumeInicio" + i));
						System.out.println("volumeFim " + req.getParameter("volumeFim" + i));
						System.out.println("consumo " + req.getParameter("consumo" + i));
						System.out.println("obs " + req.getParameter("obs" + i));
						System.out.println("************************************ ");
						
						consumoComum += Double.parseDouble(req.getParameter("consumo" + i));
						qtdeMedidorRateioN++;						
					}
					else {
						
						System.out.println("INCLUIR");
						System.out.println("idMedidor " + req.getParameter("idMedidor" + i));
						System.out.println("idEmpresa " + req.getParameter("idEmpresa" + i));
						System.out.println("idCondominio " + req.getParameter("idCondominio" + i));
						System.out.println("volumeInicio " + req.getParameter("volumeInicio" + i));
						System.out.println("volumeFim " + req.getParameter("volumeFim" + i));
						System.out.println("consumo " + req.getParameter("consumo" + i));
						System.out.println("obs " + req.getParameter("obs" + i));
						System.out.println("************************************ ");
						
						qtdeMedidorRateioS++;
					}
				}
				
				consumoComum = consumoComum / qtdeMedidorRateioS;
				
				for(int i = 1; i < Integer.parseInt(req.getParameter("cont")); i++ ) {
					
					if(req.getParameter("ckMedidor" + i) == null) {
				
						ContaRateio contaRateio = new ContaRateio();
						contaRateio.setIdContaRateio(0l);
						contaRateio.setIdConta(conta.getIdConta());
						contaRateio.setIdEmpresa(Long.parseLong(req.getParameter("idEmpresa" + i)));
						contaRateio.setIdCondominio(Long.parseLong(req.getParameter("idCondominio" + i)));
						contaRateio.setIdMedidor(Long.parseLong(req.getParameter("idMedidor" + i)));
						contaRateio.setIdUser(user.getIdUser());
						contaRateio.setVolumeInicial(Double.parseDouble(req.getParameter("volumeInicio" + i)));
						contaRateio.setVolumeFinal(Double.parseDouble(req.getParameter("volumeFim" + i)));						
						contaRateio.setConsumoReal(Double.parseDouble(req.getParameter("consumo" + i)));
						contaRateio.setConsumoRateio(0d);
						contaRateio.setValorRateio(0d);
						contaRateio.setPercRateio(0d);
						contaRateio.setObs(Auxiliar.removerCaracteres(req.getParameter("obs" + i)).toUpperCase());
						contaRateio.setDtInsert(null);
						
						contaRateioDAO.inserir(contaRateio);
					}
					else {
						
						consumoReal = Double.parseDouble(req.getParameter("consumo" + i));
						consumoRateio = consumoReal + consumoComum;
						percRateio = consumoRateio * 100 / consumoTotal;
						valorRateio = conta.getValor() * (percRateio / 100);
						
						ContaRateio contaRateio = new ContaRateio();
						contaRateio.setIdContaRateio(0l);
						contaRateio.setIdConta(conta.getIdConta());
						contaRateio.setIdEmpresa(Long.parseLong(req.getParameter("idEmpresa" + i)));
						contaRateio.setIdCondominio(Long.parseLong(req.getParameter("idCondominio" + i)));
						contaRateio.setIdMedidor(Long.parseLong(req.getParameter("idMedidor" + i)));
						contaRateio.setIdUser(user.getIdUser());
						contaRateio.setVolumeInicial(Double.parseDouble(req.getParameter("volumeInicio" + i)));
						contaRateio.setVolumeFinal(Double.parseDouble(req.getParameter("volumeFim" + i)));
						contaRateio.setConsumoReal(consumoReal);
						contaRateio.setConsumoRateio(consumoRateio);
						contaRateio.setValorRateio(valorRateio);
						contaRateio.setPercRateio(percRateio);
						contaRateio.setObs(Auxiliar.removerCaracteres(req.getParameter("obs" + i)).toUpperCase());
						contaRateio.setDtInsert(null);
						
						contaRateioDAO.inserir(contaRateio);
					}
				}
				
				System.out.println("qtdeMedidorRateioN " + qtdeMedidorRateioN);
				System.out.println("qtdeMedidorRateioS " + qtdeMedidorRateioS);
				System.out.println("consumoComum " + consumoComum);
				
				EmpresaDAO empresaDAO = new EmpresaDAO(connection);
				List<Empresa> listEmpresa = empresaDAO.listarPorUsuario(user.getIdUser());
				
				req.setAttribute("listEmpresa", listEmpresa);
				req.getRequestDispatcher("/jsp/rateio/rateiocondominioexecutar.jsp").forward(req, res); 
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




package br.com.andwaterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.auxiliar.ColunasExcel;
import br.com.waterbridge.auxiliar.Constantes;
import br.com.waterbridge.auxiliar.GeradorExcel;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.ConsumoDAO;
import br.com.waterbridge.dao.LogSqlDAO;
import br.com.waterbridge.dao.MedidorDAO;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.Consumo;
import br.com.waterbridge.modelo.Medidor;
import br.com.waterbridge.modelo.User;
import br.com.waterbridge.reldao.RelConsumoMedidorDAO;
import br.com.waterbridge.reldao.RelPressaoDAO;
import br.com.waterbridge.relmodelo.RelConsumoMedidor;
import br.com.waterbridge.relmodelo.RelPressao;

public class AndRelatorioBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
		//LISTAR CONSUMO MEDIDOR DIA
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {

			Connection connection = null;
            String sql = "";
			String data = "";
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				ConsumoDAO consumoDAO = new ConsumoDAO(connection);
				
				sql += "WHERE ID_CONSUMO > 0 " ;
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
				if(req.getParameter("data") == null) {
					
					data = consumoDAO.dataHoraMinSeg();
					data = data.substring(0, 10);
					
					sql += "AND   DTINSERT >= '" + consumoDAO.dataAdd(data, "0") + " 00:00' " +
						   "AND   DTINSERT <= '" + consumoDAO.dataAdd(data, "0") + " 23:59' " ;
					req.setAttribute("data", data);
				}
				else {
					
					data = consumoDAO.dataAdd(req.getParameter("data"), req.getParameter("sinal") + 1);					
					sql += "AND   DTINSERT >= '" + data + " 00:00' " +
						   "AND   DTINSERT <= '" + data + " 23:59' " ;
					req.setAttribute("data", data);
				}
				sql += "ORDER BY DTINSERT, " +
				       "         VOLUME ";
				
				Consumo consumoAnterior = consumoDAO.buscarAnterior(Long.parseLong(req.getParameter("idMedidor")), data + " 00:00");				
				Double volume1 = 0d;
				Double volume2 = 0d;

				List<RelConsumoMedidor> listRelConsumoMedidor = dadosTela(connection, sql, consumoAnterior, volume1, volume2);
                
                req.setAttribute("idEmpresa", req.getParameter("idEmpresa"));
                req.setAttribute("idCondominio", req.getParameter("idCondominio"));
                req.setAttribute("idBridge", req.getParameter("idBridge"));
                req.setAttribute("idMedidor", req.getParameter("idMedidor"));
                req.setAttribute("data", data);
				req.setAttribute("listRelConsumoMedidor", listRelConsumoMedidor);
        		req.getRequestDispatcher("/jspapp/consumomedidordia.jsp").forward(req, res);
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
		//LISTAR PRESSAO PRESSURE BRIDGE
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("2")) {

			Connection connection = null;
            String sql = "";
			String data = "";
			
			try {
				
				connection = ConnectionFactory.getConnection();
				ConsumoDAO consumoDAO = new ConsumoDAO(connection);
				
				sql = "WHERE ID_BRIDGETP <> 3 " ;
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
				if(req.getParameter("data") == null && req.getParameter("data") == null) {
					
					data = consumoDAO.dataHoraMinSeg();
					data = data.substring(0, 10);
					
					sql += "AND   DTINSERT >= '" + consumoDAO.dataAdd(data, "0") + " 00:00' " +
						   "AND   DTINSERT <= '" + consumoDAO.dataAdd(data, "0") + " 23:59' " ;
					req.setAttribute("data", data);
				}
				else {
					
					data = consumoDAO.dataAdd(req.getParameter("data"), req.getParameter("sinal") + 1);					
					sql += "AND   DTINSERT >= '" + data + " 00:00' " +
						   "AND   DTINSERT <= '" + data + " 23:59' " ;
					req.setAttribute("data", data);
				}
				sql += "ORDER BY DTINSERT ";

				RelPressaoDAO relPressaoDAODAO = new RelPressaoDAO(connection);
				List<RelPressao> listRelPressao = relPressaoDAODAO.listar(sql);
                
                req.setAttribute("idEmpresa", req.getParameter("idEmpresa"));
                req.setAttribute("idCondominio", req.getParameter("idCondominio"));
                req.setAttribute("idBridge", req.getParameter("idBridge"));
                req.setAttribute("idMedidor", req.getParameter("idMedidor"));
                
				req.setAttribute("listRelPressao", listRelPressao);
        		req.getRequestDispatcher("/jspapp/pressaobridgedia.jsp").forward(req, res);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("3")) {

			Connection connection = null;
            String sql = "";
			
			try {

				connection = ConnectionFactory.getConnection();
				ConsumoDAO consumoDAO = new ConsumoDAO(connection);
				
				sql += "WHERE ID_CONSUMO > 0 " ;
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
				if(req.getParameter("data") != null) {
										
					sql += "AND   DTINSERT >= '" + req.getParameter("data") + " 00:00' " +
						   "AND   DTINSERT <= '" + req.getParameter("data") + " 23:59' " ;					
				}
				sql += "ORDER BY DTINSERT ";
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				Bridge bridge = bridgeDAO.buscarPorId(Long.parseLong(req.getParameter("idBridge")));
				
				MedidorDAO medidorDAO = new MedidorDAO(connection);
				Medidor medidor = medidorDAO.buscarPorId(req.getParameter("idMedidor"));
				
//				RelConsumoMedidorDAO relConsumoMedidorDAO = new RelConsumoMedidorDAO(connection);
//				List<RelConsumoMedidor> listRelConsumoMedidor = relConsumoMedidorDAO.listar(sql);
				
				consumoDAO = new ConsumoDAO(connection);
				Consumo consumoAnterior = consumoDAO.buscarAnterior(Long.parseLong(req.getParameter("idMedidor")), req.getParameter("data") + " 00:00");				
				Double volume1 = 0d;
				Double volume2 = 0d;

				List<RelConsumoMedidor> listRelConsumoMedidor = dadosTela(connection, sql, consumoAnterior, volume1, volume2);
				
				req.setAttribute("bridge", bridge);
				req.setAttribute("medidor", medidor);
				req.setAttribute("data", Auxiliar.formataDtTela(req.getParameter("data")));
				req.setAttribute("listRelConsumoMedidor", listRelConsumoMedidor);
        		req.getRequestDispatcher("/jspapp/consumomedidordiagrafico.jsp").forward(req, res);   
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("4")) {
			
			Connection connection = null;
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
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
				if(req.getParameter("data") != null) {
					
					sql += "AND   DTINSERT >= '" + req.getParameter("data") + " 00:00' " +
						   "AND   DTINSERT <= '" + req.getParameter("data") + " 23:59' " ;
				}
				sql += "ORDER BY DTINSERT ";

				RelPressaoDAO relPressaoDAODAO = new RelPressaoDAO(connection);
				List<RelPressao> listRelPressao = relPressaoDAODAO.listar(sql);
				
				String bridge = "";
				for(int i = 0; i < listRelPressao.size(); i++) {					
					RelPressao relPressao = listRelPressao.get(i);
					if(i == 0) {
						bridge = relPressao.getDevice();
					}					
				}
				
				req.setAttribute("bridge", bridge);
				req.setAttribute("data", Auxiliar.formataDtTela(req.getParameter("data")));
				req.setAttribute("listRelPressao", listRelPressao);
        		req.getRequestDispatcher("/jspapp/relatoriopressaografico.jsp").forward(req, res);   
			}
	        catch (Exception e) {
	        	System.out.println(e);
	        	try {
					new LogSqlDAO(connection).inserir(((User) req.getSession().getAttribute("user")).getIdUser(),
							"", e.getMessage(), "UsuarioBO", "AndRelatorioBO?acao=4");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
        		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
	            req.getRequestDispatcher("/jsp/relatorios/relatorioPressao.jsp").forward(req, res);
	        }
        }
		
		//Excel
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("excel")) {
			
			Connection connection = null;
			String sql = "";
			
			try {
				
				sql += "WHERE ID_CONSUMO > 0 " ;
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
				if(req.getParameter("dtInicio") != null && !req.getParameter("dtInicio").equals("")) {
					
					sql += "AND   DTINSERT >= '" + Auxiliar.formataDtBanco(req.getParameter("dtInicio")) + " 00:00' " +
							"AND   DTINSERT <= '" + Auxiliar.formataDtBanco(req.getParameter("dtFim")) + " 23:59' " ;
				}
				sql += "ORDER BY DTINSERT ";
				
				connection = ConnectionFactory.getConnection();
				
				ConsumoDAO consumoDAO = new ConsumoDAO(connection);
				Consumo consumoAnterior = consumoDAO.buscarAnterior(Long.parseLong(req.getParameter("idMedidor")),
						Auxiliar.formataDtBanco(req.getParameter("dtInicio")) + " 00:00");				
				Double volume1 = 0d;
				Double volume2 = 0d;
				
				List<RelConsumoMedidor> listRelConsumoMedidor = dadosTela(connection, sql, consumoAnterior, volume1, volume2);
				
				List<String> abas = new ArrayList<String>();
				List<List<String>> colunas = new ArrayList<>();
				List<List<List<String>>> listaFinal = new ArrayList<>();
				
        		//Dados
	        	abas.add("Dados");
	        	
	        	colunas.add(new ColunasExcel().getColunasRelMedidorDados());
	        	
	        	List<List<String>> lista2 = new ArrayList<>();
	        	for (int i = 0; i < listRelConsumoMedidor.size(); i++) {
	        		List<String> listaValores2 = new ArrayList<>();
	        		recuperaDados(listRelConsumoMedidor, i, listaValores2);
	        		lista2.add(listaValores2);
	        	}
	        	listaFinal.add(lista2);
	        	
	        	
	        	//Aba Resumo
        		abas.add("Resumo");
        		
        		colunas.add(new ColunasExcel().getColunasRelMedidorResumo());
        		
        		List<List<String>> lista1 = new ArrayList<>();
    			List<String> listaValores1 = new ArrayList<>();
    			listaValores1.add(listRelConsumoMedidor.get(0).getEmpresa());
    			listaValores1.add(listRelConsumoMedidor.get(0).getCondominio());
    			listaValores1.add(listRelConsumoMedidor.get(0).getDevice());
    			listaValores1.add(listRelConsumoMedidor.get(0).getNumeroMedidor());
    			listaValores1.add(req.getParameter("dtInicio"));
    			listaValores1.add(req.getParameter("dtFim"));
    			lista1.add(listaValores1);
        		listaFinal.add(lista1);
        		
        		String nomeArquivo = "Relatorio_Consumo_Medidor_"+Auxiliar.dataAtual()+".xlsx";
        		
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

	private List<RelConsumoMedidor> dadosTela(Connection connection, String sql, Consumo consumoAnterior,
			Double volume1, Double volume2) throws Exception {
		if(consumoAnterior != null 
				&& consumoAnterior.getVolume().doubleValue() != 0 
				&& consumoAnterior.getVolume().doubleValue() != 0.0) {
			volume1 = consumoAnterior.getVolume();
		}

		RelConsumoMedidorDAO relConsumoMedidorDAO = new RelConsumoMedidorDAO(connection);
		List<RelConsumoMedidor> listRelConsumoMedidor = relConsumoMedidorDAO.listar(sql);
		for(int i =0 ; i < listRelConsumoMedidor.size(); i++) {
			
			RelConsumoMedidor relConsumoMedidor = listRelConsumoMedidor.get(i);
			if(relConsumoMedidor.getAlarm().longValue() == 1 
					|| relConsumoMedidor.getAlarmDesc() == null
					|| relConsumoMedidor.getVolume().doubleValue() < volume1.doubleValue()) {
				
				relConsumoMedidor.setConsumo(0d);
			}
			else {
				
				volume2 = relConsumoMedidor.getVolume();
				relConsumoMedidor.setConsumo(volume2 - volume1);
				volume1 = volume2;
			}
		}
		return listRelConsumoMedidor;
	}
	
	private void recuperaDados(List<RelConsumoMedidor> listaView, int i, List<String> listaValores2) throws ParseException {
		SimpleDateFormat formatoBanco = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		
		listaValores2.add(String.valueOf(i+1));
		listaValores2.add(formatoData.format(formatoBanco.parse(listaView.get(i).getDtInsert())));
		listaValores2.add(formatoHora.format(formatoBanco.parse(listaView.get(i).getDtInsert())));
		listaValores2.add(String.valueOf(listaView.get(i).getVolume()));
		listaValores2.add(String.valueOf(listaView.get(i).getPressure()));
		listaValores2.add(listaView.get(i).getAlarmDesc());
		listaValores2.add(String.valueOf(listaView.get(i).getBattery()));
		listaValores2.add(String.valueOf(listaView.get(i).getTemperature()));
	}
	
	
}






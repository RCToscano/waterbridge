
package br.com.waterbridge.bo;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.LogSqlDAO;
import br.com.waterbridge.dao.MessageDAO;
import br.com.waterbridge.modelo.Message;

public class MessageBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection connection;
	StringBuilder sb;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			
//            if (req.getParameter("acao").equals("1")) {
//            	req.getRequestDispatcher("/jsp/grafico/graficoconsumomes.jsp").forward(req, res);
//            }  
            
			connection = ConnectionFactory.getConnection();
			sb = new StringBuilder();
	    	
	    	String linha = null;
	    	while((linha = req.getReader().readLine()) != null) {	    
	    		sb.append(linha);
	    	}
	    		    	
	    	Message message = new Gson().fromJson(sb.toString(), Message.class);
	    	message.setData("10017335000001700017A508");//SOBREPONDO O DATA ATE QUE O FELIPE ALTERE O FRAME ENVIADO

			String dataVersion = message.getData().substring(0, 2);
	        String dataMeterPosition = message.getData().substring(2, 4);
	        String dataVolume = message.getData().substring(4, 12);
	        //ROTACIONAR VOLUME        
	        dataVolume = dataVolume.substring(6, 8) +
	                     dataVolume.substring(4, 6) +
	                     dataVolume.substring(2, 4) +
	                     dataVolume.substring(0, 2) ;
	        String dataPressure = message.getData().substring(12, 16);
	        //ROTACIONAR PRESSAO        
	        dataPressure = dataPressure.substring(2, 4) +
	        		       dataPressure.substring(0, 2) ;        
	        String dataFlow = message.getData().substring(16, 18);        
	        String dataTemperature = message.getData().substring(18, 20);
	        String dataBattery = message.getData().substring(20, 22);
	        String dataAlarme = message.getData().substring(22, 24);
	        
	        System.out.println("dataVersion " + dataVersion);
			System.out.println("dataMeterPosition " + dataMeterPosition);
			System.out.println("dataVolume " + dataVolume);
			System.out.println("dataPressure " + dataPressure);
			System.out.println("dataFlow " + dataFlow);
			System.out.println("dataTemperature " + dataTemperature);
			System.out.println("dataBattery " + dataBattery);
			System.out.println("dataAlarme " + dataAlarme);
			
			BigInteger biVersion = new BigInteger(dataVersion, 16);
			BigInteger biMeterPosition = new BigInteger(dataMeterPosition, 16);
			BigInteger biVolume = new BigInteger(dataVolume, 16);		
			biVolume = new BigInteger(biVolume.toString(10));//CONVERTE VOLUME DECIMAL
			BigInteger biPressure = new BigInteger(dataPressure, 16);
			BigInteger biFlow = new BigInteger(dataFlow, 16);
			BigInteger biTemperature = new BigInteger(dataTemperature, 16);
			BigInteger biBattery = new BigInteger(dataBattery, 16);
			BigInteger biAlarme = new BigInteger(dataAlarme, 16);
			
			Connection connection = ConnectionFactory.getConnection();
			MessageDAO messageDAO = new MessageDAO(connection);
			
			DecimalFormatSymbols dfs = new DecimalFormatSymbols();
			dfs.setDecimalSeparator('.');
			DecimalFormat df = new DecimalFormat("0.##", dfs);
			
			message.setIdMessage(0l);
			message.setIdUser(4l);
			//message.setDevice();
			//message.setData(data);
			message.setVersion(biVersion.toString(10));
			message.setMeterPosition(Long.parseLong(biMeterPosition.toString(10)));
			message.setVolume(Double.parseDouble(biVolume.toString(10)));
			message.setVolume(message.getVolume() / 1000);
			message.setVolume(Double.parseDouble(df.format(message.getVolume())));
			message.setPressure(Double.parseDouble(biPressure.toString(10)));
			message.setPressure(message.getPressure() / 900);
			message.setPressure(Double.parseDouble(df.format(message.getPressure())));
			message.setFlow(Long.parseLong(biFlow.toString(10)));
			message.setTemperature(Long.parseLong(biTemperature.toString(10)));
			message.setBattery(Double.parseDouble(biBattery.toString(10)));
			message.setBattery(message.getBattery() / 50);
			message.setBattery(Double.parseDouble(df.format(message.getBattery())));
			message.setAlarm(Long.parseLong(biAlarme.toString(10)));
			message.setConsumo(0D);
			message.setVazao(0D);
			message.setDtInsert(messageDAO.dataHoraMinSeg());		
			
			System.out.println("");
			System.out.println("getIdMessage " + message.getIdMessage());
			System.out.println("getIdUser " + message.getIdUser());
			System.out.println("getData " + message.getData());
			System.out.println("getVersion " + message.getVersion());
			System.out.println("getMeterPosition " + message.getMeterPosition());
			System.out.println("getVolume " + message.getVolume());
			System.out.println("getPressure " + message.getPressure());
			System.out.println("getFlow " + message.getFlow());
			System.out.println("getTemperature " + message.getTemperature());
			System.out.println("getBattery " + message.getBattery());
			System.out.println("getAlarm " + message.getAlarm());
			System.out.println("getConsumo " + message.getConsumo());
			System.out.println("getVazao " + message.getVazao());
			System.out.println("getDtInsert " + message.getDtInsert());

			//BUSCA MENSAGEM ANTERIOR
			//Message messageAnt = messageDAO.buscarUltimoPorMeterId(message.getMeterId());
			Message messageAnt = messageDAO.buscarUltimo(message.getDevice(), message.getMeterPosition());

			//CALCULA CONSUMO
			if(messageAnt != null) {
				message.setConsumo(message.getVolume() - messageAnt.getVolume());
				message.setConsumo(Double.parseDouble(df.format(message.getConsumo())));
			}
			
			//CALCULA VAZAO
			if(messageAnt != null) {
				Long qtdeMinutos = messageDAO.difDataEmMinutos(messageAnt.getDtInsert(), message.getDtInsert());
				Double vazao = 0D;
				if(message.getConsumo().longValue() > 0 && qtdeMinutos > 0) {
					vazao = message.getConsumo() / qtdeMinutos;
					message.setVazao(Double.parseDouble(df.format(vazao)));
				}
			}
			
			//INSERIR
			messageDAO.inserir(message);
			
            String json = "ok";
            
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(json); 
        }
        catch (Exception e) {
        	if(e.toString().length() > 1000) {
        		try {new LogSqlDAO(connection).inserir(4l, e.toString().substring(0, 999), sb.toString(), "", "");} catch (SQLException e1) {e1.printStackTrace();}
        	}
        	else {
        		try {new LogSqlDAO(connection).inserir(4l, e.toString(), sb.toString(), "", "");} catch (SQLException e1) {e1.printStackTrace();}
        	}
        	res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write("erro");
        }
		finally {
			if(connection != null) {
				try {connection.close();} catch (SQLException ex) {}
			}
//			if(sb != null) {
//				sb.delete(0, sb.toString().length());
//			}
		}
    }
}



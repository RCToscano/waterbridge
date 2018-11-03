package br.com.waterbridge.auxiliar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import br.com.andwaterbridge.dao.AlarmPressaoDAO;
import br.com.andwaterbridge.dao.MetaPressaoDAO;
import br.com.andwaterbridge.modelo.AlarmPressao;
import br.com.andwaterbridge.modelo.MetaPressao;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.ConsumoDAO;
import br.com.waterbridge.dao.LogSqlDAO;
import br.com.waterbridge.dao.MedidorDAO;
import br.com.waterbridge.dao.MessageDAO;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.Consumo;
import br.com.waterbridge.modelo.Message;

public class Frame {

	public static void main(String[] args) {
		
		Connection connection = null;
		StringBuilder sb = null;
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\wagner\\Documents\\Wagner\\Projeto Felipe\\ProcessamentoFrame20181026\\405619_20181026.txt"));
			while(br.ready()){
				
				String linha = br.readLine();
				String [] arr = linha.split("\t");
				//System.out.print(arr[0] + "|" + arr[1] + "|" + arr[2]);
				
				Message message = new Message();	    	
		    	message.setData(arr[0]);//SOBREPONDO O DATA ATE QUE O FELIPE ALTERE O FRAME ENVIADO
		    	message.setDevice(arr[1]);//SOBREPONDO O DATA ATE QUE O FELIPE ALTERE O FRAME ENVIADO
		    	
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
		        
				BigInteger biVersion = new BigInteger(dataVersion, 16);
				BigInteger biMeterPosition = new BigInteger(dataMeterPosition, 16);
				BigInteger biVolume = new BigInteger(dataVolume, 16);		
				biVolume = new BigInteger(biVolume.toString(10));//CONVERTE VOLUME DECIMAL
				BigInteger biPressure = new BigInteger(dataPressure, 16);
				BigInteger biFlow = new BigInteger(dataFlow, 16);
				BigInteger biTemperature = new BigInteger(dataTemperature, 16);
				BigInteger biBattery = new BigInteger(dataBattery, 16);
				BigInteger biAlarme = new BigInteger(dataAlarme, 16);
				
				
				MessageDAO messageDAO = new MessageDAO(connection);
				ConsumoDAO consumoDAO = new ConsumoDAO(connection);
				MedidorDAO medidorDAO = new MedidorDAO(connection);
				
				DecimalFormatSymbols dfs = new DecimalFormatSymbols();
				dfs.setDecimalSeparator('.');
				DecimalFormat df = new DecimalFormat("0.###", dfs);
				
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
				message.setPressure(message.getPressure() / 700);
				message.setPressure(Double.parseDouble(df.format(message.getPressure())));
				message.setFlow(Long.parseLong(biFlow.toString(10)));
				message.setTemperature(Long.parseLong(biTemperature.toString(10)));
				message.setBattery(Double.parseDouble(biBattery.toString(10)));
				message.setBattery(message.getBattery() / 50);
				message.setBattery(Double.parseDouble(df.format(message.getBattery())));
				message.setAlarm(Long.parseLong(biAlarme.toString(10)));
				message.setDtInsert(Auxiliar.formataDtBancoHr(arr[2]));
				
				//INSERIR
				messageDAO.inserir(message);
				
				//TRATAMENTO CONSUMO
				Consumo consumo = new Consumo();
				consumo.setIdConsumo(0l);
				consumo.setIdUser(4l);
				consumo.setIdMedidor(medidorDAO.buscarIdMedidor(message.getDevice(), message.getMeterPosition().intValue()));
				consumo.setDevice(message.getDevice());
				consumo.setData(message.getData());
				consumo.setVersion(message.getVersion());
				consumo.setMeterPosition(message.getMeterPosition());
				consumo.setVolume(message.getVolume());
				consumo.setPressure(message.getPressure());
				consumo.setFlow(message.getFlow());
				consumo.setTemperature(message.getTemperature());
				consumo.setBattery(message.getBattery());
				consumo.setAlarm(message.getAlarm());			
				consumo.setDtInsert(Auxiliar.formataDtBancoHr(arr[2]));
				
				consumoDAO.inserir(consumo);
				
				/*
				//VERIFICA ALARM PRESSAO
			    BridgeDAO bridgeDAO = new BridgeDAO(connection);
			    Bridge bridge = bridgeDAO.buscarPorDeviceNum(message.getDevice());
			    if(bridge != null) {		    	
			    	
			    	if(bridge.getBridgeTp().getIdBridgeTp().longValue() == 2) {//PRESSURE BRIDGE
			    		
			    		MetaPressaoDAO metaPressaoDAO = new MetaPressaoDAO(connection);
			    		MetaPressao metaPressao = metaPressaoDAO.buscarPorIdBridge(bridge.getIdBridge());
			    		if(metaPressao != null) {
			    			
			    			if(consumo.getPressure().doubleValue() > metaPressao.getPressaoMax().doubleValue()
			    					|| consumo.getPressure().doubleValue() < metaPressao.getPressaoMin().doubleValue()) {
			    				
			    				AlarmPressao alarmPressao = new AlarmPressao();
			    				alarmPressao.setIdAlarmPressao(0l);
			    				alarmPressao.setIdEmpresa(metaPressao.getIdEmpresa());
			    				alarmPressao.setIdCondominio(bridge.getIdCondominio());
			    				alarmPressao.setIdBridge(bridge.getIdBridge());
			    				alarmPressao.setIdMedidor(null);
			    				alarmPressao.setMeterPosition(0l);
			    				alarmPressao.setPressaoMin(metaPressao.getPressaoMin());
			    				alarmPressao.setPressaoMax(metaPressao.getPressaoMax());
			    				alarmPressao.setPressaoReal(consumo.getPressure());
			    				alarmPressao.setDtInsert(null);
			    				
			    				AlarmPressaoDAO alarmPressaoDAO = new AlarmPressaoDAO(connection);
			    				alarmPressaoDAO.inserir(alarmPressao);
			    			}
			    		}
			    	}
			    }
			    */
				System.out.println(linha);
			}
			br.close();
        		    
			/*
	    		
		    */
        }
        catch (Exception e) {
        	System.out.println("erro " + e.toString());
        	if(e.toString().length() > 1000) {
        		try {new LogSqlDAO(connection).inserir(4l, e.toString().substring(0, 999), sb.toString(), "", "");} catch (SQLException e1) {e1.printStackTrace();}
        	}
        	else {
        		try {new LogSqlDAO(connection).inserir(4l, e.toString(), sb.toString(), "", "");} catch (SQLException e1) {e1.printStackTrace();}
        	}        	
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



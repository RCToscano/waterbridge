
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

import br.com.andwaterbridge.dao.AlarmPressaoDAO;
import br.com.andwaterbridge.dao.MetaPressaoDAO;
import br.com.andwaterbridge.modelo.AlarmPressao;
import br.com.andwaterbridge.modelo.MetaPressao;
import br.com.waterbridge.auxiliar.Constantes;
import br.com.waterbridge.auxiliar.Email;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.AlarmDAO;
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.ConsumoDAO;
import br.com.waterbridge.dao.EmailAlarmDAO;
import br.com.waterbridge.dao.LogSqlDAO;
import br.com.waterbridge.dao.MedidorDAO;
import br.com.waterbridge.dao.MessageDAO;
import br.com.waterbridge.modelo.Alarm;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.Consumo;
import br.com.waterbridge.modelo.EmailAlarm;
import br.com.waterbridge.modelo.Message;

public class MessageBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Connection connection = null;
		StringBuilder sb = null;
		
		try {
        
			sb = new StringBuilder();
	    	
	    	String linha = null;
	    	while((linha = req.getReader().readLine()) != null) {	    
	    		sb.append(linha);
	    	}
	    		    	
	    	Message message = new Gson().fromJson(sb.toString(), Message.class);
	    	//message.setData("10017335000001700017A508");//SOBREPONDO O DATA ATE QUE O FELIPE ALTERE O FRAME ENVIADO

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
			
			connection = ConnectionFactory.getConnection();
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
			message.setDtInsert(messageDAO.dataHoraMinSeg());
			
			//INSERIR
			messageDAO.inserir(message);
			
			Long idEmpresa = null;
			Long idCondominio = null;
			Long idBridge = null;	
			Long idMedidor = null;
			
			BridgeDAO bridgeDAO = new BridgeDAO(connection);
		    Bridge bridge = bridgeDAO.buscarPorDeviceNum(message.getDevice(), "A");
		    if(bridge != null) {
		    	
		    	idMedidor = medidorDAO.buscarIdMedidor(message.getDevice(), message.getMeterPosition().intValue());
		    	
		    	idBridge = bridge.getIdBridge();
		    	idCondominio = bridge.getIdCondominio();
		    	
		    	CondominioDAO condominioDAO = new CondominioDAO(connection);
		    	Condominio condominio = condominioDAO.buscarPorId(bridge.getIdCondominio());		    	
		    	idEmpresa = condominio.getIdEmpresa();
		    }
		   
			//TRATAMENTO CONSUMO
			Consumo consumo = new Consumo();
			consumo.setIdConsumo(0l);
			consumo.setIdEmpresa(idEmpresa);
			consumo.setIdCondominio(idCondominio);
			consumo.setIdBridge(idBridge);
			consumo.setIdMedidor(idMedidor);
			consumo.setIdUser(4l);
			consumo.setDevice(message.getDevice());
			consumo.setData(message.getData());
			consumo.setVersion(message.getVersion());
			consumo.setMeterPosition(message.getMeterPosition());
			consumo.setVolume(message.getVolume());
			consumo.setPressure(message.getPressure());
			if(bridge != null && bridge.getAjuste() != null
					&& (bridge.getBridgeTp().getIdBridgeTp().longValue() == 2 
			    			|| bridge.getBridgeTp().getIdBridgeTp().longValue() == 4)) {
				consumo.setPressure(consumo.getPressure() + bridge.getAjuste());
				//TRATAMENTO DE PRESSAO QUANDO FOR MENOR QUE 0 APOSTO AJUSTE //ATUALIZADO 06/03/2019 12:28
				if(consumo.getPressure() != null && consumo.getPressure().doubleValue() < 0){
					consumo.setPressure(0d);						
				}
			}
			consumo.setFlow(message.getFlow());
			consumo.setTemperature(message.getTemperature());
			consumo.setBattery(message.getBattery());
			consumo.setAlarm(message.getAlarm());			
			consumo.setDtInsert(consumoDAO.dataHoraMinSeg());
			
			consumoDAO.inserir(consumo);
			
			//VERIFICA ALARM PRESSAO		    
		    if(bridge != null) {		    	
		    	
		    	if(bridge.getBridgeTp().getIdBridgeTp().longValue() == 2 
		    			|| bridge.getBridgeTp().getIdBridgeTp().longValue() == 4) {//PRESSURE BRIDGE
		    		
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
		    				alarmPressao.setPressaoMinBaixa(metaPressao.getPressaoMinBaixa());
		    				alarmPressao.setPressaoMaxAlta(metaPressao.getPressaoMaxAlta());
		    				alarmPressao.setDtInsert(null);
		    				
		    				AlarmPressaoDAO alarmPressaoDAO = new AlarmPressaoDAO(connection);
		    				alarmPressaoDAO.inserir(alarmPressao);
		    			}
		    		}
		    	}
		    }
			
			verificarAlarm(consumo, connection);
			
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
	
	public void verificarAlarm(Consumo consumo, Connection connection) {
		try {
			if(consumo.getAlarm() > 0) {
				
				EmailAlarmDAO emailAlarmDAO = new EmailAlarmDAO(connection);
				EmailAlarm emailAlarm = emailAlarmDAO.buscarPorAlarmDevice(consumo.getAlarm(), consumo.getDevice());
				if(emailAlarm == null) {
					emailAlarm = new EmailAlarm();
					emailAlarm.setDevice(consumo.getDevice());
					emailAlarm.setIdAlarm(consumo.getAlarm());
					
					enviarEmailAlarm(emailAlarm, connection);

					emailAlarmDAO.inserir(emailAlarm);
				}
			}
		} 
		catch (Exception e) {
			System.out.println(e);
			try {
				new LogSqlDAO(connection).inserir(1l, "", e.getMessage(), "MessageBO", "");
			} catch (SQLException e1) {
				System.out.println(e1);
			}
		}
	}
	
	public void enviarEmailAlarm(EmailAlarm emailAlarm, Connection connection) throws Exception {
		AlarmDAO alarmDAO = new AlarmDAO(connection);
		Alarm alarm = alarmDAO.buscarPorId(emailAlarm.getIdAlarm());
		if(alarm != null) {
			String mensagem = Email.corpoEmailAlarme(emailAlarm.getDevice(), emailAlarm.getIdAlarm(),
					alarm.getAlarm() + " - " + alarm.getDescricao());
    		Email.enviarEmail("WaterBridge - Alarme Device "+emailAlarm.getDevice(), mensagem, Constantes.EMAIL_DESOLTEC);
		}
		else {
			new LogSqlDAO(connection).inserir(1l, "", "Alarm nao encontrado na tabela", "MessageBO", "");
		}
	}
	
}



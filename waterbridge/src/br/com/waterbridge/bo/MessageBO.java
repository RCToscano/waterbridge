
package br.com.waterbridge.bo;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.MessageDAO;
import br.com.waterbridge.modelo.Message;

public class MessageBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection connection;
	
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
			
	    	StringBuilder sb = new StringBuilder();
	    	String linha = null;
	    	while((linha = req.getReader().readLine()) != null) {	    
	    		sb.append(linha);
	    	}
	    	
	    	Message message = new Gson().fromJson(sb.toString(), Message.class);

	        String dataVersion = message.getData().substring(0, 2);
	        String dataMeterId = message.getData().substring(2, 10);
	        //ROTACIONAR METER ID
	        dataMeterId = dataMeterId.substring(6, 8) +
	                      dataMeterId.substring(4, 6) +
	                      dataMeterId.substring(2, 4) +
	                      dataMeterId.substring(0, 2) ;
	        String dataVolume = message.getData().substring(10, 18);
	        //ROTACIONAR VOLUME
	        dataVolume = dataVolume.substring(6, 8) +
	                     dataVolume.substring(4, 6) +
	                     dataVolume.substring(2, 4) +
	                     dataVolume.substring(0, 2) ;
	        String dataTemperature = message.getData().substring(18, 20);
	        String dataBattery = message.getData().substring(20, 22);
	        String dataAlarme = message.getData().substring(22, 24);
	    	
	    	message.setIdMessage(0l);
	    	message.setIdUser(4l);
	    	//message.setData(data);
	    	//message.setConsumo(consumo);
	    	//message.setVazao(vazao);
	    	message.setBiVersion(new BigInteger(dataVersion));
	    	message.setBiMeterId(new BigInteger(dataMeterId));
	    	message.setBiVolume(new BigInteger(dataVolume));//RECEBE VOLUME HEXA ROTACIONADO
	    	message.setBiVolume(new BigInteger(message.getBiVolume().toString(10)));//CONVERTE VOLUME DECIMAL
	    	message.setBiVolume(message.getBiVolume().divide(new BigInteger("1000")));//COMVERTE VOLUME EM LITROS	       
	    	message.setBiTemperature(new BigInteger(dataTemperature));
	    	message.setBiBattery(new BigInteger(dataBattery));
	    	message.setBiAlarme(new BigInteger(dataAlarme));
	    	message.setDtInsert(null);

	    	MessageDAO messageDAO = new MessageDAO(connection);
	    	messageDAO.inserir(message);
			
            String json = "ok";
            
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(json); 
        }
        catch (Exception e) {
        	
        	Message message = new Message();
	    	message.setTexto(e.toString());
	    	
	    	MessageDAO messageDAO = new MessageDAO(connection);
	    	try {messageDAO.inserir(message);} catch (SQLException e1) {}
        	
        	res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write("erro");
        }
		finally {
			if(connection != null) {
				try {connection.close();} catch (SQLException ex) {}
			}
		}
    }
}



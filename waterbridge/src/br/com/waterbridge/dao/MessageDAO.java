package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.modelo.Message;

public class MessageDAO {
    
    Connection connection = null;

    public MessageDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(Message message) throws SQLException {
        
        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_MESSAGE ( " +
    		//"       ID_MESSAGE, " +
    		"       ID_USER, " +
    		"       DATA, " +
    		"       VERSION, " +
    		"       METERID, " +
    		"       VOLUME, " +
    		"       TEMPERATURE, " +
    		"       BATTERY, " +
    		"       ALARM, " +
    		"       CONSUMO, " +
    		"       VAZAO, " +
    		"       DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,?,?,?,?,?,?,?, " +
            "       ? " +
            ") ");
            
            //stmt.setObject(1, message.getIdMessage());
            stmt.setObject(1, message.getIdUser());
            stmt.setObject(2, message.getData());
            stmt.setObject(3, message.getVersion());
            stmt.setObject(4, message.getMeterId());
            stmt.setObject(5, message.getVolume());
            stmt.setObject(6, message.getTemperature());
            stmt.setObject(7, message.getBattery());
            stmt.setObject(8, message.getAlarm());
            stmt.setObject(9, message.getConsumo());
            stmt.setObject(10, message.getVazao());
            stmt.setObject(11, message.getDtInsert());

            stmt.executeUpdate();
        }
        catch(SQLException e) {
            
            throw e;
        }
        finally {

            if(stmt != null) {
                
                stmt.close();
            }
        }
    }
    
    public Message buscarUltimoPorMeterId(Long meterId) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Message message = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT TB_MESSAGE.ID_MESSAGE, " +
        	"       TB_MESSAGE.ID_USER, " +
        	"	    TB_MESSAGE.DATA, " +
        	"       TB_MESSAGE.VERSION, " +
        	"       TB_MESSAGE.METERID, " +
        	"       TB_MESSAGE.VOLUME, " +
        	"       TB_MESSAGE.TEMPERATURE, " +
        	"       TB_MESSAGE.BATTERY, " +
        	"       TB_MESSAGE.ALARM, " +
        	"       TB_MESSAGE.CONSUMO, " +
        	"       TB_MESSAGE.VAZAO, " +
        	"       TB_MESSAGE.DTINSERT " +
        	"FROM   TB_MESSAGE " +
        	"WHERE  TB_MESSAGE.ID_MESSAGE = ( " +
        	"  SELECT MAX(ID_MESSAGE) FROM TB_MESSAGE WHERE METERID = ? " +
        	") "
    		);
            
            stmt.setObject(1, meterId);
            
            rs = stmt.executeQuery();

            if(rs.next()) {
                
            	message = new Message();
            	message.setIdMessage(rs.getLong("ID_MESSAGE"));
            	message.setIdUser(rs.getLong("ID_USER"));
            	message.setData(rs.getString("DATA"));
            	message.setVersion(rs.getString("VERSION"));
            	message.setMeterId(rs.getLong("METERID"));
            	message.setVolume(rs.getLong("VOLUME"));
            	message.setTemperature(rs.getLong("TEMPERATURE"));
            	message.setBattery(rs.getDouble("BATTERY"));
            	message.setAlarm(rs.getLong("ALARM"));
            	message.setConsumo(rs.getLong("CONSUMO"));
            	message.setVazao(rs.getLong("VAZAO"));
            	message.setDtInsert(rs.getString("DTINSERT"));
            }

            return message;
        }
        catch(SQLException e) {            
            
            throw e;
        }
        finally {

            if(stmt != null) {
                
                stmt.close();
            }
            if(rs != null) {              
                
                rs.close();
            }
        }
    }
    
    public void alterar(Message message) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"UPDATE TB_MESSAGE SET " +
        	"       ID_USER = ?, " +
        	"	    DATA = ?, " +
        	"       VERSION = ?, " +
        	"       METERID = ?, " +
        	"       VOLUME = ?, " +
        	"       TEMPERATURE = ?, " +
        	"       BATTERY = ?, " +
        	"       ALARM = ?, " +
        	"       CONSUMO = ?, " +
        	"       VAZAO = ?, " +
        	"       DTINSERT = ? " +
        	"WHERE  ID_MESSAGE = ? " 
    		);
            
            stmt.setObject(1, message.getIdUser());
            stmt.setObject(2, message.getData());
            stmt.setObject(3, message.getVersion());
            stmt.setObject(4, message.getMeterId());
            stmt.setObject(5, message.getVolume());
            stmt.setObject(6, message.getTemperature());
            stmt.setObject(7, message.getBattery());
            stmt.setObject(8, message.getAlarm());
            stmt.setObject(9, message.getConsumo());
            stmt.setObject(10, message.getVazao());
            stmt.setObject(11, message.getDtInsert());
            stmt.setObject(12, message.getIdMessage());
            
            stmt.executeUpdate();
        }
        catch(SQLException e) {            
            
            throw e;
        }
        finally {

            if(stmt != null) {
                
                stmt.close();
            }
        }
    }    
    
    public Long difDataEmMinutos(String dtInicio, String dtFim) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Long qtdeMinutos = 0l;
        
        try {
            
            stmt = connection.prepareStatement(
            "SELECT TIMESTAMPDIFF (MINUTE, '" + dtInicio + "', '" + dtFim + "') AS QTDEMINUTOS "
    		);
            
            rs = stmt.executeQuery();

            if(rs.next()) {
                
        		qtdeMinutos = rs.getLong("QTDEMINUTOS");
            }

            return qtdeMinutos;
        }
        catch(SQLException e) {            
            
            throw e;
        }
        finally {

            if(stmt != null) {
                
                stmt.close();
            }
            if(rs != null) {              
                
                rs.close();
            }
        }
    }
    
    public String dataHoraMinSeg() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String dataHoraMinSeg = "";
        
        try {
            
            stmt = connection.prepareStatement(
            "SELECT sysdate() AS DATAHORAMINSEG "
    		);
            
            rs = stmt.executeQuery();

            if(rs.next()) {
                
            	dataHoraMinSeg = rs.getString("DATAHORAMINSEG");
            }

            return dataHoraMinSeg;
        }
        catch(SQLException e) {            
            
            throw e;
        }
        finally {

            if(stmt != null) {
                
                stmt.close();
            }
            if(rs != null) {              
                
                rs.close();
            }
        }
    }
    
//    public static void main(String[] args) throws SQLException {
//		
//    	Connection connection = ConnectionFactory.getConnection();
//    	
//    	Message message = new Message();
//    	message.setTexto("teste");
//    	
//    	MessageDAO messageDAO = new MessageDAO(connection);
//    	messageDAO.inserir(message);
//    	
//    	connection.close();
//	}
}



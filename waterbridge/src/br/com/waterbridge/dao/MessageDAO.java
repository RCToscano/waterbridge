package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    		"       DEVICE, " +
    		"       DATA, " +
    		"       VERSION, " +
    		"       METERPOSITION, " +
    		"       VOLUME, " +
    		"       PRESSURE, " +
    		"       FLOW, " +
    		"       TEMPERATURE, " +
    		"       BATTERY, " +
    		"       ALARM, " +
    		"       DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,?,?,?,?,?,?,?, " +
            "       ?,? " +
            ") ");
            
            //stmt.setObject(1, message.getIdMessage());
            stmt.setObject(1, message.getIdUser());
            stmt.setObject(2, message.getDevice());
            stmt.setObject(3, message.getData());
            stmt.setObject(4, message.getVersion());
            stmt.setObject(5, message.getMeterPosition());
            stmt.setObject(6, message.getVolume());
            stmt.setObject(7, message.getPressure());
    		stmt.setObject(8, message.getFlow());
            stmt.setObject(9, message.getTemperature());
            stmt.setObject(10, message.getBattery());
            stmt.setObject(11, message.getAlarm());
            stmt.setObject(12, message.getDtInsert());

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
    
    public Message buscarUltimo(String device, Long meterPosition) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Message message = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_MESSAGE, " +
    		"       ID_USER, " +
    		"       DEVICE, " +
    		"       DATA, " +
    		"       VERSION, " +
    		"       METERPOSITION, " +
    		"       VOLUME, " +
    		"       PRESSURE, " +
    		"       FLOW, " +
    		"       TEMPERATURE, " +
    		"       BATTERY, " +
    		"       ALARM, " +
    		"       DTINSERT " +
        	"FROM   TB_MESSAGE " +
        	"WHERE  ID_MESSAGE = ( " +
        	"  SELECT MAX(ID_MESSAGE) FROM TB_MESSAGE WHERE DEVICE = ? AND METERPOSITION = ? " +
        	") "
    		);
            
            stmt.setObject(1, device);
            stmt.setObject(2, meterPosition);
            
            rs = stmt.executeQuery();

            if(rs.next()) {
                
            	message = new Message();
            	message.setIdMessage(rs.getLong("ID_MESSAGE"));
            	message.setIdUser(rs.getLong("ID_USER"));
            	message.setDevice(rs.getString("DEVICE"));
            	message.setData(rs.getString("DATA"));
            	message.setVersion(rs.getString("VERSION"));
            	message.setMeterPosition(rs.getLong("METERPOSITION"));
            	message.setVolume(rs.getDouble("VOLUME"));
            	message.setPressure(rs.getDouble("PRESSURE"));
            	message.setFlow(rs.getDouble("FLOW"));
            	message.setTemperature(rs.getDouble("TEMPERATURE"));
            	message.setBattery(rs.getDouble("BATTERY"));
            	message.setAlarm(rs.getLong("ALARM"));
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
    
    public Message buscarPorDeviceData(String device, String data) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Message message = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_MESSAGE, " +
    		"       ID_USER, " +
    		"       DEVICE, " +
    		"       DATA, " +
    		"       VERSION, " +
    		"       METERPOSITION, " +
    		"       VOLUME, " +
    		"       PRESSURE, " +
    		"       FLOW, " +
    		"       TEMPERATURE, " +
    		"       BATTERY, " +
    		"       ALARM, " +
    		"       DTINSERT " +
        	"FROM   TB_MESSAGE " +
        	"WHERE  DEVICE = ? " +
        	"AND    DATA = ?  " 
    		);
            
            stmt.setObject(1, device);
            stmt.setObject(2, data);
            
            rs = stmt.executeQuery();

            if(rs.next()) {
                
            	message = new Message();
            	message.setIdMessage(rs.getLong("ID_MESSAGE"));
            	message.setIdUser(rs.getLong("ID_USER"));
            	message.setDevice(rs.getString("DEVICE"));
            	message.setData(rs.getString("DATA"));
            	message.setVersion(rs.getString("VERSION"));
            	message.setMeterPosition(rs.getLong("METERPOSITION"));
            	message.setVolume(rs.getDouble("VOLUME"));
            	message.setPressure(rs.getDouble("PRESSURE"));
            	message.setFlow(rs.getDouble("FLOW"));
            	message.setTemperature(rs.getDouble("TEMPERATURE"));
            	message.setBattery(rs.getDouble("BATTERY"));
            	message.setAlarm(rs.getLong("ALARM"));
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
			"       DEVICE = ?, " +
			"       DATA = ?, " +
			"       VERSION = ?, " +
			"       METERPOSITION = ?, " +
			"       VOLUME = ?, " +
			"       PRESSURE = ?, " +
			"       FLOW = ?, " +
			"       TEMPERATURE = ?, " +
			"       BATTERY = ?, " +
			"       ALARM = ?, " +
			"       DTINSERT = ? " +
        	"WHERE  ID_MESSAGE = ? " 
    		);
            
            stmt.setObject(1, message.getIdUser());
            stmt.setObject(2, message.getDevice());
            stmt.setObject(3, message.getData());
            stmt.setObject(4, message.getVersion());
            stmt.setObject(5, message.getMeterPosition());
            stmt.setObject(6, message.getVolume());
            stmt.setObject(7, message.getPressure());
            stmt.setObject(8, message.getFlow());
            stmt.setObject(9, message.getTemperature());
            stmt.setObject(10, message.getBattery());
            stmt.setObject(11, message.getAlarm());
            stmt.setObject(12, message.getDtInsert());
            stmt.setObject(13, message.getIdMessage());
            
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
}



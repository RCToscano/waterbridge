
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.waterbridge.modelo.Consumo;

public class ConsumoDAO {
    
    Connection connection = null;

    public ConsumoDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(Consumo consumo) throws SQLException {
        
        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_CONSUMO ( " +
            //"		ID_CONSUMO, " +
            "		ID_USER, " +
            "		ID_MEDIDOR, " +
            "		DEVICE, " +
            "		DATA, " +
            "		VERSION, " +
            "		METERPOSITION, " +
            "		VOLUME, " +
            "		PRESSURE, " +
            "		FLOW, " +
            "		TEMPERATURE, " +
            "		BATTERY, " +
            "		ALARM, " +
            "		CONSUMO, " +
            "		VAZAO, " +
            "		DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,?,?,?,?,?,?,?, " +
            "       ?,?,?,?,? " +
            ") ");
            
            //stmt.setObject(1, consumo.getIdConsumo());
            stmt.setObject(1, consumo.getIdUser());
            stmt.setObject(2, consumo.getIdMedidor());
            stmt.setObject(3, consumo.getDevice());
            stmt.setObject(4, consumo.getData());
            stmt.setObject(5, consumo.getVersion());
            stmt.setObject(6, consumo.getMeterPosition());
            stmt.setObject(7, consumo.getVolume());
            stmt.setObject(8, consumo.getPressure());
            stmt.setObject(9, consumo.getFlow());
            stmt.setObject(10, consumo.getTemperature());
            stmt.setObject(11, consumo.getBattery());
            stmt.setObject(12, consumo.getAlarm());
            stmt.setObject(13, consumo.getConsumo());
            stmt.setObject(14, consumo.getVazao());
            stmt.setObject(15, consumo.getDtInsert());
            
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
    
    public Consumo buscarUltimo(String device, Long meterPosition) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consumo consumo = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_CONSUMO, " +
    		"		ID_USER, " +
    		"		ID_MEDIDOR, " +
    		"		DEVICE, " +
    		"		DATA, " +
    		"		VERSION, " +
    		"		METERPOSITION, " +
    		"		VOLUME, " +
    		"		PRESSURE, " +
    		"		FLOW, " +
    		"		TEMPERATURE, " +
    		"		BATTERY, " +
    		"		ALARM, " +
    		"		CONSUMO, " +
    		"		VAZAO, " +
    		"		DTINSERT " +
        	"FROM   TB_CONSUMO " +
        	"WHERE  ID_CONSUMO = ( " +
        	"  SELECT MAX(ID_CONSUMO) FROM TB_CONSUMO WHERE DEVICE = ? AND METERPOSITION = ? " +
        	") "
    		);
            
            stmt.setObject(1, device);
            stmt.setObject(2, meterPosition);
            
            rs = stmt.executeQuery();

            if(rs.next()) {
                
            	consumo = new Consumo();
            	consumo.setIdConsumo(rs.getLong("ID_CONSUMO"));
            	consumo.setIdUser(rs.getLong("ID_USER"));
            	consumo.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	consumo.setDevice(rs.getString("DEVICE"));
            	consumo.setData(rs.getString("DATA"));
            	consumo.setVersion(rs.getString("VERSION"));
            	consumo.setMeterPosition(rs.getLong("METERPOSITION"));
            	consumo.setVolume(rs.getDouble("VOLUME"));
            	consumo.setPressure(rs.getDouble("PRESSURE"));
            	consumo.setFlow(rs.getLong("FLOW"));
            	consumo.setTemperature(rs.getLong("TEMPERATURE"));
            	consumo.setBattery(rs.getDouble("BATTERY"));
            	consumo.setAlarm(rs.getLong("ALARM"));
            	consumo.setConsumo(rs.getDouble("CONSUMO"));
            	consumo.setVazao(rs.getDouble("VAZAO"));
            	consumo.setDtInsert(rs.getString("DTINSERT"));
            }

            return consumo;
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
    
    public void alterar(Consumo consumo) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"UPDATE TB_CONSUMO SET " +
    		"		ID_USER = ?, " +
    		"		ID_MEDIDOR = ?, " +
    		"		DEVICE = ?, " +
    		"		DATA = ?, " +
    		"		VERSION = ?, " +
    		"		METERPOSITION = ?, " +
    		"		VOLUME = ?, " +
    		"		PRESSURE = ?, " +
    		"		FLOW = ?, " +
    		"		TEMPERATURE = ?, " +
    		"		BATTERY = ?, " +
    		"		ALARM = ?, " +
    		"		CONSUMO = ?, " +
    		"		VAZAO = ?, " +
    		"		DTINSERT = ? " +
        	"WHERE  ID_CONSUMO = ? " 
    		);
          
            stmt.setObject(1, consumo.getIdUser());
            stmt.setObject(2, consumo.getIdMedidor());
            stmt.setObject(3, consumo.getDevice());
            stmt.setObject(4, consumo.getData());
            stmt.setObject(5, consumo.getVersion());
            stmt.setObject(6, consumo.getMeterPosition());
            stmt.setObject(7, consumo.getVolume());
            stmt.setObject(8, consumo.getPressure());
            stmt.setObject(9, consumo.getFlow());
            stmt.setObject(10, consumo.getTemperature());
            stmt.setObject(11, consumo.getBattery());
            stmt.setObject(12, consumo.getAlarm());
            stmt.setObject(13, consumo.getConsumo());
            stmt.setObject(14, consumo.getVazao());
            stmt.setObject(15, consumo.getDtInsert());
            stmt.setObject(16, consumo.getIdConsumo());
            
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



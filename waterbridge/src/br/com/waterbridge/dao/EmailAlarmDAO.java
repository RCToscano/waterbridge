package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.waterbridge.modelo.EmailAlarm;

public class EmailAlarmDAO {
	
	Connection connection = null;
	
	public EmailAlarmDAO(Connection connection) {
        this.connection = connection;
    }
	
	public void inserir(EmailAlarm emailAlarm) throws SQLException {
		PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	stmt = connection.prepareStatement(
        			"INSERT INTO TB_EMAILALARM ( " +
        			"ID_ALARM, " +
        			"DEVICE, " +
        			"DTINSERT " +
        			")" +
					" VALUES ( " +
            		"?, ?, sysdate() ) " 
    				);
            		
            stmt.setObject(1, emailAlarm.getIdAlarm());
            stmt.setObject(2, emailAlarm.getDevice());
            stmt.executeUpdate();
		} 
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
	}

	public EmailAlarm buscarPorAlarmDevice(Long idAlarm, String device) throws SQLException {
    	
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT * " +
					"  FROM TB_EMAILALARM " +	
					" WHERE ID_ALARM = ? " +
					"   AND DEVICE = ? " +
					"   AND DTINSERT = ? "
    				);
    		
    		stmt.setLong(1, idAlarm);
    		stmt.setString(2, device);
    		stmt.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    		
    		rs = stmt.executeQuery();
    		
    		if(rs.next()) {
    			EmailAlarm emailAlarm = new EmailAlarm();
    			emailAlarm.setIdEmailAlarm(rs.getLong("ID_EMAILALARM"));
    			emailAlarm.setIdAlarm(rs.getLong("ID_ALARM"));
    			emailAlarm.setDevice(rs.getString("DEVICE"));
    			emailAlarm.setDtInsert(rs.getString("DTINSERT"));
    			return emailAlarm;
    		}
    		
    		return null;
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

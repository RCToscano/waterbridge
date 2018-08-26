package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.modelo.Alarm;

public class AlarmDAO {
	
	Connection connection = null;
	
	public AlarmDAO(Connection connection) {
        this.connection = connection;
    }
	
	public List<Alarm> buscarTodos() throws SQLException {
    	
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	List<Alarm> lista = new ArrayList<>();
    	
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT * FROM TB_ALARM " 
    				);
    		
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
    			Alarm alarm = new Alarm();
    			alarm.setIdAlarm(rs.getLong("ID_ALARM"));
    			alarm.setAlarm(rs.getString("ALARM"));
    			alarm.setDescricao(rs.getString("DESCRIPTION"));
    			lista.add(alarm);
    		}
    		
    		return lista;
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
	
	public Alarm buscarPorId(Long idAlarm) throws SQLException {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.prepareStatement(
					"SELECT * FROM TB_ALARM " + 
					" WHERE ID_ALARM = ? "
					);
			
			stmt.setObject(1, idAlarm);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				Alarm alarm = new Alarm();
				alarm.setIdAlarm(rs.getLong("ID_ALARM"));
				alarm.setAlarm(rs.getString("ALARM"));
				alarm.setDescricao(rs.getString("DESCRIPTION"));
				return alarm;
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


package br.com.andwaterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.andwaterbridge.modelo.AlarmPressao;
import br.com.andwaterbridge.modelo.AlarmPressaoApp;
import br.com.waterbridge.connection.ConnectionFactory;

public class AlarmPressaoAppDAO {
    
    private Connection connection;

    public AlarmPressaoAppDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(AlarmPressaoApp alarmPressaoApp) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_ALARMPRESSAOAPP ( " +
            //"		ID_ALARMPRESSAOAPP, " +
            "		ID_ALARMPRESSAO, " +
            "		ID_USER, " +
            "		DTINSERT " +
            ") VALUES ( " +
            "       ?,?,SYSDATE() " +
            ")");
            
            //stmt.setObject(1, alarmPressaoApp.getIdAlarmPressaoApp());
            stmt.setObject(1, alarmPressaoApp.getIdAlarmPressao());
            stmt.setObject(2, alarmPressaoApp.getIdUser());
            //stmt.setObject(3, alarmPressaoApp.getDtInsert());
            
            stmt.execute();
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
    
    
    
    public static void main(String[] args) throws SQLException {
		
    	Connection connection = ConnectionFactory.getConnection();
    	
    	AlarmPressaoApp alarmPressaoApp = new AlarmPressaoApp();
    	alarmPressaoApp.setIdAlarmPressaoApp(0l);;
    	alarmPressaoApp.setIdAlarmPressao(1l);;
    	alarmPressaoApp.setIdUser(1L);;
    	alarmPressaoApp.setDtInsert(null);;
    	
    	AlarmPressaoAppDAO alarmPressaoAppDAO = new AlarmPressaoAppDAO(connection);
    	alarmPressaoAppDAO.inserir(alarmPressaoApp);
    	
    	//System.out.println("metapressao " + alarmPressao);
    	
    	connection.close();
    	
    	System.out.println("fim");
	}
}

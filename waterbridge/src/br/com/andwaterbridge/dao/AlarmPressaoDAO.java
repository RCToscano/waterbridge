
package br.com.andwaterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.andwaterbridge.modelo.AlarmPressao;
import br.com.waterbridge.connection.ConnectionFactory;

public class AlarmPressaoDAO {
    
    private Connection connection;

    public AlarmPressaoDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(AlarmPressao alarmPressao) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_ALARMPRESSAO ( " +
            //"		ID_ALARMPRESSAO, " +
            "       ID_EMPRESA, " +
            "       ID_CONDOMINIO, " +	
            "		ID_BRIDGE, " +
            "		ID_MEDIDOR, " +
            "		METERPOSITION, " +
            "		PRESSAOMIN, " +
            "		PRESSAOMAX, " +
            "		PRESSAOREAL, " +
            "		DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,?,?,?,?,?,SYSDATE() " +
            ")");
            
            //stmt.setObject(1, alarmPressao.getIdAlarmPressao());
            stmt.setObject(1, alarmPressao.getIdEmpresa());
            stmt.setObject(2, alarmPressao.getIdCondominio());            
            stmt.setObject(3, alarmPressao.getIdBridge());
            stmt.setObject(4, alarmPressao.getIdMedidor());
            stmt.setObject(5, alarmPressao.getMeterPosition());
            stmt.setObject(6, alarmPressao.getPressaoMin());
            stmt.setObject(7, alarmPressao.getPressaoMax());
            stmt.setObject(8, alarmPressao.getPressaoReal());
            //stmt.setObject(9, alarmPressao.getDtInsert());
            
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
    
    public List<AlarmPressao> listarPerfilProgramador(String idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AlarmPressao> listAlarmPressao = new ArrayList<AlarmPressao>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    TB_ALARMPRESSAO.ID_ALARMPRESSAO, " +
	        "          TB_ALARMPRESSAO.ID_EMPRESA, " +
	        "          TB_ALARMPRESSAO.ID_CONDOMINIO, " +
	        "   	   TB_ALARMPRESSAO.ID_BRIDGE, " +
	        "          TB_ALARMPRESSAO.ID_MEDIDOR, " +
	        "          TB_ALARMPRESSAO.METERPOSITION, " +
	        "          TB_ALARMPRESSAO.PRESSAOMIN, " +
	        "          TB_ALARMPRESSAO.PRESSAOMAX, " +
	        "          TB_ALARMPRESSAO.PRESSAOREAL, " +
	        "          TB_ALARMPRESSAO.DTINSERT, " +
	        "          TB_ALARMPRESSAOAPP.* " +
	        "FROM      TB_ALARMPRESSAO " +
	        "LEFT JOIN TB_ALARMPRESSAOAPP " +
	        "ON        TB_ALARMPRESSAO.ID_ALARMPRESSAO = TB_ALARMPRESSAOAPP.ID_ALARMPRESSAO " +
	        "AND       TB_ALARMPRESSAOAPP.ID_USER = ? " +
	        "WHERE     TB_ALARMPRESSAOAPP.ID_ALARMPRESSAOAPP IS NULL " +  	
	        "AND       DATE_FORMAT(TB_ALARMPRESSAO.DTINSERT, '%Y%m%d') = DATE_FORMAT(sysdate(), '%Y%m%d') "
            );
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	AlarmPressao alarmPressao = new AlarmPressao();
            	alarmPressao.setIdAlarmPressao(rs.getLong("ID_ALARMPRESSAO"));
            	alarmPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	alarmPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	alarmPressao.setIdBridge(rs.getLong("ID_BRIDGE"));
            	alarmPressao.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	alarmPressao.setMeterPosition(rs.getLong("METERPOSITION"));
            	alarmPressao.setPressaoMin(rs.getDouble("PRESSAOMIN"));
            	alarmPressao.setPressaoMax(rs.getDouble("PRESSAOMAX"));
            	alarmPressao.setPressaoReal(rs.getDouble("PRESSAOREAL"));
            	alarmPressao.setDtInsert(rs.getString("DTINSERT"));
            	
            	listAlarmPressao.add(alarmPressao);
            }
            
            return listAlarmPressao;
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
    
    public List<AlarmPressao> listarPerfilGerencial(String idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AlarmPressao> listAlarmPressao = new ArrayList<AlarmPressao>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    TB_ALARMPRESSAO.ID_ALARMPRESSAO, " +
	        "          TB_ALARMPRESSAO.ID_EMPRESA, " +
	        "          TB_ALARMPRESSAO.ID_CONDOMINIO, " +
	        "   	   TB_ALARMPRESSAO.ID_BRIDGE, " +
	        "          TB_ALARMPRESSAO.ID_MEDIDOR, " +
	        "          TB_ALARMPRESSAO.METERPOSITION, " +
	        "          TB_ALARMPRESSAO.PRESSAOMIN, " +
	        "          TB_ALARMPRESSAO.PRESSAOMAX, " +
	        "          TB_ALARMPRESSAO.PRESSAOREAL, " +
	        "          TB_ALARMPRESSAO.DTINSERT, " +
	        "          TB_ALARMPRESSAOAPP.* " +
	        "FROM      TB_ALARMPRESSAO " +
	        "LEFT JOIN TB_ALARMPRESSAOAPP " +
	        "ON        TB_ALARMPRESSAO.ID_ALARMPRESSAO = TB_ALARMPRESSAOAPP.ID_ALARMPRESSAO " +
	        "AND       TB_ALARMPRESSAOAPP.ID_USER = ? " +
	        "WHERE     TB_ALARMPRESSAOAPP.ID_ALARMPRESSAOAPP IS NULL " +
	        "AND       DATE_FORMAT(TB_ALARMPRESSAO.DTINSERT, '%Y%m%d') = DATE_FORMAT(sysdate(), '%Y%m%d') "
            );
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	AlarmPressao alarmPressao = new AlarmPressao();
            	alarmPressao.setIdAlarmPressao(rs.getLong("ID_ALARMPRESSAO"));
            	alarmPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	alarmPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	alarmPressao.setIdBridge(rs.getLong("ID_BRIDGE"));
            	alarmPressao.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	alarmPressao.setMeterPosition(rs.getLong("METERPOSITION"));
            	alarmPressao.setPressaoMin(rs.getDouble("PRESSAOMIN"));
            	alarmPressao.setPressaoMax(rs.getDouble("PRESSAOMAX"));
            	alarmPressao.setPressaoReal(rs.getDouble("PRESSAOREAL"));
            	alarmPressao.setDtInsert(rs.getString("DTINSERT"));
            	
            	listAlarmPressao.add(alarmPressao);
            }
            
            return listAlarmPressao;
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
    
    public List<AlarmPressao> listarPerfilRepresentante(String idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AlarmPressao> listAlarmPressao = new ArrayList<AlarmPressao>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    TB_ALARMPRESSAO.ID_ALARMPRESSAO, " +
	        "          TB_ALARMPRESSAO.ID_EMPRESA, " +
	        "          TB_ALARMPRESSAO.ID_CONDOMINIO, " +
	        "   	   TB_ALARMPRESSAO.ID_BRIDGE, " +
	        "          TB_ALARMPRESSAO.ID_MEDIDOR, " +
	        "          TB_ALARMPRESSAO.METERPOSITION, " +
	        "          TB_ALARMPRESSAO.PRESSAOMIN, " +
	        "          TB_ALARMPRESSAO.PRESSAOMAX, " +
	        "          TB_ALARMPRESSAO.PRESSAOREAL, " +
	        "          TB_ALARMPRESSAO.DTINSERT, " +
	        "          TB_ALARMPRESSAOAPP.* " +
	        "FROM      TB_ALARMPRESSAO " +
	        "LEFT JOIN TB_ALARMPRESSAOAPP " +
	        "ON        TB_ALARMPRESSAO.ID_ALARMPRESSAO = TB_ALARMPRESSAOAPP.ID_ALARMPRESSAO " +
	        "AND       TB_ALARMPRESSAOAPP.ID_USER = ? " +
	        "LEFT JOIN VW_USEREMPRESA " +
	        "ON        TB_ALARMPRESSAO.ID_EMPRESA = VW_USEREMPRESA.ID_EMPRESA " +
	        "WHERE     TB_ALARMPRESSAOAPP.ID_ALARMPRESSAOAPP IS NULL " +
	        "AND       VW_USEREMPRESA.ID_USEREMPRESA IS NOT NULL " +
	        "AND       DATE_FORMAT(TB_ALARMPRESSAO.DTINSERT, '%Y%m%d') = DATE_FORMAT(sysdate(), '%Y%m%d') "
            );
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	AlarmPressao alarmPressao = new AlarmPressao();
            	alarmPressao.setIdAlarmPressao(rs.getLong("ID_ALARMPRESSAO"));
            	alarmPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	alarmPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	alarmPressao.setIdBridge(rs.getLong("ID_BRIDGE"));
            	alarmPressao.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	alarmPressao.setMeterPosition(rs.getLong("METERPOSITION"));
            	alarmPressao.setPressaoMin(rs.getDouble("PRESSAOMIN"));
            	alarmPressao.setPressaoMax(rs.getDouble("PRESSAOMAX"));
            	alarmPressao.setPressaoReal(rs.getDouble("PRESSAOREAL"));
            	alarmPressao.setDtInsert(rs.getString("DTINSERT"));
            	
            	listAlarmPressao.add(alarmPressao);
            }
            
            return listAlarmPressao;
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
    
    public List<AlarmPressao> listarPerfilAdministrador(String idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AlarmPressao> listAlarmPressao = new ArrayList<AlarmPressao>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    TB_ALARMPRESSAO.ID_ALARMPRESSAO, " +
	        "          TB_ALARMPRESSAO.ID_EMPRESA, " +
	        "          TB_ALARMPRESSAO.ID_CONDOMINIO, " +
	        "   	   TB_ALARMPRESSAO.ID_BRIDGE, " +
	        "          TB_ALARMPRESSAO.ID_MEDIDOR, " +
	        "          TB_ALARMPRESSAO.METERPOSITION, " +
	        "          TB_ALARMPRESSAO.PRESSAOMIN, " +
	        "          TB_ALARMPRESSAO.PRESSAOMAX, " +
	        "          TB_ALARMPRESSAO.PRESSAOREAL, " +
	        "          TB_ALARMPRESSAO.DTINSERT, " +
	        "          TB_ALARMPRESSAOAPP.* " +
	        "FROM      TB_ALARMPRESSAO " +
	        "LEFT JOIN TB_ALARMPRESSAOAPP " +
	        "ON        TB_ALARMPRESSAO.ID_ALARMPRESSAO = TB_ALARMPRESSAOAPP.ID_ALARMPRESSAO " +
	        "AND       TB_ALARMPRESSAOAPP.ID_USER = ? " +
	        "LEFT JOIN VW_USERCONDOMINIO " +
	        "ON        TB_ALARMPRESSAO.ID_CONDOMINIO = VW_USERCONDOMINIO.ID_CONDOMINIO " +
	        "WHERE     TB_ALARMPRESSAOAPP.ID_ALARMPRESSAOAPP IS NULL " +	        
	        "AND       VW_USERCONDOMINIO.ID_USERCONDOMINIO IS NOT NULL " +
	        "AND       DATE_FORMAT(TB_ALARMPRESSAO.DTINSERT, '%Y%m%d') = DATE_FORMAT(sysdate(), '%Y%m%d') "
            );
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	AlarmPressao alarmPressao = new AlarmPressao();
            	alarmPressao.setIdAlarmPressao(rs.getLong("ID_ALARMPRESSAO"));
            	alarmPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	alarmPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	alarmPressao.setIdBridge(rs.getLong("ID_BRIDGE"));
            	alarmPressao.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	alarmPressao.setMeterPosition(rs.getLong("METERPOSITION"));
            	alarmPressao.setPressaoMin(rs.getDouble("PRESSAOMIN"));
            	alarmPressao.setPressaoMax(rs.getDouble("PRESSAOMAX"));
            	alarmPressao.setPressaoReal(rs.getDouble("PRESSAOREAL"));
            	alarmPressao.setDtInsert(rs.getString("DTINSERT"));
            	
            	listAlarmPressao.add(alarmPressao);
            }
            
            return listAlarmPressao;
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
    
    public List<AlarmPressao> listarPerfilConsumidor(String idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AlarmPressao> listAlarmPressao = new ArrayList<AlarmPressao>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    TB_ALARMPRESSAO.ID_ALARMPRESSAO, " +
	        "          TB_ALARMPRESSAO.ID_EMPRESA, " +
	        "          TB_ALARMPRESSAO.ID_CONDOMINIO, " +
	        "   	   TB_ALARMPRESSAO.ID_BRIDGE, " +
	        "          TB_ALARMPRESSAO.ID_MEDIDOR, " +
	        "          TB_ALARMPRESSAO.METERPOSITION, " +
	        "          TB_ALARMPRESSAO.PRESSAOMIN, " +
	        "          TB_ALARMPRESSAO.PRESSAOMAX, " +
	        "          TB_ALARMPRESSAO.PRESSAOREAL, " +
	        "          TB_ALARMPRESSAO.DTINSERT, " +
	        "          TB_ALARMPRESSAOAPP.* " +
	        "FROM      TB_ALARMPRESSAO " +
	        "LEFT JOIN TB_ALARMPRESSAOAPP " +
	        "ON        TB_ALARMPRESSAO.ID_ALARMPRESSAO = TB_ALARMPRESSAOAPP.ID_ALARMPRESSAO " +
	        "AND       TB_ALARMPRESSAOAPP.ID_USER = ? " +
	        "LEFT JOIN VW_USERMEDIDOR " +
	        "ON        TB_ALARMPRESSAO.ID_MEDIDOR = VW_USERMEDIDOR.ID_MEDIDOR " +
	        "WHERE     TB_ALARMPRESSAOAPP.ID_ALARMPRESSAOAPP IS NULL " +	        
	        "AND       VW_USERMEDIDOR.ID_USERMEDIDOR IS NOT NULL " +
	        "AND       DATE_FORMAT(TB_ALARMPRESSAO.DTINSERT, '%Y%m%d') = DATE_FORMAT(sysdate(), '%Y%m%d') "
            );
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	AlarmPressao alarmPressao = new AlarmPressao();
            	alarmPressao.setIdAlarmPressao(rs.getLong("ID_ALARMPRESSAO"));
            	alarmPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	alarmPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	alarmPressao.setIdBridge(rs.getLong("ID_BRIDGE"));
            	alarmPressao.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	alarmPressao.setMeterPosition(rs.getLong("METERPOSITION"));
            	alarmPressao.setPressaoMin(rs.getDouble("PRESSAOMIN"));
            	alarmPressao.setPressaoMax(rs.getDouble("PRESSAOMAX"));
            	alarmPressao.setPressaoReal(rs.getDouble("PRESSAOREAL"));
            	alarmPressao.setDtInsert(rs.getString("DTINSERT"));
            	
            	listAlarmPressao.add(alarmPressao);
            }
            
            return listAlarmPressao;
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
    
    public static void main(String[] args) throws SQLException {
		
    	Connection connection = ConnectionFactory.getConnection();
    	
    	AlarmPressao alarmPressao = new AlarmPressao();
    	alarmPressao.setIdAlarmPressao(0l);;
    	alarmPressao.setIdEmpresa(2l);;
    	alarmPressao.setIdCondominio(8l);;
    	alarmPressao.setIdBridge(16l);;
    	alarmPressao.setIdMedidor(null);;
    	alarmPressao.setMeterPosition(1l);;
    	alarmPressao.setPressaoMin(10.0);;
    	alarmPressao.setPressaoMax(20.0);;
    	alarmPressao.setPressaoReal(15.0);;
    	alarmPressao.setDtInsert(null);;
    	
    	AlarmPressaoDAO alarmPressaoDAO = new AlarmPressaoDAO(connection);
    	//alarmPressaoDAO.inserir(alarmPressao);
    	
    	List<AlarmPressao> listAlarmPressao = alarmPressaoDAO.listarPerfilProgramador("2");
    	
    	System.out.println("listAlarmPressao " + listAlarmPressao.size());
    	
    	connection.close();
    	
    	System.out.println("fim");
	}
}

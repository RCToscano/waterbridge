package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.modelo.BridgeTpAlim;

public class BridgeTpAlimDAO {
    
    Connection connection = null;

    public BridgeTpAlimDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public BridgeTpAlim buscar(Long idBridgeTpAlim) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        BridgeTpAlim bridgeTpAlim = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "SELECT ID_BRIDGETPALIM, " +
            "		DESCRICAO " +
            "FROM   TB_BRIDGETPALIM " +
            "WHERE  ID_BRIDGETPALIM = ? "
            );

            stmt.setLong(1, idBridgeTpAlim);
            
            rs = stmt.executeQuery();

            if(rs.next()) {

            	bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim.setIdBridgeTpAlim(rs.getLong("ID_BRIDGETPALIM"));
            	bridgeTpAlim.setTpAlimentacao(rs.getString("DESCRICAO"));
            }
            
            return bridgeTpAlim;
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
    
    public List<BridgeTpAlim> listar() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BridgeTpAlim> list = new ArrayList<BridgeTpAlim>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_BRIDGETPALIM, " +
            "		DESCRICAO " +
            "FROM   TB_BRIDGETPALIM " 
            );

            rs = stmt.executeQuery();

            while(rs.next()) {

            	BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
                bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim.setIdBridgeTpAlim(rs.getLong("ID_BRIDGETPALIM"));
            	bridgeTpAlim.setTpAlimentacao(rs.getString("DESCRICAO"));
            	
            	list.add(bridgeTpAlim);
            }
            
            return list;
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
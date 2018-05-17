
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.modelo.BridgeTp;

public class BridgeTpDAO {
    
    Connection connection = null;

    public BridgeTpDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public BridgeTp buscar(Long idBridgeTp) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        BridgeTp bridgeTp = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "SELECT ID_BRIDGETP, " + 
            "       DESCRICAO " + 
            "FROM   TB_BRIDGETP " +
            "WHERE  ID_BRIDGETP = ? "
            );

            stmt.setLong(1, idBridgeTp);
            
            rs = stmt.executeQuery();

            if(rs.next()) {

            	bridgeTp = new BridgeTp();
            	bridgeTp.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
            	bridgeTp.setBridgeTp(rs.getString("DESCRICAO"));
            }
            
            return bridgeTp;
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
    
    public List<BridgeTp> listar() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BridgeTp> list = new ArrayList<BridgeTp>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_BRIDGETP, " +
            "		DESCRICAO " +
            "FROM   TB_BRIDGETP " 
            );

            rs = stmt.executeQuery();

            while(rs.next()) {

            	BridgeTp bridgeTp = new BridgeTp();
                bridgeTp = new BridgeTp();
            	bridgeTp.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
            	bridgeTp.setBridgeTp(rs.getString("DESCRICAO"));
            	
            	list.add(bridgeTp);
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
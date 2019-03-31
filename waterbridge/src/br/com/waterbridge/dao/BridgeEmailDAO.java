package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.BridgeEmail;

public class BridgeEmailDAO {
    
    private Connection connection;

    public BridgeEmailDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(BridgeEmail bridge) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_BRIDGEEMAIL ( " +
    		"       ID_BRIDGE, " +
    		"       ID_USER, " +
    		"       EMAIL, " +
    		"       DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,SYSDATE() " +
            ")");
            
            stmt.setObject(1, bridge.getIdBridge());
            stmt.setObject(2, bridge.getIdUser());
            stmt.setObject(3, bridge.getEmail());
            
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
    
    public void alterar(BridgeEmail bridge) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(bridge.getIdBridgeEmail());
        
            stmt = connection.prepareStatement(
            "UPDATE TB_BRIDGEEMAIL SET " +
    		"       ID_USER = ?, " +
    		"       EMAIL = ?, " +
    		"       DTINSERT = SYSDATE() " +
    		"WHERE  ID_BRIDGEEMAIL = ? ");
            
            stmt.setObject(1, bridge.getIdUser());
            stmt.setObject(2, bridge.getEmail());
            stmt.setObject(3, bridge.getIdBridgeEmail());
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
    
    public void alterarDtEnvioEmail(BridgeEmail bridge) throws SQLException {
    	
    	PreparedStatement stmt = null;
    	
    	try {
    		
    		//LOGA REGISTRO ANTES DE ALTERAR
    		logar(bridge.getIdBridgeEmail());
    		
    		stmt = connection.prepareStatement(
    				"UPDATE TB_BRIDGEEMAIL SET " +
    						"       DT_ENVIO_EMAIL = SYSDATE() " +
    				"WHERE  ID_BRIDGEEMAIL = ? ");
    		
    		stmt.setObject(1, bridge.getIdBridgeEmail());
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

    public void logar(Long idBridge) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "INSERT INTO TB_BRIDGEEMAILLOG " +
            "SELECT * " +
            "FROM   TB_BRIDGEEMAIL " +
            "WHERE  ID_BRIDGEEMAIL = ? "
            );

            stmt.setLong(1, idBridge);
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
    
    public void excluir(BridgeEmail bridge) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(bridge.getIdBridgeEmail());
        
            stmt = connection.prepareStatement(
            "DELETE FROM TB_BRIDGEEMAIL WHERE ID_BRIDGEEMAIL = ? ");
            
            stmt.setObject(1, bridge.getIdBridgeEmail());
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
    
    public List<BridgeEmail> listarPorBridge(Long idBridge) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BridgeEmail> listBridge = new ArrayList<>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_BRIDGEEMAIL, " +
			"		ID_BRIDGE, " +
            "       ID_USER, " +
            "       EMAIL, " +
            "       DT_ENVIO_EMAIL, " +
            " 	    DTINSERT " +
            "FROM   TB_BRIDGEEMAIL " +
            "WHERE  ID_BRIDGE = ?"
            );

            stmt.setLong(1, idBridge);
            rs = stmt.executeQuery();

            while (rs.next()) {
            	BridgeEmail bridge = new BridgeEmail();
            	bridge.setIdBridgeEmail(rs.getLong("ID_BRIDGEEMAIL"));
            	bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
                bridge.setIdUser(rs.getLong("ID_USER"));
                bridge.setEmail(rs.getString("EMAIL"));
                bridge.setDtEnvioEmail(rs.getString("DT_ENVIO_EMAIL"));
                bridge.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
                listBridge.add(bridge);
            }
            return listBridge;
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

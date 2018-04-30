package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.BridgeTpAlim;

public class BridgeDAO {
    
    private Connection connection;

    public BridgeDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(Bridge bridge) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_BRIDGE ( " +
    		//"       ID_BRIDGE, " +
    		"       ID_BRIDGETPALIM, " +
    		"       ID_USER, " +
    		"       DEVICENUM, " +
    		"       ATIVATIONDATE, " +
    		"       TOKENVALID, " +
    		"       DESCRICAO, " +
    		"       CUSTOMENSAL, " +
    		"       TAXAENVIO, " +
    		"       SITUACAO, " +
    		"       DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,?,?,?,?,?,?, " +
            "       SYSDATE() " +
            ")");
            
            //stmt.setObject(1, bridge.getIdBridge());
            stmt.setObject(1, bridge.getBridgeTpAlim().getIdBridgeTpAlim());
            stmt.setObject(2, bridge.getIdUser());
            stmt.setObject(3, bridge.getDeviceNum());
            stmt.setObject(4, Auxiliar.formataDtBancoHr(bridge.getDtAtivacao()));
            stmt.setObject(5, Auxiliar.formataDtBanco(bridge.getValidadeToken()));
            stmt.setObject(6, bridge.getDescricao());
            stmt.setObject(7, bridge.getCustoMensal());
            stmt.setObject(8, bridge.getTaxaEnvio());
            stmt.setObject(9, bridge.getSituacao());
            //stmt.setObject(10, bridge.getDtInsert());
            
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
    
    public void alterar(Bridge bridge) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(bridge.getIdBridge());
        
            stmt = connection.prepareStatement(
            "UPDATE TB_BRIDGE SET " +
    		"       ID_BRIDGETPALIM = ?, " +
    		"       ID_USER = ?, " +
    		"       DEVICENUM = ?, " +
    		"       ATIVATIONDATE = ?, " +
    		"       TOKENVALID = ?, " +
    		"       DESCRICAO = ?, " +
    		"       CUSTOMENSAL = ?, " +
    		"       TAXAENVIO = ?, " +
    		"       SITUACAO = ?, " +
    		"       DTINSERT = SYSDATE() " +
    		"WHERE  ID_BRIDGE = ? ");
            
            stmt.setObject(1, bridge.getBridgeTpAlim().getIdBridgeTpAlim());
            stmt.setObject(2, bridge.getIdUser());
            stmt.setObject(3, bridge.getDeviceNum());
            stmt.setObject(4, Auxiliar.formataDtBancoHr(bridge.getDtAtivacao()));
            stmt.setObject(5, Auxiliar.formataDtBanco(bridge.getValidadeToken()));
            stmt.setObject(6, bridge.getDescricao());
            stmt.setObject(7, bridge.getCustoMensal());
            stmt.setObject(8, bridge.getTaxaEnvio());
            stmt.setObject(9, bridge.getSituacao());
            //stmt.setObject(10, bridge.getDtInsert());
            stmt.setObject(10, bridge.getIdBridge());

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
            "INSERT INTO TB_BRIDGELOG " +
            "SELECT * " +
            "FROM   TB_BRIDGELOG " +
            "WHERE  ID_BRIDGE = ? "
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
    
    public Bridge buscar(Long idBridge) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Bridge bridge = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_BRIDGE, " +
            "       ID_BRIDGETPALIM, " +
            "       ID_USER, " +
            "	    DEVICENUM, " +
            "       ATIVATIONDATE, " +
            "       TOKENVALID, " +
            "       DESCRICAO, " +
            "       CUSTOMENSAL, " +
            "       TAXAENVIO, " +
            "       SITUACAO, " +
            " 	    DTINSERT " +
            "FROM   TB_BRIDGE " +
            "WHERE  ID_BRIDGE = ? "
            );

            stmt.setLong(1, idBridge);

            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim = new BridgeTpAlimDAO(connection).buscar(rs.getLong("ID_BRIDGETPALIM"));
            	
            	bridge = new Bridge();
            	bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
                bridge.setBridgeTpAlim(bridgeTpAlim);
                bridge.setIdUser(rs.getLong("ID_USER"));
                bridge.setDeviceNum(rs.getString("DEVICENUM"));
                bridge.setDtAtivacao(Auxiliar.formataDtTelaHr(rs.getString("ATIVATIONDATE")));
                bridge.setValidadeToken(Auxiliar.formataDtTela(rs.getString("TOKENVALID")));
                bridge.setDescricao(rs.getString("DESCRICAO"));
                bridge.setCustoMensal(rs.getDouble("CUSTOMENSAL"));
                bridge.setTaxaEnvio(rs.getLong("TAXAENVIO"));
                bridge.setSituacao(rs.getString("SITUACAO"));
                bridge.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));                
            }
            
            return bridge;
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
    
    public Bridge buscarPorDeviceNum(String deviceNum) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Bridge bridge = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_BRIDGE, " +
            "       ID_BRIDGETPALIM, " +
            "       ID_USER, " +
            "	    DEVICENUM, " +
            "       ATIVATIONDATE, " +
            "       TOKENVALID, " +
            "       DESCRICAO, " +
            "       CUSTOMENSAL, " +
            "       TAXAENVIO, " +
            "       SITUACAO, " +
            " 	    DTINSERT " +
            "FROM   TB_BRIDGE " +
            "WHERE  DEVICENUM = ? "
            );

            stmt.setString(1, deviceNum);

            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim = new BridgeTpAlimDAO(connection).buscar(rs.getLong("ID_BRIDGETPALIM"));
            	
            	bridge = new Bridge();
            	bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
                bridge.setBridgeTpAlim(bridgeTpAlim);
                bridge.setIdUser(rs.getLong("ID_USER"));
                bridge.setDeviceNum(rs.getString("DEVICENUM"));
                bridge.setDtAtivacao(Auxiliar.formataDtTelaHr(rs.getString("ATIVATIONDATE")));
                bridge.setValidadeToken(Auxiliar.formataDtTela(rs.getString("TOKENVALID")));
                bridge.setDescricao(rs.getString("DESCRICAO"));
                bridge.setCustoMensal(rs.getDouble("CUSTOMENSAL"));
                bridge.setTaxaEnvio(rs.getLong("TAXAENVIO"));
                bridge.setSituacao(rs.getString("SITUACAO"));
                bridge.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));                
            }
            
            return bridge;
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
    
    public List<Bridge> listarPorDeviceNum(String deviceNum) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bridge> listBridge = new ArrayList<Bridge>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_BRIDGE, " +
            "       ID_BRIDGETPALIM, " +
            "       ID_USER, " +
            "	    DEVICENUM, " +
            "       ATIVATIONDATE, " +
            "       TOKENVALID, " +
            "       DESCRICAO, " +
            "       CUSTOMENSAL, " +
            "       TAXAENVIO, " +
            "       SITUACAO, " +
            " 	    DTINSERT " +
            "FROM   TB_BRIDGE " +
            "WHERE  UPPER(DEVICENUM) LIKE '%" + deviceNum.toUpperCase() + "%' "
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim = new BridgeTpAlimDAO(connection).buscar(rs.getLong("ID_BRIDGETPALIM"));
            	
            	Bridge bridge = new Bridge();
            	bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
                bridge.setBridgeTpAlim(bridgeTpAlim);
                bridge.setIdUser(rs.getLong("ID_USER"));
                bridge.setDeviceNum(rs.getString("DEVICENUM"));
                bridge.setDtAtivacao(Auxiliar.formataDtTelaHr(rs.getString("ATIVATIONDATE")));
                bridge.setValidadeToken(Auxiliar.formataDtTela(rs.getString("TOKENVALID")));
                bridge.setDescricao(rs.getString("DESCRICAO"));
                bridge.setCustoMensal(rs.getDouble("CUSTOMENSAL"));
                bridge.setTaxaEnvio(rs.getLong("TAXAENVIO"));
                bridge.setSituacao(rs.getString("SITUACAO"));
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

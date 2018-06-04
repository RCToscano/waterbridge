package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.BridgeTp;
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
    		"       ID_CONDOMINIO, " +
    		"       DEVICENUM, " +
    		"       ATIVATIONDATE, " +
    		"       TOKENVALID, " +
    		"       DESCRICAO, " +
    		"       CUSTOMENSAL, " +
    		"       TAXAENVIO, " +
    		"       SITUACAO, " +
    		"       ID_BRIDGETP, " +
    		"       DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,?,?,?,?,?,?,?, " +
            "       ?,SYSDATE() " +
            ")");
            
            //stmt.setObject(1, bridge.getIdBridge());
            stmt.setObject(1, bridge.getBridgeTpAlim().getIdBridgeTpAlim());
            stmt.setObject(2, bridge.getIdUser());
            stmt.setObject(3, bridge.getIdCondominio());
            stmt.setObject(4, bridge.getDeviceNum());
            stmt.setObject(5, Auxiliar.formataDtBancoHr(bridge.getDtAtivacao()));
            stmt.setObject(6, Auxiliar.formataDtBanco(bridge.getValidadeToken()));
            stmt.setObject(7, bridge.getDescricao());
            stmt.setObject(8, bridge.getCustoMensal());
            stmt.setObject(9, bridge.getTaxaEnvio());
            stmt.setObject(10, bridge.getSituacao());
            stmt.setObject(11, bridge.getBridgeTp().getIdBridgeTp());
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
    		"       ID_CONDOMINIO = ?, " +
    		"       DEVICENUM = ?, " +
    		"       ATIVATIONDATE = ?, " +
    		"       TOKENVALID = ?, " +
    		"       DESCRICAO = ?, " +
    		"       CUSTOMENSAL = ?, " +
    		"       TAXAENVIO = ?, " +
    		"       SITUACAO = ?, " +
    		"       ID_BRIDGETP = ?, " +
    		"       DTINSERT = SYSDATE() " +
    		"WHERE  ID_BRIDGE = ? ");
            
            stmt.setObject(1, bridge.getBridgeTpAlim().getIdBridgeTpAlim());
            stmt.setObject(2, bridge.getIdUser());
            stmt.setObject(3, bridge.getIdCondominio());
            stmt.setObject(4, bridge.getDeviceNum());
            stmt.setObject(5, Auxiliar.formataDtBancoHr(bridge.getDtAtivacao()));
            stmt.setObject(6, Auxiliar.formataDtBanco(bridge.getValidadeToken()));
            stmt.setObject(7, bridge.getDescricao());
            stmt.setObject(8, bridge.getCustoMensal());
            stmt.setObject(9, bridge.getTaxaEnvio());
            stmt.setObject(10, bridge.getSituacao());
            stmt.setObject(11, bridge.getBridgeTp().getIdBridgeTp());
            //stmt.setObject(10, bridge.getDtInsert());
            stmt.setObject(12, bridge.getIdBridge());

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
            "FROM   TB_BRIDGE " +
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
    
    public Bridge buscarPorId(Long idBridge) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Bridge bridge = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_BRIDGE, " +
    		"		ID_BRIDGETP, " +
            "       ID_BRIDGETPALIM, " +
            "       ID_USER, " +
            "       ID_CONDOMINIO, " +
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
            	
            	BridgeTp bridgeTp = new BridgeTp();
            	bridgeTp = new BridgeTpDAO(connection).buscar(rs.getLong("ID_BRIDGETP"));
            	
            	BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim = new BridgeTpAlimDAO(connection).buscar(rs.getLong("ID_BRIDGETPALIM"));
            	
            	bridge = new Bridge();
            	bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
            	bridge.setBridgeTp(bridgeTp);
            	bridge.setBridgeTpAlim(bridgeTpAlim);
                bridge.setIdUser(rs.getLong("ID_USER"));
                bridge.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
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
    
    public List<Bridge> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bridge> listBridge = new ArrayList<Bridge>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_BRIDGE, " +
			"		ID_BRIDGETP, " +
            "       ID_BRIDGETPALIM, " +
            "       ID_USER, " +
            "       ID_CONDOMINIO, " +
            "	    DEVICENUM, " +
            "       ATIVATIONDATE, " +
            "       TOKENVALID, " +
            "       DESCRICAO, " +
            "       CUSTOMENSAL, " +
            "       TAXAENVIO, " +
            "       SITUACAO, " +
            " 	    DTINSERT " +
            "FROM   TB_BRIDGE " +
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	BridgeTp bridgeTp = new BridgeTp();
            	bridgeTp = new BridgeTpDAO(connection).buscar(rs.getLong("ID_BRIDGETP"));
            	
            	BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim = new BridgeTpAlimDAO(connection).buscar(rs.getLong("ID_BRIDGETPALIM"));
            	
            	Bridge bridge = new Bridge();
            	bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
            	bridge.setBridgeTp(bridgeTp);
                bridge.setBridgeTpAlim(bridgeTpAlim);
                bridge.setIdUser(rs.getLong("ID_USER"));
                bridge.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
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
    
    public Bridge buscarPorDeviceNum(String deviceNum) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Bridge bridge = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_BRIDGE, " +
			"		ID_BRIDGETP, " +
            "       ID_BRIDGETPALIM, " +
            "       ID_USER, " +
            "       ID_CONDOMINIO, " +
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
            	
            	BridgeTp bridgeTp = new BridgeTp();
            	bridgeTp = new BridgeTpDAO(connection).buscar(rs.getLong("ID_BRIDGETP"));
            	
            	BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim = new BridgeTpAlimDAO(connection).buscar(rs.getLong("ID_BRIDGETPALIM"));
            	
            	bridge = new Bridge();
            	bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
            	bridge.setBridgeTp(bridgeTp);
                bridge.setBridgeTpAlim(bridgeTpAlim);
                bridge.setIdUser(rs.getLong("ID_USER"));
                bridge.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
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
			"		ID_BRIDGETP, " +
            "       ID_BRIDGETPALIM, " +
            "       ID_USER, " +
            "       ID_CONDOMINIO, " +
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
            	
            	BridgeTp bridgeTp = new BridgeTp();
            	bridgeTp = new BridgeTpDAO(connection).buscar(rs.getLong("ID_BRIDGETP"));
            	
            	BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim = new BridgeTpAlimDAO(connection).buscar(rs.getLong("ID_BRIDGETPALIM"));
            	
            	Bridge bridge = new Bridge();
            	bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
            	bridge.setBridgeTp(bridgeTp);
                bridge.setBridgeTpAlim(bridgeTpAlim);
                bridge.setIdUser(rs.getLong("ID_USER"));
                bridge.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
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
    
    public List<Bridge> listarPorIdCondominio(Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bridge> listBridge = new ArrayList<Bridge>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_BRIDGE, " +
			"		ID_BRIDGETP, " +
            "       ID_BRIDGETPALIM, " +
            "       ID_USER, " +
            "       ID_CONDOMINIO, " +
            "	    DEVICENUM, " +
            "       ATIVATIONDATE, " +
            "       TOKENVALID, " +
            "       DESCRICAO, " +
            "       CUSTOMENSAL, " +
            "       TAXAENVIO, " +
            "       SITUACAO, " +
            " 	    DTINSERT " +
            "FROM   TB_BRIDGE " +
            "WHERE  ID_CONDOMINIO = ? "
            );
            
            stmt.setObject(1, idCondominio);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	BridgeTp bridgeTp = new BridgeTp();
            	bridgeTp = new BridgeTpDAO(connection).buscar(rs.getLong("ID_BRIDGETP"));
            	
            	BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim = new BridgeTpAlimDAO(connection).buscar(rs.getLong("ID_BRIDGETPALIM"));
            	
            	Bridge bridge = new Bridge();
            	bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
            	bridge.setBridgeTp(bridgeTp);
                bridge.setBridgeTpAlim(bridgeTpAlim);
                bridge.setIdUser(rs.getLong("ID_USER"));
                bridge.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
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
    
    public List<Bridge> listarPorUsuario(Long idUser, Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bridge> listBridge = new ArrayList<Bridge>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    TB_BRIDGE.ID_BRIDGE, " +
			"		   TB_BRIDGE.ID_BRIDGETP, " +
            "          TB_BRIDGE.ID_BRIDGETPALIM, " +
            "          TB_BRIDGE.ID_USER, " +
            "          TB_BRIDGE.ID_CONDOMINIO, " +
            "	       TB_BRIDGE.DEVICENUM, " +
            "          TB_BRIDGE.ATIVATIONDATE, " +
            "          TB_BRIDGE.TOKENVALID, " +
            "          TB_BRIDGE.DESCRICAO, " +
            "          TB_BRIDGE.CUSTOMENSAL, " +
            "          TB_BRIDGE.TAXAENVIO, " +
            "          TB_BRIDGE.SITUACAO, " +
            " 	       TB_BRIDGE.DTINSERT " +
            "FROM      TB_BRIDGE " +
            "LEFT JOIN VW_USERBRIDGEID " +
		    "ON        TB_BRIDGE.ID_BRIDGE = VW_USERBRIDGEID.ID_BRIDGE " +
		    "WHERE     VW_USERBRIDGEID.ID_USER = ? " +
		    "AND       TB_BRIDGE.ID_CONDOMINIO = ? "
            );
            
            stmt.setObject(1, idUser);
            stmt.setObject(2, idCondominio);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	BridgeTp bridgeTp = new BridgeTp();
            	bridgeTp = new BridgeTpDAO(connection).buscar(rs.getLong("ID_BRIDGETP"));
            	
            	BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim = new BridgeTpAlimDAO(connection).buscar(rs.getLong("ID_BRIDGETPALIM"));
            	
            	Bridge bridge = new Bridge();
            	bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
            	bridge.setBridgeTp(bridgeTp);
                bridge.setBridgeTpAlim(bridgeTpAlim);
                bridge.setIdUser(rs.getLong("ID_USER"));
                bridge.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
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
    
    public List<Bridge> listarWaterBridgePorUsuario(Long idUser, Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bridge> listBridge = new ArrayList<Bridge>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    TB_BRIDGE.ID_BRIDGE, " +
			"		   TB_BRIDGE.ID_BRIDGETP, " +
            "          TB_BRIDGE.ID_BRIDGETPALIM, " +
            "          TB_BRIDGE.ID_USER, " +
            "          TB_BRIDGE.ID_CONDOMINIO, " +
            "	       TB_BRIDGE.DEVICENUM, " +
            "          TB_BRIDGE.ATIVATIONDATE, " +
            "          TB_BRIDGE.TOKENVALID, " +
            "          TB_BRIDGE.DESCRICAO, " +
            "          TB_BRIDGE.CUSTOMENSAL, " +
            "          TB_BRIDGE.TAXAENVIO, " +
            "          TB_BRIDGE.SITUACAO, " +
            " 	       TB_BRIDGE.DTINSERT " +
            "FROM      TB_BRIDGE " +
            "LEFT JOIN VW_USERBRIDGEID " +
		    "ON        TB_BRIDGE.ID_BRIDGE = VW_USERBRIDGEID.ID_BRIDGE " +
		    "WHERE     VW_USERBRIDGEID.ID_USER = ? " +
		    "AND       TB_BRIDGE.ID_CONDOMINIO = ? " +
		    "AND       TB_BRIDGE.ID_BRIDGETP IN(1,3) "
            );
            
            stmt.setObject(1, idUser);
            stmt.setObject(2, idCondominio);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	BridgeTp bridgeTp = new BridgeTp();
            	bridgeTp = new BridgeTpDAO(connection).buscar(rs.getLong("ID_BRIDGETP"));
            	
            	BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim = new BridgeTpAlimDAO(connection).buscar(rs.getLong("ID_BRIDGETPALIM"));
            	
            	Bridge bridge = new Bridge();
            	bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
            	bridge.setBridgeTp(bridgeTp);
                bridge.setBridgeTpAlim(bridgeTpAlim);
                bridge.setIdUser(rs.getLong("ID_USER"));
                bridge.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
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
    
    public List<Bridge> listarPressureBridgePorUsuario(Long idUser, Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bridge> listBridge = new ArrayList<Bridge>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    TB_BRIDGE.ID_BRIDGE, " +
			"		   TB_BRIDGE.ID_BRIDGETP, " +
            "          TB_BRIDGE.ID_BRIDGETPALIM, " +
            "          TB_BRIDGE.ID_USER, " +
            "          TB_BRIDGE.ID_CONDOMINIO, " +
            "	       TB_BRIDGE.DEVICENUM, " +
            "          TB_BRIDGE.ATIVATIONDATE, " +
            "          TB_BRIDGE.TOKENVALID, " +
            "          TB_BRIDGE.DESCRICAO, " +
            "          TB_BRIDGE.CUSTOMENSAL, " +
            "          TB_BRIDGE.TAXAENVIO, " +
            "          TB_BRIDGE.SITUACAO, " +
            " 	       TB_BRIDGE.DTINSERT " +
            "FROM      TB_BRIDGE " +
            "LEFT JOIN VW_USERBRIDGEID " +
		    "ON        TB_BRIDGE.ID_BRIDGE = VW_USERBRIDGEID.ID_BRIDGE " +
		    "WHERE     VW_USERBRIDGEID.ID_USER = ? " +
		    "AND       TB_BRIDGE.ID_CONDOMINIO = ? " +
		    "AND       TB_BRIDGE.ID_BRIDGETP = 2 "
            );
            
            stmt.setObject(1, idUser);
            stmt.setObject(2, idCondominio);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	BridgeTp bridgeTp = new BridgeTp();
            	bridgeTp = new BridgeTpDAO(connection).buscar(rs.getLong("ID_BRIDGETP"));
            	
            	BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
            	bridgeTpAlim = new BridgeTpAlimDAO(connection).buscar(rs.getLong("ID_BRIDGETPALIM"));
            	
            	Bridge bridge = new Bridge();
            	bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
            	bridge.setBridgeTp(bridgeTp);
                bridge.setBridgeTpAlim(bridgeTpAlim);
                bridge.setIdUser(rs.getLong("ID_USER"));
                bridge.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
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
    
    public List<Bridge> listarTodos() throws Exception {
    	
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	List<Bridge> listBridge = new ArrayList<Bridge>();
    	
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT ID_BRIDGE, " +
					"		ID_BRIDGETP, " +
					"       ID_BRIDGETPALIM, " +
					"       ID_USER, " +
					"       ID_CONDOMINIO, " +
					"	    DEVICENUM, " +
					"       ATIVATIONDATE, " +
					"       TOKENVALID, " +
					"       DESCRICAO, " +
					"       CUSTOMENSAL, " +
					"       TAXAENVIO, " +
					"       SITUACAO, " +
					" 	    DTINSERT " +
					"FROM   TB_BRIDGE " 
    				);
    		
    		rs = stmt.executeQuery();
    		
    		while (rs.next()) {
    			
    			BridgeTp bridgeTp = new BridgeTp();
            	bridgeTp = new BridgeTpDAO(connection).buscar(rs.getLong("ID_BRIDGETP"));
    			
    			BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
    			bridgeTpAlim = new BridgeTpAlimDAO(connection).buscar(rs.getLong("ID_BRIDGETPALIM"));
    			
    			Bridge bridge = new Bridge();
    			bridge.setIdBridge(rs.getLong("ID_BRIDGE"));
    			bridge.setBridgeTp(bridgeTp);
    			bridge.setBridgeTpAlim(bridgeTpAlim);
    			bridge.setIdUser(rs.getLong("ID_USER"));
    			bridge.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
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
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
}

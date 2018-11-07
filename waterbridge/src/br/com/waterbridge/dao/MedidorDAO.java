package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.FabricMedidor;
import br.com.waterbridge.modelo.Medidor;

public class MedidorDAO {
	
	private Connection connection;

    public MedidorDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void inserir(Medidor medidor) throws Exception {
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	stmt = connection.prepareStatement(
    				"INSERT INTO TB_MEDIDOR ( " +
					"ID_FABRICMEDIDOR, " +
					"MODELO, " +
					"SERIE, " +
					"TIPO, " +
					"CHAVEDECRIPTO, " +
					"VALIDBATERIA, " +
					"OBS, " +
					"SITUACAO, " +
					"METERID, " +
					"ID_BRIDGE, " +
					"DEVICENUM, " +
					"METERPOSITION, " +
					"ID_USER, " +
					"ID_CONDOMINIO, " +
					"ENDERECO, " +
					"NUMERO, " +
					"COMPL, " +
					"MUNICIPIO, " +
					"UF, " +
					"CEP, " +
					"COORDX, " +
					"COORDY, " +
					"DTINSERT " +
					")" +
    				" VALUES ( " +
            		"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            		"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            		"?, ?, sysdate() ) " 
    				);
            		
            stmt.setObject(1, medidor.getIdFabricMedidor());
            stmt.setObject(2, medidor.getModelo());
            stmt.setObject(3, medidor.getSerie());
            stmt.setObject(4, medidor.getTipo());
            stmt.setObject(5, medidor.getChaveDeCripto());
            stmt.setObject(6, medidor.getValidBateria());
            stmt.setObject(7, medidor.getObs());
            stmt.setObject(8, medidor.getSituacao());
            stmt.setObject(9, medidor.getNumeroMedidor());
            stmt.setObject(10, medidor.getIdBridge());
            stmt.setObject(11, medidor.getDeviceNum());
            stmt.setObject(12, medidor.getMeterPosition());
            stmt.setObject(13, medidor.getIdUser());
            stmt.setObject(14, medidor.getIdCondominio());
            stmt.setObject(15, medidor.getEndereco());
            stmt.setObject(16, medidor.getNumero());
            stmt.setObject(17, medidor.getCompl());
            stmt.setObject(18, medidor.getMunicipio());
            stmt.setObject(19, medidor.getUf());
            stmt.setObject(20, medidor.getCep());
            stmt.setObject(21, medidor.getCoordX());
            stmt.setObject(22, medidor.getCoordY());
            stmt.executeUpdate();
		} 
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public void alterar(Medidor medidor) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		
    		//LOGA REGISTRO ANTES DE ALTERAR
            logar(medidor.getIdMedidor());
    		
    		stmt = connection.prepareStatement(
    				" UPDATE TB_MEDIDOR SET " +
					"ID_FABRICMEDIDOR = ?, " +
					"MODELO = ?, " +
					"SERIE = ?, " +
					"TIPO = ?, " +
					"CHAVEDECRIPTO = ?, " +
					"VALIDBATERIA = ?, " +
					"OBS = ?, " +
					"SITUACAO = ?, " +
					"METERID = ?, " +
					"ID_BRIDGE = ?, " +
					"DEVICENUM = ?, " +
					"METERPOSITION = ?, " +
					"ID_USER = ?, " +
					"ID_CONDOMINIO = ?, " +
					"ENDERECO = ?, " +
					"NUMERO = ?, " +
					"COMPL = ?, " +
					"MUNICIPIO = ?, " +
					"UF = ?, " +
					"CEP = ?, " +
					"COORDX = ?, " +
					"COORDY = ?, " +
					"DTINSERT = sysdate() " +
					"WHERE ID_MEDIDOR = ? "
    				);
    		
    		stmt.setObject(1, medidor.getIdFabricMedidor());
    		stmt.setObject(2, medidor.getModelo());
    		stmt.setObject(3, medidor.getSerie());
    		stmt.setObject(4, medidor.getTipo());
    		stmt.setObject(5, medidor.getChaveDeCripto());
    		stmt.setObject(6, medidor.getValidBateria());
    		stmt.setObject(7, medidor.getObs());
    		stmt.setObject(8, medidor.getSituacao());
    		stmt.setObject(9, medidor.getNumeroMedidor());
    		stmt.setObject(10, medidor.getIdBridge());
    		stmt.setObject(11, medidor.getDeviceNum());
    		stmt.setObject(12, medidor.getMeterPosition());
    		stmt.setObject(13, medidor.getIdUser());
    		stmt.setObject(14, medidor.getIdCondominio());
    		stmt.setObject(15, medidor.getEndereco());
            stmt.setObject(16, medidor.getNumero());
            stmt.setObject(17, medidor.getCompl());
            stmt.setObject(18, medidor.getMunicipio());
            stmt.setObject(19, medidor.getUf());
            stmt.setObject(20, medidor.getCep());
            stmt.setObject(21, medidor.getCoordX());
            stmt.setObject(22, medidor.getCoordY());            
            stmt.setObject(23, medidor.getIdMedidor());
    		
    		stmt.executeUpdate();
    	} 
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    
    public void logar(Long idMedidor) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
            "INSERT INTO TB_MEDIDORLOG " +
            "SELECT * " +
            "FROM   TB_MEDIDOR " +
            "WHERE  ID_MEDIDOR = ? "
            );

            stmt.setLong(1, idMedidor);
            stmt.executeUpdate();
        }
        finally {
            if(stmt != null)
                stmt.close();
        }
    }
    
    public Medidor buscarPorId(String idMedidor) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT TB_MEDIDOR.* " +
		            "  FROM TB_MEDIDOR " +
		            " WHERE TB_MEDIDOR.ID_MEDIDOR = ? "
		            );
    		
    		stmt.setObject(1, idMedidor);
            
            rs = stmt.executeQuery();
    		
    		Medidor medidor = new Medidor();
            if (rs.next()) {
            	medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	medidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
        		medidor.setModelo(rs.getString("MODELO"));
        		medidor.setSerie(rs.getString("SERIE"));
        		medidor.setTipo(rs.getString("TIPO"));
        		medidor.setChaveDeCripto(rs.getString("CHAVEDECRIPTO"));
        		medidor.setValidBateria(Auxiliar.converteInteger(rs.getString("VALIDBATERIA")));
        		medidor.setNumeroMedidor(rs.getString("METERID"));
        		medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
        		medidor.setDeviceNum(rs.getString("DEVICENUM"));
        		medidor.setMeterPosition(rs.getInt("METERPOSITION"));
        		medidor.setObs(rs.getString("OBS"));
        		medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
        		medidor.setEndereco(rs.getString("ENDERECO"));
        		medidor.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
        		medidor.setCompl(rs.getString("COMPL"));
        		medidor.setMunicipio(rs.getString("MUNICIPIO"));
        		medidor.setUf(rs.getString("UF"));
        		medidor.setCep(rs.getString("CEP"));
        		medidor.setCoordX(rs.getString("COORDX"));
        		medidor.setCoordY(rs.getString("COORDY"));
        		medidor.setSituacao(rs.getString("SITUACAO"));
        		medidor.setDtInsert(rs.getString("DTINSERT"));
        		
            }
            return medidor;
    	} 
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    
    public Medidor buscarPorBridgePosicao(Long idBridge, int posicao) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT TB_MEDIDOR.* " +
					"  FROM TB_MEDIDOR " +
					" WHERE TB_MEDIDOR.ID_BRIDGE = ? " +
					"   AND TB_MEDIDOR.METERPOSITION = ? "
    				);
    		
    		stmt.setObject(1, idBridge);
    		stmt.setObject(2, posicao);
    		
    		rs = stmt.executeQuery();
    		
    		Medidor medidor = null;
    		if (rs.next()) {
    			medidor = new Medidor();
    			medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
    			medidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
    			medidor.setModelo(rs.getString("MODELO"));
    			medidor.setSerie(rs.getString("SERIE"));
    			medidor.setTipo(rs.getString("TIPO"));
    			medidor.setChaveDeCripto(rs.getString("CHAVEDECRIPTO"));
    			medidor.setValidBateria(Auxiliar.converteInteger(rs.getString("VALIDBATERIA")));
    			medidor.setNumeroMedidor(rs.getString("METERID"));
    			medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
    			medidor.setDeviceNum(rs.getString("DEVICENUM"));
    			medidor.setMeterPosition(rs.getInt("METERPOSITION"));
    			medidor.setObs(rs.getString("OBS"));
    			medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
    			medidor.setEndereco(rs.getString("ENDERECO"));
    			medidor.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
        		medidor.setCompl(rs.getString("COMPL"));
        		medidor.setMunicipio(rs.getString("MUNICIPIO"));
        		medidor.setUf(rs.getString("UF"));
        		medidor.setCep(rs.getString("CEP"));
        		medidor.setCoordX(rs.getString("COORDX"));
        		medidor.setCoordY(rs.getString("COORDY"));
    			medidor.setSituacao(rs.getString("SITUACAO"));
    			medidor.setDtInsert(rs.getString("DTINSERT"));
    			
    		}
    		return medidor;
    	} 
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    
    public Medidor buscarPorFabricanteNumero(Long idfabricMedidor, String numero) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT TB_MEDIDOR.* " +
					"  FROM TB_MEDIDOR " +
					" WHERE TB_MEDIDOR.ID_FABRICMEDIDOR = ? " +
					"   AND TB_MEDIDOR.METERID = ? "
    				);
    		
    		stmt.setObject(1, idfabricMedidor);
    		stmt.setObject(2, numero);
    		
    		rs = stmt.executeQuery();
    		
    		Medidor medidor = null;
    		if (rs.next()) {
    			medidor = new Medidor();
    			medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
    			medidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
    			medidor.setModelo(rs.getString("MODELO"));
    			medidor.setSerie(rs.getString("SERIE"));
    			medidor.setTipo(rs.getString("TIPO"));
    			medidor.setChaveDeCripto(rs.getString("CHAVEDECRIPTO"));
    			medidor.setValidBateria(Auxiliar.converteInteger(rs.getString("VALIDBATERIA")));
    			medidor.setNumeroMedidor(rs.getString("METERID"));
    			medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
    			medidor.setDeviceNum(rs.getString("DEVICENUM"));
    			medidor.setMeterPosition(rs.getInt("METERPOSITION"));
    			medidor.setObs(rs.getString("OBS"));
    			medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
    			medidor.setEndereco(rs.getString("ENDERECO"));
    			medidor.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
        		medidor.setCompl(rs.getString("COMPL"));
        		medidor.setMunicipio(rs.getString("MUNICIPIO"));
        		medidor.setUf(rs.getString("UF"));
        		medidor.setCep(rs.getString("CEP"));
        		medidor.setCoordX(rs.getString("COORDX"));
        		medidor.setCoordY(rs.getString("COORDY"));
    			medidor.setSituacao(rs.getString("SITUACAO"));
    			medidor.setDtInsert(rs.getString("DTINSERT"));
    			
    		}
    		return medidor;
    	} 
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    
    public Long buscarIdMedidor(String deviceNum, int meterPosition) throws Exception {
    	
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	Long idMedidor = null;
    	
    	try {
    		stmt = connection.prepareStatement(
			"SELECT TB_MEDIDOR.ID_MEDIDOR " + 
			"FROM   TB_MEDIDOR " +
			"WHERE  TB_MEDIDOR.DEVICENUM = ? " +
			"AND    TB_MEDIDOR.SITUACAO = 'A' " +
			"AND    TB_MEDIDOR.METERPOSITION = ? "
    		);
    		
    		stmt.setObject(1, deviceNum);
    		stmt.setObject(2, meterPosition);
            
            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	idMedidor = rs.getLong("ID_MEDIDOR");
            }
            
            return idMedidor;
    	} 
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    
    public List<Medidor> listarTodos() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medidor> list = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(
            		"   SELECT TB_MEDIDOR.*, " +
            		"          TB_FABRICMEDIDOR.FABRICANTE " +
		            "     FROM TB_MEDIDOR " +
            		"LEFT JOIN TB_FABRICMEDIDOR " +
		            "       ON TB_FABRICMEDIDOR.ID_FABRICMEDIDOR = TB_MEDIDOR.ID_FABRICMEDIDOR "
		            );
            
            rs = stmt.executeQuery();

            while (rs.next()) {
            	Medidor medidor = new Medidor();
            	medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	medidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
        		medidor.setModelo(rs.getString("MODELO"));
        		medidor.setSerie(rs.getString("SERIE"));
        		medidor.setTipo(rs.getString("TIPO"));
        		medidor.setChaveDeCripto(rs.getString("CHAVEDECRIPTO"));
        		medidor.setValidBateria(rs.getInt("VALIDBATERIA"));
        		medidor.setNumeroMedidor(rs.getString("METERID"));
        		medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
        		medidor.setDeviceNum(rs.getString("DEVICENUM"));
        		medidor.setMeterPosition(rs.getInt("METERPOSITION"));
        		medidor.setObs(rs.getString("OBS"));
        		medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
        		medidor.setEndereco(rs.getString("ENDERECO"));
        		medidor.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
        		medidor.setCompl(rs.getString("COMPL"));
        		medidor.setMunicipio(rs.getString("MUNICIPIO"));
        		medidor.setUf(rs.getString("UF"));
        		medidor.setCep(rs.getString("CEP"));
        		medidor.setCoordX(rs.getString("COORDX"));
        		medidor.setCoordY(rs.getString("COORDY"));
        		medidor.setSituacao(rs.getString("SITUACAO"));
        		medidor.setDtInsert(rs.getString("DTINSERT"));
        		
        		FabricMedidor fabricMedidor = new FabricMedidor();
        		fabricMedidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
        		fabricMedidor.setFabricante(rs.getString("FABRICANTE"));
        		medidor.setFabricMedidor(fabricMedidor);
        		
                list.add(medidor);
            }
            return list;
        } 
        finally {
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
        }
    }
    
    public List<Medidor> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medidor> listMedidor = new ArrayList<Medidor>();
        
        try {
            
            stmt = connection.prepareStatement(
            "SELECT ID_MEDIDOR, " +
            "       ID_USER, " +
            "       ID_FABRICMEDIDOR, " +
            "       MODELO, " +
            "       SERIE, " +
            "       TIPO, " +
            "       CHAVEDECRIPTO, " +
            "       VALIDBATERIA, " +
            "       OBS, " +
            "       SITUACAO, " +
            "       DTINSERT, " +
            "       ID_BRIDGE, " +
            "       DEVICENUM, " +
            "       METERPOSITION, " +
            "       METERID, " +
            "       ID_CONDOMINIO, " +
            "       ENDERECO, " +
            "       NUMERO, " +
            "       COMPL, " +
            "       MUNICIPIO, " +
            "       UF, " +
            "       CEP, " +
            "       COORDX, " +
            "       COORDY " +
            "FROM   TB_MEDIDOR " +
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	FabricMedidorDAO fabricMedidorDAO = new FabricMedidorDAO(connection);
            	FabricMedidor fabricMedidor = fabricMedidorDAO.buscar(rs.getLong("ID_FABRICMEDIDOR"));
            	
            	Medidor medidor = new Medidor();
            	medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
            	medidor.setIdUser(rs.getLong("ID_USER"));
            	medidor.setDeviceNum(rs.getString("DEVICENUM"));
            	medidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
            	medidor.setFabricMedidor(fabricMedidor);
            	medidor.setModelo(rs.getString("MODELO"));
            	medidor.setSerie(rs.getString("SERIE"));
            	medidor.setTipo(rs.getString("TIPO"));
            	medidor.setChaveDeCripto(rs.getString("CHAVEDECRIPTO"));
            	medidor.setValidBateria(rs.getInt("VALIDBATERIA"));
            	medidor.setNumeroMedidor(rs.getString("METERID"));
            	medidor.setMeterPosition(rs.getInt("METERPOSITION"));
            	medidor.setEndereco(rs.getString("ENDERECO"));
            	medidor.setNumero(rs.getLong("NUMERO"));
            	medidor.setCompl(rs.getString("COMPL"));
            	medidor.setMunicipio(rs.getString("MUNICIPIO"));
            	medidor.setUf(rs.getString("UF"));
            	medidor.setCep(rs.getString("CEP"));
            	medidor.setCoordX(rs.getString("COORDX"));
            	medidor.setCoordY(rs.getString("COORDY"));
            	medidor.setObs(rs.getString("OBS"));
            	medidor.setSituacao(rs.getString("SITUACAO"));
            	medidor.setDtInsert(rs.getString("DTINSERT"));
            	
            	listMedidor.add(medidor);
            }
            
            return listMedidor;
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
    
    public List<Medidor> listarPorUsuario(Long idUser, Long idBridge) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medidor> listMedidor = new ArrayList<Medidor>();
        
        try {
            
            stmt = connection.prepareStatement(
            "SELECT    TB_MEDIDOR.ID_MEDIDOR, " +
            "          TB_MEDIDOR.ID_USER, " +
            "          TB_MEDIDOR.ID_FABRICMEDIDOR, " +
            "          TB_MEDIDOR.MODELO, " +
            "          TB_MEDIDOR.SERIE, " +
            "          TB_MEDIDOR.TIPO, " +
            "          TB_MEDIDOR.CHAVEDECRIPTO, " +
            "          TB_MEDIDOR.VALIDBATERIA, " +
            "          TB_MEDIDOR.OBS, " +
            "          TB_MEDIDOR.SITUACAO, " +
            "          TB_MEDIDOR.DTINSERT, " +
            "          TB_MEDIDOR.ID_BRIDGE, " +
            "          TB_MEDIDOR.DEVICENUM, " +
            "          TB_MEDIDOR.METERPOSITION, " +
            "          TB_MEDIDOR.METERID, " +
            "          TB_MEDIDOR.ID_CONDOMINIO, " +
            "          TB_MEDIDOR.ENDERECO, " +
            "          TB_MEDIDOR.NUMERO, " +
            "          TB_MEDIDOR.COMPL, " +
            "          TB_MEDIDOR.MUNICIPIO, " +
            "          TB_MEDIDOR.UF, " +
            "          TB_MEDIDOR.CEP, " +
            "          TB_MEDIDOR.COORDX, " +
            "          TB_MEDIDOR.COORDY " +
            "FROM      TB_MEDIDOR " +
            "LEFT JOIN VW_USERMEDIDORID " +
		    "ON        TB_MEDIDOR.ID_MEDIDOR = VW_USERMEDIDORID.ID_MEDIDOR " +
		    "WHERE     VW_USERMEDIDORID.ID_USER = ? " +
		    "AND       TB_MEDIDOR.ID_BRIDGE = ? "
            );

            stmt.setObject(1, idUser);
            stmt.setObject(2, idBridge);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	FabricMedidorDAO fabricMedidorDAO = new FabricMedidorDAO(connection);
            	FabricMedidor fabricMedidor = fabricMedidorDAO.buscar(rs.getLong("ID_FABRICMEDIDOR"));
            	
            	Medidor medidor = new Medidor();
            	medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
            	medidor.setIdUser(rs.getLong("ID_USER"));
            	medidor.setDeviceNum(rs.getString("DEVICENUM"));
            	medidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
            	medidor.setFabricMedidor(fabricMedidor);
            	medidor.setModelo(rs.getString("MODELO"));
            	medidor.setSerie(rs.getString("SERIE"));
            	medidor.setTipo(rs.getString("TIPO"));
            	medidor.setChaveDeCripto(rs.getString("CHAVEDECRIPTO"));
            	medidor.setValidBateria(rs.getInt("VALIDBATERIA"));
            	medidor.setNumeroMedidor(rs.getString("METERID"));
            	medidor.setMeterPosition(rs.getInt("METERPOSITION"));
            	medidor.setEndereco(rs.getString("ENDERECO"));
            	medidor.setNumero(rs.getLong("NUMERO"));
            	medidor.setCompl(rs.getString("COMPL"));
            	medidor.setMunicipio(rs.getString("MUNICIPIO"));
            	medidor.setUf(rs.getString("UF"));
            	medidor.setCep(rs.getString("CEP"));
            	medidor.setCoordX(rs.getString("COORDX"));
            	medidor.setCoordY(rs.getString("COORDY"));
            	medidor.setObs(rs.getString("OBS"));
            	medidor.setSituacao(rs.getString("SITUACAO"));
            	medidor.setDtInsert(rs.getString("DTINSERT"));
            	
            	listMedidor.add(medidor);
            }
            
            return listMedidor;
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

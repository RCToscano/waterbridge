package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
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
    				" INSERT INTO TB_MEDIDOR ( " +
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
					"DTINSERT " +
					")" +
    				" VALUES ( " +
            		"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            		"?, ?, ?, ?, sysdate() ) " 
    				);
            		
            stmt.setObject(1, medidor.getIdFabricMedidor());
            stmt.setObject(2, medidor.getModelo());
            stmt.setObject(3, medidor.getSerie());
            stmt.setObject(4, medidor.getTipo());
            stmt.setObject(5, medidor.getChaveDeCripto());
            stmt.setObject(6, medidor.getValidBateria());
            stmt.setObject(7, medidor.getObs());
            stmt.setObject(8, medidor.getSituacao());
            stmt.setObject(9, medidor.getNumero());
            stmt.setObject(10, medidor.getIdBridge());
            stmt.setObject(11, medidor.getDeviceNum());
            stmt.setObject(12, medidor.getMeterPosition());
            stmt.setObject(13, medidor.getIdUser());
            stmt.setObject(14, medidor.getIdCondominio());
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
    		stmt.setObject(9, medidor.getNumero());
    		stmt.setObject(10, medidor.getIdBridge());
    		stmt.setObject(11, medidor.getDeviceNum());
    		stmt.setObject(12, medidor.getMeterPosition());
    		stmt.setObject(13, medidor.getIdUser());
    		stmt.setObject(14, medidor.getIdCondominio());
    		stmt.setObject(15, medidor.getIdMedidor());
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
        		medidor.setNumero(rs.getString("METERID"));
        		medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
        		medidor.setDeviceNum(rs.getString("DEVICENUM"));
        		medidor.setMeterPosition(rs.getInt("METERPOSITION"));
        		medidor.setObs(rs.getString("OBS"));
        		medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
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
    			medidor.setNumero(rs.getString("METERID"));
    			medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
    			medidor.setDeviceNum(rs.getString("DEVICENUM"));
    			medidor.setMeterPosition(rs.getInt("METERPOSITION"));
    			medidor.setObs(rs.getString("OBS"));
    			medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
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
    
    public Medidor buscarPorFabricanteNumero(String fabricante, String numero) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT TB_MEDIDOR.* " +
					"  FROM TB_MEDIDOR " +
					" WHERE TB_MEDIDOR.FABRICANTE = ? " +
					"   AND TB_MEDIDOR.METERID = ? "
    				);
    		
    		stmt.setObject(1, fabricante);
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
    			medidor.setNumero(rs.getString("METERID"));
    			medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
    			medidor.setDeviceNum(rs.getString("DEVICENUM"));
    			medidor.setMeterPosition(rs.getInt("METERPOSITION"));
    			medidor.setObs(rs.getString("OBS"));
    			medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
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
    
    public List<Medidor> listarTodos() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medidor> list = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(
            		"SELECT TB_MEDIDOR.* " +
		            "  FROM TB_MEDIDOR "
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
        		medidor.setNumero(rs.getString("METERID"));
        		medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
        		medidor.setDeviceNum(rs.getString("DEVICENUM"));
        		medidor.setMeterPosition(rs.getInt("METERPOSITION"));
        		medidor.setObs(rs.getString("OBS"));
        		medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
        		medidor.setSituacao(rs.getString("SITUACAO"));
        		medidor.setDtInsert(rs.getString("DTINSERT"));
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

}

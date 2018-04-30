package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
					"FABRICANTE, " +
					"MODELO, " +
					"SERIE, " +
					"TIPO, " +
					"CHAVEDECRIPTO, " +
					"VALIDBATERIA, " +
					"OBS, " +
					"SITUACAO, " +
					"DTINSERT " +
					")" +
    				" VALUES ( " +
            		"?, ?, ?, ?, ?, ?, ?, ?, sysdate() ) " 
    				);
            		
            stmt.setObject(1, medidor.getFabricante());
            stmt.setObject(2, medidor.getModelo());
            stmt.setObject(3, medidor.getSerie());
            stmt.setObject(4, medidor.getTipo());
            stmt.setObject(5, medidor.getChaveDeCripto());
            stmt.setObject(6, medidor.getValidBateria());
            stmt.setObject(7, medidor.getObs());
            stmt.setObject(8, "1");
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
    		stmt = connection.prepareStatement(
    				" UPDATE TB_MEDIDOR SET " +
					"FABRICANTE = ?, " +
					"MODELO = ?, " +
					"SERIE = ?, " +
					"TIPO = ?, " +
					"CHAVEDECRIPTO = ?, " +
					"VALIDBATERIA = ?, " +
					"OBS = ?, " +
					"SITUACAO = ? " +
					"DTINSERT = sysdate() " +
					"WHERE ID_MEDIDOR = ? "
    				);
    		
    		stmt.setObject(1, medidor.getFabricante());
    		stmt.setObject(2, medidor.getModelo());
    		stmt.setObject(3, medidor.getSerie());
    		stmt.setObject(4, medidor.getTipo());
    		stmt.setObject(5, medidor.getChaveDeCripto());
    		stmt.setObject(6, medidor.getValidBateria());
    		stmt.setObject(7, medidor.getObs());
    		stmt.setObject(8, "1");
    		stmt.setObject(9, medidor.getIdMedidor());
    		stmt.executeUpdate();
    	} 
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }

}

package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.waterbridge.modelo.Pass;

public class PassDAO {
	
	private Connection connection;

    public PassDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void inserir(Pass pass) throws Exception {
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	stmt = connection.prepareStatement(
    				" INSERT INTO TB_PASS ( " +
            		" PASS, " + 
            		" ID_USER " + 
            		")" +
    				" VALUES ( " +
            		" ?, ? ) " 
    				);
            		
            stmt.setObject(1, pass.getPass());
            stmt.setObject(2, pass.getIdUser());
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

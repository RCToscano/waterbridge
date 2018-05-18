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
    
    public void alterar(Pass pass) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		
    		//LOGA REGISTRO ANTES DE ALTERAR
            logar(pass.getIdUser());
    		
    		stmt = connection.prepareStatement(
    				" UPDATE TB_PASS SET " +
					" PASS = ? " + 
					" WHERE ID_USER = ? " 
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
    
    public void logar(Long idUser) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
            "INSERT INTO TB_PASSLOG " +
            "SELECT * " +
            "FROM   TB_PASS " +
            "WHERE  ID_USER = ? "
            );

            stmt.setLong(1, idUser);
            stmt.executeUpdate();
        }
        finally {
            if(stmt != null)
                stmt.close();
        }
    }
    
    public Pass buscarSenha(Long idUser) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		stmt = connection.prepareStatement(
					"SELECT TB_PASS.* " +
					"  FROM TB_PASS " +
					" WHERE ID_USER = ? "
    		);
    		
    		stmt.setObject(1, idUser);
    		rs = stmt.executeQuery();
    		
    		Pass pass = new Pass();
            if(rs.next()) {
            	pass.setIdUser(idUser);
            	pass.setIdPass(rs.getLong("ID_PASS"));
            	pass.setPass(rs.getString("PASS"));
            }
            return pass;
    	}
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }

}

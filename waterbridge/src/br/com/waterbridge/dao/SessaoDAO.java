package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.waterbridge.modelo.Sessao;

public class SessaoDAO {
    
    Connection connection = null;

    public SessaoDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(Long idUser) throws SQLException {
        
        PreparedStatement stmt = null;
                
        try {
            stmt = connection.prepareStatement(
            "INSERT INTO TB_SESSAO ( " +
			"       ID_USER, " +
			"       DT_LOGIN " +
            ") VALUES ( " +
            "       ?,sysdate() " +
            ") ");
            
            stmt.setObject(1, idUser);
            stmt.execute();
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    
    public void alterar(Long idSessao) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		
    		stmt = connection.prepareStatement(
    					" UPDATE TB_SESSAO SET " +
						"        DT_LOGOUT = sysdate() " +
						"  WHERE ID_SESSAO = ? "
    				);
    		
    		stmt.setObject(1, idSessao);
    		stmt.executeUpdate();
    	} 
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    
    public Sessao buscarUltimo() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(
            "SELECT    MAX(TB_SESSAO.ID_SESSAO) AS ID_SESSAO " +
            "FROM      TB_SESSAO "
            );
            rs = stmt.executeQuery();

            Sessao sessao = new Sessao();
            if(rs.next()) {
            	sessao.setIdSessao(rs.getLong("ID_SESSAO"));
            }
            return sessao;
        }
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public Sessao buscarPorId(long idSessao) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT * FROM TB_SESSAO WHERE ID_SESSAO =  ?"
    				);
    		stmt.setLong(1, idSessao);
    		rs = stmt.executeQuery();
    		
    		Sessao sessao = new Sessao();
    		if(rs.next()) {
    			sessao.setIdSessao(rs.getLong("ID_SESSAO"));
    			sessao.setDtLogin(rs.getDate("DT_LOGIN"));
    			sessao.setDtLogout(rs.getDate("DT_LOGOUT"));
    			sessao.setIdUser(rs.getLong("ID_USER"));
    		}
    		return sessao;
    	}
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    
}



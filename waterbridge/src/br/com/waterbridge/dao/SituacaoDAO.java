
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.modelo.Situacao;

public class SituacaoDAO {
    
    Connection connection = null;

    public SituacaoDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public Situacao buscar(Long idSituacao) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Situacao situacao = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "SELECT ID_SITUACAO, " +
            " 		SITUACAO, " +
            "		DESCRICAO, " +
            "		DESCRICAO " +
            "FROM   TB_SITUACAO " +
            "WHERE  ID_SITUACAO = ? "
            );

            stmt.setLong(1, idSituacao);
            
            rs = stmt.executeQuery();

            if(rs.next()) {

            	situacao = new Situacao();
            	situacao.setIdSituacao(rs.getLong("ID_SITUACAO"));
            	situacao.setSituacao(rs.getString("SITUACAO"));
            	situacao.setDescricao(rs.getString("DESCRICAO"));
            }
            
            return situacao;
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
    
    public List<Situacao> listar() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Situacao> list = new ArrayList<Situacao>();
        
        try {
            
            stmt = connection.prepareStatement(
			"SELECT   ID_SITUACAO, " +
	        " 		  SITUACAO, " +
	        "		  DESCRICAO, " +
	        "	   	  DESCRICAO " +
	        "FROM     TB_SITUACAO " +
	        "ORDER BY ID_SITUACAO "
            );

            rs = stmt.executeQuery();

            while(rs.next()) {

            	Situacao situacao = new Situacao();
            	situacao.setIdSituacao(rs.getLong("ID_SITUACAO"));
            	situacao.setIdSituacao(rs.getLong("ID_SITUACAO"));
            	situacao.setSituacao(rs.getString("SITUACAO"));
            	situacao.setDescricao(rs.getString("DESCRICAO"));
            	
            	list.add(situacao);
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

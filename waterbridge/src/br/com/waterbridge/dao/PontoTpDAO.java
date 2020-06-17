
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.modelo.PontoTp;

public class PontoTpDAO {
    
    private Connection connection;

    public PontoTpDAO(Connection connection) {
        
        this.connection = connection;
    }
  
    public List<PontoTp> listar() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PontoTp> listPontoTp = new ArrayList<PontoTp>();
        
        try {
            
            stmt = connection.prepareStatement(
			"SELECT   ID_PONTOTP, " +
		    "         DESCRICAO " +
            "FROM     TB_PONTOTP " +
            "ORDER BY DESCRICAO " 
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	PontoTp pontoTp = new PontoTp();
            	pontoTp.setIdPontoTp(rs.getLong("ID_PONTOTP"));
            	pontoTp.setDescricao(rs.getString("DESCRICAO"));
            	
            	listPontoTp.add(pontoTp);
            }
            
            return listPontoTp;
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

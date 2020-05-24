package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.relmodelo.RelPonto;

public class RelPontoDAO {
    
    private Connection connection;

    public RelPontoDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelPonto> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelPonto> listRelPonto = new ArrayList<RelPonto>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_PONTO, " +
        	"       ID_EMPRESA, " +
        	"       ID_PONTOTP, " +
        	"       DESCRICAO_PONTO, " +
        	"       DESCRICAO_PONTOTP, " +
        	" 	    COORDX, " +
        	"       COORDY, " +
        	"       DTINSERT " +
        	"FROM   VW_PONTO " +            		            
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	RelPonto relponto = new RelPonto();
            	relponto.setIdPonto(rs.getLong("ID_PONTO"));
            	relponto.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relponto.setIdPontoTp(rs.getLong("ID_PONTOTP"));
            	relponto.setDescricaoPonto(rs.getString("DESCRICAO_PONTO"));
            	relponto.setDescricaoPontoTp(rs.getString("DESCRICAO_PONTOTP"));
            	relponto.setCoordX(rs.getString("COORDX"));
            	relponto.setCoordY(rs.getString("COORDY"));
            	relponto.setDtInsert(rs.getString("DTINSERT"));
                        	
            	listRelPonto.add(relponto);
            }
            
            return listRelPonto;
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


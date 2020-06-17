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
            	
            	RelPonto relPonto = new RelPonto();
            	relPonto.setIdPonto(rs.getLong("ID_PONTO"));
            	relPonto.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relPonto.setIdPontoTp(rs.getLong("ID_PONTOTP"));
            	relPonto.setDescricaoPonto(rs.getString("DESCRICAO_PONTO"));
            	relPonto.setDescricaoPontoTp(rs.getString("DESCRICAO_PONTOTP"));
            	relPonto.setCoordX(rs.getString("COORDX"));
            	relPonto.setCoordY(rs.getString("COORDY"));
            	relPonto.setDtInsert(rs.getString("DTINSERT"));
                        	
            	listRelPonto.add(relPonto);
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

    public RelPonto buscarPorId(Long idPonto) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        RelPonto relPonto = null;
        
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
            "WHERE  ID_PONTO = " + idPonto
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	relPonto = new RelPonto();
            	relPonto.setIdPonto(rs.getLong("ID_PONTO"));
            	relPonto.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relPonto.setIdPontoTp(rs.getLong("ID_PONTOTP"));
            	relPonto.setDescricaoPonto(rs.getString("DESCRICAO_PONTO"));
            	relPonto.setDescricaoPontoTp(rs.getString("DESCRICAO_PONTOTP"));
            	relPonto.setCoordX(rs.getString("COORDX"));
            	relPonto.setCoordY(rs.getString("COORDY"));
            	relPonto.setDtInsert(rs.getString("DTINSERT"));
            }
            
            return relPonto;
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


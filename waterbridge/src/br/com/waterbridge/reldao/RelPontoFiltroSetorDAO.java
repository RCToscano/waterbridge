
package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.relmodelo.RelPontoFiltroSetor;

public class RelPontoFiltroSetorDAO {
    
    private Connection connection;

    public RelPontoFiltroSetorDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelPontoFiltroSetor> listar(List<Long> listIdEmpresa) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelPontoFiltroSetor> listRelPontoFiltroSetor = new ArrayList<RelPontoFiltroSetor>();
        
        try {
            
        	String sql = 
			"SELECT    TB_EMPRESA.ID_EMPRESA, " +
            "          TB_EMPRESA.NOME, " +
            "          TB_CONDOMINIO.ID_CONDOMINIO, " +
            "          TB_CONDOMINIO.COMPL " +
            "FROM      TB_EMPRESA " +
            "LEFT JOIN TB_CONDOMINIO " +
            "ON        TB_EMPRESA.ID_EMPRESA = TB_CONDOMINIO.ID_EMPRESA " ;
        	if(listIdEmpresa.size() > 0) {
        		sql += "WHERE     TB_EMPRESA.ID_EMPRESA IN(" + listIdEmpresa.toString().replace("[", "").replace("]", "") + ") " ;
        	}
        	else {
        		sql += "WHERE     TB_EMPRESA.ID_EMPRESA IN(0) " ;
        	}        	
        	sql +=
            "AND       TB_EMPRESA.SITUACAO = 'A' " +
            "AND       TB_CONDOMINIO.SITUACAO = 'A' " +
            "AND       TB_CONDOMINIO.COMPL IS NOT NULL " +
            "AND       TB_CONDOMINIO.COMPL <> '' " +
            "GROUP BY  TB_EMPRESA.ID_EMPRESA, " +
            "          TB_EMPRESA.NOME, " +
            "          TB_CONDOMINIO.COMPL " +
            "ORDER BY  TB_EMPRESA.NOME, " +
            "          TB_CONDOMINIO.COMPL " ;		
        	
            stmt = connection.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	RelPontoFiltroSetor relPontoFiltroSetor = new RelPontoFiltroSetor();
            	relPontoFiltroSetor.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relPontoFiltroSetor.setNome(rs.getString("NOME"));
            	relPontoFiltroSetor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relPontoFiltroSetor.setCompl(rs.getString("COMPL"));
                        	
            	listRelPontoFiltroSetor.add(relPontoFiltroSetor);
            }
            
            return listRelPontoFiltroSetor;
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


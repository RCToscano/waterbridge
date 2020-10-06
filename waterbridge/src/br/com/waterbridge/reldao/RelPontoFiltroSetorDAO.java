
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

    public List<RelPontoFiltroSetor> listar() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelPontoFiltroSetor> listRelPontoFiltroSetor = new ArrayList<RelPontoFiltroSetor>();
        
        try {
            
            stmt = connection.prepareStatement(
            "SELECT    TB_EMPRESA.ID_EMPRESA, " +
            "          TB_EMPRESA.NOME, " +
            "          TB_CONDOMINIO.ID_CONDOMINIO, " +
            "          TB_CONDOMINIO.COMPL " +
            "FROM      TB_EMPRESA " +
            "LEFT JOIN TB_CONDOMINIO " +
            "ON        TB_EMPRESA.ID_EMPRESA = TB_CONDOMINIO.ID_EMPRESA " +
            "WHERE     TB_EMPRESA.ID_EMPRESA IN(4, 6, 7) " +
            "AND       TB_EMPRESA.SITUACAO = 'A' " +
            "AND       TB_CONDOMINIO.SITUACAO = 'A' " +
            "AND       TB_CONDOMINIO.COMPL IS NOT NULL " +
            "AND       TB_CONDOMINIO.COMPL <> '' " +
            "GROUP BY  TB_EMPRESA.ID_EMPRESA, " +
            "          TB_EMPRESA.NOME, " +
            "          TB_CONDOMINIO.COMPL " +
            "ORDER BY  TB_EMPRESA.NOME, " +
            "          TB_CONDOMINIO.COMPL "
            );

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



package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.relmodelo.RelUserCondominio;

public class RelUserCondominioDAO {
    
    private Connection connection;

    public RelUserCondominioDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelUserCondominio> listar(Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelUserCondominio> listRelUserCondominio = new ArrayList<RelUserCondominio>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT   ID_USERCONDOMINIO, " +
    		"		  ID_INSERT, " +
    		"		  NOMEINSERT, " +
    		"		  ID_USER, " +
    		"		  NOMEUSER, " +
    		"		  CPFUSER, " +
    		"		  ID_CONDOMINIO, " +
    		"		  OBS, " +
    		"		  SITUACAO, " +
    		"		  DTINICIO, " +
    		"		  DTFIM " +
        	"FROM     VW_USERCONDOMINIO " +
        	"WHERE    ID_CONDOMINIO = ? " +
        	"ORDER BY DTINICIO DESC "
            );
            
            stmt.setObject(1, idCondominio);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	RelUserCondominio relUserCondominio = new RelUserCondominio();
            	relUserCondominio.setIdUserCondominio(rs.getLong("ID_USERCONDOMINIO"));
            	relUserCondominio.setIdInsert(rs.getLong("ID_INSERT"));
            	relUserCondominio.setNomeInsert(rs.getString("NOMEINSERT"));
            	relUserCondominio.setIdUser(rs.getLong("ID_USER"));
            	relUserCondominio.setNomeUser(rs.getString("NOMEUSER"));
            	relUserCondominio.setCpfUser(rs.getString("CPFUSER"));
            	relUserCondominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relUserCondominio.setObs(rs.getString("OBS"));
            	relUserCondominio.setSituacao(rs.getString("SITUACAO"));
            	relUserCondominio.setDtInicio(Auxiliar.formataDtTelaHr(rs.getString("DTINICIO")));
            	relUserCondominio.setDtFim(Auxiliar.formataDtTelaHr(rs.getString("DTFIM")));
            	
            	listRelUserCondominio.add(relUserCondominio);
            }
            
            return listRelUserCondominio;
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
    
    public List<RelUserCondominio> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelUserCondominio> listRelUserCondominio = new ArrayList<RelUserCondominio>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT   ID_USERCONDOMINIO, " +
    		"		  ID_INSERT, " +
    		"		  NOMEINSERT, " +
    		"		  ID_USER, " +
    		"		  NOMEUSER, " +
    		"		  CPFUSER, " +
    		"		  ID_CONDOMINIO, " +
    		"		  OBS, " +
    		"		  SITUACAO, " +
    		"		  DTINICIO, " +
    		"		  DTFIM " +
        	"FROM     VW_USERCONDOMINIO " +
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	RelUserCondominio relUserCondominio = new RelUserCondominio();
            	relUserCondominio.setIdUserCondominio(rs.getLong("ID_USERCONDOMINIO"));
            	relUserCondominio.setIdInsert(rs.getLong("ID_INSERT"));
            	relUserCondominio.setNomeInsert(rs.getString("NOMEINSERT"));
            	relUserCondominio.setIdUser(rs.getLong("ID_USER"));
            	relUserCondominio.setNomeUser(rs.getString("NOMEUSER"));
            	relUserCondominio.setCpfUser(rs.getString("CPFUSER"));
            	relUserCondominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relUserCondominio.setObs(rs.getString("OBS"));
            	relUserCondominio.setSituacao(rs.getString("SITUACAO"));
            	relUserCondominio.setDtInicio(Auxiliar.formataDtTelaHr(rs.getString("DTINICIO")));
            	relUserCondominio.setDtFim(Auxiliar.formataDtTelaHr(rs.getString("DTFIM")));
            	
            	listRelUserCondominio.add(relUserCondominio);
            }
            
            return listRelUserCondominio;
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

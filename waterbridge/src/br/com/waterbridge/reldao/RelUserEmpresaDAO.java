
package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.relmodelo.RelUserEmpresa;
import br.com.waterbridge.relmodelo.RelUserMedidor;

public class RelUserEmpresaDAO {
    
    private Connection connection;

    public RelUserEmpresaDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelUserEmpresa> listar(Long idEmpresa) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelUserEmpresa> listRelUserEmpresa = new ArrayList<RelUserEmpresa>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT   ID_USEREMPRESA, " +
    		"		  ID_INSERT, " +
    		"		  NOMEINSERT, " +
    		"		  ID_USER, " +
    		"		  NOMEUSER, " +
    		"		  CPFUSER, " +
    		"		  ID_EMPRESA, " +
    		"		  CNP, " +
    		"         OBS, " +
    		"		  SITUACAO, " +
    		"		  DTINICIO, " +
    		"		  DTFIM " +
        	"FROM     VW_USEREMPRESA " +
        	"WHERE    ID_EMPRESA = ? " +
        	"ORDER BY DTINICIO DESC "
            );
            
            stmt.setObject(1, idEmpresa);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	RelUserEmpresa relUserEmpresa = new RelUserEmpresa();
            	relUserEmpresa.setIdUserEmpresa(rs.getLong("ID_USEREMPRESA"));
            	relUserEmpresa.setIdInsert(rs.getLong("ID_INSERT"));
            	relUserEmpresa.setNomeInsert(rs.getString("NOMEINSERT"));
            	relUserEmpresa.setIdUser(rs.getLong("ID_USER"));
            	relUserEmpresa.setNomeUser(rs.getString("NOMEUSER"));
            	relUserEmpresa.setCpfUser(rs.getString("CPFUSER"));
            	relUserEmpresa.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relUserEmpresa.setCnp(rs.getLong("CNP"));
            	relUserEmpresa.setObs(rs.getString("OBS"));
            	relUserEmpresa.setSituacao(rs.getString("SITUACAO"));
            	relUserEmpresa.setDtInicio(Auxiliar.formataDtTelaHr(rs.getString("DTINICIO")));
            	relUserEmpresa.setDtFim(Auxiliar.formataDtTelaHr(rs.getString("DTFIM")));
            	
            	listRelUserEmpresa.add(relUserEmpresa);
            }
            
            return listRelUserEmpresa;
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
    
    public List<RelUserEmpresa> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelUserEmpresa> listRelUserEmpresa = new ArrayList<RelUserEmpresa>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT   ID_USEREMPRESA, " +
    		"		  ID_INSERT, " +
    		"		  NOMEINSERT, " +
    		"		  ID_USER, " +
    		"		  NOMEUSER, " +
    		"		  CPFUSER, " +
    		"		  ID_EMPRESA, " +
    		"		  CNP, " +
    		"         OBS, " +
    		"		  SITUACAO, " +
    		"		  DTINICIO, " +
    		"		  DTFIM " +
        	"FROM     VW_USEREMPRESA " +
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	RelUserEmpresa relUserEmpresa = new RelUserEmpresa();
            	relUserEmpresa.setIdUserEmpresa(rs.getLong("ID_USEREMPRESA"));
            	relUserEmpresa.setIdInsert(rs.getLong("ID_INSERT"));
            	relUserEmpresa.setNomeInsert(rs.getString("NOMEINSERT"));
            	relUserEmpresa.setIdUser(rs.getLong("ID_USER"));
            	relUserEmpresa.setNomeUser(rs.getString("NOMEUSER"));
            	relUserEmpresa.setCpfUser(rs.getString("CPFUSER"));
            	relUserEmpresa.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relUserEmpresa.setCnp(rs.getLong("CNP"));
            	relUserEmpresa.setObs(rs.getString("OBS"));
            	relUserEmpresa.setSituacao(rs.getString("SITUACAO"));
            	relUserEmpresa.setDtInicio(Auxiliar.formataDtTelaHr(rs.getString("DTINICIO")));
            	relUserEmpresa.setDtFim(Auxiliar.formataDtTelaHr(rs.getString("DTFIM")));
            	
            	listRelUserEmpresa.add(relUserEmpresa);
            }
            
            return listRelUserEmpresa;
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

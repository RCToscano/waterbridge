
package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.relmodelo.RelUserMedidor;

public class RelUserMedidorDAO {
    
    private Connection connection;

    public RelUserMedidorDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelUserMedidor> listar(Long idMedidor) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelUserMedidor> listRelUserMedidor = new ArrayList<RelUserMedidor>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT   ID_USERMEDIDOR, " +
        	"         ID_INSERT, " +
        	"         NOMEINSERT, " +
        	"         ID_USER, " +
        	"         NOMEUSER, " +
        	"         CPFUSER, " +
        	"         ID_MEDIDOR, " +
        	"         METERID, " +
        	"         OBS, " +
        	"         SITUACAO, " +
        	"         DTINICIO, " +
        	"         DTFIM " +
        	"FROM     VW_USERMEDIDOR " +
        	"WHERE    ID_MEDIDOR = ? " +
        	"ORDER BY DTINICIO DESC "
            );
            
            stmt.setObject(1, idMedidor);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	RelUserMedidor relUserMedidor = new RelUserMedidor();
            	relUserMedidor.setIdUserMedidor(rs.getLong("ID_USERMEDIDOR"));
            	relUserMedidor.setIdInsert(rs.getLong("ID_INSERT"));
            	relUserMedidor.setNomeInsert(rs.getString("NOMEINSERT"));
            	relUserMedidor.setIdUser(rs.getLong("ID_USER"));
            	relUserMedidor.setNomeUser(rs.getString("NOMEUSER"));
            	relUserMedidor.setCpfUser(rs.getString("CPFUSER"));
            	relUserMedidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	relUserMedidor.setMeterId(rs.getLong("METERID"));
            	relUserMedidor.setObs(rs.getString("OBS"));
            	relUserMedidor.setSituacao(rs.getString("SITUACAO"));
            	relUserMedidor.setDtInicio(Auxiliar.formataDtTelaHr(rs.getString("DTINICIO")));
            	relUserMedidor.setDtFim(Auxiliar.formataDtTelaHr(rs.getString("DTFIM")));
            	
            	listRelUserMedidor.add(relUserMedidor);
            }
            
            return listRelUserMedidor;
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
    
    public List<RelUserMedidor> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelUserMedidor> listRelUserMedidor = new ArrayList<RelUserMedidor>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_USERMEDIDOR, " +
        	"       ID_INSERT, " +
        	"       NOMEINSERT, " +
        	"       ID_USER, " +
        	"       NOMEUSER, " +
        	"       CPFUSER, " +
        	"       ID_MEDIDOR, " +
        	"       METERID, " +
        	"       OBS, " +
        	"       SITUACAO, " +
        	"       DTINICIO, " +
        	"       DTFIM " +
        	"FROM   VW_USERMEDIDOR " +
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	RelUserMedidor relUserMedidor = new RelUserMedidor();
            	relUserMedidor.setIdUserMedidor(rs.getLong("ID_USERMEDIDOR"));
            	relUserMedidor.setIdInsert(rs.getLong("ID_INSERT"));
            	relUserMedidor.setNomeInsert(rs.getString("NOMEINSERT"));
            	relUserMedidor.setIdUser(rs.getLong("ID_USER"));
            	relUserMedidor.setNomeUser(rs.getString("NOMEUSER"));
            	relUserMedidor.setCpfUser(rs.getString("CPFUSER"));
            	relUserMedidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	relUserMedidor.setMeterId(rs.getLong("METERID"));
            	relUserMedidor.setObs(rs.getString("OBS"));
            	relUserMedidor.setSituacao(rs.getString("SITUACAO"));
            	relUserMedidor.setDtInicio(Auxiliar.formataDtTelaHr(rs.getString("DTINICIO")));
            	relUserMedidor.setDtFim(Auxiliar.formataDtTelaHr(rs.getString("DTFIM")));
            	
            	listRelUserMedidor.add(relUserMedidor);
            }
            
            return listRelUserMedidor;
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

package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.relmodelo.RelConta;

public class RelContaDAO {
    
    Connection connection = null;

    public RelContaDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public List<RelConta> listarPorIdCondominio(Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelConta> list = new ArrayList<RelConta>();
        
        try {
            
        	stmt = connection.prepareStatement(
    		"SELECT ID_CONTA, " +
        	"       ID_EMPRESA, " +
        	"       ID_CONDOMINIO, " +
        	"       ID_USER, " +
        	"	    DTLEITURAATUAL, " +
        	"       DTLEITURAANTERIOR, " +
        	"       VALOR, " +
        	"       CONSUMO, " +
        	"       OBS, " +
        	"       DTINSERT, " +
        	"       ( SELECT COUNT(*) " +
		    "         FROM  TB_CONTARATEIO " + 
		    "         WHERE TB_CONTARATEIO.ID_CONTA = TB_CONTA.ID_CONTA " +
			"       ) RATEIO " +
        	"FROM   TB_CONTA " +	
            "WHERE  ID_CONDOMINIO = ? "
            );

        	stmt.setLong(1, idCondominio);
        	
            rs = stmt.executeQuery();

            while(rs.next()) {

            	RelConta relConta = new RelConta();
            	relConta.setIdConta(rs.getLong("ID_CONTA"));
            	relConta.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relConta.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relConta.setIdUser(rs.getLong("ID_USER"));
            	relConta.setDtLeituraAtual(Auxiliar.formataDtTela(rs.getString("DTLEITURAATUAL")));
            	relConta.setDtLeituraAnterior(Auxiliar.formataDtTela(rs.getString("DTLEITURAANTERIOR")));
            	relConta.setValor(rs.getDouble("VALOR"));
            	relConta.setConsumo(rs.getDouble("CONSUMO"));
            	relConta.setObs(rs.getString("OBS"));
            	relConta.setRateio(rs.getLong("RATEIO"));
            	relConta.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	
            	list.add(relConta);
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
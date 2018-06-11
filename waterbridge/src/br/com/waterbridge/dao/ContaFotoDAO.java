
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.ContaFoto;

public class ContaFotoDAO {
    
    Connection connection = null;

    public ContaFotoDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(ContaFoto conta) throws Exception {
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	stmt = connection.prepareStatement(
        			"INSERT INTO TB_CONTAFOTO ( " +
        			"ID_CONTA, " +
        			"ID_USER, " +
        			"DIR, " +
        			"NOME, " +
        			"DTINSERT " +
        			")" +
					" VALUES ( " +
            		"?, ?, ?, ?, sysdate() ) " 
    				);
            		
            stmt.setObject(1, conta.getIdConta());
            stmt.setObject(2, conta.getIdUser());
            stmt.setObject(3, conta.getDiretorio());
            stmt.setObject(4, conta.getNome());
            stmt.executeUpdate();
		} 
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public List<ContaFoto> listarPorConta(Long idConta) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	List<ContaFoto> list = new ArrayList<>();
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT ID_CONTA, " +
    	        	"       ID_USER, " +
    	        	"       DIR, " +
    	        	"       NOME, " +
    	        	"       DTINSERT " +
					"  FROM TB_CONTAFOTO " +	
					" WHERE ID_CONTA = ? " 
    				);
    		
    		stmt.setLong(1, idConta);
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
    			ContaFoto contaFoto = new ContaFoto();
    			contaFoto.setIdConta(rs.getLong("ID_CONTA"));
    			contaFoto.setDiretorio(rs.getString("DIR"));
    			contaFoto.setNome(rs.getString("NOME"));
    			contaFoto.setIdUser(rs.getLong("ID_USER"));
    			contaFoto.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
    			list.add(contaFoto);
    		}
    		return list;
    	}
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    

}
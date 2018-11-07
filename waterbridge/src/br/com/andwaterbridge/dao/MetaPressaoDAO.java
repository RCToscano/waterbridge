
package br.com.andwaterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.andwaterbridge.modelo.MetaPressao;
import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.connection.ConnectionFactory;

public class MetaPressaoDAO {
    
    private Connection connection;

    public MetaPressaoDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(MetaPressao metaPressao) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_METAPRESSAO ( " +
            //"		ID_METAPRESSAO, " +
            "		ID_USER, " +
            "		ID_EMPRESA, " +
            "		ID_CONDOMINIO, " +
            "		ID_BRIDGE, " +
            "		ID_MEDIDOR, " +
            "		METERPOSITION, " +
            "		PRESSAOMIN, " +
            "		PRESSAOMAX, " +
            "		DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,?,?,?,?,?,SYSDATE() " +
            ")");
            
            //stmt.setObject(1, metaPressao.getIdMetaPressao());
    		stmt.setObject(1, metaPressao.getIdUser());
    		stmt.setObject(2, metaPressao.getIdEmpresa());
    		stmt.setObject(3, metaPressao.getIdCondominio());
			stmt.setObject(4, metaPressao.getIdBridge());
			stmt.setObject(5, metaPressao.getIdMedidor());
			stmt.setObject(6, metaPressao.getMeterPosition());
			stmt.setObject(7, metaPressao.getPressaoMin());
			stmt.setObject(8, metaPressao.getPressaoMax());
			//stmt.setObject(1, metaPressao.getDtInsert());
            
            stmt.execute();
        }
        catch(SQLException e) {
            
            throw e;
        }
        finally {
            
            if(stmt != null) {
                
                stmt.close();
            }
        }
    }
    
    public void alterar(MetaPressao metaPressao) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(metaPressao.getIdMetaPressao());
        
            stmt = connection.prepareStatement(
            "UPDATE TB_METAPRESSAO SET " +
            "		ID_USER = ?, " +
            "       ID_EMPRESA = ?, " +
            "       ID_CONDOMINIO = ?, " +
            "		ID_BRIDGE = ?, " +
            "		ID_MEDIDOR = ?, " +
            "		METERPOSITION = ?, " +
            "		PRESSAOMIN = ?, " +
            "		PRESSAOMAX = ?, " +
            "		DTINSERT = SYSDATE() " +
    		"WHERE  ID_METAPRESSAO = ? ");
            
    		stmt.setObject(1, metaPressao.getIdUser());    		
    		stmt.setObject(2, metaPressao.getIdEmpresa());
    		stmt.setObject(3, metaPressao.getIdCondominio());    		
			stmt.setObject(4, metaPressao.getIdBridge());
			stmt.setObject(5, metaPressao.getIdMedidor());
			stmt.setObject(6, metaPressao.getMeterPosition());
			stmt.setObject(7, metaPressao.getPressaoMin());
			stmt.setObject(8, metaPressao.getPressaoMax());
			//stmt.setObject(1, metaPressao.getDtInsert());
			stmt.setObject(9, metaPressao.getIdMetaPressao());

            stmt.execute();
        }
        catch(SQLException e) {
            
            throw e;
        }
        finally {
            
            if(stmt != null) {
                
                stmt.close();
            }
        }
    }    

    public void logar(Long idMetaPressao) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "INSERT INTO TB_METAPRESSAOLOG " +
            "SELECT * " +
            "FROM   TB_METAPRESSAO " +
            "WHERE  ID_METAPRESSAO = ? "
            );

            stmt.setLong(1, idMetaPressao);
            
            stmt.executeUpdate();
        }
        catch(SQLException e) {
            
            throw e;
        }
        finally {

            if(stmt != null) {
                
                stmt.close();
            }
        }
    }
    
    public MetaPressao buscarPorIdMedidor(Long idMedidor) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        MetaPressao metaPressao = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_METAPRESSAO, " +
    		"		ID_USER, " +
    		"       ID_EMPRESA, " +
    		"       ID_CONDOMINIO, " +	
    		"		ID_BRIDGE, " +
    		"		ID_MEDIDOR, " +
    		"		METERPOSITION, " +
    		"		PRESSAOMIN, " +
    		"		PRESSAOMAX, " +
    		"		DTINSERT " +
        	"FROM   TB_METAPRESSAO " +
        	"WHERE  ID_MEDIDOR = ? "
            );

            stmt.setLong(1, idMedidor);

            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	metaPressao = new MetaPressao();
            	metaPressao.setIdMetaPressao(rs.getLong("ID_METAPRESSAO"));
            	metaPressao.setIdUser(rs.getLong("ID_USER"));            	
            	metaPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	metaPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));            	
            	metaPressao.setIdBridge(rs.getLong("ID_BRIDGE"));
            	metaPressao.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	metaPressao.setMeterPosition(rs.getLong("METERPOSITION"));
            	metaPressao.setPressaoMin(rs.getDouble("PRESSAOMIN"));
            	metaPressao.setPressaoMax(rs.getDouble("PRESSAOMAX"));
            	metaPressao.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            }
            
            return metaPressao;
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
    
    public MetaPressao buscarPorIdBridge(Long idBridge) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        MetaPressao metaPressao = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_METAPRESSAO, " +
    		"		ID_USER, " +
    		"       ID_EMPRESA, " +
    		"       ID_CONDOMINIO, " +	
    		"		ID_BRIDGE, " +
    		"		ID_MEDIDOR, " +
    		"		METERPOSITION, " +
    		"		PRESSAOMIN, " +
    		"		PRESSAOMAX, " +
    		"		DTINSERT " +
        	"FROM   TB_METAPRESSAO " +
        	"WHERE  ID_BRIDGE = ? "
            );

            stmt.setLong(1, idBridge);

            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	metaPressao = new MetaPressao();
            	metaPressao.setIdMetaPressao(rs.getLong("ID_METAPRESSAO"));
            	metaPressao.setIdUser(rs.getLong("ID_USER"));            	
            	metaPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	metaPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));            	
            	metaPressao.setIdBridge(rs.getLong("ID_BRIDGE"));
            	metaPressao.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	metaPressao.setMeterPosition(rs.getLong("METERPOSITION"));
            	metaPressao.setPressaoMin(rs.getDouble("PRESSAOMIN"));
            	metaPressao.setPressaoMax(rs.getDouble("PRESSAOMAX"));
            	metaPressao.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            }
            
            return metaPressao;
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
    
    public static void main(String[] args) throws SQLException {
		
    	Connection connection = ConnectionFactory.getConnection();
    	
    	MetaPressao metaPressao = new MetaPressao();
    	metaPressao.setIdMetaPressao(1l);;
    	metaPressao.setIdUser(2L);;
    	metaPressao.setIdEmpresa(2L);;
    	metaPressao.setIdCondominio(8L);;
    	metaPressao.setIdBridge(16L);;
    	metaPressao.setIdMedidor(10L);;
    	metaPressao.setMeterPosition(1L);;
    	metaPressao.setPressaoMin(30.0);;
    	metaPressao.setPressaoMax(40.0);;
    	metaPressao.setDtInsert(null);;
    	
    	MetaPressaoDAO metaPressaoDAO = new MetaPressaoDAO(connection);
    	metaPressaoDAO.alterar(metaPressao);
    	
    	metaPressao = metaPressaoDAO.buscarPorIdMedidor(10l);
    	
    	System.out.println("metapressao " + metaPressao);
    	
    	connection.close();
    	
    	System.out.println("fim");
	}
}

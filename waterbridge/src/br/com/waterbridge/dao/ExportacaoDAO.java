
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.modelo.Exportacao;

public class ExportacaoDAO {
    
    Connection connection = null;

    public ExportacaoDAO(Connection connection) {
        
        this.connection = connection;
    }
        
    public List<Exportacao> listarParaExportacao() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Exportacao> list = new ArrayList<>();
        
        try {
            
        	stmt = connection.prepareStatement(
    		"SELECT    TB_EXPORTACAO.ID_CONSUMO, " +
    		"		   TB_EXPORTACAO.ID_EMPRESA, " +
    		"		   TB_EXPORTACAO.ID_CONDOMINIO, " +
    		"		   TB_EXPORTACAO.ID_BRIDGE, " +
    		"		   TB_EXPORTACAO.DEVICE, " +
    		"		   TB_EXPORTACAO.PRESSURE, " +
    		"		   TB_EXPORTACAO.FLOW, " +
    		"		   TB_EXPORTACAO.ENDERECO, " +
    		"		   TB_EXPORTACAO.NUMERO, " +
    		"		   TB_EXPORTACAO.COMPL, " +
    		"		   TB_EXPORTACAO.COORDX, " +
    		"		   TB_EXPORTACAO.COORDY, " +
    		"		   TB_EXPORTACAO.DTINSERT, " +
    		"		   TB_EXPORTACAO.ID_ARQUIVO, " +
    		"          TB_EMPRESA.NOME EMPRESA " +
    		"FROM      TB_EXPORTACAO " +
    		"LEFT JOIN TB_EMPRESA " +
    		"ON        TB_EXPORTACAO.ID_EMPRESA = TB_EMPRESA.ID_EMPRESA " +
            "WHERE     TB_EXPORTACAO.ID_ARQUIVO IS NULL "
            );

            rs = stmt.executeQuery();

            while(rs.next()) {

            	Exportacao exportacao = new Exportacao();
            	exportacao.setIdConsumo(rs.getLong("ID_CONSUMO"));
            	exportacao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	exportacao.setEmpresa(rs.getString("EMPRESA"));
            	exportacao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	exportacao.setIdBridge(rs.getLong("ID_BRIDGE"));
            	exportacao.setDevice(rs.getString("DEVICE"));
            	exportacao.setPressure(rs.getDouble("PRESSURE"));
            	exportacao.setFlow(rs.getDouble("FLOW"));
            	exportacao.setEndereco(rs.getString("ENDERECO"));
            	exportacao.setNumero(rs.getLong("NUMERO"));
            	exportacao.setCompl(rs.getString("COMPL"));
            	exportacao.setCoordX(rs.getString("COORDX"));
            	exportacao.setCoordY(rs.getString("COORDY"));
            	exportacao.setDtInsert(rs.getString("DTINSERT"));
            	exportacao.setIdArquivo(rs.getLong("ID_ARQUIVO"));
            	
            	list.add(exportacao);
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
    
    public void atualizarIdArquivo(Long idArquivo) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "UPDATE TB_EXPORTACAO SET " +
    		"       ID_ARQUIVO = ? " +
    		"WHERE  ID_ARQUIVO IS NULL ");
            
            stmt.setObject(1, idArquivo);
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
}
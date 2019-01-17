
package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.relmodelo.RelPressaoLast;

public class RelPressaoLastDAO {
    
    private Connection connection;

    public RelPressaoLastDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelPressaoLast> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelPressaoLast> listRelCondominio = new ArrayList<RelPressaoLast>();
        
        try {
            
            stmt = connection.prepareStatement(
			"SELECT    VW_PRESSAOLAST.ID_EMPRESA, " +
			"          VW_PRESSAOLAST.EMPRESA, " +
			"          VW_PRESSAOLAST.ID_CONDOMINIO, " +
			"          VW_PRESSAOLAST.CONDOMINIO, " +
			"          VW_PRESSAOLAST.ID_BRIDGE, " +
			"          VW_PRESSAOLAST.DEVICENUM, " +
			"          VW_PRESSAOLAST.ID_CONSUMO, " +
			"          VW_PRESSAOLAST.PRESSURE, " +
			"          VW_PRESSAOLAST.PRESSAOMINBAIXA, " +
			"          VW_PRESSAOLAST.PRESSAOMIN, " +
			"          VW_PRESSAOLAST.PRESSAOMAX, " +
			"          VW_PRESSAOLAST.PRESSAOMAXALTA, " +
			"          VW_PRESSAOLAST.DTINSERT " +
			"FROM      VW_PRESSAOLAST " +		
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	RelPressaoLast relPressaoLast = new RelPressaoLast();
            	relPressaoLast.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relPressaoLast.setEmpresa(rs.getString("EMPRESA"));
            	relPressaoLast.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relPressaoLast.setCondominio(rs.getString("CONDOMINIO"));	
            	relPressaoLast.setIdBridge(rs.getLong("ID_BRIDGE"));
            	relPressaoLast.setDeviceNum(rs.getString("DEVICENUM"));	
            	relPressaoLast.setIdConsumo(rs.getLong("ID_CONSUMO"));
            	relPressaoLast.setPressao(rs.getDouble("PRESSURE"));
            	relPressaoLast.setPressaoMinBaixa(rs.getDouble("PRESSAOMINBAIXA"));
            	relPressaoLast.setPressaoMin(rs.getDouble("PRESSAOMIN"));
            	relPressaoLast.setPressaoMax(rs.getDouble("PRESSAOMAX"));
            	relPressaoLast.setPressaoMaxAlta(rs.getDouble("PRESSAOMAXALTA"));	
            	relPressaoLast.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	
            	listRelCondominio.add(relPressaoLast);
            }
            
            return listRelCondominio;
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


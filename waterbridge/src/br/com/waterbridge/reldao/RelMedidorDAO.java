
package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.relmodelo.RelMedidor;
import br.com.waterbridge.relmodelo.RelUserMedidor;

public class RelMedidorDAO {
    
    private Connection connection;

    public RelMedidorDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelMedidor> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelMedidor> listRelMedidor = new ArrayList<RelMedidor>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_EMPRESA, " +
            "       EMPRESA, " +
    		"       ID_CONDOMINIO, " +
        	"       CONDOMINIO, " +
        	"       ID_BRIDGE, " +
        	"       DEVICENUM, " +
        	"       ID_BRIDGETPALIM, " +
        	"       BRIDGETPALIM, " +
        	"       ID_BRIDGETP, " +
        	"       BRIDGETP, " +
        	"       ID_MEDIDOR, " +
        	"       ID_FABRICMEDIDOR, " +
        	"       FABRICANTE, " +
        	"       MODELO, " +
        	"       SERIE, " +
        	"       TIPO, " +
        	"       CHAVEDECRIPTO, " +
        	"       VALIDBATERIA, " +
        	"       OBS, " +
        	"       SITUACAO, " +
        	"       DTINSERT, " +
        	"       METERPOSITION, " +
        	"       METERID, " +
        	"       ENDERECO, " +
        	"       NUMERO, " +
        	"       COMPL, " +
        	"       MUNICIPIO, " +
        	"       UF, " +
        	"       CEP, " +
        	"       COORDX, " +
        	"       COORDY " +
        	"FROM   VW_MEDIDOR " +
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	List<RelUserMedidor> listRelUserMedidor = new RelUserMedidorDAO(connection).listar(rs.getLong("ID_MEDIDOR"));
            	
            	RelMedidor relMedidor = new RelMedidor();
            	relMedidor.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relMedidor.setEmpresa(rs.getString("EMPRESA"));
            	relMedidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relMedidor.setCondominio(rs.getString("CONDOMINIO"));
            	relMedidor.setIdBridge(rs.getLong("ID_BRIDGE"));
            	relMedidor.setDeviceNum(rs.getString("DEVICENUM"));
            	relMedidor.setIdBridgeTpAlim(rs.getLong("ID_BRIDGETPALIM"));
            	relMedidor.setBridgeTpAlim(rs.getString("BRIDGETPALIM"));
            	relMedidor.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
            	relMedidor.setBridgeTp(rs.getString("BRIDGETP"));
            	relMedidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	relMedidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
            	relMedidor.setFabricante(rs.getString("FABRICANTE"));
            	relMedidor.setModelo(rs.getString("MODELO"));
            	relMedidor.setSerie(rs.getString("SERIE"));
            	relMedidor.setTipo(rs.getString("TIPO"));
            	relMedidor.setChaveDecripto(rs.getString("CHAVEDECRIPTO"));
            	relMedidor.setValidBateria(rs.getLong("VALIDBATERIA"));
            	relMedidor.setObs(rs.getString("OBS"));
            	relMedidor.setSituacao(rs.getString("SITUACAO"));
            	relMedidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	relMedidor.setMeterPosition(rs.getLong("METERPOSITION"));
            	relMedidor.setMeterId(rs.getString("METERID"));
            	relMedidor.setEndereco(rs.getString("ENDERECO"));
            	relMedidor.setNumero(rs.getLong("NUMERO"));
            	relMedidor.setCompl(rs.getString("COMPL"));
            	relMedidor.setMunicipio(rs.getString("MUNICIPIO"));
            	relMedidor.setUf(rs.getString("UF"));
            	relMedidor.setCep(rs.getString("CEP"));
            	relMedidor.setCoordX(rs.getString("COORDX"));
            	relMedidor.setCoordY(rs.getString("COORDY"));
            	relMedidor.setListRelUserMedidor(listRelUserMedidor);
            	
            	listRelMedidor.add(relMedidor);
            }
            
            return listRelMedidor;
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

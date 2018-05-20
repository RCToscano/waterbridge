
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
        List<RelMedidor> listRelUsuarioMedidor = new ArrayList<RelMedidor>();
        
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
            	
            	RelMedidor relUsuarioMedidor = new RelMedidor();
            	relUsuarioMedidor.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relUsuarioMedidor.setEmpresa(rs.getString("EMPRESA"));
            	relUsuarioMedidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relUsuarioMedidor.setCondominio(rs.getString("CONDOMINIO"));
            	relUsuarioMedidor.setIdBridge(rs.getLong("ID_BRIDGE"));
            	relUsuarioMedidor.setDeviceNum(rs.getString("DEVICENUM"));
            	relUsuarioMedidor.setIdBridgeTpAlim(rs.getLong("ID_BRIDGETPALIM"));
            	relUsuarioMedidor.setBridgeTpAlim(rs.getString("BRIDGETPALIM"));
            	relUsuarioMedidor.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
            	relUsuarioMedidor.setBridgeTp(rs.getString("BRIDGETP"));
            	relUsuarioMedidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	relUsuarioMedidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
            	relUsuarioMedidor.setFabricante(rs.getString("FABRICANTE"));
            	relUsuarioMedidor.setModelo(rs.getString("MODELO"));
            	relUsuarioMedidor.setSerie(rs.getString("SERIE"));
            	relUsuarioMedidor.setTipo(rs.getString("TIPO"));
            	relUsuarioMedidor.setChaveDecripto(rs.getString("CHAVEDECRIPTO"));
            	relUsuarioMedidor.setValidBateria(rs.getLong("VALIDBATERIA"));
            	relUsuarioMedidor.setObs(rs.getString("OBS"));
            	relUsuarioMedidor.setSituacao(rs.getString("SITUACAO"));
            	relUsuarioMedidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	relUsuarioMedidor.setMeterPosition(rs.getLong("METERPOSITION"));
            	relUsuarioMedidor.setMeterId(rs.getString("METERID"));
            	relUsuarioMedidor.setEndereco(rs.getString("ENDERECO"));
            	relUsuarioMedidor.setNumero(rs.getLong("NUMERO"));
            	relUsuarioMedidor.setCompl(rs.getString("COMPL"));
            	relUsuarioMedidor.setMunicipio(rs.getString("MUNICIPIO"));
            	relUsuarioMedidor.setUf(rs.getString("UF"));
            	relUsuarioMedidor.setCep(rs.getString("CEP"));
            	relUsuarioMedidor.setCoordX(rs.getString("COORDX"));
            	relUsuarioMedidor.setCoordY(rs.getString("COORDY"));
            	relUsuarioMedidor.setListRelUserMedidor(listRelUserMedidor);
            	
            	listRelUsuarioMedidor.add(relUsuarioMedidor);
            }
            
            return listRelUsuarioMedidor;
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

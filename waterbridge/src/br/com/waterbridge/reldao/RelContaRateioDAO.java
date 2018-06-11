
package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.relmodelo.RelContaRateio;
import br.com.waterbridge.relmodelo.RelUserMedidor;

public class RelContaRateioDAO {
    
    private Connection connection;

    public RelContaRateioDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelContaRateio> listarPorIdConta(Long idConta) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelContaRateio> listRelContaRateio = new ArrayList<RelContaRateio>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT   ID_CONTARATEIO, " +
    		"		  ID_CONTA, " +
    		"		  DTLEITURAATUAL, " +
    		"		  DTLEITURAANTERIOR, " +
    		"		  CONTAVALOR, " +
    		"		  CONTACONSUMO, " +
    		"		  ID_EMPRESA, " +
    		"		  EMPRESA, " +
    		"		  ID_CONDOMINIO, " +
    		"		  CONDOMINIO, " +
    		"		  ID_MEDIDOR, " +
    		"		  METERID, " +
    		"		  ENDERECO, " +
    		"		  NUMERO, " +
    		"		  COMPL, " +
    		"		  MUNICIPIO, " +
    		"		  UF, " +
    		"		  CEP, " +
    		"		  COORDX, " +
    		"		  COORDY, " +
    		"		  ID_USER, " +
    		"		  VOLUMEINICIAL, " +
    		"		  VOLUMEFINAL, " +
    		"		  CONSUMOREAL, " +
    		"		  CONSUMORATEIO, " +
    		"		  VALORRATEIO, " +
    		"		  PERCRATEIO, " +
    		"		  OBS, " +
    		"		  DTINSERT " +
        	"FROM     VW_CONTARATEIO " +		
            "WHERE    ID_CONTA = ? " +
        	"ORDER BY ENDERECO, " +
    		"		  NUMERO, " +
    		"		  COMPL, " +
    		"		  METERID "
            );
            
            stmt.setLong(1, idConta);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	List<RelUserMedidor> listRelUserMedidor = new RelUserMedidorDAO(connection).listar(rs.getLong("ID_MEDIDOR"));
            	
            	RelContaRateio relContaRateio = new RelContaRateio();
            	relContaRateio.setIdContaRateio(rs.getLong("ID_CONTARATEIO"));
            	relContaRateio.setIdConta(rs.getLong("ID_CONTA"));
            	relContaRateio.setDtLeituraAtual(Auxiliar.formataDtTela(rs.getString("DTLEITURAATUAL")));
            	relContaRateio.setDtLeituraAnterior(Auxiliar.formataDtTela(rs.getString("DTLEITURAANTERIOR")));
            	relContaRateio.setContaValor(rs.getDouble("CONTAVALOR"));
            	relContaRateio.setContaConsumo(rs.getDouble("CONTACONSUMO"));
            	relContaRateio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relContaRateio.setEmpresa(rs.getString("EMPRESA"));
            	relContaRateio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relContaRateio.setCondominio(rs.getString("CONDOMINIO"));
            	relContaRateio.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	relContaRateio.setNumeroMedidor(rs.getString("METERID"));
            	relContaRateio.setEndereco(rs.getString("ENDERECO"));
            	relContaRateio.setNumero(rs.getLong("NUMERO"));
            	relContaRateio.setCompl(rs.getString("COMPL"));
            	relContaRateio.setMunicipio(rs.getString("MUNICIPIO"));
            	relContaRateio.setUf(rs.getString("UF"));
            	relContaRateio.setCep(rs.getString("CEP"));
            	relContaRateio.setCoordX(rs.getString("COORDX"));
            	relContaRateio.setCoordY(rs.getString("COORDY"));
            	relContaRateio.setIdUser(rs.getLong("ID_USER"));
            	relContaRateio.setVolumeInicial(rs.getDouble("VOLUMEINICIAL"));
            	relContaRateio.setVolumeFinal(rs.getDouble("VOLUMEFINAL"));
            	relContaRateio.setConsumoReal(rs.getDouble("CONSUMOREAL"));
            	relContaRateio.setConsumoRateio(rs.getDouble("CONSUMORATEIO"));
            	relContaRateio.setValorRateio(rs.getDouble("VALORRATEIO"));
            	relContaRateio.setPercRateio(rs.getDouble("PERCRATEIO"));
            	relContaRateio.setObs(rs.getString("OBS"));
            	relContaRateio.setDtInsert(Auxiliar.formataDtBancoHr(rs.getString("DTINSERT")));
            	relContaRateio.setListRelUserMedidor(listRelUserMedidor);
            	
            	listRelContaRateio.add(relContaRateio);
            }
            
            return listRelContaRateio;
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



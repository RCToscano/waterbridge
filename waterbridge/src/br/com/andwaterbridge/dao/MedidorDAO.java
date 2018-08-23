
package br.com.andwaterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.andwaterbridge.modelo.Medidor;
import br.com.waterbridge.auxiliar.Auxiliar;

public class MedidorDAO {
    
    private Connection connection;

    public MedidorDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public Medidor buscarPorId(Long idMedidor) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Medidor medidor = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT   VW_MEDIDORAPP.ID_EMPRESA, " +
        	"         VW_MEDIDORAPP.EMPRESA, " +
        	"         VW_MEDIDORAPP.ID_CONDOMINIO, " +
        	"         VW_MEDIDORAPP.CONDOMINIO, " +
        	"         VW_MEDIDORAPP.ENDERECOCOND, " +
        	"         VW_MEDIDORAPP.NUMEROCOND, " +
        	"         VW_MEDIDORAPP.COMPLCOND, " +
        	"         VW_MEDIDORAPP.MUNICIPIOCOND, " +
        	"         VW_MEDIDORAPP.UFCOND, " +
        	"         VW_MEDIDORAPP.CEPCOND, " +
        	"         VW_MEDIDORAPP.COORDXCOND, " +
        	"         VW_MEDIDORAPP.COORDYCOND, " +
        	"         VW_MEDIDORAPP.ID_BRIDGE, " +
        	"         VW_MEDIDORAPP.ID_BRIDGETP, " +
        	"         VW_MEDIDORAPP.BRIDGETP, " +
        	"         VW_MEDIDORAPP.DEVICENUM, " +
        	"         VW_MEDIDORAPP.ID_MEDIDOR, " +
        	"         VW_MEDIDORAPP.METERID, " +
        	"         VW_MEDIDORAPP.METERPOSITION, " +
        	"         VW_MEDIDORAPP.ENDERECOMED, " +
        	"         VW_MEDIDORAPP.NUMEROMED, " +
        	"         VW_MEDIDORAPP.COMPLMED, " +
        	"         VW_MEDIDORAPP.MUNICIPIOMED, " +
        	"         VW_MEDIDORAPP.UFMED, " +
        	"         VW_MEDIDORAPP.CEPMED, " +
        	"         VW_MEDIDORAPP.COORDXMED, " +
        	"         VW_MEDIDORAPP.COORDYMED, " +
        	"         VW_MEDIDORAPP.DTINSERT " +
        	"FROM     VW_MEDIDORAPP  " +	
        	"WHERE    VW_MEDIDORAPP.ID_MEDIDOR = ? "	
            );

            stmt.setObject(1, idMedidor);
            
            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	medidor = new Medidor();
            	medidor.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	medidor.setEmpresa(rs.getString("EMPRESA"));
            	medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	medidor.setCondominio(Auxiliar.removerCaracteres(rs.getString("CONDOMINIO")));
            	medidor.setEnderecoCond(Auxiliar.removerCaracteres(rs.getString("ENDERECOCOND")));
            	medidor.setNumeroCond(rs.getLong("NUMEROCOND"));
            	medidor.setComplCond(Auxiliar.removerCaracteres(rs.getString("COMPLCOND")));
            	medidor.setMunicipioCond(Auxiliar.removerCaracteres(rs.getString("MUNICIPIOCOND")));
            	medidor.setUfCond(rs.getString("UFCOND"));
            	medidor.setCepCond(rs.getString("CEPCOND"));
            	medidor.setCoordXCond(rs.getString("COORDXCOND"));
            	medidor.setCoordYCond(rs.getString("COORDYCOND"));
            	medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
            	medidor.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
            	medidor.setBridgeTp(rs.getString("BRIDGETP"));
            	medidor.setDeviceNum(rs.getString("DEVICENUM"));
            	medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	medidor.setNumeroMedidor(rs.getString("METERID"));
            	medidor.setMeterPosition(rs.getInt("METERPOSITION"));
            	medidor.setEnderecoMed(Auxiliar.removerCaracteres(rs.getString("ENDERECOMED")));
            	medidor.setNumeroMed(rs.getLong("NUMEROMED"));
            	medidor.setComplMed(Auxiliar.removerCaracteres(rs.getString("COMPLMED")));
            	medidor.setMunicipioMed(Auxiliar.removerCaracteres(rs.getString("MUNICIPIOMED")));
            	medidor.setUfMed(rs.getString("UFMED"));
            	medidor.setCepMed(rs.getString("CEPMED"));
            	medidor.setCoordXMed(rs.getString("COORDXMED"));
            	medidor.setCoordYMed(rs.getString("COORDYMED"));    
            	medidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            }
            
            return medidor;
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
    
    public List<Medidor> listarPerfilProgramador() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medidor> listMedidor = new ArrayList<Medidor>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT   VW_MEDIDORAPP.ID_EMPRESA, " +
        	"         VW_MEDIDORAPP.EMPRESA, " +
        	"         VW_MEDIDORAPP.ID_CONDOMINIO, " +
        	"         VW_MEDIDORAPP.CONDOMINIO, " +
        	"         VW_MEDIDORAPP.ENDERECOCOND, " +
        	"         VW_MEDIDORAPP.NUMEROCOND, " +
        	"         VW_MEDIDORAPP.COMPLCOND, " +
        	"         VW_MEDIDORAPP.MUNICIPIOCOND, " +
        	"         VW_MEDIDORAPP.UFCOND, " +
        	"         VW_MEDIDORAPP.CEPCOND, " +
        	"         VW_MEDIDORAPP.COORDXCOND, " +
        	"         VW_MEDIDORAPP.COORDYCOND, " +
        	"         VW_MEDIDORAPP.ID_BRIDGE, " +
        	"         VW_MEDIDORAPP.ID_BRIDGETP, " +
        	"         VW_MEDIDORAPP.BRIDGETP, " +
        	"         VW_MEDIDORAPP.DEVICENUM, " +
        	"         VW_MEDIDORAPP.ID_MEDIDOR, " +
        	"         VW_MEDIDORAPP.METERID, " +
        	"         VW_MEDIDORAPP.METERPOSITION, " +
        	"         VW_MEDIDORAPP.ENDERECOMED, " +
        	"         VW_MEDIDORAPP.NUMEROMED, " +
        	"         VW_MEDIDORAPP.COMPLMED, " +
        	"         VW_MEDIDORAPP.MUNICIPIOMED, " +
        	"         VW_MEDIDORAPP.UFMED, " +
        	"         VW_MEDIDORAPP.CEPMED, " +
        	"         VW_MEDIDORAPP.COORDXMED, " +
        	"         VW_MEDIDORAPP.COORDYMED, " +
        	"         VW_MEDIDORAPP.DTINSERT " +
        	"FROM     VW_MEDIDORAPP  " +	
	    	"ORDER BY VW_MEDIDORAPP.CONDOMINIO, " +
	    	"         VW_MEDIDORAPP.ENDERECOMED, " + 
	    	"         VW_MEDIDORAPP.NUMEROMED, " +
	    	"         VW_MEDIDORAPP.COMPLMED "   	
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Medidor medidor = new Medidor();
            	medidor.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	medidor.setEmpresa(rs.getString("EMPRESA"));
            	medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	medidor.setCondominio(Auxiliar.removerCaracteres(rs.getString("CONDOMINIO")));
            	medidor.setEnderecoCond(Auxiliar.removerCaracteres(rs.getString("ENDERECOCOND")));
            	medidor.setNumeroCond(rs.getLong("NUMEROCOND"));
            	medidor.setComplCond(Auxiliar.removerCaracteres(rs.getString("COMPLCOND")));
            	medidor.setMunicipioCond(Auxiliar.removerCaracteres(rs.getString("MUNICIPIOCOND")));
            	medidor.setUfCond(rs.getString("UFCOND"));
            	medidor.setCepCond(rs.getString("CEPCOND"));
            	medidor.setCoordXCond(rs.getString("COORDXCOND"));
            	medidor.setCoordYCond(rs.getString("COORDYCOND"));
            	medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
            	medidor.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
            	medidor.setBridgeTp(rs.getString("BRIDGETP"));
            	medidor.setDeviceNum(rs.getString("DEVICENUM"));
            	medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	medidor.setNumeroMedidor(rs.getString("METERID"));
            	medidor.setMeterPosition(rs.getInt("METERPOSITION"));
            	medidor.setEnderecoMed(Auxiliar.removerCaracteres(rs.getString("ENDERECOMED")));
            	medidor.setNumeroMed(rs.getLong("NUMEROMED"));
            	medidor.setComplMed(Auxiliar.removerCaracteres(rs.getString("COMPLMED")));
            	medidor.setMunicipioMed(Auxiliar.removerCaracteres(rs.getString("MUNICIPIOMED")));
            	medidor.setUfMed(rs.getString("UFMED"));
            	medidor.setCepMed(rs.getString("CEPMED"));
            	medidor.setCoordXMed(rs.getString("COORDXMED"));
            	medidor.setCoordYMed(rs.getString("COORDYMED"));    
            	medidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listMedidor.add(medidor);
            }
            
            return listMedidor;
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
    
    public List<Medidor> listarPerfilGerencial() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medidor> listMedidor = new ArrayList<Medidor>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT   VW_MEDIDORAPP.ID_EMPRESA, " +
        	"         VW_MEDIDORAPP.EMPRESA, " +
        	"         VW_MEDIDORAPP.ID_CONDOMINIO, " +
        	"         VW_MEDIDORAPP.CONDOMINIO, " +
        	"         VW_MEDIDORAPP.ENDERECOCOND, " +
        	"         VW_MEDIDORAPP.NUMEROCOND, " +
        	"         VW_MEDIDORAPP.COMPLCOND, " +
        	"         VW_MEDIDORAPP.MUNICIPIOCOND, " +
        	"         VW_MEDIDORAPP.UFCOND, " +
        	"         VW_MEDIDORAPP.CEPCOND, " +
        	"         VW_MEDIDORAPP.COORDXCOND, " +
        	"         VW_MEDIDORAPP.COORDYCOND, " +
        	"         VW_MEDIDORAPP.ID_BRIDGE, " +
        	"         VW_MEDIDORAPP.ID_BRIDGETP, " +
        	"         VW_MEDIDORAPP.BRIDGETP, " +
        	"         VW_MEDIDORAPP.DEVICENUM, " +
        	"         VW_MEDIDORAPP.ID_MEDIDOR, " +
        	"         VW_MEDIDORAPP.METERID, " +
        	"         VW_MEDIDORAPP.METERPOSITION, " +
        	"         VW_MEDIDORAPP.ENDERECOMED, " +
        	"         VW_MEDIDORAPP.NUMEROMED, " +
        	"         VW_MEDIDORAPP.COMPLMED, " +
        	"         VW_MEDIDORAPP.MUNICIPIOMED, " +
        	"         VW_MEDIDORAPP.UFMED, " +
        	"         VW_MEDIDORAPP.CEPMED, " +
        	"         VW_MEDIDORAPP.COORDXMED, " +
        	"         VW_MEDIDORAPP.COORDYMED, " +
        	"         VW_MEDIDORAPP.DTINSERT " +
        	"FROM     VW_MEDIDORAPP  " +	
	    	"ORDER BY VW_MEDIDORAPP.CONDOMINIO, " +
	    	"         VW_MEDIDORAPP.ENDERECOMED, " + 
	    	"         VW_MEDIDORAPP.NUMEROMED, " +
	    	"         VW_MEDIDORAPP.COMPLMED "   	
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Medidor medidor = new Medidor();
            	medidor.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	medidor.setEmpresa(rs.getString("EMPRESA"));
            	medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	medidor.setCondominio(Auxiliar.removerCaracteres(rs.getString("CONDOMINIO")));
            	medidor.setEnderecoCond(Auxiliar.removerCaracteres(rs.getString("ENDERECOCOND")));
            	medidor.setNumeroCond(rs.getLong("NUMEROCOND"));
            	medidor.setComplCond(Auxiliar.removerCaracteres(rs.getString("COMPLCOND")));
            	medidor.setMunicipioCond(Auxiliar.removerCaracteres(rs.getString("MUNICIPIOCOND")));
            	medidor.setUfCond(rs.getString("UFCOND"));
            	medidor.setCepCond(rs.getString("CEPCOND"));
            	medidor.setCoordXCond(rs.getString("COORDXCOND"));
            	medidor.setCoordYCond(rs.getString("COORDYCOND"));
            	medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
            	medidor.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
            	medidor.setBridgeTp(rs.getString("BRIDGETP"));
            	medidor.setDeviceNum(rs.getString("DEVICENUM"));
            	medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	medidor.setNumeroMedidor(rs.getString("METERID"));
            	medidor.setMeterPosition(rs.getInt("METERPOSITION"));
            	medidor.setEnderecoMed(Auxiliar.removerCaracteres(rs.getString("ENDERECOMED")));
            	medidor.setNumeroMed(rs.getLong("NUMEROMED"));
            	medidor.setComplMed(Auxiliar.removerCaracteres(rs.getString("COMPLMED")));
            	medidor.setMunicipioMed(Auxiliar.removerCaracteres(rs.getString("MUNICIPIOMED")));
            	medidor.setUfMed(rs.getString("UFMED"));
            	medidor.setCepMed(rs.getString("CEPMED"));
            	medidor.setCoordXMed(rs.getString("COORDXMED"));
            	medidor.setCoordYMed(rs.getString("COORDYMED"));    
            	medidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listMedidor.add(medidor);
            }
            
            return listMedidor;
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
    
    public List<Medidor> listarPerfilRepresentante(String idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medidor> listMedidor = new ArrayList<Medidor>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    VW_MEDIDORAPP.ID_EMPRESA, " +
        	"          VW_MEDIDORAPP.EMPRESA, " +
        	"          VW_MEDIDORAPP.ID_CONDOMINIO, " +
        	"          VW_MEDIDORAPP.CONDOMINIO, " +
        	"          VW_MEDIDORAPP.ENDERECOCOND, " +
        	"          VW_MEDIDORAPP.NUMEROCOND, " +
        	"          VW_MEDIDORAPP.COMPLCOND, " +
        	"          VW_MEDIDORAPP.MUNICIPIOCOND, " +
        	"          VW_MEDIDORAPP.UFCOND, " +
        	"          VW_MEDIDORAPP.CEPCOND, " +
        	"          VW_MEDIDORAPP.COORDXCOND, " +
        	"          VW_MEDIDORAPP.COORDYCOND, " +
        	"          VW_MEDIDORAPP.ID_BRIDGE, " +
        	"          VW_MEDIDORAPP.ID_BRIDGETP, " +
        	"          VW_MEDIDORAPP.BRIDGETP, " +
        	"          VW_MEDIDORAPP.DEVICENUM, " +
        	"          VW_MEDIDORAPP.ID_MEDIDOR, " +
        	"          VW_MEDIDORAPP.METERID, " +
        	"          VW_MEDIDORAPP.METERPOSITION, " +
        	"          VW_MEDIDORAPP.ENDERECOMED, " +
        	"          VW_MEDIDORAPP.NUMEROMED, " +
        	"          VW_MEDIDORAPP.COMPLMED, " +
        	"          VW_MEDIDORAPP.MUNICIPIOMED, " +
        	"          VW_MEDIDORAPP.UFMED, " +
        	"          VW_MEDIDORAPP.CEPMED, " +
        	"          VW_MEDIDORAPP.COORDXMED, " +
        	"          VW_MEDIDORAPP.COORDYMED, " +
        	"          VW_MEDIDORAPP.DTINSERT " +
        	"FROM      VW_MEDIDORAPP " +	
        	"LEFT JOIN TB_USEREMPRESA " +
        	"ON        VW_MEDIDORAPP.ID_EMPRESA = TB_USEREMPRESA.ID_EMPRESA " +
        	"WHERE     TB_USEREMPRESA.ID_USER = ? " +
        	"AND       TB_USEREMPRESA.SITUACAO = 'A' " +
	    	"ORDER BY  VW_MEDIDORAPP.CONDOMINIO, " +
	    	"          VW_MEDIDORAPP.ENDERECOMED, " + 
	    	"          VW_MEDIDORAPP.NUMEROMED, " +
	    	"          VW_MEDIDORAPP.COMPLMED "   	
            );
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Medidor medidor = new Medidor();
            	medidor.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	medidor.setEmpresa(rs.getString("EMPRESA"));
            	medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	medidor.setCondominio(Auxiliar.removerCaracteres(rs.getString("CONDOMINIO")));
            	medidor.setEnderecoCond(Auxiliar.removerCaracteres(rs.getString("ENDERECOCOND")));
            	medidor.setNumeroCond(rs.getLong("NUMEROCOND"));
            	medidor.setComplCond(Auxiliar.removerCaracteres(rs.getString("COMPLCOND")));
            	medidor.setMunicipioCond(Auxiliar.removerCaracteres(rs.getString("MUNICIPIOCOND")));
            	medidor.setUfCond(rs.getString("UFCOND"));
            	medidor.setCepCond(rs.getString("CEPCOND"));
            	medidor.setCoordXCond(rs.getString("COORDXCOND"));
            	medidor.setCoordYCond(rs.getString("COORDYCOND"));
            	medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
            	medidor.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
            	medidor.setBridgeTp(rs.getString("BRIDGETP"));
            	medidor.setDeviceNum(rs.getString("DEVICENUM"));
            	medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	medidor.setNumeroMedidor(rs.getString("METERID"));
            	medidor.setMeterPosition(rs.getInt("METERPOSITION"));
            	medidor.setEnderecoMed(Auxiliar.removerCaracteres(rs.getString("ENDERECOMED")));
            	medidor.setNumeroMed(rs.getLong("NUMEROMED"));
            	medidor.setComplMed(Auxiliar.removerCaracteres(rs.getString("COMPLMED")));
            	medidor.setMunicipioMed(Auxiliar.removerCaracteres(rs.getString("MUNICIPIOMED")));
            	medidor.setUfMed(rs.getString("UFMED"));
            	medidor.setCepMed(rs.getString("CEPMED"));
            	medidor.setCoordXMed(rs.getString("COORDXMED"));
            	medidor.setCoordYMed(rs.getString("COORDYMED"));    
            	medidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listMedidor.add(medidor);
            }
            
            return listMedidor;
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
    
    public List<Medidor> listarPerfilAdministrador(String idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medidor> listMedidor = new ArrayList<Medidor>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    VW_MEDIDORAPP.ID_EMPRESA, " +
        	"          VW_MEDIDORAPP.EMPRESA, " +
        	"          VW_MEDIDORAPP.ID_CONDOMINIO, " +
        	"          VW_MEDIDORAPP.CONDOMINIO, " +
        	"          VW_MEDIDORAPP.ENDERECOCOND, " +
        	"          VW_MEDIDORAPP.NUMEROCOND, " +
        	"          VW_MEDIDORAPP.COMPLCOND, " +
        	"          VW_MEDIDORAPP.MUNICIPIOCOND, " +
        	"          VW_MEDIDORAPP.UFCOND, " +
        	"          VW_MEDIDORAPP.CEPCOND, " +
        	"          VW_MEDIDORAPP.COORDXCOND, " +
        	"          VW_MEDIDORAPP.COORDYCOND, " +
        	"          VW_MEDIDORAPP.ID_BRIDGE, " +
        	"          VW_MEDIDORAPP.ID_BRIDGETP, " +
        	"          VW_MEDIDORAPP.BRIDGETP, " +
        	"          VW_MEDIDORAPP.DEVICENUM, " +
        	"          VW_MEDIDORAPP.ID_MEDIDOR, " +
        	"          VW_MEDIDORAPP.METERID, " +
        	"          VW_MEDIDORAPP.METERPOSITION, " +
        	"          VW_MEDIDORAPP.ENDERECOMED, " +
        	"          VW_MEDIDORAPP.NUMEROMED, " +
        	"          VW_MEDIDORAPP.COMPLMED, " +
        	"          VW_MEDIDORAPP.MUNICIPIOMED, " +
        	"          VW_MEDIDORAPP.UFMED, " +
        	"          VW_MEDIDORAPP.CEPMED, " +
        	"          VW_MEDIDORAPP.COORDXMED, " +
        	"          VW_MEDIDORAPP.COORDYMED, " +
        	"          VW_MEDIDORAPP.DTINSERT " +
        	"FROM      VW_MEDIDORAPP " +	
        	"LEFT JOIN TB_USERCONDOMINIO " +
        	"ON        VW_MEDIDORAPP.ID_CONDOMINIO = TB_USERCONDOMINIO.ID_CONDOMINIO " +
        	"WHERE     TB_USERCONDOMINIO.ID_USER = ? " +
        	"AND       TB_USERCONDOMINIO.SITUACAO = 'A' " +
	    	"ORDER BY  VW_MEDIDORAPP.CONDOMINIO, " +
	    	"          VW_MEDIDORAPP.ENDERECOMED, " + 
	    	"          VW_MEDIDORAPP.NUMEROMED, " +
	    	"          VW_MEDIDORAPP.COMPLMED "   	
            );
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Medidor medidor = new Medidor();
            	medidor.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	medidor.setEmpresa(rs.getString("EMPRESA"));
            	medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	medidor.setCondominio(Auxiliar.removerCaracteres(rs.getString("CONDOMINIO")));
            	medidor.setEnderecoCond(Auxiliar.removerCaracteres(rs.getString("ENDERECOCOND")));
            	medidor.setNumeroCond(rs.getLong("NUMEROCOND"));
            	medidor.setComplCond(Auxiliar.removerCaracteres(rs.getString("COMPLCOND")));
            	medidor.setMunicipioCond(Auxiliar.removerCaracteres(rs.getString("MUNICIPIOCOND")));
            	medidor.setUfCond(rs.getString("UFCOND"));
            	medidor.setCepCond(rs.getString("CEPCOND"));
            	medidor.setCoordXCond(rs.getString("COORDXCOND"));
            	medidor.setCoordYCond(rs.getString("COORDYCOND"));
            	medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
            	medidor.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
            	medidor.setBridgeTp(rs.getString("BRIDGETP"));
            	medidor.setDeviceNum(rs.getString("DEVICENUM"));
            	medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	medidor.setNumeroMedidor(rs.getString("METERID"));
            	medidor.setMeterPosition(rs.getInt("METERPOSITION"));
            	medidor.setEnderecoMed(Auxiliar.removerCaracteres(rs.getString("ENDERECOMED")));
            	medidor.setNumeroMed(rs.getLong("NUMEROMED"));
            	medidor.setComplMed(Auxiliar.removerCaracteres(rs.getString("COMPLMED")));
            	medidor.setMunicipioMed(Auxiliar.removerCaracteres(rs.getString("MUNICIPIOMED")));
            	medidor.setUfMed(rs.getString("UFMED"));
            	medidor.setCepMed(rs.getString("CEPMED"));
            	medidor.setCoordXMed(rs.getString("COORDXMED"));
            	medidor.setCoordYMed(rs.getString("COORDYMED"));    
            	medidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listMedidor.add(medidor);
            }
            
            return listMedidor;
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
    
    public List<Medidor> listarPerfilConsumidor(String idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medidor> listMedidor = new ArrayList<Medidor>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    VW_MEDIDORAPP.ID_EMPRESA, " +
        	"          VW_MEDIDORAPP.EMPRESA, " +
        	"          VW_MEDIDORAPP.ID_CONDOMINIO, " +
        	"          VW_MEDIDORAPP.CONDOMINIO, " +
        	"          VW_MEDIDORAPP.ENDERECOCOND, " +
        	"          VW_MEDIDORAPP.NUMEROCOND, " +
        	"          VW_MEDIDORAPP.COMPLCOND, " +
        	"          VW_MEDIDORAPP.MUNICIPIOCOND, " +
        	"          VW_MEDIDORAPP.UFCOND, " +
        	"          VW_MEDIDORAPP.CEPCOND, " +
        	"          VW_MEDIDORAPP.COORDXCOND, " +
        	"          VW_MEDIDORAPP.COORDYCOND, " +
        	"          VW_MEDIDORAPP.ID_BRIDGE, " +
        	"          VW_MEDIDORAPP.ID_BRIDGETP, " +
        	"          VW_MEDIDORAPP.BRIDGETP, " +
        	"          VW_MEDIDORAPP.DEVICENUM, " +
        	"          VW_MEDIDORAPP.ID_MEDIDOR, " +
        	"          VW_MEDIDORAPP.METERID, " +
        	"          VW_MEDIDORAPP.METERPOSITION, " +
        	"          VW_MEDIDORAPP.ENDERECOMED, " +
        	"          VW_MEDIDORAPP.NUMEROMED, " +
        	"          VW_MEDIDORAPP.COMPLMED, " +
        	"          VW_MEDIDORAPP.MUNICIPIOMED, " +
        	"          VW_MEDIDORAPP.UFMED, " +
        	"          VW_MEDIDORAPP.CEPMED, " +
        	"          VW_MEDIDORAPP.COORDXMED, " +
        	"          VW_MEDIDORAPP.COORDYMED, " +
        	"          VW_MEDIDORAPP.DTINSERT " +
        	"FROM      VW_MEDIDORAPP " +	
        	"LEFT JOIN TB_USERMEDIDOR " +
        	"ON        VW_MEDIDORAPP.ID_MEDIDOR = TB_USERMEDIDOR.ID_MEDIDOR " +
        	"WHERE     TB_USERMEDIDOR.ID_USER = ? " +
        	"AND       TB_USERMEDIDOR.SITUACAO = 'A' " +
	    	"ORDER BY  VW_MEDIDORAPP.CONDOMINIO, " +
	    	"          VW_MEDIDORAPP.ENDERECOMED, " + 
	    	"          VW_MEDIDORAPP.NUMEROMED, " +
	    	"          VW_MEDIDORAPP.COMPLMED "   	
            );
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Medidor medidor = new Medidor();
            	medidor.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	medidor.setEmpresa(rs.getString("EMPRESA"));
            	medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	medidor.setCondominio(Auxiliar.removerCaracteres(rs.getString("CONDOMINIO")));
            	medidor.setEnderecoCond(Auxiliar.removerCaracteres(rs.getString("ENDERECOCOND")));
            	medidor.setNumeroCond(rs.getLong("NUMEROCOND"));
            	medidor.setComplCond(Auxiliar.removerCaracteres(rs.getString("COMPLCOND")));
            	medidor.setMunicipioCond(Auxiliar.removerCaracteres(rs.getString("MUNICIPIOCOND")));
            	medidor.setUfCond(rs.getString("UFCOND"));
            	medidor.setCepCond(rs.getString("CEPCOND"));
            	medidor.setCoordXCond(rs.getString("COORDXCOND"));
            	medidor.setCoordYCond(rs.getString("COORDYCOND"));
            	medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
            	medidor.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
            	medidor.setBridgeTp(rs.getString("BRIDGETP"));
            	medidor.setDeviceNum(rs.getString("DEVICENUM"));
            	medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	medidor.setNumeroMedidor(rs.getString("METERID"));
            	medidor.setMeterPosition(rs.getInt("METERPOSITION"));
            	medidor.setEnderecoMed(Auxiliar.removerCaracteres(rs.getString("ENDERECOMED")));
            	medidor.setNumeroMed(rs.getLong("NUMEROMED"));
            	medidor.setComplMed(Auxiliar.removerCaracteres(rs.getString("COMPLMED")));
            	medidor.setMunicipioMed(Auxiliar.removerCaracteres(rs.getString("MUNICIPIOMED")));
            	medidor.setUfMed(rs.getString("UFMED"));
            	medidor.setCepMed(rs.getString("CEPMED"));
            	medidor.setCoordXMed(rs.getString("COORDXMED"));
            	medidor.setCoordYMed(rs.getString("COORDYMED"));    
            	medidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listMedidor.add(medidor);
            }
            
            return listMedidor;
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


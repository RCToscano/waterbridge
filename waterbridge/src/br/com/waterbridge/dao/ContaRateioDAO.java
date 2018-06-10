
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.CnpTp;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.ContaRateio;

public class ContaRateioDAO {
    
    private Connection connection;

    public ContaRateioDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(ContaRateio contaRateio) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_CONTARATEIO ( " +
            //"		ID_CONTARATEIO, " +
            "		ID_CONTA, " +
            "		ID_EMPRESA, " +
            "		ID_CONDOMINIO, " +
            "		ID_MEDIDOR, " +
            "		ID_USER, " +
            "		VOLUMEINICIAL, " +
            "		VOLUMEFINAL, " +
            "		CONSUMOREAL, " +
            "		CONSUMORATEIO, " +
            "		VALORRATEIO, " +
            "		PERCRATEIO, " +
            "		OBS, " +
            "		DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,?,?,?,?,?,?,?, " +
            "       ?,?,SYSDATE() " +
            ")");
            
            //stmt.setObject(1, contaRateio.getIdContaRateio());
            stmt.setObject(1, contaRateio.getIdConta());
            stmt.setObject(2, contaRateio.getIdEmpresa());
            stmt.setObject(3, contaRateio.getIdCondominio());
            stmt.setObject(4, contaRateio.getIdMedidor());
            stmt.setObject(5, contaRateio.getIdUser());
            stmt.setObject(6, contaRateio.getVolumeInicial());
            stmt.setObject(7, contaRateio.getVolumeFinal());
            stmt.setObject(8, contaRateio.getConsumoReal());
            stmt.setObject(9, contaRateio.getConsumoRateio());
            stmt.setObject(10, contaRateio.getValorRateio());
            stmt.setObject(11, contaRateio.getPercRateio());
            stmt.setObject(12, contaRateio.getObs());
            //stmt.setObject(13, contaRateio.getDtInsert());
            
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
    
    public void alterar(ContaRateio contaRateio) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(contaRateio.getIdContaRateio());
        
            stmt = connection.prepareStatement(
            "UPDATE TB_CONTARATEIO SET " +    
            "		ID_CONTA = ?, " +
            "		ID_EMPRESA = ?, " +
            "		ID_CONDOMINIO = ?, " +
            "		ID_MEDIDOR = ?, " +
            "		ID_USER = ?, " +
            "		VOLUMEINICIAL = ?, " +
            "		VOLUMEFINAL = ?, " +
            "		CONSUMOREAL = ?, " +
            "		CONSUMORATEIO = ?, " +
            "		VALORRATEIO = ?, " +
            "		PERCRATEIO = ?, " +
            "		OBS = ?, " +
            "		DTINSERT = SYSDATE() " +
    		"WHERE  ID_CONTARATEIO = ? ");		
          
            stmt.setObject(1, contaRateio.getIdConta());
            stmt.setObject(2, contaRateio.getIdEmpresa());
            stmt.setObject(3, contaRateio.getIdCondominio());
            stmt.setObject(4, contaRateio.getIdMedidor());
            stmt.setObject(5, contaRateio.getIdUser());
            stmt.setObject(6, contaRateio.getVolumeInicial());
            stmt.setObject(7, contaRateio.getVolumeFinal());
            stmt.setObject(8, contaRateio.getConsumoReal());
            stmt.setObject(9, contaRateio.getConsumoRateio());
            stmt.setObject(10, contaRateio.getValorRateio());
            stmt.setObject(11, contaRateio.getPercRateio());
            stmt.setObject(12, contaRateio.getObs());
            //stmt.setObject(13, contaRateio.getDtInsert());
            stmt.setObject(13, contaRateio.getIdContaRateio());

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

    public void logar(Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "INSERT INTO TB_CONDOMINIOLOG " +
            "SELECT * " +
            "FROM   TB_CONDOMINIO " +
            "WHERE  ID_CONDOMINIO = ? "
            );

            stmt.setLong(1, idCondominio);
            
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
    
    public Condominio buscarPorId(Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Condominio condominio = null;
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT ID_CONDOMINIO, " +
	    	"		ID_EMPRESA, " +
	    	"       ID_USER, " +
	    	"       ID_CNPTP, " +
	    	"       NOME, " +
	    	"       CNP, " +
	    	"       TELFIXO, " +
	    	"       TELCEL, " +
	    	"       EMAIL, " +
	    	"       ENDERECO, " +
	    	"       NUMERO, " +
	    	"       COMPL, " +
	    	"       MUNICIPIO, " +
	        "       UF, " +
	    	"       CEP, " +
	    	"       COORDX, " +
	    	"       COORDY, " +
	    	"       RESPONSAVEL, " +
	    	"       CONTRNUM, " +
	    	"       CONTACICLO, " +
	    	"       SITUACAO, " +
	    	"       DTINSERT " +
	    	"FROM   TB_CONDOMINIO " +		
            "WHERE  ID_CONDOMINIO = ? "		            		
            );

            stmt.setLong(1, idCondominio);

            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	CnpTp cnpTp = new CnpTp();
            	cnpTp = new CnpTpDAO(connection).buscar(rs.getLong("ID_CNPTP"));
            	
            	condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setCnpTp(cnpTp);
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setCnp(rs.getString("CNP"));
            	condominio.setTelFixo(rs.getString("TELFIXO"));
            	condominio.setTelCel(rs.getString("TELCEL"));
            	condominio.setEmail(rs.getString("EMAIL"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(rs.getLong("NUMERO"));
            	condominio.setCompl(rs.getString("COMPL"));
            	condominio.setMunicipio(rs.getString("MUNICIPIO"));
            	condominio.setUf(rs.getString("UF"));
            	condominio.setCep(rs.getString("CEP"));
            	condominio.setCoordX(rs.getString("COORDX"));
            	condominio.setCoordY(rs.getString("COORDY"));
            	condominio.setResponsavel(rs.getString("RESPONSAVEL"));
            	condominio.setContratoNum(rs.getString("CONTRNUM"));
            	condominio.setContaCiclo(rs.getLong("CONTACICLO"));
            	condominio.setSituacao(rs.getString("SITUACAO"));
            	condominio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));            	                
            }
            
            return condominio;
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
    
    public Condominio buscarPorCnp(String cnp) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Condominio condominio = null;
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT ID_CONDOMINIO, " +
			"		ID_EMPRESA, " +
	    	"       ID_USER, " +
	    	"       ID_CNPTP, " +
	    	"       NOME, " +
	    	"       CNP, " +
	    	"       TELFIXO, " +
	    	"       TELCEL, " +
	    	"       EMAIL, " +
	    	"       ENDERECO, " +
	    	"       NUMERO, " +
	    	"       COMPL, " +
	    	"       MUNICIPIO, " +
	        "       UF, " +
	    	"       CEP, " +
	    	"       COORDX, " +
	    	"       COORDY, " +
	    	"       RESPONSAVEL, " +
	    	"       CONTRNUM, " +
	    	"       CONTACICLO, " +
	    	"       SITUACAO, " +
	    	"       DTINSERT " +
	    	"FROM   TB_CONDOMINIO " +		
            "WHERE  CNP = ? "		            		
            );

            stmt.setString(1, cnp);

            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	CnpTp cnpTp = new CnpTp();
            	cnpTp = new CnpTpDAO(connection).buscar(rs.getLong("ID_CNPTP"));
            	
            	condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setCnpTp(cnpTp);
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setCnp(rs.getString("CNP"));
            	condominio.setTelFixo(rs.getString("TELFIXO"));
            	condominio.setTelCel(rs.getString("TELCEL"));
            	condominio.setEmail(rs.getString("EMAIL"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(rs.getLong("NUMERO"));
            	condominio.setCompl(rs.getString("COMPL"));
            	condominio.setMunicipio(rs.getString("MUNICIPIO"));
            	condominio.setUf(rs.getString("UF"));
            	condominio.setCep(rs.getString("CEP"));
            	condominio.setCoordX(rs.getString("COORDX"));
            	condominio.setCoordY(rs.getString("COORDY"));
            	condominio.setResponsavel(rs.getString("RESPONSAVEL"));
            	condominio.setContratoNum(rs.getString("CONTRNUM"));
            	condominio.setContaCiclo(rs.getLong("CONTACICLO"));
            	condominio.setSituacao(rs.getString("SITUACAO"));
            	condominio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));            	                
            }
            
            return condominio;
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
    
    public List<Condominio> listar() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Condominio> listCondominio = new ArrayList<Condominio>();
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT   ID_CONDOMINIO, " +
			"		  ID_EMPRESA, " +
	    	"         ID_USER, " +
	    	"         ID_CNPTP, " +
	    	"         NOME, " +
	    	"         CNP, " +
	    	"         TELFIXO, " +
	    	"         TELCEL, " +
	    	"         EMAIL, " +
	    	"         ENDERECO, " +
	    	"         NUMERO, " +
	    	"         COMPL, " +
	    	"         MUNICIPIO, " +
	        "         UF, " +
	    	"         CEP, " +
	    	"         COORDX, " +
	    	"         COORDY, " +
	    	"         RESPONSAVEL, " +
	    	"         CONTRNUM, " +
	    	"         CONTACICLO, " +
	    	"         SITUACAO, " +
	    	"         DTINSERT " +
	    	"FROM     TB_CONDOMINIO " +		
	    	"ORDER BY NOME, " +
	    	"         ENDERECO, " + 
	    	"         NUMERO, " +
	    	"         COMPL "   	
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	CnpTp cnpTp = new CnpTp();
            	cnpTp = new CnpTpDAO(connection).buscar(rs.getLong("ID_CNPTP"));
            	
            	Condominio condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setCnpTp(cnpTp);
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setCnp(rs.getString("CNP"));
            	condominio.setTelFixo(rs.getString("TELFIXO"));
            	condominio.setTelCel(rs.getString("TELCEL"));
            	condominio.setEmail(rs.getString("EMAIL"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(rs.getLong("NUMERO"));
            	condominio.setCompl(rs.getString("COMPL"));
            	condominio.setMunicipio(rs.getString("MUNICIPIO"));
            	condominio.setUf(rs.getString("UF"));
            	condominio.setCep(rs.getString("CEP"));
            	condominio.setCoordX(rs.getString("COORDX"));
            	condominio.setCoordY(rs.getString("COORDY"));
            	condominio.setResponsavel(rs.getString("RESPONSAVEL"));
            	condominio.setContratoNum(rs.getString("CONTRNUM"));
            	condominio.setContaCiclo(rs.getLong("CONTACICLO"));
            	condominio.setSituacao(rs.getString("SITUACAO"));
            	condominio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listCondominio.add(condominio);
            }
            
            return listCondominio;
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
    
    public List<Condominio> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Condominio> listCondominio = new ArrayList<Condominio>();
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT   ID_CONDOMINIO, " +
			"		  ID_EMPRESA, " +
	    	"         ID_USER, " +
	    	"         ID_CNPTP, " +
	    	"         NOME, " +
	    	"         CNP, " +
	    	"         TELFIXO, " +
	    	"         TELCEL, " +
	    	"         EMAIL, " +
	    	"         ENDERECO, " +
	    	"         NUMERO, " +
	    	"         COMPL, " +
	    	"         MUNICIPIO, " +
	        "         UF, " +
	    	"         CEP, " +
	    	"         COORDX, " +
	    	"         COORDY, " +
	    	"         RESPONSAVEL, " +
	    	"         CONTRNUM, " +
	    	"         CONTACICLO, " +
	    	"         SITUACAO, " +
	    	"         DTINSERT " +
	    	"FROM     TB_CONDOMINIO " +		
	    	sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	CnpTp cnpTp = new CnpTp();
            	cnpTp = new CnpTpDAO(connection).buscar(rs.getLong("ID_CNPTP"));
            	
            	Condominio condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setCnpTp(cnpTp);
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setCnp(rs.getString("CNP"));
            	condominio.setTelFixo(rs.getString("TELFIXO"));
            	condominio.setTelCel(rs.getString("TELCEL"));
            	condominio.setEmail(rs.getString("EMAIL"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(rs.getLong("NUMERO"));
            	condominio.setCompl(rs.getString("COMPL"));
            	condominio.setMunicipio(rs.getString("MUNICIPIO"));
            	condominio.setUf(rs.getString("UF"));
            	condominio.setCep(rs.getString("CEP"));
            	condominio.setCoordX(rs.getString("COORDX"));
            	condominio.setCoordY(rs.getString("COORDY"));
            	condominio.setResponsavel(rs.getString("RESPONSAVEL"));
            	condominio.setContratoNum(rs.getString("CONTRNUM"));
            	condominio.setContaCiclo(rs.getLong("CONTACICLO"));
            	condominio.setSituacao(rs.getString("SITUACAO"));
            	condominio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listCondominio.add(condominio);
            }
            
            return listCondominio;
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
    
    public List<Condominio> listarPorUsuario(Long idUser, Long idEmpresa) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Condominio> listCondominio = new ArrayList<Condominio>();
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT    TB_CONDOMINIO.ID_CONDOMINIO, " +
			"		   TB_CONDOMINIO.ID_EMPRESA, " +
	    	"          TB_CONDOMINIO.ID_USER, " +
	    	"          TB_CONDOMINIO.ID_CNPTP, " +
	    	"          TB_CONDOMINIO.NOME, " +
	    	"          TB_CONDOMINIO.CNP, " +
	    	"          TB_CONDOMINIO.TELFIXO, " +
	    	"          TB_CONDOMINIO.TELCEL, " +
	    	"          TB_CONDOMINIO.EMAIL, " +
	    	"          TB_CONDOMINIO.ENDERECO, " +
	    	"          TB_CONDOMINIO.NUMERO, " +
	    	"          TB_CONDOMINIO.COMPL, " +
	    	"          TB_CONDOMINIO.MUNICIPIO, " +
	        "          TB_CONDOMINIO.UF, " +
	    	"          TB_CONDOMINIO.CEP, " +
	    	"          TB_CONDOMINIO.COORDX, " +
	    	"          TB_CONDOMINIO.COORDY, " +
	    	"          TB_CONDOMINIO.RESPONSAVEL, " +
	    	"          TB_CONDOMINIO.CONTRNUM, " +
	    	"          TB_CONDOMINIO.CONTACICLO, " +
	    	"          TB_CONDOMINIO.SITUACAO, " +
	    	"          TB_CONDOMINIO.DTINSERT " +
	    	"FROM      TB_CONDOMINIO " +		
	    	"LEFT JOIN VW_USERCONDOMINIOID " +
		    "ON        TB_CONDOMINIO.ID_CONDOMINIO = VW_USERCONDOMINIOID.ID_CONDOMINIO " +
		    "WHERE     VW_USERCONDOMINIOID.ID_USER = ? " +
		    "AND       TB_CONDOMINIO.ID_EMPRESA = ? "
		    );
            
            stmt.setObject(1, idUser);
            stmt.setObject(2, idEmpresa);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	CnpTp cnpTp = new CnpTp();
            	cnpTp = new CnpTpDAO(connection).buscar(rs.getLong("ID_CNPTP"));
            	
            	Condominio condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setCnpTp(cnpTp);
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setCnp(rs.getString("CNP"));
            	condominio.setTelFixo(rs.getString("TELFIXO"));
            	condominio.setTelCel(rs.getString("TELCEL"));
            	condominio.setEmail(rs.getString("EMAIL"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(rs.getLong("NUMERO"));
            	condominio.setCompl(rs.getString("COMPL"));
            	condominio.setMunicipio(rs.getString("MUNICIPIO"));
            	condominio.setUf(rs.getString("UF"));
            	condominio.setCep(rs.getString("CEP"));
            	condominio.setCoordX(rs.getString("COORDX"));
            	condominio.setCoordY(rs.getString("COORDY"));
            	condominio.setResponsavel(rs.getString("RESPONSAVEL"));
            	condominio.setContratoNum(rs.getString("CONTRNUM"));
            	condominio.setContaCiclo(rs.getLong("CONTACICLO"));
            	condominio.setSituacao(rs.getString("SITUACAO"));
            	condominio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listCondominio.add(condominio);
            }
            
            return listCondominio;
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

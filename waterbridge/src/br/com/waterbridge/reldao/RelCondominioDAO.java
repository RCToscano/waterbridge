
package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.relmodelo.RelCondominio;
import br.com.waterbridge.relmodelo.RelUserCondominio;

public class RelCondominioDAO {
    
    private Connection connection;

    public RelCondominioDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelCondominio> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelCondominio> listRelCondominio = new ArrayList<RelCondominio>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_EMPRESA, " +
    		"		EMPRESA, " +
    		"		ID_CONDOMINIO, " +
    		"		ID_USER, " +
    		"		ID_CNPTP, " +
    		"		CNPTP, " +
    		"		NOME, " +
    		"		CNP, " +
    		"		TELFIXO, " +
    		"		TELCEL, " +
    		"		EMAIL, " +
    		"		ENDERECO, " +
    		"		NUMERO, " +
    		"		COMPL, " +
    		"		MUNICIPIO, " +
    		"		UF, " +
    		"		CEP, " +
    		"		COORDX, " +
    		"		COORDY, " +
    		"		RESPONSAVEL, " +
    		"		CONTRNUM, " +
    		"		CONTACICLO, " +
    		"		SITUACAO, " +
    		"		DTINSERT " +
        	"FROM   VW_CONDOMINIO " +		
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	List<RelUserCondominio> listRelUserCondominio = new RelUserCondominioDAO(connection).listar(rs.getLong("ID_CONDOMINIO"));
            	
            	RelCondominio relCondominio = new RelCondominio();
            	relCondominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relCondominio.setEmpresa(rs.getString("EMPRESA"));
            	relCondominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));	
            	relCondominio.setIdUser(rs.getLong("ID_USER"));
            	relCondominio.setIdCnpTp(rs.getLong("ID_CNPTP"));
            	relCondominio.setCnpTp(rs.getString("CNPTP"));
            	relCondominio.setNome(rs.getString("NOME"));
            	relCondominio.setCnp(rs.getString("CNP"));
            	relCondominio.setTelFixo(rs.getString("TELFIXO"));
            	relCondominio.setTelCel(rs.getString("TELCEL"));
            	relCondominio.setEmail(rs.getString("EMAIL"));
            	relCondominio.setEndereco(rs.getString("ENDERECO"));
            	relCondominio.setNumero(rs.getLong("NUMERO"));
            	relCondominio.setCompl(rs.getString("COMPL"));
            	relCondominio.setMunicipio(rs.getString("MUNICIPIO"));
            	relCondominio.setUf(rs.getString("UF"));
            	relCondominio.setCep(rs.getString("CEP"));
            	relCondominio.setCoordX(rs.getString("COORDX"));
            	relCondominio.setCoordY(rs.getString("COORDY"));
            	relCondominio.setResponsavel(rs.getString("RESPONSAVEL"));
            	relCondominio.setContratoNum(rs.getString("CONTRNUM"));
            	relCondominio.setContaCiclo(rs.getLong("CONTACICLO"));
            	relCondominio.setSituacao(rs.getString("SITUACAO"));
            	relCondominio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	relCondominio.setListRelUserCondominio(listRelUserCondominio);
            	
            	listRelCondominio.add(relCondominio);
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


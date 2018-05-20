
package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.relmodelo.RelEmpresa;
import br.com.waterbridge.relmodelo.RelUserEmpresa;

public class RelEmpresaDAO {
    
    private Connection connection;

    public RelEmpresaDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelEmpresa> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelEmpresa> listRelEmpresa = new ArrayList<RelEmpresa>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_EMPRESA, " +
        	"       ID_USER, " +
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
        	"       LOGOPDIR, " +
        	"       LOGOPNOME, " +
        	"       SITUACAO, " +
        	"       DTINSERT, " +
        	"	    RESPONSAVEL, " +
        	"       OBS " +
        	"FROM   VW_EMPRESA " +		
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	List<RelUserEmpresa> listRelUserEmpresa = new RelUserEmpresaDAO(connection).listar(rs.getLong("ID_EMPRESA"));
            	
            	RelEmpresa relEmpresa = new RelEmpresa();
            	relEmpresa.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relEmpresa.setIdUser(rs.getLong("ID_USER"));
            	relEmpresa.setNome(rs.getString("NOME"));
            	relEmpresa.setCnp(rs.getString("CNP"));
            	relEmpresa.setTelFixo(rs.getString("TELFIXO"));
            	relEmpresa.setTelCel(rs.getString("TELCEL"));
            	relEmpresa.setEmail(rs.getString("EMAIL"));
            	relEmpresa.setResponsavel(rs.getString("RESPONSAVEL"));
            	relEmpresa.setEndereco(rs.getString("ENDERECO"));
            	relEmpresa.setNumero(rs.getLong("NUMERO"));
            	relEmpresa.setCompl(rs.getString("COMPL"));
            	relEmpresa.setMunicipio(rs.getString("MUNICIPIO"));
            	relEmpresa.setUf(rs.getString("UF"));
            	relEmpresa.setCep(rs.getString("CEP"));
            	relEmpresa.setCoordX(rs.getString("COORDX"));
            	relEmpresa.setCoordY(rs.getString("COORDY"));
            	relEmpresa.setLogoPDir(rs.getString("LOGOPDIR"));
            	relEmpresa.setLogoPNome(rs.getString("LOGOPNOME"));
            	relEmpresa.setSituacao(rs.getString("SITUACAO"));
            	relEmpresa.setDtInsert(rs.getString("DTINSERT"));
            	relEmpresa.setObs(Auxiliar.formataDtTelaHr(rs.getString("OBS")));
            	relEmpresa.setListRelUserEmpresa(listRelUserEmpresa);
            	
            	listRelEmpresa.add(relEmpresa);
            }
            
            return listRelEmpresa;
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


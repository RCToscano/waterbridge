
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.Empresa;

public class EmpresaDAO {
    
    private Connection connection;

    public EmpresaDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<Empresa> listarPorUsuario(Long idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Empresa> listEmpresa = new ArrayList<Empresa>();
        
        try {
            
            stmt = connection.prepareStatement(
		    "SELECT    TB_EMPRESA.ID_EMPRESA, " +
		    "          TB_EMPRESA.ID_USER, " +
		    "          TB_EMPRESA.NOME, " +
		    "          TB_EMPRESA.CNP, " +
		    "          TB_EMPRESA.TELFIXO, " +
		    "          TB_EMPRESA.TELCEL, " +
		    "          TB_EMPRESA.RESPONSAVEL, " +
		    "          TB_EMPRESA.EMAIL, " +
		    "          TB_EMPRESA.ENDERECO, " +
		    "          TB_EMPRESA.NUMERO, " +
		    "          TB_EMPRESA.COMPL, " +
		    "          TB_EMPRESA.MUNICIPIO, " +
		    "          TB_EMPRESA.UF, " +
		    "          TB_EMPRESA.CEP, " +
		    "          TB_EMPRESA.COORDX, " +
		    "          TB_EMPRESA.COORDY, " +
		    "          TB_EMPRESA.LOGOPDIR, " +
		    "          TB_EMPRESA.LOGOPNOME, " +
		    "          TB_EMPRESA.SITUACAO, " +
		    "          TB_EMPRESA.DTINSERT " +
		    "FROM      TB_EMPRESA " +
		    "LEFT JOIN TB_USEREMPRESA " +
		    "ON        TB_EMPRESA.ID_EMPRESA = TB_USEREMPRESA.ID_EMPRESA " +
		    "WHERE     TB_USEREMPRESA.ID_USER = ? ");
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Empresa empresa = new Empresa();
            	empresa.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	empresa.setIdUser(rs.getLong("ID_USER"));
            	empresa.setNome(rs.getString("NOME"));
            	empresa.setCnpj(rs.getString("CNP"));
            	empresa.setTelFixo(rs.getString("TELFIXO"));
            	empresa.setTelCel(rs.getString("TELCEL"));
            	empresa.setEmail(rs.getString("EMAIL"));
            	empresa.setResponsavel(rs.getString("RESPONSAVEL"));
            	empresa.setEndereco(rs.getString("ENDERECO"));
            	empresa.setNumero(rs.getLong("NUMERO"));
            	empresa.setCompl(rs.getString("COMPL"));
            	empresa.setMunicipio(rs.getString("MUNICIPIO"));
            	empresa.setUf(rs.getString("UF"));
            	empresa.setCep(rs.getString("CEP"));
            	empresa.setCoordX(rs.getString("COORDX"));
            	empresa.setCoordY(rs.getString("COORDY"));
            	empresa.setLogoPDir(rs.getString("LOGOPDIR"));;
            	empresa.setLogoPNome(rs.getString("LOGOPNOME"));;
            	empresa.setSituacao(rs.getString("SITUACAO"));
            	empresa.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	
            	listEmpresa.add(empresa);
            }
            
            return listEmpresa;
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

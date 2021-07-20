package br.com.waterbridge.schedulers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.net.SocketException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.ArquivoDAO;
import br.com.waterbridge.dao.ExportacaoDAO;
import br.com.waterbridge.modelo.Arquivo;
import br.com.waterbridge.modelo.Exportacao;

public class ExportacaoBO implements Runnable {

	Connection conn = null;
	CallableStatement stmt = null;
	
	@Override
	public void run() {
		
		try {
			
			conn = ConnectionFactory.getConnection();			
			stmt = conn.prepareCall("{call PROC_PREPARAR_EXPORTACAO()}");
			stmt.execute();
			
			ExportacaoDAO exportacaoDAO = new ExportacaoDAO(conn);
			List<Exportacao> listExportacao = exportacaoDAO.listarParaExportacao();
			
			if(listExportacao.size() > 0) {
			
				ArquivoDAO arquivoDAO = new ArquivoDAO(conn);
				String dataAtual = arquivoDAO.buscarDataAtual();
				
				Integer ultimaRemessa = arquivoDAO.buscarUltimaRemessa(dataAtual.substring(0, 10));
				
				Integer remessa = ultimaRemessa + 1;
				String nomeArquivo = "W" + Auxiliar.leftPad(remessa.toString(), 4, "0") + dataAtual.replace("-", "").replace(":", "").replace(" ", "");
			
				//File file = new File("c:/Temp/" + nomeArquivo + ".txt");
				File file = new File("/home/waterbridge/exportacao/" + nomeArquivo + ".txt");
				file.createNewFile();
				
				FileOutputStream fo = new FileOutputStream(file.getAbsoluteFile(), Boolean.TRUE);
				Writer writer = Channels.newWriter(fo.getChannel(), StandardCharsets.UTF_8.name());
				
				writer.append("1" + nomeArquivo + Auxiliar.rightPad("", 319, " ") + System.lineSeparator());			
				for(Exportacao exportacao: listExportacao) {
					
					writer.append(
							"2" + 
							exportacao.getDevice() +
							Auxiliar.leftPad(exportacao.getEmpresa() == null ? "": exportacao.getEmpresa(), 100, " ") +
							Auxiliar.leftPad(exportacao.getEndereco() == null ? "": exportacao.getEndereco(), 100, " ") +
							Auxiliar.leftPad(exportacao.getNumero() == null ? "": exportacao.getNumero().toString(), 10, "0")  +
							Auxiliar.leftPad(exportacao.getCompl() == null ? "": exportacao.getCompl(), 50, " ") +
							Auxiliar.leftPad(exportacao.getCoordX() == null ? "": exportacao.getCoordX(), 20, " ") +
							Auxiliar.leftPad(exportacao.getCoordY() == null ? "": exportacao.getCoordY(), 20, " ") +
							Auxiliar.leftPad(exportacao.getPressure().toString(), 7, " ") +
							Auxiliar.leftPad(exportacao.getFlow().toString(), 11, " ") +
							exportacao.getDtInsert().substring(0, 19).replace("-", "").replace(":", "").replace(" ", "") +
							System.lineSeparator());
				}						
				writer.append("3" + nomeArquivo + Auxiliar.leftPad(String.valueOf((listExportacao.size() + 2)), 5, "0") + Auxiliar.rightPad("", 314, " "));
				
				writer.close();
				fo.close();
				
				enviarArquivo(file);
				
				Arquivo arquivo = new Arquivo();
				arquivo.setIdArquivo(null);
				arquivo.setNomeArquivo(nomeArquivo + ".txt");
				arquivo.setRemessa(remessa);
				arquivo.setQtde(listExportacao.size());
				arquivo.setDtInsert(dataAtual);
				arquivoDAO.inserir(arquivo);
				
				arquivo = arquivoDAO.buscarPorRemessaData(remessa, dataAtual);
				
				exportacaoDAO.atualizarIdArquivo(arquivo.getIdArquivo());
			}
		} 
		catch (Exception e) {
			System.out.println("erro " + e.toString());
		}
		finally {
			if(conn != null) {
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(conn != null) {
				try {stmt.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
	}
	
	public void enviarArquivo(File file) throws SocketException, IOException {
		
		FTPClient client = null;
		FileInputStream fis = null;
		
		try {		
			client = new FTPClient();
			client.connect("waws-prod-cq1-007.ftp.azurewebsites.windows.net");
		    client.login("ftp-barragens\\engenharia", "jhoykRweBzm8nxwWlX2kZN33cEdE3lCwsxGWvTyLlCiKl4XKKbNKPbk9QqmM");		    
		    client.changeWorkingDirectory("/Desoltec");
		    
		    fis = new FileInputStream(file);
		    client.storeFile(file.getName(), fis);
		} 
		catch(Exception e) {
			throw e;
		}
		finally {
			if(fis != null) {
				fis.close();
			}
			if(client != null) {
				client.logout();
			}
		}	
	}
}

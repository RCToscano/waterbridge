package br.com.waterbridge.schedulers;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.waterbridge.auxiliar.ColunasExcel;
import br.com.waterbridge.auxiliar.GeradorExcel;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.modelo.bo.RelConsumoPressaoExcelBO;
import br.com.waterbridge.reldao.RelPressaoDAO;

public class ExcelConsumoUserEmpresa implements Runnable {
	
	@Override
	public void run() {
		Calendar dataInicio = Calendar.getInstance();
		if (dataInicio.get(Calendar.DAY_OF_MONTH) == 01) {
		
			System.out.println("Inicio geracao Excel Mensal Consumo Empresa Usuario");
			try (Connection connection = ConnectionFactory.getConnection()) {
				
				SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				dataInicio.add(Calendar.MONTH, -1);
				dataInicio.set(Calendar.DAY_OF_MONTH, 1);
				dataInicio.set(Calendar.HOUR_OF_DAY, 0);
				dataInicio.set(Calendar.MINUTE, 0);
				dataInicio.set(Calendar.SECOND, 0);
				System.out.println("DataInicio: " + dataInicio.getTime());
		    	
				Calendar dataFim = Calendar.getInstance();
				dataFim.set(Calendar.DAY_OF_MONTH, 1);
				dataFim.add(Calendar.DAY_OF_MONTH, -1);
				dataFim.set(Calendar.HOUR_OF_DAY, 23);
				dataFim.set(Calendar.MINUTE, 59);
				dataFim.set(Calendar.SECOND, 59);
				System.out.println("DataFim: " + dataFim.getTime());
	
				System.out.println("Buscando consumo no banco de dados");
				RelPressaoDAO relPressaoDAO = new RelPressaoDAO(connection);
				RelConsumoPressaoExcelBO resultado = relPressaoDAO.buscarConsumoUserEmpresa(4l, 4l,
						formatoBanco.format(dataInicio.getTime()),
						formatoBanco.format(dataFim.getTime()));
				
				if (!resultado.getAbas().isEmpty()) {
					System.out.println("Gerando excel com ["+resultado.getAbas().size()+"] Bridges");
					
					GeradorExcel.gerarExcelConsumo("C:\\Temp\\Teste.xlsx", resultado.getAbas(),
							new ColunasExcel().getColunasConsumoUserEmpresa(), resultado.getMap());
					
					System.out.println("Excel gerado com sucesso");
				}
				else {
					System.out.println("Nenhum resultado encontrado");
				}
			} 
			catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public static void main(String[] args) {
	
		new ExcelConsumoUserEmpresa().run();
	}	
}

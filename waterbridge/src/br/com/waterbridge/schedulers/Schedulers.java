package br.com.waterbridge.schedulers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Schedulers implements ServletContextListener {

    private ScheduledExecutorService scheduler;
    
    public static void main(String[] args) {
    	Calendar dataInicio = Calendar.getInstance();
		dataInicio.set(Calendar.DAY_OF_MONTH, 1);
		dataInicio.add(Calendar.MONTH, -1);
		System.out.println(dataInicio.getTime());
    	
		Calendar dataFim = Calendar.getInstance();
		dataFim.set(Calendar.DAY_OF_MONTH, 1);
		dataFim.add(Calendar.DAY_OF_MONTH, -1);
		System.out.println(dataFim.getTime());
	}

    @Override
    public void contextInitialized(ServletContextEvent event) {
    	try {	    	
	    	scheduler = Executors.newSingleThreadScheduledExecutor();
	    	validadeToken();
	    	excelConsumoUserEmpresa();
    	} 
    	catch (Exception e) {
			System.out.println(e);
		}
    }
    
    private void validadeToken() throws ParseException {
    	String timeToStart = "08:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
		SimpleDateFormat formatOnlyDay = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		Date dateToStart = format.parse(formatOnlyDay.format(now) + " at " + timeToStart);
		long diff = dateToStart.getTime() - now.getTime();
		if (diff < 0) {
			Date tomorrow = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(tomorrow);
			c.add(Calendar.DATE, 1);
			tomorrow = c.getTime();
			dateToStart = format.parse(formatOnlyDay.format(tomorrow) + " at " + timeToStart);
			diff = dateToStart.getTime() - now.getTime();
		}
		scheduler.scheduleAtFixedRate(new ValidadeToken(), TimeUnit.MILLISECONDS.toSeconds(diff), 24 * 60 * 60,
				TimeUnit.SECONDS);
    }
    
	private void excelConsumoUserEmpresa() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 04);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		scheduler.schedule(new ExcelConsumoUserEmpresa(), calendar.getTimeInMillis() - System.currentTimeMillis(),
				TimeUnit.MILLISECONDS);
	}

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }

}

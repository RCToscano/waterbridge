package br.com.waterbridge.schedulers;

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

    @Override
    public void contextInitialized(ServletContextEvent event) {
    	try {
	    	System.out.println("entrou");
	    	
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
	    	
	        scheduler = Executors.newSingleThreadScheduledExecutor();
	        scheduler.scheduleAtFixedRate(new ValidadeToken(), TimeUnit.MILLISECONDS.toSeconds(diff), 24*60*60, TimeUnit.SECONDS);
    	} 
    	catch (Exception e) {
			System.out.println(e);
		}
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }

}

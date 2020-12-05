package br.com.waterbridge.schedulers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ExportacaoScheduler implements ServletContextListener {

	private ScheduledExecutorService scheduler;
	
    @Override
    public void contextInitialized(ServletContextEvent event) {
    	try {	    	
	    	scheduler = Executors.newSingleThreadScheduledExecutor();
	    	scheduler.scheduleAtFixedRate(new ExportacaoBO(), 0, 15L, TimeUnit.MINUTES);
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

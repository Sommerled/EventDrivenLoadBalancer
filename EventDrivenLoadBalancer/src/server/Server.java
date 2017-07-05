package server;

import java.util.List;

import context.ConnectionContext;
import context.ContextLoader;
import context.ShutdownContextEvent;
import eventhandler.EventHandler;
import server.connectionkeeper.ConnectionCreator;
import server.connectionkeeper.ConnectionKeeper;
import server.connectionkeeper.NewConnectionEvent;

public class Server {
	private EventHandler handler = null;
	
	public Server(){
		init();
	}
	
	public void init(){
		this.handler = new EventHandler();
		
		ConnectionCreator cc = new ConnectionCreator(this.handler, this.handler);
		Thread ccThread = new Thread(cc);
		ccThread.setName("ConnectionCreator");
		ccThread.start();
		
		ConnectionKeeper ck = new ConnectionKeeper(this.handler, this.handler);
		Thread ckThread = new Thread(ck);
		ckThread.setName("ConnectionKeeper");
		ckThread.start();
		
		EventProducerMonitor epm = new EventProducerMonitor(this.handler);
		Thread epmThread = new Thread(epm);
		epmThread.setName("ProducerMonitor");
		epmThread.start();
		
		List<ConnectionContext> contexts = ContextLoader.getLoadedContexts();
		ConnectionContext context = null;
		for(int i = 0; i < contexts.size(); i++){
			NewConnectionEvent nce = new NewConnectionEvent(null, null, contexts.get(i));
			if(contexts.get(i).getListening()){
				context = contexts.get(i);
			}
			try {
				this.handler.put(nce);
			} catch (InterruptedException e) {
				e.printStackTrace();
				
				break;
			}
		}
		
		try {
			Thread.sleep(60000);
			ShutdownContextEvent sce = new ShutdownContextEvent(null, null, context);
			this.handler.put(sce);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

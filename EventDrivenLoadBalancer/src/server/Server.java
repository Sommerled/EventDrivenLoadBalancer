package server;

import java.util.List;

import context.ConnectionContext;
import context.ContextLoader;
import eventhandler.EventHandler;
import server.connectionkeeper.ConnectionCreator;
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
		
		List<ConnectionContext> contexts = ContextLoader.getLoadedContexts();
		for(int i = 0; i < contexts.size(); i++){
			NewConnectionEvent nce = new NewConnectionEvent(null, null, contexts.get(i));
			try {
				this.handler.put(nce);
			} catch (InterruptedException e) {
				e.printStackTrace();
				
				break;
			}
		}
	}
}

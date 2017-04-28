package server;

import java.util.List;

import context.ConnectionContext;
import context.ContextLoader;
import eventhandler.EventHandler;

public class Server {
	private EventHandler handler = null;
	
	public Server(){
		
	}
	
	public void init(){
		this.handler = new EventHandler();
		List<ConnectionContext> contexts = ContextLoader.getLoadedContexts();
		for(int i = 0; i < contexts.size(); i++){
			//spawn worker threads
		}
	}
}

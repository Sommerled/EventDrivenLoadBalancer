package server.connectionkeeper;

import context.ConnectionContext;
import eventhandler.AbsEvent;
import server.AbsWorker;

public class NewConnectionEvent extends AbsEvent {
	private ConnectionContext context;
	
	public NewConnectionEvent(Integer id, AbsWorker originator, ConnectionContext context) {
		super(id, originator);
		
		this.context = context;
	}
	
	public void setContext(ConnectionContext context){
		this.context = context;
	}

	public ConnectionContext getContext(){
		return this.context;
	}
}

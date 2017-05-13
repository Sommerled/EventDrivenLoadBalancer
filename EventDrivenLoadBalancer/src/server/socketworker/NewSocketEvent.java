package server.socketworker;

import java.net.Socket;

import context.ConnectionContext;
import eventhandler.AbsEvent;
import server.AbsWorker;

public class NewSocketEvent extends AbsEvent{
	private Socket s = null;
	private ConnectionContext context = null;

	public NewSocketEvent(Integer id, AbsWorker originator, Socket s, ConnectionContext context) {
		super(id, originator);
		
		this.s = s;
		this.context = context;
	}

	public Socket getSocket(){
		return this.s;
	}
	
	public ConnectionContext getContext(){
		return this.context;
	}
}

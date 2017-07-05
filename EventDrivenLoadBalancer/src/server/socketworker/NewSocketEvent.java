package server.socketworker;

import java.net.Socket;

import context.AbsContextEvent;
import context.ConnectionContext;
import eventhandler.AbsEvent;
import server.AbsWorker;

public class NewSocketEvent extends AbsContextEvent{
	private Socket s = null;

	public NewSocketEvent(Integer id, AbsWorker originator, ConnectionContext context, Socket s) {
		super(id, originator, context);
		
		this.s = s;
	}

	public Socket getSocket(){
		return this.s;
	}
}

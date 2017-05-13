package server.socketworker;

import context.ConnectionContext;
import eventhandler.AbsEvent;
import server.AbsWorker;

public class SocketWorkerExceptionEvent extends AbsEvent{
	private Exception e;
	private ConnectionContext cc;
	
	public SocketWorkerExceptionEvent(Integer id, AbsWorker originator, Exception e, ConnectionContext cc) {
		super(id, originator);
		this.e = e;
		this.cc = cc;
	}
	
	public void setException(Exception e){
		this.e = e;
	}
	
	public Exception getException(){
		return this.e;
	}
	
	public ConnectionContext getContext(){
		return this.cc;
	}

	@Override
	public void destroy() {
		super.destroy();
		this.e = null;
		this.cc = null;
	}
}

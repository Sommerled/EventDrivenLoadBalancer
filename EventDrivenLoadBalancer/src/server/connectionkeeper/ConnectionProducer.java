package server.connectionkeeper;

import context.ConnectionContext;
import eventhandler.EventDispatcher;
import server.AbsEventProducer;

public abstract class ConnectionProducer extends AbsEventProducer implements Connection {
	private ConnectionContext context;
	
	public ConnectionProducer(EventDispatcher eventDispatcher, ConnectionContext context) {
		super(eventDispatcher);
		
		this.context = context;
	}

	@Override
	public ConnectionContext getContext(){
		return this.context;
	}
	
	protected void setConnection(ConnectionContext context){
		this.context = context;
	}
	
	@Override
	public boolean equals(Object o){
		boolean ret = false;
		
		if(!(o == null || o instanceof ConnectionProducer)){
			ConnectionProducer cp = (ConnectionProducer) o;
			
			String curHost = this.context.getHost();
			int curPort = this.context.getPort();
			String curProtocol = this.context.getProtocol();
			
			String cpHost = cp.getContext().getHost();
			int cpPort = cp.getContext().getPort();
			String cpProtocol = cp.getContext().getProtocol();
			
			ret = (curHost.equals(cpHost) && curPort == cpPort && curProtocol.equals(cpProtocol));
		}
		
		return ret;
		
	}
}

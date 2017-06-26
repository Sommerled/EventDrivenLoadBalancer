package server.connectionkeeper;

import java.util.Iterator;
import java.util.LinkedList;

import context.ConnectionContext;

public class RoundRobinBalancer implements ConnectionBallancer{
	private LinkedList<ConnectionContext> connections = null;
	private Iterator<ConnectionContext> iter = null;
	
	public RoundRobinBalancer(){
		this.connections = new LinkedList<ConnectionContext>();
	}
	
	@Override
	public ConnectionContext nextConncetion(ConnectionContext c) {
		ConnectionContext cc = null;
		
		if(this.iter.hasNext()){
			cc = this.iter.next();
		}else{
			this.iter = null;
			this.iter = this.connections.iterator();
			cc = this.iter.next();
		}
		
		return cc;
	}

	@Override
	public void registerContext(ConnectionContext c) {
		if(! this.connections.contains(c)){
			this.connections.addLast(c);
			
			if(this.connections.size() == 1){
				this.iter = this.connections.iterator();
			}
		}
	}

}

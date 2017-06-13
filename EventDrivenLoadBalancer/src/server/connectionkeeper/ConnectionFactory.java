package server.connectionkeeper;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import eventhandler.AbsEvent;
import server.AbsWorker;

public class ConnectionFactory {
	private  Map<String, ConnectionAdapter> adapters;
	
	public ConnectionFactory(){
		this.adapters = new ConcurrentHashMap<String, ConnectionAdapter>();
	}
	
	public ConnectionFactory(Hashtable<String, ConnectionAdapter> adapters){
		this.adapters = adapters;
	}
	
	public void setAdapters(Hashtable<String, ConnectionAdapter> adapters){
		this.adapters = adapters;
	}
	
	public void registerAdapter(String name, ConnectionAdapter adapter){
		if(this.adapters != null){
			this.adapters.put(name, adapter);
		}
	}
	
	public AbsWorker getConnection(String name, AbsEvent e){
		ConnectionAdapter a =  this.adapters.get(name);
		
		return a.getConnection(e);
	}
}

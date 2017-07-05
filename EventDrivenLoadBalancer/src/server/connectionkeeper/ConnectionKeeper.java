package server.connectionkeeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import context.ConnectionContext;
import eventhandler.AbsEvent;
import eventhandler.EventDispatcher;
import eventhandler.EventListener;
import eventhandler.EventListenerException;
import server.AbsEventProcesser;
import server.socketworker.ServerSocketWorker;

public class ConnectionKeeper extends AbsEventProcesser implements Runnable{
	private Map<ConnectionContext, ConnectionBallancer> balancingAct; //ya I needed a laugh
	private List<ServerSocketWorker> incomingSocketListeners;
	
	public ConnectionKeeper(EventListener eventListener, EventDispatcher eventDispatcher) {
		super(eventListener, eventDispatcher);
		this.balancingAct = new HashMap<ConnectionContext, ConnectionBallancer>();
	}

	@Override
	public void idComplete(Integer id) {
		
	}

	@Override
	public boolean validEvent(AbsEvent e) {
		if(e instanceof NewBalancedConnectionEvent){
			return true;
		}
		
		return false;
	}

	@Override
	public List<AbsEvent> init() {
		return null;
	}

	@Override
	public List<AbsEvent> process(AbsEvent e) {
		List<AbsEvent> events  = new ArrayList<AbsEvent>();
		
		if(e instanceof NewBalancedConnectionEvent){
			NewBalancedConnectionEvent nbce = (NewBalancedConnectionEvent)e;
			
			ConnectionContext cc = nbce.getContext();
			ConnectionContext listeningFor = cc.getListensFor();
			
			if(listeningFor != null){
				if(this.balancingAct.containsKey(listeningFor)){
					ConnectionBallancer cb =  this.balancingAct.get(listeningFor);
					cb.registerContext(cc);
					System.out.println("Adding New Balanced Connection");
				}else{
					switch(listeningFor.getAlgorithm()){
					case "Round Robin":
						RoundRobinBalancer rr = new RoundRobinBalancer();
						rr.registerContext(cc);
						this.balancingAct.put(listeningFor, rr);
						System.out.println("New Round Robin Balanced Connection");
						break;
					}
				}
				
			}else{
				//context lacks a context that it is balancing for.
			}
		}
		
		return events;
	}

	@Override
	public void run() {
		try {
			this.work();
		} catch (EventListenerException e) {
			e.printStackTrace();
		}
		
	}

}

package server.connectionkeeper;

import eventhandler.AbsEvent;
import eventhandler.EventDispatcher;
import eventhandler.EventListener;
import eventhandler.EventListenerAware;
import eventhandler.EventDispatcherAware;
import server.AbsWorker;
import server.WorkerCreator;

public class ConnectionAdapter {
	private WorkerCreator wc = null;
	private EventListener eventListener = null;
	private EventDispatcher eventDispatcher = null;
	
	public ConnectionAdapter(WorkerCreator wc, EventListener eventListener, EventDispatcher eventDispatcher){
		this.wc = wc;
		this.eventListener = eventListener;
		this.eventDispatcher = eventDispatcher;
	}
	
	public AbsWorker getConnection(AbsEvent e){
		AbsWorker worker = wc.getInstance();
		
		if(worker instanceof EventListener){
			((EventListenerAware) worker).setEventListener(eventListener);
		}
		
		if(worker instanceof EventDispatcher){
			((EventDispatcherAware)worker).setEventDispatcher(eventDispatcher);
		}
		
		if(worker instanceof EventInitializable){
			((EventInitializable)worker).setInitialEvent(e);
		}
		
		return worker;
	}
}

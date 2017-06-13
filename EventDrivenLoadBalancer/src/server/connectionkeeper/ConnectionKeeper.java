package server.connectionkeeper;

import java.util.List;

import eventhandler.AbsEvent;
import eventhandler.EventDispatcher;
import eventhandler.EventListener;
import server.AbsEventConsumer;
import server.AbsEventProcesser;

public class ConnectionKeeper extends AbsEventProcesser implements Runnable{
	
	public ConnectionKeeper(EventListener eventListener, EventDispatcher eventDispatcher) {
		super(eventListener, eventDispatcher);
	}

	@Override
	public void idComplete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validEvent(AbsEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AbsEvent> init() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AbsEvent> process(AbsEvent e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

package server.connectionkeeper;

import java.util.List;

import context.ConnectionContext;
import context.Protocals;
import eventhandler.AbsEvent;
import eventhandler.ErrorProne;
import eventhandler.EventDispatcher;
import eventhandler.EventDispatcherAware;
import eventhandler.EventListener;
import eventhandler.EventListenerException;
import server.AbsEventConsumer;
import server.AbsEventProcesser;
import server.socketworker.ServerSocketWorker;

public class ConnectionCreator extends AbsEventConsumer implements Runnable, ErrorProne, EventDispatcherAware{
	private EventListenerException ele = null;
	private EventDispatcher eventDispatcher = null;

	public ConnectionCreator(EventListener eventListener, EventDispatcher eventDispatcher) {
		super(eventListener);
		this.eventDispatcher = eventDispatcher;
	}

	@Override
	public Exception getException() {
		return this.ele;
	}

	@Override
	public void run() {
		try {
			this.work();
		} catch (EventListenerException e) {
			e.printStackTrace();
			this.ele = e;
		}
	}

	@Override
	public void idComplete(Integer id) {
	}

	@Override
	public boolean validEvent(AbsEvent e) {
		return (e instanceof NewConnectionEvent);
	}

	@Override
	public void consume(AbsEvent e) throws InterruptedException {
		NewConnectionEvent nce = (NewConnectionEvent)e;
		Thread t = null;
		if(nce.getContext().getListening()){
			ServerSocketWorker ssw = new ServerSocketWorker(this.getEventDispatcher(), nce.getContext());
			t = new Thread(ssw);
			System.out.println("new ServerSocketWorker");
		}else{
			System.out.println("new SocketWorker");
		}
		
		if(t != null){
			t.start();
		}
	}

	@Override
	public List<AbsEvent> init() {
		return null;
	}

	@Override
	public EventDispatcher getEventDispatcher() {
		return this.eventDispatcher;
	}

	@Override
	public void setEventDispatcher(EventDispatcher eventDispatcher) {
		this.eventDispatcher = eventDispatcher;
	}
}

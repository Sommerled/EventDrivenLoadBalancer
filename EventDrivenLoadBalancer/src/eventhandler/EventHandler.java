package eventhandler;

import java.util.concurrent.LinkedBlockingQueue;

public class EventHandler implements EventDispatcher {
	private LinkedBlockingQueue<AbsEvent> eventQueue = null;
	
	public EventHandler(){
		this.eventQueue = new LinkedBlockingQueue<AbsEvent>();
	}

	@Override
	public void put(AbsEvent e) throws InterruptedException {
		this.eventQueue.put(e);
	}

	@Override
	public AbsEvent peek() {
		return this.eventQueue.peek();
	}

	@Override
	public boolean remove(AbsEvent e) {
		return this.eventQueue.remove(e);
	}
	
	
}

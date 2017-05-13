package eventhandler;

import java.util.concurrent.LinkedBlockingQueue;

public class EventHandler implements EventDispatcher, EventListener {
	private LinkedBlockingQueue<AbsEvent> eventQueue = null;
	
	public EventHandler(){
		this.eventQueue = new LinkedBlockingQueue<AbsEvent>();
	}

	@Override
	public void put(AbsEvent e) throws InterruptedException {
		this.eventQueue.put(e);
		this.notifyAll();
	}

	@Override
	public AbsEvent peek() throws InterruptedException {
		while(size() == 0){
			this.wait();
		}
		
		return this.eventQueue.peek();
	}
	
	public synchronized int size(){
		return this.eventQueue.size();
	}

	@Override
	public boolean remove(AbsEvent e) {
		return this.eventQueue.remove(e);
	}
	
	
}

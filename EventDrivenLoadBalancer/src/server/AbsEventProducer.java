package server;

import java.util.List;

import eventhandler.AbsEvent;
import eventhandler.EventDispatcher;

public abstract class AbsEventProducer extends AbsWorker {
	private EventDispatcher eventDispatcher = null;
	
	public AbsEventProducer(EventDispatcher eventDispatcher) {
		this.eventDispatcher = eventDispatcher;
	}
	
	public void setEventDispatcher(EventDispatcher eventDispatcher){
		this.eventDispatcher = eventDispatcher;
	}
	
	public EventDispatcher getEventDispatcher(){
		return this.eventDispatcher;
	}
	
	@Override
	public void work(){
		while(true){
			List<AbsEvent> events;
			try {
				events = produce();

				try {
					dispatchEvents(events);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	public void dispatchEvents(List<AbsEvent> events) throws InterruptedException{
		for(int i = 0; i < events.size(); i++){
			this.eventDispatcher.put(events.get(i));
		}
	}
	
	public void shutdown(){
		this.eventDispatcher = null;
	}
	
	public abstract List<AbsEvent> produce() throws Exception;
}

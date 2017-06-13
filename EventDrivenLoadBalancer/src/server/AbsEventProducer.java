package server;

import java.util.List;

import eventhandler.AbsEvent;
import eventhandler.EventDispatcher;
import eventhandler.EventDispatcherException;
import eventhandler.EventDispatcherAware;

/**
 * An abstract object for producing events.
 */
public abstract class AbsEventProducer extends AbsWorker implements EventDispatcherAware{
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
	public void work() throws EventDispatcherException{
		List<AbsEvent> events;
		if(this.eventDispatcher == null){
			throw new EventDispatcherException("eventDispatcher not set", this.getId());
		}else{
			do{
				try {
					events = produce();
					if(events != null){
						try {
							dispatchEvents(events);
						} catch (InterruptedException e) {
							e.printStackTrace();
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}while(events != null && events.size() > 0);
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
	
	/**
	 * Produces events. When there are no more events to be created,
	 * the method returns either null or a list of size 0. It is
	 * assumed that this method will remove it's references to
	 * resources before returning null.
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract List<AbsEvent> produce() throws Exception;
}

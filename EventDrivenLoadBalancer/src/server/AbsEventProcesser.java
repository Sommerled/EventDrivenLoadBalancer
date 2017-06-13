package server;

import java.util.List;

import eventhandler.AbsEvent;
import eventhandler.EventDispatcher;
import eventhandler.EventListener;
import eventhandler.EventDispatcherAware;

/**
 * This object consumes and produces events.
 *
 */
public abstract class AbsEventProcesser extends AbsEventConsumer implements EventDispatcherAware{
	private EventDispatcher eventDispatcher = null;

	public AbsEventProcesser(EventListener eventListener, EventDispatcher eventDispatcher) {
		super(eventListener);
		
		this.eventDispatcher = eventDispatcher;
	}
	
	public void setEventDispatcher(EventDispatcher eventDispatcher){
		this.eventDispatcher = eventDispatcher;
	}

	public EventDispatcher getEventDispatcher(){
		return this.eventDispatcher;
	}
	
	@Override
	public void consume(AbsEvent e) throws InterruptedException{
		List<AbsEvent> events = process(e);
		
		if(events != null){
			for(int i = 0; i < events.size(); i++){
				this.eventDispatcher.put(events.get(i));
			}
		}
	}
	
	public abstract List<AbsEvent> process(AbsEvent e);
}

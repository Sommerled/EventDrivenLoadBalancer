package server;

import java.util.List;

import eventhandler.AbsEvent;
import eventhandler.EventDispatcher;

public abstract class AbsEventProducer extends AbsWorker {

	public AbsEventProducer(EventDispatcher eventDispatcher) {
		super(eventDispatcher);
	}
	
	@Override
	public void work(){
		EventDispatcher eventDispatcher = this.getEventDispatcher();
		while(true){
			List<AbsEvent> events = produce();
			for(int i = 0; i < events.size(); i++){
				try {
					eventDispatcher.put(events.get(i));
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}
	
	public abstract List<AbsEvent> produce();
}

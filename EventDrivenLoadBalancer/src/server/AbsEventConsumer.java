package server;

import eventhandler.AbsEvent;
import eventhandler.EventListener;
import eventhandler.EventListenerAware;
import eventhandler.EventListenerException;
import eventhandler.SystemMessage;
import eventhandler.SystemMessageEvent;

/**
 * An abstract object for consuming events.
 */
public abstract class AbsEventConsumer extends AbsWorker implements EventListenerAware{
	private EventListener eventListener = null;

	public AbsEventConsumer(EventListener eventListener) {
		this.eventListener = eventListener;
	}
	
	@Override
	public void setEventListener(EventListener eventListener){
		this.eventListener = eventListener;
	}
	
	@Override
	public EventListener getEventListener(){
		return this.eventListener;
	}

	@Override
	public void work() throws EventListenerException{
		if(this.eventListener != null){
			while(true){
				AbsEvent e;
				try {
					e = this.eventListener.peek();
					if(e instanceof SystemMessageEvent){
						SystemMessageEvent sme = (SystemMessageEvent) e;
						if(sme.getPayload().equals(SystemMessage.SHUTDOWN)){
							shutdown();
							sme.processedId(this.getId());
							idComplete(this.getId());
							this.setId(null);
						}
					}else if(validEvent(e)){
						if(this.eventListener.remove(e)){
							this.consume(e);
						}
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					break;
				}
				
			}
		}else{
			throw new EventListenerException("eventListener not set", this.getId());
		}
	}
	
	@Override
	public void shutdown(){
		this.eventListener = null;
	}
	
	public abstract void idComplete(Integer id);
	public abstract boolean validEvent(AbsEvent e);
	public abstract void consume(AbsEvent e) throws InterruptedException;
}

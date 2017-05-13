package server;

import eventhandler.AbsEvent;
import eventhandler.EventListener;
import eventhandler.SystemMessage;
import eventhandler.SystemMessageEvent;

public abstract class AbsEventConsumer extends AbsWorker{
	private EventListener eventListener = null;

	public AbsEventConsumer(EventListener eventListener) {
		this.eventListener = eventListener;
	}
	
	public void setEventListener(EventListener eventListener){
		this.eventListener = eventListener;
	}
	
	public EventListener getEventListener(){
		return this.eventListener;
	}

	@Override
	public void work(){
		while(true){
			AbsEvent e = this.eventListener.peek();
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
					this.process(e);
				}
			}
		}
	}
	
	@Override
	public void shutdown(){
		this.eventListener = null;
	}
	
	public abstract void idComplete(Integer id);
	public abstract boolean validEvent(AbsEvent e);
	public abstract void process(AbsEvent e);
}

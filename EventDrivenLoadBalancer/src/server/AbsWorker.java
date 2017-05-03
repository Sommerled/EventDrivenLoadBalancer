package server;

import eventhandler.AbsEvent;
import eventhandler.EventPoller;
import eventhandler.IdCompleteEvent;
import eventhandler.SystemMessage;
import eventhandler.SystemMessageEvent;

public abstract class AbsWorker{
	private EventPoller eventListener;
	private Integer id;
	
	public AbsWorker(EventPoller eventListener){
		this.eventListener = eventListener;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setEventListener(EventPoller eventListener){
		this.eventListener = eventListener;
	}
	
	public EventPoller getEventListener(EventPoller eventListener){
		return this.eventListener;
	}
	
	public void work(){
		while(true){
			AbsEvent e = this.eventListener.peek();
			if(e instanceof SystemMessageEvent){
				SystemMessageEvent sme = (SystemMessageEvent) e;
				if(sme.getPayload().equals(SystemMessage.SHUTDOWN)){
					shutdown();
					sme.processedId(this.id);
					IdCompleteEvent ice = new IdCompleteEvent(this.id);
					this.eventListener = null;
					this.id = null;
					break;
				}
			}else{
				if(validEvent(e)){
					if(this.eventListener.remove(e)){
						this.process(e);
					}
				}
			}
		}
	}
	
	public abstract boolean validEvent(AbsEvent e);
	public abstract void process(AbsEvent e);
	public abstract void shutdown();
}

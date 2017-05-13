package server;

import java.util.List;

import eventhandler.AbsEvent;
import eventhandler.EventDispatcher;
import eventhandler.IdCompleteEvent;
import eventhandler.SystemMessage;
import eventhandler.SystemMessageEvent;

public abstract class AbsWorker{
	private EventDispatcher eventDispatcher;
	private Integer id;
	
	public AbsWorker(EventDispatcher eventDispatcher){
		this.eventDispatcher = eventDispatcher;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setEventDispatcher(EventDispatcher eventDispatcher){
		this.eventDispatcher = eventDispatcher;
	}
	
	public EventDispatcher getEventDispatcher(){
		return this.eventDispatcher;
	}
	
	protected void addEvent(AbsEvent e) throws InterruptedException{
		this.eventDispatcher.put(e);
	}
	
	public abstract void work();
	public abstract List<AbsEvent> init();
	
	public void shutdown(){
		this.eventDispatcher = null;
	};
}

package server;

import java.util.List;

import eventhandler.AbsEvent;
import eventhandler.EventDispatcher;
import eventhandler.EventDispatcherException;
import eventhandler.EventListenerException;
import eventhandler.IdCompleteEvent;
import eventhandler.SystemMessage;
import eventhandler.SystemMessageEvent;

public abstract class AbsWorker{
	private Integer id;
		
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public abstract void work() throws EventListenerException, EventDispatcherException;
	public abstract List<AbsEvent> init();
	public abstract void shutdown();
}

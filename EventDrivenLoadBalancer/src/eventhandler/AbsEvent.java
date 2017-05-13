package eventhandler;

import server.AbsWorker;

public abstract class AbsEvent {
	private Integer id;
	private AbsWorker originator;
	
	public AbsEvent(Integer id, AbsWorker originator){
		this.id = id;
		this.originator = originator;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public AbsWorker getOriginator(){
		return this.originator;
	}
	
	public void destroy(){
		this.originator = null;
	}
}

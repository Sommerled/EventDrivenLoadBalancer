package eventhandler;

import server.AbsWorker;

public abstract class AbsEvent {
	private Integer id;
	private Object payload;
	private AbsWorker originator;
	
	public AbsEvent(Integer id, Object payload, AbsWorker originator){
		this.id = id;
		this.payload = payload;
		this.originator = originator;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public Object getPayload(){
		return this.payload;
	}
	
	public AbsWorker getOriginator(){
		return this.originator;
	}
}

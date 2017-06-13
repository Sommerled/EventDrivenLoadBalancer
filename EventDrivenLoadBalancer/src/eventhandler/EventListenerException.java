package eventhandler;

public class EventListenerException extends Exception{
	private static final long serialVersionUID = -5681189601924621774L;
	private Integer objId = -1;
	
	public EventListenerException(String msg, Integer id){
		super(msg);
		this.objId = id;
	}
	
	public Integer getObjId(){
		return this.objId;
	}

}

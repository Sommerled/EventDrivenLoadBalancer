package eventhandler;

public class EventDispatcherException extends Exception{
	private static final long serialVersionUID = 6820921270761063961L; 
	private Integer objId = -1;

	public EventDispatcherException(String msg, Integer objId){
		super(msg);
		this.objId = objId;
	}
	
	public Integer getObjId(){
		return this.objId;
	}
}

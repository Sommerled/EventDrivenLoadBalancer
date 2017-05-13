package eventhandler;

public class IdCompleteEvent extends AbsEvent{
	private Integer payload;
	
	public IdCompleteEvent(Integer payload) {
		super(null, null);
		this.payload = payload;
	}
	
	public Integer getPayload(){
		return this.payload;
	}
}

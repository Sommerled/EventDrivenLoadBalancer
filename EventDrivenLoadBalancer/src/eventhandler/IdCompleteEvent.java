package eventhandler;

import server.AbsWorker;

public class IdCompleteEvent extends AbsEvent{

	public IdCompleteEvent(Integer payload) {
		super(null, payload, null);
	}
	
	@Override
	public Integer getPayload(){
		return (Integer) super.getPayload();
	}
}

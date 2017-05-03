package eventhandler;

import java.util.ArrayList;
import java.util.List;

import server.AbsWorker;

public class SystemMessageEvent extends AbsEvent implements GlobalMessage {
	private List<Integer> processed;

	public SystemMessageEvent(Integer id, SystemMessage payload, AbsWorker originator) {
		super(id, payload, originator);
		processed = new ArrayList<Integer>();
	}

	@Override
	public SystemMessage getPayload(){
		return (SystemMessage) super.getPayload();
	}
	
	@Override
	public void processedId(Integer id) {
		this.processed.add(id);
	}

	@Override
	public int processedSize() {
		return this.processed.size();
	}
}

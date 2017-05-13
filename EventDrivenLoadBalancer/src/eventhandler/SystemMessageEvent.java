package eventhandler;

import java.util.ArrayList;
import java.util.List;
import server.AbsWorker;

public class SystemMessageEvent extends AbsEvent implements GlobalMessage {
	private List<Integer> processed;
	private SystemMessage payload;

	public SystemMessageEvent(Integer id, SystemMessage payload, AbsWorker originator) {
		super(id, originator);
		this.processed = new ArrayList<Integer>();
		this.payload = payload;
	}

	public SystemMessage getPayload(){
		return this.payload;
	}
	
	@Override
	public synchronized void processedId(Integer id){
		this.processed.add(id);
	}

	@Override
	public int processedSize(){
		int size = this.processed.size();
		return size;
	}
	
	@Override
	public void destroy(){
		this.processed = null;
	}
}

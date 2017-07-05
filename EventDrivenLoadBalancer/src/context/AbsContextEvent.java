package context;

import eventhandler.AbsEvent;
import server.AbsWorker;

public abstract class AbsContextEvent extends AbsEvent {
	private ConnectionContext context;

	public AbsContextEvent(Integer id, AbsWorker originator, ConnectionContext context) {
		super(id, originator);
		this.context = context;
	}

	public ConnectionContext getConnectionContext(){
		return this.context;
	}

}

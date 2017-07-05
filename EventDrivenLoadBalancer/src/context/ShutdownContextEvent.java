package context;

import server.AbsWorker;

public class ShutdownContextEvent extends AbsContextEvent{

	public ShutdownContextEvent(Integer id, AbsWorker originator, ConnectionContext context) {
		super(id, originator, context);
	}
}

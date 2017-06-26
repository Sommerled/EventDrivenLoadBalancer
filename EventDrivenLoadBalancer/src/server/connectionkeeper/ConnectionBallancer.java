package server.connectionkeeper;

import context.ConnectionContext;

public interface ConnectionBallancer {
	public ConnectionContext nextConncetion(ConnectionContext c);
	public void registerContext(ConnectionContext c);
}

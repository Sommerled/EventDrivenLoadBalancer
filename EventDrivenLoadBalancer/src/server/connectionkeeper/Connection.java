package server.connectionkeeper;

import context.ConnectionContext;

public interface Connection extends Runnable{
	public ConnectionContext getContext();
}

package server.socketworker;

import java.net.Socket;
import java.util.List;

import eventhandler.AbsEvent;
import eventhandler.EventListener;
import server.AbsEventConsumer;

public class SocketWorker extends AbsEventConsumer {
	private Socket s;

	public SocketWorker(EventListener eventListener, Socket s) {
		super(eventListener);
		this.s = s;
	}

	@Override
	public void idComplete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validEvent(AbsEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AbsEvent> init() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void consume(AbsEvent e) {
		// TODO Auto-generated method stub
		
	}

}

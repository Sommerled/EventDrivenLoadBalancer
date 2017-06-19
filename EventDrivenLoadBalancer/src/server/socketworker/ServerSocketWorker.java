package server.socketworker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import context.ConnectionContext;
import eventhandler.AbsEvent;
import eventhandler.ErrorProne;
import eventhandler.EventDispatcher;
import eventhandler.EventDispatcherException;
import server.LbSocketFactory;
import server.connectionkeeper.ConnectionProducer;
import server.connectionkeeper.EventInitializable;

public class ServerSocketWorker extends ConnectionProducer implements ErrorProne{
	private ServerSocket ss;
	private EventDispatcherException ede = null;

	public ServerSocketWorker(EventDispatcher eventDispatcher, ConnectionContext context) {
		super(eventDispatcher, context);
	}

	@Override
	public void shutdown() {
		super.shutdown();
		this.setConnection(null);
		if(this.ss != null){
			try {
				this.ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void run() {
		SocketWorkerExceptionEvent sweEvent = new SocketWorkerExceptionEvent(this.getId(), this, null, this.getContext());
		
		try {
			this.ss = LbSocketFactory.createServerSocket(this.getContext());
			work();
		} catch (UnrecoverableKeyException e) {
			sweEvent.setException(e);
		} catch (KeyManagementException e) {
			sweEvent.setException(e);
		} catch (KeyStoreException e) {
			sweEvent.setException(e);
		} catch (NoSuchAlgorithmException e) {
			sweEvent.setException(e);
		} catch (CertificateException e) {
			sweEvent.setException(e);
		} catch (IOException e) {
			sweEvent.setException(e);
		} catch (eventhandler.EventDispatcherException e1) {
			ede = (EventDispatcherException) e1;
			return;
		}
		
		if(sweEvent.getException() != null){
			try {
				this.getEventDispatcher().put(sweEvent);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<AbsEvent> produce() throws IOException, InterruptedException {
		Socket socket = null;
		List<AbsEvent>events = new ArrayList<AbsEvent>();
		while(socket == null){
			try {
				socket = this.ss.accept();
				NewSocketEvent nsEvent = new NewSocketEvent(this.getId(), this, socket, this.getContext());
				events.add(nsEvent);
			} catch (IOException e) {
				e.printStackTrace();
				if(this.ss.isClosed()){
					SocketWorkerExceptionEvent sweEvent = new SocketWorkerExceptionEvent(this.getId(), this, e, this.getContext());
					this.getEventDispatcher().put(sweEvent);
					throw new IOException(e);
				}
			}
		}
		return events;
	}

	@Override
	public List<AbsEvent> init() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exception getException() {
		return this.ede;
	}

}

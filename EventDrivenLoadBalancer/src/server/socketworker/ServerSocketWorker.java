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
import eventhandler.EventDispatcher;
import server.AbsEventProducer;
import server.LbSocketFactory;

public class ServerSocketWorker extends AbsEventProducer implements Runnable {
	private ConnectionContext context;
	private ServerSocket ss;

	public ServerSocketWorker(EventDispatcher eventDispatcher, ConnectionContext context) {
		super(eventDispatcher);
		this.context = context;
	}

	@Override
	public void shutdown() {
		super.shutdown();
		this.context = null;
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
		SocketWorkerExceptionEvent sweEvent = new SocketWorkerExceptionEvent(this.getId(), this, null, this.context);
		try {
			this.ss = LbSocketFactory.createServerSocket(this.context);
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
				NewSocketEvent nsEvent = new NewSocketEvent(this.getId(), this, socket, this.context);
				events.add(nsEvent);
			} catch (IOException e) {
				e.printStackTrace();
				if(this.ss.isClosed()){
					SocketWorkerExceptionEvent sweEvent = new SocketWorkerExceptionEvent(this.getId(), this, e, this.context);
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

}

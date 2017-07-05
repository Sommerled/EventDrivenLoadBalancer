package server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import context.ConnectionContext;
import context.ShutdownContextEvent;
import eventhandler.AbsEvent;
import eventhandler.EventListener;
import eventhandler.EventListenerAware;
import eventhandler.SystemMessage;
import eventhandler.SystemMessageEvent;

/***
 * The point of this class is to detect when
 * EventProducers need to shutdown
 *
 */
public class EventProducerMonitor implements Runnable, EventListenerAware{
	private EventListener eventListener = null;
	private Map<ConnectionContext, AbsEventProducer> contexts = null;
	
	public EventProducerMonitor(EventListener eventListener){
		this.contexts = new HashMap<ConnectionContext, AbsEventProducer>();
		this.eventListener = eventListener;
	}
	
	@Override
	public void run() {
		while(true){
			AbsEvent event;
			try {
				event = this.eventListener.peek();
				if(event instanceof ShutdownContextEvent){
					ShutdownContextEvent sce = (ShutdownContextEvent)event;
					if(this.contexts.containsKey(sce.getConnectionContext())){
						shutdownProducer(sce.getConnectionContext());
					}
					this.eventListener.remove(event);
				}else if(event instanceof SystemMessageEvent){
					SystemMessageEvent sme = (SystemMessageEvent) event;
					if(sme.getPayload().equals(SystemMessage.SHUTDOWN)){
						Set<ConnectionContext> keys = this.contexts.keySet();
						Iterator<ConnectionContext> iter = keys.iterator();
						while (iter.hasNext()){
							shutdownProducer(iter.next());
						}
					}
					this.eventListener.remove(event);
				}else if(event instanceof RegisterProducerEvent){
					RegisterProducerEvent rpe = (RegisterProducerEvent)event;
					this.contexts.put(rpe.getConnectionContext(), rpe.getEventProducer());
					this.eventListener.remove(event);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	private void shutdownProducer(ConnectionContext cc){
		AbsEventProducer producer = this.contexts.get(cc);
		producer.shutdown();
		producer = null;
	}

	@Override
	public EventListener getEventListener() {
		return this.eventListener;
	}

	@Override
	public void setEventListener(EventListener eventListener) {
		this.eventListener = eventListener;
	}

}

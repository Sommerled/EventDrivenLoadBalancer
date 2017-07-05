package server;

import context.AbsContextEvent;
import context.ConnectionContext;

public class RegisterProducerEvent extends AbsContextEvent {
	private AbsEventProducer producer = null;
	
	public RegisterProducerEvent(Integer id, AbsWorker originator, ConnectionContext context, 
			AbsEventProducer producer) {
		super(id, originator, context);
		this.producer = producer;
	}

	public AbsEventProducer getEventProducer(){
		return this.producer;
	}
}

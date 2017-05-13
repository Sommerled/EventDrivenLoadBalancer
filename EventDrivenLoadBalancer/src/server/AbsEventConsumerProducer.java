package server;

import java.util.List;

import eventhandler.AbsEvent;
import eventhandler.EventDispatcher;
import eventhandler.IdCompleteEvent;
import eventhandler.SystemMessage;
import eventhandler.SystemMessageEvent;

public abstract class AbsEventConsumerProducer extends AbsWorker{	
	public AbsEventConsumerProducer(EventDispatcher eventDispatcher) {
		super(eventDispatcher);
	}

	@Override
	public void work(){
		EventDispatcher eventDispatcher = this.getEventDispatcher();
		
		while(true){
			AbsEvent e = eventDispatcher.peek();
			if(e instanceof SystemMessageEvent){
				SystemMessageEvent sme = (SystemMessageEvent) e;
				if(sme.getPayload().equals(SystemMessage.SHUTDOWN)){
					shutdown();
					sme.processedId(this.getId());
					IdCompleteEvent ice = new IdCompleteEvent(this.getId());
					
					try {
						eventDispatcher.put(ice);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					eventDispatcher = null;
					this.setId(null);
					break;
				}
			}else{
				if(validEvent(e)){
					try {
						beginProcess(e, eventDispatcher);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
						break;
					}
				}
			}
		}
	}
	
	private void beginProcess(AbsEvent e, EventDispatcher eventDispatcher) throws InterruptedException{
		if(eventDispatcher.remove(e)){
			List<AbsEvent> newEvents = this.process(e);
			if(newEvents != null){
				for(int i = 0; i < newEvents.size(); i ++){
					eventDispatcher.put(newEvents.get(i));
				}
			}
		}
	}
	
	public abstract boolean validEvent(AbsEvent e);
	public abstract List<AbsEvent> process(AbsEvent e);
}

package eventhandler;

public interface EventListenerAware {
	public EventListener getEventListener();
	public void setEventListener(EventListener eventListener);
	
}

package eventhandler;

public interface EventDispatcherAware {
	public EventDispatcher getEventDispatcher();
	public void setEventDispatcher(EventDispatcher eventDispatcher);
}

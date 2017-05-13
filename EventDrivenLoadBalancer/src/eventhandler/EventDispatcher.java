package eventhandler;

public interface EventDispatcher {
	public void put(AbsEvent e) throws InterruptedException;
}

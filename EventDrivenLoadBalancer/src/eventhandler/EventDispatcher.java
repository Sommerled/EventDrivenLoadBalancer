package eventhandler;

public interface EventDispatcher {
	public void put(AbsEvent e) throws InterruptedException;
	public AbsEvent peek();
	public boolean remove(AbsEvent e);
}

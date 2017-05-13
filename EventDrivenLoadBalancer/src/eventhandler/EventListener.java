package eventhandler;

public interface EventListener {
	public AbsEvent peek() throws InterruptedException;
	public boolean remove(AbsEvent e);

}

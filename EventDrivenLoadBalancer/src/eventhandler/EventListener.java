package eventhandler;

public interface EventListener {
	public AbsEvent peek();
	public boolean remove(AbsEvent e);

}

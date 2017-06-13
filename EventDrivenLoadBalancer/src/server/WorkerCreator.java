package server;

public interface WorkerCreator {
	/**
	 * Returns the instance of an AbsWorker
	 * that was constructed using the default,
	 * no parameter, constructor.
	 */
	public AbsWorker getInstance();
}

package context;

public class ConnectionContext {
	private Integer timeout = 0;
	private String host;
	private Integer port;
	private String algorithm;
	private String protocol;
	private boolean listening;
	private ConnectionContext listensFor;
	
	public ConnectionContext(){
		
	}
	
	public ConnectionContext(Integer timeout, String host, Integer port, 
			String algorithm, String protocol, boolean listening, ConnectionContext listensFor){
		this.timeout = timeout;
		this.host = host;
		this.port = port;
		this.algorithm = algorithm;
		this.protocol = protocol;
		this.listensFor = listensFor;
	}
	
	public void setTimeout(Integer timeout){
		this.timeout = timeout;
	}
	
	public Integer getTimeout(){
		return this.timeout;
	}
	
	public void setHost(String host){
		this.host = host;
	}
	
	public String getHost(){
		return this.host;
	}
	
	public void setPort(Integer port){
		this.port = port;
	}
	
	public Integer getPort(){
		return this.port;
	}
	
	public void setAlgorithm(String algorithm){
		this.algorithm = algorithm;
	}
	
	public String getAlgorithm(){
		return this.algorithm;
	}
	
	public void setProtocol(String protocol){
		this.protocol = protocol;
	}
	
	public String getProtocol(){
		return this.protocol;
	}
	
	public void setListening(boolean listening){
		this.listening = listening;
	}
	
	public boolean getListening(){
		return this.listening;
	}
	
	public void setListensFor(ConnectionContext listensFor){
		this.listensFor = listensFor;
	}
	
	public ConnectionContext getListensFor(){
		return this.listensFor;
	}
}
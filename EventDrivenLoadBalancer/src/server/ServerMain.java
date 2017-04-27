package server;

import java.util.List;

import context.ConnectionContext;
import context.ContextLoader;

public class ServerMain {

	public static void main(String[] args) {
		String configFile = "";
		
		if(args.length > 0){
			configFile = args[0];
		}else{
			configFile = System.getProperty("user.home") + "\\AppData\\Roaming\\EDLB\\Connections.xml";
		}
		
		try {
			ContextLoader.loadContexts(configFile);
			List<ConnectionContext> contexts = ContextLoader.getLoadedContexts();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

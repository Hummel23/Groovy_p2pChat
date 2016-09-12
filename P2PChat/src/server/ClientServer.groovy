package server

import org.glassfish.jersey.server.ResourceConfig

class ClientServer {
	
	def startClientServer() {
		
		GrizzlyHttpServerFactory.createHttpServer(
			"http://localhost:8080".toURI(),
			new ResourceConfig(Core.class));
		
	}

}

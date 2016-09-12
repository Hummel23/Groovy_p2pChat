package server

import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.server.ResourceConfig

class ClientServer {
	
	def startClientServer() {
		
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
			"http://141.45.206.251:8080".toURI(),
			new ResourceConfig(Root.class));
//		System.in.read()
//		server.stop()
	}

}

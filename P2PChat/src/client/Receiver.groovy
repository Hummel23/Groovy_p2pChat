package client

import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.server.ResourceConfig

/**
 * acts as client-side server
 */
class Receiver {

	def name
	def inetAddr 
	def server

	public Receiver(String name, String inetAddr) {
		this.name = name
		this.inetAddr = inetAddr
		startClientServer()
	}

	public void receiveMessage(Message msg) {
		
	}

	def startClientServer() {
		this.server = GrizzlyHttpServerFactory.createHttpServer(
			"http://${this.inetAddr}:8080".toURI(),
			new ResourceConfig(Chat.class));
		println "startet local server..."
	}
	
	def stopClientServer() {
		this.server.stop()
	}

}

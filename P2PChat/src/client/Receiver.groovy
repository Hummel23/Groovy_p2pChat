package client

import groovy.json.JsonSlurper
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.server.ResourceConfig

/**
 * acts as client-side server
 */
@Singleton
class Receiver {

	def name
	def inetAddr 
	HttpServer server
	String lastSender

	def startClientServer() {
		this.server = GrizzlyHttpServerFactory.createHttpServer(
			"http://${this.inetAddr}:8080".toURI(), new ResourceConfig(Chat.class))
//		println "startet local server..."
	}
	
	def stopClientServer() {
		this.server.stop()
	}

}

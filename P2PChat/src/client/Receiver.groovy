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


	public void receiveMessage(def msg) {
		def json = new JsonSlurper().parseText(msg)		
		String sender = json['sender']
		String content = json['content']
		
		println """
	°°°°°°°°°°°°°°°°°°°°°°°°°
	von ${sender.toUpperCase()}:
	-------------------------
	${content}

	°°°°°°°°°°°°°°°°°°°°°°°°°"""
	}

	def startClientServer() {
		println "this.inetAddr = " + this.inetAddr
		this.server = GrizzlyHttpServerFactory.createHttpServer(
			"http://${this.inetAddr}:8080".toURI(), new ResourceConfig(Chat.class, Root.class))
		println "startet local server..."
	}
	
	def stopClientServer() {
		this.server.stop()
	}

}



package client

import client.services.TransportService;
import groovyx.net.http.RESTClient

class Main {

	static main(args) {

		TransportService transportService = new TransportService()
		//call to server
		transportService.connectToUserServer("141.45.206:8080")

		ClientServer cl = new ClientServer()
		cl.startClientServer()
		println "startet local server..."
			
		//read input from console
		def br = new BufferedReader(new InputStreamReader(System.in))
		println "Please enter your username: "
		def userName = br.readLine()
		println "Welcome to MESSAS $userName!"
	}

}

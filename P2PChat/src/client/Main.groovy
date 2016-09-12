package client

import client.services.TransportService;
import groovyx.net.http.RESTClient
import server.ClientServer

class Main {

	static main(args) {

		//call to server
		TransportService transportService = new TransportService()
//		transportService.connectToUserServer("141.45.206.251")

		ClientServer cl = new ClientServer()
		cl.startClientServer()
		println "startet local server..."
			
		//read input from console
		def br = new BufferedReader(new InputStreamReader(System.in))
		
		println "Please enter your username: "
		def userName = br.readLine()
		println "Welcome to MESSAS $userName!"
		//get onlineUserList
		//find own IP and create sender-obj
		Sender sender = new Sender(userName) //Singleton
		//println "Please choose friend: "
		//create receiver from input & onlineUserList
		Receiver rec = new Receiver("saba")
		
		println "Please enter your message: "
		def textMessage = br.readLine()
		TextMessage msg = new TextMessage(textMessage, sender, rec)
		println msg
		
//		transportService.sendMessage(msg)
		
	}

}

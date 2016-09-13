package client

import client.services.TransportService;
import groovyx.net.http.RESTClient

class Main {

	static main(args) {
				//call to server
//				TransportService transportService = new TransportService()
		//		transportService.connectToUserServer("141.45.206.251")
						
				//read input from console
				def br = new BufferedReader(new InputStreamReader(System.in))
				
				println "Please enter your username: "
				def userName = br.readLine()
				//login at server
				//get onlineUserList
				def inetAddr = "141.45.211.14"
				//create sender & receiver & start local server
				def messenger = new Messenger(name: userName, inetAddr:inetAddr)	
				println "Welcome to MESSAS $userName!"
				
				//find own IP and create sender-obj
				//println "Please choose friend: "
				//create receiver from input & onlineUserList
				
				println "Enter name of chat partner: "
				def chatPartnerName = br.readLine()
				def chatPartnerIP = "141.45.211.14"
				println "Please enter your message: "
				
				def content = br.readLine()
				// on ENTER send message
				def msg = new TextMessage(content, messenger.sender, chatPartnerName)
				messenger.sender.sendMessage(msg)
				
						
			}
	
}

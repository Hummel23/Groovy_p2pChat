package client

import client.services.TransportService;
import groovyx.net.http.RESTClient

import javax.ws.rs.core.GenericType
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response;


static main(args) {
	//call to server
	//				TransportService transportService = new TransportService()
	//		transportService.connectToUserServer("141.45.206.251")

	//read input from console
	//				def br = new BufferedReader(new InputStreamReader(System.in))
	//
	//				println "Please enter your username: "
	//				def userName = br.readLine()


	//create sender & receiver & start local server
	def messenger = Messenger.instance
	messenger.greeting()

	def br = new BufferedReader(new InputStreamReader(System.in))

	while(!messenger.isOnline){
		def command = br.readLine()
		if(command == "login"){
			messenger.login()
		}
		else {
			println messenger.name+ ",please enter <login>, if you want to chat. "
		}
	}


	while(messenger.isOnline){
		def command = br.readLine().trim().toLowerCase()
		boolean commandIsCorrect=false
		
		while(!commandIsCorrect){
			println "command valid:" + (command == 'help')
			if(messenger.isValidEntry(command)){

				messenger.executeUserEntry(command)
				commandIsCorrect=true
			}else{
				println "The command you entered is incorrect. Please try again."
				println messenger.commands
			}
		}
	}

}


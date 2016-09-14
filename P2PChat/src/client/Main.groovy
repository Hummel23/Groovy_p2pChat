package client

import client.services.TransportService;
import groovyx.net.http.RESTClient

import javax.ws.rs.core.GenericType
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response;


static main(args) {

	def messenger = Messenger.instance
	messenger.greeting()

	def br = new BufferedReader(new InputStreamReader(System.in))
	
	while(!messenger.isOnline){
		messenger.name = br.readLine()
		messenger.login()
	}


	while(messenger.isOnline){
		def command = br.readLine().trim().toLowerCase()
		boolean commandIsCorrect=false
		
		while(!commandIsCorrect){
			if(messenger.isValidEntry(command)){

				messenger.executeUserEntry(command)
				commandIsCorrect=true
			}else{
				println "The command you entered is incorrect. Please try again."
				println messenger.commands
				command = br.readLine().trim().toLowerCase()
			}
		}
	}
}


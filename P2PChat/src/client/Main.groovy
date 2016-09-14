package client

import client.services.ValidationService


static main(args) {

	def messenger = Messenger.instance
	messenger.greeting()

	def br = new BufferedReader(new InputStreamReader(System.in))
	
	while(!messenger.isOnline){
		messenger.login()
	}


	while(messenger.isOnline){
		def command = br.readLine().trim().toLowerCase()
		
		boolean commandIsCorrect=false
		
		while(!commandIsCorrect){
			if(ValidationService.instance.isValidEntry(command)){

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


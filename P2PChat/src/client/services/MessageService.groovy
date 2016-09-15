package client.services

import groovy.json.JsonBuilder

@Singleton
class MessageService {
	
	Map<String,Set> messages = [:]
	
	public def convertToJSON(def obj) {
		def json = new JsonBuilder(obj)
		.toPrettyString()
	}
	
	public void storeMessage(def msg) {
		String name = msg.sender
		Set usersMessages = messages.name
		usersMessages.
		
		println name
		println messages.size()
	}

}

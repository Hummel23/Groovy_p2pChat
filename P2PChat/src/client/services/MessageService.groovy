package client.services

import groovy.json.JsonBuilder

@Singleton
class MessageService {
	
//	Map<String,List> messages = [:]
	
	public def convertToJSON(def obj) {
		def json = new JsonBuilder(obj)
		.toPrettyString()
	}
	
	//TODO
	public void storeMessage(def msg) {
		String name = msg.sender
		Set usersMessages = messages.name		
		println name
		println messages.size()
	}

}

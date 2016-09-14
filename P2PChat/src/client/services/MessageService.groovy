package client.services

import groovy.json.JsonBuilder

@Singleton
class MessageService {
	
	public def convertToJSON(def obj) {
		def json = new JsonBuilder(obj)
		.toPrettyString()
	}

}

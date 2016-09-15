package client

import groovy.json.JsonSlurper

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces

/**
 * 
 */
@Path('/')
class Root {
	
	@GET
	public String startServer() {
		return "client server up..."
	}
}

@Path('/{message}')
class Chat {
	
	@GET
//	@Produces([MediaType.APPLICATION_JSON])
	public void getTextMessage(
		@PathParam("message") String message) {
		def json = new JsonSlurper().parseText(message)
		println messageToString(json)
	}
		
	String messageToString(def message) {
		return """
		============================
		${message.sender.toUpperCase()}:
		----------------------------
		${message.content}
		============================"""
	}
}



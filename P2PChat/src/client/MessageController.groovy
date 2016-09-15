package client

import groovy.json.JsonBuilder

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

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
	@Produces([MediaType.APPLICATION_JSON])
	public void getTextMessage(
		@PathParam("message") String message) {
		println message
		new JsonBuilder(message)
		.toPrettyString()
	}
}



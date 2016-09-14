package client

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

/**
 * 
 */
@Path('/')
class Root {
	
	@GET
	public String startServer() {
		return "(0)client server up..."
	}
}

@Path('/{message}')
class Chat {
	
	@GET
	public void getTextMessage(
		@PathParam("message") String message) {

	}
}



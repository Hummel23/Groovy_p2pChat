package client

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

/**
 * 
 */
@Path('/{message}')
class Chat {
	
	@GET
	public String getTextMessage(
		@PathParam("message") String message) {
		return message
	}
}


//@Path('/{}')
//class Chat {
//	@GET
//	@Produces([MediaType.APPLICATION_JSON])
//	def resource() {
//		new JsonBuilder("Hello from other Client")
//				.toPrettyString()
//	}
//}

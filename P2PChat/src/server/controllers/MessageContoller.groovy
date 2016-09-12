package server.controllers



import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import client.Message

@Path('/msg')


public class MessageController {
	
	// Service
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	def receiveMessage(Message msg){
		
	}
}

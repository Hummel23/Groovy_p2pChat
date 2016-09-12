package client.controllers

import groovy.json.JsonBuilder

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path('/')
class Core {
	@GET
	@Path('/resource')
	@Produces([MediaType.APPLICATION_JSON])
	def resource() {
		new JsonBuilder("Hello from other Client")
				.toPrettyString()
	}
}

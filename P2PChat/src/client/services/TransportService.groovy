package client.services

import groovyx.net.http.RESTClient

import javax.ws.rs.PathParam



boolean connectToUserServer(@PathParam ("inetAddr")def inetAddr) {
	
	RESTClient client = new RESTClient('http://${inetAddr}:8080/')
	def response = client.get(path: '/resource')
	println response.data
	assert response.status == 200
//		assert response.data == 'Hello Server'
	
}





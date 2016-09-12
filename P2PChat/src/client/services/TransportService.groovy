package client.services

import client.Message
import groovyx.net.http.RESTClient

import javax.ws.rs.PathParam



boolean connectToUserServer(@PathParam ("inetAddr")def inetAddr) {
	
	RESTClient client = new RESTClient('http://${inetAddr}:8080/')
	def response = client.get(path: '/resource')
	println response.data
	assert response.status == 200
//		assert response.data == 'Hello Server'
	
}

void sendMessage(Message msg) {
	
	def http = new HTTPBuilder( 'http://${msg.receiver.}' )
	def postBody = [name: 'bob', title: 'construction worker'] // will be url-encoded
	
	http.post( path: '/', body: postBody,
			   requestContentType: URLENC ) { resp ->
	
	  println "POST Success: ${resp.statusLine}"
	  assert resp.statusLine.statusCode == 201
	}
}





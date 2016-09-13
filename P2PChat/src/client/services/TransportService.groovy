package client.services

import groovyx.net.http.RESTClient

/**
 * connect to external server for authorization
 */
class TransportService {

	static final String USER_SERVER_IP = "141.45.206.251"
	def messages = []
	def sender, receiver

	boolean connectToUserServer(def inetAddr) {
		RESTClient client = new RESTClient("http://${USER_SERVER_IP}:8080")
		def response = client.get(path: '/resource')
		println response.data
		assert response.status == 200
		//		assert response.data == 'Hello Server'
	}

}





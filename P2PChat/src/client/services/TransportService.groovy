package client.services

import groovyx.net.http.RESTClient
import client.InetAddr

/**
 * connect to external server for authorization
 */
@Singleton
class TransportService {

	def messages = []
	def sender, receiver

	boolean loginToUserServer() {
		RESTClient client = new RESTClient("http://${InetAddr.ServerInetAddr}:8080")
		def response = client.get(path: '/login')
//		println response.data
//		assert response.status == 200
		//		assert response.data == 'Hello Server'
	}

}





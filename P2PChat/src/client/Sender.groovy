package client

import groovyx.net.http.RESTClient


@Singleton
class Sender {

	def name
	RESTClient client

	public void sendMessage(String msg) {
//		def ip = getChatPartnerIP(msg.chatPartnerID)
		def ip = InetAddr.getChatPartnerInetAddr()
		client = new RESTClient("http://${ip}:8080/")
		def response = client.get(path: "/${msg}")
//		assert response.status == 200
//		assert response.data == 'Hello Server'
//		println response.data.toString()
		
		
//		def http = new HTTPBuilder( "http://${msg.receiver.inetAddr}:8080" )
//		def postBody = [message: msg] // will be url-encoded
//
//		http.post( path: '/msg', body: postBody,
//		requestContentType: MediaType.APPLICATION_JSON ) { resp -> 	//oder URLENC statt MediaType.APPLICATION_JSON ???
//			println "POST Success: ${resp.statusLine}"
//			assert resp.statusLine.statusCode == 201	//201 = Created
//		}		
	}
	
//	public String getChatPartnerIP(String id){
//		return "141.45.206.251"
//	}
}

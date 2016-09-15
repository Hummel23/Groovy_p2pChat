package client

import groovyx.net.http.RESTClient
import client.services.MessageService

@Singleton
class Sender {

	def name
	RESTClient client
	def messageService = MessageService.instance

	public void sendMessage(String msg) {
//		def ip = getChatPartnerIP(msg.chatPartnerID)
		def ip = InetAddr.ChatPartnerInetAddr
		println "ip: $ip"
		Message messageObject = new TextMessage(msg, this.name, "chatPartnerID")
		def msgJson = messageService.convertToJSON(messageObject)	
		println "JSON: "+msgJson
		client = new RESTClient("http://${ip}:8080/")
		def response = client.get(path: "/${msgJson}")
		println "resp: "+ response.data
		println "status: "+ response.status
		
		//-----
//		@Produces([MediaType.APPLICATION_JSON])
//		def getList(){
//			def list = Login.userListe
//			return new JsonBuilder(list)
//					.toPrettyString()
//		}

		//-----
//		http.request(POST) {
//			uri.path = 'http://example.com/handler.php'
//			body = [name: 'bob', title: 'construction worker']
//			requestContentType = ContentType.JSON
//		
//			response.success = { resp ->
//				println "Success! ${resp.status}"
//			}
//		
//			response.failure = { resp ->
//				println "Request failed with status ${resp.status}"
//			}
//		}
		
		
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

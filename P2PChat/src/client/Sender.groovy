package client

import groovyx.net.http.RESTClient
import client.services.MessageService

@Singleton
class Sender {

	def name
	RESTClient client
	def messageService = MessageService.instance

	public void sendMessage(String msg, String chatPartnerIp, String chatPartnerName) {
//		def ip = getChatPartnerIP(msg.chatPartnerID)
//		def ip = InetAddr.ChatPartnerInetAddr
//		println "ip: $ip"
		Message messageObject = new TextMessage(msg, this.name, chatPartnerName)
		def msgJson = messageService.convertToJSON(messageObject)	
		client = new RESTClient("http://${ip}:8080/")
		def response = client.get(path: "/${msgJson}")
		if(response.status == 204) {	//successful, but no returned content
			println ">>erfolgreich gesendet<<"
		}
		else {
			println ">>diese Nachricht ist im Nirvana verschwunden<<"
		}
		
//		MessageService.instance.storeMessage(messageObject)
		//TODO: notify user if message could not be sent.	
	}
		
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
	
//	public String getChatPartnerIP(String id){
//		return "141.45.206.251"
//	}
}

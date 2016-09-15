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
		client = new RESTClient("http://${ip}:8080/")
		def response = client.get(path: "/${msgJson}")	
		//TODO: notify user if message could not be sent.	
	}
	
//	public String getChatPartnerIP(String id){
//		return "141.45.206.251"
//	}
}

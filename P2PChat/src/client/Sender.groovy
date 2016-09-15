package client

import groovyx.net.http.RESTClient
import client.services.MessageService

@Singleton
class Sender {

	def name
	RESTClient client
	def messageService = MessageService.instance

	public void sendMessage(String msg, String chatPartnerIp, String chatPartnerName) {
		try {
			Message messageObject = new TextMessage(msg, this.name, chatPartnerName)
			def msgJson = messageService.convertToJSON(messageObject)
			client = new RESTClient("http://${chatPartnerIp}:8080/")
			def response = client.get(path: "/${msgJson}")
			if(response.status == 204) {		//204: successful, but no returned content
				println ">> sent successfully <<"
			}
		} catch (Exception e) {
			println ">> Ooops...this message got lost in space...\n${chatPartnerName.toUpperCase()} might have lost connection. <<\n"
		}

		// TODO: messageService.storeMessage(messageObject)
	}

}

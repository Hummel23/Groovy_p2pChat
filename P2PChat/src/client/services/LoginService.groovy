package client.services

import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder

class LoginService {

	def login(String name) {
		
				def http = new HTTPBuilder(ClientServer.CLIENT_SERVER_IP)
				http.get(path: '/login') { resp ->
					def jsonSlurper = new JsonSlurper()
					def content = resp.entity.content.text
					println content
				}
				
				
//				def postBody = [mail: mail, password: password] // will be url-encoded
//		
//				boolean result = false
//		
//				
//					http.post(path: '/login', body: postBody,
//							requestContentType: MediaType.APPLICATION_JSON) { resp ->
//						if (resp.statusLine.statusCode == 200) {
//							MessageStore.getMessageStore().ownerMail = mail
//							result = true
//						}
//					}
		

				
	}
		
}


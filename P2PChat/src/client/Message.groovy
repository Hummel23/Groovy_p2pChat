package client

import groovy.transform.ToString

@ToString(includeNames=true)
abstract class Message {
	
	def content, sender, chatPartnerID
	def date = new Date()
		
}

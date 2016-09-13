package client

import groovy.transform.ToString

@ToString(includeNames=true)
abstract class Message {
	
	def content, sender, chatPartner
	def date = new Date()
		
}

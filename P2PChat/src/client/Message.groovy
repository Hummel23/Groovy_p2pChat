package client

import groovy.transform.ToString


abstract class Message {
	
	def content, sender, chatPartnerID
	def date = new Date()
	
}

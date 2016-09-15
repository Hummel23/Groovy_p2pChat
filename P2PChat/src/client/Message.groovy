package client

abstract class Message {
	
	def content, sender, chatPartnerID
	def date = new Date()
	
}

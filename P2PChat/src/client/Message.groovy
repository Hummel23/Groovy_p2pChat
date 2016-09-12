package client

abstract class Message {
	
	def content, sender, receiver
	def date = new Date()
		
}

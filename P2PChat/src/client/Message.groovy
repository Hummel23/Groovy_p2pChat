package client

abstract class Message {
	
	def content, sender, date, receiver
	
	public Message(Sender sender, Receiver receiver) {
		this.date = new Date()
		this.sender = sender
		this.receiver = receiver
	}
		
}

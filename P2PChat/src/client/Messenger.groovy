package client

@Singleton
class Messenger {
	
	def name
	def inetAddr
	Sender sender = new Sender(name)
	Receiver rec = new Receiver (name, inetAddr)

}

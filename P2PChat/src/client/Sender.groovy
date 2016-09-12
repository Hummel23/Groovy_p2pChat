package client

class Sender implements ISender {

	def name
	def inetAddr = "141.45.206.251"

	public Sender(String name) {
		this.name = name
	}
}

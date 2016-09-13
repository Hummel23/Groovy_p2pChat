package client

class Messenger {

	Sender sender
	Receiver receiver
	
	public Messenger(String name, String inetAddr){
		this.sender = new Sender(name)
		this.receiver = new Receiver (name, inetAddr)
	}

}

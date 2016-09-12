package client

class Receiver implements IReceiver {
	
	def name
	def inetAddr = "145.45.211.14"
	
	public Receiver(String name) {
		this.name = name
	}
	
	@Override
	public void receive() {
		// TODO Auto-generated method stub
		
	}
	
}

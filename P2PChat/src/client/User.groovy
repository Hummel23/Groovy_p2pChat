package client

class User {
	
	def name, email
	def ipAddress = findIPAddress()
	
	/**
	 * Get users IP-address
	 */
	String findIPAddress(){
		try {
			def hostAddr = InetAddress.getLocalHost().getHostAddress()
			def hostName = InetAddress.localHost.hostName
		
		}
		catch (UnknownHostException e) {
			println "Host not found: " + e.getMessage()
		}
	}
}

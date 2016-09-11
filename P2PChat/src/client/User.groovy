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
			return hostAddr
		}
		catch (UnknownHostException e) {
			println "Host not found: " + e.getMessage()
		}
	}
}

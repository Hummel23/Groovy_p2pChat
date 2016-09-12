package client

import groovyx.net.http.RESTClient

class Main {
	
	static main(args) {
		
		//call to server
		connectToUserServer("141.45.206:8080")
		
		//read input from console
		def br = new BufferedReader(new InputStreamReader(System.in))
		println "Please enter your username: "
		def userName = br.readLine()
		println "Welcome to MESSAS $userName!"
	}

}

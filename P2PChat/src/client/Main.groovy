package client

import groovyx.net.http.RESTClient

class Main {
	
	static main(args) {
		
		//call to server
		RESTClient client = new RESTClient('http://141.45.211.14:8080/')
		def response = client.get(path: '/resource')
		assert response.status == 200
		assert response.data == 'Hello Server'
		
		//read input from console
		def br = new BufferedReader(new InputStreamReader(System.in))
		println "Please enter your username: "
		def userName = br.readLine()
		println "Welcome to MESSAS $userName!"
	}

}

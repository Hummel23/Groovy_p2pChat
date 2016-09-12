package client

import groovyx.net.http.RESTClient

class Main {
	
	static main(args) {
		
		def zahl = 1
//		List list = new ArrayList<String>()
//		list.add("Silvia")
//		list.add("Elif")
//		list.add("Maryna")
		
		
		//call to server
		RESTClient client = new RESTClient('http://141.45.211.14:8080/')
		def response = client.get(path: '/resource')
		assert response.status == 200
		assert response.data == 'Hello Server'
		
		//read input from console
		def br = new BufferedReader(new InputStreamReader(System.in))
		println "Welcome to MESSAS-Chat! \nPlease enter your nickname: "
		def userName = br.readLine()
		println "Please enter your password: "
		def userPwd = br.readLine()     //todo: hide password
		println "Hello " + userName + "! You are now in your personally chatroom. "
		
		
		
		if(zahl == 1){
			println "These friends are available:\n With wich friend do you want to chat? \n (0)logout" 
		}
		else{
			println "I´m sorry, it´s seems that all your friends are busy :( \n (0)logout"
		}
		
		def eingabe = br.readLine()
		
		if(eingabe == "0"){
			println "Bye Bye"
		}
	}

}

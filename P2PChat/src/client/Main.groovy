package client

import groovyx.net.http.RESTClient

import javax.ws.rs.core.GenericType
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response;

class Main {

	static main(args) {

		boolean isLoggedIn = false;
		def zahl = 1
		//		List list = new ArrayList<String>()
		//		list.add("Silvia")
		//		list.add("Elif")
		//		list.add("Maryna")


		//call to server
		RESTClient client = new RESTClient('http://192.168.178.26:8080/')
		def response = client.get(path: '/resource')
		assert response.status == 200
		assert response.data == 'Hello Server'

		def br = new BufferedReader(new InputStreamReader(System.in))

		println "Please enter your nickname: "
		def userName = br.readLine()
		def responseLogin = client.get(path: '/login', query:[
			'name': userName]);

		println responseLogin.data

		//		def responseListOfUser = client.get(path: '/list')
		//		List<User> listOfUser = responseListOfUser.data;
		//		for (user in listOfUser) {
		//			System.out.println(user.name + " " + user.ip)
		//		}
		//		System.out.println("Press any key to close")
		//		System.in.read()



		//		//read input from console
		//		def br = new BufferedReader(new InputStreamReader(System.in))
		//		println "Welcome to MESSAS-Chat!"
		//		def userName = ""
		//
		//
		//
		//		final String keyOfMap = "username"
		//
		//			println "Please enter your nickname: "
		//			userName = br.readLine()
		//			//mapOfUser.put(keyOfMap, userName)
		//			//isLoggedIn = client.put(mapOfUser)
		//			println "The nickname " + userName + " is already in use."
		//
		//		//println "Please enter your password: "
		//		//def userPwd = br.readLine()     //todo: hide password
		//		println "Hello " + userName + "! You are now in your personally chatroom. "
		//
		//
		//
		//		if(zahl == 1){
		//			println "These friends are available:\n With wich friend do you want to chat? \n (0)logout"
		//		}
		//		else{
		//			println "I´m sorry, it´s seems that all your friends are busy :( \n (0)logout"
		//		}
		//
		//		def eingabe = br.readLine()
		//
		//		if(eingabe == "0"){
		//			println "Bye Bye"
		//		}
	}

}

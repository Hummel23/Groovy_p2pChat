package client

class Main {
	
	static main(args) {
		def actualUser = new User()
		println "Your IP-address is: ${actualUser.ipAddress}"
		
		//read input from console
		def br = new BufferedReader(new InputStreamReader(System.in))
		println "Please enter your username: "
		def userName = br.readLine()
		actualUser.name = userName
		println "Welcome to BChatty $actualUser.name!"
	}

}

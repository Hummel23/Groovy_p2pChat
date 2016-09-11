package client

class Main {
	
	static main(args) {
		
		//read input from console
		def br = new BufferedReader(new InputStreamReader(System.in))
		println "Please enter your username: "
		def userName = br.readLine()
		User.name = userName
		println "Welcome to BChatty $User.name!"
	}

}

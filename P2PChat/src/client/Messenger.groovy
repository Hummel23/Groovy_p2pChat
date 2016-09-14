package client

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

@Singleton
class Messenger {

	Sender sender
//	 = sender.instance
	Receiver receiver
//	 = receiver.instance
	boolean isWriting = false
	def name
	def commands = """
			**********************************************
				List of possible Commands:
				--------------------------
				list : show a list of all online users
				chat : write a message
				exit : exit chat program
				help : show commands

			**********************************************"""

	boolean isOnline = false


	public void greeting(){
		def br = new BufferedReader(new InputStreamReader(System.in))
		
		println "Please enter your username: "
		this.name = br.readLine()
		//TODO: validateName()
		//		final String keyOfMap = "username"
		//
		//			println "Please enter your nickname: "
		//			userName = br.readLine()
		//			//mapOfUser.put(keyOfMap, userName)
		//			//isLoggedIn = client.put(mapOfUser)
		//			println "The nickname " + userName + " is already in use."
		
		println "Welcome to MESSAS ${this.name} Please type <login>, if you want to login. "

	}

	public void login(){

		def inetAddr = InetAddr.InetAddr	
//		def inetAddr = addUserToServer()	//TODO: change to this!!!!
		//TODO get onlineUserList
		//TODO start local server
		receiver = Receiver.instance
		receiver.name = name
		this.receiver.inetAddr = inetAddr
		this.receiver.startClientServer()
		sender = Sender.instance
		this.sender.name = name
//		println "Got users inetAddr: ${addUserToServer()}"	//TEST
		isOnline = true
		println commands
		//TODO wait for command or incoming message
	}
	
	public String addUserToServer() {
		def responseLogin = sender.instance.client.get(path: '/login', query:[
			'name': this.name]);

		return responseLogin.data
	}

	public void chat(){
		def br = new BufferedReader(new InputStreamReader(System.in))
		println "Enter name of chat partner: "
//		def chatPartnerID = validateChatPartnerName()
//		def chatPartnerInetAddr = findInetAddr(chatPartnerID)
		def chatPartnerInetAddr = InetAddr.ChatPartnerInetAddr
		//			getListFromServer()
		//			getIPFromList()
		println "Enter message: "
		def msg = br.readLine()
		Message messageObject = new TextMessage(msg, sender.instance.name, "chatPartnerID")
		def msgJson = convertToJSON(messageObject)
		sender.instance.sendMessage(msgJson)
		println "Please type a new command before continuing."
	}
	
	public def convertToJSON(def obj) {
		def json = new JsonBuilder(obj)
		.toPrettyString()
	}
	
	public void getOnlineUsers() {
		def responseListOfUser = sender.client.get(path: '/list')
		List<User> listOfUser = responseListOfUser.data;
		for (user in listOfUser) {
			System.out.println(user.name + " " + user.ip)
		}
	}

	
	public String validateChatPartnerName(){
		def br = new BufferedReader(new InputStreamReader(System.in))
		boolean nameIsCorrect=false
		def chatPartnerName
		
		while(!nameIsCorrect){
			chatPartnerName = br.readLine().trim().toLowerCase()
			if (!chatPartnerName.isEmpty()){
				if (isInList(chatPartnerName)){
					nameIsCorrect=true
				}
			}
			else{
				println chatPartnerName " does not exist. Please chose another user."
			}
		}
		return chatPartnerName
	}

	public boolean isInList(String name){
		return true
	}

	boolean isValidEntry(String val){
		(val == 'exit' ||val == 'list' || val == 'chat' || val == 'help') ? true : false
	}

	void executeUserEntry(String val) {

		if(val == 'exit') {
			logout()
		}
		else if(val == 'chat'){
			chat()
		}
		else if(val == 'list'){
			//			getOnlineUsers()
		}
		else if(val == 'help'){
			println commands
		}
	}

	public void logout(){
		//TODO logout from userServer by removing user from list
		this.receiver.stopClientServer()
		isOnline=false
		println "Thanks for using MESSAS. We are looking forward to seeing you again soon!"
	}



}

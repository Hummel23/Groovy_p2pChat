package client

import groovy.json.JsonSlurper

@Singleton
class Messenger {

	Sender sender
	Receiver receiver 
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
		println "Welcome to MESSAS ${this.name} Please type <login>, if you want to login. "

	}

	public void login(){

		//TODO login at userserver
		//TODO get own IP-address from Server
		def inetAddr = InetAddr.InetAddr
		//TODO get onlineUserList
		//TODO start local server
		receiver = Receiver.instance
		receiver.name = name
		receiver.inetAddr = inetAddr
		receiver.startClientServer()
		sender = Sender.instance
		sender.name = name
		isOnline = true
		println commands
		//TODO wait for command or incoming message
	}

	public void chat(){
		println "Enter name of chat partner: "
		def chatPartnerID = validateChatPartnerName()
//		def chatPartnerInetAddr = findInetAddr(chatPartnerID)
		def chatPartnerInetAddr = InetAddr.ChatPartnerInetAddr
		
		




		//			getListFromServer()
		//			getIPFromList()
		println "Enter message: "
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
	
	//TODO implement findInetAddr (-> put in UserService)
//	public String findInetAddr(String name){
//		def jsonText = '''
//{
//    "message": {
//        "header": {
//            "from": "mrhaki",
//            "to": ["Groovy Users", "Java Users"]
//        },
//        "body": "Check out Groovy's gr8 JSON support."
//    }
//}      
//'''
//		 
//		def json = new JsonSlurper().parseText(jsonText)
//		 
//		def header = json.message.header
//		assert header.from == 'mrhaki'
//		assert header.to[0] == 'Groovy Users'
//		assert header.to[1] == 'Java Users'
//		assert json.message.body == "Check out Groovy's gr8 JSON support."
//	}

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
		receiver.instance.stopClientServer()
		isOnline=false
		println "Thanks for using MESSAS. We are looking forward to seeing you again soon!"
	}



}

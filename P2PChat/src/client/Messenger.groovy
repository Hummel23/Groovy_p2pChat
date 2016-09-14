package client

import client.services.TransportService
import client.services.UserService

@Singleton
class Messenger {

	Sender sender
	Receiver receiver
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

			**********************************************
Please choose a command or wait for messages and be happy: 
		"""

	boolean isOnline = false


	public void greeting(){
		println "Welcome to MESSAS! Please type your name if you want to log in."

	}

	public void login(){
		def br = new BufferedReader(new InputStreamReader(System.in))
		this.name = br.readLine().toLowerCase()
		receiver = Receiver.instance	
		sender = Sender.instance
		
//		this.name =  br.readLine()
		
		boolean userAdded = false
		while(!userAdded){
			def login = UserService.instance.addUserToServer(this.name)
			println "returned from userserver " + login
			if(login == ""){
				println "This name is already in use. Please choose another name."
				this.name = br.readLine().toLowerCase()
				
			}else{
				println "name to be saved:"+ name
				this.name = name
				this.receiver.name = name
				this.sender.name = name
				receiver.inetAddr = login
				println login
				userAdded = true
			}
		}
		this.receiver.startClientServer()
		
//		def inetAddr = InetAddr.InetAddr
		//		def inetAddr = addUserToServer()	//TODO: change to this!!!!
		//TODO get onlineUserList
		//TODO start local server
		
		//		println "Got users inetAddr: ${addUserToServer()}"	//TEST
		isOnline = true
		println commands
		//TODO wait for command or incoming message
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



	public void getOnlineUsers() {
		def responseListOfUser = sender.client.get(path: '/list')
		//		List<User> listOfUser = responseListOfUser.data;
		//		for (user in listOfUser) {
		//			System.out.println(user.name + " " + user.ip)
		//		}
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
		UserService.instance.removeUserFromServer()
		this.receiver.stopClientServer()
		isOnline=false
		println "Thanks for using MESSAS. We are looking forward to seeing you again soon!"
	}



}

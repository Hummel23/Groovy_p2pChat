package client


import client.services.TransportService
import client.services.UserService
import groovyx.net.http.RESTClient
import client.services.MessageService

@Singleton
class Messenger {

	Sender sender
	Receiver receiver
	boolean isWriting = false
	boolean isOnline = false

	def messageService = MessageService.instance
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


	public void greeting(){
		println "Welcome to MESSAS! Please type your name if you want to log in."

	}

	public void login(){
		def br = new BufferedReader(new InputStreamReader(System.in))
		this.name = br.readLine().toLowerCase()
		receiver = Receiver.instance
		sender = Sender.instance
		sender = Sender.instance
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
		//		println "Got users inetAddr: ${addUserToServer()}"	//TEST
		isOnline = true
		println commands
		//TODO wait for command or incoming message

	}

	public void chat(){
		def br = new BufferedReader(new InputStreamReader(System.in))
		println "Enter name of chat partner: "

		def chatPartnerID = br.readLine().trim().toLowerCase()
		def onlineUsers = UserService.instance.getOnlineUsers()
		def isValidChatPartnerID = validateChatPartner(chatPartnerID, onlineUsers)
		def chatPartnerInetAddr = findChatPartnerInetAddr(chatPartnerID, onlineUsers)

		if(!isValidChatPartnerID){
			println "Sorry - there is no user online with the name \"${chatPartnerID.toUpperCase()}\"."
			println "Please try again or type 'list' to search for another user."
			return
		}

		println "Enter message to \"${chatPartnerID.toUpperCase()}\": "
		def msg = br.readLine()
		sender.instance.sendMessage(msg)
		println "Please type a new command before continuing."
	}

	public boolean validateChatPartner(String chatPartnerID, def onlineUsers){
		for(user in onlineUsers) {
				if(chatPartnerID == user.name.toLowerCase()){
				return true
			}
		}
		return false
	}

	public def findChatPartnerInetAddr(String chatPartnerID, def onlineUsers) {
		for(user in onlineUsers) {
			if(chatPartnerID == user.name.toLowerCase()) {
				return user.ip
			}
		}
	}
	
	public String showUserList(def onlineUsers) {
		def list = ""
		onlineUsers.each { it ->
			if(it.name != sender.instance.name){
				list += "      " + it.name + "\n      -----------------\n"
			}
		}
		return list
	}

	void executeUserEntry(String val) {

		if(val == 'exit') {
			logout()
		}
		else if(val == 'chat'){
			chat()
		}
		else if(val == 'list'){
			def list = UserService.instance.getOnlineUsers()
			if(list.size() > 1) {
				println "++++++++++++++++++++++++++++++++++++++++++++++++\n"
				println "      Want to chat? These users are online:"
				println "      ====================================="
				println showUserList(list)
				println "      ====================================="
				println "      Type 'chat' to start a conversation."
				println "++++++++++++++++++++++++++++++++++++++++++++++++"
			} else {
				println "Sorry - nobody online"
			}
		}
		else if(val == 'help'){
			println commands
		}
	}

	public void logout(){
		UserService.instance.removeUserFromServer()
		this.receiver.stopClientServer()
		isOnline = false
		println "Thanks for using MESSAS. \nWe are looking forward to seeing you again soon!"
	}

}

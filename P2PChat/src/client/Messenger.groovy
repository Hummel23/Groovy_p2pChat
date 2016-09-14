package client

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

**********************************************"""


	public void greeting(){
		println "Welcome to MESSAS! Please type your name if you want to log in."
	}

	public void login(){
		def br = new BufferedReader(new InputStreamReader(System.in))

		//		TODO boolean userAdded = false
		//		(while(!userAdded){
		//		if(addUser == null){
		//		prinln "This name is already in use. Please choose another name."
		//		messenger.name = br.readLine()
		//		addUser()
		//		}else{
		//		userAdded = true
		//		}
		//	}
		//
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

	public void chat(){
		def onlineUsers = getOnlineUsers()
		if(onlineUsers.size() > 0) {
			def br = new BufferedReader(new InputStreamReader(System.in))
			println "Enter name of chat partner: "
			def chatPartnerID = br.readLine().trim().toLowerCase()

			def isValidChatPartnerID = validateChatPartner(chatPartnerID, onlineUsers)
			def chatPartnerInetAddr = findChatPartnerInetAddr(chatPartnerID, onlineUsers)

			if(!isValidChatPartnerID){
				println "Sorry - there is no user online with the name \"${chatPartnerID.toUpperCase()}\"."
				println "Please try again or type 'list' to search for another user."
				return
			}

			println "Enter message to \"${chatPartnerID.toUpperCase()}\": "
			def msg = br.readLine()
			println "Please type a new command before continuing."
			Message messageObject = new TextMessage(msg, sender.instance.name, "chatPartnerID")
			def msgJson = messageService.convertToJSON(messageObject)
			sender.instance.sendMessage(msgJson)
			println "Please type a new command before continuing."
		}
		else{
			println "Sorry - nobody online"
		}
	}

	public def getOnlineUsers() {
		RESTClient client = new RESTClient("http://${InetAddr.UserServerInetAddr}:8080")
		def response = client.get(path: '/list')
		return response.data
	}

	public String showUserList(def onlineUsers) {
		def list = ""

		for(user in onlineUsers) {
			if(user.name != sender.instance.name){
				list += "      " + user.name + "\n      -----------------\n"
			}
		}
		return list
	}

	public boolean validateChatPartner(String chatPartnerID, def onlineUsers){
		println "in validateChatPartner: " + chatPartnerID
		for(user in onlineUsers){
			if(chatPartnerID == user.name.toLowerCase()){
				return true
			}
		}
		return false
	}

	public def findChatPartnerInetAddr(String chatPartnerID, def onlineUsers) {
		for(user in onlineUsers){
			if(chatPartnerID == user.name.toLowerCase()) {
				return user.ip
			}
		}
	}

	void executeUserEntry(String val) {

		if(val == 'exit') {
			logout()
		}
		else if(val == 'chat'){
			chat()
		}
		else if(val == 'list'){
			def list = getOnlineUsers()
			if(list.size() > 0) {
				println "++++++++++++++++++++++++++++++++++++++++++++++++\n"
				println"      Want to chat? These users are online:"
				println"      ====================================="
				println showUserList(list)
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
		//TODO logout from userServer by removing user from list
		RESTClient client = new RESTClient("http://${InetAddr.UserServerInetAddr}:8080")
		def response = client.get(path: '/logout')
		this.receiver.stopClientServer()
		isOnline=false
		println "Thanks for using MESSAS. \nWe are looking forward to seeing you again soon!"
	}

}

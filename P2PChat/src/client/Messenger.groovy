package client

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient

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

**********************************************"""

	boolean isOnline = false

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


	void executeUserEntry(String val) {

		if(val == 'exit') {
			logout()
		}
		else if(val == 'chat'){
			chat()
		}
		else if(val == 'list'){
			def list = getOnlineUsers()
			println "list is: " + list
			if(list.size() > 0) {
			println "++++++++++++++++++++++++++++++++++++++++++++++++"
			println"      Want to chat? These users are online:"
			println "     ====================================="
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
		this.receiver.stopClientServer()
		isOnline=false
		println "Thanks for using MESSAS. We are looking forward to seeing you again soon!"
	}

}

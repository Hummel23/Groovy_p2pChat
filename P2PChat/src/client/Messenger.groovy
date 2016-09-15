package client


import groovyx.net.http.RESTClient
import client.services.MessageService
import client.services.UserService

@Singleton
class Messenger {

	Sender sender
	Receiver receiver
	boolean isWriting = false
	boolean isOnline = false

	def messageService = MessageService.instance
	def userService = UserService.instance
	def name
	def commands = """
▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬

	List of possible Commands:
	---------------------------------------
	list : show a list of all online users
	chat : write a message
	exit : exit chat program
	help : show commands

▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬
		"""

	public void greeting(){
		println "Welcome to MESSAS!\nPlease type your name if you want to log in."
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
				System.err.println "This name is already in use. Please choose another name."
				this.name = br.readLine().toLowerCase()
				println ""
			}else{
				this.name = name
				this.receiver.name = name
				this.sender.name = name
				receiver.inetAddr = login
				userAdded = true
			}
		}
		this.receiver.startClientServer()
		isOnline = true
		println commands
	}

	public void chat(String val){
		def br = new BufferedReader(new InputStreamReader(System.in))
		boolean chatPartnerChosen = false
		def chatPartnerName
		if(val == 'chat') {
			println "Enter name of chat partner: "
			chatPartnerName = br.readLine().trim().toLowerCase()
			println ""
		}else{
			chatPartnerName = val
		}
		
		def chatPartnerInetAddr = UserService.instance.getInetAddrChatPartner(chatPartnerName)
		if (chatPartnerInetAddr==""){
				System.err.println "Sorry - there is no user online with the name \"${chatPartnerName.toUpperCase()}\"."
				println "Please try again or type 'list' to search for another user."
				return
			}
		println "Enter message to \"${chatPartnerName.toUpperCase()}\": "
		print "${this.name.toUpperCase()} : "
		def msg = br.readLine()
		println ""
		sender.instance.sendMessage(msg, chatPartnerInetAddr, chatPartnerName)
		println "Please type a new command before continuing."
	}
	
	public String showUserList(def onlineUsers) {
		def userList = ""
		def users = onlineUsers.findAll {it -> it.name != sender.instance.name}
		users.each { it ->
				userList += "	> " + it.name + "\n\n"
//				if(it != users.last()){
//					userList += "      -----------------"
//				}
		}
		return userList
	}

	void executeUserEntry(String val) {
		val = val.trim().toLowerCase()
		
		if(val == 'exit') {
			logout()
		}
		else if(val == 'list'){
			def list = UserService.instance.getOnlineUsers()
			if(list.size() > 1) {
			println"╔══════════════════════════════════════════════════════╗"
			println"║		These users are online:                	      ║"
			println"╚══════════════════════════════════════════════════════╝"
			println showUserList(list)
			} else {
				println "Sorry - nobody online"
				println "ಥ_ಥ"
			}
		}
		else if(val == 'help'){
			println commands
		}
		else {
			chat(val)	//is chat or valid online-user name 
		}
	}

	public void logout(){
		UserService.instance.removeUserFromServer()
		this.receiver.stopClientServer()
		isOnline = false
		println "Thanks for using MESSAS. \nWe are looking forward to seeing you again soon!"
		println """
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░▄▄▀▀▀▀▀▄░░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░▄▀░░░░░░░▀▄░░░░░░░░░░░░░░░░░░░░░░░░░░
░▄▀░░░▄▄░░░░▀▀▀▀▀▀▀▄▄▀▀▀▀▀▀▀▀▀▀▀▀▄▄░░░░
░█░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░▀▄░░
░█░░░░██▄████▄░██▄░░░░▄██░▄████▄░░░░▀▄░
░█░░░░██▀░░▀██▄░██▄░░██▀░██▀░▄██░░░░░█░
░█░░░░██░░░░███░░█████▀░░██▄█▀▀░░░░░░█░
░█░░░░███▄▄███▀░░░▀██▀░░░▀██▄▄▄██░░░░█░
░▀▄░░░░▀▀▀▀▀▀░░░░░██▀░░░░░░▀▀▀▀▀░░░░░█░
░░▀▄░░░░░░░░░░░░░██▀░░░▄▄░░░░░░░░░▄▄▀░░
░░░░▀▀▀▀▀▀▀▀▀▄░░░▀▀░░░▄▀░▀▀▀▀▀▀▀▀▀░░░░░
░░░░░░░░░░░░░▀▄░░░░░░▄▀░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░▀▀▀▀▀▀░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
"""
	}

}

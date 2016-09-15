package client.services

import groovyx.net.http.RESTClient
import client.InetAddr

@Singleton
class UserService {
	
	RESTClient client = new RESTClient(InetAddr.UserServerInetAddr)
	
	/**asks the user to enter the name of the Chatpartner and checks that the name entered is 
	* is registered at the userServer. If the name is not registered at the userserver
	* the user will be asked to enter another name. If the name is known the method returns the
	* correct name (beautified)
	**/ 
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
	
	/**
	 * iterates through the list received from the userserver and checks if the name entered
	 * is present in the list
	 */
	public boolean isInList(String name){
		return true
	}
	
	public def getOnlineUsers() {
		def response = this.client.get(path: '/list')
		return response.data
	}
	
	public String addUserToServer(String name) {
		def responseLogin = client.get(path:'/login', query:['name':name])
		return responseLogin.data
	}
	
	public boolean removeUserFromServer(){
		def responseLogout = client.get(path:'/logout')
		return responseLogout.data
	}
	
	public String getInetAddrChatPartner(def name){
		def responseLogin = client.get(path:'/findInetAddr', query:['name':name])
		return responseLogin.data
	}

}

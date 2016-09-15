package client.services

import client.services.UserService

@Singleton
class ValidationService {
	
	def keywords = ['exit', 'list', 'chat', 'help']
	
	/**
	 * returns true if the command entered is either exit, list, chat or help or an online-user-name
	 * @param val
	 * @return
	 */
	boolean isValidEntry(String val){
		def keys = keywords.findAll{it -> it == val}
		return (!keys.isEmpty() || (UserService.instance.getInetAddrChatPartner(val).size() > 0))
	}
	
	/**
	 * returns true if the entered login name is neither a keyword, nor empty
	 * @param name
	 * @return
	 */
	boolean validateLoginName(String name) {
		def keys = keywords.findAll{it -> it == name}
		return (keys.isEmpty() && name.size() > 0)
	}

}

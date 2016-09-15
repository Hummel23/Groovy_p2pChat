package client.services

import client.services.UserService

@Singleton
class ValidationService {
	
	// returns true if the command entered is either exit, list, chat or help
	boolean isValidEntry(String val){
		(val == 'exit' ||
			val == 'list' || 
			val == 'chat' || 
			val == 'help' ||
			(UserService.instance.getInetAddrChatPartner(val).size() > 0) ? true : false)
	}

}

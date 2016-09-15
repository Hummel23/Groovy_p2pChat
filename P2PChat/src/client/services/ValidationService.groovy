package client.services
import client.Messenger

@Singleton
class ValidationService {
	
	// returns true if the command entered is either exit, list, chat or help
	boolean isValidEntry(String val){
		(val == 'exit' ||
			val == 'list' || 
			val == 'chat' || 
			val == 'help' ||
			Messenger.instance.validateChatPartner(val, UserService.instance.getOnlineUsers())) ? true : false
	}

}

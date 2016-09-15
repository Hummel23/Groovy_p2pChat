package client

import groovy.transform.ToString

@ToString(includeNames=true)
class TextMessage extends Message {
	
	public TextMessage(String content, def sender, def chatPartnerID) {
		this.content = content
		this.sender = sender
		this.chatPartnerID = chatPartnerID
	}
	
	@Override
	public String toString() {
				
		return """
╔=======================================================
    ${this.sender.toUpperCase()}:
	----------------------------
    ${this.content}
╚=======================================================
"""
	}
	
}

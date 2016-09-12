package client

import groovy.transform.ToString

@ToString(includeNames=true)
class TextMessage extends Message{
	
	public TextMessage(String content, Sender sender, Receiver rec) {
		this.content = content
		this.sender = sender
		this.receiver = rec
	}
	
}

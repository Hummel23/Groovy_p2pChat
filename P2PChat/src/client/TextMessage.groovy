package client

import groovy.transform.ToString

@ToString(includeNames=true)
class TextMessage extends Message{
	
	public TextMessage(String content) {
		super()
		this.content = content
	}
	
	
}

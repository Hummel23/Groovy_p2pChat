package client

import groovy.transform.ToString


abstract class Message {
	
	def content, sender, chatPartnerID
	def date = new Date()

	@Override
	public String toString() {
				
		def msg = """
		============================
		${this.sender.toUpperCase()}:
		----------------------------
		${this.content}
		============================"""
	}	
	
}

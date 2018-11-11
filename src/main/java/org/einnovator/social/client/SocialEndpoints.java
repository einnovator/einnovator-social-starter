package org.einnovator.social.client;

public class SocialEndpoints {
	

	public static String chat(String id, SocialClientConfiguration config) {
		return chats(config) + "/" + id;
	}

	public static String chats(SocialClientConfiguration config) {
		return config.getServer() + "/api/chat/";
	}



}

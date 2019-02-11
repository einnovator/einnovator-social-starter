package org.einnovator.social.client;

public class SocialEndpoints {
	

	public static String channel(String id, SocialClientConfiguration config) {
		return channels(config) + "/" + id;
	}

	public static String channels(SocialClientConfiguration config) {
		return config.getServer() + "/api/channel/";
	}



}

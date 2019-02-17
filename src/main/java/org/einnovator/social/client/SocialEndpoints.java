package org.einnovator.social.client;

public class SocialEndpoints {
	

	public static String channel(String id, SocialClientConfiguration config) {
		return channels(config) + "/" + id;
	}

	public static String channels(SocialClientConfiguration config) {
		return config.getServer() + "/api/channel";
	}

	public static String messages(String channelId, SocialClientConfiguration config) {
		return channel(channelId + "/message", config);
	}

	public static String message(String channelId, String msgId, SocialClientConfiguration config) {
		return channel(channelId, config) + "/message/" + msgId;
	}

	public static String comments(String channelId, String msgId, SocialClientConfiguration config) {
		return message(channelId, msgId, config) + "/comment";
	}
}

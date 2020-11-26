package org.einnovator.social.client.config;

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

	public static String reactions(String channelId, String msgId, SocialClientConfiguration config) {
		return message(channelId, msgId, config) + "/reaction";
	}

	public static String reactionStats(String channelId, String msgId, SocialClientConfiguration config) {
		return message(channelId, msgId, config) + "/reactionstats";
	}

	public static String reaction(String channelId, String msgId, String reactionId, SocialClientConfiguration config) {
		return reactions(channelId, msgId, config) + "/" + reactionId;
	}

	public static String channelReactions(String channelId, SocialClientConfiguration config) {
		return channel(channelId, config) + "/reaction";
	}

	public static String channelReactionStats(String channelId, SocialClientConfiguration config) {
		return channel(channelId, config) + "/reactionstats";
	}

	public static String channelReaction(String channelId, String reactionId, SocialClientConfiguration config) {
		return channelReactions(channelId, config) + "/" + reactionId;
	}

}

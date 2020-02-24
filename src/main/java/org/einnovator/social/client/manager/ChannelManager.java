package org.einnovator.social.client.manager;


import java.net.URI;
import java.util.Map;

import org.einnovator.social.client.config.SocialClientContext;
import org.einnovator.social.client.model.Channel;
import org.einnovator.social.client.model.Message;
import org.einnovator.social.client.modelx.ChannelFilter;
import org.einnovator.social.client.modelx.ChannelOptions;
import org.einnovator.social.client.modelx.MessageFilter;
import org.einnovator.social.client.modelx.MessageOptions;
import org.springframework.cache.Cache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChannelManager {
	
	Channel getChannel(String id, SocialClientContext context);
	
	Channel getChannel(String id, ChannelOptions options, SocialClientContext context);

	Page<Channel> listChannels(ChannelFilter filter, Pageable pageable, SocialClientContext context);

	/**
	 * Create Channel.
	 * 
	 * @param channel the {@code Channel}
	 * @return the {@code URI} for te created {@code Channel}.
	 */

	URI createChannel(Channel channel, SocialClientContext context);
	
	Channel updateChannel(Channel channel, SocialClientContext context);
	

	/**
	 * Create or Update Channel.
	 * 
	 * If {@code Channel.uuid} has text is assumed to be an edit. Otherwise a create. A single call is made to seerver.
	 * @param channel the {@code Channel}
	 * @param context TODO
	 * @return the {@code Channel} with {@code uuid} property set; or null if error.
	 */
	Channel createOrUpdateChannel(Channel channel, SocialClientContext context);
	
	boolean deleteChannel(String id, SocialClientContext context);
	
	Page<Message> listMessages(String channelId, MessageFilter filter, Pageable pageable, SocialClientContext context);

	URI postMessage(String channelId, Message msg, SocialClientContext context);

	Message getMessage(String channelId, String id, MessageOptions options, SocialClientContext context);
	
	Message updateMessage(String channelId, Message msg, SocialClientContext context);

	boolean deleteMessage(String channelId, String id, SocialClientContext context);

	URI postComment(String channelId, String msgId, Message comment, SocialClientContext context);

	void onChannelUpdate(String id, Map<String, Object> details, SocialClientContext context);

	void clearCache();
	
	Cache getChannelCache();


}

package org.einnovator.social.client.manager;


import java.net.URI;
import java.util.Map;

import org.einnovator.social.client.model.Channel;
import org.einnovator.social.client.modelx.ChannelFilter;
import org.einnovator.social.client.modelx.ChannelOptions;
import org.springframework.cache.Cache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChannelManager {
	
	
	Channel getChannel(String id);
	
	Channel getChannel(String id, ChannelOptions options);

	Page<Channel> listChannels(ChannelFilter filter, Pageable pageable);

	URI createChannel(Channel channel);
	
	Channel updateChannel(Channel channel);
	
	boolean deleteChannel(String id);
	
	void onChannelUpdate(String id, Map<String, Object> details);

	void clearCache();
	
	Cache getChannelCache();

}

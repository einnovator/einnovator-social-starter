package org.einnovator.social.client.manager;

import java.net.URI;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.social.client.SocialClient;
import org.einnovator.social.client.model.Channel;
import org.einnovator.social.client.model.Message;
import org.einnovator.social.client.modelx.ChannelFilter;
import org.einnovator.social.client.modelx.ChannelOptions;
import org.einnovator.social.client.modelx.MessageFilter;
import org.einnovator.util.UriUtils;
import org.einnovator.util.cache.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;

public class ChannelManagerImpl implements ChannelManager {

	public static final String CACHE_CHANNEL = "Channel";
	public static final String CACHE_MESSAGE = "Message";

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SocialClient client;
	
	private CacheManager cacheManager;

	@Autowired
	public ChannelManagerImpl(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public ChannelManagerImpl(SocialClient client, CacheManager cacheManager) {
		this.client = client;
		this.cacheManager = cacheManager;
	}
	
	public ChannelManagerImpl() {
	}


	@Override
	public Channel getChannel(String id) {
		if (id==null) {
			return null;
		}
			
		try {
			Channel channel = CacheUtils.getCacheValue(Channel.class, getChannelCache(), id);
			if (channel!=null) {
				return channel;
			}	
			channel = client.getChannel(id);		
			if (channel==null) {
				logger.error(String.format("getChannel: %s", id));
				return null;
			}
			return CacheUtils.putCacheValue(channel, getChannelCache(), id);
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error(String.format("getChannel: %s %s", e, id));
			}
			return null;
		} catch (RuntimeException e) {
			logger.error(String.format("getChannel: %s %s", e, id));
			return null;
		}
	}

	@Override
	//@Cacheable(value=CACHE_CHANNEL, key="#id + ' ' + #options.orgs + ' ' + #options.ops + ' ' + #options.teams + ' ' + #options.roles + ' ' + #options.permissions")
	public Channel getChannel(String id, ChannelOptions options) {
		try {
			Channel channel = client.getChannel(id, options);		
			if (channel==null) {
				logger.error("getChannel" + id);
			}
			return channel;
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error(String.format("getChannel: %s %s %s", e, id, options));
			}
			return null;
		} catch (RuntimeException e) {
			logger.error(String.format("getChannel: %s %s %s", e, id, options));
			return null;
		}
	}

	@Override
	public URI createChannel(Channel channel) {
		try {
			return client.createChannel(channel);
		} catch (RuntimeException e) {
			logger.error(String.format("createChannel: %s %s", e, channel));
			return null;
		}
	}
	
	@Override
	@CachePut(value=CACHE_CHANNEL, key="#channel.uuid")
	public Channel updateChannel(Channel channel) {
		try {
			client.updateChannel(channel);
			return channel;
		} catch (RuntimeException e) {
			logger.error(String.format("updateChannel: %s %s", e, channel));
			return null;
		}
	}

	@Override
	public Channel createOrUpdateChannel(Channel channel) {
		if (channel.getUuid()==null) {
			URI uri = createChannel(channel);
			if (uri==null) {
				return null;
			}
			channel.setUuid(UriUtils.extractId(uri));
			return channel;
		} else {
			return updateChannel(channel);
		}
	}
	
	
	@Override
	@CacheEvict(value=CACHE_CHANNEL, key="#id")
	public boolean deleteChannel(String id) {
		try {
			client.deleteChannel(id);
			return true;
		} catch (RuntimeException e) {
			logger.error(String.format("deleteChannel: %s %s %s", e, id));
			return false;
		}
	}
	
	
	@Override
	public Page<Channel> listChannels(ChannelFilter filter, Pageable pageable) {
		try {
			return client.listChannels(filter, pageable);
		} catch (RuntimeException e) {
			logger.error(String.format("listChannels: %s %s %s", e, filter, pageable));
			return null;
		}
	}
	
	
	public void onChannelUpdate(String id, Map<String, Object> details) {
		if (id==null) {
			return;
		}
		try {
			Cache cache = getChannelCache();
			if (cache!=null) {
				Channel channel = (Channel) cache.get(id);
				if (channel!=null) {
					if (details!=null) {
						channel.updateFrom(details);						
					} else {
						cache.evict(id);
					}
				}
			}
		} catch (RuntimeException e) {
			logger.error(String.format("onChannelUpdate: %s", e));
		}
	}

	@Override
	public void clearCache() {
		Cache cache = getChannelCache();
		if (cache!=null) {
			cache.clear();
		}
	}

	@Override
	public Cache getChannelCache() {
		Cache cache = cacheManager.getCache(ChannelManagerImpl.CACHE_CHANNEL);
		return cache;
	}

	/*
	 * @see org.einnovator.social.client.manager.ChannelManager#findMessages(org.einnovator.social.client.model.Channel, org.einnovator.social.client.modelx.MessageFilter, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Message> listMessages(String channelId, MessageFilter filter, Pageable pageable) {
		try {
			return client.listMessages(channelId, filter, pageable);
		} catch (RuntimeException e) {
			logger.error(String.format("listMessages: %s %s %s %s", e, channelId, filter, pageable));
			return null;
		}
	}

	/*
	 * @see org.einnovator.social.client.manager.ChannelManager#postMessage(org.einnovator.social.client.model.Channel, org.einnovator.social.client.model.Message)
	 */
	@Override
	public URI postMessage(String channelId, Message msg) {
		try {
			return client.postMessage(channelId, msg);
		} catch (RuntimeException e) {
			logger.error(String.format("postMessage: %s %s %s", e, channelId, msg));
			return null;
		}
	}

	/*
	 * @see org.einnovator.social.client.manager.ChannelManager#findMessage(org.einnovator.social.client.model.Channel, java.lang.String)
	 */
	@Override
	public Message getMessage(String channelId, String id) {
		try {
			Message msg = client.getMessage(channelId, id, null);		
			if (msg==null) {
				logger.error("getMessage" + channelId + " " + id);
			}
			return msg;
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error(String.format("getMessage: %s %s", channelId, id));
			}
			return null;
		} catch (RuntimeException e) {
			logger.error(String.format("getMessage: %s %s %s", e, channelId, id));
			return null;
		}
	}

	/*
	 * @see org.einnovator.social.client.manager.ChannelManager#updateMessage(org.einnovator.social.client.model.Channel, org.einnovator.social.client.model.Message)
	 */
	@Override
	public Message updateMessage(String channelId, Message msg) {
		try {
			client.updateMessage(channelId, msg);
			return msg;
		} catch (RuntimeException e) {
			logger.error(String.format("updateMessage: %s %s %s", e, channelId, msg));
			return null;
		}
	}

	/*
	 * @see org.einnovator.social.client.manager.ChannelManager#deleteMessage(org.einnovator.social.client.model.Channel, java.lang.String)
	 */
	@Override
	public boolean deleteMessage(String channelId, String id) {
		try {
			client.deleteMessage(channelId, id);
			return true;
		} catch (RuntimeException e) {
			logger.error(String.format("deleteMessage: %s %s %s", e, channelId, id));
			return false;
		}
	}

	/*
	 * @see org.einnovator.social.client.manager.ChannelManager#postComment(org.einnovator.social.client.model.Channel, java.lang.String, org.einnovator.social.client.model.Message)
	 */
	@Override
	public URI postComment(String channelId, String msgId, Message comment) {
		try {
			return client.postComment(channelId, msgId, comment);
		} catch (RuntimeException e) {
			logger.error(String.format("postComment: %s %s %s", e, channelId, comment));
			return null;
		}
	}
	
}

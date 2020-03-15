package org.einnovator.social.client.manager;

import java.net.URI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.social.client.SocialClient;

import org.einnovator.social.client.model.Channel;
import org.einnovator.social.client.model.Message;
import org.einnovator.social.client.model.MessageType;
import org.einnovator.social.client.modelx.ChannelFilter;
import org.einnovator.social.client.modelx.ChannelOptions;
import org.einnovator.social.client.modelx.MessageFilter;
import org.einnovator.social.client.modelx.MessageOptions;
import org.einnovator.util.UriUtils;
import org.einnovator.util.cache.CacheUtils;
import org.einnovator.util.web.RequestOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
		return getChannel(id, null);
	}
	
	@Override
	public Channel getChannel(String id, ChannelOptions options) {
		try {
			Channel channel = null;
			
			if (isCacheable(options)) {
				channel = CacheUtils.getCacheValue(Channel.class, getChannelCache(), id);
				if (channel!=null) {
					return channel;
				}	
			}
			channel = client.getChannel(id, options);		
			if (channel==null) {
				logger.error(String.format("getChannel: %s", id));
				return null;
			}
			if (isCacheable(options)) {
				CacheUtils.putCacheValue(channel, getChannelCache(), id);				
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
	
	protected boolean isCacheable(ChannelOptions options) {
		return options==null;
	}

	@Override
	public URI createChannel(Channel channel, RequestOptions options) {
		try {
			return client.createChannel(channel, options);
		} catch (RuntimeException e) {
			logger.error(String.format("createChannel: %s %s", e, channel));
			return null;
		}
	}
	
	@Override
	@CachePut(value=CACHE_CHANNEL, key="#channel.uuid")
	public Channel updateChannel(Channel channel, RequestOptions options) {
		try {
			client.updateChannel(channel, options);
			return channel;
		} catch (RuntimeException e) {
			logger.error(String.format("updateChannel: %s %s", e, channel));
			return null;
		}
	}

	@Override
	public Channel createOrUpdateChannel(Channel channel, RequestOptions options) {
		if (channel.getUuid()==null) {
			URI uri = createChannel(channel, options);
			if (uri==null) {
				return null;
			}
			channel.setUuid(UriUtils.extractId(uri));
			return channel;
		} else {
			return updateChannel(channel, options);
		}
	}
	
	
	@Override
	@CacheEvict(value=CACHE_CHANNEL, key="#id")
	public boolean deleteChannel(String id, RequestOptions options) {
		try {
			client.deleteChannel(id, options);
			return true;
		} catch (RuntimeException e) {
			logger.error(String.format("deleteChannel: %s %s", e, id));
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
	
	
	@Override
	public void onChannelUpdate(String id, Object details) {
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

	@Override
	public Page<Message> listMessages(String channelId, MessageFilter filter, Pageable pageable) {
		try {
			return client.listMessages(channelId, filter, pageable);
		} catch (RuntimeException e) {
			logger.error(String.format("listMessages: %s %s %s %s", e, channelId, filter, pageable));
			return null;
		}
	}

	@Override
	public URI postMessage(String channelId, Message msg, RequestOptions options) {
		try {
			return client.postMessage(channelId, msg, options);
		} catch (RuntimeException e) {
			logger.error(String.format("postMessage: %s %s %s", e, channelId, msg));
			return null;
		}
	}

	@Override
	public Message getMessage(String channelId, String id, MessageOptions options) {
		try {
			Message msg = client.getMessage(channelId, id, options);		
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

	@Override
	public Message updateMessage(String channelId, Message msg, RequestOptions options) {
		try {
			client.updateMessage(channelId, msg, options);
			return msg;
		} catch (RuntimeException e) {
			logger.error(String.format("updateMessage: %s %s %s", e, channelId, msg, options));
			return null;
		}
	}

	@Override
	public boolean deleteMessage(String channelId, String id, RequestOptions options) {
		try {
			client.deleteMessage(channelId, id, options);
			return true;
		} catch (RuntimeException e) {
			logger.error(String.format("deleteMessage: %s %s %s", e, channelId, id, options));
			return false;
		}
	}
	
	@Override
	public URI postChildMessage(String channelId, String msgId, Message message, RequestOptions options) {
		try {
			return client.postChildMessage(channelId, msgId, message, options);
		} catch (RuntimeException e) {
			logger.error(String.format("postComment: %s %s %s", e, channelId, message));
			return null;
		}
	}
	

	@Override
	public URI postComment(String channelId, String msgId, Message comment, RequestOptions options) {
		comment.setType(MessageType.COMMENT);
		return postChildMessage(channelId, msgId, comment, options);		
	}

	@Override
	public URI postAnswer(String channelId, String msgId, Message answer, RequestOptions options) {
		answer.setType(MessageType.ANSWER);
		return postChildMessage(channelId, msgId, answer, options);		
	}
}

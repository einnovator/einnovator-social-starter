package org.einnovator.social.client.manager;

import java.net.URI;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.social.client.SocialClient;
import org.einnovator.social.client.model.Channel;
import org.einnovator.social.client.modelx.ChannelFilter;
import org.einnovator.social.client.modelx.ChannelOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

@CacheConfig(cacheManager="channelCacheManager", cacheResolver="channelCacheResolver")
public class ChannelManagerImpl implements ChannelManager {

	public static final String CACHE_META = "Channel";

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
	@Cacheable(value=CACHE_META, key="#id", cacheManager="channelCacheManager")
	public Channel getChannel(String id) {
		try {
			Channel channel = client.getChannel(id);		
			if (channel==null) {
				logger.error("getChannel" + id);
			}
			return channel;
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error("getChannel:" + id + "  " + e);				
			}
			return null;
		} catch (RuntimeException e) {
			logger.error("getChannel:" + id + "  " + e);
			return null;
		}
	}

	@Override
	//@Cacheable(value=CACHE_META, key="#id + ' ' + #options.orgs + ' ' + #options.ops + ' ' + #options.teams + ' ' + #options.roles + ' ' + #options.permissions", cacheManager="channelCacheManager")
	public Channel getChannel(String id, ChannelOptions options) {
		try {
			Channel channel = client.getChannel(id, options);		
			if (channel==null) {
				logger.error("getChannel" + id);
			}
			return channel;
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error("getChannel:" + id + "  " + options + " " +  e);				
			}
			return null;
		} catch (RuntimeException e) {
			logger.error("getChannel:" + id + "  " + options + " " + e);				
			return null;
		}
	}

	@Override
	public URI createChannel(Channel channel) {
		try {
			return client.createChannel(channel);
		} catch (RuntimeException e) {
			logger.error("createChannel:" + e);
			return null;
		}
	}
	
	@Override
	@CachePut(value=CACHE_META, key="#channel.id", cacheManager="channelCacheManager")
	public Channel updateChannel(Channel channel) {
		try {
			client.updateChannel(channel);
			return channel;
		} catch (RuntimeException e) {
			logger.error("updateChannel:" + e);
			return null;
		}
	}

	
	@Override
	@CacheEvict(value=CACHE_META, key="#id", cacheManager="channelCacheManager")
	public boolean deleteChannel(String channelId) {
		try {
			client.deleteChannel(channelId);
			return true;
		} catch (RuntimeException e) {
			logger.error("deleteChannel:" + e);
			return false;
		}
	}
	
	
	@Override
	public Page<Channel> listChannels(ChannelFilter filter, Pageable pageable) {
		try {
			return client.listChannels(filter, pageable);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("listChannels:" + e);
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
			e.printStackTrace();
			logger.error("onChannelUpdate: " + e);
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
		Cache cache = cacheManager.getCache(ChannelManagerImpl.CACHE_META);
		return cache;
	}
	
}

package org.einnovator.social.client.manager;

import java.net.URI;
import java.util.Map;

import org.apache.log4j.Logger;
import org.einnovator.social.client.SocialClient;
import org.einnovator.social.client.model.Chat;
import org.einnovator.social.client.modelx.ChatFilter;
import org.einnovator.social.client.modelx.ChatOptions;
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

@CacheConfig(cacheManager="chatCacheManager", cacheResolver="chatCacheResolver")
public class ChatManagerImpl implements ChatManager {

	public static final String CACHE_META = "Chat";

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private SocialClient client;
	
	private CacheManager cacheManager;

	@Autowired
	public ChatManagerImpl(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public ChatManagerImpl(SocialClient client, CacheManager cacheManager) {
		this.client = client;
		this.cacheManager = cacheManager;
	}
	
	public ChatManagerImpl() {
	}


	@Override
	@Cacheable(value=CACHE_META, key="#id", cacheManager="chatCacheManager")
	public Chat getChat(String id) {
		try {
			Chat chat = client.getChat(id);		
			if (chat==null) {
				logger.error("getChat" + id);
			}
			return chat;
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error("getChat:" + id + "  " + e);				
			}
			return null;
		} catch (RuntimeException e) {
			logger.error("getChat:" + id + "  " + e);
			return null;
		}
	}

	@Override
	//@Cacheable(value=CACHE_META, key="#id + ' ' + #options.orgs + ' ' + #options.ops + ' ' + #options.teams + ' ' + #options.roles + ' ' + #options.permissions", cacheManager="chatCacheManager")
	public Chat getChat(String id, ChatOptions options) {
		try {
			Chat chat = client.getChat(id, options);		
			if (chat==null) {
				logger.error("getChat" + id);
			}
			return chat;
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error("getChat:" + id + "  " + options + " " +  e);				
			}
			return null;
		} catch (RuntimeException e) {
			logger.error("getChat:" + id + "  " + options + " " + e);				
			return null;
		}
	}

	@Override
	public URI createChat(Chat chat) {
		try {
			return client.createChat(chat);
		} catch (RuntimeException e) {
			logger.error("createChat:" + e);
			return null;
		}
	}
	
	@Override
	@CachePut(value=CACHE_META, key="#chat.id", cacheManager="chatCacheManager")
	public Chat updateChat(Chat chat) {
		try {
			client.updateChat(chat);
			return chat;
		} catch (RuntimeException e) {
			logger.error("updateChat:" + e);
			return null;
		}
	}

	
	@Override
	@CacheEvict(value=CACHE_META, key="#id", cacheManager="chatCacheManager")
	public boolean deleteChat(String chatId) {
		try {
			client.deleteChat(chatId);
			return true;
		} catch (RuntimeException e) {
			logger.error("deleteChat:" + e);
			return false;
		}
	}
	
	
	@Override
	public Page<Chat> listChats(ChatFilter filter, Pageable pageable) {
		try {
			return client.listChats(filter, pageable);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("listChats:" + e);
			return null;
		}
	}
	
	
	public void onChatUpdate(String id, Map<String, Object> details) {
		if (id==null) {
			return;
		}
		try {
			Cache cache = getChatCache();
			if (cache!=null) {
				Chat chat = (Chat) cache.get(id);
				if (chat!=null) {
					if (details!=null) {
						chat.updateFrom(details);						
					} else {
						cache.evict(id);
					}
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("onChatUpdate: " + e);
		}
	}

	@Override
	public void clearCache() {
		Cache cache = getChatCache();
		if (cache!=null) {
			cache.clear();
		}
	}

	@Override
	public Cache getChatCache() {
		Cache cache = cacheManager.getCache(ChatManagerImpl.CACHE_META);
		return cache;
	}
	
}

package org.einnovator.social.client.manager;

import java.net.URI;
import java.util.Map;

import org.apache.log4j.Logger;
import org.einnovator.social.client.SocialClient;
import org.einnovator.social.client.model.MBox;
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

@CacheConfig(cacheManager="mboxCacheManager", cacheResolver="mboxCacheResolver")
public class MBoxManagerImpl implements MBoxManager {

	public static final String CACHE_META = "Chat";

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private SocialClient client;
	
	private CacheManager cacheManager;

	@Autowired
	public MBoxManagerImpl(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public MBoxManagerImpl(SocialClient client, CacheManager cacheManager) {
		this.client = client;
		this.cacheManager = cacheManager;
	}
	
	public MBoxManagerImpl() {
	}


	@Override
	@Cacheable(value=CACHE_META, key="#id", cacheManager="mboxCacheManager")
	public MBox getChat(String id) {
		try {
			MBox mbox = client.getChat(id);		
			if (mbox==null) {
				logger.error("getChat" + id);
			}
			return mbox;
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
	//@Cacheable(value=CACHE_META, key="#id + ' ' + #options.orgs + ' ' + #options.ops + ' ' + #options.teams + ' ' + #options.roles + ' ' + #options.permissions", cacheManager="mboxCacheManager")
	public MBox getChat(String id, ChatOptions options) {
		try {
			MBox mbox = client.getChat(id, options);		
			if (mbox==null) {
				logger.error("getChat" + id);
			}
			return mbox;
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
	public URI createChat(MBox mbox) {
		try {
			return client.createChat(mbox);
		} catch (RuntimeException e) {
			logger.error("createChat:" + e);
			return null;
		}
	}
	
	@Override
	@CachePut(value=CACHE_META, key="#mbox.id", cacheManager="mboxCacheManager")
	public MBox updateChat(MBox mbox) {
		try {
			client.updateChat(mbox);
			return mbox;
		} catch (RuntimeException e) {
			logger.error("updateChat:" + e);
			return null;
		}
	}

	
	@Override
	@CacheEvict(value=CACHE_META, key="#id", cacheManager="mboxCacheManager")
	public boolean deleteChat(String mboxId) {
		try {
			client.deleteChat(mboxId);
			return true;
		} catch (RuntimeException e) {
			logger.error("deleteChat:" + e);
			return false;
		}
	}
	
	
	@Override
	public Page<MBox> listChats(ChatFilter filter, Pageable pageable) {
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
				MBox mbox = (MBox) cache.get(id);
				if (mbox!=null) {
					if (details!=null) {
						mbox.updateFrom(details);						
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
		Cache cache = cacheManager.getCache(MBoxManagerImpl.CACHE_META);
		return cache;
	}
	
}

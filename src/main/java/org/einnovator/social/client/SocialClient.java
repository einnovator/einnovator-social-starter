package org.einnovator.social.client;

import static org.einnovator.util.UriUtils.appendQueryParameters;
import static org.einnovator.util.UriUtils.makeURI;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.einnovator.social.client.model.Chat;
import org.einnovator.social.client.modelx.ChatFilter;
import org.einnovator.social.client.modelx.ChatOptions;
import org.einnovator.util.MappingUtils;
import org.einnovator.util.PageUtil;
import org.einnovator.util.ListOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestClientException;


public class SocialClient {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private SocialClientConfiguration config;
	
	@Autowired
	@Qualifier("metaRestTemplate")
	private OAuth2RestTemplate restTemplate;
	
	@Autowired
	public SocialClient() {
	}
	
	public SocialClient(SocialClientConfiguration config) {
		this.config = config;
	}

	public SocialClient(OAuth2RestTemplate restTemplate, SocialClientConfiguration config) {
		this.restTemplate = restTemplate;
		this.config = config;
	}
	
	public OAuth2RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(OAuth2RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	// Chat
	
	public Chat getChat(String id) {
		return getChat(id, null);
	}
	
	public Chat getChat(String id, ChatOptions options) {
		URI uri = makeURI(SocialEndpoints.chat(id, config));
		if (options!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			if (options!=null) {
				params.putAll(MappingUtils.toMapFormatted(options));
			}
			uri = appendQueryParameters(uri, params);
		}
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Chat> result = exchange(request, Chat.class);
		return result.getBody();
	}

	
	public Page<Chat> listChats(ChatFilter filter, Pageable pageable) {
		URI uri = makeURI(SocialEndpoints.chats(config));
		if (pageable!=null || filter!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			if (pageable!=null) {
				params.putAll(MappingUtils.toMapFormatted(new ListOptions(pageable)));
			}
			if (filter!=null) {
				params.putAll(MappingUtils.toMapFormatted(filter));				
			}
			uri = appendQueryParameters(uri, params);
		}
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<Page> result = exchange(request, Page.class);
		return PageUtil.create(result.getBody(),  Chat.class);
	}
	
	public URI createChat(Chat chat) {
		URI uri = makeURI(SocialEndpoints.chats(config));
		RequestEntity<Chat> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(chat);
		
		ResponseEntity<Void> result = exchange(request, Void.class);
		return result.getHeaders().getLocation();
	}
	
	public void updateChat(Chat chat) {
		URI uri = makeURI(SocialEndpoints.chat(chat.getUuid(), config));
		RequestEntity<Chat> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(chat);
		
		exchange(request, Chat.class);
	}
	
	public void deleteChat(String id) {
		URI uri = makeURI(SocialEndpoints.chat(id, config));
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class);
	}

	

	protected <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType) throws RestClientException {
		return restTemplate.exchange(request, responseType);
	}

}

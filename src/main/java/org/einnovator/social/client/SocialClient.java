package org.einnovator.social.client;

import static org.einnovator.util.UriUtils.appendQueryParameters;
import static org.einnovator.util.UriUtils.makeURI;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.social.client.model.MBox;
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
	private final Log logger = LogFactory.getLog(getClass());

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
	
	public MBox getChat(String id) {
		return getChat(id, null);
	}
	
	public MBox getChat(String id, ChatOptions options) {
		URI uri = makeURI(SocialEndpoints.mbox(id, config));
		if (options!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			if (options!=null) {
				params.putAll(MappingUtils.toMapFormatted(options));
			}
			uri = appendQueryParameters(uri, params);
		}
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<MBox> result = exchange(request, MBox.class);
		return result.getBody();
	}

	
	public Page<MBox> listChats(ChatFilter filter, Pageable pageable) {
		URI uri = makeURI(SocialEndpoints.mboxs(config));
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
		return PageUtil.create(result.getBody(),  MBox.class);
	}
	
	public URI createChat(MBox mbox) {
		URI uri = makeURI(SocialEndpoints.mboxs(config));
		RequestEntity<MBox> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(mbox);
		
		ResponseEntity<Void> result = exchange(request, Void.class);
		return result.getHeaders().getLocation();
	}
	
	public void updateChat(MBox mbox) {
		URI uri = makeURI(SocialEndpoints.mbox(mbox.getUuid(), config));
		RequestEntity<MBox> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(mbox);
		
		exchange(request, MBox.class);
	}
	
	public void deleteChat(String id) {
		URI uri = makeURI(SocialEndpoints.mbox(id, config));
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class);
	}

	

	protected <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType) throws RestClientException {
		return restTemplate.exchange(request, responseType);
	}

}

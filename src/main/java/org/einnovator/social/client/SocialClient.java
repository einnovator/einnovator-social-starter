package org.einnovator.social.client;

import static org.einnovator.util.UriUtils.appendQueryParameters;
import static org.einnovator.util.UriUtils.makeURI;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.social.client.config.SocialClientConfiguration;
import org.einnovator.social.client.config.SocialEndpoints;
import org.einnovator.social.client.model.Channel;
import org.einnovator.social.client.model.Message;
import org.einnovator.social.client.modelx.ChannelFilter;
import org.einnovator.social.client.modelx.ChannelOptions;
import org.einnovator.social.client.modelx.MessageFilter;
import org.einnovator.social.client.modelx.MessageOptions;
import org.einnovator.util.MappingUtils;
import org.einnovator.util.PageUtil;
import org.einnovator.util.PageOptions;
import org.einnovator.util.PageResult;
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


	// Channel
	
	public Channel getChannel(String id) {
		return getChannel(id, null);
	}
	
	public Channel getChannel(String id, ChannelOptions options) {
		URI uri = makeURI(SocialEndpoints.channel(id, config));
		if (options!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			if (options!=null) {
				params.putAll(MappingUtils.toMapFormatted(options));
			}
			uri = appendQueryParameters(uri, params);
		}
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Channel> result = exchange(request, Channel.class);
		return result.getBody();
	}

	
	public Page<Channel> listChannels(ChannelFilter filter, Pageable pageable) {
		URI uri = makeURI(SocialEndpoints.channels(config));
		if (pageable!=null || filter!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			if (pageable!=null) {
				params.putAll(MappingUtils.toMapFormatted(new PageOptions(pageable)));
			}
			if (filter!=null) {
				params.putAll(MappingUtils.toMapFormatted(filter));				
			}
			uri = appendQueryParameters(uri, params);
		}
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<PageResult> result = exchange(request, PageResult.class);
		return PageUtil.create2(result.getBody(),  Channel.class);
	}
	
	public URI createChannel(Channel channel) {
		URI uri = makeURI(SocialEndpoints.channels(config));
		RequestEntity<Channel> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(channel);
		
		ResponseEntity<Void> result = exchange(request, Void.class);
		return result.getHeaders().getLocation();
	}
	
	public void updateChannel(Channel channel) {
		URI uri = makeURI(SocialEndpoints.channel(channel.getUuid(), config));
		RequestEntity<Channel> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(channel);
		
		exchange(request, Channel.class);
	}
	
	public void deleteChannel(String id) {
		URI uri = makeURI(SocialEndpoints.channel(id, config));
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class);
	}

	public Page<Message> listMessages(String channelId, MessageFilter filter, Pageable pageable) {
		URI uri = makeURI(SocialEndpoints.messages(channelId, config));
		if (pageable!=null || filter!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			if (pageable!=null) {
				params.putAll(MappingUtils.toMapFormatted(new PageOptions(pageable)));
			}
			if (filter!=null) {
				params.putAll(MappingUtils.toMapFormatted(filter));				
			}
			uri = appendQueryParameters(uri, params);
		}
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<PageResult> result = exchange(request, PageResult.class);
		return PageUtil.create2(result.getBody(),  Message.class);
		
	}

	public URI postMessage(String channelId, Message msg) {
		URI uri = makeURI(SocialEndpoints.messages(channelId, config));
		RequestEntity<Message> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(msg);
		
		ResponseEntity<Void> result = exchange(request, Void.class);
		return result.getHeaders().getLocation();
		
	}

	public Message getMessage(String channelId, String id, MessageOptions options) {
		URI uri = makeURI(SocialEndpoints.message(channelId, id, config));
		if (options!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			if (options!=null) {
				params.putAll(MappingUtils.toMapFormatted(options));
			}
			uri = appendQueryParameters(uri, params);
		}
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Message> result = exchange(request, Message.class);
		return result.getBody();
		
	}
	
	public void updateMessage(String channelId, Message msg) {
		URI uri = makeURI(SocialEndpoints.message(channelId, msg.getUuid(), config));
		RequestEntity<Message> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(msg);
		
		exchange(request, Message.class);
	}

	public void deleteMessage(String channelId, String id) {
		URI uri = makeURI(SocialEndpoints.message(channelId, id, config));
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class);
	}

	public URI postComment(String channelId, String msgId, Message comment) {
		URI uri = makeURI(SocialEndpoints.comments(channelId, msgId, config));
		RequestEntity<Message> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(comment);
		
		ResponseEntity<Void> result = exchange(request, Void.class);
		return result.getHeaders().getLocation();
	}


	protected <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType) throws RestClientException {
		return restTemplate.exchange(request, responseType);
	}

}

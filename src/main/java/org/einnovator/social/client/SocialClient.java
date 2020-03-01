package org.einnovator.social.client;

import static org.einnovator.util.UriUtils.appendQueryParameters;
import static org.einnovator.util.UriUtils.makeURI;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.social.client.config.SocialClientConfiguration;
import org.einnovator.social.client.config.SocialClientContext;
import org.einnovator.social.client.config.SocialEndpoints;
import org.einnovator.social.client.model.Channel;
import org.einnovator.social.client.model.Message;
import org.einnovator.social.client.model.Reaction;
import org.einnovator.social.client.modelx.ChannelFilter;
import org.einnovator.social.client.modelx.ChannelOptions;
import org.einnovator.social.client.modelx.MessageFilter;
import org.einnovator.social.client.modelx.MessageOptions;
import org.einnovator.util.MappingUtils;
import org.einnovator.util.PageOptions;
import org.einnovator.util.PageResult;
import org.einnovator.util.PageUtil;
import org.einnovator.util.web.ClientContext;
import org.einnovator.util.web.RequestOptions;
import org.einnovator.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestClientException;


/**
 * Client to the Social Hub service.
 * 
 * <p>Provide methods for all server endpoints and resource types. 
 * <p>Including: {@link Channel}, {@link Message}, and {@link Reaction}.
 * <p>Errors are propagated using Java runtime exceptions.
 * <p>For caching enabled "high-level" API, see Manager classes.
 * <p>{@code SocialClientConfiguration} specifies configuration details, including server URL and client credentials.
 * <p>Property {@link #getConfig()} provides the default {@code SocialClientConfiguration} to use.
 * <p>All API methods that invoke a server endpoint accept an <em>optional</em> tail parameter to connect to alternative server
 *  (e.g. for cover the less likely case where an application need to connect to multiple servers in different clusters).
 * <p>Internally, {@code SocialClient} uses a {@code OAuth2RestTemplate} to invoke a remote server.
 * <p>When setup as a <b>Spring Bean</b> both {@code SocialClientConfiguration} and {@code OAuth2RestTemplate} are auto-configured.
 * <p>Requests use a session-scoped  {@code OAuth2ClientContext} if running in a web-environment.
 * <p>If the invoking thread does not have an associated web session, the default behavior is to fallback to use a {@code OAuth2ClientContext} 
 * with client credentials. This can be disabled by setting property {@link #web} to false.
 * <p>Method {@link #register()} can be used to register custom application roles with server.
 * <p>This is automatically performed by if configuration property {@code sso.registration.roles.auto} is set to true.
 * 
 * @see org.einnovator.social.client.manager.ChannelManager
 * 
 * @author support@einnovator.org
 *
 */
public class SocialClient {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SocialClientConfiguration config;
	
	@Autowired
	@Qualifier("socialRestTemplate")
	private OAuth2RestTemplate restTemplate;
	
	private OAuth2ClientContext oauth2ClientContext0 = new DefaultOAuth2ClientContext();

	private OAuth2RestTemplate restTemplate0;

	private ClientHttpRequestFactory clientHttpRequestFactory;

	
	/**
	 * Create instance of {@code SocialClient}.
	 *
	 */
	@Autowired
	public SocialClient() {
	}
	
	/**
	 * Create instance of {@code SocialClient}.
	 *
	 * @param config the {@code SocialClientConfiguration}
	 */
	public SocialClient(SocialClientConfiguration config) {
		this.config = config;
	}

	/**
	 * Create instance of {@code SocialClient}.
	 *
	 * @param restTemplate the {@code OAuth2RestTemplate} to use for HTTP transport
	 * @param config the {@code SocialClientConfiguration}
	 */
	public SocialClient(OAuth2RestTemplate restTemplate, SocialClientConfiguration config) {
		this.restTemplate = restTemplate;
		this.config = config;
	}
	
	//
	// Getters/Setters
	//

	/**
	 * Get the value of property {@code config}.
	 *
	 * @return the config
	 */
	public SocialClientConfiguration getConfig() {
		return config;
	}

	/**
	 * Set the value of property {@code config}.
	 *
	 * @param config the config to set
	 */
	public void setConfig(SocialClientConfiguration config) {
		this.config = config;
	}

	/**
	 * Get the value of property {@code restTemplate}.
	 *
	 * @return the restTemplate
	 */
	public OAuth2RestTemplate getRestTemplate() {
		return restTemplate;
	}

	/**
	 * Set the value of property {@code restTemplate}.
	 *
	 * @param restTemplate the restTemplate to set
	 */
	public void setRestTemplate(OAuth2RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	//
	// Channel
	//
	

	/**
	 * Get {@code Channel} with specified identifier.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param id the identifier
	 * @param options (optional) the {@code ChannelOptions} that tailor which fields are returned (projection)
	 * @param context optional {@code SsoClientContext}
	 * @return the {@code Channel}
	 * @throws RestClientException if request fails
	 */
	public Channel getChannel(String id, ChannelOptions options, SocialClientContext context) {
		URI uri = makeURI(SocialEndpoints.channel(id, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Channel> result = exchange(request, Channel.class, context);
		return result.getBody();
	}

	
	/**
	 * List {@code Channel}s.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param filter a {@code ChannelFilter}
	 * @param pageable a {@code Pageable} (optional)
	 * @param context optional {@code SsoClientContext}
	 * @throws RestClientException if request fails
	 * @return a {@code Page} with {@code Channel}s, or null if request failed
	 */
	public Page<Channel> listChannels(ChannelFilter filter, Pageable pageable, SocialClientContext context) {
		URI uri = makeURI(SocialEndpoints.channels(config));
		uri = processURI(uri, filter, pageable);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<PageResult> result = exchange(request, PageResult.class, context);
		return PageUtil.create2(result.getBody(),  Channel.class);
	}
	

	/**
	 * Create a new {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Any.
	 * 
	 * @param channel the {@code Channel}
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code SsoClientContext}
	 * @return the location {@code URI} for the created {@code Channel}
	 * @throws RestClientException if request fails
	 */
	public URI createChannel(Channel channel, RequestOptions options, SocialClientContext context) {
		URI uri = makeURI(SocialEndpoints.channels(config));
		uri = processURI(uri, options);
		RequestEntity<Channel> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(channel);
		ResponseEntity<Void> result = exchange(request, Void.class, context);
		return result.getHeaders().getLocation();
	}
	
	/**
	 * Update existing {@code Channel}
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param channel the {@code Channel}
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code SsoClientContext}
	 * @throws RestClientException if request fails
	 */
	public void updateChannel(Channel channel, RequestOptions options, SocialClientContext context) {
		URI uri = makeURI(SocialEndpoints.channel(channel.getUuid(), config));
		uri = processURI(uri, options);
		RequestEntity<Channel> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(channel);
		exchange(request, Channel.class, context);
	}
	
	
	/**
	 * Delete existing {@code Channel}
	 * 
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param id the {@code Channel} identifier (UUID)
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code SsoClientContext}
	 * @throws RestClientException if request fails
	 */
	public void deleteChannel(String id, RequestOptions options, SocialClientContext context) {
		URI uri = makeURI(SocialEndpoints.channel(id, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class, context);
	}

	//
	// Messages
	//
	
	
	/**
	 * List {@code Message} of a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param filter a {@code MessageFilter}
	 * @param pageable a {@code Pageable} (optional)
	 * @param context optional {@code SsoClientContext}
	 * @return a {@code Page} with {@code Message}s
	 * @throws RestClientException if request fails
	 */
	public Page<Message> listMessages(String channelId, MessageFilter filter, Pageable pageable, SocialClientContext context) {
		URI uri = makeURI(SocialEndpoints.messages(channelId, config));
		uri = processURI(uri, filter, pageable);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<PageResult> result = exchange(request, PageResult.class, context);
		return PageUtil.create2(result.getBody(),  Message.class);
		
	}

	/**
	 * Post a {@code Message} to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the identifier of a {@code Channel} (UUID)
	 * @param msg the {@code Message}
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code SsoClientContext}
	 * @return the location {@code URI} for the created {@code Message}
	 * @throws RestClientException if request fails
	 */
	public URI postMessage(String channelId, Message msg, RequestOptions options, SocialClientContext context) {
		URI uri = makeURI(SocialEndpoints.messages(channelId, config));
		uri = processURI(uri, options);
		RequestEntity<Message> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(msg);
		ResponseEntity<Void> result = exchange(request, Void.class, context);
		return result.getHeaders().getLocation();
		
	}

	/**
	 * Get {@code Message} with specified identifier post to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param msgId the identifier of a {@code Message} (UUID)
	 * @param options optional {@code UserOptions}
	 * @param context optional {@code SsoClientContext}
	 * @return the {@code Message}
	 * @throws RestClientException if request fails
	 */
	public Message getMessage(String channelId, String msgId, MessageOptions options, SocialClientContext context) {
		URI uri = makeURI(SocialEndpoints.message(channelId, msgId, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Message> result = exchange(request, Message.class, context);
		return result.getBody();
		
	}
	
	/**
	 * Update existing {@code Message} posted to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param channel the {@code Channel}
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code SsoClientContext}
	 * @throws RestClientException if request fails
	 */
	public void updateMessage(String channelId, Message msg, RequestOptions options, SocialClientContext context) {
		URI uri = makeURI(SocialEndpoints.message(channelId, msg.getUuid(), config));
		uri = processURI(uri, options);
		RequestEntity<Message> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(msg);
		exchange(request, Message.class, context);
	}

	/**
	 * Delete {@code Message} posted to a {@code Channel}.
	 * 
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner of {@code Message}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param msgId the {@code Message} identifier (UUID)
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code SsoClientContext}
	 * @throws RestClientException if request fails
	 */
	public void deleteMessage(String channelId, String msgId, RequestOptions options, SocialClientContext context) {
		URI uri = makeURI(SocialEndpoints.message(channelId, msgId, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class, context);
	}

	/**
	 * Post a {@code Message} as child to other {@code Message} previous posted to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the identifier of a {@code Channel} (UUID)
	 * @param msgId the identifier of the parent {@code Message} (UUID)
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code SsoClientContext}
	 * @return the location {@code URI} for the created {@code Message}
	 * @throws RestClientException if request fails
	 */
	public URI postChildMessage(String channelId, String msgId, Message message, RequestOptions options, SocialClientContext context) {
		URI uri = makeURI(SocialEndpoints.comments(channelId, msgId, config));
		uri = processURI(uri, options);
		RequestEntity<Message> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(message);
		ResponseEntity<Void> result = exchange(request, Void.class, context);
		return result.getHeaders().getLocation();
	}


	//
	// HTTP transport
	//
	
	protected <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType, SocialClientContext context) throws RestClientException {
		OAuth2RestTemplate restTemplate = this.restTemplate;
		if (context!=null && context.getRestTemplate()!=null) {
			restTemplate = context.getRestTemplate();
		} else {
			if (WebUtil.getHttpServletRequest()==null && this.restTemplate0!=null) {
				restTemplate = this.restTemplate0;
			}			
		}
		return exchange(restTemplate, request, responseType);
	}

	protected <T> ResponseEntity<T> exchange(OAuth2RestTemplate restTemplate, RequestEntity<?> request, Class<T> responseType) throws RestClientException {
		return restTemplate.exchange(request, responseType);
	}
	
	//
	// RestTemplate util factory methods
	//

	public OAuth2RestTemplate makeOAuth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext oauth2ClientContext) {
		OAuth2RestTemplate template = new OAuth2RestTemplate(resource, oauth2ClientContext);			
		template.setRequestFactory(clientHttpRequestFactory);
		return template;
	}
	
	public OAuth2RestTemplate makeOAuth2RestTemplate(OAuth2ClientContext oauth2ClientContext, ClientCredentialsResourceDetails resource) {
		OAuth2RestTemplate template = new OAuth2RestTemplate(resource, oauth2ClientContext);			
		template.setRequestFactory(clientHttpRequestFactory);
		return template;
	}
	
	public OAuth2RestTemplate setupClientOAuth2RestTemplate(ClientCredentialsResourceDetails resource) {
		if (restTemplate0==null) {
			restTemplate0 = makeOAuth2RestTemplate(oauth2ClientContext0, resource);
		}
		return restTemplate0;
	}
	
	//
	// Other
	//

	/**
	 * Process URI by adding parameters from properties of specified objectes.
	 * 
	 * @param uri the {@code URI}
	 * @param objs a variadic array of objects
	 * @return the processed {@code URI}
	 */
	public static URI processURI(URI uri, Object... objs) {
		if (objs!=null && objs.length>0) {
			Map<String, String> params = new LinkedHashMap<>();
			for (Object obj: objs) {
				if (obj==null) {
					continue;
				}
				if (obj instanceof Pageable) {
					obj = new PageOptions((Pageable)obj);
				}
				params.putAll(MappingUtils.toMapFormatted(obj));
			}
			uri = appendQueryParameters(uri, params);			
		}
		return uri;
	}
	
	
	/**
	 * Check if request is for admin endpoint.
	 * 
	 * @param options optional {@code RequestOptions}
	 * @param context options {@code SsoClientContext}
	 * @return true if reques is for an admin endpoint, false otherwise
	 */
	public static boolean isAdminRequest(RequestOptions options, ClientContext context) {
		if (options!=null && options.getAdmin()!=null) {
			return Boolean.TRUE.equals(options.getAdmin());
		}
		if (context!=null && context.isAdmin()) {
			return true;
		}
		return false;
	}

	
}

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
import org.einnovator.social.client.model.Reaction;
import org.einnovator.social.client.modelx.ChannelFilter;
import org.einnovator.social.client.modelx.ChannelOptions;
import org.einnovator.social.client.modelx.MessageFilter;
import org.einnovator.social.client.modelx.MessageOptions;
import org.einnovator.social.client.modelx.ReactionFilter;
import org.einnovator.social.client.modelx.ReactionOptions;
import org.einnovator.util.MappingUtils;
import org.einnovator.util.PageOptions;
import org.einnovator.util.PageResult;
import org.einnovator.util.PageUtil;
import org.einnovator.util.web.RequestOptions;
import org.einnovator.util.web.Result;
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

	private boolean web = true;

	
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
	
	/**
	 * Create instance of {@code SocialClient}.
	 *
	 * @param restTemplate the {@code OAuth2RestTemplate} used for HTTP transport
	 * @param config the {@code SocialClientConfiguration}
	 * @param web true if auto-detect web-environment 
	 */
	public SocialClient(OAuth2RestTemplate restTemplate, SocialClientConfiguration config, boolean web) {
		this(restTemplate, config);
		this.web = web;
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

	/**
	 * Get the value of property {@code oauth2ClientContext0}.
	 *
	 * @return the oauth2ClientContext0
	 */
	public OAuth2ClientContext getOauth2ClientContext0() {
		return oauth2ClientContext0;
	}

	/**
	 * Set the value of property {@code oauth2ClientContext0}.
	 *
	 * @param oauth2ClientContext0 the value of property oauth2ClientContext0
	 */
	public void setOauth2ClientContext0(OAuth2ClientContext oauth2ClientContext0) {
		this.oauth2ClientContext0 = oauth2ClientContext0;
	}

	/**
	 * Get the value of property {@code restTemplate0}.
	 *
	 * @return the restTemplate0
	 */
	public OAuth2RestTemplate getRestTemplate0() {
		return restTemplate0;
	}

	/**
	 * Set the value of property {@code restTemplate0}.
	 *
	 * @param restTemplate0 the value of property restTemplate0
	 */
	public void setRestTemplate0(OAuth2RestTemplate restTemplate0) {
		this.restTemplate0 = restTemplate0;
	}

	/**
	 * Get the value of property {@code web}.
	 *
	 * @return the web
	 */
	public boolean isWeb() {
		return web;
	}

	/**
	 * Set the value of property {@code web}.
	 *
	 * @param web the value of property web
	 */
	public void setWeb(boolean web) {
		this.web = web;
	}
	
	//
	// Channel
	//

	
	/**
	 * List {@code Channel}s.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param filter a {@code ChannelFilter}
	 * @param pageable a {@code Pageable} (optional)
	 * @throws RestClientException if request fails
	 * @return a {@code Page} with {@code Channel}s
	 * @throws RestClientException if request fails
	 */
	public Page<Channel> listChannels(ChannelFilter filter, Pageable pageable) {
		URI uri = makeURI(SocialEndpoints.channels(config));
		uri = processURI(uri, filter, pageable);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<PageResult> result = exchange(request, PageResult.class, filter);
		return PageUtil.create2(result.getBody(),  Channel.class);
	}
	
	

	/**
	 * Get {@code Channel} with specified identifier.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param id the identifier
	 * @param options (optional) the {@code ChannelOptions} that tailor which fields are returned (projection)
	 * @return the {@code Channel}
	 * @throws RestClientException if request fails
	 */
	public Channel getChannel(String id, ChannelOptions options) {
		URI uri = makeURI(SocialEndpoints.channel(id, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Channel> result = exchange(request, Channel.class, options);
		return result.getBody();
	}


	/**
	 * Create a new {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Any.
	 * 
	 * @param channel the {@code Channel}
	 * @param options optional {@code RequestOptions}
	 * @return the location {@code URI} for the created {@code Channel}
	 * @throws RestClientException if request fails
	 */
	public URI createChannel(Channel channel, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.channels(config));
		uri = processURI(uri, options);
		RequestEntity<Channel> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(channel);
		ResponseEntity<Void> result = exchange(request, Void.class, options);
		return result.getHeaders().getLocation();
	}
	
	/**
	 * Update existing {@code Channel}
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param channel the {@code Channel}
	 * @param options optional {@code RequestOptions}
	 * @throws RestClientException if request fails
	 */
	public void updateChannel(Channel channel, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.channel(channel.getUuid(), config));
		uri = processURI(uri, options);
		RequestEntity<Channel> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(channel);
		exchange(request, Channel.class, options);
	}
	
	
	/**
	 * Delete existing {@code Channel}
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param id the {@code Channel} identifier (UUID)
	 * @param options optional {@code RequestOptions}
	 * @throws RestClientException if request fails
	 */
	public void deleteChannel(String id, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.channel(id, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class, options);
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
	
	 * @return a {@code Page} with {@code Message}s
	 * @throws RestClientException if request fails
	 */
	public Page<Message> listMessages(String channelId, MessageFilter filter, Pageable pageable) {
		URI uri = makeURI(SocialEndpoints.messages(channelId, config));
		uri = processURI(uri, filter, pageable);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<PageResult> result = exchange(request, PageResult.class, filter);
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
	
	 * @return the location {@code URI} for the created {@code Message}
	 * @throws RestClientException if request fails
	 */
	public URI postMessage(String channelId, Message msg, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.messages(channelId, config));
		uri = processURI(uri, options);
		RequestEntity<Message> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(msg);
		ResponseEntity<Void> result = exchange(request, Void.class, options);
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
	
	 * @return the {@code Message}
	 * @throws RestClientException if request fails
	 */
	public Message getMessage(String channelId, String msgId, MessageOptions options) {
		URI uri = makeURI(SocialEndpoints.message(channelId, msgId, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Message> result = exchange(request, Message.class, options);
		return result.getBody();
		
	}
	
	/**
	 * Update existing {@code Message} posted to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param message the message to be update (UUID property should be set)
	 * @param options optional {@code RequestOptions}
	
	 * @throws RestClientException if request fails
	 */
	public void updateMessage(String channelId, Message message, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.message(channelId, message.getUuid(), config));
		uri = processURI(uri, options);
		RequestEntity<Message> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(message);
		exchange(request, Message.class, options);
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
	 * @throws RestClientException if request fails
	 */
	public void deleteMessage(String channelId, String msgId, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.message(channelId, msgId, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class, options);
	}

	/**
	 * Post a {@code Message} as child to other {@code Message} previous posted to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the identifier of a {@code Channel} (UUID)
	 * @param msgId the identifier of the parent {@code Message} (UUID)
	 * @param message the {@code Message} to post
	 * @param options optional {@code RequestOptions}
	 * @return the location {@code URI} for the created {@code Message}
	 * @throws RestClientException if request fails
	 */
	public URI postChildMessage(String channelId, String msgId, Message message, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.comments(channelId, msgId, config));
		uri = processURI(uri, options);
		RequestEntity<Message> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(message);
		ResponseEntity<Void> result = exchange(request, Void.class, options);
		return result.getHeaders().getLocation();
	}

	//
	// Message Reactions
	//
	
	/**
	 * List {@code Reaction} of a {@code Message}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param msgId the identifier of a {@code Message} (UUID)
	 * @param filter a {@code ReactionFilter}
	 * @param pageable a {@code Pageable} (optional)
	 * @return a {@code Page} with {@code Reaction}s
	 * @throws RestClientException if request fails
	 */
	public Page<Reaction> listReactions(String channelId, String msgId, ReactionFilter filter, Pageable pageable) {
		URI uri = makeURI(SocialEndpoints.reactions(channelId, msgId, config));
		uri = processURI(uri, filter, pageable);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<PageResult> result = exchange(request, PageResult.class, filter);
		return PageUtil.create2(result.getBody(),  Reaction.class);
		
	}

	/**
	 * Post a {@code Reaction} to a {@code Message}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the identifier of a {@code Channel} (UUID)
	 * @param msgId the identifier of a {@code Message} (UUID)
	 * @param reaction the {@code Reaction}
	 * @param options optional {@code RequestOptions}
	 * @return the location {@code URI} for the created {@code Reaction}
	 * @throws RestClientException if request fails
	 */
	public URI postReaction(String channelId, String msgId, Reaction reaction, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.reactions(channelId, msgId, config));
		uri = processURI(uri, options);
		RequestEntity<Reaction> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(reaction);
		ResponseEntity<Void> result = exchange(request, Void.class, options);
		return result.getHeaders().getLocation();
		
	}

	/**
	 * Get {@code Reaction} with specified identifier posted on a {@code Message}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param msgId the identifier of a {@code Message} (UUID)
	 * @param reactionId the {@code Reaction} identifier (UUID)
	 * @param options optional {@code UserOptions}
	 * @return the {@code Reaction}
	 * @throws RestClientException if request fails
	 */
	public Reaction getReaction(String channelId, String msgId, String reactionId, ReactionOptions options) {
		URI uri = makeURI(SocialEndpoints.reaction(channelId, msgId, reactionId, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Reaction> result = exchange(request, Reaction.class, options);
		return result.getBody();
		
	}
	
	/**
	 * Update existing {@code Reaction} posted to a {@code Message}.
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param msgId the identifier of a {@code Message} (UUID)
	 * @param reaction the reaction to be update (UUID property should be set)
	 * @param options optional {@code RequestOptions}
	 * @throws RestClientException if request fails
	 */
	public void updateReaction(String channelId, String msgId, Reaction reaction, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.reaction(channelId, msgId, reaction.getUuid(), config));
		uri = processURI(uri, options);
		RequestEntity<Reaction> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(reaction);
		exchange(request, Reaction.class, options);
	}

	/**
	 * Delete {@code Reaction} posted to a {@code Message}.
	 * 
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner of {@code Reaction}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param msgId the {@code Message} identifier (UUID)
	 * @param reactionId the {@code Reaction} identifier (UUID)
	 * @param options optional {@code RequestOptions}
	 * @throws RestClientException if request fails
	 */
	public void deleteReaction(String channelId, String msgId, String reactionId, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.reaction(channelId, msgId, reactionId, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class, options);
	}

	//
	// Channel Reactions
	//
	
	/**
	 * List {@code Reaction} of a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param filter a {@code ReactionFilter}
	 * @param pageable a {@code Pageable} (optional)
	 * @return a {@code Page} with {@code Reaction}s
	 * @throws RestClientException if request fails
	 */
	public Page<Reaction> listChannelReactions(String channelId, ReactionFilter filter, Pageable pageable) {
		URI uri = makeURI(SocialEndpoints.channelReactions(channelId, config));
		uri = processURI(uri, filter, pageable);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<PageResult> result = exchange(request, PageResult.class, filter);
		return PageUtil.create2(result.getBody(),  Reaction.class);
		
	}

	/**
	 * Post a {@code Reaction} to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the identifier of a {@code Channel} (UUID)
	 * @param reaction the {@code Reaction}
	 * @param options optional {@code RequestOptions}	
	 * @return the location {@code URI} for the created {@code Reaction}
	 * @throws RestClientException if request fails
	 */
	public URI postChannelReaction(String channelId, Reaction reaction, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.channelReactions(channelId, config));
		uri = processURI(uri, options);
		RequestEntity<Reaction> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(reaction);
		ResponseEntity<Void> result = exchange(request, Void.class, options);
		return result.getHeaders().getLocation();
		
	}

	/**
	 * Get {@code Reaction} with specified identifier post on a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param reactionId the {@code Reaction} identifier (UUID)
	 * @param options optional {@code UserOptions}
	 * @return the {@code Reaction}
	 * @throws RestClientException if request fails
	 */
	public Reaction getChannelReaction(String channelId, String reactionId, ReactionOptions options) {
		URI uri = makeURI(SocialEndpoints.channelReaction(channelId, reactionId, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Reaction> result = exchange(request, Reaction.class, options);
		return result.getBody();
		
	}
	
	/**
	 * Update existing {@code Reaction} posted on a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param reaction the reaction to be update (UUID property should be set)
	 * @param options optional {@code RequestOptions}
	 * @throws RestClientException if request fails
	 */
	public void updateChannelReaction(String channelId, Reaction reaction, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.channelReaction(channelId, reaction.getUuid(), config));
		uri = processURI(uri, options);
		RequestEntity<Reaction> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(reaction);
		exchange(request, Reaction.class, options);
	}

	/**
	 * Delete {@code Reaction} posted to a {@code Channel}.
	 * 
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner of {@code Reaction}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param reactionId the {@code Reaction} identifier (UUID)
	 * @param options optional {@code RequestOptions}
	 * @throws RestClientException if request fails
	 */
	public void deleteChannelReaction(String channelId, String reactionId, RequestOptions options) {
		URI uri = makeURI(SocialEndpoints.channelReaction(channelId,reactionId, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class, options);
	}

	
	//
	// HTTP transport
	//
	
	protected <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType, RequestOptions options) throws RestClientException {
		OAuth2RestTemplate restTemplate = getRequiredRestTemplate(options);
		try {
			return exchange(restTemplate, request, responseType);			
		} catch (RuntimeException e) {
			if (options!=null && !options.isSingleton()) {
				options.setResult(new Result<Object>(e));
			}
			throw e;
		}
	}

	protected <T> ResponseEntity<T> exchange(OAuth2RestTemplate restTemplate, RequestEntity<?> request, Class<T> responseType) throws RestClientException {
		return restTemplate.exchange(request, responseType);
	}
	
	/**
	 * Get the {@code OAuth2RestTemplate} to use to perform a request.
	 * 
	 * Return the configured {@code OAuth2RestTemplate} in property {@link #restTemplate}.
	 * If property {@link web} is true, check if current thread is bound to a web request with a session-scope. If not, fallback
	 * to client credential {@code OAuth2RestTemplate} in property {@link #restTemplate0} or create one if needed.
	 * 
	 * @param options optional {@code RequestOptions}
	 * @return the {@code OAuth2RestTemplate}
	 */
	protected OAuth2RestTemplate getRequiredRestTemplate(RequestOptions options) {
		OAuth2RestTemplate restTemplate = this.restTemplate;
		if (WebUtil.getHttpServletRequest()==null && web && this.restTemplate0!=null ) {
			restTemplate = this.restTemplate0;
		}			
		return restTemplate;
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
	
	
	
}

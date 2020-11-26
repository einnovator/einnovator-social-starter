package org.einnovator.social.client.manager;


import static org.einnovator.util.UriUtils.makeURI;

import java.net.URI;

import org.einnovator.social.client.config.SocialEndpoints;
import org.einnovator.social.client.model.Channel;
import org.einnovator.social.client.model.Message;
import org.einnovator.social.client.model.MessageType;
import org.einnovator.social.client.model.Reaction;
import org.einnovator.social.client.modelx.ChannelFilter;
import org.einnovator.social.client.modelx.ChannelOptions;
import org.einnovator.social.client.modelx.MessageFilter;
import org.einnovator.social.client.modelx.MessageOptions;
import org.einnovator.social.client.modelx.ReactionFilter;
import org.einnovator.social.client.modelx.ReactionOptions;
import org.einnovator.util.PageResult;
import org.einnovator.util.PageUtil;
import org.einnovator.util.web.RequestOptions;
import org.springframework.cache.Cache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

public interface ChannelManager {
	
	/**
	 * Get {@code Channel} with specified identifier.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param id the identifier
	 * @return the {@code Channel} if found, or null if not found or request failed
	 */
	Channel getChannel(String id);
	
	/**
	 * Get {@code Channel} with specified identifier.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param id the identifier
	 * @param options (optional) the {@code ChannelOptions} that tailor which fields are returned (projection)
	 * @return the {@code Channel} if found, or null if not found or request failed
	 */
	Channel getChannel(String id, ChannelOptions options);

	/**
	 * List {@code Channel}s.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param filter a {@code ChannelFilter}
	 * @param pageable a {@code Pageable} (optional)
	 * @throws RestClientException if request fails
	 * @return a {@code Page} with {@code Channel}s
	 */
	Page<Channel> listChannels(ChannelFilter filter, Pageable pageable);

	/**
	 * Create a new {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Any.
	 * 
	 * @param channel the {@code Channel}
	 * @param options optional {@code RequestOptions}
	 * @return the location {@code URI} for the created {@code Channel}, or null if request fails
	 */
	URI createChannel(Channel channel, RequestOptions options);
	
	/**
	 * Update existing {@code Channel}
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param channel the {@code Channel}
	 * @param options optional {@code RequestOptions}
	 * @return the same {@code Channel}, or null if request failed
	 */
	Channel updateChannel(Channel channel, RequestOptions options);
	

	/**
	 * Create or Update Channel.
	 * 
	 * If {@code Channel.uuid} has text is assumed to be an update. Otherwise a create. A single call is made to the server.
	 * 
	 * @param channel the {@code Channel}
	 * @param options optional {@code RequestOptions}
	 * @return the {@code Channel} with {@code uuid} property set; or null if error.
	 */
	Channel createOrUpdateChannel(Channel channel, RequestOptions options);
	
	
	/**
	 * Delete existing {@code Channel}
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param id the {@code Channel} identifier (UUID)
	 * @param options optional {@code RequestOptions}
	 * @return true if {@code Channel} was deleted, or false if request failed
	 */
	boolean deleteChannel(String id, RequestOptions options);
	
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
	 * @return a {@code Page} with {@code Message}s, or null if request failed
	 */
	Page<Message> listMessages(String channelId, MessageFilter filter, Pageable pageable);

	/**
	 * Post a {@code Message} to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the identifier of a {@code Channel} (UUID)
	 * @param msg the {@code Message}
	 * @param options optional {@code RequestOptions}
	 * @return the location {@code URI} for the created {@code Message}, or null if request failed
	 */
	URI postMessage(String channelId, Message msg, RequestOptions options);

	/**
	 * Get {@code Message} with specified identifier post to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param msgId the identifier of a {@code Message} (UUID)
	 * @param options optional {@code UserOptions}
	 * @return the {@code Message}, or null if not found or request failed
	 */
	Message getMessage(String channelId, String msgId, MessageOptions options);
	
	/**
	 * Update existing {@code Message} posted to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param message the message to be update (UUID property should be set)
	 * @param options optional {@code RequestOptions}
	 * @return the same {@code Message}, or null if request failed
	 */
	Message updateMessage(String channelId, Message message, RequestOptions options);

	/**
	 * Delete {@code Message} posted to a {@code Channel}.
	 * 
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner of {@code Message}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param msgId the {@code Message} identifier (UUID)
	 * @param options optional {@code RequestOptions}
	 * @return true if {@code Message} was deleted, or false if request failed
	 */
	boolean deleteMessage(String channelId, String msgId, RequestOptions options);

	/**
	 * Post a {@code Message} as child to other {@code Message} previous posted to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the identifier of a {@code Channel} (UUID)
	 * @param msgId the identifier of the parent {@code Message} (UUID)
	 * @param message the {@code Message} to post
	 * @param options optional {@code RequestOptions}
	 * @return the location {@code URI} for the created {@code Message}, or null if request failed
	 */
	URI postChildMessage(String channelId, String msgId, Message message, RequestOptions options);

	/**
	 * Post a {@code Message} of type {@code COMMENT} as child to other {@code Message} previous posted to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the identifier of a {@code Channel} (UUID)
	 * @param msgId the identifier of the parent {@code Message} (UUID)
	 * @param comment the child {@code Message} to post
	 * @param options optional {@code RequestOptions}
	 * @return the location {@code URI} for the created {@code Message}, or null if request failed
	 * @see MessageType
	 */
	URI postComment(String channelId, String msgId, Message comment, RequestOptions options);


	/**
	 * Post a {@code Message} of type {@code ANSWER} as child to other {@code Message} previous posted to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the identifier of a {@code Channel} (UUID)
	 * @param msgId the identifier of the parent {@code Message} (UUID)
	 * @param answer the child {@code Message} to post
	 * @param options optional {@code RequestOptions}
	 * @return the location {@code URI} for the created {@code Message}, or null if request failed
	 * @see MessageType
	 */
	URI postAnswer(String channelId, String msgId, Message answer, RequestOptions options);


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
	 * @return a {@code Page} with {@code Reaction}s, or null if request failed
	 */
	Page<Reaction> listReactions(String channelId, String msgId, ReactionFilter filter, Pageable pageable);

	/**
	 * Post a {@code Reaction} to a {@code Message}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the identifier of a {@code Channel} (UUID)
	 * @param msgId the identifier of a {@code Message} (UUID)
	 * @param reaction the {@code Reaction}
	 * @param options optional {@code RequestOptions}
	 * @return the location {@code URI} for the created {@code Reaction}, or null if request failed
	 */
	URI postReaction(String channelId, String msgId, Reaction reaction, RequestOptions options);

	/**
	 * Get {@code Reaction} with specified identifier posted on a {@code Message}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param msgId the identifier of a {@code Message} (UUID)
	 * @param reactionId the {@code Reaction} identifier (UUID)
	 * @param options optional {@code UserOptions}
	 * @return the {@code Reaction}, or null if not found or request failed
	 */
	Reaction getReaction(String channelId, String msgId, String reactionId, ReactionOptions options);
	
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
	Reaction updateReaction(String channelId, String msgId, Reaction reaction, RequestOptions options);

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
	 * @return true if {@code Reaction} was deleted, or false if request failed
	 */
	boolean deleteReaction(String channelId, String msgId, String reactionId, RequestOptions options);

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
	 * @return a {@code Page} with {@code Reaction}s, or null if request failed
	 */
	Page<Reaction> listChannelReactions(String channelId, ReactionFilter filter, Pageable pageable);

	/**
	 * Post a {@code Reaction} to a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Matching {@link Channel#getSharing()} and {@link Channel#getAuthorities()}.
	 * 
	 * @param channelId the identifier of a {@code Channel} (UUID)
	 * @param reaction the {@code Reaction}
	 * @param options optional {@code RequestOptions}	
	 * @return the location {@code URI} for the created {@code Reaction}, or null if request failed
	 */
	URI postChannelReaction(String channelId, Reaction reaction, RequestOptions options);

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
	Reaction getChannelReaction(String channelId, String reactionId, ReactionOptions options);
	
	/**
	 * Update existing {@code Reaction} posted on a {@code Channel}.
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param reaction the reaction to be update (UUID property should be set)
	 * @param options optional {@code RequestOptions}
	 * @return the {@code Reaction}, or null if not found or request failed
	 */
	Reaction updateChannelReaction(String channelId, Reaction reaction, RequestOptions options);

	/**
	 * Delete {@code Reaction} posted to a {@code Channel}.
	 * 
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner of {@code Reaction}.
	 * 
	 * @param channelId the {@code Channel} identifier (UUID)
	 * @param reactionId the {@code Reaction} identifier (UUID)
	 * @param options optional {@code RequestOptions}
	 * @return true if {@code Reaction} was deleted, or false if request failed
	 */
	boolean deleteChannelReaction(String channelId, String reactionId, RequestOptions options);

	//
	// Caching
	//
	
	/**
	 * Update cache entry for {@code Channel}.
	 * 
	 * @param id the {@code Channel} uuid or username
	 * @param details new state of {@code Channel}

	 */
	void onChannelUpdate(String id, Object details);

	/**
	 * Clear the cache for {@code Channel}s.
	 * 
	 */
	void clearCache();
	

	/**
	 * Get the cache for {@code Channel}s.
	 * 
	 * @return the {@code Cache}
	 */
	Cache getChannelCache();


}

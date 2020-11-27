package org.einnovator.social.client.web;

import java.net.URI;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.einnovator.social.client.manager.ChannelManager;
import org.einnovator.social.client.model.Channel;
import org.einnovator.social.client.model.Message;
import org.einnovator.social.client.model.Reaction;
import org.einnovator.social.client.model.Reactions;
import org.einnovator.social.client.modelx.ChannelFilter;
import org.einnovator.social.client.modelx.ChannelOptions;
import org.einnovator.social.client.modelx.MessageFilter;
import org.einnovator.social.client.modelx.ReactionFilter;
import org.einnovator.social.client.modelx.ReactionOptions;
import org.einnovator.util.PageOptions;
import org.einnovator.util.PageUtil;
import org.einnovator.util.UriUtils;
import org.einnovator.util.web.RequestOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;



@RestController
@RequestMapping("/api/channel")
public class ChannelRestController extends ControllerBase {

	@Autowired
	private ChannelManager manager;


	@GetMapping
	public ResponseEntity<Page<Channel>> listChannels(ChannelFilter filter, PageOptions options,
		Principal principal, HttpServletRequest request, HttpServletResponse response) {
		try {
			setupReadOnlyRequest(principal, filter, request);
			Page<Channel> channels = manager.listChannels(filter, options.toPageRequest());
			if (channels==null) {
				return badrequest("listChannels", response);				
			}
			return ok(channels, "listChannels", response, PageUtil.toString(channels), filter, options);
		} catch (RuntimeException e) {
			return status("listChannels", e, response, filter, options);
		}
	}
	
	
	@PostMapping
	public ResponseEntity<Void> createChannel(@RequestBody Channel channel, 
			RequestOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		try {
			setupRequest(principal, options, request);
			URI location =  manager.createChannel(channel, options);
			if (location==null) {
				return badrequest("createChannel", response, channel);
			}
			return created(location, "createChannel", response, channel);
		} catch (RuntimeException e) {
			return status("createChannel", e, response, channel);
		}
	}
	
	@GetMapping("/{id:.*}")
	public ResponseEntity<Channel> getChannel(@PathVariable String id,
			ChannelOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		try {
			setupReadOnlyRequest(principal, options, request);
			Channel channel = manager.getChannel(id, options);
			if (channel==null) {
				return notfound("getChannel", response);				
			}
			return ok(channel, "getChannel", response);
		} catch (RuntimeException e) {
			return status("getChannel", e, response, id);
		}		
	}
	
	@PutMapping("/{id:.*}")
	public ResponseEntity<Void> updateChannel(@RequestBody Channel channel, @PathVariable String id,
			RequestOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {			
		try {
			setupRequest(principal, options, request);
			if (manager.updateChannel(channel, null)==null) {
				return badrequest("getChannel", response);
			}
			return nocontent("getChannel", response);
		} catch (RuntimeException e) {
			return status("getChannel", e, response, id);
		}		
	}
	
	@DeleteMapping("/{channelId:.*}")
	public ResponseEntity<Void> deleteChannel(@PathVariable String id,
			RequestOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {			
		try {
			setupRequest(principal, options, request);
			if (!manager.deleteChannel(id, null)) {
				return badrequest("deleteChannel", response);
			}
			return nocontent("deleteChannel", response);
		} catch (RuntimeException e) {
			return status("deleteChannel", e, response, id);
		}		
	}
	

	@GetMapping("/{cid:.*}/message")
	public ResponseEntity<Page<Message>> listMessages(@PathVariable("cid") String cid, MessageFilter filter, PageOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {		
		try {
			setupReadOnlyRequest(principal, filter, request);
			Page<Message> page = manager.listMessages(cid, filter, options.toPageRequest());
			if (page==null) {
				return badrequest("listMessages", response, cid);				
			}
			return ok(page, "listMessages", response);
		} catch (RuntimeException e) {
			return status("listMessages", e, response, cid);
		}	
	}

	@PostMapping("/{cid:.*}/message")
	public ResponseEntity<Void> postMessage(@PathVariable("cid") String cid,
			RequestOptions options,
			@RequestBody Message msg, BindingResult errors,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		try {
			setupRequest(principal, options, request);
			URI location =  manager.postMessage(cid, msg, options);
			if (location==null) {
				return badrequest("postMessage", response, msg);
			}
			return created(location, "postMessage", response, location);
		} catch (RuntimeException e) {
			return status("postMessage", e, response, msg);
		}
	}

	@PutMapping("/{cid:.*}/message/{id:.*}")
	public ResponseEntity<Void> updateMessage(@PathVariable("cid") String cid, @PathVariable("id") String id,
			@RequestBody Message msg, BindingResult errors,
			RequestOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		try {
			setupRequest(principal, options, request);
			if (manager.updateMessage(cid, msg, null)==null) {
				return badrequest("updateMessage", response);
			}
			return nocontent("updateMessage", response);
		} catch (RuntimeException e) {
			return status("updateMessage", e, response, id);
		}		
	}

	@DeleteMapping("/{cid:.*}/message/{id:.*}")
	public ResponseEntity<Void> deleteMessage(@PathVariable("cid") String cid, @PathVariable("id") String id,
			RequestOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		try {
			setupRequest(principal, options, request);
			if (!manager.deleteMessage(cid, id, null)) {
				return badrequest("deleteMessage", response);
			}
			return nocontent("deleteMessage", response);
		} catch (RuntimeException e) {
			return status("deleteMessage", e, response, id);
		}		
	}
	
	//
	// Comments
	//
	

	@PostMapping({"/{cid:.*}/message/{mid:.*}/comment", "/{cid:.*}/message/{mid:.*}/message"})
	public ResponseEntity<Void> postComment(@PathVariable("cid") String cid, @PathVariable("mid") String mid, 
			@RequestBody Message message, BindingResult errors,
			RequestOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {

		try {
			setupRequest(principal, options, request);
			URI location = manager.postComment(cid, mid, message, options); 	
			if (location==null) {
				return badrequest("postComment", response, message);				
			}
			URI location2 = new UriTemplate(request.getRequestURI() + "/{id}").expand(UriUtils.extractId(location));
			return created(location2, "postComment", response);
		} catch (RuntimeException e) {
			return badrequest("postComment", response, e, message);
		}
	}

	
	//
	// Message Reactions
	//
	

	@GetMapping("/{cid:.*}/reaction")
	public ResponseEntity<Page<Reaction>> listChannelReactions(@PathVariable("cid") String cid,
			ReactionFilter filter, PageOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {


		try {
			setupReadOnlyRequest(principal, filter, request);
			Page<Reaction> page = manager.listChannelReactions(cid, filter, options.toPageRequest());
			if (page==null) {
				return badrequest("listChannelReactions", response);				
			}
			return ok(page, "listChannelReactions", response, PageUtil.toString(page), filter, options);
		} catch (RuntimeException e) {
			return badrequest("listChannelReactions", response, e, cid, filter, options);
		}
	}

	@GetMapping("/{cid:.*}/reactionstats")
	public ResponseEntity<Reactions> getChannelReactionStats(@PathVariable("cid") String cid,
			ReactionFilter filter, PageOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {

		try {
			setupReadOnlyRequest(principal, filter, request);
			Reactions reactions = manager.getChannelReactionStats(cid, filter, options.toPageRequest());
			if (reactions==null) {
				return notfound("getChannelReactionStats", response);				
			}
			return ok(reactions, "getChannelReactionStats", response);
		} catch (RuntimeException e) {
			return badrequest("getChannelReactionStats", response, e, cid, filter, options);
		}
	}

	@PostMapping("/{cid:.*}/reaction")
	public ResponseEntity<Void> postChannelReaction(@PathVariable("cid") String cid, 
			@RequestBody Reaction reaction, 
			@RequestParam(required = false) Boolean cancel,
			RequestOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {

		try {
			setupRequest(principal, options, request);
			if (Boolean.TRUE.equals(cancel)) {
				if (manager.cancelChannelReaction(cid, reaction, options)==null) {
					return badrequest("postChannelReaction", response);
				}
				return nocontent("postChannelReaction", response);
			} else {
				URI location = manager.postChannelReaction(cid, reaction, options);
				if (location==null) {
					return badrequest("postChannelReaction", response, cid, reaction);				
				}
				return created(location, "postChannelReaction", response);					
			}
			
		} catch (RuntimeException e) {
			return badrequest("postChannelReaction", response, e, reaction);
		}
	}

	@GetMapping("/{cid:.*}/reaction/{id:.*}")
	public ResponseEntity<Reaction> getChannelReaction(@PathVariable("cid") String cid, @PathVariable("id") String id,
			ReactionOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {

		try {
			setupReadOnlyRequest(principal, options, request);
			Reaction reaction = manager.getChannelReaction(cid, id, options);
			if (reaction == null) {
				return notfound("getChannelReaction", response, Reaction.class, id);
			}
			return ok(reaction, "getChannelReaction", response);
		} catch (RuntimeException e) {
			return badrequest("getChannelReaction", response, e, cid, id);
		}
	}

	@PutMapping("/{cid:.*}/reaction/{id:.*}")
	public ResponseEntity<Void> updateChannelReaction(@PathVariable("cid") String cid,  @PathVariable("id") String id,
			@RequestBody Reaction reaction, 
			RequestOptions options,
		Principal principal, HttpServletRequest request, HttpServletResponse response) {
		try {
			setupRequest(principal, options, request);
			Reaction reaction2 = manager.updateChannelReaction(cid, reaction, options);
			if (reaction2 == null) {
				return badrequest("updateChannelReaction", response, reaction);
			}
			return nocontent("updateChannelReaction", response, reaction);
		} catch (RuntimeException e) {
			return badrequest("updateChannelReaction", response, e, reaction);
		}
	}

	@DeleteMapping("/{cid:.*}/reaction/{id:.*}")
	public ResponseEntity<Void> deleteChannelReaction(@PathVariable("cid") String cid, @PathVariable("id") String id,
			RequestOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			setupRequest(principal, options, request);
			boolean b = manager.deleteChannelReaction(cid, id, options);
			if (!b) {
				return badrequest("deleteChannelReaction:", response, id);
			}
			return nocontent("deleteChannelReaction", response, cid, id);
		} catch (RuntimeException e) {
			return badrequest("deleteChannelReaction", response, e, cid, id);
		}
	}
	
	//
	// Reactions
	//
	

	@GetMapping("/{cid:.*}/message/{mid:.*}/reaction")
	public ResponseEntity<Page<Reaction>> listReactions(@PathVariable("cid") String cid, @PathVariable("mid") String mid,
			ReactionFilter filter, PageOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {

		try {
			setupReadOnlyRequest(principal, filter, request);
			Page<Reaction> page = manager.listReactions(cid, mid, filter, options.toPageRequest());
			if (page==null) {
				return badrequest("listReactions", response, cid, mid);				
			}
			return ok(page, "listReactions", response, PageUtil.toString(page), filter, options);
		} catch (RuntimeException e) {
			return badrequest("listReactions", response, e, cid, mid, filter, options);
		}
	}
	
	@GetMapping("/{cid:.*}/message/{mid:.*}/reactionstats")
	public ResponseEntity<Reactions> getReactionStats(@PathVariable("cid") String cid, @PathVariable("mid") String mid,
		ReactionFilter filter, PageOptions options,
		Principal principal, HttpServletRequest request, HttpServletResponse response) {

		try {
			setupRequest(principal, filter, request);
			Reactions reactions = manager.getReactionStats(cid, mid, filter, options.toPageRequest());
			if (reactions==null) {
				return badrequest("getReactionStats", response, cid, mid);				
			}
			return ok(reactions, "getReactionStats", response);
		} catch (RuntimeException e) {
			return badrequest("getReactionStats", response, e, cid, mid);
		}
	}

	@PostMapping("/{cid:.*}/message/{mid:.*}/reaction")
	public ResponseEntity<Void> postReaction(@PathVariable("cid") String cid, @PathVariable("mid") String mid, 
			@RequestBody Reaction reaction, 
			@RequestParam(required = false) Boolean cancel,
			RequestOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {

		try {
			setupRequest(principal, options, request);
			if (Boolean.TRUE.equals(cancel)) {
				if (manager.cancelReaction(cid, mid, reaction, options)==null) {
					return badrequest("postReaction", response);
				}
				return nocontent("postReaction", response);
			} else {
				URI location = manager.postReaction(cid, mid, reaction, options); 			
				if (location==null) {
					return badrequest("postReaction", response, cid, mid, reaction);				
				}
				return created(location, "postReaction", response);				
			}
		} catch (RuntimeException e) {
			return badrequest("postReaction", response, e, reaction);
		}
	}

	@GetMapping("/{cid:.*}/message/{mid:.*}/reaction/{id:.*}")
	public ResponseEntity<Reaction> getReaction(@PathVariable("cid") String cid, @PathVariable("mid") String mid, @PathVariable("id") String id,
			ReactionOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {

		try {
			setupReadOnlyRequest(principal, options, request);
			Reaction reaction = manager.getReaction(cid, mid, id, options);
			if (reaction == null) {
				return notfound("getReaction", response, cid, mid);
			}
			return ok(reaction, "getReaction", response);
		} catch (RuntimeException e) {
			return badrequest("getReaction", response, e, cid, mid);
		}
	}

	@PutMapping("/{cid:.*}/message/{mid:.*}/reaction/{id:.*}")
	public ResponseEntity<Void> updateReaction(@PathVariable("cid") String cid, @PathVariable("mid") String mid,  @PathVariable("id") String id,
			@RequestBody Reaction reaction, 
			RequestOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {

		try {
			setupRequest(principal, options, request);
			Reaction reaction2 = manager.updateReaction(cid, mid, reaction, options);
			if (reaction2 == null) {
				return badrequest("updateReaction", response, reaction);
			}
			return nocontent("updateReaction", response, reaction);
		} catch (RuntimeException e) {
			return badrequest("updateReaction", response, e, reaction);
		}
	}

	@DeleteMapping("/{cid:.*}/message/{mid:.*}/reaction/{id:.*}")
	public ResponseEntity<Void> deleteReaction(@PathVariable("cid") String cid, @PathVariable("mid") String mid, @PathVariable("id") String id,
			RequestOptions options,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {

		try {
			setupRequest(principal, options, request);
			boolean b = manager.deleteReaction(cid, mid, id, options);
			if (!b) {
				return badrequest("deleteReaction:", response, id);
			}
			return nocontent("deleteReaction", response, cid, mid, id);
		} catch (RuntimeException e) {
			return badrequest("deleteReaction", response, e, cid, mid, id);
		}
	}
	
	
	
	
	protected void setupReadOnlyRequest(Principal principal, RequestOptions options, HttpServletRequest request) {
		setupRequest(principal, options, request);
	}
	
	protected void setupRequest(Principal principal, RequestOptions options, HttpServletRequest request) {
	}
}

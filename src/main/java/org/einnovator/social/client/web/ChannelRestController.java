package org.einnovator.social.client.web;

import java.net.URI;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.einnovator.social.client.manager.ChannelManager;
import org.einnovator.social.client.model.Channel;
import org.einnovator.social.client.model.Message;
import org.einnovator.social.client.modelx.ChannelFilter;
import org.einnovator.social.client.modelx.MessageFilter;
import org.einnovator.util.PageOptions;
import org.einnovator.util.PageUtil;
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



@RestController
@RequestMapping("/api/channel")
public class ChannelRestController extends ControllerBase {

	@Autowired
	private ChannelManager manager;


	@GetMapping
	public ResponseEntity<Page<Channel>> listChannel(ChannelFilter filter, PageOptions options, @RequestParam(required=false) Boolean publish,
		Principal principal, HttpServletResponse response) {
		try {
			setupToken(principal);
			Page<Channel> channels = manager.listChannels(filter, options.toPageRequest());
			return ok(channels, "listChannel", response, PageUtil.toString(channels), filter, options);
		} catch (RuntimeException e) {
			return status("listChannel", e, response, filter, options);
		}
	}
	

	
	@PostMapping
	public ResponseEntity<Void> createChannel(@RequestBody Channel channel, HttpServletRequest request, @RequestParam(required=false) Boolean publish,
		Principal principal, HttpServletResponse response) {
		try {
			setupToken(principal);
			URI location =  manager.createChannel(channel);
			return created(location, "createChannel", response, channel);
		} catch (RuntimeException e) {
			return status("createChannel", e, response, channel);
		}
	}
	
	@GetMapping("/{id:.*}")
	public ResponseEntity<Channel> getChannel(@PathVariable String id, @RequestParam(required=false) Boolean publish,
		Principal principal, HttpServletResponse response) {
		try {
			setupToken(principal);
			Channel channel = manager.getChannel(id);
			return ok(channel, "getChannel", response);
		} catch (RuntimeException e) {
			return status("getChannel", e, response, id);
		}		
	}
	
	@PutMapping("/{id:.*}")
	public ResponseEntity<Void> updateChannel(@RequestBody Channel channel, @PathVariable String id, @RequestParam(required=false) Boolean publish, 
			Principal principal, HttpServletResponse response) {			
		try {
			setupToken(principal);
			if (manager.updateChannel(channel)==null) {
				return badrequest("getChannel", response);
			}
			return nocontent("getChannel", response);
		} catch (RuntimeException e) {
			return status("getChannel", e, response, id);
		}		
	}
	
	@DeleteMapping("/{channelId:.*}")
	public ResponseEntity<Void> deleteChannel(@PathVariable String id, @RequestParam(required=false) Boolean publish,
			Principal principal, HttpServletResponse response) {			
		try {
			setupToken(principal);
			if (!manager.deleteChannel(id)) {
				return badrequest("deleteChannel", response);
			}
			return nocontent("deleteChannel", response);
		} catch (RuntimeException e) {
			return status("deleteChannel", e, response, id);
		}		
	}
	

	@GetMapping("/{cid:.*}/message")
	public ResponseEntity<Page<Message>> listMessagesFor(@PathVariable("cid") String cid, MessageFilter filter, PageOptions options,
			Principal principal, HttpServletResponse response) {		
		try {
			setupToken(principal);
			Page<Message> page = manager.listMessages(cid, filter, options.toPageRequest());
			return ok(page, "deleteChannel", response);
		} catch (RuntimeException e) {
			return status("deleteChannel", e, response, cid);
		}	
	}

	@PostMapping("/{cid:.*}/message")
	public ResponseEntity<Void> postMessage(@PathVariable("cid") String cid,
			@RequestBody Message msg, BindingResult errors,
			Principal principal, HttpServletRequest request, HttpServletResponse response) {
		try {
			setupToken(principal);
			URI location =  manager.postMessage(cid, msg);
			return created(location, "postMessage", response, location);
		} catch (RuntimeException e) {
			return status("createChannel", e, response);
		}
	}

	@PutMapping("/{cid:.*}/message/{id:.*}")
	public ResponseEntity<Void> updateMessage(@PathVariable("cid") String cid, @PathVariable("id") String id,
			@RequestBody Message msg, BindingResult errors,
			Principal principal, HttpServletResponse response) {
		try {
			setupToken(principal);
			if (manager.updateMessage(cid, msg)==null) {
				return badrequest("updateMessage", response);
			}
			return nocontent("updateMessage", response);
		} catch (RuntimeException e) {
			return status("updateMessage", e, response, id);
		}		
	}

	@DeleteMapping("/{cid:.*}/message/{id:.*}")
	public ResponseEntity<Void> deleteMessage(@PathVariable("cid") String cid, @PathVariable("id") String id,
			Principal principal, HttpServletResponse response) {
		try {
			setupToken(principal);
			if (!manager.deleteMessage(cid, id)) {
				return badrequest("deleteMessage", response);
			}
			return nocontent("deleteMessage", response);
		} catch (RuntimeException e) {
			return status("deleteChannel", e, response, id);
		}		
	}

	protected void setupToken(Principal principal) {
		
	}
}

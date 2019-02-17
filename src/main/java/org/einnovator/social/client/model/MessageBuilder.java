package org.einnovator.social.client.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.einnovator.util.model.EntityBase;

public class MessageBuilder extends EntityBase {

	private String subject;

	private MessageType type;

	private String content;

	private Long followerCount;
	
	private Boolean blocked;

	private String blockedBy;
	
	private String blockedDate;

	private Boolean reported;

	private String reportedBy;
	
	private String reportedDate;

	private Long viewCount;
	
	private Integer size;

	private Integer number;
	
	private Date sentDate;
	
	private String contentType;
	
	private String from;

	private String to;

	private String cc;
	
	private String bcc;

	private String replyTo;

	private Map<String, List<String>> headers;
	
	//private User reportedByUser;

	//private User blockedByUser;

	private List<Attachment> attachments;
	
	private List<Message> comments;
	
	private List<Reaction> reactions = new ArrayList<Reaction>();
	

	public MessageBuilder subject(String subject) {
		this.subject = subject;
		return this;
	}

	public MessageBuilder type(MessageType type) {
		this.type = type;
		return this;
	}
	
	public MessageBuilder content(String content) {
		this.content = content;
		return this;
	}
	
	public MessageBuilder followerCount(Long followerCount) {
		this.followerCount = followerCount;
		return this;
	}
	
	public MessageBuilder blocked(Boolean blocked) {
		this.blocked = blocked;
		return this;
	}
	
	public MessageBuilder blockedBy(String blockedBy) {
		this.blockedBy = blockedBy;
		return this;
	}

	public MessageBuilder blockedDate(String blockedDate) {
		this.blockedDate = blockedDate;
		return this;
	}

	public MessageBuilder reported(Boolean reported) {
		this.reported = reported;
		return this;
	}
	
	public MessageBuilder reportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
		return this;
	}

	public MessageBuilder reportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
		return this;
	}

	public MessageBuilder viewCount(Long viewCount) {
		this.viewCount = viewCount;
		return this;
	}

	public MessageBuilder sentDate(Date sentDate) {
		this.sentDate = sentDate;
		return this;
	}

	public MessageBuilder contentType(String contentType) {
		this.contentType = contentType;
		return this;
	}

	public MessageBuilder from(String from) {
		this.from = from;
		return this;
	}

	public MessageBuilder to(String to) {
		this.to = to;
		return this;
	}

	public MessageBuilder cc(String cc) {
		this.cc = cc;
		return this;
	}

	public MessageBuilder bcc(String bcc) {
		this.bcc = bcc;
		return this;
	}

	public MessageBuilder replyTo(String replyTo) {
		this.replyTo = replyTo;
		return this;
	}

	public MessageBuilder headers(Map<String, List<String>> headers) {
		this.headers = headers;
		return this;
	}

	public MessageBuilder header(String name, String... values) {
		if (this.headers==null) {
			this.headers = new LinkedHashMap<>();
		}
		this.headers.put(name, new ArrayList<>(Arrays.asList(values)));
		return this;
	}


	public MessageBuilder attachments(Attachment... attachments) {
		if (this.attachments==null) {
			this.attachments = new ArrayList<>();
		}
		this.attachments.addAll(Arrays.asList(attachments));
		return this;
	}

	public MessageBuilder comments(Message... comments) {
		if (this.comments==null) {
			this.comments = new ArrayList<>();
		}
		this.comments.addAll(Arrays.asList(comments));
		return this;
	}

	public MessageBuilder reaction(Reaction... reactions) {
		if (this.reactions==null) {
			this.reactions = new ArrayList<>();
		}
		this.reactions.addAll(Arrays.asList(reactions));
		return this;
	}
	

	public Message build() {
		Message msg = new Message();
		msg.setSubject(subject);
		msg.setType(type);
		msg.setContent(content);
		msg.setFollowerCount(followerCount);
		msg.setBlocked(blocked);
		msg.setBlockedBy(blockedBy);
		msg.setBlockedDate(blockedDate);		
		msg.setReported(reported);
		msg.setReportedBy(reportedBy);
		msg.setReportedDate(reportedDate);
		msg.setViewCount(viewCount);
		msg.setSize(size);
		msg.setNumber(number);
		msg.setSentDate(sentDate);
		msg.setContentType(contentType);
		msg.setFrom(from);
		msg.setTo(to);
		msg.setCc(cc);
		msg.setBcc(bcc);
		msg.setReplyTo(replyTo);
		msg.setHeaders(headers);
		msg.setAttachments(attachments);
		msg.setComments(comments);
		msg.setReactions(reactions);

		return msg;
	}
	
}

package org.einnovator.social.client.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message extends EntityBase {

	private String subject;

	private MessageType type;

	private String content;

	private List<Attachment> attachments;
	
	private List<Message> comments;
	
	private List<Reaction> reactions = new ArrayList<Reaction>();
	
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
	
	private Map<String, Object> createdByUser;

	private Map<String, Object> lastModifiedByUser;

	private Map<String, Object> reportedByUser;

	private Map<String, Object> blockedByUser;
	
	public Message() {
	}
	
	/**
	 * Get the value of property {@code subject}.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Set the value of property {@code subject}.
	 *
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Get the value of property {@code type}.
	 *
	 * @return the type
	 */
	public MessageType getType() {
		return type;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the type to set
	 */
	public void setType(MessageType type) {
		this.type = type;
	}

	/**
	 * Get the value of property {@code content}.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Set the value of property {@code content}.
	 *
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Get the value of property {@code attachments}.
	 *
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * Set the value of property {@code attachments}.
	 *
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * Get the value of property {@code comments}.
	 *
	 * @return the comments
	 */
	public List<Message> getComments() {
		return comments;
	}

	/**
	 * Set the value of property {@code comments}.
	 *
	 * @param comments the comments to set
	 */
	public void setComments(List<Message> comments) {
		this.comments = comments;
	}


	/**
	 * Get the value of property {@code reactions}.
	 *
	 * @return the reactions
	 */
	public List<Reaction> getReactions() {
		return reactions;
	}

	/**
	 * Set the value of property {@code reactions}.
	 *
	 * @param reactions the reactions to set
	 */
	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}

	/**
	 * Get the value of property {@code followerCount}.
	 *
	 * @return the followerCount
	 */
	public Long getFollowerCount() {
		return followerCount;
	}

	/**
	 * Set the value of property {@code followerCount}.
	 *
	 * @param followerCount the followerCount to set
	 */
	public void setFollowerCount(Long followerCount) {
		this.followerCount = followerCount;
	}

	/**
	 * Get the value of property {@code blocked}.
	 *
	 * @return the blocked
	 */
	public Boolean getBlocked() {
		return blocked;
	}

	/**
	 * Set the value of property {@code blocked}.
	 *
	 * @param blocked the blocked to set
	 */
	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	/**
	 * Get the value of property {@code blockedBy}.
	 *
	 * @return the blockedBy
	 */
	public String getBlockedBy() {
		return blockedBy;
	}

	/**
	 * Set the value of property {@code blockedBy}.
	 *
	 * @param blockedBy the blockedBy to set
	 */
	public void setBlockedBy(String blockedBy) {
		this.blockedBy = blockedBy;
	}

	/**
	 * Get the value of property {@code blockedDate}.
	 *
	 * @return the blockedDate
	 */
	public String getBlockedDate() {
		return blockedDate;
	}

	/**
	 * Set the value of property {@code blockedDate}.
	 *
	 * @param blockedDate the blockedDate to set
	 */
	public void setBlockedDate(String blockedDate) {
		this.blockedDate = blockedDate;
	}

	/**
	 * Get the value of property {@code reported}.
	 *
	 * @return the reported
	 */
	public Boolean getReported() {
		return reported;
	}


	/**
	 * Set the value of property {@code reported}.
	 *
	 * @param reported the reported to set
	 */
	public void setReported(Boolean reported) {
		this.reported = reported;
	}

	/**
	 * Get the value of property {@code reportedBy}.
	 *
	 * @return the reportedBy
	 */
	public String getReportedBy() {
		return reportedBy;
	}

	/**
	 * Set the value of property {@code reportedBy}.
	 *
	 * @param reportedBy the reportedBy to set
	 */
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	/**
	 * Get the value of property {@code reportedDate}.
	 *
	 * @return the reportedDate
	 */
	public String getReportedDate() {
		return reportedDate;
	}

	/**
	 * Set the value of property {@code reportedDate}.
	 *
	 * @param reportedDate the reportedDate to set
	 */
	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}

	/**
	 * Get the value of property {@code viewCount}.
	 *
	 * @return the viewCount
	 */
	public Long getViewCount() {
		return viewCount;
	}

	/**
	 * Set the value of property {@code viewCount}.
	 *
	 * @param viewCount the viewCount to set
	 */
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	/**
	 * Get the value of property {@code size}.
	 *
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * Set the value of property {@code size}.
	 *
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * Get the value of property {@code number}.
	 *
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * Set the value of property {@code number}.
	 *
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}


	/**
	 * Get the value of property {@code sentDate}.
	 *
	 * @return the sentDate
	 */
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * Set the value of property {@code sentDate}.
	 *
	 * @param sentDate the sentDate to set
	 */
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}


	/**
	 * Get the value of property {@code contentType}.
	 *
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Set the value of property {@code contentType}.
	 *
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * Get the value of property {@code from}.
	 *
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Set the value of property {@code from}.
	 *
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Get the value of property {@code to}.
	 *
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Set the value of property {@code to}.
	 *
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * Get the value of property {@code cc}.
	 *
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * Set the value of property {@code cc}.
	 *
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/**
	 * Get the value of property {@code bcc}.
	 *
	 * @return the bcc
	 */
	public String getBcc() {
		return bcc;
	}

	/**
	 * Set the value of property {@code bcc}.
	 *
	 * @param bcc the bcc to set
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	/**
	 * Get the value of property {@code replyTo}.
	 *
	 * @return the replyTo
	 */
	public String getReplyTo() {
		return replyTo;
	}

	/**
	 * Set the value of property {@code replyTo}.
	 *
	 * @param replyTo the replyTo to set
	 */
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	/**
	 * Get the value of property {@code headers}.
	 *
	 * @return the headers
	 */
	public Map<String, List<String>> getHeaders() {
		return headers;
	}


	/**
	 * Set the value of property {@code headers}.
	 *
	 * @param headers the headers to set
	 */
	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}
	

	/**
	 * Get the value of property {@code createdByUser}.
	 *
	 * @return the createdByUser
	 */
	public Map<String, Object> getCreatedByUser() {
		return createdByUser;
	}

	/**
	 * Set the value of property {@code createdByUser}.
	 *
	 * @param createdByUser the createdByUser to set
	 */
	public void setCreatedByUser(Map<String, Object> createdByUser) {
		this.createdByUser = createdByUser;
	}

	/**
	 * Get the value of property {@code lastModifiedByUser}.
	 *
	 * @return the lastModifiedByUser
	 */
	public Map<String, Object> getLastModifiedByUser() {
		return lastModifiedByUser;
	}

	/**
	 * Set the value of property {@code lastModifiedByUser}.
	 *
	 * @param lastModifiedByUser the lastModifiedByUser to set
	 */
	public void setLastModifiedByUser(Map<String, Object> lastModifiedByUser) {
		this.lastModifiedByUser = lastModifiedByUser;
	}

	/**
	 * Get the value of property {@code reportedByUser}.
	 *
	 * @return the reportedByUser
	 */
	public Map<String, Object> getReportedByUser() {
		return reportedByUser;
	}

	/**
	 * Set the value of property {@code reportedByUser}.
	 *
	 * @param reportedByUser the reportedByUser to set
	 */
	public void setReportedByUser(Map<String, Object> reportedByUser) {
		this.reportedByUser = reportedByUser;
	}

	/**
	 * Get the value of property {@code blockedByUser}.
	 *
	 * @return the blockedByUser
	 */
	public Map<String, Object> getBlockedByUser() {
		return blockedByUser;
	}

	/**
	 * Set the value of property {@code blockedByUser}.
	 *
	 * @param blockedByUser the blockedByUser to set
	 */
	public void setBlockedByUser(Map<String, Object> blockedByUser) {
		this.blockedByUser = blockedByUser;
	}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("subject", subject)
				.append("content", content)
				.append("type", type)
				.append("size", size)
				.append("contentType", contentType)
				.append("number", number)
				.append("from", from)
				.append("to", to)
				.append("cc", cc)
				.append("bcc", bcc)
				.append("replyTo", replyTo)
				.append("attachments", attachments)
				.append("comments", comments)
				.append("followerCount", followerCount)
				.append("blocked", blocked)
				.append("blockedBy", blockedBy)
				.append("blockedDate", blockedDate)
				.append("reported", reported)
				.append("reportedBy", reportedBy)
				.append("reportedDate", reportedDate)
				.append("viewCount", viewCount)
				;
	}


}

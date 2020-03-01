package org.einnovator.social.client.model;

import java.util.List;

import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * A Message.
 * 
 * @author support@einnovator.org
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message extends EntityBase {

	private MessageType type;

	private MessageStatus status;

	private String title;

	private String content;

	private Integer size;
	
	private List<Attachment> attachments;
	
	private List<Message> tree;
	
	private Long watchCount;
	
	private Boolean reported;

	private String reportedBy;
	
	private String reportedDate;
	
	private Object reportedByUser;

	private List<Reaction> reactions;
	
	
	/**
	 * Create instance of {@code Message}.
	 *
	 */
	public Message() {
	}

	/**
	 * Create instance of {@code Message}.
	 *
	 * @param obj a prototype
	 */
	public Message(Object obj) {
		super(obj);
	}
	
	//
	// Getters/Setters
	//

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
	 * @param type the value of property type
	 */
	public void setType(MessageType type) {
		this.type = type;
	}

	/**
	 * Get the value of property {@code status}.
	 *
	 * @return the status
	 */
	public MessageStatus getStatus() {
		return status;
	}

	/**
	 * Set the value of property {@code status}.
	 *
	 * @param status the value of property status
	 */
	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	/**
	 * Get the value of property {@code title}.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the value of property {@code title}.
	 *
	 * @param title the value of property title
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @param content the value of property content
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @param size the value of property size
	 */
	public void setSize(Integer size) {
		this.size = size;
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
	 * @param attachments the value of property attachments
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * Get the value of property {@code tree}.
	 *
	 * @return the tree
	 */
	public List<Message> getTree() {
		return tree;
	}

	/**
	 * Set the value of property {@code tree}.
	 *
	 * @param tree the value of property tree
	 */
	public void setTree(List<Message> tree) {
		this.tree = tree;
	}

	/**
	 * Get the value of property {@code watchCount}.
	 *
	 * @return the watchCount
	 */
	public Long getWatchCount() {
		return watchCount;
	}

	/**
	 * Set the value of property {@code watchCount}.
	 *
	 * @param watchCount the value of property watchCount
	 */
	public void setWatchCount(Long watchCount) {
		this.watchCount = watchCount;
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
	 * @param reported the value of property reported
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
	 * @param reportedBy the value of property reportedBy
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
	 * @param reportedDate the value of property reportedDate
	 */
	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}

	/**
	 * Get the value of property {@code reportedByUser}.
	 *
	 * @return the reportedByUser
	 */
	public Object getReportedByUser() {
		return reportedByUser;
	}

	/**
	 * Set the value of property {@code reportedByUser}.
	 *
	 * @param reportedByUser the value of property reportedByUser
	 */
	public void setReportedByUser(Object reportedByUser) {
		this.reportedByUser = reportedByUser;
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
	 * @param reactions the value of property reactions
	 */
	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}

	//
	// With
	//


	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 */
	public Message withType(MessageType type) {
		this.type = type;
		return this;
	}

	/**
	 * Set the value of property {@code status}.
	 *
	 * @param status the value of property status
	 */
	public Message withStatus(MessageStatus status) {
		this.status = status;
		return this;
	}

	/**
	 * Set the value of property {@code title}.
	 *
	 * @param title the value of property title
	 */
	public Message withTitle(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Set the value of property {@code content}.
	 *
	 * @param content the value of property content
	 */
	public Message withContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * Set the value of property {@code size}.
	 *
	 * @param size the value of property size
	 */
	public Message withSize(Integer size) {
		this.size = size;
		return this;
	}

	/**
	 * Set the value of property {@code attachments}.
	 *
	 * @param attachments the value of property attachments
	 */
	public Message withAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
		return this;
	}

	/**
	 * Set the value of property {@code tree}.
	 *
	 * @param tree the value of property tree
	 */
	public Message withTree(List<Message> tree) {
		this.tree = tree;
		return this;
	}

	/**
	 * Set the value of property {@code watchCount}.
	 *
	 * @param watchCount the value of property watchCount
	 */
	public Message withWatchCount(Long watchCount) {
		this.watchCount = watchCount;
		return this;
	}

	/**
	 * Set the value of property {@code reported}.
	 *
	 * @param reported the value of property reported
	 */
	public Message withReported(Boolean reported) {
		this.reported = reported;
		return this;
	}

	/**
	 * Set the value of property {@code reportedBy}.
	 *
	 * @param reportedBy the value of property reportedBy
	 */
	public Message withReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
		return this;
	}

	/**
	 * Set the value of property {@code reportedDate}.
	 *
	 * @param reportedDate the value of property reportedDate
	 */
	public Message withReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
		return this;
	}

	/**
	 * Set the value of property {@code reportedByUser}.
	 *
	 * @param reportedByUser the value of property reportedByUser
	 */
	public Message withReportedByUser(Object reportedByUser) {
		this.reportedByUser = reportedByUser;
		return this;
	}

	/**
	 * Set the value of property {@code reactions}.
	 *
	 * @param reactions the value of property reactions
	 */
	public Message withReactions(List<Reaction> reactions) {
		this.reactions = reactions;
		return this;
	}

	
	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("title", title)
				.append("content", content)
				.append("type", type)
				.append("size", size)
				.append("attachments", attachments)
				.append("tree", tree)
				.append("watchCount", watchCount)
				.append("reported", reported)
				.append("reportedBy", reportedBy)
				.append("reportedDate", reportedDate)
				;
	}
}

package org.einnovator.social.client.model;

import java.util.ArrayList;
import java.util.List;

import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message extends EntityBase {

	private String title;

	private String type;

	private String content;

	private List<Attachment> attachments;
	
	private List<Message> messages;
	
	private List<Impression> impressions = new ArrayList<Impression>();
	
	private Long followerCount;
	
	private Boolean blocked;

	private String blockedBy;
	
	private String blockedDate;

	private Boolean reported;

	private String reportedBy;
	
	private String reportedDate;

	private Long viewCount;
	
	public Message() {
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Message> getNotes() {
		return messages;
	}

	public void setNotes(List<Message> messages) {
		this.messages = messages;
	}
	

	public List<Impression> getImpressions() {
		return impressions;
	}

	public void setImpressions(List<Impression> impressions) {
		this.impressions = impressions;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public Long getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(Long followerCount) {
		this.followerCount = followerCount;
	}

	public Boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	public Boolean getReported() {
		return reported;
	}

	public void setReported(Boolean reported) {
		this.reported = reported;
	}

	public String getBlockedBy() {
		return blockedBy;
	}

	public void setBlockedBy(String blockedBy) {
		this.blockedBy = blockedBy;
	}

	public String getBlockedDate() {
		return blockedDate;
	}

	public void setBlockedDate(String blockedDate) {
		this.blockedDate = blockedDate;
	}

	public String getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	public String getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("title", title)
				.append("type", type)
				.append("content", content)
				.append("attachments", attachments)
				.append("messages", messages)
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

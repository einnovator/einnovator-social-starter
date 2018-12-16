package org.einnovator.social.client.model;

import java.util.List;

import org.einnovator.meta.client.model.Meta;
import org.einnovator.meta.client.model.Tagging;
import org.einnovator.util.model.EntityBase;
import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MBox extends EntityBase {

	private String metaId;

	private Meta meta;

	private String taggingId;

	private Tagging tagging;

	private Message head;

	private List<Message> messages;

	
	public MBox() {
	}

	public String getMetaId() {
		return metaId;
	}

	public void setMetaId(String metaId) {
		this.metaId = metaId;
	}

	public Message getHead() {
		return head;
	}

	public void setHead(Message head) {
		this.head = head;
	}

	public List<Message> getNotes() {
		return messages;
	}

	public void setNotes(List<Message> messages) {
		this.messages = messages;
	}


	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public String getTaggingId() {
		return taggingId;
	}

	public void setTaggingId(String taggingId) {
		this.taggingId = taggingId;
	}

	public Tagging getTagging() {
		return tagging;
	}

	public void setTagging(Tagging tagging) {
		this.tagging = tagging;
	}

	@JsonIgnore
	public String getType() {
		return meta!=null ? meta.getType() : null;
	}
	
	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("metaId", metaId)
				.append("head", head)
				.append("tagging", tagging)
				.append("meta", meta)
				.append("messages", messages)
				;
	}
	
}

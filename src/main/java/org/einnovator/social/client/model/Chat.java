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
public class Chat extends EntityBase {

	private String metaId;

	private Meta meta;

	private String taggingId;

	private Tagging tagging;

	private Note head;

	private List<Note> notes;

	
	public Chat() {
	}

	public String getMetaId() {
		return metaId;
	}

	public void setMetaId(String metaId) {
		this.metaId = metaId;
	}

	public Note getHead() {
		return head;
	}

	public void setHead(Note head) {
		this.head = head;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
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
				.append("notes", notes)
				;
	}
	
}

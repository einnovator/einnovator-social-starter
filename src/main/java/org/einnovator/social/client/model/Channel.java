package org.einnovator.social.client.model;

import java.util.ArrayList;
import java.util.List;

import org.einnovator.util.model.Ref;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A Channel.
 * 
 * @author support@einnovator.org
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Channel extends ProtectedEntity {

	protected ChannelType type;

	protected ChannelStatus status;

	protected String name;

	protected String purpose;

	protected String img;

	protected String thumbnail;

	protected Ref ref;

	private List<Attachment> attachments;
	
	/**
	 * Create instance of {@code Channel}.
	 *
	 */
	public Channel() {
	}

	//
	// Getters/Setters
	//

	/**
	 * Get the value of property {@code type}.
	 *
	 * @return the type
	 */
	public ChannelType getType() {
		return type;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 */
	public void setType(ChannelType type) {
		this.type = type;
	}

	/**
	 * Get the value of property {@code status}.
	 *
	 * @return the status
	 */
	public ChannelStatus getStatus() {
		return status;
	}

	/**
	 * Set the value of property {@code status}.
	 *
	 * @param status the value of property status
	 */
	public void setStatus(ChannelStatus status) {
		this.status = status;
	}
	
	/**
	 * Get the value of property {@code name}.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the value of property {@code name}.
	 *
	 * @param name the value of property name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the value of property {@code purpose}.
	 *
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * Set the value of property {@code purpose}.
	 *
	 * @param purpose the value of property purpose
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * Get the value of property {@code img}.
	 *
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * Set the value of property {@code img}.
	 *
	 * @param img the value of property img
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * Get the value of property {@code thumbnail}.
	 *
	 * @return the thumbnail
	 */
	public String getThumbnail() {
		return thumbnail;
	}

	/**
	 * Set the value of property {@code thumbnail}.
	 *
	 * @param thumbnail the value of property thumbnail
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * Get the value of property {@code ref}.
	 *
	 * @return the ref
	 */
	public Ref getRef() {
		return ref;
	}

	/**
	 * Set the value of property {@code ref}.
	 *
	 * @param ref the value of property ref
	 */
	public void setRef(Ref ref) {
		this.ref = ref;
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


	//
	// With
	//

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 * @return this {@code Channel}
	 */
	public Channel withType(ChannelType type) {
		this.type = type;
		return this;
	}
	
	/**
	 * Set the value of property {@code status}.
	 *
	 * @param status the value of property status
	 * @return this {@code Channel}
	 */
	public Channel withStatus(ChannelStatus status) {
		this.status = status;
		return this;
	}
	
	/**
	 * Set the value of property {@code name}.
	 *
	 * @param name the value of property name
	 * @return this {@code Channel}
	 */
	public Channel withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Set the value of property {@code purpose}.
	 *
	 * @param purpose the value of property purpose
	 */
	public Channel withPurpose(String purpose) {
		this.purpose = purpose;
		return this;
	}

	/**
	 * Set the value of property {@code img}.
	 *
	 * @param img the value of property img
	 * @return this {@code Channel}
	 */
	public Channel withImg(String img) {
		this.img = img;
		return this;
	}


	/**
	 * Set the value of property {@code thumbnail}.
	 *
	 * @param thumbnail the value of property thumbnail
	 */
	public Channel withThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
		return this;
	}


	/**
	 * Set the value of property {@code ref}.
	 *
	 * @param ref the value of property ref
	 * @return this {@code Channel}
	 */
	public Channel withRef(Ref ref) {
		this.ref = ref;
		return this;
	}


	/**
	 * Set the value of property {@code attachments}.
	 *
	 * @param attachments the value of property attachments
	 * @return this {@code Channel}
	 */
	public Channel withAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
		return this;
	}


	public Channel withAttachments(Attachment... attachments) {
		if (this.attachments==null) {
			this.attachments = new ArrayList<>();
		}
		for (Attachment attachment: attachments) {
			this.attachments.add(attachment);
			
		}
		return this;
	}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("name", name)
				.append("type", type)
				.append("status", status)
				.append("img", img)
				.append("thumbnail", thumbnail)
				.append("attachments", attachments)
				.append("#purpose", purpose!=null ? purpose.length() : null)
				;
	}
	
}

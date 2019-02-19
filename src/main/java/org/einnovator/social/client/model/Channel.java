package org.einnovator.social.client.model;

import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.Ref;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Channel extends EntityBase {

	private ChannelType type;

	private String owner;
	
	private String name;

	private String purpose;

	private String img;

	private String thumbnail;

	private Ref ref;

	private Message head;

	//private User ownerUser;
	
	public Channel() {
	}

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
	 * @param type the type to set
	 */
	public void setType(ChannelType type) {
		this.type = type;
	}

	/**
	 * Get the value of property {@code owner}.
	 *
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Set the value of property {@code owner}.
	 *
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
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
	 * @param name the name to set
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
	 * @param purpose the purpose to set
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
	 * @param img the img to set
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
	 * @param thumbnail the thumbnail to set
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
	 * @param ref the ref to set
	 */
	public void setRef(Ref ref) {
		this.ref = ref;
	}

	/**
	 * Get the value of property {@code head}.
	 *
	 * @return the head
	 */
	public Message getHead() {
		return head;
	}


	/**
	 * Set the value of property {@code head}.
	 *
	 * @param head the head to set
	 */
	public void setHead(Message head) {
		this.head = head;
	}

	
	/**
	 * Get the value of property {@code ownerUser}.
	 *
	 * @return the ownerUser
	 */
	//public User getOwnerUser() {
	//	return ownerUser;
	//}

	/**
	 * Set the value of property {@code ownerUser}.
	 *
	 * @param ownerUser the ownerUser to set
	 */
	//public void setOwnerUser(User ownerUser) {
	//	this.ownerUser = ownerUser;
	//}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("name", name)
				.append("type", type)
				.append("owner", owner)
				.append("img", img)
				.append("thumbnail", thumbnail)
				.append("head", head)
				;
	}

	
}

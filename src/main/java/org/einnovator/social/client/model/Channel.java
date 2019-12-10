package org.einnovator.social.client.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.Ref;
import org.einnovator.util.model.ToStringCreator;
import org.einnovator.util.security.Authority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Channel extends EntityBase {

	protected ChannelType type;

	protected String owner;
	
	protected String name;

	protected String purpose;

	protected String img;

	protected String thumbnail;

	protected Ref ref;

	protected Message head;

	protected List<Authority> authorities;

	protected Boolean publik;
	
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
	 * Get the value of property {@code authorities}.
	 *
	 * @return the authorities
	 */
	public List<Authority> getAuthorities() {
		return authorities;
	}

	/**
	 * Set the value of property {@code authorities}.
	 *
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * Get the value of property {@code publik}.
	 *
	 * @return the publik
	 */
	public Boolean getPublic() {
		return publik;
	}


	/**
	 * Set the value of property {@code publik}.
	 *
	 * @param publik the value of property publik
	 */
	public void setPublic(Boolean publik) {
		this.publik = publik;
	}
	
	// With
	
	public Channel withUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public Channel withType(ChannelType type) {
		this.type = type;
		return this;
	}

	public Channel withOwner(String owner) {
		this.owner = owner;
		return this;
	}

	public Channel withName(String name) {
		this.name = name;
		return this;
	}

	public Channel withPurpose(String purpose) {
		this.purpose = purpose;
		return this;
	}

	public Channel withImg(String img) {
		this.img = img;
		return this;
	}

	public Channel withThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
		return this;
	}

	public Channel withRef(Ref ref) {
		this.ref = ref;
		return this;
	}

	public Channel withHead(Message head) {
		this.head = head;
		return this;
	}
	
	public Channel withPublic(Boolean publik) {
		this.publik = publik;
		return this;
	}

	public Channel withAuthorities(Authority... authorities) {
		if (this.authorities==null) {
			this.authorities = new ArrayList<>();
		}
		this.authorities.addAll(Arrays.asList(authorities));
		return this;
	}

	
	public Channel withAuthorities(Collection<Authority> authorities) {
		if (this.authorities==null) {
			this.authorities = new ArrayList<>();
		}
		if (authorities!=null) {
			this.authorities.addAll(authorities);			
		}
		return this;
	}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("name", name)
				.append("type", type)
				.append("owner", owner)
				.append("img", img)
				.append("thumbnail", thumbnail)
				.append("head", head)
				.append("public", publik)
				.append("authorities", authorities)
				;
	}
	
}

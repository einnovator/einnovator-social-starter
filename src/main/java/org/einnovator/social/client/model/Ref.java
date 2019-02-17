/**
 * 
 */
package org.einnovator.social.client.model;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ref extends ObjectBase {

	private String id;

	private String type;

	private String name;

	private String img;

	private String thumbnail;

	private String redirectUri;

	private String pingUri;

	/**
	 * Create instance of {@code Ref}.
	 *
	 */
	public Ref() {
	}
	
	/**
	 * Get the value of property {@code id}.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the value of property {@code id}.
	 *
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the value of property {@code type}.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * Get the value of property {@code redirectUri}.
	 *
	 * @return the redirectUri
	 */
	public String getRedirectUri() {
		return redirectUri;
	}

	/**
	 * Set the value of property {@code redirectUri}.
	 *
	 * @param redirectUri the redirectUri to set
	 */
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	/**
	 * Get the value of property {@code pingUri}.
	 *
	 * @return the pingUri
	 */
	public String getPingUri() {
		return pingUri;
	}

	/**
	 * Set the value of property {@code pingUri}.
	 *
	 * @param pingUri the pingUri to set
	 */
	public void setPingUri(String pingUri) {
		this.pingUri = pingUri;
	}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("id", id)
				.append("type", type)
				.append("name", name)
				.append("redirectUri", redirectUri)
				.append("pingUri", pingUri)
				;
	}



}

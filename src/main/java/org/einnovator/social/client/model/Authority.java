package org.einnovator.social.client.model;

import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Authority extends EntityBase {

	public static final Authority PUBLIC = new Authority(true);

	private String username;

	private String groupId;

	private String role;
	
	private Boolean publik;

	private Boolean read;

	private Boolean write;

	private Boolean manage;

	public Authority(Boolean publik) {
		this.publik = publik;
	}
	
	/**
	 * Get the value of property {@code username}.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the value of property {@code username}.
	 *
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Get the value of property {@code groupId}.
	 *
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * Set the value of property {@code groupId}.
	 *
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * Get the value of property {@code publik}.
	 *
	 * @return the publik
	 */
	public Boolean getPublik() {
		return publik;
	}

	/**
	 * Set the value of property {@code publik}.
	 *
	 * @param publik the publik to set
	 */
	public void setPublik(Boolean publik) {
		this.publik = publik;
	}

	/**
	 * Get the value of property {@code read}.
	 *
	 * @return the read
	 */
	public Boolean getRead() {
		return read;
	}

	/**
	 * Set the value of property {@code read}.
	 *
	 * @param read the read to set
	 */
	public void setRead(Boolean read) {
		this.read = read;
	}

	/**
	 * Get the value of property {@code write}.
	 *
	 * @return the write
	 */
	public Boolean getWrite() {
		return write;
	}

	/**
	 * Set the value of property {@code write}.
	 *
	 * @param write the write to set
	 */
	public void setWrite(Boolean write) {
		this.write = write;
	}

	/**
	 * Get the value of property {@code manage}.
	 *
	 * @return the manage
	 */
	public Boolean getManage() {
		return manage;
	}

	/**
	 * Set the value of property {@code manage}.
	 *
	 * @param manage the manage to set
	 */
	public void setManage(Boolean manage) {
		this.manage = manage;
	}

	/**
	 * Get the value of property {@code role}.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Set the value of property {@code role}.
	 *
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("username", username)
			.append("groupId", groupId)
			.append("publik", publik)
			.append("role", role)			
			.append("read", read)
			.append("write", write)
			.append("manage", manage)
			;
	}

}

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

	public Authority() {
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

	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((manage == null) ? 0 : manage.hashCode());
		result = prime * result + ((publik == null) ? 0 : publik.hashCode());
		result = prime * result + ((read == null) ? 0 : read.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((write == null) ? 0 : write.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authority other = (Authority) obj;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (manage == null) {
			if (other.manage != null)
				return false;
		} else if (!manage.equals(other.manage))
			return false;
		if (publik == null) {
			if (other.publik != null)
				return false;
		} else if (!publik.equals(other.publik))
			return false;
		if (read == null) {
			if (other.read != null)
				return false;
		} else if (!read.equals(other.read))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (write == null) {
			if (other.write != null)
				return false;
		} else if (!write.equals(other.write))
			return false;
		return true;
	}

	public static Authority user(String username, Boolean read, Boolean write, Boolean manage) {
		Authority authority = new Authority();
		authority.username = username;
		authority.read = read;
		authority.write = write;
		authority.manage = manage;
		return authority;
	}

	public static Authority publik(Boolean read, Boolean write, Boolean manage) {
		Authority authority = new Authority();
		authority.publik = true;
		authority.read = read;
		authority.write = write;
		authority.manage = manage;
		return authority;
	}

	public static Authority group(String groupId, Boolean read, Boolean write, Boolean manage) {
		Authority authority = new Authority();
		authority.groupId = groupId;
		authority.read = read;
		authority.write = write;
		authority.manage = manage;
		return authority;
	}

	public static Authority group(String groupId, String role, Boolean read, Boolean write, Boolean manage) {
		Authority authority = new Authority();
		authority.groupId = groupId;
		authority.role = role;
		authority.read = read;
		authority.write = write;
		authority.manage = manage;
		return authority;
	}
	
	
	

}
